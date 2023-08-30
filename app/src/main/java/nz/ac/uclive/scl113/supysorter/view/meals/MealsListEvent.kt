package nz.ac.uclive.scl113.supysorter.view.meals

import nz.ac.uclive.scl113.supysorter.model.Meal

sealed class MealsListEvent {
    data class DeleteMealEvent(val meal: Meal) : MealsListEvent()
}
