package com.otimonov.sizerecommender.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.otimonov.sizerecommendersdk.RecommendedSize
import com.otimonov.sizerecommendersdk.SizeRecommender

@Composable
fun BodyDataInputView(modifier: Modifier) {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var recommendedSize by remember { mutableStateOf<RecommendedSize?>(null) }

    Column(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Find Your Ideal Fit", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val heightValue = height.toDoubleOrNull()
                val weightValue = weight.toDoubleOrNull()
                if (heightValue != null && weightValue != null) {
                    recommendedSize = SizeRecommender.recommendSize(heightValue, weightValue)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Size Recommendation")
        }

        recommendedSize?.let {
            ResultView(size = it)
        }
    }
}
