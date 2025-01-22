package net.darkmun.digitaldepartments

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.darkmun.digitaldepartments.databinding.ActivityRegistrationBinding


class RegistrationActivity : AppCompatActivity() {

    private lateinit var registrationBinding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        registrationBinding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(registrationBinding.root)

        val toolBar = registrationBinding.registrationToolbar
        setSupportActionBar(toolBar)

        makePolicyAgreementTextClickable()

        ViewCompat.setOnApplyWindowInsetsListener(registrationBinding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun makePolicyAgreementTextClickable() {
        val policy = registrationBinding.policy

        val policyAgreement1 = getString(R.string.policy_agreement_1)
        val policyAgreementLink1 = getString(R.string.policy_agreement_link_1)
        val policyAgreement2 = getString(R.string.policy_agreement_2)
        val policyAgreementLink2 = getString(R.string.policy_agreement_link_2)

        val spannableText = SpannableStringBuilder("$policyAgreement1 ")
        spannableText.append(policyAgreementLink1)
        spannableText.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                // TODO: something?
            }
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }, spannableText.length - policyAgreementLink1.length, spannableText.length, 0)

        spannableText.append(" $policyAgreement2 ")
        spannableText.append(policyAgreementLink2)
        spannableText.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                // TODO: something?
            }
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }, spannableText.length - policyAgreementLink2.length, spannableText.length, 0)

        policy.movementMethod = LinkMovementMethod.getInstance()
        policy.setText(spannableText, TextView.BufferType.SPANNABLE)
    }
}