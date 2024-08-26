package fr.jhelp.tool.providers

import org.junit.Assert
import org.junit.Test

class ProvidedTests
{
    private val defaultElement: Element by provided<Element>()
    private val firstQualifierElement: Element by provided<Element>("first")
    private val secondQualifierElement: Element by provided<Element>("second")

    @Test
    fun providedSingleTest()
    {
        Assert.assertFalse(isProvided<Element>())
        Assert.assertFalse(isProvided<Element>("first"))
        Assert.assertFalse(isProvided<Element>("second"))
        provideSingle { Element("default") }
        provideSingle("first") { Element("first") }
        provideSingle("second") { Element("second") }
        Assert.assertTrue(isProvided<Element>())
        Assert.assertTrue(isProvided<Element>("first"))
        Assert.assertTrue(isProvided<Element>("second"))

        val defaultElement = this.defaultElement
        val firstQualifierElement = this.firstQualifierElement
        val secondQualifierElement = this.secondQualifierElement
        Assert.assertEquals("default", defaultElement.information)
        Assert.assertEquals("first", firstQualifierElement.information)
        Assert.assertEquals("second", secondQualifierElement.information)

        Assert.assertEquals(this.defaultElement.id, defaultElement.id)
        Assert.assertEquals(this.firstQualifierElement.id, firstQualifierElement.id)
        Assert.assertEquals(this.secondQualifierElement.id, secondQualifierElement.id)

        forget<Element>()
        forget<Element>("first")
        forget<Element>("second")
        Assert.assertFalse(isProvided<Element>())
        Assert.assertFalse(isProvided<Element>("first"))
        Assert.assertFalse(isProvided<Element>("second"))
    }

    @Test
    fun providedMultipleTest()
    {
        Assert.assertFalse(isProvided<Element>())
        Assert.assertFalse(isProvided<Element>("first"))
        Assert.assertFalse(isProvided<Element>("second"))
        provideMultiple { Element("default") }
        provideMultiple("first") { Element("first") }
        provideMultiple("second") { Element("second") }
        Assert.assertTrue(isProvided<Element>())
        Assert.assertTrue(isProvided<Element>("first"))
        Assert.assertTrue(isProvided<Element>("second"))

        val defaultElement = this.defaultElement
        val firstQualifierElement = this.firstQualifierElement
        val secondQualifierElement = this.secondQualifierElement
        Assert.assertEquals("default", defaultElement.information)
        Assert.assertEquals("first", firstQualifierElement.information)
        Assert.assertEquals("second", secondQualifierElement.information)

        Assert.assertNotEquals(this.defaultElement.id, defaultElement.id)
        Assert.assertNotEquals(this.firstQualifierElement.id, firstQualifierElement.id)
        Assert.assertNotEquals(this.secondQualifierElement.id, secondQualifierElement.id)

        forget<Element>()
        forget<Element>("first")
        forget<Element>("second")
        Assert.assertFalse(isProvided<Element>())
        Assert.assertFalse(isProvided<Element>("first"))
        Assert.assertFalse(isProvided<Element>("second"))
    }
}