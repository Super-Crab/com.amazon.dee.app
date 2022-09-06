package com.amazon.alexa.handsfree.audio;

import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.handsfree.audio.api.OnReleaseListener;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class ReleaseListenerProvider {
    private static final String TAG = "ReleaseListenerProvider";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getAudioReleaser$0(AlexaServicesConnection alexaServicesConnection, AlexaConnectionListener alexaConnectionListener) {
        Log.i(TAG, "Alexa services connection is being released.");
        alexaServicesConnection.deregisterListener(alexaConnectionListener);
        alexaServicesConnection.release();
    }

    public OnReleaseListener getAudioReleaser(@NonNull final AlexaConnectionListener alexaConnectionListener, @NonNull final AlexaServicesConnection alexaServicesConnection) {
        return new OnReleaseListener() { // from class: com.amazon.alexa.handsfree.audio.-$$Lambda$ReleaseListenerProvider$3ApSc9XzHxZNLzKzHCOtEPf8GSU
            @Override // com.amazon.alexa.handsfree.audio.api.OnReleaseListener
            public final void onRelease() {
                ReleaseListenerProvider.lambda$getAudioReleaser$0(AlexaServicesConnection.this, alexaConnectionListener);
            }
        };
    }
}
