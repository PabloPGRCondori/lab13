package com.example.laboratorio13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            lab13()
        }
    }
}

@Composable
fun lab13() {
    var currentView by remember { mutableStateOf("vista1") }

    when (currentView) {
        "vista1" -> Vista1 { currentView = "vista2" }
        "vista2" -> Vista2 { currentView = "vista3" }
        "vista3" -> Vista3 { currentView = "vista1" }
    }
}

@Composable
fun Vista1(onNavigate: () -> Unit) {
    var isVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón para alternar visibilidad
            Button(
                onClick = { isVisible = !isVisible },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Mostrar animación")
            }
        }

        // Botón para pasar a la siguiente animación
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(
                onClick = onNavigate,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Siguiente")
            }
        }

        // Cuadro con AnimatedVisibility
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
            exit = fadeOut(animationSpec = tween(durationMillis = 1000))
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun Vista2(onNavigate: () -> Unit) {
    var isBlue by remember { mutableStateOf(true) }

    // Animación de color de fondo
    val backgroundColor by animateColorAsState(
        targetValue = if (isBlue) Color.Blue else Color.Green,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 50f)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { isBlue = !isBlue },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Cambiar Color")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(
                onClick = onNavigate,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Siguiente")
            }
        }
    }
}

@Composable
fun Vista3(onNavigate: () -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    // Animación de tamaño y posición
    val size by animateDpAsState(
        targetValue = if (isExpanded) 200.dp else 100.dp,
        animationSpec = tween(durationMillis = 1000)
    )
    val offset by animateDpAsState(
        targetValue = if (isExpanded) 50.dp else 0.dp,
        animationSpec = tween(durationMillis = 1000)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón para cambiar el tamaño y la posición del cuadro
            Button(
                onClick = { isExpanded = !isExpanded },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Mover y Cambiar Tamaño")
            }
        }

        // Cuadro animado
        Box(
            modifier = Modifier
                .offset(x = offset, y = offset)
                .size(size)
                .background(Color.Magenta)
                .align(Alignment.Center)
        )

        // Botón para volver a la primera vista
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(
                onClick = onNavigate,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Regresar al inicio")
            }
        }
    }
}