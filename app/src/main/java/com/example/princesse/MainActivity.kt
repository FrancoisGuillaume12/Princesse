package com.example.princesse

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.princesse.ui.theme.PrincesseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrincesseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldComposable()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScaffoldComposable() {
    Scaffold(
        topBar = { Top() },
        content = { Body() }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Top() {
    TopAppBar(
        title = { Text(text = "Quelle princesse es-tu ?") }
    )
}

@Composable
fun Body(){
    val image = listOf(
        R.drawable.all,
        R.drawable.merida,
        R.drawable.mirabel,
        R.drawable.pocahontas,
        R.drawable.raiponce
    )
    val prenom = listOf(
        "Devine qui tu est !",
        "je suis merida",
        "je suis mirabel",
        "je suis pocahontas",
        "je suis raiponce"
    )
    var indexImage by remember { mutableIntStateOf(0) }

    val localConfiguration = LocalConfiguration.current
    val width = localConfiguration.screenWidthDp
    val height = localConfiguration.screenHeightDp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(top = 50.dp, bottom = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
            Image(
                painter = painterResource(id = image[indexImage]),
                contentDescription = null,
                modifier = Modifier
                    .height((height / 2).dp)
                    .width((width * 0.8).dp)
                    .padding(20.dp)
                    .clip(RoundedCornerShape(percent = 5)),
                contentScale = ContentScale.Crop
            )
            Text(text = prenom[indexImage])
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                indexImage = (1 until image.size).random()}) {
                Text(text = "C'est parti !")
            }
            IconButton(onClick = {indexImage = 0}) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrincesseTheme {
        ScaffoldComposable()
    }
}
