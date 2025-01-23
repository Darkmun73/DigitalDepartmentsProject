package net.darkmun.digitaldepartments.activity.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.darkmun.digitaldepartments.R
import net.darkmun.digitaldepartments.activity.ActivityInfo
import net.darkmun.digitaldepartments.databinding.ItemDateBinding
import net.darkmun.digitaldepartments.databinding.ItemMyActivityBinding
import net.darkmun.digitaldepartments.databinding.ItemUserActivityBinding

class ActivitiesAdapter(private val activitiesAndDates : List<ActivityInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private var itemClickListener: (ActivityInfo) -> Unit = {}

    companion object {
        private const val MY_ACTIVITY = 1
        private const val USER_ACTIVITY = 2
        private const val DATE_SECTION = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when(activitiesAndDates[position]) {
            is ActivityInfo.MyActivityInfo -> {
                MY_ACTIVITY
            }

            is ActivityInfo.UserActivityInfo -> {
                USER_ACTIVITY
            }

            //ActivityInfo.DateSectionInfo
            else -> {
                DATE_SECTION
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            MY_ACTIVITY -> {
                val myActivityBinding = ItemMyActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MyActivityVH(myActivityBinding)
            }

            USER_ACTIVITY -> {
                val userActivityBinding = ItemUserActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return UserActivityVH(userActivityBinding)
            }

            //DATE_SECTION
            else -> {
                val dateBinding = ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return DateSectionVH(dateBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is MyActivityVH -> holder.bind(activitiesAndDates[position] as ActivityInfo.MyActivityInfo)
            is UserActivityVH -> holder.bind(activitiesAndDates[position] as ActivityInfo.UserActivityInfo)
            is DateSectionVH -> holder.bind(activitiesAndDates[position] as ActivityInfo.DateSectionInfo)
        }
    }

    override fun getItemCount(): Int {
        return activitiesAndDates.count()
    }

    fun setItemClickListener(listener: (ActivityInfo) -> Unit) {
        itemClickListener = listener
    }

    inner class MyActivityVH(itemBinding: ItemMyActivityBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val distance = itemBinding.distance
        private val time = itemBinding.time
        private val activityName = itemBinding.activityName
        private val activityDate = itemBinding.activityDate

        init {
            itemBinding.root.setOnClickListener {
                itemClickListener.invoke(activitiesAndDates[adapterPosition])
            }
        }

        fun bind(activityInfo: ActivityInfo.MyActivityInfo) {
            distance.text = activityInfo.distance
            time.text = activityInfo.time
            activityName.text = activityInfo.activityName
            activityDate.text = activityInfo.activityDate
        }
    }

    inner class UserActivityVH(itemBinding: ItemUserActivityBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val root = itemBinding.root

        private val distance = itemBinding.distance
        private val time = itemBinding.time
        private val activityName = itemBinding.activityName
        private val activityDate = itemBinding.activityDate
        private val username = itemBinding.username

        init {
            itemBinding.root.setOnClickListener {
                itemClickListener.invoke(activitiesAndDates[adapterPosition])
            }
        }

        fun bind(activityInfo: ActivityInfo.UserActivityInfo) {
            distance.text = activityInfo.distance
            time.text = activityInfo.time
            activityName.text = activityInfo.activityName
            activityDate.text = activityInfo.activityDate
            username.text = root.context.getString(R.string.username_template, activityInfo.userName)
        }
    }

    inner class DateSectionVH(itemBinding: ItemDateBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val dateSection = itemBinding.dateSection

        fun bind(dateSectionInfo: ActivityInfo.DateSectionInfo) {
            dateSection.text = dateSectionInfo.activityDate
        }
    }

}