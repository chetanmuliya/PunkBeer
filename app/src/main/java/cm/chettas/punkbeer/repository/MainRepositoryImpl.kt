package cm.chettas.punkbeer.repository

import cm.chettas.punkbeer.data.local.BeerEntity
import cm.chettas.punkbeer.data.local.BeerLocalDataSource
import cm.chettas.punkbeer.data.remote.BeerRemoteDataSource
import cm.chettas.punkbeer.utils.Resource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val localDataSource: BeerLocalDataSource,
    private val remoteDataSource: BeerRemoteDataSource,
): MainRepository {

    override suspend fun getBeers(): Resource<List<BeerEntity>> {
        return try{
            val localData = localDataSource.getBeers()
            if (localData.isEmpty()){
                remoteDataSource.getBeers()
            } else Resource.Success(localDataSource.getBeers())
        }catch (e: Exception){
            Resource.Error("Failed to Fetch Response ${e.message}")
        }
    }

    override suspend fun getBeerById(id: Int): BeerEntity? = localDataSource.getBeerById(id)

    override suspend fun getBeerByName(name: String): List<BeerEntity> = localDataSource.getBeerByName(name)

    override suspend fun updateBeers(): Resource<List<BeerEntity>> {
        TODO("Not yet implemented")
    }
}