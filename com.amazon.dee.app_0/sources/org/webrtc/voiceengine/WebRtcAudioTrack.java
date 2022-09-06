package org.webrtc.voiceengine;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import org.webrtc.ContextUtils;
import org.webrtc.Logging;
import org.webrtc.ThreadUtils;
/* loaded from: classes5.dex */
public class WebRtcAudioTrack {
    private static final long AUDIO_TRACK_THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioTrack";
    private final AudioManager audioManager;
    private ByteBuffer byteBuffer;
    private byte[] emptyBytes;
    private final long nativeAudioTrack;
    private volatile int platformPropertyBufferSizeBytes;
    private final int streamType;
    private static final int DEFAULT_USAGE = getDefaultUsageAttribute();
    private static int usageAttribute = DEFAULT_USAGE;
    private static volatile boolean speakerMute = false;
    private static WebRtcAudioTrackErrorCallback errorCallback = null;
    private AudioTrack audioTrack = null;
    private AudioTrackThread audioThread = null;

    /* loaded from: classes5.dex */
    private class AudioTrackThread extends Thread {
        private volatile boolean keepAlive;

        public AudioTrackThread(String str) {
            super(str);
            this.keepAlive = true;
        }

        @TargetApi(21)
        private int writeOnLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
            return audioTrack.write(byteBuffer, i, 0);
        }

        private int writePreLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
            return audioTrack.write(byteBuffer.array(), byteBuffer.arrayOffset(), i);
        }

        /* JADX WARN: Code restructure failed: missing block: B:36:0x00d1, code lost:
            org.webrtc.Logging.d(org.webrtc.voiceengine.WebRtcAudioTrack.TAG, "keepAlive is false, breaking");
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00fe, code lost:
            org.webrtc.Logging.d(org.webrtc.voiceengine.WebRtcAudioTrack.TAG, "keepAlive is false, breaking");
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x0130, code lost:
            r10.this$0.reportWebRtcAudioTrackInitError("Initialization of audio track failed.");
            r10.this$0.releaseAudioResources();
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x013c, code lost:
            return;
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 578
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.webrtc.voiceengine.WebRtcAudioTrack.AudioTrackThread.run():void");
        }

        public void stopThread() {
            Logging.d(WebRtcAudioTrack.TAG, "stopThread");
            this.keepAlive = false;
        }
    }

    /* loaded from: classes5.dex */
    public interface WebRtcAudioTrackErrorCallback {
        void onWebRtcAudioTrackError(String str);

        void onWebRtcAudioTrackInitError(String str);

        void onWebRtcAudioTrackStartError(String str);
    }

    WebRtcAudioTrack(long j) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ctor");
        outline107.append(WebRtcAudioUtils.getThreadInfo());
        Logging.d(TAG, outline107.toString());
        this.nativeAudioTrack = j;
        this.audioManager = (AudioManager) ContextUtils.getApplicationContext().getSystemService("audio");
        this.streamType = getStreamType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertTrue(boolean z) {
        if (z) {
            return;
        }
        throw new AssertionError("Expected condition to be true");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int channelCountToConfiguration(int i) {
        return i == 1 ? 4 : 12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(21)
    public static AudioTrack createAudioTrackOnLollipopOrHigher(int i, int i2, int i3, int i4) {
        Logging.d(TAG, "createAudioTrackOnLollipopOrHigher");
        boolean z = false;
        int nativeOutputSampleRate = AudioTrack.getNativeOutputSampleRate(0);
        Logging.d(TAG, "nativeOutputSampleRate: " + nativeOutputSampleRate);
        if (i != nativeOutputSampleRate) {
            Logging.w(TAG, "Unable to use fast mode since requested sample rate is not native");
            z = true;
        }
        if (usageAttribute != DEFAULT_USAGE) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("A non default usage attribute is used: ");
            outline107.append(usageAttribute);
            Logging.w(TAG, outline107.toString());
        }
        AudioAttributes.Builder contentType = new AudioAttributes.Builder().setUsage(usageAttribute).setContentType(1);
        if (WebRtcAudioUtils.getOutputAudioDevice() != -1) {
            contentType.setFlags(WebRtcAudioUtils.getOutputAudioDevice());
        }
        if (!z && WebRtcAudioUtils.runningOnNougatOrHigher() && WebRtcAudioUtils.isFlagFastRequestedViaAudioTrack()) {
            setLowLatencyFlag(contentType);
            if (WebRtcAudioUtils.isRenderTrackBufferSizeOverriden()) {
                int overridenRenderTrackBufferSize = WebRtcAudioUtils.getOverridenRenderTrackBufferSize();
                StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Render track buffer size overriden Requested:", overridenRenderTrackBufferSize, " platformAdvertised: ", i4, "bufferSizeInBytes: ");
                outline110.append(i3);
                Logging.d(TAG, outline110.toString());
                if (overridenRenderTrackBufferSize >= i4) {
                    i4 = overridenRenderTrackBufferSize;
                }
                if (i4 < i3) {
                    i3 = i4;
                }
            }
            Logging.d(TAG, "Requesting flag low latency from platform. Track size = " + i3);
        }
        return new AudioTrack(contentType.build(), new AudioFormat.Builder().setEncoding(2).setSampleRate(i).setChannelMask(i2).build(), i3, 1, 0);
    }

    private static AudioTrack createAudioTrackOnLowerThanLollipop(int i, int i2, int i3, int i4) {
        return new AudioTrack(i4, i, i2, 2, i3, 1);
    }

    private static int getDefaultUsageAttribute() {
        if (WebRtcAudioUtils.runningOnLollipopOrHigher()) {
            return getDefaultUsageAttributeOnLollipopOrHigher();
        }
        return 0;
    }

    @TargetApi(21)
    private static int getDefaultUsageAttributeOnLollipopOrHigher() {
        return 2;
    }

    @TargetApi(17)
    private int getLowLatencyOutputFramesPerBuffer() {
        String property;
        if (WebRtcAudioUtils.runningOnJellyBeanMR1OrHigher() && (property = this.audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER")) != null) {
            return Integer.parseInt(property);
        }
        return 256;
    }

    private int getStreamMaxVolume() {
        Logging.d(TAG, "getStreamMaxVolume");
        assertTrue(this.audioManager != null);
        return this.audioManager.getStreamMaxVolume(this.streamType);
    }

    private int getStreamType() {
        if (WebRtcAudioUtils.isDefaultStreamTypeOverridden()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Default stream type is overriden to ");
            outline107.append(WebRtcAudioUtils.getDefaultStreamType());
            Logging.d(TAG, outline107.toString());
            return WebRtcAudioUtils.getDefaultStreamType();
        }
        return 0;
    }

    private int getStreamVolume() {
        Logging.d(TAG, "getStreamVolume");
        assertTrue(this.audioManager != null);
        return this.audioManager.getStreamVolume(this.streamType);
    }

    private boolean initPlayout(int i, int i2) {
        AudioTrack audioTrack;
        Logging.d(TAG, GeneratedOutlineSupport1.outline54("initPlayout(sampleRate=", i, ", channels=", i2, ")"));
        int i3 = i2 * 2;
        this.platformPropertyBufferSizeBytes = getLowLatencyOutputFramesPerBuffer() * i3;
        this.byteBuffer = ByteBuffer.allocateDirect((i / 100) * i3);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("byteBuffer.capacity: ");
        outline107.append(this.byteBuffer.capacity());
        Logging.d(TAG, outline107.toString());
        this.emptyBytes = new byte[this.byteBuffer.capacity()];
        nativeCacheDirectBufferAddress(this.byteBuffer, this.nativeAudioTrack);
        int channelCountToConfiguration = channelCountToConfiguration(i2);
        int minBufferSize = AudioTrack.getMinBufferSize(i, channelCountToConfiguration, 2);
        Logging.d(TAG, "AudioTrack.getMinBufferSize: " + minBufferSize);
        if (minBufferSize < this.byteBuffer.capacity()) {
            reportWebRtcAudioTrackInitError("AudioTrack.getMinBufferSize returns an invalid value.");
            return false;
        } else if (this.audioTrack != null) {
            reportWebRtcAudioTrackInitError("Conflict with existing AudioTrack.");
            return false;
        } else {
            try {
                if (!WebRtcAudioUtils.runningOnMarshmallowOrHigher() && (!WebRtcAudioUtils.runningOnLollipop() || WebRtcAudioUtils.isDefaultStreamTypeOverridden())) {
                    this.audioTrack = createAudioTrackOnLowerThanLollipop(i, channelCountToConfiguration, minBufferSize, this.streamType);
                    audioTrack = this.audioTrack;
                    if (audioTrack == null && audioTrack.getState() == 1) {
                        logMainParameters();
                        logMainParametersExtended();
                        return true;
                    }
                    reportWebRtcAudioTrackInitError("Initialization of audio track failed.");
                    releaseAudioResources();
                    return false;
                }
                this.audioTrack = createAudioTrackOnLollipopOrHigher(i, channelCountToConfiguration, minBufferSize, this.platformPropertyBufferSizeBytes);
                WebRtcAudioUtils.resetOutputAudioDeviceChanged();
                audioTrack = this.audioTrack;
                if (audioTrack == null) {
                }
                reportWebRtcAudioTrackInitError("Initialization of audio track failed.");
                releaseAudioResources();
                return false;
            } catch (IllegalArgumentException e) {
                reportWebRtcAudioTrackInitError(e.getMessage());
                releaseAudioResources();
                return false;
            }
        }
    }

    private boolean isVolumeFixed() {
        if (!WebRtcAudioUtils.runningOnLollipopOrHigher()) {
            return false;
        }
        return this.audioManager.isVolumeFixed();
    }

    private void logMainParameters() {
        if (this.audioTrack != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AudioTrack: session ID: ");
            outline107.append(this.audioTrack.getAudioSessionId());
            outline107.append(", channels: ");
            outline107.append(this.audioTrack.getChannelCount());
            outline107.append(", sample rate: ");
            outline107.append(this.audioTrack.getSampleRate());
            outline107.append(", max gain: ");
            outline107.append(AudioTrack.getMaxVolume());
            Logging.d(TAG, outline107.toString());
        }
    }

    @TargetApi(24)
    private void logMainParametersExtended() {
        if (WebRtcAudioUtils.runningOnMarshmallowOrHigher() && this.audioTrack != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AudioTrack: buffer size in frames: ");
            outline107.append(this.audioTrack.getBufferSizeInFrames());
            Logging.d(TAG, outline107.toString());
        }
        if (!WebRtcAudioUtils.runningOnNougatOrHigher() || this.audioTrack == null) {
            return;
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("AudioTrack: buffer capacity in frames: ");
        outline1072.append(this.audioTrack.getBufferCapacityInFrames());
        Logging.d(TAG, outline1072.toString());
    }

    @TargetApi(24)
    private void logUnderrunCount() {
        AudioTrack audioTrack;
        if (!WebRtcAudioUtils.runningOnNougatOrHigher() || (audioTrack = this.audioTrack) == null) {
            return;
        }
        int underrunCount = audioTrack.getUnderrunCount();
        Logging.d(TAG, "underrun count: " + underrunCount);
        WebRtcAudioUtils.addAudioRenderTrackUnderrunReport(underrunCount);
    }

    private native void nativeCacheDirectBufferAddress(ByteBuffer byteBuffer, long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeGetPlayoutData(int i, long j);

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseAudioResources() {
        Logging.d(TAG, "releaseAudioResources");
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack != null) {
            audioTrack.release();
            this.audioTrack = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportWebRtcAudioTrackError(String str) {
        Logging.e(TAG, "Run-time playback error: " + str);
        WebRtcAudioTrackErrorCallback webRtcAudioTrackErrorCallback = errorCallback;
        if (webRtcAudioTrackErrorCallback != null) {
            webRtcAudioTrackErrorCallback.onWebRtcAudioTrackError(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportWebRtcAudioTrackInitError(String str) {
        Logging.e(TAG, "Init error: " + str);
        WebRtcAudioTrackErrorCallback webRtcAudioTrackErrorCallback = errorCallback;
        if (webRtcAudioTrackErrorCallback != null) {
            webRtcAudioTrackErrorCallback.onWebRtcAudioTrackInitError(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportWebRtcAudioTrackStartError(String str) {
        Logging.e(TAG, "Start error: " + str);
        WebRtcAudioTrackErrorCallback webRtcAudioTrackErrorCallback = errorCallback;
        if (webRtcAudioTrackErrorCallback != null) {
            webRtcAudioTrackErrorCallback.onWebRtcAudioTrackStartError(str);
        }
    }

    public static synchronized void setAudioTrackUsageAttribute(int i) {
        synchronized (WebRtcAudioTrack.class) {
            Logging.w(TAG, "Default usage attribute is changed from: " + DEFAULT_USAGE + " to " + i);
            usageAttribute = i;
        }
    }

    public static void setErrorCallback(WebRtcAudioTrackErrorCallback webRtcAudioTrackErrorCallback) {
        Logging.d(TAG, "Set error callback");
        errorCallback = webRtcAudioTrackErrorCallback;
    }

    @TargetApi(24)
    private static AudioAttributes.Builder setLowLatencyFlag(AudioAttributes.Builder builder) {
        builder.setFlags(256);
        return builder;
    }

    public static void setSpeakerMute(boolean z) {
        Logging.w(TAG, "setSpeakerMute(" + z + ")");
        speakerMute = z;
    }

    private boolean setStreamVolume(int i) {
        Logging.d(TAG, "setStreamVolume(" + i + ")");
        assertTrue(this.audioManager != null);
        if (isVolumeFixed()) {
            Logging.e(TAG, "The device implements a fixed volume policy.");
            return false;
        }
        this.audioManager.setStreamVolume(this.streamType, i, 0);
        return true;
    }

    private boolean startPlayout() {
        Logging.d(TAG, "startPlayout");
        assertTrue(this.audioTrack != null);
        assertTrue(this.audioThread == null);
        if (this.audioTrack.getState() != 1) {
            reportWebRtcAudioTrackStartError("AudioTrack instance is not successfully initialized.");
            return false;
        }
        this.audioThread = new AudioTrackThread("AudioTrackJavaThread");
        this.audioThread.start();
        return true;
    }

    private boolean stopPlayout() {
        Logging.d(TAG, "stopPlayout");
        assertTrue(this.audioThread != null);
        logUnderrunCount();
        this.audioThread.stopThread();
        AudioTrackThread audioTrackThread = this.audioThread;
        this.audioThread = null;
        if (audioTrackThread != null) {
            Logging.d(TAG, "Stopping the AudioTrackThread...");
            audioTrackThread.interrupt();
            if (!ThreadUtils.joinUninterruptibly(audioTrackThread, 2000L)) {
                Logging.e(TAG, "Join of AudioTrackThread timed out.");
            }
            Logging.d(TAG, "AudioTrackThread has now been stopped.");
        }
        releaseAudioResources();
        WebRtcAudioUtils.resetOutputAudioDevice();
        return true;
    }
}
