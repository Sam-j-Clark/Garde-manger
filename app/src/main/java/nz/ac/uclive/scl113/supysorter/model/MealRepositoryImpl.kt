package nz.ac.uclive.scl113.supysorter.model

import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.scl113.supysorter.model.relations.MealIngredientCrossReference
import nz.ac.uclive.scl113.supysorter.model.relations.MealWithIngredients

class MealRepositoryImpl(
    private val dao: MealDao
): MealRepository {

    override fun getMeals(): Flow<List<Meal>> {
        return dao.getMeals()
    }

    override fun getAllIngredients(): Flow<List<Ingredient>> {
        return dao.getAllIngredients()
    }

    override suspend fun getMealAndItsIngredients(mealName: String): MealWithIngredients? {
        return dao.getIngredientsOfMeal(mealName)
    }

    override suspend fun getMealByName(name: String): Meal? {
        return dao.getMealByName(name)
    }

    override suspend fun insertMeal(meal: Meal) {
        return dao.insertMeal(meal)
    }

    override suspend fun insertIngredient(ingredient: Ingredient) {
        return dao.insertIngredient(ingredient)
    }

    override suspend fun insertMealIngredientCrossReference(crossReference: MealIngredientCrossReference) {
        dao.insertMealIngredientCrossReference(crossReference)
    }

    override suspend fun deleteMeal(meal: Meal) {
        return dao.deleteMeal(meal)
    }

    override suspend fun deleteMealIngredients(mealName: String) {
        return dao.deleteMealIngredients(mealName)
    }

}