package cm.chettas.punkbeer.data.local

import cm.chettas.punkbeer.data.model.BeerResponse
import cm.chettas.punkbeer.utils.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object LocalDataSourceUtil {

    suspend fun saveBeers(
        beerLocalDataSource: BeerLocalDataSource,
        result: BeerResponse
    ): List<BeerEntity> = withContext(Dispatchers.IO){

        val beers = Mapper.responseToBeerEntity(result)

        beerLocalDataSource.saveBeers(
            beers = beers
        )

        return@withContext beers
    }
}