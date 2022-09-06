package org.webrtc.voiceengine;

import android.annotation.TargetApi;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.os.Process;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import org.webrtc.ContextUtils;
import org.webrtc.Logging;
import org.webrtc.ThreadUtils;
/* loaded from: classes5.dex */
public class WebRtcAudioRecord {
    private static final long AUDIO_RECORD_THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int BUFFER_SIZE_FACTOR = 2;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioRecord";
    private static WebRtcAudioRecordErrorCallback errorCallback = null;
    private static volatile boolean microphoneMute = false;
    private ByteBuffer byteBuffer;
    private WebRtcAudioEffects effects;
    private byte[] emptyBytes;
    private final long nativeAudioRecord;
    private final ThreadUtils.ThreadChecker threadChecker = new ThreadUtils.ThreadChecker();
    private AudioRecord audioRecord = null;
    private AudioRecordThread audioThread = null;

    /* loaded from: classes5.dex */
    public enum AudioRecordStartErrorCode {
        AUDIO_RECORD_START_EXCEPTION,
        AUDIO_RECORD_START_STATE_MISMATCH
    }

    /* loaded from: classes5.dex */
    private class AudioRecordThread extends Thread {
        private volatile boolean keepAlive;

        public AudioRecordThread(String str) {
            super(str);
            this.keepAlive = true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-19);
            Logging.d(WebRtcAudioRecord.TAG, "AudioRecordThread" + WebRtcAudioUtils.getThreadInfo());
            WebRtcAudioRecord.assertTrue(WebRtcAudioRecord.this.audioRecord.getRecordingState() == 3);
            System.nanoTime();
            while (this.keepAlive) {
                int read = WebRtcAudioRecord.this.audioRecord.read(WebRtcAudioRecord.this.byteBuffer, WebRtcAudioRecord.this.byteBuffer.capacity());
                if (read == WebRtcAudioRecord.this.byteBuffer.capacity()) {
                    if (WebRtcAudioRecord.microphoneMute) {
                        WebRtcAudioRecord.this.byteBuffer.clear();
                        WebRtcAudioRecord.this.byteBuffer.put(WebRtcAudioRecord.this.emptyBytes);
                    }
                    if (this.keepAlive) {
                        WebRtcAudioRecord webRtcAudioRecord = WebRtcAudioRecord.this;
                        webRtcAudioRecord.nativeDataIsRecorded(read, webRtcAudioRecord.nativeAudioRecord);
                    }
                } else {
                    String str = "AudioRecord.read failed: " + read;
                    Logging.e(WebRtcAudioRecord.TAG, str);
                    if (read == -3) {
                        this.keepAlive = false;
                        WebRtcAudioRecord.this.reportWebRtcAudioRecordError(str);
                    }
                }
            }
            try {
                if (WebRtcAudioRecord.this.audioRecord == null) {
                    return;
                }
                WebRtcAudioRecord.this.audioRecord.stop();
            } catch (IllegalStateException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AudioRecord.stop failed: ");
                outline107.append(e.getMessage());
                Logging.e(WebRtcAudioRecord.TAG, outline107.toString());
            }
        }

        public void stopThread() {
            Logging.d(WebRtcAudioRecord.TAG, "stopThread");
            this.keepAlive = false;
        }
    }

    /* loaded from: classes5.dex */
    public interface WebRtcAudioRecordErrorCallback {
        void onWebRtcAudioRecordError(String str);

        void onWebRtcAudioRecordInitError(String str);

        void onWebRtcAudioRecordStartError(AudioRecordStartErrorCode audioRecordStartErrorCode, String str);
    }

    WebRtcAudioRecord(long j) {
        this.effects = null;
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        this.nativeAudioRecord = j;
        this.effects = WebRtcAudioEffects.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertTrue(boolean z) {
        if (z) {
            return;
        }
        throw new AssertionError("Expected condition to be true");
    }

    private int channelCountToConfiguration(int i) {
        return i == 1 ? 16 : 12;
    }

    @TargetApi(23)
    private AudioRecord createAudioRecordOnMarshmallowOrHigher(int i, int i2, int i3) {
        Logging.d(TAG, "createAudioRecordOnMarshmallowOrHigher");
        return new AudioRecord.Builder().setAudioSource(7).setAudioFormat(new AudioFormat.Builder().setEncoding(2).setSampleRate(i).setChannelMask(i2).build()).setBufferSizeInBytes(i3).build();
    }

    private boolean enableBuiltInAEC(boolean z) {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "enableBuiltInAEC(" + z + ')');
        WebRtcAudioEffects webRtcAudioEffects = this.effects;
        if (webRtcAudioEffects == null) {
            Logging.e(TAG, "Built-in AEC is not supported on this platform");
            return false;
        }
        return webRtcAudioEffects.setAEC(z);
    }

    private boolean enableBuiltInNS(boolean z) {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "enableBuiltInNS(" + z + ')');
        WebRtcAudioEffects webRtcAudioEffects = this.effects;
        if (webRtcAudioEffects == null) {
            Logging.e(TAG, "Built-in NS is not supported on this platform");
            return false;
        }
        return webRtcAudioEffects.setNS(z);
    }

    private int initRecording(int i, int i2) {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "initRecording(sampleRate=" + i + ", channels=" + i2 + ")");
        if (!WebRtcAudioUtils.hasPermission(ContextUtils.getApplicationContext(), "android.permission.RECORD_AUDIO")) {
            reportWebRtcAudioRecordInitError("RECORD_AUDIO permission is missing");
            return -1;
        } else if (this.audioRecord != null) {
            reportWebRtcAudioRecordInitError("InitRecording called twice without StopRecording.");
            return -1;
        } else {
            int i3 = i / 100;
            this.byteBuffer = ByteBuffer.allocateDirect(i2 * 2 * i3);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("byteBuffer.capacity: ");
            outline107.append(this.byteBuffer.capacity());
            Logging.d(TAG, outline107.toString());
            this.emptyBytes = new byte[this.byteBuffer.capacity()];
            nativeCacheDirectBufferAddress(this.byteBuffer, this.nativeAudioRecord);
            int channelCountToConfiguration = channelCountToConfiguration(i2);
            int minBufferSize = AudioRecord.getMinBufferSize(i, channelCountToConfiguration, 2);
            if (minBufferSize != -1 && minBufferSize != -2) {
                Logging.d(TAG, "AudioRecord.getMinBufferSize: " + minBufferSize);
                int max = Math.max(minBufferSize * 2, this.byteBuffer.capacity());
                Logging.d(TAG, "bufferSizeInBytes: " + max);
                try {
                    if (WebRtcAudioUtils.runningOnMarshmallowOrHigher()) {
                        this.audioRecord = createAudioRecordOnMarshmallowOrHigher(i, channelCountToConfiguration, max);
                    } else {
                        this.audioRecord = new AudioRecord(7, i, channelCountToConfiguration, 2, max);
                    }
                    AudioRecord audioRecord = this.audioRecord;
                    if (audioRecord != null && audioRecord.getState() == 1) {
                        WebRtcAudioEffects webRtcAudioEffects = this.effects;
                        if (webRtcAudioEffects != null) {
                            webRtcAudioEffects.enable(this.audioRecord.getAudioSessionId());
                        }
                        logMainParameters();
                        logMainParametersExtended();
                        return i3;
                    }
                    reportWebRtcAudioRecordInitError("Failed to create a new AudioRecord instance");
                    releaseAudioResources();
                    return -1;
                } catch (IllegalArgumentException e) {
                    reportWebRtcAudioRecordInitError(GeneratedOutlineSupport1.outline43(e, GeneratedOutlineSupport1.outline107("AudioRecord ctor error: ")));
                    releaseAudioResources();
                    return -1;
                }
            }
            reportWebRtcAudioRecordInitError(GeneratedOutlineSupport1.outline49("AudioRecord.getMinBufferSize failed: ", minBufferSize));
            return -1;
        }
    }

    private void logMainParameters() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AudioRecord: session ID: ");
        outline107.append(this.audioRecord.getAudioSessionId());
        outline107.append(", channels: ");
        outline107.append(this.audioRecord.getChannelCount());
        outline107.append(", sample rate: ");
        outline107.append(this.audioRecord.getSampleRate());
        Logging.d(TAG, outline107.toString());
    }

    @TargetApi(23)
    private void logMainParametersExtended() {
        if (WebRtcAudioUtils.runningOnMarshmallowOrHigher()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AudioRecord: buffer size in frames: ");
            outline107.append(this.audioRecord.getBufferSizeInFrames());
            Logging.d(TAG, outline107.toString());
        }
    }

    private native void nativeCacheDirectBufferAddress(ByteBuffer byteBuffer, long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDataIsRecorded(int i, long j);

    private void releaseAudioResources() {
        AudioRecord audioRecord = this.audioRecord;
        if (audioRecord != null) {
            audioRecord.release();
            this.audioRecord = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportWebRtcAudioRecordError(String str) {
        Logging.e(TAG, "Run-time recording error: " + str);
        WebRtcAudioRecordErrorCallback webRtcAudioRecordErrorCallback = errorCallback;
        if (webRtcAudioRecordErrorCallback != null) {
            webRtcAudioRecordErrorCallback.onWebRtcAudioRecordError(str);
        }
    }

    private void reportWebRtcAudioRecordInitError(String str) {
        Logging.e(TAG, "Init recording error: " + str);
        WebRtcAudioRecordErrorCallback webRtcAudioRecordErrorCallback = errorCallback;
        if (webRtcAudioRecordErrorCallback != null) {
            webRtcAudioRecordErrorCallback.onWebRtcAudioRecordInitError(str);
        }
    }

    private void reportWebRtcAudioRecordStartError(AudioRecordStartErrorCode audioRecordStartErrorCode, String str) {
        Logging.e(TAG, "Start recording error: " + audioRecordStartErrorCode + ". " + str);
        WebRtcAudioRecordErrorCallback webRtcAudioRecordErrorCallback = errorCallback;
        if (webRtcAudioRecordErrorCallback != null) {
            webRtcAudioRecordErrorCallback.onWebRtcAudioRecordStartError(audioRecordStartErrorCode, str);
        }
    }

    public static void setErrorCallback(WebRtcAudioRecordErrorCallback webRtcAudioRecordErrorCallback) {
        Logging.d(TAG, "Set error callback");
        errorCallback = webRtcAudioRecordErrorCallback;
    }

    public static void setMicrophoneMute(boolean z) {
        Logging.w(TAG, "setMicrophoneMute(" + z + ")");
        microphoneMute = z;
    }

    private boolean startRecording() {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "startRecording");
        assertTrue(this.audioRecord != null);
        assertTrue(this.audioThread == null);
        try {
            this.audioRecord.startRecording();
            int i = 0;
            while (this.audioRecord.getRecordingState() != 3 && (i = i + 1) < 2) {
                threadSleep(200L);
            }
            if (this.audioRecord.getRecordingState() != 3) {
                AudioRecordStartErrorCode audioRecordStartErrorCode = AudioRecordStartErrorCode.AUDIO_RECORD_START_STATE_MISMATCH;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AudioRecord.startRecording failed - incorrect state :");
                outline107.append(this.audioRecord.getRecordingState());
                reportWebRtcAudioRecordStartError(audioRecordStartErrorCode, outline107.toString());
                return false;
            }
            this.audioThread = new AudioRecordThread("AudioRecordJavaThread");
            this.audioThread.start();
            return true;
        } catch (IllegalStateException e) {
            AudioRecordStartErrorCode audioRecordStartErrorCode2 = AudioRecordStartErrorCode.AUDIO_RECORD_START_EXCEPTION;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("AudioRecord.startRecording failed: ");
            outline1072.append(e.getMessage());
            reportWebRtcAudioRecordStartError(audioRecordStartErrorCode2, outline1072.toString());
            return false;
        }
    }

    private boolean stopRecording() {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "stopRecording");
        assertTrue(this.audioThread != null);
        this.audioThread.stopThread();
        if (!ThreadUtils.joinUninterruptibly(this.audioThread, 2000L)) {
            Logging.e(TAG, "Join of AudioRecordJavaThread timed out");
        }
        this.audioThread = null;
        WebRtcAudioEffects webRtcAudioEffects = this.effects;
        if (webRtcAudioEffects != null) {
            webRtcAudioEffects.release();
        }
        releaseAudioResources();
        return true;
    }

    private void threadSleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Thread.sleep failed: ");
            outline107.append(e.getMessage());
            Logging.e(TAG, outline107.toString());
        }
    }
}
