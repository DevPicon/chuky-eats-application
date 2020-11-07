package pe.devpicon.android.compose.chukyeats.home

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import pe.devpicon.android.compose.chukyeats.FoodItem
import pe.devpicon.android.compose.chukyeats.PriceType
import pe.devpicon.android.compose.chukyeats.R
import pe.devpicon.android.compose.chukyeats.ui.MyChukyEatsApplicationTheme
import pe.devpicon.android.compose.chukyeats.ui.greenChuky

@Composable
fun HomeScreen() {

    val foodList = listOf(FoodItem(
            name = "McDonald's (Pacolma - Osborne)",
            priceType = PriceType.Cheap,
            tags = listOf("American", "Fast Food", "Burgers"),
            estimatedDelivery = "15-20 min",
            rating = 4.6f,
            deliveryFee = 5.99f,
            photoId = R.drawable.food01
    ), FoodItem(
            name = "McDonald's (Pacolma - Osborne)",
            priceType = PriceType.Cheap,
            tags = listOf("American", "Fast Food", "Burgers"),
            estimatedDelivery = "15-20 min",
            rating = 4.6f,
            deliveryFee = 5.99f,
            photoId = R.drawable.food01
    ), FoodItem(
            name = "McDonald's (Pacolma - Osborne)",
            priceType = PriceType.Cheap,
            tags = listOf("American", "Fast Food", "Burgers"),
            estimatedDelivery = "15-20 min",
            rating = 4.6f,
            deliveryFee = 5.99f,
            photoId = R.drawable.food01
    ))

    Surface(Modifier.fillMaxSize()) {
        Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ChukySlogan()
            Spacer(Modifier.height(16.dp))
            CustomerAddress(
                    address = "2700 West Victory",
                    Modifier.fillMaxWidth()
            )
            Spacer(
                    modifier = Modifier
                            .height(8.dp)
                            .fillMaxWidth()
            )
            LazyColumnFor(items = foodList) {
                FoodEntry(model = it)
            }
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
                        spanStyle = commonStyle.copy(greenChuky)
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
                    .padding(
                            top = 8.dp,
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 8.dp
                    )
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyChukyEatsApplicationTheme {
        HomeScreen()
    }
}