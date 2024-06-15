package fr.jhelp.composetutorial.ui.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.jhelp.composetutorial.ui.composable.MainComposable
import fr.jhelp.composetutorial.ui.theme.ComposeTutorialTheme
import fr.jhelp.model.data.HomeData
import fr.jhelp.model.data.MainData
import fr.jhelp.model.data.Screen
import fr.jhelp.model.dummy.HomeDummy
import fr.jhelp.model.dummy.MainDummy
import fr.jhelp.model.dummy.SplashDummy
import fr.jhelp.model.shared.HomeModel
import fr.jhelp.model.shared.MainModel
import fr.jhelp.model.shared.SplashModel
import fr.jhelp.tool.providers.provideSingle

@Preview(showBackground = true)
@Composable
fun MainComposableSplashPreview()
{
    provideSingle<MainModel> { MainDummy(MainData(screen = Screen.SPLASH)) }
    provideSingle<SplashModel> { SplashDummy() }
    val mainComposable = MainComposable()

    ComposeTutorialTheme { mainComposable.Show(modifier = Modifier.fillMaxSize()) }
}


@Preview(showBackground = true)
@Composable
fun MainComposableHomePreview()
{
    provideSingle<MainModel> { MainDummy(MainData(screen = Screen.HOME)) }
    provideSingle<HomeModel> { HomeDummy(HomeData(information = "Example")) }
    val mainComposable = MainComposable()

    ComposeTutorialTheme { mainComposable.Show(modifier = Modifier.fillMaxSize()) }
}
