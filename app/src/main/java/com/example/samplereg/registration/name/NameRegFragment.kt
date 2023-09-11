package com.example.samplereg.registration.name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.samplereg.databinding.FragmentNameRegBinding
import com.example.samplereg.registration.onMultipleTextFieldsChanges
import com.example.samplereg.registration.stringText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NameRegFragment : Fragment() {

    private val viewModel: NameRegViewModel by viewModels()

    private var _binding: FragmentNameRegBinding? = null
    private val binding: FragmentNameRegBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentNameRegBinding.inflate(inflater, container, false).run {
            _binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel.state.observe(viewLifecycleOwner) { state ->
                firstNameInput.error = state.firstNameError
                lastNameInput.error = state.lastNameError
                nextButton.isEnabled = state.isNextAvailable
            }

            onMultipleTextFieldsChanges(firstNameInput, lastNameInput) {
                viewModel.onFieldChanges(firstNameInput.stringText, lastNameInput.stringText)
            }

            nextButton.setOnClickListener {
                viewModel.next()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
