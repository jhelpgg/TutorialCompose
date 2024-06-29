package fr.jhelp.model.implementation

import fr.jhelp.model.action.home.HomeActionChangeInformation
import fr.jhelp.tool.tasks.ImmediateDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Test

class HomeTests
{
    @Test
    fun homeChangeInformation()
    {
        Dispatchers.setMain(ImmediateDispatcher)
        val homeModel = HomeImplementation()
        Assert.assertEquals("Information", homeModel.data.composeState.value.information)
        homeModel.action(HomeActionChangeInformation("New"))
        Assert.assertEquals("New", homeModel.data.composeState.value.information)
    }
}