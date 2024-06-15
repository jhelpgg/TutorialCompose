package fr.jhelp.composetutorial.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import fr.jhelp.composetutorial.R
import fr.jhelp.model.shared.SplashModel
import fr.jhelp.tool.providers.provided

class SplashComposable
{
    private val splashModel by provided<SplashModel>()

    @Composable
    fun Show(modifier: Modifier = Modifier)
    {
        LaunchedEffect(key1 = Unit) { this@SplashComposable.splashModel.splashShow() }

        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
              contentDescription = "Splash screen",
              contentScale = ContentScale.FillBounds,
              modifier = modifier.fillMaxSize().testTag(SPLASH_IMAGE))
    }
}
