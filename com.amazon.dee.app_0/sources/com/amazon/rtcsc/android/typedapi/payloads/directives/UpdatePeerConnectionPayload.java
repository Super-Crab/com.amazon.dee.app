package com.amazon.rtcsc.android.typedapi.payloads.directives;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.rtcsc.android.typedapi.payloads.Payload;
import com.amazon.rtcsc.android.typedapi.types.WebRTCHandshake;
import com.amazon.tarazed.core.webrtc.signals.WebRTCSignalTypes;
import lombok.NonNull;
/* loaded from: classes13.dex */
public class UpdatePeerConnectionPayload extends Payload {
    private final WebRTCHandshake answer;

    public UpdatePeerConnectionPayload(@NonNull String str, @NonNull String str2, @NonNull WebRTCHandshake webRTCHandshake) {
        if (str != null) {
            if (str2 == null) {
                throw new NullPointerException("sessionDomain");
            }
            if (webRTCHandshake != null) {
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
