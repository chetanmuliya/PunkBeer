package cm.chettas.punkbeer.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import cm.chettas.punkbeer.R
import cm.chettas.punkbeer.databinding.ActivityMainBinding
import cm.chettas.punkbeer.ui.main.adapter.BeerAdapter
import cm.chettas.punkbeer.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BeerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //setSplashScreen()
        setUpUi()
        setUpViewModel()
    }

    private fun setUpUi() {
        adapter = BeerAdapter()
        binding.rvBeers.adapter = adapter

        binding.searchBox.addTextChangedListener{
            val query = it.toString().trim()
            performSearch(query)
        }
    }

    private fun setSplashScreen() {
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.isLoading.value
            }
        }
    }

    private fun performSearch(query: String) {
        viewModel.searchBeer(query)
    }

    private fun setUpViewModel() {
        viewModel.getBeers()
        viewModel.beers.observe(this, Observer{
            when(it) {
                is Resource.Error -> Log.d("*************", "setUpViewModel: Error ${it.message}")
                is Resource.Loading -> Log.d("*************", "setUpViewModel: ${it.message}")
                is Resource.Success -> adapter.submitList(it.data ?: emptyList())
            }
        })
    }
}