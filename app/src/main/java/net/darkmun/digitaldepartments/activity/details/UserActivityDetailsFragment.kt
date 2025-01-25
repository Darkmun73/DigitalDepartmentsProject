package net.darkmun.digitaldepartments.activity.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.darkmun.digitaldepartments.R
import net.darkmun.digitaldepartments.activity.database.ActivityInfo
import net.darkmun.digitaldepartments.databinding.FragmentUserActivityDetailsBinding

private const val ACTIVITY_NAME_ARG = "activity_name"
private const val ACTIVITY_DATE_ARG = "activity_date"
private const val USERNAME_ARG = "username"
private const val DISTANCE_ARG = "distance"
private const val TIME_ARG = "time"
private const val START_TIME_ARG = "start_time"
private const val FINISH_TIME_ARG = "finish_time"
private const val COMMENTARY_ARG = "commentary"

class UserActivityDetailsFragment : Fragment() {

    private lateinit var userActivityDetailsBinding: FragmentUserActivityDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userActivityDetailsBinding = FragmentUserActivityDetailsBinding.inflate(inflater, container, false)

        arguments?.let {
            userActivityDetailsBinding.activityDetailsToolbar.title = it.getString(ACTIVITY_NAME_ARG)
            userActivityDetailsBinding.activityDate.text = it.getString(ACTIVITY_DATE_ARG)
            userActivityDetailsBinding.username.text = it.getString(USERNAME_ARG)
            userActivityDetailsBinding.distance.text = it.getString(DISTANCE_ARG)
            userActivityDetailsBinding.duration.text = it.getString(TIME_ARG)
            userActivityDetailsBinding.startTime.text = it.getString(START_TIME_ARG)
            userActivityDetailsBinding.finishTime.text = it.getString(FINISH_TIME_ARG)
            userActivityDetailsBinding.commentary.editText!!.setText(it.getString(COMMENTARY_ARG))
        }

        return userActivityDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolBar = userActivityDetailsBinding.activityDetailsToolbar
        toolBar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
    }

    companion object {
        @JvmStatic
        fun newInstance(activityInfo: ActivityInfo.UserActivityInfo, context: Context) =
            UserActivityDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ACTIVITY_NAME_ARG, activityInfo.activityName)
                    putString(ACTIVITY_DATE_ARG, activityInfo.activityDate)
                    putString(USERNAME_ARG, context.getString(R.string.username_template, activityInfo.userName))
                    putString(DISTANCE_ARG, activityInfo.distance)
                    putString(TIME_ARG, activityInfo.duration)
                    putString(START_TIME_ARG, activityInfo.startTime)
                    putString(FINISH_TIME_ARG, activityInfo.finishTime)
                    putString(COMMENTARY_ARG, activityInfo.commentary)
                }
            }
    }
}