package nz.ac.uclive.scl113.supysorter.model

import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.scl113.supysorter.model.relations.MealIngredientCrossReference
import nz.ac.uclive.scl113.supysorter.model.relations.MealWithIngredients

interface MealRepository {

    fun getMeals(): Flow<List<Meal>>

    fun getAllIngredients(): Flow<List<Ingredient>>

    suspend fun getMealAndItsIngredients(mealName: String): MealWithIngredients?

    suspend fun getMealByName(name: String): Meal?

    suspend fun insertMeal(meal: Meal)

    suspend fun insertIngredient(ingredient: Ingredient)

    suspend fun insertMealIngredientCrossReference(crossReference: MealIngredientCrossReference)

    suspend fun deleteMeal(meal: Meal)

    suspend fun deleteMealIngredients(mealName: String)
}