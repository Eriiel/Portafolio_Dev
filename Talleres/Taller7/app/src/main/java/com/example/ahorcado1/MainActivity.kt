package com.example.ahorcado1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                JuegoAhorcado()
            }
        }
    }
}

@Composable
fun JuegoAhorcado() {

    var palabra by remember { mutableStateOf("") }
    var palabraOculta by remember { mutableStateOf("") }

    var juegoIniciado by remember { mutableStateOf(false) }

    var errores by remember { mutableStateOf(0) }

    var letrasUsadas by remember {
        mutableStateOf(listOf<Char>())
    }

    var mensaje by remember {
        mutableStateOf("")
    }

    val maxErrores = 6

    val imagenes = listOf(
        R.drawable.fallo1,
        R.drawable.fallo2,
        R.drawable.fallo3,
        R.drawable.fallo4,
        R.drawable.fallo5,
        R.drawable.fallo6,
        R.drawable.fallo7
    )

    val abecedario = ('A'..'Z').toList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A))
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Juego del Ahorcado",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // INGRESAR PALABRA
        if (!juegoIniciado) {

            OutlinedTextField(
                value = palabra,

                onValueChange = {
                    palabra = it
                },

                label = {
                    Text("Palabra secreta")
                },

                visualTransformation = PasswordVisualTransformation(),

                singleLine = true
            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(

                onClick = {

                    when {

                        palabra.isBlank() -> {
                            mensaje = "La palabra no puede estar vacía"
                        }

                        !palabra.matches(
                            Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")
                        ) -> {

                            mensaje = "Solo se permiten letras"

                        }

                        else -> {

                            palabra = palabra.uppercase()

                            palabraOculta = "_ ".repeat(palabra.length)

                            juegoIniciado = true

                            mensaje = ""
                        }
                    }
                }

            ) {
                Text("Iniciar Juego")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = mensaje,
                color = Color.Red
            )
        }

        // JUEGO
        else {

            // IMAGEN
            Image(
                painter = painterResource(id = imagenes[errores]),
                contentDescription = "Ahorcado",

                modifier = Modifier
                    .size(250.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // PALABRA OCULTA
            Text(
                text = palabraOculta,

                color = Color.White,

                fontSize = 34.sp,

                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            // BOTONES LETRAS
            LazyVerticalGrid(

                columns = GridCells.Fixed(7),

                modifier = Modifier.height(260.dp)

            ) {

                items(abecedario) { letra ->

                    Button(

                        onClick = {

                            if (!letrasUsadas.contains(letra)) {

                                letrasUsadas = letrasUsadas + letra

                                // LETRA CORRECTA
                                if (palabra.contains(letra)) {

                                    var nuevaPalabra = ""

                                    palabra.forEach { caracter ->

                                        nuevaPalabra += if (
                                            letrasUsadas.contains(caracter)
                                        ) {

                                            "$caracter "

                                        } else {

                                            "_ "
                                        }
                                    }

                                    palabraOculta = nuevaPalabra

                                    // GANAR
                                    if (!palabraOculta.contains("_")) {

                                        mensaje = "¡GANASTE!"
                                    }

                                }

                                // LETRA INCORRECTA
                                else {

                                    errores++

                                    // PERDER
                                    if (errores >= maxErrores) {

                                        mensaje =
                                            "PERDISTE\nLa palabra era: $palabra"
                                    }
                                }
                            }
                        },

                        enabled =
                            !letrasUsadas.contains(letra)
                                    &&
                                    errores < maxErrores
                                    &&
                                    !mensaje.contains("GANASTE"),

                        modifier = Modifier
                            .padding(4.dp)
                            .height(50.dp)
                    ) {

                        Text(
                            text = letra.toString()
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // MENSAJE FINAL
            Text(

                text = mensaje,

                color =
                    if (mensaje.contains("GANASTE"))
                        Color.Green
                    else
                        Color.Red,

                fontSize = 24.sp,

                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            // REINICIAR
            Button(

                onClick = {

                    palabra = ""

                    palabraOculta = ""

                    juegoIniciado = false

                    errores = 0

                    letrasUsadas = emptyList()

                    mensaje = ""
                }

            ) {

                Text("Reiniciar Juego")
            }
        }
    }
}