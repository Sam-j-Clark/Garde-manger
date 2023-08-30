package nz.ac.uclive.scl113.supysorter.view.meals

import nz.ac.uclive.scl113.supysorter.model.Meal

data class MealsState (
    val meals: List<Meal> = emptyList()
)