package nz.ac.uclive.scl113.supysorter.view.add_edit_meal

import android.widget.Toast
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import nz.ac.uclive.scl113.supysorter.R
import nz.ac.uclive.scl113.supysorter.view.Screen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun AddEditMealScreen(
    navController: NavController,
    viewModel: AddEditMealViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (state.oldMealName == "") stringResource(id = R.string.create_meal) else "${stringResource(id = R.string.edit)} ${state.oldMealName}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (state.oldMealName == "") {
                                navController.navigate(
                                    Screen.MealsListScreen.route
                                )
                            } else {
                                navController.navigate(
                                    Screen.MealInfoScreen.route +
                                            "?mealName=${state.oldMealName}"
                                )
                            }
                        },
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
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
                    viewModel.onEvent(AddEditMealEvent.SaveMealEvent)
                    scope.launch{
                        Toast.makeText(
                            context,
                            context.resources.getString(R.string.meal_updated_message),
                            Toast.LENGTH_SHORT
                        )
                        .apply {
                            setGravity(android.view.Gravity.BOTTOM or android.view.Gravity.CENTER_HORIZONTAL, 0, 0)
                            show()
                        }
                    }
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(65.dp),
                containerColor = MaterialTheme.colorScheme.primary
                ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = stringResource(id = R.string.save_meal),
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(30.dp)
                )
            }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = state.mealName,
                    onValueChange = {
                        viewModel.onEvent(AddEditMealEvent.ChangeMealNameEvent(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.headlineSmall,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.new_meal),
                            style = MaterialTheme.typography.headlineSmall,

                            )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .imePadding()
                ) {
                    items(state.ingredients) { ingredient ->
                        Row(
                            modifier = Modifier
                                .animateItemPlacement(
                                    animationSpec = tween(
                                        durationMillis = 500,
                                        easing = LinearOutSlowInEasing,
                                    )
                                )
                                .fillMaxWidth()
                                .padding(start = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = SpaceBetween
                        ) {
                            Text(
                                text = ingredient,
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    viewModel.onEvent(
                                        AddEditMealEvent.IngredientRemovedEvent(
                                            ingredient
                                        )
                                    )
                                }
                            ) {
                                Text(text = stringResource(id = R.string.remove))
                            }
                        }
                    }
                    item {
                        TextField(
                            value = state.newIngredient,
                            onValueChange = {
                                viewModel.onEvent(AddEditMealEvent.NewIngredientNameChangeEvent(it))
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        viewModel.onEvent(AddEditMealEvent.SaveNewIngredientEvent)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = stringResource(id = R.string.add_ingredient)
                                    )
                                }
                            },
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}