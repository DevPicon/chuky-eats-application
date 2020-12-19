package pe.devpicon.android.compose.chukyeats.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
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
fun FoodEntry(model: FoodItem) {

    val tags = model.tags.joinToString(separator = " - ")

    Card(modifier = Modifier
            .fillMaxWidth()
            .padding(
                    top = 16.dp,
                    bottom = 16.dp
            ),
            shape = MaterialTheme.shapes.small) {
        Column(modifier = Modifier
                .fillMaxWidth()
                .background(greenChuky),
                horizontalAlignment = Alignment.CenterHorizontally) {

            FoodPicture(model)

            Spacer(Modifier.height(8.dp))
            Text(text = model.name,
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary)
            Text(text = tags,
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary)
            Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly) {
                EstimatedDelivery(value = model.estimatedDelivery)
                Rating(value = model.rating)
                DeliveryFee(value = model.deliveryFee)
            }
            Spacer(Modifier.height(8.dp))
        }
    }

}

@Composable
private fun FoodPicture(
        model: FoodItem,
        modifier: Modifier = Modifier
) {
    ConstraintLayout(
            modifier = modifier.fillMaxWidth()
    )
    {

        val (imageFood, iconFavorite) = createRefs()

        Image(
                asset = imageResource(id = model.photoId),
                contentScale = ContentScale.FillWidth,
                modifier = modifier
                        .constrainAs(imageFood) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .fillMaxWidth()
                        .aspectRatio(16 / 7f))

        Image(asset = Icons.Default.Favorite,
                modifier = modifier
                        .constrainAs(iconFavorite) {
                            bottom.linkTo(
                                    anchor = imageFood.bottom,
                                    margin = 8.dp
                            )
                            end.linkTo(
                                    anchor = imageFood.end,
                                    margin = 8.dp
                            )
                        }
                        .width(32.dp)
                        .height(32.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary))

    }
}

@Composable
fun EstimatedDelivery(value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
                asset = vectorResource(id = R.drawable.ic_time),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary),
                modifier = Modifier.width(16.dp).height(16.dp),
        )
        Text(
                text = value,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun Rating(value: Float) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = value.toString(),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onPrimary)
        Image(asset = Icons.Default.Star,
                modifier = Modifier.width(16.dp).height(16.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary))
    }
}

@Composable
fun DeliveryFee(value: Float) {
    Text(text = stringResource(
            id = R.string.label_delivery_fee,
            formatArgs = arrayOf(value)),
            fontSize = 12.sp,
            color = MaterialTheme.colors.onPrimary)
}

@Preview(showBackground = true)
@Composable
fun FoodEntryPreview() {
    MyChukyEatsApplicationTheme() {
        FoodEntry(model = FoodItem(
                name = "McDonald's (Pacolma - Osborne)",
                priceType = PriceType.Cheap,
                tags = listOf("American", "Fast Food", "Burgers"),
                estimatedDelivery = "15-20 min",
                rating = 4.6f,
                deliveryFee = 5.99f,
                photoId = R.drawable.food01
        ))
    }
}