package fr.jhelp.composetutorial.ui.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.jhelp.composetutorial.ui.composable.HomeComposable
import fr.jhelp.composetutorial.ui.theme.ComposeTutorialTheme
import fr.jhelp.model.data.HomeData
import fr.jhelp.model.dummy.HomeDummy
import fr.jhelp.model.shared.HomeModel
import fr.jhelp.tool.providers.provideSingle

@Preview(showBackground = true)
@Composable
fun HomeComposablePreview()
{
    provideSingle<HomeModel> { HomeDummy(HomeData(information = "Example")) }
    val homeComposable = HomeComposable()

    ComposeTutorialTheme { homeComposable.Show(modifier = Modifier.fillMaxSize()) }
}
