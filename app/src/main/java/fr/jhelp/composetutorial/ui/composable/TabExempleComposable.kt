package fr.jhelp.composetutorial.ui.composable

import android.widget.ToggleButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import fr.jhelp.composetutorial.R

class TabExempleComposable
{
    @Composable
    fun currentView(selectedIndex:Int, modifier: Modifier)
    {
        when(selectedIndex)
        {
            0 -> Screen0()
            1 -> Screen1()
            2 -> Screen2()
        }
    }

    @Composable
    fun Screen0()
    {
        Text("Hello this screen 0 !")
    }


    @Composable
    fun Screen1()
    {
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
              contentDescription = "")
    }


    @Composable
    fun Screen2()
    {
        Text("The last screen !")
    }

    @Composable
    fun Show(modifier: Modifier = Modifier)
    {
        ConstraintLayout(modifier = modifier) {
            var selectedTabIndex: Int by remember { mutableStateOf(0) }
            val (holder, tabBar) = this.createRefs()

            this@TabExempleComposable.currentView(selectedIndex =  selectedTabIndex,
                                                  modifier =
                                                  modifier.constrainAs(holder) {
                                                      this.width = Dimension.fillToConstraints
                                                      this.height = Dimension.fillToConstraints

                                                      this.top.linkTo(this.parent.top, 8.dp)
                                                      this.start.linkTo(this.parent.start, 8.dp)
                                                      this.end.linkTo(this.parent.end, 8.dp)
                                                      this.bottom.linkTo(tabBar.top, 16.dp)
                                                  }
                                                 )
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = modifier.constrainAs(tabBar) {
                    this.width = Dimension.fillToConstraints
                    this.height = Dimension.wrapContent

                    // top
                    this.start.linkTo(this.parent.start, 8.dp)
                    this.end.linkTo(this.parent.end, 8.dp)
                    this.bottom.linkTo(this.parent.bottom, 8.dp)

                }) {
                    for(index in 0 until 3)
                    {
                        Tab(selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = { Text("Title $index") })
                    }
                }
        }
    }
}