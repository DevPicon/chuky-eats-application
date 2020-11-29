package pe.devpicon.android.compose.chukyeats.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.onActive
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.ui.tooling.preview.Preview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import pe.devpicon.android.compose.chukyeats.FoodDetail
import pe.devpicon.android.compose.chukyeats.R
import pe.devpicon.android.compose.chukyeats.ui.MyChukyEatsApplicationTheme
import pe.devpicon.android.compose.chukyeats.ui.greenChuky

class FoodDetailViewModel: ViewModel() {

    private val _detail = MutableLiveData<FoodDetail>()
    val detail: LiveData<FoodDetail> = _detail

    fun load(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(5000)
            _detail.postValue(FoodDetail(
                name = if(id == "1") "Wok-Fired Shrimp" else "Pavo navideno",
                description = "Wok-Fired Shrimp features seared premium marinated shrimp with fresh cut vegetables (sugar snap peas, chopped red bell peppers and yellow onions), all wok-tossed in a sauce that's sweet, savory and spicy.",
                photoId = R.drawable.food04
            ))
        }
        //...
    }
}

@Composable
fun FoodScreenNavigatable(
    id: String,
    viewModel: FoodDetailViewModel
) {
    viewModel.load(id)

    val detail = viewModel.detail.observeAsState().value

    if(detail == null) {
        CircularProgressIndicator()
    } else {
        FoodScreen(foodDetail = detail)
    }
}


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
            Image(
                    asset = imageResource(id = foodDetail.photoId),
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                            .size(244.dp)
                            .padding(8.dp)
                            .drawShadow(
                                    elevation = 8.dp,
                                    shape = CircleShape
                            )
                            .align(Alignment.CenterHorizontally)
            )
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
                colors = ButtonConstants.defaultButtonColors(
                    backgroundColor = greenChuky
                ),
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
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
                shape = MaterialTheme.shapes.small
            )
            .height(ButtonConstants.DefaultMinHeight)
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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface,
                shape = MaterialTheme.shapes.small
            )
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
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

    ConstraintLayout(
        modifier = modifier
            .padding(8.dp)
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