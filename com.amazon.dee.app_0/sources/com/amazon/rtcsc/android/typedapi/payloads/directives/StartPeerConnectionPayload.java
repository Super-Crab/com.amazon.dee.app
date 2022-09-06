package com.amazon.rtcsc.android.typedapi.payloads.directives;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.rtcsc.android.typedapi.payloads.Payload;
import com.amazon.rtcsc.android.typedapi.types.WebRTCHandshake;
import com.amazon.tarazed.core.webrtc.signals.WebRTCSignalTypes;
import lombok.NonNull;
/* loaded from: classes13.dex */
public class StartPeerConnectionPayload extends Payload {
    private final WebRTCHandshake answer;
    private final Boolean isProvisional;

    public StartPeerConnectionPayload(@NonNull String str, @NonNull String str2, @NonNull Boolean bool, @NonNull WebRTCHandshake webRTCHandshake) {
        if (str != null) {
            if (str2 == null) {
                throw new NullPointerException("sessionDomain");
            }
            if (bool == null) {
                throw new NullPointerException("isProvisional");
            }
            if (webRTCHandshake != null) {
                this.isProvisional = bool;
                this.answer = webRTCHandshake;
                this.sessionId = str;
                this.sessionDomain = str2;
                return;
            }
            throw new NullPointerException(WebRTCSignalTypes.ANSWER);
        }
        throw new NullPointerException(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY);
    }
}
