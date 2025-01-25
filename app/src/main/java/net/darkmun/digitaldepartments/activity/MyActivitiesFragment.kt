package net.darkmun.digitaldepartments.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import net.darkmun.digitaldepartments.R
import net.darkmun.digitaldepartments.activity.database.ActivityInfo
import net.darkmun.digitaldepartments.activity.database.ActivityDBSingleton
import net.darkmun.digitaldepartments.activity.recycler.ActivitiesAdapter
import net.darkmun.digitaldepartments.activity.details.MyActivityDetailsFragment
import net.darkmun.digitaldepartments.activity.recycler.ActivityItemDecoration
import net.darkmun.digitaldepartments.databinding.FragmentMyActivitiesBinding

class MyActivitiesFragment : Fragment() {

    private lateinit var myActivitiesBinding: FragmentMyActivitiesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myActivitiesBinding = FragmentMyActivitiesBinding.inflate(inflater, container, false)

        val adapter = ActivitiesAdapter(ActivitiesExamples.getMyActivitiesAndDateSections())
        // TODO: При быстром нажатии может открыться несколько фрагментов
        adapter.setItemClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                add(R.id.fitness_tracker_frag_container,
                    MyActivityDetailsFragment.newInstance(it as ActivityInfo.MyActivityInfo, requireContext()),
                    "Activity details fragment")
                addToBackStack("Activity details fragment")
                setReorderingAllowed(true)
                commit()
            }
        }
        ActivityDBSingleton.roomDB.myActivityDAO().getAll().observe(viewLifecycleOwner) {
            adapter.setActivities(it)
        }

        val recyclerView = myActivitiesBinding.recyclerViewMy
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
        recyclerView.setEmptyView(myActivitiesBinding.emptyRecyclerView)


        val spacing = resources.getDimensionPixelSize(R.dimen.rv_item_margin_top)
        recyclerView.addItemDecoration(ActivityItemDecoration(spacing))

        return myActivitiesBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MyActivitiesFragment()
    }
}