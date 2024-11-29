package com.ecoliving.mobile.presentation.ui.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.ecoliving.mobile.data.Result
import com.ecoliving.mobile.data.pref.UserModel
import com.ecoliving.mobile.presentation.ViewModelFactory
import com.example.ecoliving.R
import com.example.ecoliving.databinding.FragmentRegisterBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    private var name: String = ""
    private var email: String = ""
    private var password: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener { registerAction() }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_onboardFragment)
        }

        binding.goLoginButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        viewModel.signUpUser.observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        errorDialogBox()
                    }

                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        successDialogBox()
                    }
                }
            }
        }
    }

    private fun registerAction() {
        name = binding.usernameFieldText.text.toString()
        email = binding.emailFieldText.text.toString()
        password = binding.passwordFieldText.text.toString()
        viewModel.signUp(name, email, password)
    }

    private fun errorDialogBox() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.register_dialog_error)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val icon: ImageView = dialog.findViewById(R.id.fail_icon_error_register)
        val text: TextView = dialog.findViewById(R.id.card_title_error_register)
        val closeButton: MaterialButton = dialog.findViewById(R.id.close_button_error_register)
        val cardView: MaterialCardView = dialog.findViewById(R.id.card_view_error_register)

        startBreathingAnimation(icon, text, closeButton, cardView)

        val window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun successDialogBox() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.register_dialog_success)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val icon: ImageView = dialog.findViewById(R.id.success_icon_register)
        val text: TextView = dialog.findViewById(R.id.card_title_success_register)
        val closeButton: MaterialButton = dialog.findViewById(R.id.login_button_success_register)
        val cardView: MaterialCardView = dialog.findViewById(R.id.card_view_success_register)

        startBreathingAnimation(icon, text, closeButton, cardView)

        val window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        closeButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            dialog.dismiss()
        }

        dialog.show()
    }


    private fun startBreathingAnimation(
        icon: ImageView,
        text: TextView,
        closeButton: MaterialButton,
        cardView: MaterialCardView
    ) {
        val scaleUpIconX = ObjectAnimator.ofFloat(icon, "scaleX", 1.0f, 1.2f)
        val scaleDownIconX = ObjectAnimator.ofFloat(icon, "scaleX", 1.2f, 1.0f)
        val scaleUpIconY = ObjectAnimator.ofFloat(icon, "scaleY", 1.0f, 1.2f)
        val scaleDownIconY = ObjectAnimator.ofFloat(icon, "scaleY", 1.2f, 1.0f)

        scaleUpIconX.duration = 500
        scaleDownIconX.duration = 500
        scaleUpIconY.duration = 500
        scaleDownIconY.duration = 500

        val scaleUpTextX = ObjectAnimator.ofFloat(text, "scaleX", 1.0f, 1.2f)
        val scaleDownTextX = ObjectAnimator.ofFloat(text, "scaleX", 1.2f, 1.0f)
        val scaleUpTextY = ObjectAnimator.ofFloat(text, "scaleY", 1.0f, 1.2f)
        val scaleDownTextY = ObjectAnimator.ofFloat(text, "scaleY", 1.2f, 1.0f)

        scaleUpTextX.duration = 500
        scaleDownTextX.duration = 500
        scaleUpTextY.duration = 500
        scaleDownTextY.duration = 500

        val scaleUpButtonX = ObjectAnimator.ofFloat(closeButton, "scaleX", 1.0f, 1.2f)
        val scaleDownButtonX = ObjectAnimator.ofFloat(closeButton, "scaleX", 1.2f, 1.0f)
        val scaleUpButtonY = ObjectAnimator.ofFloat(closeButton, "scaleY", 1.0f, 1.2f)
        val scaleDownButtonY = ObjectAnimator.ofFloat(closeButton, "scaleY", 1.2f, 1.0f)

        scaleUpButtonX.duration = 500
        scaleDownButtonX.duration = 500
        scaleUpButtonY.duration = 500
        scaleDownButtonY.duration = 500

        val scaleUpCardX = ObjectAnimator.ofFloat(cardView, "scaleX", 1.0f, 1.05f)
        val scaleDownCardX = ObjectAnimator.ofFloat(cardView, "scaleX", 1.05f, 1.0f)
        val scaleUpCardY = ObjectAnimator.ofFloat(cardView, "scaleY", 1.0f, 1.05f)
        val scaleDownCardY = ObjectAnimator.ofFloat(cardView, "scaleY", 1.05f, 1.0f)

        scaleUpCardX.duration = 500
        scaleDownCardX.duration = 500
        scaleUpCardY.duration = 500
        scaleDownCardY.duration = 500

        val iconAnimatorSet = AnimatorSet()
        iconAnimatorSet.play(scaleUpIconX).before(scaleDownIconX)
        iconAnimatorSet.play(scaleUpIconY).before(scaleDownIconY)

        val textAnimatorSet = AnimatorSet()
        textAnimatorSet.play(scaleUpTextX).before(scaleDownTextX)
        textAnimatorSet.play(scaleUpTextY).before(scaleDownTextY)

        val buttonAnimatorSet = AnimatorSet()
        buttonAnimatorSet.play(scaleUpButtonX).before(scaleDownButtonX)
        buttonAnimatorSet.play(scaleUpButtonY).before(scaleDownButtonY)

        val cardAnimatorSet = AnimatorSet()
        cardAnimatorSet.play(scaleUpCardX).before(scaleDownCardX)
        cardAnimatorSet.play(scaleUpCardY).before(scaleDownCardY)

        val combinedAnimatorSet = AnimatorSet()
        combinedAnimatorSet.play(iconAnimatorSet).with(textAnimatorSet).with(buttonAnimatorSet)
            .with(cardAnimatorSet)

        combinedAnimatorSet.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}