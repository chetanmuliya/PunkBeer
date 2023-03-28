package cm.chettas.punkbeer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cm.chettas.punkbeer.data.model.BeerResponseItem

@Dao
interface BeerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBeers(beers: List<BeerEntity>)

    @Query("Select * from beer")
    suspend fun getBeers(): List<BeerEntity>

    @Query("Select * from beer where id = :id")
    suspend fun getBeerById(id: Int): BeerEntity?

    @Query("SELECT * FROM beer WHERE name LIKE '%' || :query || '%'")
    suspend fun getBeerByName(query: String): List<BeerEntity>

    @Query("DELETE FROM beer")
    fun deleteBeers()
}