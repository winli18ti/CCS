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
import com.winli.ccs.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = "Algoritma X"

        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        binding.btnEncrypt.setOnClickListener {
            binding.etOutput.setText("Encryption Result")
        }

        binding.btnDecrypt.setOnClickListener {
            binding.etOutput.setText("Decryption Result")
        }

        binding.btnCopy.setOnClickListener {
            val text = binding.etOutput.text
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

        binding.btnPaste.setOnClickListener {
            val clipData: ClipData? = clipboard.primaryClip

            clipData?.apply {
                val text = this.getItemAt(0).text.toString().trim()
                binding.etInput.setText(text)
            }
        }

        binding.btnClear.setOnClickListener {
            binding.etInput.setText("")
            binding.etOutput.setText("")
        }

        binding.titleEncrypt.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.clEncrypt, AutoTransition())
            TransitionManager.beginDelayedTransition(binding.clDecrypt, AutoTransition())
            if (binding.expandEncrypt.visibility == View.VISIBLE) {
                binding.expandEncrypt.visibility = View.GONE
                binding.ibEncrypt.setImageResource(R.drawable.ic_down)
            } else {
                binding.expandEncrypt.visibility = View.VISIBLE
                binding.ibEncrypt.setImageResource(R.drawable.ic_up)
            }
        }

        binding.titleDecrypt.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.clDecrypt, AutoTransition())
            if (binding.expandDecrypt.visibility == View.VISIBLE) {
                binding.expandDecrypt.visibility = View.GONE
                binding.ibDecrypt.setImageResource(R.drawable.ic_down)
            } else {
                binding.expandDecrypt.visibility = View.VISIBLE
                binding.ibDecrypt.setImageResource(R.drawable.ic_up)
            }
        }
    }
}