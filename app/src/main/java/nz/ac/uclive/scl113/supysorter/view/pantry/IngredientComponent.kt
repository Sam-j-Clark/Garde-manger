package nz.ac.uclive.scl113.supysorter.view.pantry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import nz.ac.uclive.scl113.supysorter.model.Ingredient

@Composable
fun IngredientComponent(
    ingredient: Ingredient,
    onToggle: () -> Unit = {}
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 4.dp)
            .fillMaxWidth()
            .clickable(onClick = onToggle)
    ) {
        Text(
            text = ingredient.ingredientName,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSecondary,
            lineHeight = 2.em
        )
        Checkbox(
            checked = ingredient.owned,
            onCheckedChange = {
                onToggle()
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.onPrimary,
                uncheckedColor = MaterialTheme.colorScheme.onPrimary,
                checkmarkColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

