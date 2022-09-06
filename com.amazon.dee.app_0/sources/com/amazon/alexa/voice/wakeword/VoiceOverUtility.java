package com.amazon.alexa.voice.wakeword;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import android.view.accessibility.AccessibilityManager;
import com.amazon.alexa.voice.metrics.VoxMetricEvent;
import com.amazon.alexa.voice.metrics.VoxMetricEventName;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
/* loaded from: classes11.dex */
public class VoiceOverUtility {
    private static final int ACCESSIBILITY_STREAM = 10;
    private final Context context;
    private final VoxMetricEventProcessingService voxMetricEventProcessingService;

    public VoiceOverUtility(Context context, VoxMetricEventProcessingService voxMetricEventProcessingService) {
        this.context = context;
        this.voxMetricEventProcessingService = voxMetricEventProcessingService;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"MissingPermission"})
    public boolean isExternalAudioDeviceConnected() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled() && defaultAdapter.getProfileConnectionState(1) == 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"PrivateApi"})
    public boolean isVoiceOverCurrentlySpeaking() {
        Boolean bool;
        try {
            bool = (Boolean) Class.forName("android.media.AudioSystem").getDeclaredMethod("isStreamActive", Integer.TYPE, Integer.TYPE).invoke(null, 10, 0);
        } catch (Exception e) {
            e.printStackTrace();
            this.voxMetricEventProcessingService.process(VoxMetricEvent.occurNow(VoxMetricEventName.VOX_CHECKING_ACCESSIBILITY_STREAM_FAILED));
            bool = false;
        }
        return bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isVoiceOverServiceEnabled() {
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.context.getSystemService("accessibility");
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return false;
        }
        return !accessibilityManager.getEnabledAccessibilityServiceList(1).isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isWiredHeadsetConnected() {
        AudioDeviceInfo[] devices;
        int i = Build.VERSION.SDK_INT;
        for (AudioDeviceInfo audioDeviceInfo : ((AudioManager) this.context.getSystemService("audio")).getDevices(3)) {
            if (audioDeviceInfo.getType() == 4 || audioDeviceInfo.getType() == 3) {
                return true;
            }
        }
        return false;
    }
}
