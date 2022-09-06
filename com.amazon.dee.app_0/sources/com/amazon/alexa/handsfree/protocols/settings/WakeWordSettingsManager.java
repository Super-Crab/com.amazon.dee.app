package com.amazon.alexa.handsfree.protocols.settings;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import java.util.List;
/* loaded from: classes8.dex */
public abstract class WakeWordSettingsManager {
    public abstract void checkHandsFreeState(@NonNull ResultCallback<Boolean> resultCallback);

    public abstract void checkVoiceAppCurrentLocale(@NonNull ResultCallback<String> resultCallback);

    public abstract void checkVoiceAppSupportedLocales(@NonNull ResultCallback<List<String>> resultCallback);

    public abstract void setHandsFreeState(boolean z, @NonNull ResponseCallback responseCallback);

    public abstract void setHandsFreeState(boolean z, @NonNull ResponseCallback responseCallback, String str);

    public abstract void setVoiceAppLocale(@NonNull String str, @NonNull ResponseCallback responseCallback);
}
