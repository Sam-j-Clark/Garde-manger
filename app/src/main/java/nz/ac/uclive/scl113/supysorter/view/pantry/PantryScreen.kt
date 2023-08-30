package nz.ac.uclive.scl113.supysorter.view.pantry

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nz.ac.uclive.scl113.supysorter.R
import nz.ac.uclive.scl113.supysorter.view.Screen


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PantryScreen(
    navController: NavController,
    viewModel: PantryViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigate(Screen.HomeScreen.route) },
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
                        text = stringResource(id = R.string.pantry),
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    Switch(
                        checked = state.filtered,
                        onCheckedChange = {
                            viewModel.onEvent(PantryEvent.ToggleShoppingMode)
                        },
                        colors = SwitchDefaults.colors(
                            checkedBorderColor = MaterialTheme.colorScheme.onPrimary,
                            uncheckedBorderColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            )
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
            LazyColumn(modifier = Modifier
                .fillMaxSize()
            ) {
                items(state.ingredients) { ingredient ->
                    AnimatedVisibility(
                        visible = !ingredient.owned || !state.filtered,
                        enter = fadeIn(
                            animationSpec = TweenSpec(200, 200, FastOutLinearInEasing)
                        ),
                        exit = fadeOut(
                            animationSpec = TweenSpec(200, 0, FastOutLinearInEasing)
                        ),
                        modifier = Modifier.animateItemPlacement()
                    ) {
                        IngredientComponent(
                            ingredient = ingredient
                        ) {
                            viewModel.onEvent(PantryEvent.ToggleOwned(ingredient))
                        }
                    }

                }
            }

        }
    }
}