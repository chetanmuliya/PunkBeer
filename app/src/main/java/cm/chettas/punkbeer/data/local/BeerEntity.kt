package cm.chettas.punkbeer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beer")
data class BeerEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val name: String? = null,
    val tagline: String? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    val volumeValue: Int? = null,
    val volumeUnit: String? = null,
    val ingredients: String? = null,
    val foodPairing: String? = null,
    val brewersTips: String? = null
)