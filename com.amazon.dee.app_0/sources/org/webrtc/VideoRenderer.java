package org.webrtc;

import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import org.webrtc.VideoFrame;
import org.webrtc.VideoRenderer;
/* loaded from: classes5.dex */
public class VideoRenderer {
    long nativeVideoRenderer;

    /* loaded from: classes5.dex */
    public interface Callbacks {
        void renderFrame(I420Frame i420Frame);
    }

    public VideoRenderer(Callbacks callbacks) {
        this.nativeVideoRenderer = nativeWrapVideoRenderer(callbacks);
    }

    private static native void freeWrappedVideoRenderer(long j);

    public static native void nativeCopyPlane(ByteBuffer byteBuffer, int i, int i2, int i3, ByteBuffer byteBuffer2, int i4);

    private static native long nativeWrapVideoRenderer(Callbacks callbacks);

    private static native void releaseNativeFrame(long j);

    public static void renderFrameDone(I420Frame i420Frame) {
        i420Frame.yuvPlanes = null;
        i420Frame.textureId = 0;
        if (i420Frame.nativeFramePointer != 0) {
            releaseNativeFrame(i420Frame.nativeFramePointer);
            i420Frame.nativeFramePointer = 0L;
        }
    }

    public void dispose() {
        long j = this.nativeVideoRenderer;
        if (j == 0) {
            return;
        }
        freeWrappedVideoRenderer(j);
        this.nativeVideoRenderer = 0L;
    }

    /* loaded from: classes5.dex */
    public static class I420Frame {
        private final VideoFrame.Buffer backingBuffer;
        public final int height;
        private long nativeFramePointer;
        public int rotationDegree;
        public final float[] samplingMatrix;
        public int textureId;
        public final int width;
        public final boolean yuvFrame;
        public ByteBuffer[] yuvPlanes;
        public final int[] yuvStrides;

        public I420Frame(int i, int i2, int i3, int[] iArr, ByteBuffer[] byteBufferArr, long j) {
            this.width = i;
            this.height = i2;
            this.yuvStrides = iArr;
            this.yuvPlanes = byteBufferArr;
            this.yuvFrame = true;
            this.rotationDegree = i3;
            this.nativeFramePointer = j;
            this.backingBuffer = null;
            if (i3 % 90 == 0) {
                this.samplingMatrix = RendererCommon.verticalFlipMatrix();
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Rotation degree not multiple of 90: ", i3));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$toVideoFrame$0$VideoRenderer$I420Frame() {
            VideoRenderer.renderFrameDone(this);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$toVideoFrame$1$VideoRenderer$I420Frame() {
            VideoRenderer.renderFrameDone(this);
        }

        public int rotatedHeight() {
            return this.rotationDegree % 180 == 0 ? this.height : this.width;
        }

        public int rotatedWidth() {
            return this.rotationDegree % 180 == 0 ? this.width : this.height;
        }

        public String toString() {
            StringBuilder outline107;
            int i;
            if (this.yuvFrame) {
                outline107 = GeneratedOutlineSupport1.outline107("Y: ");
                outline107.append(this.yuvStrides[0]);
                outline107.append(", U: ");
                outline107.append(this.yuvStrides[1]);
                outline107.append(", V: ");
                i = this.yuvStrides[2];
            } else {
                outline107 = GeneratedOutlineSupport1.outline107("Texture: ");
                i = this.textureId;
            }
            outline107.append(i);
            String sb = outline107.toString();
            return this.width + ReactProperties.HereMapMarker.X + this.height + ", " + sb;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public VideoFrame toVideoFrame() {
            VideoFrame.Buffer textureBufferImpl;
            VideoFrame.Buffer buffer = this.backingBuffer;
            if (buffer != null) {
                buffer.retain();
                VideoRenderer.renderFrameDone(this);
                textureBufferImpl = this.backingBuffer;
            } else if (this.yuvFrame) {
                int i = this.width;
                int i2 = this.height;
                ByteBuffer[] byteBufferArr = this.yuvPlanes;
                ByteBuffer byteBuffer = byteBufferArr[0];
                int[] iArr = this.yuvStrides;
                textureBufferImpl = JavaI420Buffer.wrap(i, i2, byteBuffer, iArr[0], byteBufferArr[1], iArr[1], byteBufferArr[2], iArr[2], new Runnable(this) { // from class: org.webrtc.VideoRenderer$I420Frame$$Lambda$0
                    private final VideoRenderer.I420Frame arg$1;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.arg$1 = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        this.arg$1.lambda$toVideoFrame$0$VideoRenderer$I420Frame();
                    }
                });
            } else {
                textureBufferImpl = new TextureBufferImpl(this.width, this.height, VideoFrame.TextureBuffer.Type.OES, this.textureId, RendererCommon.convertMatrixToAndroidGraphicsMatrix(this.samplingMatrix), null, new Runnable(this) { // from class: org.webrtc.VideoRenderer$I420Frame$$Lambda$1
                    private final VideoRenderer.I420Frame arg$1;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.arg$1 = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        this.arg$1.lambda$toVideoFrame$1$VideoRenderer$I420Frame();
                    }
                });
            }
            return new VideoFrame(textureBufferImpl, this.rotationDegree, 0L);
        }

        public I420Frame(int i, int i2, int i3, int i4, float[] fArr, long j) {
            this.width = i;
            this.height = i2;
            this.yuvStrides = null;
            this.yuvPlanes = null;
            this.samplingMatrix = fArr;
            this.textureId = i4;
            this.yuvFrame = false;
            this.rotationDegree = i3;
            this.nativeFramePointer = j;
            this.backingBuffer = null;
            if (i3 % 90 == 0) {
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Rotation degree not multiple of 90: ", i3));
        }

        public I420Frame(int i, VideoFrame.Buffer buffer, long j) {
            this.width = buffer.getWidth();
            this.height = buffer.getHeight();
            this.rotationDegree = i;
            if (i % 90 == 0) {
                if (buffer instanceof VideoFrame.TextureBuffer) {
                    VideoFrame.TextureBuffer textureBuffer = (VideoFrame.TextureBuffer) buffer;
                    if (textureBuffer.getType() == VideoFrame.TextureBuffer.Type.OES) {
                        this.yuvFrame = false;
                        this.textureId = textureBuffer.getTextureId();
                        this.samplingMatrix = RendererCommon.convertMatrixFromAndroidGraphicsMatrix(textureBuffer.getTransformMatrix());
                        this.yuvStrides = null;
                        this.yuvPlanes = null;
                        this.nativeFramePointer = j;
                        this.backingBuffer = buffer;
                        return;
                    }
                }
                if (buffer instanceof VideoFrame.I420Buffer) {
                    VideoFrame.I420Buffer i420Buffer = (VideoFrame.I420Buffer) buffer;
                    this.yuvFrame = true;
                    this.yuvStrides = new int[]{i420Buffer.getStrideY(), i420Buffer.getStrideU(), i420Buffer.getStrideV()};
                    this.yuvPlanes = new ByteBuffer[]{i420Buffer.getDataY(), i420Buffer.getDataU(), i420Buffer.getDataV()};
                    this.samplingMatrix = RendererCommon.verticalFlipMatrix();
                    this.textureId = 0;
                } else {
                    this.yuvFrame = false;
                    this.textureId = 0;
                    this.samplingMatrix = null;
                    this.yuvStrides = null;
                    this.yuvPlanes = null;
                }
                this.nativeFramePointer = j;
                this.backingBuffer = buffer;
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Rotation degree not multiple of 90: ", i));
        }
    }
}
