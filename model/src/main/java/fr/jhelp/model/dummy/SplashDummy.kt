package fr.jhelp.model.dummy

import fr.jhelp.model.shared.SplashModel

class SplashDummy(private val onShow: () -> Unit = {}) : SplashModel
{
    override fun splashShow()
    {
        this.onShow()
    }
}
