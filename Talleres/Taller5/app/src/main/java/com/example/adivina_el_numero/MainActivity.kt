package com.example.adivina_el_numero
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JuegoAdivinaNumero()
        }
    }
}

@Composable
fun JuegoAdivinaNumero() {

    var numeroSecreto by remember { mutableStateOf(Random.nextInt(1, 101)) }
    var input by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("Adivina un número entre 1 y 100") }
    var intentosRestantes by remember { mutableStateOf(3) }
    var juegoTerminado by remember { mutableStateOf(false) }

    var tiempo by remember { mutableStateOf(0) }
    var corriendo by remember { mutableStateOf(true) }

    var ranking by remember { mutableStateOf(listOf<Int>()) }

    // ⏱️ Cronómetro
    LaunchedEffect(corriendo) {
        while (corriendo) {
            delay(1000)
            tiempo++
        }
    }

    val colorMensaje = when {
        mensaje.contains("Correcto") -> MaterialTheme.colorScheme.primary
        mensaje.contains("Perdiste") -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.onBackground
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "🎮 Adivina el Número",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("⏱ $tiempo segundos")
            Text("Intentos: $intentosRestantes")

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = mensaje,
                color = colorMensaje,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = input,
                onValueChange = {
                    if (it.all { c -> c.isDigit() }) input = it
                },
                label = { Text("Número") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (input.isNotEmpty() && !juegoTerminado) {

                        val numeroUsuario = input.toInt()

                        var evaluado = false
                        while (!evaluado) {

                            if (numeroUsuario == numeroSecreto) {
                                mensaje = "🎉 ¡Correcto!"
                                corriendo = false
                                juegoTerminado = true
                                ranking = ranking + tiempo
                            } else {
                                intentosRestantes--

                                if (intentosRestantes == 0) {
                                    mensaje = "❌ Perdiste (era $numeroSecreto)"
                                    corriendo = false
                                    juegoTerminado = true
                                } else {
                                    mensaje = if (numeroUsuario < numeroSecreto) {
                                        "🔼 Es MAYOR"
                                    } else {
                                        "🔽 Es MENOR"
                                    }
                                }
                            }

                            evaluado = true
                        }

                        input = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Validar")
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = {
                    numeroSecreto = Random.nextInt(1, 101)
                    intentosRestantes = 3
                    mensaje = "Nuevo juego"
                    tiempo = 0
                    corriendo = true
                    juegoTerminado = false
                    input = ""
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Reiniciar")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("🏆 Ranking", style = MaterialTheme.typography.titleMedium)

            ranking.sorted().take(5).forEachIndexed { i, t ->
                Text("${i + 1}. $t s")
            }
        }
    }
}