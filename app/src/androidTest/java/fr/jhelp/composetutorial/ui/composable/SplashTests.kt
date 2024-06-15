package fr.jhelp.composetutorial.ui.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import fr.jhelp.composetutorial.MainActivity
import fr.jhelp.model.data.MainData
import fr.jhelp.model.data.Screen
import fr.jhelp.model.dummy.MainDummy
import fr.jhelp.model.dummy.SplashDummy
import fr.jhelp.model.shared.MainModel
import fr.jhelp.model.shared.SplashModel
import fr.jhelp.tool.providers.provideSingle
import java.util.concurrent.atomic.AtomicBoolean
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test

class SplashTests
{
    companion object
    {
        val splashActionCalled = AtomicBoolean(false)

        @JvmStatic
        @BeforeClass
        fun initialization()
        {
            provideSingle<MainModel> { MainDummy(MainData(screen = Screen.SPLASH)) }

            provideSingle<SplashModel> { SplashDummy { SplashTests.splashActionCalled.set(true) } }
        }
    }

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun splashTest()
    {
        this.composeTestRule.onNodeWithTag(SPLASH_IMAGE)
            .assertExists("No image on splash screen")
            .assertIsDisplayed()
        Assert.assertTrue("The splash function should be called", SplashTests.splashActionCalled.get())
    }
}