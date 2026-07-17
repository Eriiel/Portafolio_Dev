# 📱 Portafolio – Desarrollo Móvil
 
Repositorio de talleres desarrollados para la asignatura de **Desarrollo Móvil**, Facultad de Ingeniería de Sistemas Computacionales, **Universidad Tecnológica de Panamá**.
 
Aquí se recopila el proceso de aprendizaje de Android nativo, desde la lógica base en Kotlin hasta la construcción de interfaces con **Jetpack Compose**: manejo de estado, navegación entre pantallas, listas dinámicas y juegos interactivos.
 
---
 
## 🧰 Stack técnico
 
| Tecnología | Uso |
|---|---|
| **Kotlin** | Lenguaje principal |
| **Jetpack Compose** | UI declarativa |
| **Android Studio / Gradle (KTS)** | Entorno y build system |
| **Material 3** | Sistema de diseño |
| **Navigation Compose** | Navegación entre pantallas |
 
---
 
## 📂 Estructura del repositorio
 
```
Portafolio_Dev/
└── Talleres/
    ├── Taller1/   → Lógica en Kotlin puro (consola)
    ├── Taller2/   → Calculadora (Android)
    ├── taller3/   → LazyColumn con Cards y Botones
    ├── Taller5/   → Adivina el número
    ├── Taller6/   → Navegación con 4 pantallas
    └── Taller7/   → Juego del Ahorcado
```
 
---
 
## 📝 Talleres
 
### Taller 1 — Sistema de venta de boletos (Kotlin puro)
Programa de consola que simula el sistema de venta de pasajes de una cooperativa de transporte (**East Coast P.A.**). Incluye:
- Registro de pasajero con validaciones (nombre, apellido, edad, género).
- Cálculo de descuentos por edad y género (niños, adultos mayores).
- Emisión de un "recibo" de compra formateado en consola.
- Manejo de errores con `try/catch` y menú interactivo con `do-while`.
📁 [`Talleres/Taller1`](./Talleres/Taller1)
 
---
 
### Taller 2 — Calculadora
Aplicación Android de calculadora básica (operaciones aritméticas) con interfaz simple.
 
📁 [`Talleres/Taller2`](./Talleres/Taller2)
 
---
 
### Taller 3 — LazyColumn con Cards y Botones
App Android que practica listas eficientes en Compose (`LazyColumn`), tarjetas (`Card`) interactivas y una pantalla de favoritos para gestionar los elementos seleccionados.
 
**Conceptos clave:** `LazyColumn`, `Card`, manejo de estado con `remember`/`mutableStateOf`, tipografía personalizada.
 
📁 [`Talleres/taller3`](./Talleres/taller3)
 
---
 
### Taller 5 — Adivina el número
Juego donde el usuario intenta adivinar un número secreto generado aleatoriamente (1–100) en un número limitado de intentos.
 
**Conceptos clave:** `Random`, cronómetro con `LaunchedEffect` + `delay`, validación de entradas numéricas, feedback visual dinámico según el resultado (correcto / incorrecto / perdiste).
 
📁 [`Talleres/Taller5`](./Talleres/Taller5)
 
---
 
### Taller 6 — Navegación básica con 4 pantallas
App que implementa **Navigation Compose** entre cuatro pantallas: Home, Detalles, Perfil y Configuración, con paso de argumentos entre rutas y componentes reutilizables.
 
**Conceptos clave:** `NavHost`, `NavController`, rutas, componentes compartidos (`Components.kt`), theming personalizado.
 
📁 [`Talleres/Taller6`](./Talleres/Taller6)
 
---
 
### Taller 7 — Juego del Ahorcado
Implementación del clásico juego del ahorcado con teclado alfabético, contador de errores y una imagen distinta según el número de fallos (hasta 6 antes de perder).
 
**Conceptos clave:** `LazyVerticalGrid` para el teclado, manejo de estado del juego (palabra, letras usadas, errores), recursos gráficos (`painterResource`).
 
📁 [`Talleres/Taller7`](./Talleres/Taller7)
 

## 👤 Autor
 
**Eriol Tuñon (Eriiel)** — Estudiante de Ingeniería de Software, Universidad Tecnológica de Panamá.
 
 
