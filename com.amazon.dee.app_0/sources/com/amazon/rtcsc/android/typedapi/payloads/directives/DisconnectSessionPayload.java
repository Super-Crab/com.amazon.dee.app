package com.amazon.rtcsc.android.typedapi.payloads.directives;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.rtcsc.android.typedapi.payloads.Payload;
import lombok.NonNull;
/* loaded from: classes13.dex */
public class DisconnectSessionPayload extends Payload {
    public DisconnectSessionPayload(@NonNull String str, @NonNull String str2) {
        if (str != null) {
            if (str2 != null) {
                this.sessionDomain = str2;
                this.sessionId = str;
                return;
            }
            throw new NullPointerException("sessionDomain");
        }
        throw new NullPointerException(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY);
    }
}
