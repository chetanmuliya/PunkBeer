package cm.chettas.punkbeer.data.remote

import cm.chettas.punkbeer.data.model.BeerResponse
import retrofit2.Response
import retrofit2.http.GET


interface BeerService {

    @GET("beers")
    suspend fun getBeers(): Response<BeerResponse>
}