package nz.ac.uclive.scl113.supysorter.service

import android.util.Log
import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.scl113.supysorter.model.Ingredient
import nz.ac.uclive.scl113.supysorter.model.Meal
import nz.ac.uclive.scl113.supysorter.model.MealRepository
import nz.ac.uclive.scl113.supysorter.model.relations.MealIngredientCrossReference
import nz.ac.uclive.scl113.supysorter.model.relations.MealWithIngredients

class MealService (
    private val repository: MealRepository
) {

    fun getMeals(): Flow<List<Meal>> {
        return repository.getMeals()
    }

    fun getAllIngredients(): Flow<List<Ingredient>> {
        return repository.getAllIngredients()
    }

    suspend fun getMealAndItsIngredients(mealName: String): MealWithIngredients? {
        return repository.getMealAndItsIngredients(mealName)
    }

    suspend fun toggleIngredientOwned(ingredient: Ingredient) {
        Log.d("Meal Service", "Toggle ingredient owned state for ${ingredient.ingredientName} to ${!ingredient.owned}")
        return repository.insertIngredient(
            ingredient.copy(
                owned = !ingredient.owned
            )
        )
    }

    suspend fun upsertMeal(mealToChange: String, newMealName: String, mealIngredients: List<String>) {
        val mealToDelete = repository.getMealByName(mealToChange)
        mealToDelete?.let {
            repository.deleteMeal(it)
            repository.deleteMealIngredients(it.mealName)
        }
        repository.insertMeal(Meal(newMealName, null))

        mealIngredients.forEach { ingredient ->
            repository.insertIngredient(Ingredient(ingredient, false))
            repository.insertMealIngredientCrossReference(
                MealIngredientCrossReference(
                    newMealName, ingredient
                )
            )
        }
    }

    suspend fun deleteMeal(meal: Meal) {
        repository.deleteMealIngredients(meal.mealName)
        return repository.deleteMeal(meal)
    }
}