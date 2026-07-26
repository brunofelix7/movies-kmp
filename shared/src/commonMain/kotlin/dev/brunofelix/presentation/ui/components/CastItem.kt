package dev.brunofelix.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import movies_kmp.shared.generated.resources.Res
import movies_kmp.shared.generated.resources.poster_path
import org.jetbrains.compose.resources.painterResource

@Composable
fun CastItem(
    name: String,
    character: String,
    profileUrl: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(76.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(Res.drawable.poster_path),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(
                        MaterialTheme.shapes.medium.copy(
                            topEnd = ZeroCornerSize,
                            bottomEnd = ZeroCornerSize
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = character,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CastItem(
        name = "Tom Holland",
        character = "Peter Parker",
        profileUrl = "{profileUrl}"
    )
}