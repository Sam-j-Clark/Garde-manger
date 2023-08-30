package nz.ac.uclive.scl113.supysorter.view.mealInfo

import nz.ac.uclive.scl113.supysorter.model.Ingredient

sealed class MealInfoEvent {
    data class ToggleOwned(val ingredient: Ingredient): MealInfoEvent()

}