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
                    TabataConfigScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TabataConfigScreen(modifier: Modifier = Modifier) {
    var sets by remember {mutableStateOf(6)}
    var work by remember {mutableStateOf(60)}
    var rest by remember {mutableStateOf(15)}

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "SETS", fontSize = 30.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { if (sets > 0) sets-- }) {
                Text(text = "-", fontSize = 25.sp)
            }
            Text(text = sets.toString(), fontSize = 30.sp, modifier = Modifier.padding(40.dp, 40.dp))
            Button(onClick = { sets++ }) {
                Text(text = "+", fontSize = 25.sp)
            }
        }


        Text(text = "WORK", fontSize = 30.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { if (work > 14){work -= 15}}) {
                Text(text = "-", fontSize = 25.sp)
            }
            Text(text = work.toString(), fontSize = 30.sp, modifier = Modifier.padding(40.dp, 40.dp))
            Button(onClick = { work += 15 }) {
                Text(text = "+", fontSize = 25.sp)
            }
        }


        Text(text = "REST", fontSize = 30.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { if (rest > 14){rest -= 15}}) {
                Text(text = "-", fontSize = 25.sp)
            }
            Text(text = rest.toString(), fontSize = 30.sp, modifier = Modifier.padding(40.dp, 40.dp))
            Button(onClick = { rest += 15 }) {
                Text(text = "+", fontSize = 25.sp)
            }
        }

        Button(onClick = { //////
        }) {
            Text(text = "Start", fontSize = 50.sp)
        }
    }
}
/*
        Button(onClick = {
            miConterDown.toggle()
        }) {
            Text(
                text = "Pulsar"
            )
        }
        */
