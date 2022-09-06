package com.amazon.alexa.voice.handsfree.settings.locale;

import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaArtifactDownloadListener;
import com.amazon.alexa.api.AlexaLocalesListener;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServicesApis;
import com.amazon.alexa.api.compat.AlexaSupportedLocalesListener;
import java.util.List;
import java.util.Locale;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class AlexaServicesLocaleAPI {
    /* JADX INFO: Access modifiers changed from: package-private */
    public void deregisterArtifactDownloadListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
        AlexaServicesApis.Locale.deregisterArtifactDownloadListener(alexaServicesConnection, alexaArtifactDownloadListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deregisterLocalesListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaLocalesListener alexaLocalesListener) {
        AlexaServicesApis.Locale.deregisterLocalesListener(alexaServicesConnection, alexaLocalesListener);
    }

    void deregisterSupportedLocalesListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        AlexaServicesApis.Locale.deregisterSupportedLocalesListener(alexaServicesConnection, alexaSupportedLocalesListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerArtifactDownloadListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
        AlexaServicesApis.Locale.registerArtifactDownloadListener(alexaServicesConnection, alexaArtifactDownloadListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerLocalesListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaLocalesListener alexaLocalesListener) {
        AlexaServicesApis.Locale.registerLocalesListener(alexaServicesConnection, alexaLocalesListener);
    }

    void registerSupportedLocalesListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        AlexaServicesApis.Locale.registerSupportedLocalesListener(alexaServicesConnection, alexaSupportedLocalesListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLocales(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull List<Locale> list, @NonNull AlexaApiCallbacks alexaApiCallbacks) {
        AlexaServicesApis.Locale.setLocales(alexaServicesConnection, list, alexaApiCallbacks);
    }
}
