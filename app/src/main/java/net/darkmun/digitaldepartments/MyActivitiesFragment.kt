package net.darkmun.digitaldepartments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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