package com.amazon.rtcsc.android.typedapi.payloads.directives;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.rtcsc.android.typedapi.payloads.Payload;
import com.amazon.rtcsc.android.typedapi.types.IceServer;
import com.amazon.rtcsc.android.typedapi.types.MediaStreams;
import java.util.List;
import lombok.NonNull;
/* loaded from: classes13.dex */
public class UpdateSessionPayload extends Payload {
    private final List<IceServer> iceServers;
    private final MediaStreams mediaStreams;

    public UpdateSessionPayload(@NonNull String str, @NonNull String str2, @NonNull MediaStreams mediaStreams, @NonNull List<IceServer> list) {
        if (str != null) {
            if (str2 == null) {
                throw new NullPointerException("sessionDomain");
            }
            if (mediaStreams == null) {
                throw new NullPointerException("mediaStreams");
            }
            if (list != null) {
                this.iceServers = list;
                this.mediaStreams = mediaStreams;
                this.sessionId = str;
                this.sessionDomain = str2;
                return;
            }
            throw new NullPointerException("iceServers");
        }
        throw new NullPointerException(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY);
    }

    public UpdateSessionPayload(@NonNull String str, @NonNull String str2) {
        if (str != null) {
            if (str2 != null) {
                this.iceServers = null;
                this.mediaStreams = null;
                this.sessionId = str;
                this.sessionDomain = str2;
                return;
            }
            throw new NullPointerException("sessionDomain");
        }
        throw new NullPointerException(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY);
    }
}
