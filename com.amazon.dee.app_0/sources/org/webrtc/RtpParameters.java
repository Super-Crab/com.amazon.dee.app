package org.webrtc;

import java.util.LinkedList;
import org.webrtc.MediaStreamTrack;
/* loaded from: classes5.dex */
public class RtpParameters {
    public final LinkedList<Encoding> encodings = new LinkedList<>();
    public final LinkedList<Codec> codecs = new LinkedList<>();

    /* loaded from: classes5.dex */
    public static class Codec {
        public Integer clockRate;
        MediaStreamTrack.MediaType kind;
        public String name;
        public Integer numChannels;
        public int payloadType;
    }

    /* loaded from: classes5.dex */
    public static class Encoding {
        public boolean active = true;
        public Integer maxBitrateBps;
        public Long ssrc;
    }
}
