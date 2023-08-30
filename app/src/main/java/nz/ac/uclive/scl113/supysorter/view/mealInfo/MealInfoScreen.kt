package nz.ac.uclive.scl113.supysorter.view.mealInfo

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nz.ac.uclive.scl113.supysorter.R
import nz.ac.uclive.scl113.supysorter.view.Screen
import nz.ac.uclive.scl113.supysorter.view.pantry.IngredientComponent

@SuppressLint("UnnecessaryComposedModifier")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealInfoScreen (
    navController: NavController,
    viewModel: MealInfoViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigate(Screen.MealsListScreen.route) },
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.meal_information),
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        Screen.AddEditMealScreen.route +
                                "?mealName=${state.mealAndIngredients?.meal?.mealName}"
                    )
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(65.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(id = R.string.edit_meal),
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    ) {
        padding ->
        Column(modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .padding(10.dp)
            .clip(RoundedCornerShape(10))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(24.dp)
        ) {
            if (state.mealAndIngredients != null) {
                Text(
                    text = state.mealAndIngredients.meal.mealName,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSecondary,
                    lineHeight = 60.sp
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, top = 10.dp)
                )
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                ) {
                    items(state.mealAndIngredients.ingredients) { ingredient ->
                        IngredientComponent(
                            ingredient = ingredient,
                            onToggle = {
                                viewModel.onEvent(MealInfoEvent.ToggleOwned(ingredient))
                            }
                        )
                    }
                }
            }
        }
    }
}