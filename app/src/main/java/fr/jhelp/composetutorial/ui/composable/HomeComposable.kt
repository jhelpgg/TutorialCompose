package fr.jhelp.composetutorial.ui.composable

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import fr.jhelp.model.action.home.HomeActionCallData
import fr.jhelp.model.action.home.HomeActionChangeInformation
import fr.jhelp.model.shared.HomeModel
import fr.jhelp.tool.providers.provided

class HomeComposable
{
    private val homeModel by provided<HomeModel>()

    @Composable
    fun Show(modifier: Modifier = Modifier)
    {
        val homeData by this.homeModel.data.composeState

        ConstraintLayout(modifier = modifier.testTag(HOME_CONTAINER))
        {
            val (text, button) = this.createRefs()

            Text(text = homeData.information,
                 textAlign = TextAlign.Center,
                 modifier = Modifier
                     .constrainAs(text)
                     {
                         this.width = Dimension.fillToConstraints
                         this.height = Dimension.wrapContent

                         this.top.linkTo(this.parent.top)
                         this.bottom.linkTo(button.top)
                         this.start.linkTo(this.parent.start)
                         this.end.linkTo(this.parent.end)
                     }
                     .testTag(HOME_TEXT_INFORMATION))

            Button(onClick =
                   {
                       this@HomeComposable.homeModel.action(HomeActionCallData)
                   },
                   modifier = Modifier
                       .constrainAs(button)
                       {
                           this.width = Dimension.fillToConstraints
                           this.height = Dimension.wrapContent

                           // top free
                           this.bottom.linkTo(this.parent.bottom, 32.dp)
                           this.start.linkTo(this.parent.start, 16.dp)
                           this.end.linkTo(this.parent.end, 16.dp)
                       }
                       .testTag(HOME_BUTTON_CHANGE))
            {
                Text(text = "Change")
            }
        }
    }
}