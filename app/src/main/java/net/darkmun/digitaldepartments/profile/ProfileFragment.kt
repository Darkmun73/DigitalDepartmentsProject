package net.darkmun.digitaldepartments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.darkmun.digitaldepartments.R
import net.darkmun.digitaldepartments.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var profileBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        profileBinding.changePassword.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                add(R.id.fitness_tracker_frag_container,
                    ChangePasswordFragment.newInstance(),
                    "Change password fragment")
                addToBackStack("Change password fragment")
                commit()
            }
        }
        return profileBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}