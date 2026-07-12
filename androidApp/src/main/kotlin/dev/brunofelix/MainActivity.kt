package dev.brunofelix

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowInsetsControllerCompat
import dev.brunofelix.presentation.ui.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.dark(Color.Black.hashCode()),
            statusBarStyle = SystemBarStyle.dark(Color.Black.hashCode())
        )
        super.onCreate(savedInstanceState)

        setContent {
            val view = LocalView.current
            SideEffect {
                val window = (view.context as Activity).window
                val controller = WindowInsetsControllerCompat(window, view)
                // Status Bar icons colors (false for light icons, true for dark icons)
                controller.isAppearanceLightStatusBars = false

                // Navigation Bar icons colors (true for dark icons, false for light icons)
                controller.isAppearanceLightNavigationBars = false
            }
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}