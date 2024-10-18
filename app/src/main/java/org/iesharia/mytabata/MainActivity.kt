package org.iesharia.mytabata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.iesharia.mytabata.ui.theme.MytabataTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MytabataTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ConfigScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ConfigScreen(modifier: Modifier = Modifier) {
    var mostrar by remember { mutableStateOf(true) }
    var sets by remember { mutableStateOf(6) }
    var work by remember { mutableStateOf(60) }
    var rest by remember { mutableStateOf(15) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (mostrar) {
            Text(text = "SETS", fontSize = 30.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { if (sets > 1) sets-- }) {
                    Text(text = "-", fontSize = 25.sp)
                }
                Text(text = sets.toString(), fontSize = 30.sp, modifier = Modifier.padding(60.dp, 40.dp))
                Button(onClick = { sets++ }) {
                    Text(text = "+", fontSize = 25.sp)
                }
            }

            Text(text = "WORK", fontSize = 30.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { if (work > 15) work -= 15 }) {
                    Text(text = "-", fontSize = 25.sp)
                }
                Text(text = work.toString(), fontSize = 30.sp, modifier = Modifier.padding(60.dp, 40.dp))
                Button(onClick = { work += 15 }) {
                    Text(text = "+", fontSize = 25.sp)
                }
            }

            Text(text = "REST", fontSize = 30.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { if (rest > 14) rest -= 15 }) {
                    Text(text = "-", fontSize = 25.sp)
                }
                Text(text = rest.toString(), fontSize = 30.sp, modifier = Modifier.padding(60.dp, 40.dp))
                Button(onClick = { rest += 15 }) {
                    Text(text = "+", fontSize = 25.sp)
                }
            }

            Button(onClick = { mostrar = false }) {
                Text(text = "Start", fontSize = 50.sp)
            }
        } else {
            CounterScreen(
                sets = sets,
                work = work,
                rest = rest
            )
        }
    }
}

@Composable
fun CounterScreen(sets: Int, work: Int, rest: Int) {
    var fase by remember { mutableStateOf("WORK") }
    var restante by remember { mutableStateOf(work) }
    var setActual by remember { mutableStateOf(sets) }
    var funcionando by remember { mutableStateOf(false) }


    fun iniciar(seconds: Int, onFinish: () -> Unit) {
        val counter = CounterDown(seconds) { remainingTime ->
            restante = remainingTime.toInt()
            if (remainingTime <= 0) {
                onFinish()
            }
        }
        counter.start()
    }

    fun siguienteFase() {
        if (fase == "WORK") {
            fase = "REST"
            iniciar(rest) {
                if (setActual > 1) {
                    setActual--
                    fase = "WORK"
                    iniciar(work) { siguienteFase() }
                } else {
                    fase = "Finish"
                }
            }
        }
    }

    LaunchedEffect(funcionando) {
        if (!funcionando) {
            funcionando = true
            iniciar(work) { siguienteFase() }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (fase != "Finish") {
            Text(text = "Fase: $fase", fontSize = 40.sp)
            Text(text = "Tiempo restante: $restante", fontSize = 40.sp)
            Text(text = "Sets restantes: $setActual", fontSize = 40.sp)
        } else {
            Text(text = "¡Tabata completado!", fontSize = 40.sp)
        }
    }
}
