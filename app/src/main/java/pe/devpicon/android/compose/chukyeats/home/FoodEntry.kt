package pe.devpicon.android.compose.chukyeats.home

import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import pe.devpicon.android.compose.chukyeats.FoodItem
import pe.devpicon.android.compose.chukyeats.PriceType
import pe.devpicon.android.compose.chukyeats.R

@Composable
fun FoodEntry(model: FoodItem) {

}

@Preview
@Composable
fun FoodEntryPreview() {
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