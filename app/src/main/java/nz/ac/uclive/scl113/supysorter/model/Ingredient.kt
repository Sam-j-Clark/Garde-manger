package nz.ac.uclive.scl113.supysorter.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ingredient(
    @PrimaryKey(autoGenerate = false) val ingredientName: String,
    val owned: Boolean
)
