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
import net.darkmun.digitaldepartments.databinding.FragmentMyActivitiesBinding

class MyActivitiesFragment : Fragment() {

    private lateinit var myActivitiesBinding: FragmentMyActivitiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myActivitiesBinding = FragmentMyActivitiesBinding.inflate(inflater, container, false)

        val recyclerView = myActivitiesBinding.recyclerViewMy

        val adapter = ActivitiesAdapter(ActivitiesExamples.getMyActivitiesAndDateSections())
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter

        val spacing = resources.getDimensionPixelSize(R.dimen.rv_item_margin_top)
        recyclerView.addItemDecoration(CustomItemDecoration(spacing))

        return myActivitiesBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MyActivitiesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}