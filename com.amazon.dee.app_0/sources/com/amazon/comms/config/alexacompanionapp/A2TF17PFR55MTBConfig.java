package com.amazon.comms.config.alexacompanionapp;

import android.content.Context;
import android.os.Build;
import com.amazon.asp.AudioSignalProcessor;
import com.amazon.comms.calling.service.AudioConfig;
import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.amazon.comms.calling.service.PlatformVoIPSelection;
import com.amazon.comms.calling.service.VideoConstraints;
import com.amazon.comms.config.DeviceConfig;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.comms.config.util.DeviceInfo;
import com.amazon.comms.config.util.SimJobScheduler;
import com.amazon.comms.device.AlexaAppDeviceFacade;
import com.amazon.comms.device.AvsDeviceFacade;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.joda.time.DateTimeConstants;
/* loaded from: classes11.dex */
public class A2TF17PFR55MTBConfig extends DeviceConfig {
    private static final int ASP_CMD_GET_AFE_VOIP_SUPPORT = 121;
    private static final String FIREOS_WEBRTC_FIELD_TRIALS = "WebRTC-Rfc5389StunRetransmissions/Enabled/WebRTC-MTKH264/Enabled/WebRTC-MTKH264-LowLatency/Enabled/WebRTC-AVSync-AudioDelayEstimateMs/Enabled-300/WebRTC-Audio-SendSideBwe/Enabled/";
    private static A2TF17PFR55MTBConfig a2TF17PFR55MTBConfig;
    private static final Map<Integer, VideoConstraints> supportedVideoConstraints;

    static {
        TreeMap treeMap = new TreeMap();
        GeneratedOutlineSupport1.outline140(DeviceConfigConstants.VIDEO_WIDTH_320, 180, 15, treeMap, 3600);
        GeneratedOutlineSupport1.outline140(640, 360, 24, treeMap, 21600);
        GeneratedOutlineSupport1.outline140(640, 360, 30, treeMap, 27600);
        GeneratedOutlineSupport1.outline140(960, 540, 24, treeMap, 48600);
        GeneratedOutlineSupport1.outline140(960, 540, 30, treeMap, 61200);
        GeneratedOutlineSupport1.outline140(1280, 720, 24, treeMap, Integer.valueOf((int) DateTimeConstants.SECONDS_PER_DAY));
        GeneratedOutlineSupport1.outline140(1280, 720, 30, treeMap, 108000);
        supportedVideoConstraints = Collections.unmodifiableMap(treeMap);
        a2TF17PFR55MTBConfig = new A2TF17PFR55MTBConfig();
    }

    private static String generateNonFireOSFieldTrials() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(DeviceConfigConstants.WEBRTC_FIELD_TRIAL_RFC5389STUNRETRANSMISSIONS_ENABLED);
        int i = Build.VERSION.SDK_INT;
        outline107.append("WebRTC-KIRINH264/Enabled-FpsAdjust/");
        outline107.append(DeviceConfigConstants.WEBRTC_FIELD_TRIAL_MTKH264_ENABLED_NOADJUST);
        if (Build.VERSION.SDK_INT >= 31) {
            outline107.append("WebRTC-TENSOREXYNOSH264/Enabled/");
        }
        outline107.append(DeviceConfigConstants.WEBRTC_FIELD_TRIAL_AUDIO_SENDSIDEBWE_ENABLED);
        return outline107.toString();
    }

    private static List<String> getBlockedAecDevicesList() {
        return Arrays.asList("Nexus 6P");
    }

    public static A2TF17PFR55MTBConfig getDeviceConfigInstance() {
        return a2TF17PFR55MTBConfig;
    }

    private static List<String> getPlatformVoIPSelectionDevicesList() {
        return Arrays.asList("KFMUWI", "KFMAWI", "KFONWI", "KFTRWI", "KFTRPWI", "KFQUWI", "KFRAWI", "KFRAPWI");
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean captureToTexture() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getAudioStartBitrateKbps() {
        return 32;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public AvsDeviceFacade getAvsDeviceFacade(Context context) {
        return new AlexaAppDeviceFacade(context);
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public DynamicAcousticParams.ConfigPath getDynamicAcousticParamsConfigPath() {
        if (DeviceInfo.isAmazonDevice() && getPlatformVoIPSelectionDevicesList().contains(Build.MODEL) && isPlatformAFEVoIPSupported()) {
            return DeviceConfigConstants.DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH_LIBASP;
        }
        return DeviceConfigConstants.DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH_NONE;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public AudioConfig.FastRenderPath getFastAudioRenderPath() {
        return DeviceConfigConstants.FAST_RENDER_PATH_NONE;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public SimJobScheduler getJobScheduler(Context context) {
        return null;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public String getLocalSurfaceViewShape() {
        return "rectangle";
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public VideoConstraints getMaxVideoConstraintsToRequestFromRemote() {
        return new VideoConstraints(1280, 800, 30);
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getNonH264VideoFps() {
        return 24;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getNonH264VideoHeight() {
        return 180;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getNonH264VideoWidth() {
        return DeviceConfigConstants.VIDEO_WIDTH_320;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public PlatformVoIPSelection getPlatformVoIPSelection() {
        if (!DeviceInfo.isAmazonDevice() || !getPlatformVoIPSelectionDevicesList().contains(Build.MODEL) || !isPlatformAFEVoIPSupported()) {
            return null;
        }
        return new PlatformVoIPSelection(true, false);
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public String getRemoteSurfaceViewShape() {
        return "rectangle";
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public Map<Integer, VideoConstraints> getSupportedResolutions() {
        return supportedVideoConstraints;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public String getUILauncherName() {
        return null;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public String getUIMainActivity() {
        return null;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public String getUINotificationManagerName() {
        return null;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoFps() {
        return 30;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoHeight() {
        return 720;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoMaxBitrate() {
        return 2500;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoStartBitrate() {
        return 900;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getVideoWidth() {
        return 1280;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public String getWebRTCFieldTrials() {
        return DeviceInfo.isAmazonDevice() ? FIREOS_WEBRTC_FIELD_TRIALS : generateNonFireOSFieldTrials();
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getWebRTCStatsLoggingFrequency() {
        return DeviceConfigConstants.WEBRTC_STATS_LOGGING_FREQUENCY_14000_MS;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public int getWebRTCStatsPollingFrequency() {
        return 2000;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean hasBuiltInSpeaker() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean ignoreCameraEvictionError() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean isAudioCaptureSampleRateOverriden() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean isAudioRenderSampleRateOverriden() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean isCameraShutterPresent() {
        return false;
    }

    @VisibleForTesting
    boolean isPlatformAFEVoIPSupported() {
        try {
            byte[] bArr = {0};
            if (AudioSignalProcessor.getInstance().command(121, new byte[0], bArr) != 0) {
                DeviceConfig.log.e("ASP command failed, cmdCode: %d", 121);
            } else if (bArr[0] == 0) {
                DeviceConfig.log.i("ASP command succeeded, cmdCode: %d", 121);
                DeviceConfig.log.w("ASP VoIP is not supported");
            } else {
                DeviceConfig.log.i("ASP command succeeded, cmdCode: %d", 121);
                DeviceConfig.log.i("ASP VoIP is supported");
                return true;
            }
        } catch (Exception e) {
            CommsLogger commsLogger = DeviceConfig.log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception on ASP comamnd: ");
            outline107.append(e.getMessage());
            commsLogger.e(outline107.toString());
        }
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean isPresencePublishCapable() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean isRealTimeTextCapable() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean isWebRTCAcousticEchoCancelerPreferred() {
        return getBlockedAecDevicesList().contains(Build.MODEL);
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean preferCamera1API() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean processParkedCallToken() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean provideCallingServiceHalParameter() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean shouldConstraintVideoBeforeIceConnection() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean shouldEnableAMZNBAudioFilter() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean simulateFirstFrameReceived() {
        return false;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean supportsHints() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean supportsInsights(Context context) {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean updateCameraHalFramerateAllowed() {
        return false;
    }
}
