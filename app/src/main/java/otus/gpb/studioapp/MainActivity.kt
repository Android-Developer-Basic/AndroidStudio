package otus.gpb.studioapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import otus.gpb.mylibrary.Repository

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var repository: Repository
    private val employeeAdapter = EmployeeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = Repository(applicationContext)

        findViewById<RecyclerView>(R.id.recycler).apply {
            adapter = ConcatAdapter(HeaderAdapter(), employeeAdapter)
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(
                MaterialDividerItemDecoration(
                    context,
                    MaterialDividerItemDecoration.VERTICAL
                )
            )
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            getNetwork()
        }

        observeEmployees()
    }

    private fun observeEmployees() {
        repository.employees.onEach { employeeAdapter.submitList(it) }.launchIn(lifecycleScope)
    }

    private fun getNetwork() {
        lifecycleScope.launch {
            repository.update()
                .onFailure { error ->
                    Toast.makeText(this@MainActivity, "Network error: $error", Toast.LENGTH_SHORT).show()
                    Log.w(TAG, "Network error:", error)
                }
                .onSuccess { count ->
                    Toast.makeText(this@MainActivity, "Network success. Total: $count", Toast.LENGTH_SHORT).show()
                    Log.i(TAG,"Network success. Total: $count")
                }
        }
    }
}