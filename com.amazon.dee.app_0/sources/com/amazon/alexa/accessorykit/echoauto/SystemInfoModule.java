package com.amazon.alexa.accessorykit.echoauto;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessorykit.R;
import com.amazon.alexa.accessorykit.audiodelay.AudioDelayUtils;
import com.amazon.alexa.accessorykit.echoauto.MonitoredMediaPlayerWrapper;
import com.amazon.alexa.accessorykit.echoauto.PlaybackLatencyMeasurementThread;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public class SystemInfoModule extends ReactContextBaseJavaModule {
    private static final String CALIBRATION_STATUS_KEY = "status";
    public static final String MODULE_NAME = "SystemInfo";
    private static final String PLAYBACK_PROGRESS_EVENT_NAME = "test-sound-playback-progress";
    private static final String PLAYBACK_PROGRESS_KEY = "progressPercent";
    private static final String PLAYBACK_STOPPED_EVENT_NAME = "test-sound-playback-stopped";
    private static final String TEST_SOUND_CALIBRATION_STATUS_EVENT_NAME = "test-sound-calibration-status";
    private LatencyCalibrationRunnable latencyCalibrationRunnable;
    private MonitoredMediaPlayerWrapper mediaPlayer;
    private boolean mediaPlayerShouldLoop;
    private ReactApplicationContext reactApplicationContext;

    /* loaded from: classes6.dex */
    private class LatencyCalibrationRunnable implements Runnable {
        private static final long FINAL_LATENCY_OFFSET = -1550;
        private static final int MAX_FAILURES = 2;
        private static final int MAX_ITERATIONS = 3;
        private static final int MIN_ITERATIONS = 2;
        private static final String TAG = "LatencyCalibrationRunnable";
        private MonitoredMediaPlayerWrapper calibrationMediaPlayer;
        private List<Long> collectedLatencyData;
        private int currentIteration;
        private int failureCount;
        private volatile boolean isStopRequested = false;
        private volatile boolean isTimeoutFailure = false;
        private final float[] frequencies = {16975.0f};
        private final double[] thresholds = {100.0d};
        private PlaybackLatencyMeasurementThread currentMeasurementThread = null;
        private final AudioPlayer audioPlayerInterface = new AudioPlayer() { // from class: com.amazon.alexa.accessorykit.echoauto.SystemInfoModule.LatencyCalibrationRunnable.1
            @Override // com.amazon.alexa.accessorykit.echoauto.AudioPlayer
            public double duration() {
                return LatencyCalibrationRunnable.this.calibrationMediaPlayer != null ? LatencyCalibrationRunnable.this.calibrationMediaPlayer.getDuration() : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            }

            @Override // com.amazon.alexa.accessorykit.echoauto.AudioPlayer
            public void release() {
            }

            @Override // com.amazon.alexa.accessorykit.echoauto.AudioPlayer
            public void start() {
                if (LatencyCalibrationRunnable.this.calibrationMediaPlayer != null) {
                    LatencyCalibrationRunnable.this.calibrationMediaPlayer.play();
                }
            }

            @Override // com.amazon.alexa.accessorykit.echoauto.AudioPlayer
            public void stop() {
                if (LatencyCalibrationRunnable.this.calibrationMediaPlayer != null) {
                    LatencyCalibrationRunnable.this.calibrationMediaPlayer.stop();
                }
            }
        };
        private final PlaybackLatencyMeasurementThread.Callback measurementCallback = new PlaybackLatencyMeasurementThread.Callback() { // from class: com.amazon.alexa.accessorykit.echoauto.SystemInfoModule.LatencyCalibrationRunnable.2
            @Override // com.amazon.alexa.accessorykit.echoauto.PlaybackLatencyMeasurementThread.Callback
            public void onLatencyMeasured(long j) {
                LatencyCalibrationRunnable.this.collectedLatencyData.add(Long.valueOf(j));
            }

            @Override // com.amazon.alexa.accessorykit.echoauto.PlaybackLatencyMeasurementThread.Callback
            public void timedOut() {
                LatencyCalibrationRunnable.access$208(LatencyCalibrationRunnable.this);
                if (LatencyCalibrationRunnable.this.failureCount >= 2) {
                    Logger.d("LatencyCalibrationRunnable latency measurement timed out");
                    LatencyCalibrationRunnable.this.isTimeoutFailure = true;
                    LatencyCalibrationRunnable.this.isStopRequested = true;
                }
            }
        };

        LatencyCalibrationRunnable(@NonNull MonitoredMediaPlayerWrapper monitoredMediaPlayerWrapper) {
            this.calibrationMediaPlayer = monitoredMediaPlayerWrapper;
        }

        static /* synthetic */ int access$208(LatencyCalibrationRunnable latencyCalibrationRunnable) {
            int i = latencyCalibrationRunnable.failureCount;
            latencyCalibrationRunnable.failureCount = i + 1;
            return i;
        }

        private long computeLatencyFromData() {
            List<Long> list = this.collectedLatencyData;
            if (list == null || list.size() < 3) {
                return -1L;
            }
            this.collectedLatencyData.remove(0);
            double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            double d2 = 0.0d;
            for (Long l : this.collectedLatencyData) {
                d2 += l.longValue();
            }
            double size = (d2 / this.collectedLatencyData.size()) - 1550.0d;
            if (size >= FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                d = size;
            }
            return Double.valueOf(Math.ceil(d)).longValue();
        }

        private boolean processLatencyData() {
            try {
                long computeLatencyFromData = computeLatencyFromData();
                if (computeLatencyFromData < 0) {
                    return false;
                }
                AudioDelayUtils.writeAudioDelayToConfigFile(Long.valueOf(computeLatencyFromData).intValue(), SystemInfoModule.this.getReactApplicationContext());
                return true;
            } catch (IOException e) {
                Logger.e("LatencyCalibrationRunnableerror writing computed latency to storage", e);
                return false;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            LatencyCalibrationStatus latencyCalibrationStatus;
            this.isTimeoutFailure = false;
            this.isStopRequested = false;
            this.currentIteration = 1;
            this.failureCount = 0;
            this.collectedLatencyData = new ArrayList(3);
            SystemInfoModule.this.emitCalibrationEvent(LatencyCalibrationStatus.CALIBRATING);
            while (!this.isStopRequested && this.currentIteration <= 3) {
                this.currentMeasurementThread = new PlaybackLatencyMeasurementThread(this.audioPlayerInterface, this.frequencies, this.thresholds, this.measurementCallback);
                this.currentMeasurementThread.start();
                try {
                    this.currentMeasurementThread.join();
                    this.calibrationMediaPlayer.seekTo(0);
                    this.currentIteration++;
                } catch (InterruptedException e) {
                    Logger.d("LatencyCalibrationRunnablethread interrupted while waiting for measurement thread join", e);
                    stopCalibration();
                }
            }
            if (this.isTimeoutFailure) {
                latencyCalibrationStatus = LatencyCalibrationStatus.FAILED;
            } else if (this.isStopRequested) {
                latencyCalibrationStatus = LatencyCalibrationStatus.STOPPED;
            } else {
                latencyCalibrationStatus = processLatencyData() ? LatencyCalibrationStatus.SUCCEEDED : LatencyCalibrationStatus.FAILED;
            }
            SystemInfoModule.this.emitCalibrationEvent(latencyCalibrationStatus);
            MonitoredMediaPlayerWrapper monitoredMediaPlayerWrapper = this.calibrationMediaPlayer;
            if (monitoredMediaPlayerWrapper != null) {
                monitoredMediaPlayerWrapper.release();
                this.calibrationMediaPlayer = null;
            }
        }

        public void stopCalibration() {
            this.isStopRequested = true;
            PlaybackLatencyMeasurementThread playbackLatencyMeasurementThread = this.currentMeasurementThread;
            if (playbackLatencyMeasurementThread != null) {
                playbackLatencyMeasurementThread.stopThread();
            }
        }
    }

    /* loaded from: classes6.dex */
    private enum LatencyCalibrationStatus {
        STOPPED(0),
        CALIBRATING(1),
        SUCCEEDED(2),
        FAILED(3);
        
        private final int value;

        LatencyCalibrationStatus(int i) {
            this.value = i;
        }
    }

    public SystemInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mediaPlayer = null;
        this.mediaPlayerShouldLoop = false;
        this.reactApplicationContext = reactApplicationContext;
    }

    private MonitoredMediaPlayerWrapper createCalibrationMediaPlayer() {
        MonitoredMediaPlayerWrapper monitoredMediaPlayerWrapper = new MonitoredMediaPlayerWrapper(this.reactApplicationContext, R.raw.med_state_ir_calibration_16975hz_1350ms_silence, null);
        monitoredMediaPlayerWrapper.shouldLoopPlayback = false;
        return monitoredMediaPlayerWrapper;
    }

    private MonitoredMediaPlayerWrapper createMediaPlayer() {
        MonitoredMediaPlayerWrapper monitoredMediaPlayerWrapper = new MonitoredMediaPlayerWrapper(this.reactApplicationContext, R.raw.med_state_ir_calibration_withfades, new MonitoredMediaPlayerWrapper.PlaybackProgressCallbackInterface() { // from class: com.amazon.alexa.accessorykit.echoauto.SystemInfoModule.1
            @Override // com.amazon.alexa.accessorykit.echoauto.MonitoredMediaPlayerWrapper.PlaybackProgressCallbackInterface
            public void onPlaybackPercentageChange(Double d) {
                WritableMap createMap = Arguments.createMap();
                createMap.putDouble(SystemInfoModule.PLAYBACK_PROGRESS_KEY, d.doubleValue());
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) SystemInfoModule.this.reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(SystemInfoModule.PLAYBACK_PROGRESS_EVENT_NAME, createMap);
            }

            @Override // com.amazon.alexa.accessorykit.echoauto.MonitoredMediaPlayerWrapper.PlaybackProgressCallbackInterface
            public void onPlaybackStopped() {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) SystemInfoModule.this.reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(SystemInfoModule.PLAYBACK_STOPPED_EVENT_NAME, null);
            }
        });
        monitoredMediaPlayerWrapper.shouldLoopPlayback = this.mediaPlayerShouldLoop;
        return monitoredMediaPlayerWrapper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emitCalibrationEvent(LatencyCalibrationStatus latencyCalibrationStatus) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("status", latencyCalibrationStatus.ordinal());
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(TEST_SOUND_CALIBRATION_STATUS_EVENT_NAME, createMap);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map<String, Object> getConstants() {
        HashMap outline134 = GeneratedOutlineSupport1.outline134("testSoundProgressEventName", PLAYBACK_PROGRESS_EVENT_NAME, "testSoundProgressKey", PLAYBACK_PROGRESS_KEY);
        outline134.put("testSoundPlaybackStoppedEventName", PLAYBACK_STOPPED_EVENT_NAME);
        outline134.put("testSoundCalibrationStatusEventName", TEST_SOUND_CALIBRATION_STATUS_EVENT_NAME);
        return outline134;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void navigateToBluetoothSettings(Promise promise) {
        Logger.d(MODULE_NAME, "navigateToBluetoothSettings()");
        Intent intent = new Intent("android.settings.BLUETOOTH_SETTINGS");
        intent.setFlags(268435456);
        this.reactApplicationContext.startActivity(intent);
        promise.resolve(true);
    }

    @ReactMethod
    public boolean pauseTestSound() {
        Logger.d(MODULE_NAME, "pauseTestSound()");
        try {
            if (this.mediaPlayer == null) {
                return false;
            }
            this.mediaPlayer.pause();
            return true;
        } catch (IllegalStateException e) {
            Logger.e(MODULE_NAME, "Error while trying to pause media", e);
            return false;
        }
    }

    @ReactMethod
    public boolean playTestSound() {
        Logger.d(MODULE_NAME, "playTestSound()");
        try {
            if (this.mediaPlayer == null) {
                this.mediaPlayer = createMediaPlayer();
            }
            this.mediaPlayer.play();
            return true;
        } catch (IllegalStateException e) {
            Logger.e(MODULE_NAME, "Error while trying to play media", e);
            return false;
        }
    }

    @ReactMethod
    public boolean setDefaultLatency(int i) {
        Logger.d(MODULE_NAME, "setDefaultLatency()");
        try {
            AudioDelayUtils.writeAudioDelayToConfigFile(i, getReactApplicationContext());
            return true;
        } catch (IOException e) {
            Logger.e(MODULE_NAME, "Error writing default latency to storage", e);
            return false;
        }
    }

    @ReactMethod
    public void setTestSoundLooping(boolean z) {
        Object[] objArr = new Object[1];
        Object[] objArr2 = new Object[1];
        objArr2[0] = z ? "true" : PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE;
        objArr[0] = String.format("setTestSoundLooping(%s)", objArr2);
        Logger.d(MODULE_NAME, objArr);
        MonitoredMediaPlayerWrapper monitoredMediaPlayerWrapper = this.mediaPlayer;
        if (monitoredMediaPlayerWrapper != null) {
            monitoredMediaPlayerWrapper.shouldLoopPlayback = z;
        }
        this.mediaPlayerShouldLoop = z;
    }

    @ReactMethod
    public void startCalibration(Promise promise) {
        Logger.d(MODULE_NAME, "startCalibration()");
        LatencyCalibrationRunnable latencyCalibrationRunnable = this.latencyCalibrationRunnable;
        if (latencyCalibrationRunnable != null) {
            latencyCalibrationRunnable.stopCalibration();
        }
        this.latencyCalibrationRunnable = new LatencyCalibrationRunnable(createCalibrationMediaPlayer());
        new Thread(this.latencyCalibrationRunnable).start();
        promise.resolve(true);
    }

    @ReactMethod
    public void stopCalibration(Promise promise) {
        Logger.d(MODULE_NAME, "stopCalibration()");
        LatencyCalibrationRunnable latencyCalibrationRunnable = this.latencyCalibrationRunnable;
        if (latencyCalibrationRunnable != null) {
            latencyCalibrationRunnable.stopCalibration();
            this.latencyCalibrationRunnable = null;
            promise.resolve(true);
            return;
        }
        promise.resolve(false);
    }
}
