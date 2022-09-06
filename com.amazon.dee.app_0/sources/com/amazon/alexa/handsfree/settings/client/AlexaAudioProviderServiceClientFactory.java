package com.amazon.alexa.handsfree.settings.client;

import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public class AlexaAudioProviderServiceClientFactory {
    @NonNull
    public AlexaAudioProviderServiceClient getAlexaAudioProviderServiceClient(@NonNull Context context) {
        return new AlexaAudioProviderServiceClient(context);
    }
}
