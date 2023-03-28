package cm.chettas.punkbeer.ui.detail_screen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import cm.chettas.punkbeer.R
import cm.chettas.punkbeer.databinding.ActivityBeerDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailActivity : AppCompatActivity() {

    private val viewModel: BeerDetailViewModel by viewModels()
    private lateinit var binding: ActivityBeerDetailBinding

    companion object{
        private const val BEER_ID = "beer_id"
        fun startActivity(context: Context, beerId: Int) {
            context.startActivity(
                Intent(context,BeerDetailActivity::class.java).apply {
                    putExtra(BEER_ID, beerId)
                }
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_beer_detail)

        setUpUi()
    }

    private fun setUpUi() {
        val id = intent.getIntExtra(BEER_ID,0)

        if (id == 0) {
            Toast.makeText(this,"Beer Not Found",Toast.LENGTH_SHORT).show()
            finish()
        }
        viewModel.getBeer(id)
        viewModel.beer.observe(this, Observer{
            binding.model = it
            Glide.with(binding.root.context).load(it.imageUrl).into(binding.ivBeerImage)
        })

        binding.ivGoBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}