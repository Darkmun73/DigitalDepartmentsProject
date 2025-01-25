package net.darkmun.digitaldepartments.activity.new_activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.darkmun.digitaldepartments.R
import net.darkmun.digitaldepartments.activity.new_activity.type_recycler.ActivityTypesAdapter
import net.darkmun.digitaldepartments.activity.new_activity.type_recycler.NewActivityTypeItemDecoration
import net.darkmun.digitaldepartments.databinding.FragmentNewActivityBinding

class NewActivityFragment : Fragment() {

    private lateinit var types : List<ActivityTypeInfo>

    private lateinit var newActivityBinding: FragmentNewActivityBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        types = listOf(
            ActivityTypeInfo(context.getString(R.string.activity_type_bicycle), R.drawable.ic_activity_type_bicycles),
            ActivityTypeInfo(context.getString(R.string.activity_type_running), R.drawable.ic_activity_type_bicycles),
            ActivityTypeInfo(context.getString(R.string.activity_type_walking), R.drawable.ic_activity_type_bicycles)
        )
    }

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

        newActivityBinding.newActivityStartButton.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                add(R.id.new_activity_container,
                    NewActivityProcessFragment.newInstance(),
                    "New activity process fragment")
                remove(this@NewActivityFragment)
                commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewActivityFragment()
    }
}