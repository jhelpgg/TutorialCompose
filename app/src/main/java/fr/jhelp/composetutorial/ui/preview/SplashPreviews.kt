package fr.jhelp.composetutorial.ui.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.jhelp.composetutorial.ui.composable.SplashComposable
import fr.jhelp.composetutorial.ui.theme.ComposeTutorialTheme
import fr.jhelp.model.dummy.SplashDummy
import fr.jhelp.model.shared.SplashModel
import fr.jhelp.tool.providers.provideSingle

@Preview(showBackground = true)
@Composable
fun SplashComposablePreview()
{
    provideSingle<SplashModel> { SplashDummy() }
    val splashComposable = SplashComposable()

    ComposeTutorialTheme { splashComposable.Show(modifier = Modifier.fillMaxSize()) }
}
                           