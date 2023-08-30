package nz.ac.uclive.scl113.supysorter.view.pantry

import nz.ac.uclive.scl113.supysorter.model.Ingredient

sealed class PantryEvent {
    data class ToggleOwned(val ingredient: Ingredient): PantryEvent()
    object ToggleShoppingMode : PantryEvent()
}