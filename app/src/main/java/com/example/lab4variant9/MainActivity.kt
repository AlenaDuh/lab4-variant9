package com.example.lab4variant9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab4variant9.ui.theme.Lab4Variant9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4Variant9Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DemoScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DemoScreen(modifier: Modifier = Modifier) {
    var inputText by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Введите n (натуральное число):", fontSize = 20.sp)

        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("n") },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                val n = inputText.toIntOrNull()

                if (n == null || n <= 0) {
                    resultText = "Ошибка! Введите натуральное число."
                } else {
                    // 1. Вычисляем факториал n! с помощью цикла for
                    var factorial: Long = 1
                    for (i in 1..n) {
                        factorial *= i
                    }

                    // 2. Ищем три последовательных числа через цикл while
                    var found = false
                    var k: Long = 1

                    while (true) {
                        val product = k * (k + 1) * (k + 2)
                        if (product == factorial) {
                            resultText = "Можно!\n$n! = $factorial = $k * ${k + 1} * ${k + 2}"
                            found = true
                            break // Выходим из цикла, если нашли
                        } else if (product > factorial) {
                            break // Выходим, если перескочили нужное значение
                        }
                        k++
                    }

                    if (!found) {
                        resultText = "Нельзя! Факториал $factorial не представляется в виде произведения 3-х последовательных чисел."
                    }
                }
            },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text("Проверить")
        }

        Text(
            text = resultText,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DemoScreenPreview() {
    Lab4Variant9Theme {
        DemoScreen()
    }
}