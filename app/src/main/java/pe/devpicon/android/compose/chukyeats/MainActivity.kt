package pe.devpicon.android.compose.chukyeats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import pe.devpicon.android.compose.chukyeats.home.HomeScreen
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
            topBar = { ChukyTopBar() },
            bottomBar = { NavigationBottomBar() }
        ) {
            HomeScreen()
        }
    }
}

@Composable
fun ChukyTopBar() {
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val content = AnnotatedString.Builder().apply {
            val defaultStyle = SpanStyle(
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            append(AnnotatedString(text = stringResource(id = R.string.title_chuky), defaultStyle))
            append(AnnotatedString(text = " ", defaultStyle))
            append(
                AnnotatedString(
                    text = stringResource(id = R.string.title_eats),
                    defaultStyle.copy(color = Color.Green)
                )
            )
        }.toAnnotatedString()
        Text(text = content)

    }
}

@Composable
fun NavigationBottomBar() {
    Box(
        modifier = Modifier
            .background(Color.LightGray)
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