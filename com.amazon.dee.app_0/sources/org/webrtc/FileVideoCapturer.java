package org.webrtc;

import android.content.Context;
import android.os.SystemClock;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import org.webrtc.VideoCapturer;
/* loaded from: classes5.dex */
public class FileVideoCapturer implements VideoCapturer {
    private static final String TAG = "FileVideoCapturer";
    private VideoCapturer.CapturerObserver capturerObserver;
    private final VideoReader videoReader;
    private final Timer timer = new Timer();
    private final TimerTask tickTask = new TimerTask() { // from class: org.webrtc.FileVideoCapturer.1
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            FileVideoCapturer.this.tick();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface VideoReader {
        void close();

        VideoFrame getNextFrame();
    }

    /* loaded from: classes5.dex */
    private static class VideoReaderY4M implements VideoReader {
        private static final String TAG = "VideoReaderY4M";
        private static final String Y4M_FRAME_DELIMETER = "FRAME";
        private final int frameHeight;
        private final int frameWidth;
        private final RandomAccessFile mediaFileStream;
        private final long videoStart;

        public VideoReaderY4M(String str) throws IOException {
            String[] split;
            this.mediaFileStream = new RandomAccessFile(str, "r");
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = this.mediaFileStream.read();
                if (read != -1) {
                    if (read == 10) {
                        this.videoStart = this.mediaFileStream.getFilePointer();
                        int i = 0;
                        int i2 = 0;
                        String str2 = "";
                        for (String str3 : sb.toString().split("[ ]")) {
                            char charAt = str3.charAt(0);
                            if (charAt == 'C') {
                                str2 = str3.substring(1);
                            } else if (charAt == 'H') {
                                i2 = Integer.parseInt(str3.substring(1));
                            } else if (charAt == 'W') {
                                i = Integer.parseInt(str3.substring(1));
                            }
                        }
                        Logging.d(TAG, "Color space: " + str2);
                        if (!str2.equals("420") && !str2.equals("420mpeg2")) {
                            throw new IllegalArgumentException("Does not support any other color space than I420 or I420mpeg2");
                        }
                        if (i % 2 != 1 && i2 % 2 != 1) {
                            this.frameWidth = i;
                            this.frameHeight = i2;
                            Logging.d(TAG, GeneratedOutlineSupport1.outline54("frame dim: (", i, ", ", i2, ")"));
                            return;
                        }
                        throw new IllegalArgumentException("Does not support odd width or height");
                    }
                    sb.append((char) read);
                } else {
                    throw new RuntimeException(GeneratedOutlineSupport1.outline72("Found end of file before end of header for file: ", str));
                }
            }
        }

        @Override // org.webrtc.FileVideoCapturer.VideoReader
        public void close() {
            try {
                this.mediaFileStream.close();
            } catch (IOException e) {
                Logging.e(TAG, "Problem closing file", e);
            }
        }

        @Override // org.webrtc.FileVideoCapturer.VideoReader
        public VideoFrame getNextFrame() {
            long nanos = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
            JavaI420Buffer allocate = JavaI420Buffer.allocate(this.frameWidth, this.frameHeight);
            ByteBuffer dataY = allocate.getDataY();
            ByteBuffer dataU = allocate.getDataU();
            ByteBuffer dataV = allocate.getDataV();
            int i = this.frameHeight;
            int i2 = (i + 1) / 2;
            int strideY = allocate.getStrideY() * i;
            int strideU = allocate.getStrideU() * i2;
            int strideV = allocate.getStrideV() * i2;
            try {
                byte[] bArr = new byte[6];
                if (this.mediaFileStream.read(bArr) < bArr.length) {
                    this.mediaFileStream.seek(this.videoStart);
                    if (this.mediaFileStream.read(bArr) < bArr.length) {
                        throw new RuntimeException("Error looping video");
                    }
                }
                String str = new String(bArr);
                if (str.equals("FRAME\n")) {
                    this.mediaFileStream.readFully(dataY.array(), dataY.arrayOffset(), strideY);
                    this.mediaFileStream.readFully(dataU.array(), dataU.arrayOffset(), strideU);
                    this.mediaFileStream.readFully(dataV.array(), dataV.arrayOffset(), strideV);
                    return new VideoFrame(allocate, 0, nanos);
                }
                throw new RuntimeException("Frames should be delimited by FRAME plus newline, found delimter was: '" + str + "'");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static {
        System.loadLibrary("jingle_peerconnection_so");
    }

    public FileVideoCapturer(String str) throws IOException {
        try {
            this.videoReader = new VideoReaderY4M(str);
        } catch (IOException e) {
            Logging.d(TAG, "Could not open video file: " + str);
            throw e;
        }
    }

    @Override // org.webrtc.VideoCapturer
    public void changeCaptureFormat(int i, int i2, int i3) {
    }

    @Override // org.webrtc.VideoCapturer
    public void dispose() {
        this.videoReader.close();
    }

    @Override // org.webrtc.VideoCapturer
    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, VideoCapturer.CapturerObserver capturerObserver) {
        this.capturerObserver = capturerObserver;
    }

    @Override // org.webrtc.VideoCapturer
    public boolean isScreencast() {
        return false;
    }

    @Override // org.webrtc.VideoCapturer
    public void startCapture(int i, int i2, int i3) {
        this.timer.schedule(this.tickTask, 0L, 1000 / i3);
    }

    @Override // org.webrtc.VideoCapturer
    public void stopCapture() throws InterruptedException {
        this.timer.cancel();
    }

    public void tick() {
        this.capturerObserver.onFrameCaptured(this.videoReader.getNextFrame());
    }
}
