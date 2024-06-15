package fr.jhelp.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import fr.jhelp.composetutorial.ui.composable.MainComposable
import fr.jhelp.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity()
{
    private val mainComposable = MainComposable()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTutorialTheme {
                this@MainActivity.mainComposable.Show(Modifier.fillMaxSize())
            }
        }
    }
}
