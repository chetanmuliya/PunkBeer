package cm.chettas.punkbeer.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class BeerLocalDataSource @Inject constructor(
    private val beerDao: BeerDao
) {

    suspend fun saveBeers(beers: List<BeerEntity>) = withContext(Dispatchers.IO) {
        beerDao.insertBeers(beers)
    }

    suspend fun getBeers(): List<BeerEntity> = withContext(Dispatchers.IO){
        beerDao.getBeers()
    }

    suspend fun getBeerById(id: Int): BeerEntity? = withContext(Dispatchers.IO){
        beerDao.getBeerById(id)
    }

    suspend fun getBeerByName(name: String): List<BeerEntity> = withContext(Dispatchers.IO){
        beerDao.getBeerByName(name)
    }

    suspend fun updateBeers(beers: List<BeerEntity>) = withContext(Dispatchers.IO){
        beerDao.insertBeers(beers)
    }

}