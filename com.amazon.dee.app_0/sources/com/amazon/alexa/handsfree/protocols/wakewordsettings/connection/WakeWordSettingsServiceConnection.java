package com.amazon.alexa.handsfree.protocols.wakewordsettings.connection;

import android.content.ServiceConnection;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import java.util.List;
/* loaded from: classes8.dex */
public abstract class WakeWordSettingsServiceConnection implements ServiceConnection {
    public abstract void endConnection();

    public abstract String getLocale() throws Exception;

    public abstract List<String> getSupportedLocales() throws Exception;

    public abstract Integer getWakeWordConfidenceThreshold() throws Exception;

    public abstract boolean isWakeWordRecognitionEnabled() throws Exception;

    public abstract void setLocale(@NonNull String str, @NonNull ResultCallback resultCallback) throws Exception;

    public abstract void setWakeWordConfidenceThreshold(@NonNull Integer num, @NonNull ResultCallback resultCallback) throws Exception;

    public abstract void setWakeWordRecognitionEnabled(boolean z, @NonNull ResultCallback resultCallback) throws Exception;

    public abstract void startConnection();
}
