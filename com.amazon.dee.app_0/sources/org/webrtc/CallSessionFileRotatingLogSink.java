package org.webrtc;

import org.webrtc.Logging;
/* loaded from: classes5.dex */
public class CallSessionFileRotatingLogSink {
    private long nativeSink;

    static {
        System.loadLibrary("jingle_peerconnection_so");
    }

    public CallSessionFileRotatingLogSink(String str, int i, Logging.Severity severity) {
        this.nativeSink = nativeAddSink(str, i, severity.ordinal());
    }

    public static byte[] getLogData(String str) {
        return nativeGetLogData(str);
    }

    private static native long nativeAddSink(String str, int i, int i2);

    private static native void nativeDeleteSink(long j);

    private static native byte[] nativeGetLogData(String str);

    public void dispose() {
        long j = this.nativeSink;
        if (j != 0) {
            nativeDeleteSink(j);
            this.nativeSink = 0L;
        }
    }
}
