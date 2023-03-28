package cm.chettas.punkbeer.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cm.chettas.punkbeer.data.local.BeerEntity
import cm.chettas.punkbeer.databinding.BeerCardItemLayBinding
import cm.chettas.punkbeer.ui.detail_screen.BeerDetailActivity
import com.bumptech.glide.Glide

class BeerAdapter : RecyclerView.Adapter<BeerAdapter.MyViewHolder>() {

    private lateinit var binding: BeerCardItemLayBinding
    private val list = mutableListOf<BeerEntity>()

    class MyViewHolder(private val binding: BeerCardItemLayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beer: BeerEntity) {
            binding.model = beer
            binding.ivBeerImage.setOnClickListener {
                BeerDetailActivity.startActivity(it.context,beer.id ?: 0)
            }
            Glide.with(binding.root.context).load(beer.imageUrl).into(binding.ivBeerImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = BeerCardItemLayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int = list.size

    fun submitList(data: List<BeerEntity>) {
        if (data.isEmpty()) return
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}