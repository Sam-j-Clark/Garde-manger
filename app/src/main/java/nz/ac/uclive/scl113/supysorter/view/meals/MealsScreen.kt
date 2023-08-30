package nz.ac.uclive.scl113.supysorter.view.meals

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nz.ac.uclive.scl113.supysorter.R
import nz.ac.uclive.scl113.supysorter.view.Screen
import nz.ac.uclive.scl113.supysorter.view.meals.components.MealListItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsScreen(
    navController: NavController,
    viewModel: MealsViewModel = hiltViewModel()
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
                        text = stringResource(id = R.string.meals),
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
                            "?mealName="
                    )
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(65.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.create_meal),
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    ) {
        padding ->
        Box(modifier = Modifier.padding(padding)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.meals) { meal ->
                    MealListItem(
                        meal = meal,
                        modifier = Modifier
                            .clickable {
                            navController.navigate(
                                Screen.MealInfoScreen.route +
                                        "?mealName=${meal.mealName}"
                            )
                        },
                        onDeleteClick = {
                            viewModel.onEvent(MealsListEvent.DeleteMealEvent(meal))
                        }
                    )
                }
                item {
                    NeedIdeas()
                }
            }
        }
    }
}


@Composable
fun NeedIdeas() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(10))
            .background(MaterialTheme.colorScheme.secondary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        val url = "https://www.jamieoliver.com/recipes/"
        val context = LocalContext.current
        Text(
            text = stringResource(id = R.string.need_ideas),
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(context, intent, null)
                }
        )
    }
}