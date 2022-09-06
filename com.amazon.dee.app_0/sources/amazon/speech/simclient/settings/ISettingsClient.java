package amazon.speech.simclient.settings;

import amazon.speech.simclient.common.InvocationStatus;
/* loaded from: classes.dex */
public interface ISettingsClient {
    InvocationStatus registerCallback(String str, SettingsStatusCallback settingsStatusCallback, boolean z);

    void teardown();

    boolean unregisterCallback(String str, SettingsStatusCallback settingsStatusCallback);
}
