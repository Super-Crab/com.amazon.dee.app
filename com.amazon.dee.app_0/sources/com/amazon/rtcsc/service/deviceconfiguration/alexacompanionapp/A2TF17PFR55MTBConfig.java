package com.amazon.rtcsc.service.deviceconfiguration.alexacompanionapp;

import android.os.Build;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.rtcsc.service.deviceconfiguration.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class A2TF17PFR55MTBConfig {
    private static final String FIREOS_WEBRTC_FIELD_TRIALS = "WebRTC-Rfc5389StunRetransmissions/Enabled/WebRTC-MTKH264/Enabled/WebRTC-MTKH264-LowLatency/Enabled/WebRTC-AVSync-AudioDelayEstimateMs/Enabled-300/";
    private static final String NON_FIREOS_WEBRTC_FIELD_TRIALS = "WebRTC-Rfc5389StunRetransmissions/Enabled/";
    private static final String a2TF17PFR55MTBConfig = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("{\"MaxWidth\": 1280,\"MaxHeight\": 720,\"MaxFramerate\": 30,\"MaxVideoKBitrate\": 2500,\"StartVideoKBitrate\": 900,\"StartAudioKBitrate\": 32,\"WebRTCFieldTrials\": \""), getWebRTCFieldTrials(), "\",\"PreferCamera1\": false,\"FrostingSupported\": true}");

    private static String generateNonFireOSFieldTrials() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WebRTC-Rfc5389StunRetransmissions/Enabled/");
        int i = Build.VERSION.SDK_INT;
        outline107.append("WebRTC-KIRINH264/Enabled-FpsAdjust/");
        outline107.append(DeviceConfigConstants.WEBRTC_FIELD_TRIAL_MTKH264_ENABLED_NOADJUST);
        if (Build.VERSION.SDK_INT >= 31) {
            outline107.append("WebRTC-TENSOREXYNOSH264/Enabled/");
        }
        return outline107.toString();
    }

    public static String getDeviceConfigInstance() {
        return a2TF17PFR55MTBConfig;
    }

    private static String getWebRTCFieldTrials() {
        return DeviceInfo.isAmazonDevice() ? FIREOS_WEBRTC_FIELD_TRIALS : generateNonFireOSFieldTrials();
    }
}
