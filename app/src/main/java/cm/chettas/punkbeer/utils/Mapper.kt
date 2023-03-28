package cm.chettas.punkbeer.utils

import cm.chettas.punkbeer.data.local.BeerEntity
import cm.chettas.punkbeer.data.model.BeerResponse
import cm.chettas.punkbeer.data.model.BeerResponseItem

object Mapper {

    @JvmStatic
    fun responseToBeerEntity(response: BeerResponse): List<BeerEntity> {
        return response.map {
            it.run {
                BeerEntity(
                    id = id ?: 0,
                    name = name?: "",
                    tagline = tagline ?: "",
                    description = description ?: "",
                    imageUrl = imageUrl ?: "",
                    volumeValue = volume?.value,
                    volumeUnit = volume?.unit,
                    ingredients = ingredients?.malt?.joinToString { name -> name.name },
                    foodPairing = foodPairing?.joinToString { value -> value },
                    brewersTips = brewersTips
                )
            }
        }
    }
}