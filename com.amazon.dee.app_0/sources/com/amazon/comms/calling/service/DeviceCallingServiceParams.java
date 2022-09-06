package com.amazon.comms.calling.service;

import com.amazon.comms.calling.service.AudioConfig;
import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.amazon.comms.calling.service.MediaParams;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.device.AvsDeviceFacade;
import com.amazon.comms.util.Size;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* loaded from: classes11.dex */
public final class DeviceCallingServiceParams {
    private static final int AUDIO_RENDER_BUFFER_SIZE_DEFAULT = -1;
    private static final int DEFAULT_SAMPLE_RATE = 48000;
    private static final int NHD_MAX_VIDEO_FPS = 24;
    private static final int NHD_VIDEO_HEIGHT = 360;
    private static final int NHD_VIDEO_WIDTH = 640;
    private static final int STATS_POLLING_FREQUENCY = 15000;
    private final AcousticParams acousticParams;
    private final AudioConfig audioCaptureAndRenderConfig;
    private int audioStartBitrateInKbps;
    private final AvsDeviceFacade avsDeviceFacade;
    private final String buildVersion;
    private final boolean builtInSpeakerPresent;
    private final boolean cameraShutterPresent;
    private boolean constraintVideoUntilIceConnection;
    private final DynamicAcousticParams.ConfigPath dynamicAcousticParamsConfigPath;
    private final boolean enableCameraMetricsReporting;
    private final boolean forceReceiveOnlyVideo;
    private VideoConstraints maxVideoConstraintsOnReducedResolution;
    private final VideoConstraints maxVideoConstraintsToRequestFromRemote;
    private final MediaParams mediaParams;
    private final PlatformVoIPSelection platformVoIPSelection;
    private final boolean presencePublishCapable;
    private final boolean realTimeTextCapable;
    private final boolean reduceVideoResolutionOnNoH264Remote;
    private final SipHeaders registrationHeaders;
    private final boolean relayOnlyIceTransport;
    private final boolean renderRemoteVideoSupported;
    private final boolean requireCallAcknowledgement;
    private final String screenShape;
    private final Size screenSize;
    private final Map<Integer, VideoConstraints> supportedVideoResolutions;
    private final boolean translucentRemoteVideoRendererSupported;
    private final boolean useAlarmManagerAuthTokenManager;
    private final boolean useTextureViewForRendering;
    private final String userAgentStackVersionCode;
    private final String userAgentStackVersionCodePrepend;
    private final VideoConfiguration videoConfiguration;
    private VideoConstraints videoConstraintsUntilIceConnection;
    private final String videoFileInjectionPath;
    private final boolean webRTCAcousticEchoCancelerPreferred;
    private final String webRTCFieldTrials;
    private final int webRTCStatsLoggingFrequency;
    private final int webRTCStatsPollingFrequency;

    /* loaded from: classes11.dex */
    public static class DeviceCallingServiceParamsBuilder {
        private AudioConfig audioCaptureAndRenderConfig;
        private AvsDeviceFacade avsDeviceFacade;
        private DynamicAcousticParams.ConfigPath dynamicAcousticParamsConfigPath;
        private DynamicAcousticParams.ConfigPath dynamicMediaConfigPath;
        private boolean enableCameraMetricsReporting;
        private VideoConstraints maxVideoConstraintsToRequestFromRemote;
        private MediaParams mediaParams;
        private PlatformVoIPSelection platformVoIPSelection;
        private boolean presencePublishCapable;
        private boolean realTimeTextCapable;
        private Map<Integer, VideoConstraints> supportedVideoResolutions;
        private boolean translucentRemoteVideoRendererSupported;
        private boolean translucentRemoteVideoRendererSupported$set;
        private boolean useAlarmManagerAuthTokenManager;
        private boolean useTextureViewForRendering;
        private boolean useTextureViewForRendering$set;
        private String userAgentStackVersionCode;
        private String userAgentStackVersionCodePrepend;
        private VideoConfiguration videoConfiguration;
        private VideoConstraints videoConstraintsUntilIceConnection;
        private String videoFileInjectionPath;
        private boolean webRTCAcousticEchoCancelerPreferred;
        private String webRTCFieldTrials;
        private int webRTCStatsLoggingFrequency;
        private int webRTCStatsPollingFrequency;
        private AcousticParams acousticParams = AcousticParams.enabled();
        private boolean relayOnlyIceTransport = false;
        private boolean requireCallAcknowledgement = false;
        private boolean builtInSpeakerPresent = true;
        private boolean cameraShutterPresent = false;
        private boolean renderRemoteVideoSupported = false;
        private boolean forceReceiveOnlyVideo = false;
        private String screenShape = "";
        private Size screenSize = null;
        private String buildVersion = null;
        private int audioStartBitrateInKbps = 20;
        private boolean reduceVideoResolutionOnNoH264Remote = false;
        private boolean constraintVideoUntilIceConnection = false;
        private VideoConstraints maxVideoConstraintsOnReducedResolution = new VideoConstraints(640, 360, 24);
        private SipHeaders registrationHeaders = new SipHeaders();

        DeviceCallingServiceParamsBuilder() {
            MediaParams.LastFrameClearOption lastFrameClearOption = MediaParams.LastFrameClearOption.NONE;
            this.mediaParams = new MediaParams(lastFrameClearOption, lastFrameClearOption, 0.0f);
            this.webRTCFieldTrials = null;
            this.webRTCStatsPollingFrequency = 15000;
            this.webRTCStatsLoggingFrequency = -1;
            this.webRTCAcousticEchoCancelerPreferred = false;
            this.audioCaptureAndRenderConfig = new AudioConfig(false, 48000, false, 48000, AudioConfig.FastRenderPath.NONE, -1);
            this.presencePublishCapable = true;
            this.useAlarmManagerAuthTokenManager = false;
            this.dynamicMediaConfigPath = DynamicAcousticParams.ConfigPath.NONE;
            this.realTimeTextCapable = false;
            this.enableCameraMetricsReporting = false;
            this.platformVoIPSelection = null;
        }

        public DeviceCallingServiceParamsBuilder acousticParams(AcousticParams acousticParams) {
            this.acousticParams = acousticParams;
            return this;
        }

        public DeviceCallingServiceParamsBuilder audioCaptureAndRenderConfig(AudioConfig audioConfig) {
            this.audioCaptureAndRenderConfig = audioConfig;
            return this;
        }

        public DeviceCallingServiceParamsBuilder audioStartBitrateInKbps(int i) {
            this.audioStartBitrateInKbps = i;
            return this;
        }

        public DeviceCallingServiceParamsBuilder avsDeviceFacade(AvsDeviceFacade avsDeviceFacade) {
            this.avsDeviceFacade = avsDeviceFacade;
            return this;
        }

        public DeviceCallingServiceParams build() {
            return new DeviceCallingServiceParams(this.acousticParams, this.avsDeviceFacade, this.requireCallAcknowledgement, this.relayOnlyIceTransport, this.reduceVideoResolutionOnNoH264Remote, this.maxVideoConstraintsOnReducedResolution, this.constraintVideoUntilIceConnection, this.videoConstraintsUntilIceConnection, this.audioStartBitrateInKbps, this.registrationHeaders, this.mediaParams, this.webRTCFieldTrials, this.videoConfiguration, this.supportedVideoResolutions, this.maxVideoConstraintsToRequestFromRemote, this.webRTCStatsPollingFrequency, this.webRTCStatsLoggingFrequency, this.webRTCAcousticEchoCancelerPreferred, this.audioCaptureAndRenderConfig, this.builtInSpeakerPresent, this.cameraShutterPresent, this.renderRemoteVideoSupported, this.forceReceiveOnlyVideo, this.buildVersion, this.screenShape, this.screenSize, this.presencePublishCapable, this.userAgentStackVersionCodePrepend, this.userAgentStackVersionCode, this.useAlarmManagerAuthTokenManager, this.dynamicAcousticParamsConfigPath, this.realTimeTextCapable, this.enableCameraMetricsReporting, this.videoFileInjectionPath, this.platformVoIPSelection, this.useTextureViewForRendering$set ? this.useTextureViewForRendering : DeviceCallingServiceParams.$default$useTextureViewForRendering(), this.translucentRemoteVideoRendererSupported$set ? this.translucentRemoteVideoRendererSupported : DeviceCallingServiceParams.$default$translucentRemoteVideoRendererSupported());
        }

        public DeviceCallingServiceParamsBuilder buildVersion(String str) {
            this.buildVersion = str;
            return this;
        }

        public DeviceCallingServiceParamsBuilder builtInSpeakerPresent(boolean z) {
            this.builtInSpeakerPresent = z;
            return this;
        }

        public DeviceCallingServiceParamsBuilder cameraShutterPresent(boolean z) {
            this.cameraShutterPresent = z;
            return this;
        }

        public DeviceCallingServiceParamsBuilder constraintVideoUntilIceConnection(boolean z) {
            this.constraintVideoUntilIceConnection = z;
            return this;
        }

        public DeviceCallingServiceParamsBuilder dynamicAcousticParamsConfigPath(DynamicAcousticParams.ConfigPath configPath) {
            this.dynamicAcousticParamsConfigPath = configPath;
            return this;
        }

        public DeviceCallingServiceParamsBuilder enableCameraMetricsReporting(boolean z) {
            this.enableCameraMetricsReporting = z;
            return this;
        }

        public DeviceCallingServiceParamsBuilder forceReceiveOnlyVideo(boolean z) {
            this.forceReceiveOnlyVideo = z;
            return this;
        }

        public DeviceCallingServiceParamsBuilder maxVideoConstraintsOnReducedResolution(VideoConstraints videoConstraints) {
            this.maxVideoConstraintsOnReducedResolution = videoConstraints;
            return this;
        }

        public DeviceCallingServiceParamsBuilder maxVideoConstraintsToRequestFromRemote(VideoConstraints videoConstraints) {
            this.maxVideoConstraintsToRequestFromRemote = videoConstraints;
            return this;
        }

        public DeviceCallingServiceParamsBuilder mediaParams(MediaParams mediaParams) {
            this.mediaParams = mediaParams;
            return this;
        }

        public DeviceCallingServiceParamsBuilder platformVoIPSelection(PlatformVoIPSelection platformVoIPSelection) {
            this.platformVoIPSelection = platformVoIPSelection;
            return this;
        }

        public DeviceCallingServiceParamsBuilder presencePublishCapable(boolean z) {
            this.presencePublishCapable = z;
            return this;
        }

        public DeviceCallingServiceParamsBuilder realTimeTextCapable(boolean z) {
            this.realTimeTextCapable = z;
            return this;
        }

        public DeviceCallingServiceParamsBuilder reduceVideoResolutionOnNoH264Remote(boolean z) {
            this.reduceVideoResolutionOnNoH264Remote = z;
            return this;
        }

        public DeviceCallingServiceParamsBuilder registrationHeaders(SipHeaders sipHeaders) {
            this.registrationHeaders = sipHeaders;
            return this;
        }

        public DeviceCallingServiceParamsBuilder relayOnlyIceTransport(boolean z) {
            this.relayOnlyIceTransport = z;
            return this;
        }

        public DeviceCallingServiceParamsBuilder renderRemoteVideoSupported(boolean z) {
            this.renderRemoteVideoSupported = z;
            return this;
        }

        public DeviceCallingServiceParamsBuilder requireCallAcknowledgement(boolean z) {
            this.requireCallAcknowledgement = z;
            return this;
        }

        public DeviceCallingServiceParamsBuilder screenShape(String str) {
            this.screenShape = str;
            return this;
        }

        public DeviceCallingServiceParamsBuilder screenSize(Size size) {
            this.screenSize = size;
            return this;
        }

        public DeviceCallingServiceParamsBuilder supportedVideoResolutions(Map<Integer, VideoConstraints> map) {
            this.supportedVideoResolutions = map;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceCallingServiceParams.DeviceCallingServiceParamsBuilder(acousticParams=");
            outline107.append(this.acousticParams);
            outline107.append(", avsDeviceFacade=");
            outline107.append(this.avsDeviceFacade);
            outline107.append(", requireCallAcknowledgement=");
            outline107.append(this.requireCallAcknowledgement);
            outline107.append(", relayOnlyIceTransport=");
            outline107.append(this.relayOnlyIceTransport);
            outline107.append(", reduceVideoResolutionOnNoH264Remote=");
            outline107.append(this.reduceVideoResolutionOnNoH264Remote);
            outline107.append(", maxVideoConstraintsOnReducedResolution=");
            outline107.append(this.maxVideoConstraintsOnReducedResolution);
            outline107.append(", constraintVideoUntilIceConnection=");
            outline107.append(this.constraintVideoUntilIceConnection);
            outline107.append(", videoConstraintsUntilIceConnection=");
            outline107.append(this.videoConstraintsUntilIceConnection);
            outline107.append(", audioStartBitrateInKbps=");
            outline107.append(this.audioStartBitrateInKbps);
            outline107.append(", registrationHeaders=");
            outline107.append(this.registrationHeaders);
            outline107.append(", mediaParams=");
            outline107.append(this.mediaParams);
            outline107.append(", webRTCFieldTrials=");
            outline107.append(this.webRTCFieldTrials);
            outline107.append(", videoConfiguration=");
            outline107.append(this.videoConfiguration);
            outline107.append(", supportedVideoResolutions=");
            outline107.append(this.supportedVideoResolutions);
            outline107.append(", maxVideoConstraintsToRequestFromRemote=");
            outline107.append(this.maxVideoConstraintsToRequestFromRemote);
            outline107.append(", webRTCStatsPollingFrequency=");
            outline107.append(this.webRTCStatsPollingFrequency);
            outline107.append(", webRTCStatsLoggingFrequency=");
            outline107.append(this.webRTCStatsLoggingFrequency);
            outline107.append(", webRTCAcousticEchoCancelerPreferred=");
            outline107.append(this.webRTCAcousticEchoCancelerPreferred);
            outline107.append(", audioCaptureAndRenderConfig=");
            outline107.append(this.audioCaptureAndRenderConfig);
            outline107.append(", builtInSpeakerPresent=");
            outline107.append(this.builtInSpeakerPresent);
            outline107.append(", cameraShutterPresent=");
            outline107.append(this.cameraShutterPresent);
            outline107.append(", renderRemoteVideoSupported=");
            outline107.append(this.renderRemoteVideoSupported);
            outline107.append(", forceReceiveOnlyVideo=");
            outline107.append(this.forceReceiveOnlyVideo);
            outline107.append(", buildVersion=");
            outline107.append(this.buildVersion);
            outline107.append(", screenShape=");
            outline107.append(this.screenShape);
            outline107.append(", screenSize=");
            outline107.append(this.screenSize);
            outline107.append(", presencePublishCapable=");
            outline107.append(this.presencePublishCapable);
            outline107.append(", userAgentStackVersionCodePrepend=");
            outline107.append(this.userAgentStackVersionCodePrepend);
            outline107.append(", userAgentStackVersionCode=");
            outline107.append(this.userAgentStackVersionCode);
            outline107.append(", useAlarmManagerAuthTokenManager=");
            outline107.append(this.useAlarmManagerAuthTokenManager);
            outline107.append(", dynamicAcousticParamsConfigPath=");
            outline107.append(this.dynamicAcousticParamsConfigPath);
            outline107.append(", realTimeTextCapable=");
            outline107.append(this.realTimeTextCapable);
            outline107.append(", enableCameraMetricsReporting=");
            outline107.append(this.enableCameraMetricsReporting);
            outline107.append(", videoFileInjectionPath=");
            outline107.append(this.videoFileInjectionPath);
            outline107.append(", platformVoIPSelection=");
            outline107.append(this.platformVoIPSelection);
            outline107.append(", useTextureViewForRendering=");
            outline107.append(this.useTextureViewForRendering);
            outline107.append(", translucentRemoteVideoRendererSupported=");
            return GeneratedOutlineSupport1.outline97(outline107, this.translucentRemoteVideoRendererSupported, ")");
        }

        public DeviceCallingServiceParamsBuilder translucentRemoteVideoRendererSupported(boolean z) {
            this.translucentRemoteVideoRendererSupported = z;
            this.translucentRemoteVideoRendererSupported$set = true;
            return this;
        }

        public DeviceCallingServiceParamsBuilder useAlarmManagerAuthTokenManager(boolean z) {
            this.useAlarmManagerAuthTokenManager = z;
            return this;
        }

        public DeviceCallingServiceParamsBuilder useTextureViewForRendering(boolean z) {
            this.useTextureViewForRendering = z;
            this.useTextureViewForRendering$set = true;
            return this;
        }

        public DeviceCallingServiceParamsBuilder userAgentStackVersionCode(String str) {
            this.userAgentStackVersionCode = str;
            return this;
        }

        public DeviceCallingServiceParamsBuilder userAgentStackVersionCodePrepend(String str) {
            this.userAgentStackVersionCodePrepend = str;
            return this;
        }

        public DeviceCallingServiceParamsBuilder videoConfiguration(VideoConfiguration videoConfiguration) {
            this.videoConfiguration = videoConfiguration;
            return this;
        }

        public DeviceCallingServiceParamsBuilder videoConstraintsUntilIceConnection(VideoConstraints videoConstraints) {
            this.videoConstraintsUntilIceConnection = videoConstraints;
            return this;
        }

        public DeviceCallingServiceParamsBuilder videoFileInjectionPath(String str) {
            this.videoFileInjectionPath = str;
            return this;
        }

        public DeviceCallingServiceParamsBuilder webRTCAcousticEchoCancelerPreferred(boolean z) {
            this.webRTCAcousticEchoCancelerPreferred = z;
            return this;
        }

        public DeviceCallingServiceParamsBuilder webRTCFieldTrials(String str) {
            this.webRTCFieldTrials = str;
            return this;
        }

        public DeviceCallingServiceParamsBuilder webRTCStatsLoggingFrequency(int i) {
            this.webRTCStatsLoggingFrequency = i;
            return this;
        }

        public DeviceCallingServiceParamsBuilder webRTCStatsPollingFrequency(int i) {
            this.webRTCStatsPollingFrequency = i;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean $default$translucentRemoteVideoRendererSupported() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean $default$useTextureViewForRendering() {
        return false;
    }

    DeviceCallingServiceParams(AcousticParams acousticParams, AvsDeviceFacade avsDeviceFacade, boolean z, boolean z2, boolean z3, VideoConstraints videoConstraints, boolean z4, VideoConstraints videoConstraints2, int i, SipHeaders sipHeaders, MediaParams mediaParams, String str, VideoConfiguration videoConfiguration, Map<Integer, VideoConstraints> map, VideoConstraints videoConstraints3, int i2, int i3, boolean z5, AudioConfig audioConfig, boolean z6, boolean z7, boolean z8, boolean z9, String str2, String str3, Size size, boolean z10, String str4, String str5, boolean z11, DynamicAcousticParams.ConfigPath configPath, boolean z12, boolean z13, String str6, PlatformVoIPSelection platformVoIPSelection, boolean z14, boolean z15) {
        this.acousticParams = acousticParams;
        this.avsDeviceFacade = avsDeviceFacade;
        this.requireCallAcknowledgement = z;
        this.relayOnlyIceTransport = z2;
        this.reduceVideoResolutionOnNoH264Remote = z3;
        this.maxVideoConstraintsOnReducedResolution = videoConstraints;
        this.constraintVideoUntilIceConnection = z4;
        this.videoConstraintsUntilIceConnection = videoConstraints2;
        this.audioStartBitrateInKbps = i;
        this.registrationHeaders = sipHeaders;
        this.mediaParams = mediaParams;
        this.webRTCFieldTrials = str;
        this.videoConfiguration = videoConfiguration;
        this.supportedVideoResolutions = map;
        this.maxVideoConstraintsToRequestFromRemote = videoConstraints3;
        this.webRTCStatsPollingFrequency = i2;
        this.webRTCStatsLoggingFrequency = i3;
        this.webRTCAcousticEchoCancelerPreferred = z5;
        this.audioCaptureAndRenderConfig = audioConfig;
        this.builtInSpeakerPresent = z6;
        this.cameraShutterPresent = z7;
        this.renderRemoteVideoSupported = z8;
        this.forceReceiveOnlyVideo = z9;
        this.buildVersion = str2;
        this.screenShape = str3;
        this.screenSize = size;
        this.presencePublishCapable = z10;
        this.userAgentStackVersionCodePrepend = str4;
        this.userAgentStackVersionCode = str5;
        this.useAlarmManagerAuthTokenManager = z11;
        this.dynamicAcousticParamsConfigPath = configPath;
        this.realTimeTextCapable = z12;
        this.enableCameraMetricsReporting = z13;
        this.videoFileInjectionPath = str6;
        this.platformVoIPSelection = platformVoIPSelection;
        this.useTextureViewForRendering = z14;
        this.translucentRemoteVideoRendererSupported = z15;
    }

    public static DeviceCallingServiceParamsBuilder builder() {
        return new DeviceCallingServiceParamsBuilder();
    }

    public AcousticParams getAcousticParams() {
        return this.acousticParams;
    }

    public AudioConfig getAudioCaptureAndRenderConfig() {
        return this.audioCaptureAndRenderConfig;
    }

    public int getAudioStartBitrateInKbps() {
        return this.audioStartBitrateInKbps;
    }

    public AvsDeviceFacade getAvsDeviceFacade() {
        return this.avsDeviceFacade;
    }

    public String getBuildVersion() {
        return this.buildVersion;
    }

    public DynamicAcousticParams.ConfigPath getDynamicAcousticParamsConfigPath() {
        return this.dynamicAcousticParamsConfigPath;
    }

    public VideoConstraints getMaxVideoConstraintsOnReducedResolution() {
        return this.maxVideoConstraintsOnReducedResolution;
    }

    public VideoConstraints getMaxVideoConstraintsToRequestFromRemote() {
        return this.maxVideoConstraintsToRequestFromRemote;
    }

    public MediaParams getMediaParams() {
        return this.mediaParams;
    }

    public PlatformVoIPSelection getPlatformVoIPSelection() {
        return this.platformVoIPSelection;
    }

    public SipHeaders getRegistrationHeaders() {
        return this.registrationHeaders;
    }

    public String getScreenShape() {
        return this.screenShape;
    }

    public Size getScreenSize() {
        return this.screenSize;
    }

    public Map<Integer, VideoConstraints> getSupportedVideoResolutions() {
        return this.supportedVideoResolutions;
    }

    public String getUserAgentStackVersionCode() {
        return this.userAgentStackVersionCode;
    }

    public String getUserAgentStackVersionCodePrepend() {
        return this.userAgentStackVersionCodePrepend;
    }

    public VideoConfiguration getVideoConfiguration() {
        return this.videoConfiguration;
    }

    public VideoConstraints getVideoConstraintsUntilIceConnection() {
        return this.videoConstraintsUntilIceConnection;
    }

    public String getVideoFileInjectionPath() {
        return this.videoFileInjectionPath;
    }

    public String getWebRTCFieldTrials() {
        return this.webRTCFieldTrials;
    }

    public int getWebRTCStatsLoggingFrequency() {
        return this.webRTCStatsLoggingFrequency;
    }

    public int getWebRTCStatsPollingFrequency() {
        return this.webRTCStatsPollingFrequency;
    }

    public boolean isBuiltInSpeakerPresent() {
        return this.builtInSpeakerPresent;
    }

    public boolean isCameraShutterPresent() {
        return this.cameraShutterPresent;
    }

    public boolean isConstraintVideoUntilIceConnection() {
        return this.constraintVideoUntilIceConnection;
    }

    public boolean isEnableCameraMetricsReporting() {
        return this.enableCameraMetricsReporting;
    }

    public boolean isForceReceiveOnlyVideo() {
        return this.forceReceiveOnlyVideo;
    }

    public boolean isPresencePublishCapable() {
        return this.presencePublishCapable;
    }

    public boolean isRealTimeTextCapable() {
        return this.realTimeTextCapable;
    }

    public boolean isReduceVideoResolutionOnNoH264Remote() {
        return this.reduceVideoResolutionOnNoH264Remote;
    }

    public boolean isRelayOnlyIceTransport() {
        return this.relayOnlyIceTransport;
    }

    public boolean isRenderRemoteVideoSupported() {
        return this.renderRemoteVideoSupported;
    }

    public boolean isRequireCallAcknowledgement() {
        return this.requireCallAcknowledgement;
    }

    public boolean isTranslucentRemoteVideoRendererSupported() {
        return this.translucentRemoteVideoRendererSupported;
    }

    public boolean isUseAlarmManagerAuthTokenManager() {
        return this.useAlarmManagerAuthTokenManager;
    }

    public boolean isUseTextureViewForRendering() {
        return this.useTextureViewForRendering;
    }

    public boolean isWebRTCAcousticEchoCancelerPreferred() {
        return this.webRTCAcousticEchoCancelerPreferred;
    }

    public void setAudioStartBitrateInKbps(int i) {
        this.audioStartBitrateInKbps = i;
    }
}
