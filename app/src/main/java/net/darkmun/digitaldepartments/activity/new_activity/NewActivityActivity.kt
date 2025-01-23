package net.darkmun.digitaldepartments.activity.new_activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.darkmun.digitaldepartments.R
import net.darkmun.digitaldepartments.databinding.ActivityNewActivityBinding

class NewActivityActivity : AppCompatActivity() {

    private lateinit var newActivityBinding: ActivityNewActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        newActivityBinding = ActivityNewActivityBinding.inflate(layoutInflater)
        setContentView(newActivityBinding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.new_activity_container,
                    NewActivityFragment.newInstance(),
                    "New activity fragment")
                commit()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}