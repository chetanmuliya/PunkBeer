package cm.chettas.punkbeer.data.model


import com.google.gson.annotations.SerializedName

data class Ingredients(
    val malt: List<Malt>,
    val yeast: String
)