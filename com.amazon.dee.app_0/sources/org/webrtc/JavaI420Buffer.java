package org.webrtc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import org.webrtc.VideoFrame;
/* loaded from: classes5.dex */
public class JavaI420Buffer implements VideoFrame.I420Buffer {
    private final ByteBuffer dataU;
    private final ByteBuffer dataV;
    private final ByteBuffer dataY;
    private final int height;
    private final Runnable releaseCallback;
    private final int strideU;
    private final int strideV;
    private final int strideY;
    private final int width;
    private final Object refCountLock = new Object();
    private int refCount = 1;

    private JavaI420Buffer(int i, int i2, ByteBuffer byteBuffer, int i3, ByteBuffer byteBuffer2, int i4, ByteBuffer byteBuffer3, int i5, Runnable runnable) {
        this.width = i;
        this.height = i2;
        this.dataY = byteBuffer;
        this.dataU = byteBuffer2;
        this.dataV = byteBuffer3;
        this.strideY = i3;
        this.strideU = i4;
        this.strideV = i5;
        this.releaseCallback = runnable;
    }

    public static JavaI420Buffer allocate(int i, int i2) {
        int i3 = (i2 + 1) / 2;
        int i4 = (i + 1) / 2;
        int i5 = i * i2;
        int i6 = i5 + 0;
        int i7 = i4 * i3;
        int i8 = i6 + i7;
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect((i4 * 2 * i3) + i5);
        allocateDirect.position(0);
        allocateDirect.limit(i6);
        ByteBuffer slice = allocateDirect.slice();
        allocateDirect.position(i6);
        allocateDirect.limit(i8);
        ByteBuffer slice2 = allocateDirect.slice();
        allocateDirect.position(i8);
        allocateDirect.limit(i8 + i7);
        return new JavaI420Buffer(i, i2, slice, i, slice2, i4, allocateDirect.slice(), i4, null);
    }

    public static JavaI420Buffer wrap(int i, int i2, ByteBuffer byteBuffer, int i3, ByteBuffer byteBuffer2, int i4, ByteBuffer byteBuffer3, int i5, Runnable runnable) {
        if (byteBuffer != null && byteBuffer2 != null && byteBuffer3 != null) {
            if (byteBuffer.isDirect() && byteBuffer2.isDirect() && byteBuffer3.isDirect()) {
                ByteBuffer slice = byteBuffer.slice();
                ByteBuffer slice2 = byteBuffer2.slice();
                ByteBuffer slice3 = byteBuffer3.slice();
                int i6 = (i2 + 1) / 2;
                int i7 = i3 * i2;
                int i8 = i4 * i6;
                int i9 = i6 * i5;
                if (slice.capacity() >= i7) {
                    if (slice2.capacity() >= i8) {
                        if (slice3.capacity() >= i9) {
                            return new JavaI420Buffer(i, i2, slice, i3, slice2, i4, slice3, i5, runnable);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("V-buffer must be at least ", i9, " bytes."));
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("U-buffer must be at least ", i8, " bytes."));
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Y-buffer must be at least ", i7, " bytes."));
            }
            throw new IllegalArgumentException("Data buffers must be direct byte buffers.");
        }
        throw new IllegalArgumentException("Data buffers cannot be null.");
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public VideoFrame.Buffer cropAndScale(int i, int i2, int i3, int i4, int i5, int i6) {
        return VideoFrame.cropAndScaleI420(this, i, i2, i3, i4, i5, i6);
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataU() {
        return this.dataU.slice();
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataV() {
        return this.dataV.slice();
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataY() {
        return this.dataY.slice();
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public int getHeight() {
        return this.height;
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public int getStrideU() {
        return this.strideU;
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public int getStrideV() {
        return this.strideV;
    }

    @Override // org.webrtc.VideoFrame.I420Buffer
    public int getStrideY() {
        return this.strideY;
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public int getWidth() {
        return this.width;
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public void release() {
        synchronized (this.refCountLock) {
            int i = this.refCount - 1;
            this.refCount = i;
            if (i == 0 && this.releaseCallback != null) {
                this.releaseCallback.run();
            }
        }
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public void retain() {
        synchronized (this.refCountLock) {
            this.refCount++;
        }
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public VideoFrame.I420Buffer toI420() {
        retain();
        return this;
    }
}
