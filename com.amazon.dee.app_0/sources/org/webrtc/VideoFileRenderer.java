package org.webrtc;

import android.os.Handler;
import android.os.HandlerThread;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import org.webrtc.EglBase;
import org.webrtc.VideoRenderer;
/* loaded from: classes5.dex */
public class VideoFileRenderer implements VideoRenderer.Callbacks {
    private static final String TAG = "VideoFileRenderer";
    private EglBase eglBase;
    private final int outputFileHeight;
    private final String outputFileName;
    private final int outputFileWidth;
    private final ByteBuffer outputFrameBuffer;
    private final int outputFrameSize;
    private final HandlerThread renderThread;
    private final Handler renderThreadHandler;
    private final FileOutputStream videoOutFile;
    private YuvConverter yuvConverter;
    private final Object handlerLock = new Object();
    private ArrayList<ByteBuffer> rawFrames = new ArrayList<>();

    static {
        System.loadLibrary("jingle_peerconnection_so");
    }

    public VideoFileRenderer(String str, int i, int i2, final EglBase.Context context) throws IOException {
        if (i % 2 != 1 && i2 % 2 != 1) {
            this.outputFileName = str;
            this.outputFileWidth = i;
            this.outputFileHeight = i2;
            this.outputFrameSize = ((i * i2) * 3) / 2;
            this.outputFrameBuffer = ByteBuffer.allocateDirect(this.outputFrameSize);
            this.videoOutFile = new FileOutputStream(str);
            FileOutputStream fileOutputStream = this.videoOutFile;
            fileOutputStream.write(("YUV4MPEG2 C420 W" + i + " H" + i2 + " Ip F30:1 A1:1\n").getBytes());
            this.renderThread = new HandlerThread(TAG);
            this.renderThread.start();
            this.renderThreadHandler = new Handler(this.renderThread.getLooper());
            ThreadUtils.invokeAtFrontUninterruptibly(this.renderThreadHandler, new Runnable() { // from class: org.webrtc.VideoFileRenderer.1
                @Override // java.lang.Runnable
                public void run() {
                    VideoFileRenderer.this.eglBase = EglBase.create(context, EglBase.CONFIG_PIXEL_BUFFER);
                    VideoFileRenderer.this.eglBase.createDummyPbufferSurface();
                    VideoFileRenderer.this.eglBase.makeCurrent();
                    VideoFileRenderer.this.yuvConverter = new YuvConverter();
                }
            });
            return;
        }
        throw new IllegalArgumentException("Does not support uneven width or height");
    }

    public static native ByteBuffer nativeCreateNativeByteBuffer(int i);

    public static native void nativeFreeNativeByteBuffer(ByteBuffer byteBuffer);

    public static native void nativeI420Scale(ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2, ByteBuffer byteBuffer3, int i3, int i4, int i5, ByteBuffer byteBuffer4, int i6, int i7);

    /* JADX INFO: Access modifiers changed from: private */
    public void renderFrameOnRenderThread(VideoRenderer.I420Frame i420Frame) {
        float[] multiplyMatrices = RendererCommon.multiplyMatrices(RendererCommon.rotateTextureMatrix(i420Frame.samplingMatrix, i420Frame.rotationDegree), RendererCommon.getLayoutMatrix(false, i420Frame.rotatedWidth() / i420Frame.rotatedHeight(), this.outputFileWidth / this.outputFileHeight));
        try {
            ByteBuffer nativeCreateNativeByteBuffer = nativeCreateNativeByteBuffer(this.outputFrameSize);
            if (!i420Frame.yuvFrame) {
                this.yuvConverter.convert(this.outputFrameBuffer, this.outputFileWidth, this.outputFileHeight, this.outputFileWidth, i420Frame.textureId, multiplyMatrices);
                int i = this.outputFileWidth;
                byte[] array = this.outputFrameBuffer.array();
                int arrayOffset = this.outputFrameBuffer.arrayOffset();
                nativeCreateNativeByteBuffer.put(array, arrayOffset, this.outputFileWidth * this.outputFileHeight);
                for (int i2 = this.outputFileHeight; i2 < (this.outputFileHeight * 3) / 2; i2++) {
                    nativeCreateNativeByteBuffer.put(array, (i2 * i) + arrayOffset, i / 2);
                }
                for (int i3 = this.outputFileHeight; i3 < (this.outputFileHeight * 3) / 2; i3++) {
                    nativeCreateNativeByteBuffer.put(array, (i3 * i) + arrayOffset + (i / 2), i / 2);
                }
            } else {
                ByteBuffer[] byteBufferArr = i420Frame.yuvPlanes;
                ByteBuffer byteBuffer = byteBufferArr[0];
                int[] iArr = i420Frame.yuvStrides;
                nativeI420Scale(byteBuffer, iArr[0], byteBufferArr[1], iArr[1], byteBufferArr[2], iArr[2], i420Frame.width, i420Frame.height, this.outputFrameBuffer, this.outputFileWidth, this.outputFileHeight);
                nativeCreateNativeByteBuffer.put(this.outputFrameBuffer.array(), this.outputFrameBuffer.arrayOffset(), this.outputFrameSize);
            }
            nativeCreateNativeByteBuffer.rewind();
            this.rawFrames.add(nativeCreateNativeByteBuffer);
        } finally {
            VideoRenderer.renderFrameDone(i420Frame);
        }
    }

    public void release() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.renderThreadHandler.post(new Runnable() { // from class: org.webrtc.VideoFileRenderer.3
            @Override // java.lang.Runnable
            public void run() {
                VideoFileRenderer.this.yuvConverter.release();
                VideoFileRenderer.this.eglBase.release();
                VideoFileRenderer.this.renderThread.quit();
                countDownLatch.countDown();
            }
        });
        ThreadUtils.awaitUninterruptibly(countDownLatch);
        try {
            Iterator<ByteBuffer> it2 = this.rawFrames.iterator();
            while (it2.hasNext()) {
                ByteBuffer next = it2.next();
                this.videoOutFile.write("FRAME\n".getBytes());
                byte[] bArr = new byte[this.outputFrameSize];
                next.get(bArr);
                this.videoOutFile.write(bArr);
                nativeFreeNativeByteBuffer(next);
            }
            this.videoOutFile.close();
            Logging.d(TAG, "Video written to disk as " + this.outputFileName + ". Number frames are " + this.rawFrames.size() + " and the dimension of the frames are " + this.outputFileWidth + ReactProperties.HereMapMarker.X + this.outputFileHeight + ".");
        } catch (IOException e) {
            Logging.e(TAG, "Error writing video to disk", e);
        }
    }

    @Override // org.webrtc.VideoRenderer.Callbacks
    public void renderFrame(final VideoRenderer.I420Frame i420Frame) {
        this.renderThreadHandler.post(new Runnable() { // from class: org.webrtc.VideoFileRenderer.2
            @Override // java.lang.Runnable
            public void run() {
                VideoFileRenderer.this.renderFrameOnRenderThread(i420Frame);
            }
        });
    }
}
