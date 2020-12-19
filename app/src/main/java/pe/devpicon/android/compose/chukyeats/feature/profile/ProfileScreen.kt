package pe.devpicon.android.compose.chukyeats.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import pe.devpicon.android.compose.chukyeats.R
import pe.devpicon.android.compose.chukyeats.ui.MyChukyEatsApplicationTheme

val options = listOf(
    ProfileOption(
        text = "Notification",
        icon = Icons.Filled.Notifications
    ),
    ProfileOption(
        text = "Help",
        icon = Icons.Filled.Info
    )
)

data class ProfileOption(
    val text: String,
    val icon: VectorAsset
)

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            ProfileHeader()
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumnFor(
                items = options,
                modifier = Modifier.fillMaxWidth()

            ) {
                ProfileOptionItem(text = it.text, icon = it.icon)
            }
        }
    }
}

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.label_profile),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
        Box(
            modifier = Modifier
                .padding(8.dp)
                .size(64.dp)
                .clip(MaterialTheme.shapes.medium)
                .drawShadow(4.dp)
                .background(Color.DarkGray)
        )
        Text(
            text = "Chuky Bueno",
            fontSize = 14.sp
        )
    }
}

@Composable
fun ProfileOptionItem(
    text: String,
    icon: VectorAsset,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit) = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ) {
        Image(
            asset = icon,
            modifier = Modifier.padding(
                4.dp
            )
        )
        Text(
            text = text,
            modifier = Modifier.padding(8.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MyChukyEatsApplicationTheme {
        ProfileScreen(
            modifier = Modifier.fillMaxWidth()
        )
    }
}