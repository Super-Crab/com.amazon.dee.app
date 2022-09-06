package org.webrtc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.LinkedList;
/* loaded from: classes5.dex */
public class MediaStream {
    final long nativeStream;
    public final LinkedList<AudioTrack> audioTracks = new LinkedList<>();
    public final LinkedList<VideoTrack> videoTracks = new LinkedList<>();
    public final LinkedList<VideoTrack> preservedVideoTracks = new LinkedList<>();

    public MediaStream(long j) {
        this.nativeStream = j;
    }

    private static native void free(long j);

    private static native boolean nativeAddAudioTrack(long j, long j2);

    private static native boolean nativeAddVideoTrack(long j, long j2);

    private static native String nativeLabel(long j);

    private static native boolean nativeRemoveAudioTrack(long j, long j2);

    private static native boolean nativeRemoveVideoTrack(long j, long j2);

    public boolean addPreservedTrack(VideoTrack videoTrack) {
        if (nativeAddVideoTrack(this.nativeStream, videoTrack.nativeTrack)) {
            this.preservedVideoTracks.add(videoTrack);
            return true;
        }
        return false;
    }

    public boolean addTrack(AudioTrack audioTrack) {
        if (nativeAddAudioTrack(this.nativeStream, audioTrack.nativeTrack)) {
            this.audioTracks.add(audioTrack);
            return true;
        }
        return false;
    }

    public void dispose() {
        while (!this.audioTracks.isEmpty()) {
            AudioTrack first = this.audioTracks.getFirst();
            removeTrack(first);
            first.dispose();
        }
        while (!this.videoTracks.isEmpty()) {
            VideoTrack first2 = this.videoTracks.getFirst();
            removeTrack(first2);
            first2.dispose();
        }
        while (!this.preservedVideoTracks.isEmpty()) {
            removeTrack(this.preservedVideoTracks.getFirst());
        }
        free(this.nativeStream);
    }

    public String label() {
        return nativeLabel(this.nativeStream);
    }

    public boolean removeTrack(AudioTrack audioTrack) {
        this.audioTracks.remove(audioTrack);
        return nativeRemoveAudioTrack(this.nativeStream, audioTrack.nativeTrack);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
        outline107.append(label());
        outline107.append(":A=");
        outline107.append(this.audioTracks.size());
        outline107.append(":V=");
        outline107.append(this.videoTracks.size());
        outline107.append("]");
        return outline107.toString();
    }

    public boolean addTrack(VideoTrack videoTrack) {
        if (nativeAddVideoTrack(this.nativeStream, videoTrack.nativeTrack)) {
            this.videoTracks.add(videoTrack);
            return true;
        }
        return false;
    }

    public boolean removeTrack(VideoTrack videoTrack) {
        this.videoTracks.remove(videoTrack);
        this.preservedVideoTracks.remove(videoTrack);
        return nativeRemoveVideoTrack(this.nativeStream, videoTrack.nativeTrack);
    }
}
