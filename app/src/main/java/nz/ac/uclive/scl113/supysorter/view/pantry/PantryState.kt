package nz.ac.uclive.scl113.supysorter.view.pantry

import nz.ac.uclive.scl113.supysorter.model.Ingredient

data class PantryState(
    val ingredients: List<Ingredient> = emptyList(),
    val filtered: Boolean = false,
)
