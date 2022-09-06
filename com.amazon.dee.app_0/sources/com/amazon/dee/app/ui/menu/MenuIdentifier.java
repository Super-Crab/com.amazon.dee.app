package com.amazon.dee.app.ui.menu;

import androidx.annotation.NonNull;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
/* loaded from: classes12.dex */
public enum MenuIdentifier {
    ENTERTAINMENT("entertainment"),
    LISTS(RouteName.LISTS),
    TIMERS_AND_ALARMS("timersAndAlarms"),
    CONTACTS("contacts"),
    SKILLS("skills"),
    BLUEPRINTS("blueprints"),
    ROUTINES("routines"),
    SMART_HOME("smartHome"),
    HELP_AND_FEEDBACK("helpAndFeedback"),
    THINGS_TO_TRY("thingsToTry"),
    DEVICES("devices"),
    SETTINGS("settings"),
    ACTIVITY(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME),
    ENTERTAINMENT_CHANNELS("entertainment_channels"),
    LISTS_CHANNELS("lists_channels"),
    LISTS_NOTES("lists_notes"),
    TIMERS_AND_ALARMS_CHANNELS("timersAndAlarms_channels"),
    CONTACTS_CHANNELS("contacts_channels"),
    SKILLS_CHANNELS("skills_channels"),
    BLUEPRINTS_CHANNELS("blueprints_channels"),
    ROUTINES_CHANNELS("routines_channels"),
    SMART_HOME_CHANNELS("smartHome_channels"),
    HELP_AND_FEEDBACK_CHANNELS("helpAndFeedback_channels"),
    THINGS_TO_TRY_CHANNELS("thingsToTry_channels"),
    ADDDEVICE_CHANNELS("addDevice_channels"),
    DEVICES_CHANNELS("devices_channels"),
    SETTINGS_CHANNELS("settings_channels"),
    ACTIVITY_CHANNELS("activity_channels");
    
    private final String value;

    MenuIdentifier(String str) {
        this.value = str;
    }

    public static MenuIdentifier named(@NonNull String str) {
        MenuIdentifier[] values;
        for (MenuIdentifier menuIdentifier : values()) {
            if (menuIdentifier.toString().equals(str)) {
                return menuIdentifier;
            }
        }
        return null;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
