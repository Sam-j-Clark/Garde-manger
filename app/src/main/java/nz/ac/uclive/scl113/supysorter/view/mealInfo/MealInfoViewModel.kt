package nz.ac.uclive.scl113.supysorter.view.mealInfo

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nz.ac.uclive.scl113.supysorter.service.MealService
import javax.inject.Inject

@HiltViewModel
class MealInfoViewModel @Inject constructor(
    private val service: MealService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MealInfoState())
    val state: State<MealInfoState> = _state

    init {
        updateState()
    }

    fun onEvent(event: MealInfoEvent) {
        event::class.simpleName?.let { Log.d("Event received", it) }
        when (event) {
            is MealInfoEvent.ToggleOwned -> {
                viewModelScope.launch {
                    service.toggleIngredientOwned(event.ingredient).also {
                        updateState()
                    }
                }
            }
        }
    }

    private fun updateState() {
        savedStateHandle.get<String>("mealName")?.let { mealName ->
            viewModelScope.launch {
                service.getMealAndItsIngredients(mealName)?.also { mealAndIngredients ->
                    _state.value = state.value.copy(
                        mealAndIngredients = mealAndIngredients
                    )
                }
            }
        }
    }
}