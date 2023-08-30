package nz.ac.uclive.scl113.supysorter.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import nz.ac.uclive.scl113.supysorter.model.Ingredient
import nz.ac.uclive.scl113.supysorter.model.Meal

data class IngredientWithMeals (
    @Embedded
    val ingredient: Ingredient,
    @Relation(
        parentColumn = "ingredientName",
        entityColumn = "mealName",
        associateBy = Junction(MealIngredientCrossReference::class)
    )
    var meals: List<Meal>
)