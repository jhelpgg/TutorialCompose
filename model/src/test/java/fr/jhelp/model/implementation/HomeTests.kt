package fr.jhelp.model.implementation

import fr.jhelp.model.action.home.HomeActionChangeInformation
import fr.jhelp.model.shared.HomeModel
import fr.jhelp.model.tools.ImmediateDispatcher
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
        Assert.assertEquals("Information", homeModel.data.value.information)
        homeModel.action(HomeActionChangeInformation("New"))
        Assert.assertEquals("New", homeModel.data.value.information)
    }
}