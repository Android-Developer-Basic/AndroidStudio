package otus.gpb.studioapp

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import otus.gpb.mylibrary.NetworkService

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var text: TextView
    private val employeeAdapter = EmployeeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    }

    private fun getNetwork() {
        lifecycleScope.launch {
            val employees = withContext(Dispatchers.IO) { NetworkService.getEmployees() }
                .onFailure { error ->
                    Toast.makeText(this@MainActivity, "Network error: $error", Toast.LENGTH_SHORT).show()
                    Log.w(TAG, "Network error:", error)
                }
                .onSuccess { response ->
                    Toast.makeText(this@MainActivity, "Network success. Total: ${ response.size }", Toast.LENGTH_SHORT).show()
                    Log.i(TAG,"Network success. Total: ${ response.size }")
                }
                .getOrNull()

            employeeAdapter.submitList(employees.orEmpty())
        }
    }
}