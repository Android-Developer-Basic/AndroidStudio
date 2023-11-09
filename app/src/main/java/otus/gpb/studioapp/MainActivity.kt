package otus.gpb.studioapp

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Property
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.Button
import android.widget.TextView
import otus.gpb.mylibrary.Printer

class MainActivity : AppCompatActivity() {
    private lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById<TextView>(R.id.text)
        text.text = Printer.print()
        val animation = ObjectAnimator.ofFloat(text, View.SCALE_X, 1.0f, 0.0f).apply {
            duration = 2000
            repeatCount = 3
            repeatMode = ObjectAnimator.REVERSE
            interpolator = BounceInterpolator()
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            animation.start()
        }
    }
}