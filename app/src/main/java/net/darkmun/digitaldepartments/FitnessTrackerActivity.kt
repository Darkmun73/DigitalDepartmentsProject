package net.darkmun.digitaldepartments

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import net.darkmun.digitaldepartments.databinding.ActivityFitnessTrackerBinding


class FitnessTrackerActivity : AppCompatActivity(), OnItemSelectedListener {

    private lateinit var fitnessTrackerBinding: ActivityFitnessTrackerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        fitnessTrackerBinding = ActivityFitnessTrackerBinding.inflate(layoutInflater)
        setContentView(fitnessTrackerBinding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fitness_tracker_frag_container,
                    ActivityFragment.newInstance(),
                    "Activity fragment")
                commit()
            }
        }

        fitnessTrackerBinding.mainBnv.setOnItemSelectedListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(fitnessTrackerBinding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragActivity = supportFragmentManager.findFragmentByTag("Activity fragment")
        val fragActivityDetails = supportFragmentManager.findFragmentByTag("Activity details fragment")
        val fragProfile = supportFragmentManager.findFragmentByTag("Profile fragment")

        when(item.itemId) {
            R.id.activity -> {
                supportFragmentManager.beginTransaction().apply {
                    if (fragActivity != null) {
                        show(fragActivity)
                    }
                    if (fragActivityDetails != null) {
                        show(fragActivityDetails)
                    }
                    if (fragProfile != null) {
                        hide(fragProfile)
                    }
                    commit()
                }
            }
            R.id.profile -> {
                supportFragmentManager.beginTransaction().apply {
                    if (fragActivity != null) {
                        hide(fragActivity)
                    }
                    if (fragActivityDetails != null) {
                        hide(fragActivityDetails)
                    }
                    if (fragProfile != null) {
                        show(fragProfile)
                    } else {
                        add(R.id.fitness_tracker_frag_container,
                            ProfileFragment.newInstance(),
                            "Profile fragment")
                    }
                    commit()
                }
                if (fragActivityDetails != null) {
                    supportFragmentManager.popBackStack("Activity details fragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                }
            }
        }
        return true
    }

}