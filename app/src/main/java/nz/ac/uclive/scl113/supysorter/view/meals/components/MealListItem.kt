package nz.ac.uclive.scl113.supysorter.view.meals.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import nz.ac.uclive.scl113.supysorter.model.Meal

@SuppressLint("UnnecessaryComposedModifier")
@Composable
fun MealListItem(
    meal: Meal,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(10))
            .background(MaterialTheme.colorScheme.secondary)
            .composed { modifier },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = meal.mealName,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = 16.dp)
        )
        IconButton(
            onClick = onDeleteClick
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Meal",
                tint = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}