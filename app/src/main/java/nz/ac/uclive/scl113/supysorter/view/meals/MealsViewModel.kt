package nz.ac.uclive.scl113.supysorter.view.meals

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
class MealsViewModel @Inject constructor(
    private val service: MealService
) : ViewModel() {

    private val _state = mutableStateOf(MealsState())
    val state: State<MealsState> = _state


    init {
        observeState()
        Log.d("Meals View Model", "Initialising home view model")
    }

    fun onEvent(event: MealsListEvent) {
        when (event) {
            is MealsListEvent.DeleteMealEvent -> {
                viewModelScope.launch {
                    service.deleteMeal(event.meal)

                }
            }
        }
    }

    private fun observeState() {
        service.getMeals().onEach { meals ->
            Log.d("Home State", "Meals state updated, now has ${meals.size} meals.")
            _state.value = state.value.copy(
                meals = meals
            )
        }.launchIn(viewModelScope)
    }
}
