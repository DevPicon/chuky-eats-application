package pe.devpicon.android.compose.chukyeats.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import pe.devpicon.android.compose.chukyeats.R

@Composable
fun HomeScreen() {
    Surface(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ChukySlogan()
            Spacer(Modifier.height(16.dp))
            CustomerAddress(
                address = "2700 West Victory",
                Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ChukySlogan() {
    val content = AnnotatedString.Builder().apply {
        val commonStyle = SpanStyle(
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        append(
            AnnotatedString(
                text = stringResource(id = R.string.label_slogan),
                spanStyle = commonStyle
            )
        )
        append(
            AnnotatedString(
                text = stringResource(id = R.string.title_chuky),
                spanStyle = commonStyle.copy(Color.Green)
            )
        )
    }.toAnnotatedString()
    Text(text = content)
}

@Composable
fun CustomerAddress(
    address: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = address,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = modifier
            .background(Color.Black, shape = RoundedCornerShape(CornerSize(4.dp)))
            .padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyChukyEatsApplicationTheme {
        HomeScreen()
    }
}