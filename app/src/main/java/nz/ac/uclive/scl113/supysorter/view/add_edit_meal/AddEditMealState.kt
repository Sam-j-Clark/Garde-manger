package nz.ac.uclive.scl113.supysorter.view.add_edit_meal

data class AddEditMealState(
    var oldMealName: String = "",
    var mealName: String = "",
    var newIngredient: String = "",
    var ingredients: List<String> = emptyList()
)