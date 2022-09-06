package com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection;
import java.util.List;
/* loaded from: classes8.dex */
public class WakeWordSettingsFactory {
    @NonNull
    public WakeWordProviderSettings getLocaleCheckSettingV2(@NonNull ResultCallback<String> resultCallback, @NonNull WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
        return new LocaleCheckSettingV2(resultCallback, wakeWordSettingsServiceConnection);
    }

    @NonNull
    public WakeWordProviderSettings getLocaleSupportedSettingV2(@NonNull ResultCallback<List<String>> resultCallback, @NonNull WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
        return new LocaleSupportedSettingV2(resultCallback, wakeWordSettingsServiceConnection);
    }

    @NonNull
    public WakeWordProviderSettings getLocaleUpdateSettingV2(@NonNull String str, @NonNull ResultCallback<Void> resultCallback, @NonNull WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
        return new LocaleUpdateSettingV2(str, resultCallback, wakeWordSettingsServiceConnection);
    }

    @NonNull
    public WakeWordProviderSettings getWakeWordRecognitionCheckSettingV2(@NonNull ResultCallback<Boolean> resultCallback, @NonNull WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
        return new WakeWordRecognitionCheckSettingV2(resultCallback, wakeWordSettingsServiceConnection);
    }

    @NonNull
    public WakeWordProviderSettings getWakeWordRecognitionUpdateSettingV2(boolean z, @NonNull ResultCallback<Void> resultCallback, @NonNull WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
        return new WakeWordRecognitionUpdateSettingV2(z, resultCallback, wakeWordSettingsServiceConnection);
    }
}
