package nz.ac.uclive.scl113.supysorter.view

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import nz.ac.uclive.scl113.supysorter.model.Ingredient
import nz.ac.uclive.scl113.supysorter.model.Meal
import nz.ac.uclive.scl113.supysorter.model.MealDatabase
import nz.ac.uclive.scl113.supysorter.model.relations.MealIngredientCrossReference
import nz.ac.uclive.scl113.supysorter.ui.theme.SupySorterTheme
import nz.ac.uclive.scl113.supysorter.view.add_edit_meal.AddEditMealScreen
import nz.ac.uclive.scl113.supysorter.view.home.HomeScreen
import nz.ac.uclive.scl113.supysorter.view.mealInfo.MealInfoScreen
import nz.ac.uclive.scl113.supysorter.view.meals.MealsScreen
import nz.ac.uclive.scl113.supysorter.view.pantry.PantryScreen
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var database: MealDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED)
        super.onCreate(savedInstanceState)
        initTestData()

        setContent {
            SupySorterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(
                            route = Screen.HomeScreen.route
                        ) {
                            HomeScreen(navController = navController)
                        }
                        composable(route = Screen.MealsListScreen.route) {
                            MealsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.MealInfoScreen.route +
                                    "?mealName={mealName}",
                            arguments = listOf(
                                navArgument(
                                    name = "mealName"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            ),
                        ) {
                            AnimatedVisibility(
                                visible = true,
                                enter = fadeIn() + slideInVertically { fullHeight -> fullHeight },
                            ) {
                                MealInfoScreen(navController = navController)
                            }
                        }
                        composable(
                            route = Screen.PantryScreen.route
                        ) {
                            PantryScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditMealScreen.route +
                                    "?mealName={mealName}",
                            arguments = listOf(
                                navArgument(
                                    name = "mealName"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )
                        ) {
                            AddEditMealScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }

    private fun initTestData() {
        val dao = database.dao
        lifecycleScope.launch {
            if (dao.getMeals().first().isEmpty()) {
                Log.d("Database Pre-population", "Database being pre-populated")

                val meals = listOf(
                    Meal("BLT Sandwich", null),
                    Meal("Potato Salad", null),
                )
                val ingredients = listOf(
                    Ingredient("Lettuce", false),
                    Ingredient("Tomato", true),
                    Ingredient("Bacon", false),
                    Ingredient("Mayonnaise", true),
                    Ingredient("White Bread", false),
                    Ingredient("Potato", false),
                    Ingredient("Egg", false),
                    Ingredient("Mustard", true)
                )

                val mealIngredients = listOf(
                    MealIngredientCrossReference("BLT Sandwich", "Lettuce"),
                    MealIngredientCrossReference("BLT Sandwich", "Tomato"),
                    MealIngredientCrossReference("BLT Sandwich", "Bacon"),
                    MealIngredientCrossReference("BLT Sandwich", "White Bread"),
                    MealIngredientCrossReference("BLT Sandwich", "Mayonnaise"),
                    MealIngredientCrossReference("Potato Salad", "Bacon"),
                    MealIngredientCrossReference("Potato Salad", "Potato"),
                    MealIngredientCrossReference("Potato Salad", "Mayonnaise"),
                    MealIngredientCrossReference("Potato Salad", "Mustard"),
                    MealIngredientCrossReference("Potato Salad", "Egg")
                )

                meals.forEach{ dao.insertMeal(it) }
                ingredients.forEach{ dao.insertIngredient(it) }
                mealIngredients.forEach{ dao.insertMealIngredientCrossReference(it) }

            } else {
                Log.d("Database Pre-population", "Database is already populated")
            }
        }
    }
}

