package com.amazon.alexa.handsfree.settings.client.settings;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import java.util.List;
/* loaded from: classes8.dex */
public class AlexaAudioProviderSettingFactory {
    @NonNull
    public AlexaAudioProviderSetting getConfidenceLevelUpdateSetting(@NonNull Context context, @NonNull ResultCallback<Void> resultCallback, int i) {
        return new ConfidenceLevelUpdateSetting(context, resultCallback, i);
    }

    @NonNull
    public AlexaAudioProviderSetting getLocaleCheckSetting(@NonNull Context context, @NonNull ResultCallback<String> resultCallback) {
        return new LocaleCheckSetting(context, resultCallback);
    }

    @NonNull
    public AlexaAudioProviderSetting getLocaleSupportedSetting(@NonNull Context context, @NonNull ResultCallback<List<String>> resultCallback) {
        return new LocaleSupportedSetting(context, resultCallback);
    }

    @NonNull
    public AlexaAudioProviderSetting getLocaleUpdateSetting(@NonNull Context context, @NonNull ResultCallback<Void> resultCallback, @NonNull String str) {
        return new LocaleUpdateSetting(context, resultCallback, str);
    }

    @NonNull
    public AlexaAudioProviderSetting getWakeWordRecognitionCheckSetting(@NonNull Context context, @NonNull ResultCallback<Boolean> resultCallback) {
        return new WakeWordRecognitionCheckSetting(context, resultCallback);
    }

    @NonNull
    public AlexaAudioProviderSetting getWakeWordRecognitionUpdateSetting(@NonNull Context context, @NonNull ResultCallback<Void> resultCallback, boolean z) {
        return new WakeWordRecognitionUpdateSetting(context, resultCallback, z);
    }
}
