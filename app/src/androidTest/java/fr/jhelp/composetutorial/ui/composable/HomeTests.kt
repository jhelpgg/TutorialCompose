package fr.jhelp.composetutorial.ui.composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import fr.jhelp.composetutorial.MainActivity
import fr.jhelp.model.action.home.HomeActionChangeInformation
import fr.jhelp.model.data.HomeData
import fr.jhelp.model.data.MainData
import fr.jhelp.model.data.Screen
import fr.jhelp.model.dummy.HomeDummy
import fr.jhelp.model.dummy.MainDummy
import fr.jhelp.model.shared.HomeModel
import fr.jhelp.model.shared.MainModel
import fr.jhelp.tool.providers.provideSingle
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test

class HomeTests
{
    companion object
    {
        val homeDataMutable = mutableStateOf(HomeData(information = "First"))

        @JvmStatic
        @BeforeClass
        fun initialize()
        {
            provideSingle<MainModel> { MainDummy(MainData(screen = Screen.HOME)) }

            provideSingle<HomeModel> {
                HomeDummy(HomeTests.homeDataMutable)
                {homeAction ->
                    when(homeAction)
                    {
                        is HomeActionChangeInformation -> HomeTests.homeDataMutable.value = HomeData(information = homeAction.information)
                    }
                }
            }
        }
    }

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun homeChangeInformation()
    {
        this.composeTestRule.onNodeWithTag(HOME_CONTAINER)
            .assertExists("No home container")
            .assertIsDisplayed()

        this.composeTestRule.onNodeWithTag(HOME_TEXT_INFORMATION)
            .assertExists("No home information")
            .assertIsDisplayed()
            .assertTextEquals("First")


        this.composeTestRule.onNodeWithTag(HOME_BUTTON_CHANGE)
            .assertExists("No home change button")
            .assertIsDisplayed()
            .assertTextEquals("Change")
            .assertHasClickAction()
            .performClick()

        this.composeTestRule.onNodeWithTag(HOME_TEXT_INFORMATION)
            .assertTextContains("Number :", substring = true, ignoreCase = false)
    }
}