package pe.devpicon.android.compose.chukyeats

import androidx.compose.foundation.Text
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import pe.devpicon.android.compose.chukyeats.home.*
import pe.devpicon.android.compose.chukyeats.ui.MyChukyEatsApplicationTheme

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
                FoodScreenNavigatable(id, viewModel = FoodDetailViewModel())
            }
        }
    }

}

@Composable
fun NavigationBottomBar(navController: NavController) {
    BottomNavigation {
        BottomNavigationItem(
            label = { Text("Home") },
            icon = { Icons.Default.Home },
            selected = false,
            onClick = { navController.navigate("home") }
        )
        BottomNavigationItem(
            label = { Text("Profile") },
            icon = { Icons.Default.Person },
            selected = false,
            onClick = { navController.navigate("profile") }
        )
        BottomNavigationItem(
            label = { Text("Detail") },
            icon = { Icons.Default.Person },
            selected = false,
            onClick = { navController.navigate("food/1") }
        )
        BottomNavigationItem(
            label = { Text("Detail 2") },
            icon = { Icons.Default.Person },
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