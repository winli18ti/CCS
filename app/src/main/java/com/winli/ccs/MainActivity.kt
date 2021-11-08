package com.winli.ccs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.winli.ccs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        binding.btn1.setOnClickListener {
            val intent = Intent(this@MainActivity, CaesarActivity::class.java)
            startActivity(intent)
        }

        binding.btn2.setOnClickListener {
            val intent = Intent(this@MainActivity, AffineActivity::class.java)
            startActivity(intent)
        }

        binding.btn3.setOnClickListener {
            val intent = Intent(this@MainActivity, VigenereActivity::class.java)
            startActivity(intent)
        }

        binding.btn4.setOnClickListener {
            val intent = Intent(this@MainActivity, RailFenceActivity::class.java)
            startActivity(intent)
        }

        binding.btn5.setOnClickListener {
            val intent = Intent(this@MainActivity, ScytaleActivity::class.java)
            startActivity(intent)
        }

        binding.btn6.setOnClickListener {
            val intent = Intent(this@MainActivity, ColumnarTranspositionActivity::class.java)
            startActivity(intent)
        }
    }
}