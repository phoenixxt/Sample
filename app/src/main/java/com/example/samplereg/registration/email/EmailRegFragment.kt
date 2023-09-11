package com.example.samplereg.registration.email

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.samplereg.databinding.FragmentEmailRegBinding
import com.example.samplereg.registration.onMultipleTextFieldsChanges
import com.example.samplereg.registration.stringText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailRegFragment : Fragment() {

    private val viewModel: EmailViewModel by viewModels()

    private var _binding: FragmentEmailRegBinding? = null
    private val binding: FragmentEmailRegBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEmailRegBinding.inflate(inflater, container, false).run {
            _binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel.state.observe(viewLifecycleOwner) { state ->
                emailInput.error = state.emailError
                repeatEmailInput.error = state.repeatEmailError
                nextButton.isEnabled = state.isNextAvailable
            }

            onMultipleTextFieldsChanges(emailInput, repeatEmailInput) {
                viewModel.onFieldChanges(emailInput.stringText, repeatEmailInput.stringText)
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
