package nz.ac.uclive.scl113.supysorter.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meal(
    @PrimaryKey(autoGenerate = false) val mealName: String,
    val cookedTimestamp: Long?,
)
