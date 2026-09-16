package otus.gpb.studioapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import otus.gpb.mylibrary.Repository
import otus.gpb.studioapp.ui.theme.StudioAppTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        repository = Repository(applicationContext)

        setContent {
            StudioAppTheme {
                MainScreen(repository)
            }
        }
    }
}
