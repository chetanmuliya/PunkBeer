package cm.chettas.punkbeer.data.model


import com.google.gson.annotations.SerializedName

data class BeerResponseItem(
    val id: Int? = null,
    val name: String? = null,
    val tagline: String? = null,
    @SerializedName("first_brewed")
    val firstBrewed: String? = null,
    val description: String? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    val abv: Double? = null,
    val ibu: Double? = null,
    @SerializedName("target_fg")
    val targetFg: Double? = null,
    @SerializedName("target_og")
    val targetOg: Double? = null,
    val ebc: Double? = null,
    val srm: Double,
    val ph: Double? = null,
    @SerializedName("attenuation_level")
    val attenuationLevel: Double? = null,
    val volume: Volume? = null,
    @SerializedName("boil_volume")
    val boilVolume: BoilVolume? = null,
    val ingredients: Ingredients? = null,
    @SerializedName("food_pairing")
    val foodPairing: List<String>? = null,
    @SerializedName("brewers_tips")
    val brewersTips: String? = null,
    @SerializedName("contributed_by")
    val contributedBy: String? = null
)