package com.hereliesaz.all24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hereliesaz.all24.ui.navigation.AppNavigation
import com.hereliesaz.all24.ui.theme.All24Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            All24Theme {
                AppNavigation()
            }
        }
    }
}
