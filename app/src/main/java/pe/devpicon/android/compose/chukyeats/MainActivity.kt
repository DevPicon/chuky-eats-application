package pe.devpicon.android.compose.chukyeats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
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
import pe.devpicon.android.compose.chukyeats.feature.HomeScreen
import pe.devpicon.android.compose.chukyeats.ui.MyChukyEatsApplicationTheme
import pe.devpicon.android.compose.chukyeats.ui.greenChuky

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
    BottomNavigation(
            backgroundColor = MaterialTheme.colors.surface
    ) {
        BottomNavigationItem(
                icon = {
                    Icon(
                            asset = Icons.Filled.Home,
                            tint = greenChuky
                    )
                },
                selected = true,
                onClick = {}
        )
        BottomNavigationItem(
                icon = {
                    Icon(
                            asset = Icons.Outlined.Place,
                            tint = MaterialTheme.colors.onSurface
                    )
                },
                selected = false,
                onClick = {}
        )
        BottomNavigationItem(
                icon = {
                    Icon(
                            asset = Icons.Outlined.Search,
                            tint = MaterialTheme.colors.onSurface
                    )
                },
                selected = false,
                onClick = {}
        )
        BottomNavigationItem(
                icon = {
                    Icon(
                            asset = Icons.Outlined.ShoppingCart,
                            tint = MaterialTheme.colors.onSurface
                    )
                },
                selected = false,
                onClick = {}
        )
        BottomNavigationItem(
                icon = {
                    Icon(
                            asset = Icons.Outlined.Person,
                            tint = MaterialTheme.colors.onSurface
                    )
                },
                selected = false,
                onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyChukyEatsApplicationTheme {
        MainApp()
    }
}