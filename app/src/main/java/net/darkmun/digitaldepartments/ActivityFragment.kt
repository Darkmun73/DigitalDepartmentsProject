package net.darkmun.digitaldepartments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import net.darkmun.digitaldepartments.databinding.FragmentActivityBinding

class ActivityFragment : Fragment() {

    private lateinit var fragmentCollectionAdapter: FragmentCollectionAdapter
    private lateinit var activityBinding: FragmentActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityBinding = FragmentActivityBinding.inflate(inflater, container, false)
        return activityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentCollectionAdapter = FragmentCollectionAdapter(this)
        activityBinding.pager.adapter = fragmentCollectionAdapter

        TabLayoutMediator(activityBinding.tabLayoutFitness, activityBinding.pager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.my)
                1 -> tab.text = getString(R.string.users)
            }
        }.attach()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ActivityFragment()
    }
}

class FragmentCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> {
                Log.w("debug", "creating1")
                return MyActivitiesFragment.newInstance()
            }
            1 -> {
                Log.w("debug", "creating2")
                return UsersActivitiesFragment.newInstance()
            }
        }
        return MyActivitiesFragment.newInstance()
    }
}
