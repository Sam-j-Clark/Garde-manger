package nz.ac.uclive.scl113.supysorter.view.add_edit_meal

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
class AddEditMealViewModel @Inject constructor(
    private val service: MealService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AddEditMealState())
    val state: State<AddEditMealState> = _state

    init {
        updateState()
    }

    fun onEvent(event: AddEditMealEvent) {
        event::class.simpleName?.let { Log.d("Event received", it) }

        when (event) {
            is AddEditMealEvent.IngredientRemovedEvent -> {
                val newIngredients = state.value.ingredients.filter { ingredient ->
                    ingredient != event.ingredient
                }
                _state.value = _state.value.copy(
                    ingredients = newIngredients
                )
            }
            is AddEditMealEvent.NewIngredientNameChangeEvent -> {
                _state.value = _state.value.copy(
                    newIngredient = event.ingredientName
                )
            }
            is AddEditMealEvent.SaveNewIngredientEvent -> {
                var newIngredients = state.value.ingredients
                if (state.value.newIngredient.isNotBlank() &&
                    !state.value.ingredients.contains(state.value.newIngredient)
                ) {
                    newIngredients = newIngredients.plus(state.value.newIngredient)
                }
                Log.d("Add Edit Meals", "${newIngredients.size}")
                _state.value = _state.value.copy(
                    ingredients = newIngredients,
                    newIngredient = ""
                )
            }
            is AddEditMealEvent.ChangeMealNameEvent -> {
                _state.value = _state.value.copy(
                    mealName = event.newMealName
                )
            }
            is AddEditMealEvent.SaveMealEvent -> {
                viewModelScope.launch {
                    service.upsertMeal(
                        state.value.oldMealName,
                        state.value.mealName,
                        state.value.ingredients
                    ).also {
                        _state.value = _state.value.copy(
                            oldMealName = _state.value.mealName
                        )
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
                        oldMealName = mealAndIngredients.meal.mealName,
                        mealName = mealAndIngredients.meal.mealName,
                        ingredients = mealAndIngredients.ingredients.map {
                                ingredient -> ingredient.ingredientName
                        }
                    )
                }
            }
        }
    }
}