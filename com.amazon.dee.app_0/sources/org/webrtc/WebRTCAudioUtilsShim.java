package org.webrtc;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.util.Log;
import java.util.List;
import org.webrtc.voiceengine.WebRtcAudioManager;
import org.webrtc.voiceengine.WebRtcAudioUtils;
/* loaded from: classes5.dex */
public class WebRTCAudioUtilsShim {
    private static final String TAG = "WebRTCAudioUtilsShim";

    private static int getDefaultPlatformSupportedSampleRate(AudioManager audioManager, boolean z, int i) {
        String property;
        return (!z && (property = audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE")) != null) ? Integer.parseInt(property) : i;
    }

    public static List<Integer> readAndResetUnderrunCounts() {
        int i = Build.VERSION.SDK_INT;
        return WebRtcAudioUtils.readAndResetUnderrunCounts();
    }

    public static void requestFlagFastIfPossible(boolean z) {
        String str = TAG;
        Log.i(str, "requestFlagFastIfPossible. viaAudioTrack= " + z);
        WebRtcAudioUtils.requestFlagFastIfPossible(z);
        if (!z) {
            WebRtcAudioManager.setBlacklistDeviceForOpenSLESUsage(false);
        }
    }

    public static void setAudioCaptureRenderSampleRate(Context context, boolean z, int i, boolean z2, int i2) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        int defaultPlatformSupportedSampleRate = getDefaultPlatformSupportedSampleRate(audioManager, z, i);
        int defaultPlatformSupportedSampleRate2 = getDefaultPlatformSupportedSampleRate(audioManager, z2, i2);
        String str = TAG;
        Log.i(str, "Record_sample_rate: " + defaultPlatformSupportedSampleRate + " render_sample_rate: " + defaultPlatformSupportedSampleRate2);
        WebRtcAudioUtils.setDefaultInputOutputSampleRateHz(defaultPlatformSupportedSampleRate, defaultPlatformSupportedSampleRate2);
    }

    public static void setOutputAudioDevice(int i) {
        WebRtcAudioUtils.setOutputAudioDevice(i);
    }

    public static void setRenderTrackBufferSize(int i) {
        String str = TAG;
        Log.i(str, "setRenderTrackBufferSize. bufferSizeInBytes= " + i);
        WebRtcAudioUtils.setRenderTrackBufferSize(i);
    }
}
