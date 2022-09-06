package com.amazon.alexa.devices.oobe;

import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.notification.INotificationComponent;
import com.amazon.alexa.devices.settings.SettingsCallback;
/* loaded from: classes6.dex */
public interface OOBEComponent extends INotificationComponent {
    public static final String DEFAULT_FLOW = "PRIMARY_FLOW";

    void getOOBEFlowState(SettingsCallback<OOBEFlowState> settingsCallback) throws AlexaException;

    void getOOBEFlowState(String str, SettingsCallback<OOBEFlowState> settingsCallback) throws AlexaException;

    void getOOBEFlowsState(SettingsCallback<OOBEFlowState[]> settingsCallback) throws AlexaException;

    void getOOBEState(SettingsCallback<OOBEState> settingsCallback) throws AlexaException;

    void launchAlexaOOBE(SettingsCallback<Boolean> settingsCallback) throws AlexaException;

    void launchAlexaOOBE(String str, SettingsCallback<Boolean> settingsCallback) throws AlexaException;

    void resetOOBE(SettingsCallback<Boolean> settingsCallback) throws AlexaException;
}
