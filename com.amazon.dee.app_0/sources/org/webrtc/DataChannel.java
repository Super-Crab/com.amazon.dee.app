package org.webrtc;

import java.nio.ByteBuffer;
/* loaded from: classes5.dex */
public class DataChannel {
    private final long nativeDataChannel;
    private long nativeObserver;

    /* loaded from: classes5.dex */
    public static class Buffer {
        public final boolean binary;
        public final ByteBuffer data;

        public Buffer(ByteBuffer byteBuffer, boolean z) {
            this.data = byteBuffer;
            this.binary = z;
        }
    }

    /* loaded from: classes5.dex */
    public interface Observer {
        void onBufferedAmountChange(long j);

        void onMessage(Buffer buffer);

        void onStateChange();
    }

    /* loaded from: classes5.dex */
    public enum State {
        CONNECTING,
        OPEN,
        CLOSING,
        CLOSED
    }

    public DataChannel(long j) {
        this.nativeDataChannel = j;
    }

    private native long registerObserverNative(Observer observer);

    private native boolean sendNative(byte[] bArr, boolean z);

    private native void unregisterObserverNative(long j);

    public native long bufferedAmount();

    public native void close();

    public native void dispose();

    public native int id();

    public native String label();

    public void registerObserver(Observer observer) {
        long j = this.nativeObserver;
        if (j != 0) {
            unregisterObserverNative(j);
        }
        this.nativeObserver = registerObserverNative(observer);
    }

    public boolean send(Buffer buffer) {
        byte[] bArr = new byte[buffer.data.remaining()];
        buffer.data.get(bArr);
        return sendNative(bArr, buffer.binary);
    }

    public native State state();

    public void unregisterObserver() {
        unregisterObserverNative(this.nativeObserver);
    }

    /* loaded from: classes5.dex */
    public static class Init {
        public int id;
        public int maxRetransmitTimeMs;
        public int maxRetransmits;
        public boolean negotiated;
        public boolean ordered;
        public String protocol;

        public Init() {
            this.ordered = true;
            this.maxRetransmitTimeMs = -1;
            this.maxRetransmits = -1;
            this.protocol = "";
            this.negotiated = false;
            this.id = -1;
        }

        private Init(boolean z, int i, int i2, String str, boolean z2, int i3) {
            this.ordered = true;
            this.maxRetransmitTimeMs = -1;
            this.maxRetransmits = -1;
            this.protocol = "";
            this.negotiated = false;
            this.id = -1;
            this.ordered = z;
            this.maxRetransmitTimeMs = i;
            this.maxRetransmits = i2;
            this.protocol = str;
            this.negotiated = z2;
            this.id = i3;
        }
    }
}
