package org.webrtc;

import android.graphics.Matrix;
import java.nio.ByteBuffer;
import org.webrtc.VideoFrame;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class TextureBufferImpl implements VideoFrame.TextureBuffer {
    private final int height;
    private final int id;
    private final Runnable releaseCallback;
    private final SurfaceTextureHelper surfaceTextureHelper;
    private final Matrix transformMatrix;
    private final VideoFrame.TextureBuffer.Type type;
    private final int width;
    private final Object refCountLock = new Object();
    private int refCount = 1;

    public TextureBufferImpl(int i, int i2, VideoFrame.TextureBuffer.Type type, int i3, Matrix matrix, SurfaceTextureHelper surfaceTextureHelper, Runnable runnable) {
        this.width = i;
        this.height = i2;
        this.type = type;
        this.id = i3;
        this.transformMatrix = matrix;
        this.surfaceTextureHelper = surfaceTextureHelper;
        this.releaseCallback = runnable;
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public VideoFrame.Buffer cropAndScale(int i, int i2, int i3, int i4, int i5, int i6) {
        retain();
        Matrix matrix = new Matrix(this.transformMatrix);
        matrix.postScale(i3 / this.width, i4 / this.height);
        matrix.postTranslate(i / this.width, i2 / this.height);
        return new TextureBufferImpl(i5, i6, this.type, this.id, matrix, this.surfaceTextureHelper, new Runnable() { // from class: org.webrtc.TextureBufferImpl.1
            @Override // java.lang.Runnable
            public void run() {
                TextureBufferImpl.this.release();
            }
        });
    }

    @Override // org.webrtc.VideoFrame.Buffer
    public int getHeight() {
        return this.height;
    }

    @Override // org.webrtc.VideoFrame.TextureBuffer
    public int getTextureId() {
        return this.id;
    }

    @Override // org.webrtc.VideoFrame.TextureBuffer
    public Matrix getTransformMatrix() {
        return this.transformMatrix;
    }

    @Override // org.webrtc.VideoFrame.TextureBuffer
    public VideoFrame.TextureBuffer.Type getType() {
        return this.type;
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
        int i = ((this.width + 7) / 8) * 8;
        int i2 = this.height;
        int i3 = (i2 + 1) / 2;
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect((i2 + i3 + 1) * i);
        VideoFrame.TextureBuffer.Type type = this.type;
        if (type == VideoFrame.TextureBuffer.Type.OES) {
            this.surfaceTextureHelper.textureToYUV(allocateDirect, this.width, this.height, i, this.id, RendererCommon.convertMatrixFromAndroidGraphicsMatrix(this.transformMatrix));
        } else if (type == VideoFrame.TextureBuffer.Type.RGB) {
            this.surfaceTextureHelper.RGBToYUV(allocateDirect, this.width, this.height, i, this.id, RendererCommon.convertMatrixFromAndroidGraphicsMatrix(this.transformMatrix));
        } else {
            throw new RuntimeException("texture type not supported.");
        }
        int i4 = (this.height * i) + 0;
        int i5 = (i / 2) + i4;
        allocateDirect.position(0);
        allocateDirect.limit((this.height * i) + 0);
        ByteBuffer slice = allocateDirect.slice();
        allocateDirect.position(i4);
        int i6 = i3 * i;
        allocateDirect.limit(i4 + i6);
        ByteBuffer slice2 = allocateDirect.slice();
        allocateDirect.position(i5);
        allocateDirect.limit(i5 + i6);
        return JavaI420Buffer.wrap(this.width, this.height, slice, i, slice2, i, allocateDirect.slice(), i, null);
    }
}
