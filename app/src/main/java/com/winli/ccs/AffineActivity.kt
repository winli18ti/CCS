package com.winli.ccs

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.winli.ccs.algorithm.Affine
import com.winli.ccs.algorithm.Caesar
import com.winli.ccs.databinding.ActivityAffineBinding

class AffineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAffineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAffineBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = "Affine Cipher"

        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        with(binding) {
            btnEncrypt.setOnClickListener {
                val input = etInput.text.toString()
                val multi = etMulti.text.toString()
                val add = etAdd.text.toString()
                if (isEmpty(input, multi, add)) {
                    return@setOnClickListener
                } else {
                    val output = Affine.encrypt(input, multi.toInt(), add.toInt())
                    etOutput.setText(output)
                }
            }

            btnDecrypt.setOnClickListener {
                val input = etInput.text.toString()
                val multi = etMulti.text.toString()
                val add = etAdd.text.toString()
                if (isEmpty(input, multi, add)) {
                    return@setOnClickListener
                } else {
                    val output = Affine.decrypt(input, multi.toInt(), add.toInt())
                    etOutput.setText(output)
                }
            }

            btnCopy.setOnClickListener {
                val text = etOutput.text
                if (text.isNullOrEmpty()) {
                    Snackbar.make(
                        it,
                        "Output Text is empty.",
                        Snackbar.LENGTH_SHORT
                    )
                        .show()
                } else {
                    val clip = ClipData.newPlainText("Copied Text", text)
                    clipboard.setPrimaryClip(clip)
                    Snackbar.make(
                        it,
                        "Output Text has been copied. Press Paste to put it on Input Text.",
                        Snackbar.LENGTH_SHORT
                    )
                        .show()
                }

            }

            btnPaste.setOnClickListener {
                val clipData: ClipData? = clipboard.primaryClip

                clipData?.apply {
                    val text = this.getItemAt(0).text.toString().trim()
                    etInput.setText(text)
                }
            }

            btnClear.setOnClickListener {
                etInput.setText("")
                etMulti.setText("")
                etAdd.setText("")
                etOutput.setText("")
            }

            titleEncrypt.setOnClickListener {
                TransitionManager.beginDelayedTransition(clEncrypt, AutoTransition())
                TransitionManager.beginDelayedTransition(clDecrypt, AutoTransition())
                if (expandEncrypt.visibility == View.VISIBLE) {
                    expandEncrypt.visibility = View.GONE
                    ibEncrypt.setImageResource(R.drawable.ic_down)
                } else {
                    expandEncrypt.visibility = View.VISIBLE
                    ibEncrypt.setImageResource(R.drawable.ic_up)
                }
            }

            titleDecrypt.setOnClickListener {
                TransitionManager.beginDelayedTransition(clDecrypt, AutoTransition())
                if (expandDecrypt.visibility == View.VISIBLE) {
                    expandDecrypt.visibility = View.GONE
                    ibDecrypt.setImageResource(R.drawable.ic_down)
                } else {
                    expandDecrypt.visibility = View.VISIBLE
                    ibDecrypt.setImageResource(R.drawable.ic_up)
                }
            }
        }
    }

    private fun isEmpty(input: String, multi: String, add: String): Boolean {
        if (input.isEmpty()) {
            Snackbar.make(
                binding.root,
                "Input Text is empty.",
                Snackbar.LENGTH_SHORT
            )
                .show()
            return true
        }
        if (multi.isEmpty()) {
            Snackbar.make(
                binding.root,
                "Multiplicative Key is empty.",
                Snackbar.LENGTH_SHORT
            )
                .show()
            return true
        }
        if (!Affine.coPrime(multi.toInt(), 26)) {
            Snackbar.make(
                binding.root,
                "Please insert any odd number from 1 to 25, except 13, in multi.",
                Snackbar.LENGTH_SHORT
            )
                .show()
            return true
        }
        if (add.isEmpty()) {
            Snackbar.make(
                binding.root,
                "Additive Key is empty.",
                Snackbar.LENGTH_SHORT
            )
                .show()
            return true
        }
        return false
    }
}