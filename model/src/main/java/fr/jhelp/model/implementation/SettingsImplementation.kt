package fr.jhelp.model.implementation

import fr.jhelp.model.action.settings.SettingsAction
import fr.jhelp.model.action.settings.SettingsActionChangeEmail
import fr.jhelp.model.action.settings.SettingsActionChangeTheme
import fr.jhelp.model.data.SettingsData
import fr.jhelp.model.shared.SettingsModel

internal class SettingsImplementation :
        GenericImplementation<SettingsData, SettingsAction>(SettingsData(email = "", theme = 0)),
        SettingsModel
{
    override fun action(action: SettingsAction)
    {
        when (action)
        {
            is SettingsActionChangeEmail -> this.update { this.copy(email = action.email) }

            is SettingsActionChangeTheme -> this.update { this.copy(theme = action.theme) }
        }
    }
}
