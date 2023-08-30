package nz.ac.uclive.scl113.supysorter.view.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nz.ac.uclive.scl113.supysorter.R
import nz.ac.uclive.scl113.supysorter.view.Screen


@Composable
fun HomeScreen(
    navController: NavController
) {
    val configuration = LocalConfiguration.current

    var orientation by remember { mutableStateOf(Configuration.ORIENTATION_PORTRAIT) }
    
    LaunchedEffect(configuration) {
        snapshotFlow { configuration.orientation }
            .collect { orientation = it }
    }
    when (orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            HorizontalHomeScreen(navController = navController)
        }
        else -> {
            VerticalHomeScreen(navController = navController)
        }
    }
}

@Composable
fun HorizontalHomeScreen(
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            onClick = {
                navController.navigate(Screen.MealsListScreen.route)
            },
            shape = RoundedCornerShape(10),
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.meals),
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Spacer(
            modifier = Modifier
                .width(20.dp)
                .fillMaxHeight()
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(10),
            onClick = { navController.navigate(Screen.PantryScreen.route) },
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.pantry),
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}


@Composable
fun VerticalHomeScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor =MaterialTheme.colorScheme.onSecondary
            ),
            onClick = {
                navController.navigate(Screen.MealsListScreen.route)
            },
            shape = RoundedCornerShape(10),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.meals),
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth()
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(10),
            onClick = { navController.navigate(Screen.PantryScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.pantry),
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}