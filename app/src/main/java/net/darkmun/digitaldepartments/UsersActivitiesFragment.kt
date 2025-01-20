package net.darkmun.digitaldepartments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.darkmun.digitaldepartments.databinding.FragmentUsersActivitiesBinding

class UsersActivitiesFragment : Fragment() {

    private lateinit var usersActivitiesBinding: FragmentUsersActivitiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        usersActivitiesBinding = FragmentUsersActivitiesBinding.inflate(inflater, container, false)
        return usersActivitiesBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UsersActivitiesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}