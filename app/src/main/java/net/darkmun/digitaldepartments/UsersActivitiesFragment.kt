package net.darkmun.digitaldepartments

import CustomItemDecoration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import net.darkmun.digitaldepartments.activities_recycler.ActivitiesAdapter
import net.darkmun.digitaldepartments.activities_recycler.ActivitiesExamples
import net.darkmun.digitaldepartments.activities_recycler.ActivityInfo
import net.darkmun.digitaldepartments.activity_details.UserActivityDetailsFragment
import net.darkmun.digitaldepartments.databinding.FragmentUsersActivitiesBinding

class UsersActivitiesFragment : Fragment() {

    private lateinit var usersActivitiesBinding: FragmentUsersActivitiesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        usersActivitiesBinding = FragmentUsersActivitiesBinding.inflate(inflater, container, false)


        val adapter = ActivitiesAdapter(ActivitiesExamples.getUsersActivitiesAndDateSections())
        // TODO: При быстром нажатии может открыться несколько фрагментов
        adapter.setItemClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                add(R.id.fitness_tracker_frag_container,
                    UserActivityDetailsFragment.newInstance(it as ActivityInfo.UserActivityInfo),
                    "Activity details fragment")
                addToBackStack("Activity details fragment")
                setReorderingAllowed(true)
                commit()
            }
        }

        val recyclerView = usersActivitiesBinding.recyclerViewUser

        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter

        val spacing = resources.getDimensionPixelSize(R.dimen.rv_item_margin_top)
        recyclerView.addItemDecoration(CustomItemDecoration(spacing))

        return usersActivitiesBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = UsersActivitiesFragment()
    }
}