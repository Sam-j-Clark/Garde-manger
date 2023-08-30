package nz.ac.uclive.scl113.supysorter.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import nz.ac.uclive.scl113.supysorter.model.Ingredient
import nz.ac.uclive.scl113.supysorter.model.Meal

data class MealWithIngredients(
    @Embedded val meal: Meal,
    @Relation(
        parentColumn = "mealName",
        entityColumn = "ingredientName",
        associateBy = Junction(MealIngredientCrossReference::class)
    )
    var ingredients: List<Ingredient>
)
