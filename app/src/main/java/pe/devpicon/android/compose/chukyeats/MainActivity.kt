package pe.devpicon.android.compose.chukyeats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import pe.devpicon.android.compose.chukyeats.ui.MyChukyEatsApplicationTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyChukyEatsApplicationTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = { UberTopBar() },
            bottomBar = { NavigationBottomBar() }
        ) {

        }
    }
}

@Composable
fun UberTopBar() {
    Box(modifier = Modifier
        .background(Color.Red)
        .height(60.dp)
        .fillMaxWidth()
    )
}

@Composable
fun NavigationBottomBar() {
    Box(modifier = Modifier
        .background(Color.Green)
        .height(60.dp)
        .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyChukyEatsApplicationTheme {
        MainApp()
    }
}