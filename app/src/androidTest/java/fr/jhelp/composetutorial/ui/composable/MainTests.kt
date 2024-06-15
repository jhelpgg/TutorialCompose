package fr.jhelp.composetutorial.ui.composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import fr.jhelp.composetutorial.MainActivity
import fr.jhelp.model.action.main.MainActionChangeScreen
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
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test

class MainTests
{
    companion object
    {
        val mainDataMutable = mutableStateOf(MainData(screen = Screen.SPLASH))
        val mainModel: MainModel = MainDummy(MainTests.mainDataMutable)
        { mainAction ->
            when (mainAction)
            {
                is MainActionChangeScreen -> MainTests.mainDataMutable.value =
                    MainData(screen = mainAction.screen)
            }
        }

        @JvmStatic
        @BeforeClass
        fun initialize()
        {
            provideSingle<SplashModel> { SplashDummy() }
            provideSingle<HomeModel> { HomeDummy(HomeData(information = "Information")) }
            provideSingle<MainModel> { MainTests.mainModel }
        }
    }

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun mainChangeScreen()
    {
        this.composeTestRule.onNodeWithTag(MAIN_CONTAINER)
            .assertExists("No main container")
            .assertIsDisplayed()

        this.composeTestRule.onNodeWithTag(SPLASH_IMAGE)
            .assertExists("No splash image")
            .assertIsDisplayed()

        this.composeTestRule.onNodeWithTag(HOME_CONTAINER)
            .assertDoesNotExist()

        MainTests.mainModel.action(MainActionChangeScreen(screen = Screen.HOME))

        this.composeTestRule.onNodeWithTag(SPLASH_IMAGE)
            .assertDoesNotExist()

        this.composeTestRule.onNodeWithTag(HOME_CONTAINER)
            .assertExists("No home container")
            .assertIsDisplayed()
    }
}
