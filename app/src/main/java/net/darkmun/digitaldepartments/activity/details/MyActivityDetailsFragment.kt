package net.darkmun.digitaldepartments.activity.details

import android.content.Context
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
import net.darkmun.digitaldepartments.activity.database.ActivityInfo
import net.darkmun.digitaldepartments.activity.database.ActivityType
import net.darkmun.digitaldepartments.databinding.FragmentMyActivityDetailsBinding
import java.time.ZoneOffset

private const val ACTIVITY_NAME_ARG = "activity_name"
private const val ACTIVITY_DATE_ARG = "activity_date"
private const val DISTANCE_ARG = "distance"
private const val DURATION_ARG = "duration"
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
            myActivityDetailsBinding.duration.text = it.getString(DURATION_ARG)
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
        fun newInstance(activityInfo: ActivityInfo.MyActivityInfo, context: Context): MyActivityDetailsFragment {
            val distanceStr = "%.2f".format(activityInfo.distance/1000)
            val distanceWithMeasureStr = context.getString(R.string.distance_measure_template, distanceStr)

            val hoursInt = activityInfo.duration.toHours().toInt()
            val minutesInt = activityInfo.duration.toMinutesPart()
            val hoursString = context.resources.getQuantityString(R.plurals.hours, hoursInt, hoursInt)
            val minutesString = context.resources.getQuantityString(R.plurals.minutes, minutesInt, minutesInt)
            val durationStr = context.getString(R.string.duration_template, hoursString, minutesString)

            val activityType =
                when(activityInfo.getType()) {
                    ActivityType.BICYCLE -> context.getString(R.string.activity_type_bicycle)
                    ActivityType.RUNNING -> context.getString(R.string.activity_type_running)
                    ActivityType.WALKING -> context.getString(R.string.activity_type_walking)
                }

            val dateMillis = activityInfo.getDate().toInstant(ZoneOffset.UTC).toEpochMilli()
            val activityDateStr = context.getString(R.string.activity_date_template, dateMillis, dateMillis, dateMillis)

            val startTime = activityInfo.getStartTime().toSecondOfDay() * 1000L
            val finishTime = activityInfo.getFinishTime().toSecondOfDay() * 1000L
            val startTimeStr = context.getString(R.string.time_template, startTime, startTime)
            val finishTimeStr = context.getString(R.string.time_template, finishTime, finishTime)

            return MyActivityDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ACTIVITY_NAME_ARG, activityType)
                    putString(ACTIVITY_DATE_ARG, activityDateStr)
                    putString(DISTANCE_ARG, distanceWithMeasureStr)
                    putString(DURATION_ARG, durationStr)
                    putString(START_TIME_ARG, startTimeStr)
                    putString(FINISH_TIME_ARG, finishTimeStr)
                }
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