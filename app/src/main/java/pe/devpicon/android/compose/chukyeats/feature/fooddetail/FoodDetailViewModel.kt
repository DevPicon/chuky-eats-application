package pe.devpicon.android.compose.chukyeats.feature.fooddetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.devpicon.android.compose.chukyeats.FoodDetail
import pe.devpicon.android.compose.chukyeats.R

class FoodDetailViewModel : ViewModel() {

    private val _detail: MutableLiveData<ScreenState> = MutableLiveData(ScreenState.Loading)
    val detail: LiveData<ScreenState> = _detail

    fun load(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(5000)
            _detail.postValue(
                ScreenState.Ready(
                    FoodDetail(
                        name = if (id == "1") "Wok-Fired Shrimp" else "Pavo navideno",
                        description = "Wok-Fired Shrimp features seared premium marinated shrimp with fresh cut vegetables (sugar snap peas, chopped red bell peppers and yellow onions), all wok-tossed in a sauce that's sweet, savory and spicy.",
                        photoId = R.drawable.food04
                    )
                )
            )
        }
        //...
    }
}

sealed class ScreenState {
    object Loading : ScreenState()
    data class Ready(val foodDetail: FoodDetail) : ScreenState()
}