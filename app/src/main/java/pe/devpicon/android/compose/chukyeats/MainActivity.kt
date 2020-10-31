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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.title_chuky),
            style = TextStyle(
                color = Color.Black,
                fontSize = 28.sp
            )
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = R.string.title_eats),
            style = TextStyle(
                color = Color.Green,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        )

    }
}

@Composable
fun NavigationBottomBar() {
    Box(
        modifier = Modifier
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