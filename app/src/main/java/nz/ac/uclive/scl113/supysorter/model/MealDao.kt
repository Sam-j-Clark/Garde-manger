package nz.ac.uclive.scl113.supysorter.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nz.ac.uclive.scl113.supysorter.model.relations.MealIngredientCrossReference
import nz.ac.uclive.scl113.supysorter.model.relations.MealWithIngredients

@Dao
interface MealDao {

    @Query("SELECT * FROM meal ORDER BY mealName")
    fun getMeals(): Flow<List<Meal>>

    @Query("SELECT * FROM ingredient WHERE ingredientName IN (SELECT ingredientName FROM MealIngredientCrossReference) ORDER BY ingredientName")
    fun getAllIngredients(): Flow<List<Ingredient>>

    @Query("SELECT * FROM meal where mealName = :mealName")
    suspend fun getIngredientsOfMeal(mealName: String): MealWithIngredients?

    @Query("SELECT * FROM meal WHERE mealName = :mealName")
    suspend fun getMealByName(mealName: String): Meal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredient: Ingredient)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealIngredientCrossReference(mealIngredientCrossReference: MealIngredientCrossReference)

    @Delete
    suspend fun deleteMeal(meal: Meal)

    @Query("DELETE FROM mealingredientcrossreference WHERE mealName = :mealName")
    suspend fun deleteMealIngredients(mealName: String)

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)

}