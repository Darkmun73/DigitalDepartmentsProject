package net.darkmun.digitaldepartments.activity.new_activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.darkmun.digitaldepartments.R
import net.darkmun.digitaldepartments.activity.database.ActivityDBSingleton
import net.darkmun.digitaldepartments.activity.database.ActivityInfo
import net.darkmun.digitaldepartments.activity.database.ActivityType
import net.darkmun.digitaldepartments.activity.database.Location
import net.darkmun.digitaldepartments.activity.new_activity.type_recycler.ActivityTypesAdapter
import net.darkmun.digitaldepartments.activity.new_activity.type_recycler.NewActivityTypeItemDecoration
import net.darkmun.digitaldepartments.databinding.FragmentNewActivityBinding
import java.time.LocalDateTime
import kotlin.random.Random

class NewActivityFragment : Fragment() {

    private var types : List<ActivityTypeInfo> = listOf(
        ActivityTypeInfo(ActivityType.BICYCLE, R.drawable.ic_activity_type_bicycles),
        ActivityTypeInfo(ActivityType.RUNNING, R.drawable.ic_activity_type_bicycles),
        ActivityTypeInfo(ActivityType.WALKING, R.drawable.ic_activity_type_bicycles)
    )

    private lateinit var newActivityBinding: FragmentNewActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newActivityBinding = FragmentNewActivityBinding.inflate(inflater, container, false)

        val adapter = ActivityTypesAdapter(types)

        val recyclerView = newActivityBinding.recyclerViewNew
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        val spacing = resources.getDimensionPixelSize(R.dimen.rv_new_activity_type_item_margin_start)
        recyclerView.addItemDecoration(NewActivityTypeItemDecoration(spacing))


        return newActivityBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newActivityBinding.newActivityStartButton.setOnClickListener(StartButtonClickListener())
//            parentFragmentManager.beginTransaction().apply {
//                add(R.id.new_activity_container,
//                    NewActivityProcessFragment.newInstance(),
//                    "New activity process fragment")
//                remove(this@NewActivityFragment)
//                commit()
//            }
    }

    inner class StartButtonClickListener: OnClickListener {
        override fun onClick(p0: View?) {
            val typeName = (newActivityBinding.recyclerViewNew.adapter as ActivityTypesAdapter).getSelectedType()

        //------------------- Random values -----------------------
            val path: MutableList<Location> = mutableListOf()
            for (i in 1..Random.nextInt(2, 15)) {
                path.add(
                    Location(Random.nextDouble(-90.0, 90.0), Random.nextDouble(-180.0, 180.0))
                )
            }
            val startDateTime = LocalDateTime.of(
                Random.nextInt(2018, 2026),
                Random.nextInt(1, 13),
                Random.nextInt(1, 31),
                Random.nextInt(0, 24),
                Random.nextInt(0, 60)
            )
            val finishDateTime = LocalDateTime.of(
                startDateTime.year,
                startDateTime.month,
                startDateTime.dayOfMonth + Random.nextInt(0,2),
                startDateTime.hour + Random.nextInt(0, 24 - startDateTime.hour),
                startDateTime.minute + Random.nextInt(0,60 - startDateTime.minute)
            )
        //------------------- Random values -----------------------

            val newMyActivity = ActivityInfo.MyActivityInfo(
                0,
                typeName,
                startDateTime,
                finishDateTime,
                path
            )
            ActivityDBSingleton.roomDB.myActivityDAO().insert(newMyActivity)
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = NewActivityFragment()
    }
}