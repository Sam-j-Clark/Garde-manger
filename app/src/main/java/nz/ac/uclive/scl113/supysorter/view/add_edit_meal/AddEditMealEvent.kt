package nz.ac.uclive.scl113.supysorter.view.add_edit_meal

sealed class AddEditMealEvent {
    data class IngredientRemovedEvent(val ingredient: String): AddEditMealEvent()
    data class NewIngredientNameChangeEvent(val ingredientName: String): AddEditMealEvent()
    object SaveNewIngredientEvent : AddEditMealEvent()
    data class ChangeMealNameEvent(val newMealName: String): AddEditMealEvent()
    object SaveMealEvent : AddEditMealEvent()
}
