package pe.devpicon.android.compose.chukyeats

import androidx.annotation.DrawableRes

data class FoodItem(
    val name: String,
    val tags: List<String>,
    val priceType: PriceType,
    val estimatedDelivery: String,
    val rating: Float,
    val deliveryFee: Float,
    @DrawableRes val photoId: Int
)

enum class PriceType { Cheap, Affordable, Expensive }