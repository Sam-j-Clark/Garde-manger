package nz.ac.uclive.scl113.supysorter.view.pantry

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nz.ac.uclive.scl113.supysorter.service.MealService
import javax.inject.Inject

@HiltViewModel
class PantryViewModel @Inject constructor(
    private val service: MealService
): ViewModel() {

    private val _state = mutableStateOf(PantryState())
    val state: State<PantryState> = _state

    init {
        Log.d("Pantry View Model", "Initialising pantry view model")
        observeState()
    }

    fun onEvent(event: PantryEvent) {
        when (event) {
            is PantryEvent.ToggleOwned -> {
                viewModelScope.launch {
                    service.toggleIngredientOwned(event.ingredient)
                }
            }
            is PantryEvent.ToggleShoppingMode -> {
                _state.value = _state.value.copy(
                    filtered = !_state.value.filtered
                )
            }
        }
    }


    private fun observeState() {
        service.getAllIngredients().onEach { ingredients ->
            Log.d("Home State", "Ingredients state updated, now has ${ingredients.size} ingredients.")
            _state.value = state.value.copy(
                ingredients = ingredients
            )
        }.launchIn(viewModelScope)
    }
}