package nz.ac.uclive.scl113.supysorter.model.relations

import androidx.room.Entity

@Entity(primaryKeys = ["mealName", "ingredientName"])
data class MealIngredientCrossReference (
    val mealName: String,
    val ingredientName: String
)