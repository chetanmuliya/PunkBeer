package cm.chettas.punkbeer.repository

import cm.chettas.punkbeer.data.local.BeerEntity
import cm.chettas.punkbeer.data.model.BeerResponse
import cm.chettas.punkbeer.data.model.BeerResponseItem
import cm.chettas.punkbeer.utils.Resource

interface MainRepository {
    suspend fun getBeers(): Resource<List<BeerEntity>>
    suspend fun getBeerById(id: Int): BeerEntity?
    suspend fun getBeerByName(name: String): List<BeerEntity>
    suspend fun updateBeers(): Resource<List<BeerEntity>>
}