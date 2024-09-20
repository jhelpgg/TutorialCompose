package fr.jhelp.composetutorial.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import fr.jhelp.model.data.Screen
import fr.jhelp.model.shared.MainModel
import fr.jhelp.tool.providers.provided

class MainComposable
{
    private val mainModel by provided<MainModel>()
    private val splashComposable by lazy { SplashComposable() }
    private val homeComposable by lazy { HomeComposable() }

    @Composable
    fun Show(modifier: Modifier = Modifier)
    {
        val mainData by this.mainModel.data


        Scaffold(modifier = modifier.testTag(MAIN_CONTAINER)) { innerPadding ->
            val innerModifier = modifier.padding(innerPadding)

            when (mainData.screen)
            {
                Screen.SPLASH -> this.splashComposable.Show(modifier = innerModifier)

                Screen.HOME   -> this.homeComposable.Show(modifier = innerModifier)
            }
        }
    }
}
