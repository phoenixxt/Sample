package com.example.samplereg.registration.additional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.samplereg.databinding.FragmentAdditionalRegBinding
import com.example.samplereg.registration.onMultipleTextFieldsChanges
import com.example.samplereg.registration.stringText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdditionalRegFragment : Fragment() {

    private val viewModel: AdditionalRegViewModel by viewModels()

    private var _binding: FragmentAdditionalRegBinding? = null
    private val binding: FragmentAdditionalRegBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAdditionalRegBinding.inflate(inflater, container, false).run {
            _binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel.state.observe(viewLifecycleOwner) { state ->
                firstAdditionalInput.error = state.firstAdditionalError
                secondAdditionalInput.error = state.secondAdditionalError
                doneButton.isEnabled = state.isDoneAvailable
            }

            onMultipleTextFieldsChanges(firstAdditionalInput, secondAdditionalInput, thirdAdditionalInput) {
                viewModel.onFieldChanges(
                    firstAdditionalInput.stringText,
                    secondAdditionalInput.stringText,
                    thirdAdditionalInput.stringText
                )
            }

            doneButton.setOnClickListener {
                viewModel.next()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
