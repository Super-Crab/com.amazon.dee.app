package org.webrtc;

import java.util.IdentityHashMap;
import java.util.LinkedList;
/* loaded from: classes5.dex */
public class VideoTrack extends MediaStreamTrack {
    private final LinkedList<VideoRenderer> renderers;
    private final IdentityHashMap<VideoSink, Long> sinks;

    public VideoTrack(long j) {
        super(j);
        this.renderers = new LinkedList<>();
        this.sinks = new IdentityHashMap<>();
    }

    private static native void nativeAddSink(long j, long j2);

    private static native void nativeFreeSink(long j);

    private static native void nativeRemoveSink(long j, long j2);

    private static native long nativeWrapSink(VideoSink videoSink);

    public void addRenderer(VideoRenderer videoRenderer) {
        this.renderers.add(videoRenderer);
        nativeAddSink(this.nativeTrack, videoRenderer.nativeVideoRenderer);
    }

    public void addSink(VideoSink videoSink) {
        long nativeWrapSink = nativeWrapSink(videoSink);
        this.sinks.put(videoSink, Long.valueOf(nativeWrapSink));
        nativeAddSink(this.nativeTrack, nativeWrapSink);
    }

    @Override // org.webrtc.MediaStreamTrack
    public void dispose() {
        while (!this.renderers.isEmpty()) {
            removeRenderer(this.renderers.getFirst());
        }
        for (Long l : this.sinks.values()) {
            long longValue = l.longValue();
            nativeRemoveSink(this.nativeTrack, longValue);
            nativeFreeSink(longValue);
        }
        this.sinks.clear();
        super.dispose();
    }

    public void removeRenderer(VideoRenderer videoRenderer) {
        if (!this.renderers.remove(videoRenderer)) {
            return;
        }
        nativeRemoveSink(this.nativeTrack, videoRenderer.nativeVideoRenderer);
        videoRenderer.dispose();
    }

    public void removeSink(VideoSink videoSink) {
        long longValue = this.sinks.remove(videoSink).longValue();
        if (longValue != 0) {
            nativeRemoveSink(this.nativeTrack, longValue);
            nativeFreeSink(longValue);
        }
    }
}
