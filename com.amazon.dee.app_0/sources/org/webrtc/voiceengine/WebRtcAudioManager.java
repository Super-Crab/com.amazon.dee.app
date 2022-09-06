package org.webrtc.voiceengine;

import android.annotation.TargetApi;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Build;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Timer;
import java.util.TimerTask;
import org.webrtc.ContextUtils;
import org.webrtc.Logging;
/* loaded from: classes5.dex */
public class WebRtcAudioManager {
    private static final String[] AUDIO_MODES = {"MODE_NORMAL", "MODE_RINGTONE", "MODE_IN_CALL", "MODE_IN_COMMUNICATION"};
    private static final int BITS_PER_SAMPLE = 16;
    private static final boolean DEBUG = false;
    static final int DEFAULT_FRAME_PER_BUFFER = 256;
    private static final String TAG = "WebRtcAudioManager";
    private static boolean blacklistDeviceForOpenSLESUsage = false;
    private static boolean blacklistDeviceForOpenSLESUsageIsOverridden = false;
    private static boolean useStereoInput = false;
    private static boolean useStereoOutput = false;
    private final AudioManager audioManager;
    private boolean hardwareAEC;
    private boolean hardwareAGC;
    private boolean hardwareNS;
    private boolean initialized = false;
    private int inputBufferSize;
    private int inputChannels;
    private int inputSampleRate;
    private boolean lowLatencyInput;
    private boolean lowLatencyOutput;
    private final long nativeAudioManager;
    private int nativeChannels;
    private int nativeSampleRate;
    private int outputBufferSize;
    private int outputChannels;
    private int outputSampleRate;
    private boolean proAudio;
    private final VolumeLogger volumeLogger;

    /* loaded from: classes5.dex */
    private static class VolumeLogger {
        private static final String THREAD_NAME = "WebRtcVolumeLevelLoggerThread";
        private static final int TIMER_PERIOD_IN_SECONDS = 30;
        private final AudioManager audioManager;
        private Timer timer;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes5.dex */
        public class LogVolumeTask extends TimerTask {
            private final int maxRingVolume;
            private final int maxVoiceCallVolume;

            LogVolumeTask(int i, int i2) {
                this.maxRingVolume = i;
                this.maxVoiceCallVolume = i2;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int mode = VolumeLogger.this.audioManager.getMode();
                if (mode == 1) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("STREAM_RING stream volume: ");
                    outline107.append(VolumeLogger.this.audioManager.getStreamVolume(2));
                    outline107.append(" (max=");
                    outline107.append(this.maxRingVolume);
                    outline107.append(")");
                    Logging.d(WebRtcAudioManager.TAG, outline107.toString());
                } else if (mode != 3) {
                } else {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("VOICE_CALL stream volume: ");
                    outline1072.append(VolumeLogger.this.audioManager.getStreamVolume(0));
                    outline1072.append(" (max=");
                    outline1072.append(this.maxVoiceCallVolume);
                    outline1072.append(")");
                    Logging.d(WebRtcAudioManager.TAG, outline1072.toString());
                }
            }
        }

        public VolumeLogger(AudioManager audioManager) {
            this.audioManager = audioManager;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stop() {
            Timer timer = this.timer;
            if (timer != null) {
                timer.cancel();
                this.timer = null;
            }
        }

        public void start() {
            this.timer = new Timer(THREAD_NAME);
            this.timer.schedule(new LogVolumeTask(this.audioManager.getStreamMaxVolume(2), this.audioManager.getStreamMaxVolume(0)), 0L, 30000L);
        }
    }

    WebRtcAudioManager(long j) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ctor");
        outline107.append(WebRtcAudioUtils.getThreadInfo());
        Logging.d(TAG, outline107.toString());
        this.nativeAudioManager = j;
        this.audioManager = (AudioManager) ContextUtils.getApplicationContext().getSystemService("audio");
        this.volumeLogger = new VolumeLogger(this.audioManager);
        storeAudioParameters();
        nativeCacheAudioParameters(this.outputSampleRate, this.inputSampleRate, this.outputChannels, this.inputChannels, this.hardwareAEC, this.hardwareAGC, this.hardwareNS, this.lowLatencyOutput, this.lowLatencyInput, this.proAudio, this.outputBufferSize, this.inputBufferSize, j);
    }

    private static void assertTrue(boolean z) {
        if (z) {
            return;
        }
        throw new AssertionError("Expected condition to be true");
    }

    private void dispose() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("dispose");
        outline107.append(WebRtcAudioUtils.getThreadInfo());
        Logging.d(TAG, outline107.toString());
        if (!this.initialized) {
            return;
        }
        this.volumeLogger.stop();
    }

    private int getLowLatencyInputFramesPerBuffer() {
        assertTrue(isLowLatencyInputSupported());
        return getLowLatencyOutputFramesPerBuffer();
    }

    @TargetApi(17)
    private int getLowLatencyOutputFramesPerBuffer() {
        String property;
        assertTrue(isLowLatencyOutputSupported());
        if (WebRtcAudioUtils.runningOnJellyBeanMR1OrHigher() && (property = this.audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER")) != null) {
            return Integer.parseInt(property);
        }
        return 256;
    }

    private static int getMinInputFrameSize(int i, int i2) {
        return AudioRecord.getMinBufferSize(i, i2 == 1 ? 16 : 12, 2) / (i2 * 2);
    }

    private static int getMinOutputFrameSize(int i, int i2) {
        return AudioTrack.getMinBufferSize(i, i2 == 1 ? 4 : 12, 2) / (i2 * 2);
    }

    private int getNativeInputSampleRate() {
        if (WebRtcAudioUtils.runningOnEmulator()) {
            Logging.d(TAG, "Running emulator, overriding input sample rate to 8 kHz.");
            return 8000;
        } else if (WebRtcAudioUtils.isDefaultSampleRateOverridden()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Default input sample rate is overriden to ");
            outline107.append(WebRtcAudioUtils.getDefaultSampleRateHz());
            outline107.append(" Hz");
            Logging.d(TAG, outline107.toString());
            return WebRtcAudioUtils.getDefaultSampleRateHz();
        } else {
            return getPlatformSupportedSampleRate();
        }
    }

    private int getNativeOutputSampleRate() {
        if (WebRtcAudioUtils.runningOnEmulator()) {
            Logging.d(TAG, "Running emulator, overriding output sample rate to 8 kHz.");
            return 8000;
        } else if (WebRtcAudioUtils.isDefaultOutputSampleRateOverridden()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Default output sample rate is overriden to ");
            outline107.append(WebRtcAudioUtils.getDefaultOutputSampleRateHz());
            outline107.append(" Hz");
            Logging.d(TAG, outline107.toString());
            return WebRtcAudioUtils.getDefaultOutputSampleRateHz();
        } else {
            return getPlatformSupportedSampleRate();
        }
    }

    private int getOutputBufferFrameSize(boolean z) {
        int minOutputFrameSize = getMinOutputFrameSize(this.outputSampleRate, this.outputChannels);
        int i = this.outputChannels * 2;
        if (z) {
            int lowLatencyOutputFramesPerBuffer = getLowLatencyOutputFramesPerBuffer();
            if (WebRtcAudioUtils.isRenderTrackBufferSizeOverriden()) {
                int overridenRenderTrackBufferSize = WebRtcAudioUtils.getOverridenRenderTrackBufferSize() / i;
                if (overridenRenderTrackBufferSize >= lowLatencyOutputFramesPerBuffer) {
                    lowLatencyOutputFramesPerBuffer = overridenRenderTrackBufferSize;
                }
                if (lowLatencyOutputFramesPerBuffer > minOutputFrameSize) {
                    lowLatencyOutputFramesPerBuffer = minOutputFrameSize;
                }
                Logging.d(TAG, "User defined output buffer size in frames after bound checks: " + lowLatencyOutputFramesPerBuffer);
            }
            return lowLatencyOutputFramesPerBuffer;
        }
        return minOutputFrameSize;
    }

    private int getPlatformSupportedSampleRate() {
        int defaultSampleRateHz;
        if (WebRtcAudioUtils.runningOnJellyBeanMR1OrHigher()) {
            defaultSampleRateHz = getSampleRateOnJellyBeanMR10OrHigher();
        } else {
            defaultSampleRateHz = WebRtcAudioUtils.getDefaultSampleRateHz();
        }
        Logging.d(TAG, "Sample rate is set to " + defaultSampleRateHz + " Hz");
        return defaultSampleRateHz;
    }

    @TargetApi(17)
    private int getSampleRateOnJellyBeanMR10OrHigher() {
        String property = this.audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
        if (property == null) {
            return WebRtcAudioUtils.getDefaultSampleRateHz();
        }
        return Integer.parseInt(property);
    }

    public static synchronized boolean getStereoInput() {
        boolean z;
        synchronized (WebRtcAudioManager.class) {
            z = useStereoInput;
        }
        return z;
    }

    public static synchronized boolean getStereoOutput() {
        boolean z;
        synchronized (WebRtcAudioManager.class) {
            z = useStereoOutput;
        }
        return z;
    }

    private boolean hasEarpiece() {
        return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    private boolean init() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("init");
        outline107.append(WebRtcAudioUtils.getThreadInfo());
        Logging.d(TAG, outline107.toString());
        if (this.initialized) {
            return true;
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("audio mode is: ");
        outline1072.append(AUDIO_MODES[this.audioManager.getMode()]);
        Logging.d(TAG, outline1072.toString());
        this.initialized = true;
        this.volumeLogger.start();
        return true;
    }

    private static boolean isAcousticEchoCancelerSupported() {
        return WebRtcAudioEffects.canUseAcousticEchoCanceler();
    }

    private boolean isCommunicationModeEnabled() {
        return this.audioManager.getMode() == 3;
    }

    private boolean isDeviceBlacklistedForOpenSLESUsage() {
        boolean deviceIsBlacklistedForOpenSLESUsage = blacklistDeviceForOpenSLESUsageIsOverridden ? blacklistDeviceForOpenSLESUsage : WebRtcAudioUtils.deviceIsBlacklistedForOpenSLESUsage();
        if (deviceIsBlacklistedForOpenSLESUsage) {
            Logging.d(TAG, Build.MODEL + " is blacklisted for OpenSL ES usage!");
        }
        return deviceIsBlacklistedForOpenSLESUsage;
    }

    private boolean isLowLatencyOutputSupported() {
        return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.audio.low_latency") || WebRtcAudioUtils.isFlagFastRequestedViaNativeTrack();
    }

    private boolean isLowLatencyOutputSupportedWithoutOverride() {
        return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    private static boolean isNoiseSuppressorSupported() {
        return WebRtcAudioEffects.canUseNoiseSuppressor();
    }

    @TargetApi(23)
    private boolean isProAudioSupported() {
        return WebRtcAudioUtils.runningOnMarshmallowOrHigher() && ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.audio.pro");
    }

    private native void nativeCacheAudioParameters(int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i5, int i6, long j);

    private void reportLastUsedOpenSLPlayerHeadStallCount(int i) {
        Logging.d(TAG, "reportLastUsedOpenSLPlayerHeadStallCount:  " + i);
        WebRtcAudioUtils.addAudioRenderTrackUnderrunReport(i);
    }

    public static synchronized void setBlacklistDeviceForOpenSLESUsage(boolean z) {
        synchronized (WebRtcAudioManager.class) {
            blacklistDeviceForOpenSLESUsageIsOverridden = true;
            blacklistDeviceForOpenSLESUsage = z;
        }
    }

    public static synchronized void setStereoInput(boolean z) {
        synchronized (WebRtcAudioManager.class) {
            Logging.w(TAG, "Overriding default input behavior: setStereoInput(" + z + ')');
            useStereoInput = z;
        }
    }

    public static synchronized void setStereoOutput(boolean z) {
        synchronized (WebRtcAudioManager.class) {
            Logging.w(TAG, "Overriding default output behavior: setStereoOutput(" + z + ')');
            useStereoOutput = z;
        }
    }

    private void storeAudioParameters() {
        int i = 2;
        this.outputChannels = getStereoOutput() ? 2 : 1;
        if (!getStereoInput()) {
            i = 1;
        }
        this.inputChannels = i;
        this.inputSampleRate = getNativeInputSampleRate();
        this.outputSampleRate = getNativeOutputSampleRate();
        this.hardwareAEC = isAcousticEchoCancelerSupported();
        this.hardwareAGC = false;
        this.hardwareNS = isNoiseSuppressorSupported();
        this.lowLatencyOutput = isLowLatencyOutputSupported();
        this.lowLatencyInput = isLowLatencyInputSupported();
        this.proAudio = isProAudioSupported();
        this.outputBufferSize = getOutputBufferFrameSize(this.lowLatencyOutput);
        this.inputBufferSize = this.lowLatencyInput ? getLowLatencyInputFramesPerBuffer() : getMinInputFrameSize(this.inputSampleRate, this.inputChannels);
    }

    public boolean isLowLatencyInputSupported() {
        return WebRtcAudioUtils.runningOnLollipopOrHigher() && isLowLatencyOutputSupportedWithoutOverride();
    }
}
