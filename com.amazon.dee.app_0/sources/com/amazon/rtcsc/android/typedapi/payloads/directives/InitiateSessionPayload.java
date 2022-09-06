package com.amazon.rtcsc.android.typedapi.payloads.directives;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.rtcsc.android.typedapi.constants.KeyExchange;
import com.amazon.rtcsc.android.typedapi.payloads.Payload;
import com.amazon.rtcsc.android.typedapi.types.IceServer;
import com.amazon.rtcsc.android.typedapi.types.MediaStreams;
import java.util.List;
import lombok.NonNull;
/* loaded from: classes13.dex */
public class InitiateSessionPayload extends Payload {
    private final List<IceServer> iceServers;
    private final String keyExchange;
    private final MediaStreams mediaStreams;
    private final Boolean trickleICE;

    public InitiateSessionPayload(@NonNull String str, @NonNull String str2, @NonNull KeyExchange keyExchange, @NonNull Boolean bool, @NonNull MediaStreams mediaStreams, @NonNull List<IceServer> list) {
        if (str != null) {
            if (str2 == null) {
                throw new NullPointerException("sessionDomain");
            }
            if (keyExchange == null) {
                throw new NullPointerException("keyExchange");
            }
            if (bool == null) {
                throw new NullPointerException("trickleICE");
            }
            if (mediaStreams == null) {
                throw new NullPointerException("mediaStreams");
            }
            if (list != null) {
                this.iceServers = list;
                this.keyExchange = keyExchange.name();
                this.mediaStreams = mediaStreams;
                this.trickleICE = bool;
                this.sessionId = str;
                this.sessionDomain = str2;
                return;
            }
            throw new NullPointerException("iceServers");
        }
        throw new NullPointerException(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY);
    }

    public InitiateSessionPayload(@NonNull String str, @NonNull String str2, @NonNull KeyExchange keyExchange, @NonNull Boolean bool, @NonNull MediaStreams mediaStreams) {
        if (str != null) {
            if (str2 == null) {
                throw new NullPointerException("sessionDomain");
            }
            if (keyExchange == null) {
                throw new NullPointerException("keyExchange");
            }
            if (bool == null) {
                throw new NullPointerException("trickleICE");
            }
            if (mediaStreams != null) {
                this.iceServers = null;
                this.keyExchange = keyExchange.name();
                this.mediaStreams = mediaStreams;
                this.trickleICE = bool;
                this.sessionId = str;
                this.sessionDomain = str2;
                return;
            }
            throw new NullPointerException("mediaStreams");
        }
        throw new NullPointerException(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY);
    }
}
