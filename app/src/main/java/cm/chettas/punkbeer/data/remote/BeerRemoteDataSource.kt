package cm.chettas.punkbeer.data.remote

import cm.chettas.punkbeer.data.local.BeerEntity
import cm.chettas.punkbeer.data.local.BeerLocalDataSource
import cm.chettas.punkbeer.data.local.LocalDataSourceUtil
import cm.chettas.punkbeer.utils.Mapper
import cm.chettas.punkbeer.utils.Resource
import javax.inject.Inject

class BeerRemoteDataSource @Inject constructor(
    private val service: BeerService,
    private val localDataSource: BeerLocalDataSource
) {

    suspend fun getBeers(): Resource<List<BeerEntity>> {
        return try {
            val response = service.getBeers()
            val result = response.body()
            if (response.isSuccessful && result != null){
                Resource.Success(LocalDataSourceUtil.saveBeers(localDataSource,result))
            }else{
                Resource.Error("Failed to Fetch Data ${response.message()}")
            }
        }catch (e: Exception){
            Resource.Error("Failed to Fetch Response ${e.message}")
        }
    }

}