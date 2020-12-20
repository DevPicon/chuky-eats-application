package pe.devpicon.android.compose.chukyeats

import androidx.compose.foundation.Text
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import pe.devpicon.android.compose.chukyeats.feature.HomeScreen
import pe.devpicon.android.compose.chukyeats.feature.fooddetail.FoodScreenNavigatable
import pe.devpicon.android.compose.chukyeats.feature.profile.ProfileScreen
import pe.devpicon.android.compose.chukyeats.ui.MyChukyEatsApplicationTheme
import pe.devpicon.android.compose.chukyeats.ui.greenChuky

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { NavigationBottomBar(navController) }
    )
    {
        NavHost(navController, startDestination = "home") {
            composable("home") { HomeScreen() }
            composable("profile") { ProfileScreen() }
            composable("food/{id}") {
                val id = it.arguments?.getString("id")!!
                FoodScreenNavigatable(id)
            }
        }
    }

}

@Composable
fun NavigationBottomBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface
    ) {
        BottomNavigationItem(
            label = { Text("Home") },
            icon = {
                Icon(
                    asset = Icons.Filled.Home,
                    tint = greenChuky
                )
            },
            selected = true,
            onClick = { navController.navigate("home") }
        )
        BottomNavigationItem(
            label = { Text("Place") },
            icon = {
                Icon(
                    asset = Icons.Outlined.Place,
                    tint = MaterialTheme.colors.onSurface
                )
            },
            selected = false,
            onClick = { navController.navigate("profile") }
        )
        BottomNavigationItem(
            label = { Text("Search") },
            icon = {
                Icon(
                    asset = Icons.Outlined.Search,
                    tint = MaterialTheme.colors.onSurface
                )
            },
            selected = false,
            onClick = { navController.navigate("profile") }
        )
        BottomNavigationItem(
            label = { Text("Detail") },
            icon = {
                Icon(
                    asset = Icons.Outlined.ShoppingCart,
                    tint = MaterialTheme.colors.onSurface
                )
            },
            selected = false,
            onClick = { navController.navigate("food/1") }
        )
        BottomNavigationItem(
            label = { Text("Detail 2") },
            icon = {
                Icon(
                    asset = Icons.Outlined.Person,
                    tint = MaterialTheme.colors.onSurface
                )
            },
            selected = false,
            onClick = { navController.navigate("food/2") }
        )
    }
}

@Preview
@Composable
fun MainNavigationPreview() {
    MyChukyEatsApplicationTheme {
        MainNavigation()
    }
}