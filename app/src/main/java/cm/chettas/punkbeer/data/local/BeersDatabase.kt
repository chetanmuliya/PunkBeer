package cm.chettas.punkbeer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BeerEntity::class], version = 1, exportSchema = false)
abstract class BeersDatabase: RoomDatabase() {
    abstract fun beerDao(): BeerDao
}
