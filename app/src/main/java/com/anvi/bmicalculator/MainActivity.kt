package com.anvi.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.anvi.bmicalculator.ui.theme.BMICalculatorTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMICalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Column(
        modifier = Modifier.padding(top = 80.dp, start = 20.dp)
    ) {
        var height by remember { mutableStateOf("") }
        var weight by remember { mutableStateOf("") }
        var bmi by remember { mutableStateOf("") }
        var category by remember { mutableStateOf("") }
        Text(
            text = "🧮 BMI Calculator", fontSize = 32.sp
        )

        Text(
            text = "BMI = Weight (kg) / Height² (m²)",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            value = height, onValueChange = { height = it }, label = { Text("Height (cm)") }
        )
        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = weight, onValueChange = { weight = it}, label = { Text("Weight (kg)") }
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                val h = height.toDoubleOrNull()
                val w = weight.toDoubleOrNull()

                if (h != null && w != null) {
                    val bmiValue = w / ((h / 100) * (h / 100))

                    bmi = String.format("%.2f", bmiValue)

                    category = when {
                        bmiValue < 18.5 -> "⚠\uFE0F Underweight"
                        bmiValue < 25 -> "✅ Normal Weight"
                        bmiValue < 30 -> "⚠\uFE0F Overweight"
                        else -> "❌ Obese"
                    }
                }
            }
        ) {
            Text(
                text = "Calculate BMI", fontSize = 24.sp
            )

        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                height = ""
                weight = ""
                bmi = ""
                category = ""
            }
        ) {
            Text("Reset")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.padding(end = 30.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Your BMI: $bmi",
                    fontSize = 24.sp
                )

                if (category.isNotEmpty()) {
                    Text(
                        text = "Category: $category",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BMICalculatorTheme {
        Greeting()
    }
}