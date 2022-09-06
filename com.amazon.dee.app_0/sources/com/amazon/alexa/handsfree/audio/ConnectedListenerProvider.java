package com.amazon.alexa.handsfree.audio;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.handsfree.audio.api.LocaleApi;
import com.amazon.alexa.handsfree.audio.api.OnConnectedListener;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class ConnectedListenerProvider {
    private static final String TAG = "ConnectedListenerProvider";
    private final LocaleApi mLocaleApi;

    public ConnectedListenerProvider() {
        this(new LocaleApi());
    }

    public OnConnectedListener getConnectedListener(@NonNull final AlexaServicesConnection alexaServicesConnection) {
        return new OnConnectedListener() { // from class: com.amazon.alexa.handsfree.audio.-$$Lambda$ConnectedListenerProvider$lcnJFN8mQyOwaGqXr90vFdREJ1g
            @Override // com.amazon.alexa.handsfree.audio.api.OnConnectedListener
            public final void onConnected() {
                ConnectedListenerProvider.this.lambda$getConnectedListener$0$ConnectedListenerProvider(alexaServicesConnection);
            }
        };
    }

    public /* synthetic */ void lambda$getConnectedListener$0$ConnectedListenerProvider(AlexaServicesConnection alexaServicesConnection) {
        Log.i(TAG, "Alexa services connection is connected.");
        this.mLocaleApi.onConnected(alexaServicesConnection);
    }

    @VisibleForTesting
    ConnectedListenerProvider(@NonNull LocaleApi localeApi) {
        this.mLocaleApi = localeApi;
    }
}
