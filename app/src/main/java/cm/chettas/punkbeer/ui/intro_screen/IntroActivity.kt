package cm.chettas.punkbeer.ui.intro_screen

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import cm.chettas.punkbeer.R
import cm.chettas.punkbeer.databinding.ActivityIntroBinding
import cm.chettas.punkbeer.databinding.ActivityMainBinding
import cm.chettas.punkbeer.ui.intro_screen.adapter.IntroAdapter
import cm.chettas.punkbeer.ui.main.MainActivity
import cm.chettas.punkbeer.utils.SharedPreferencesManager

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        checkHasSeenIntro()
        hideStatusBar()
        setUpUi()
    }

    private fun setUpUi() {
        val introAdapter = IntroAdapter(supportFragmentManager, lifecycle)
        binding.viewPager2.adapter = introAdapter

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when (position) {
                    0 -> binding.btnNext.text = "get started"
                    1 -> binding.btnNext.text = "Continue"
                    2 -> binding.btnNext.text = "finish"
                }
            }
        })

        binding.btnNext.setOnClickListener {
            if (binding.viewPager2.currentItem < introAdapter.itemCount - 1) {
                binding.viewPager2.currentItem += 1
            } else {
                // If the last slide is reached, go to the main activity
                SharedPreferencesManager.putBoolean("has_seen_intro", true)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun checkHasSeenIntro() {
        val hasSeenIntro = SharedPreferencesManager.getBoolean("has_seen_intro", false)
        if (hasSeenIntro) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}