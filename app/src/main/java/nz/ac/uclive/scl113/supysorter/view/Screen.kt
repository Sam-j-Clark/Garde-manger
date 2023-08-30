package nz.ac.uclive.scl113.supysorter.view

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object MealsListScreen: Screen("meals_list_screen")
    object MealInfoScreen: Screen("meal_info_screen")
    object PantryScreen: Screen("pantry_screen")
    object AddEditMealScreen: Screen("add_edit_meal_screen")
}
