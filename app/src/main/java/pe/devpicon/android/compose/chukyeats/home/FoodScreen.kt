package pe.devpicon.android.compose.chukyeats.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import pe.devpicon.android.compose.chukyeats.FoodDetail
import pe.devpicon.android.compose.chukyeats.R
import pe.devpicon.android.compose.chukyeats.ui.MyChukyEatsApplicationTheme
import pe.devpicon.android.compose.chukyeats.ui.greenChuky

@Composable
fun FoodScreen(
        foodDetail: FoodDetail,
        modifier: Modifier = Modifier
) {
    Surface(
            modifier = modifier
                    .fillMaxSize()
    ) {
        Column(
                modifier = modifier
                        .padding(32.dp)
        ) {
            Image(asset = imageResource(id = foodDetail.photoId))
            Text(
                    text = foodDetail.name,
                    modifier = modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
            )
            Text(
                    text = foodDetail.description,
                    modifier = modifier
                            .padding(
                                    top = 8.dp,
                                    bottom = 8.dp,
                                    start = 24.dp,
                                    end = 24.dp
                            ),
                    textAlign = TextAlign.Justify,
                    fontSize = 12.sp
            )
            Spacer(
                    modifier = modifier
                            .fillMaxWidth()
                            .height(16.dp)
            )
            ItemCustomization(modifier)
            Spacer(
                    modifier = modifier
                            .fillMaxWidth()
                            .height(16.dp)
            )
            ItemQuantity(modifier)
            Spacer(
                    modifier = modifier
                            .fillMaxWidth()
                            .height(16.dp)
            )
            Button(
                    onClick = {},
                    backgroundColor = greenChuky,
                    shape = MaterialTheme.shapes.medium,
                    modifier = modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
            ) {
                Text(
                        text = stringResource(id = R.string.label_add_item_product),
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onPrimary,
                        fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Composable
fun ItemQuantity(modifier: Modifier = Modifier) {
    Box(modifier = modifier
            .fillMaxWidth()
            .height(24.dp)
            .background(MaterialTheme.colors.onSurface)
    ) {
        Row(
                modifier = modifier.align(Alignment.Center)
                        .fillMaxWidth()
                        .padding(4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                    text = "-",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = modifier
            )
            Spacer(
                    modifier = modifier
                            .fillMaxHeight()
                            .width(8.dp)
            )
            Text(
                    text = "1",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = modifier
            )
            Spacer(
                    modifier = modifier
                            .fillMaxHeight()
                            .width(8.dp)
            )
            Text(
                    text = "+",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = modifier
            )
        }
    }
}

@Composable
fun ItemCustomization(modifier: Modifier = Modifier) {
    Column(modifier = modifier
            .fillMaxWidth()
            .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurface,
                    shape = MaterialTheme.shapes.small

            )
    ) {
        CustomOptionRow(
                optionSize = "Small",
                selected = true,
                modifier = modifier
        )
        CustomOptionRow(
                optionSize = "Medium",
                optionAdditionalCost = "+ US $3.95",
                modifier = modifier
        )
        CustomOptionRow(
                optionSize = "Large",
                optionAdditionalCost = "+ US $7.20",
                modifier = modifier
        )
    }
}

@Composable
private fun CustomOptionRow(
        optionSize: String,
        optionAdditionalCost: String = "",
        selected: Boolean = false,
        modifier: Modifier = Modifier
) {
    Row(
            modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
    ) {
        ConstraintLayout(
                modifier = modifier
                        .fillMaxWidth()
        ) {

            val (radioButton, textSize, textAdditionalCost) = createRefs()

            RadioButton(
                    selected = selected,
                    onClick = {},
                    modifier = modifier
                            .padding(8.dp)
                            .size(10.dp)
                            .constrainAs(radioButton) {
                                start.linkTo(
                                        anchor = parent.start,
                                        margin = 4.dp
                                )
                                end.linkTo(
                                        anchor = textSize.start,
                                        margin = 8.dp
                                )
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
            )
            Text(
                    text = optionSize,
                    fontSize = 10.sp,
                    modifier = modifier
                            .constrainAs(textSize) {
                                start.linkTo(
                                        anchor = radioButton.end,
                                        margin = 8.dp
                                )
                                top.linkTo(radioButton.top)
                                bottom.linkTo(radioButton.bottom)
                            }
            )
            Text(
                    text = optionAdditionalCost,
                    fontSize = 10.sp,
                    modifier = modifier
                            .constrainAs(textAdditionalCost) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
            )
        }
    }
}

@Preview
@Composable
fun PreviewFoodScreen() {
    MyChukyEatsApplicationTheme {
        FoodScreen(foodDetail = foodDetail)
    }
}

val foodDetail = FoodDetail(
        name = "Wok-Fired Shrimp",
        description = "Wok-Fired Shrimp features seared premium marinated shrimp with fresh cut vegetables (sugar snap peas, chopped red bell peppers and yellow onions), all wok-tossed in a sauce that's sweet, savory and spicy.",
        photoId = R.drawable.food04
)