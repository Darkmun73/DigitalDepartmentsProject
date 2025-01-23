package net.darkmun.digitaldepartments.activity.new_activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.darkmun.digitaldepartments.databinding.FragmentNewActivityProcessBinding

class NewActivityProcessFragment : Fragment() {

    private lateinit var newActivityProcessBinding: FragmentNewActivityProcessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newActivityProcessBinding = FragmentNewActivityProcessBinding.inflate(inflater, container, false)

        newActivityProcessBinding.finishActivityFab.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed() // for now just this
        }

        return newActivityProcessBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewActivityProcessFragment()
    }
}