package net.darkmun.digitaldepartments.activity_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import net.darkmun.digitaldepartments.R
import net.darkmun.digitaldepartments.activities_recycler.ActivityInfo
import net.darkmun.digitaldepartments.databinding.FragmentMyActivityDetailsBinding

private const val ACTIVITY_NAME_ARG = "activity_name"
private const val ACTIVITY_DATE_ARG = "activity_date"
private const val DISTANCE_ARG = "distance"
private const val TIME_ARG = "time"
private const val START_TIME_ARG = "start_time"
private const val FINISH_TIME_ARG = "finish_time"

class MyActivityDetailsFragment : Fragment() {

    private lateinit var myActivityDetailsBinding: FragmentMyActivityDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myActivityDetailsBinding = FragmentMyActivityDetailsBinding.inflate(inflater, container, false)

        arguments?.let {
            myActivityDetailsBinding.activityDetailsToolbar.title = it.getString(ACTIVITY_NAME_ARG)
            myActivityDetailsBinding.activityDate.text = it.getString(ACTIVITY_DATE_ARG)
            myActivityDetailsBinding.distance.text = it.getString(DISTANCE_ARG)
            myActivityDetailsBinding.time.text = it.getString(TIME_ARG)
            myActivityDetailsBinding.startTime.text = it.getString(START_TIME_ARG)
            myActivityDetailsBinding.finishTime.text = it.getString(FINISH_TIME_ARG)
        }

        return myActivityDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolBar = myActivityDetailsBinding.activityDetailsToolbar
        toolBar.addMenuProvider(MyActivityDetailsMenuProvider(), viewLifecycleOwner)
        toolBar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
    }

    companion object {
        @JvmStatic
        fun newInstance(activityInfo: ActivityInfo.MyActivityInfo) =
            MyActivityDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ACTIVITY_NAME_ARG, activityInfo.activityName)
                    putString(ACTIVITY_DATE_ARG, activityInfo.activityDate)
                    putString(DISTANCE_ARG, activityInfo.distance)
                    putString(TIME_ARG, activityInfo.time)
                    putString(START_TIME_ARG, activityInfo.startTime)
                    putString(FINISH_TIME_ARG, activityInfo.finishTime)
                }
            }
    }

    inner class MyActivityDetailsMenuProvider : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.toolbar_activity_details_menu, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            TODO("Not yet implemented")
        }
    }
}