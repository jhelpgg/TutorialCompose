package fr.jhelp.tool.tasks.observable

import fr.jhelp.tool.tasks.ImmediateDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Test

class ObservableTests
{
    @Test
    fun observable()
    {
        Dispatchers.setMain(ImmediateDispatcher)
        val observableSource = ObservableSource(0)
        var value = 0
        val observable = observableSource.observable
        val observer = observable.observeBy { integer -> value = integer }

        observableSource.value = 10
        Thread.sleep(16)
        Assert.assertEquals(10, value)
        Assert.assertEquals(10, observable.composeState.value)

        observableSource.value = 10
        Thread.sleep(16)
        Assert.assertEquals(10, value)
        Assert.assertEquals(10, observable.composeState.value)

        observableSource.value = 20
        Thread.sleep(16)
        Assert.assertEquals(20, value)
        Assert.assertEquals(20, observable.composeState.value)

        observer.stopObserve()

        observableSource.value = 30
        Thread.sleep(16)
        Assert.assertEquals(20, value)
        Assert.assertEquals(30, observable.composeState.value)
    }

    @Test
    fun whenConditionDoTest() {
        Dispatchers.setMain(ImmediateDispatcher)
        val observableSource = ObservableSource(0)
        var value = 0
        val observable = observableSource.observable
        observable.whenConditionDo({ integer -> integer % 11 == 0 }) { integer -> value = integer }

        observableSource.value = 5
        Thread.sleep(16)
        Assert.assertEquals(0, value)
        Assert.assertEquals(5, observable.composeState.value)

        observableSource.value = 11
        Thread.sleep(16)
        Assert.assertEquals(11, value)
        Assert.assertEquals(11, observable.composeState.value)

        observableSource.value = 20
        Thread.sleep(16)
        Assert.assertEquals(11, value)
        Assert.assertEquals(20, observable.composeState.value)

        observableSource.value = 22
        Thread.sleep(16)
        Assert.assertEquals(11, value)
        Assert.assertEquals(22, observable.composeState.value)

        observable.whenConditionDo({ integer -> integer % 11 == 0 }) { integer -> value = integer }
        Thread.sleep(16)
        Assert.assertEquals(22, value)
        Assert.assertEquals(22, observable.composeState.value)
    }
}