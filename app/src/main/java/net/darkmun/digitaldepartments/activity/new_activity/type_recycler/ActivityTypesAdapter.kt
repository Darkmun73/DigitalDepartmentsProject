package net.darkmun.digitaldepartments.activity.new_activity.type_recycler

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors
import net.darkmun.digitaldepartments.R
import net.darkmun.digitaldepartments.activity.database.ActivityType
import net.darkmun.digitaldepartments.activity.new_activity.ActivityTypeInfo
import net.darkmun.digitaldepartments.databinding.ItemNewActivityTypeBinding

class ActivityTypesAdapter(private val types : List<ActivityTypeInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedItemPosition : Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemNewActivityTypeBinding = ItemNewActivityTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActivityTypeVH(itemNewActivityTypeBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ActivityTypeVH).bind(position)
    }

    override fun getItemCount(): Int {
        return types.count()
    }

    fun getSelectedType(): ActivityType {
        return types[selectedItemPosition].type
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class ActivityTypeVH(private val itemBinding : ItemNewActivityTypeBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        init {
            itemBinding.root.setOnClickListener {
                selectedItemPosition = adapterPosition
                notifyDataSetChanged() // notifyItemChanged is bugged
            }
        }

        fun bind(position: Int) {
            val root = itemBinding.root
            val resources = root.resources

            val strokeWidth : Int
            val strokeColor : Int
            if (position == selectedItemPosition) {
                root.elevation = resources.getDimension(R.dimen.rv_new_activity_type_item_selected_elevation)
                strokeWidth = resources.getDimensionPixelSize(R.dimen.extended_stroke_width)
                strokeColor = MaterialColors.getColor(itemBinding.root, com.google.android.material.R.attr.colorPrimary)
            } else {
                root.elevation = 0F
                strokeWidth = resources.getDimensionPixelSize(R.dimen.default_stroke_width)
                strokeColor = ContextCompat.getColor(itemBinding.root.context, R.color.light_gray_2)
            }

            (root.background as GradientDrawable).apply {
                mutate()
                setStroke(strokeWidth, strokeColor)
            }


            itemBinding.activityTypeName.text =
                when(types[position].type) {
                    ActivityType.BICYCLE -> resources.getString(R.string.activity_type_bicycle)
                    ActivityType.RUNNING -> resources.getString(R.string.activity_type_running)
                    ActivityType.WALKING -> resources.getString(R.string.activity_type_walking)
                }
            itemBinding.activityTypeImage.setImageResource(types[position].image)
        }
    }
}