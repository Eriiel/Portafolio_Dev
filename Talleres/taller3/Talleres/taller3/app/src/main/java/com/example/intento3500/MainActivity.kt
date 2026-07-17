# Taller: LazyColumn con Cards y Botones

package com.example.intento3500

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "lista"
                ) {
                    composable("lista") {
                        Greeting(
                            name = "Android",
                            navController = navController
                        )
                    }

                    composable("favoritos") {
                        FavoritosScreen(navController)
                    }
                }
            }
        }
    }
}

//  fuente
val Poppins = FontFamily(
    Font(R.font.poppins_semibold)
)

// lista para favs
val favoritos = mutableStateListOf<String>()

@Composable
fun Greeting(
    name: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val listaPaises = listOf(
        "Argentina", "Bolivia", "Brasil", "Chile", "Colombia",
        "Costa Rica", "Cuba", "Ecuador", "El Salvador", "España", "Francia",
        "Guatemala", "Honduras","Italia", "Japón", "México", "Nicaragua", "Panamá",
        "Paraguay", "Perú", "Puerto Rico", "República Dominicana",
        "Uruguay", "Venezuela"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // boton de paises fasv
        Button(onClick = {
            navController.navigate("favoritos")
        }) {
            Text("Ver Favoritos")
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(listaPaises) { pais ->
                Card( // 2. Perzonalizar el card
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray, // Color de fondo
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp // Sombra
                    ),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = pais,
                            modifier = Modifier
                                .padding(16.dp)
                                .weight(1f)
                        )

                        Button(
                            onClick = {
                                Toast.makeText(
                                    context,
                                    " $pais",
                                    Toast.LENGTH_LONG
                                ).show()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Blue,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Detalles")
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button( // botón paa agg a favs
                            onClick = {
                                if (!favoritos.contains(pais)) {
                                    favoritos.add(pais)
                                    Toast.makeText(
                                        context,
                                        "$pais agregado a favoritos",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                "Favoritos",
                                fontFamily = Poppins
                            )
                        }
                    }
                }
            }
        }
    }
}

// pantalla favs
@Composable
fun FavoritosScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Text(
            text = "Países Favoritos",
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn {
            items(favoritos) { pais ->
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = pais,
                        modifier = Modifier.padding(16.dp)
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        Greeting("Android", rememberNavController())
    }
}