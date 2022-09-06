package com.amazon.comms.ringservice.webrtc;

import android.content.Context;
import android.os.Handler;
import com.amazon.comms.calling.service.AudioConfig;
import com.amazon.comms.calling.service.DeviceCallingServiceParams;
import com.amazon.comms.calling.service.VideoConfiguration;
import com.amazon.comms.device.AvsDeviceFacade;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.DataChannelListener;
import com.amazon.comms.ringservice.MediaManager;
import com.amazon.comms.ringservice.MediaSession;
import com.amazon.comms.ringservice.MediaSessionListener;
import com.amazon.comms.ringservice.MetricsSession;
import com.amazon.comms.ringservice.RealTimeTextDataChannelListener;
import com.amazon.comms.ringservice.webrtc.PeerConnectionClient;
import com.amazon.comms.util.LooperExecutor;
import com.amazon.comms.util.SystemProperty;
import com.amazon.rtcmedia.webrtc.EglBaseWrapper;
import com.amazon.rtcmedia.webrtc.PeerConnectionFactoryWrapper;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase;
import org.webrtc.WebRTCAudioUtilsShim;
import org.webrtc.voiceengine.WebRtcAudioUtils;
/* loaded from: classes12.dex */
public class WebRTCMediaManager implements MediaManager, WarmupListener, WebRTCGlobalsProvider {
    private static final int FORCE_OUTPUT_DEVICE_NOT_SUPPORTED = -1;
    private static final String WEBRTC_AA_DEFAULT = "disable";
    private static final String WEBRTC_AA_PROPERTY_DISABLE = "disable";
    private static final String WEBRTC_AA_PROPERTY_ENABLE = "enable";
    private static final String WEBRTC_AA_PROPERTY_NAME = "persist.comms_webrtc_aa";
    private static final int WEBRTC_INPUT_SAMPLE_RATE_16K = 16000;
    private WebRTCMediaSession cachedMediaSession;
    private final DeviceCallingServiceParams deviceCallingServiceParams;
    private final Runnable disposeCachedSessionRunnable = new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaManager.1
        @Override // java.lang.Runnable
        public void run() {
            WebRTCMediaManager.log.i("Disposing expired media session prepared during warmup");
            WebRTCMediaManager.this.disposeCachedMediaSession();
        }
    };
    private EglBase.Context eglContext;
    private LooperExecutor executor;
    private PeerConnectionFactoryWrapper factoryWrapper;
    private Context mApplicationContext;
    private final Handler mOrchestratorHandler;
    private final MetricsSession metricsSession;
    private LooperExecutor statsExecutor;
    private final boolean videoCapable;
    private WebrtcVideoCodecHwSupportCheckImpl videoCodecHwSupportQueryHandler;
    private static final int WARMUP_ABSOLUTE_MAXIMUM_DURATION_MS = (int) TimeUnit.MINUTES.toMillis(1);
    private static final CommsLogger log = CommsLogger.getLogger(WebRTCMediaManager.class);

    public WebRTCMediaManager(Context context, Handler handler, MetricsSession metricsSession, DeviceCallingServiceParams deviceCallingServiceParams, boolean z) throws Exception {
        Preconditions.checkNotNull(context, "Context must be non-null.");
        this.mOrchestratorHandler = handler;
        this.mApplicationContext = context;
        applyAudioConfig(deviceCallingServiceParams.getAudioCaptureAndRenderConfig(), context);
        if (deviceCallingServiceParams.isWebRTCAcousticEchoCancelerPreferred()) {
            log.i("Using WebRTC Acoustic Echo Canceler");
            WebRtcAudioUtils.setWebRtcBasedAcousticEchoCanceler(true);
        }
        AvsDeviceFacade avsDeviceFacade = deviceCallingServiceParams.getAvsDeviceFacade();
        if (avsDeviceFacade != null) {
            int defaultOutputDeviceForCallAudio = avsDeviceFacade.getDefaultOutputDeviceForCallAudio();
            CommsLogger commsLogger = log;
            commsLogger.i("Default Output Device = " + defaultOutputDeviceForCallAudio);
            if (defaultOutputDeviceForCallAudio != -1) {
                WebRTCAudioUtilsShim.setOutputAudioDevice(defaultOutputDeviceForCallAudio);
            }
            avsDeviceFacade.forceResetMicToDefault();
        } else {
            log.i("avsDeviceFacade is NULL");
        }
        this.metricsSession = metricsSession;
        this.deviceCallingServiceParams = deviceCallingServiceParams;
        this.videoCapable = z;
        this.videoCodecHwSupportQueryHandler = new WebrtcVideoCodecHwSupportCheckImpl();
    }

    private void applyAudioConfig(AudioConfig audioConfig, Context context) {
        CommsLogger commsLogger = log;
        commsLogger.i("desired audio config: " + audioConfig);
        WebRTCAudioUtilsShim.setAudioCaptureRenderSampleRate(context, audioConfig.isAudioCaptureSampleRateOverriden(), audioConfig.getDefaultOrOverridenAudioCaptureSampleRateHz(), audioConfig.isAudioRenderSampleRateOverriden(), audioConfig.getDefaultOrOverridenAudioRenderSampleRateHz());
        if (AudioConfig.FastRenderPath.NONE.equals(audioConfig.getFastRenderPath()) || audioConfig.getRenderTrackBufferSizeBytes() == -1) {
            return;
        }
        WebRTCAudioUtilsShim.requestFlagFastIfPossible(AudioConfig.FastRenderPath.AUDIOTRACK.equals(audioConfig.getFastRenderPath()));
        WebRTCAudioUtilsShim.setRenderTrackBufferSize(audioConfig.getRenderTrackBufferSizeBytes());
    }

    private void cancelCachedSessionDisposer() {
        this.mOrchestratorHandler.removeCallbacks(this.disposeCachedSessionRunnable);
    }

    private WebRTCMediaSession createMediaSession(String str, boolean z, MediaSessionListener mediaSessionListener, EventTracer eventTracer, PeerConnectionClient.PeerConnectionParameters peerConnectionParameters, DataChannelListener dataChannelListener, RealTimeTextDataChannelListener realTimeTextDataChannelListener, boolean z2) {
        if (this.executor == null) {
            initializeWebRTC();
        }
        return new WebRTCMediaSession(this.mApplicationContext, str, z, mediaSessionListener, this.mOrchestratorHandler, this.metricsSession, eventTracer, peerConnectionParameters, this.deviceCallingServiceParams.getMediaParams(), this.deviceCallingServiceParams.isRenderRemoteVideoSupported(), this.deviceCallingServiceParams.isForceReceiveOnlyVideo(), this.executor, this.statsExecutor, this, this.eglContext, dataChannelListener, realTimeTextDataChannelListener, z2, this.deviceCallingServiceParams.getAvsDeviceFacade());
    }

    private void createPeerConnectionFactory(final boolean z) {
        LooperExecutor looperExecutor = this.executor;
        looperExecutor.execute(log.getTag() + ":createPeerConnectionFactory", new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaManager.2
            @Override // java.lang.Runnable
            public void run() {
                WebRTCMediaManager.log.i("Creating PeerConnectionFactory");
                WebRTCMediaManager webRTCMediaManager = WebRTCMediaManager.this;
                webRTCMediaManager.factoryWrapper = PeerConnectionFactoryWrapper.getOrCreateInstance(webRTCMediaManager.mApplicationContext, z, WebRTCMediaManager.this.deviceCallingServiceParams.getWebRTCFieldTrials(), WebRTCMediaManager.this.eglContext, WebRTCMediaManager.this.eglContext, false);
                WebRTCMediaManager.log.i("PeerConnectionFactory created and initialized. Querying codec capability");
                WebRTCMediaManager.this.videoCodecHwSupportQueryHandler.webrtcFactoryInitialized();
            }
        });
    }

    public static PeerConnectionClient.PeerConnectionParameters.PeerConnectionParametersBuilder createPeerConnectionParametersBuilder(DeviceCallingServiceParams deviceCallingServiceParams) {
        VideoConfiguration videoConfiguration = deviceCallingServiceParams.getVideoConfiguration();
        return PeerConnectionClient.PeerConnectionParameters.builder().videoWidth(videoConfiguration.getVideoWidth()).videoHeight(videoConfiguration.getVideoHeight()).videoFps(videoConfiguration.getVideoFps()).videoStartBitrate(videoConfiguration.getVideoStartBitrate()).videoCodec("H264").captureToTexture(videoConfiguration.isCaptureToTexture()).camera1ApiPreferred(videoConfiguration.isCamera1APIPreferred()).camera2ApiForced(videoConfiguration.isCamera2APIForced()).updateCameraHalFramerateAllowed(videoConfiguration.isUpdateCameraHalFramerateAllowed()).provideCallingServiceHalParameter(videoConfiguration.isProvideCallingServiceHalParameter()).audioStartBitrateInKbps(deviceCallingServiceParams.getAudioStartBitrateInKbps()).audioProcessingParams(deviceCallingServiceParams.getAcousticParams()).videoMaxBitrate(videoConfiguration.getVideoMaxBitrate()).overrideRemoteVideoMaxBitrate(videoConfiguration.isOverrideRemoteVideoMaxBitrate()).relayOnlyIceTransport(deviceCallingServiceParams.isRelayOnlyIceTransport()).reduceVideoResolutionOnNoH264Remote(deviceCallingServiceParams.isReduceVideoResolutionOnNoH264Remote()).maxVideoConstraintsOnReducedResolution(deviceCallingServiceParams.getMaxVideoConstraintsOnReducedResolution()).fieldTrials(deviceCallingServiceParams.getWebRTCFieldTrials()).ignoreCameraEvictionError(videoConfiguration.isIgnoreCameraEvictionError()).simulateFirstFrameReceived(videoConfiguration.isSimulateFirstFrameReceived()).supportedVideoResolutions(deviceCallingServiceParams.getSupportedVideoResolutions()).maxVideoConstraintsToRequestFromRemote(deviceCallingServiceParams.getMaxVideoConstraintsToRequestFromRemote()).webRTCStatsPollingFrequency(deviceCallingServiceParams.getWebRTCStatsPollingFrequency()).webRTCStatsLoggingFrequency(deviceCallingServiceParams.getWebRTCStatsLoggingFrequency()).constraintVideoUntilIceConnection(deviceCallingServiceParams.isConstraintVideoUntilIceConnection()).videoConstraintsUntilIceConnection(deviceCallingServiceParams.getVideoConstraintsUntilIceConnection()).screenShape(deviceCallingServiceParams.getScreenShape()).screenSize(deviceCallingServiceParams.getAvsDeviceFacade().getDisplaySize()).dynamicAcousticParamsConfigPath(deviceCallingServiceParams.getDynamicAcousticParamsConfigPath()).realTimeTextCapable(deviceCallingServiceParams.isRealTimeTextCapable()).enableCameraMetricsReporting(deviceCallingServiceParams.isEnableCameraMetricsReporting()).videoFileInjectionPath(deviceCallingServiceParams.getVideoFileInjectionPath()).platformVoIPSelection(deviceCallingServiceParams.getPlatformVoIPSelection());
    }

    private PeerConnectionClient.PeerConnectionParameters createWarmUpPeerConnectionParameters(boolean z, boolean z2, boolean z3, boolean z4, DeviceCallingServiceParams deviceCallingServiceParams) {
        return createPeerConnectionParametersBuilder(deviceCallingServiceParams).videoCapable(z).videoRequestEnabled(z && !deviceCallingServiceParams.isForceReceiveOnlyVideo() && z2).initialSystemMediaEnabled(z3).initialSystemCameraEnabled(z4).build();
    }

    private WebRTCMediaSession createWarmupMediaSession(PeerConnectionClient.PeerConnectionParameters peerConnectionParameters) {
        if (this.executor == null) {
            initializeWebRTC();
        }
        return new WebRTCMediaSession(this.mApplicationContext, this.mOrchestratorHandler, this.metricsSession, peerConnectionParameters, this.deviceCallingServiceParams.getMediaParams(), this.deviceCallingServiceParams.isRenderRemoteVideoSupported(), this.deviceCallingServiceParams.isForceReceiveOnlyVideo(), this, this.executor, this.statsExecutor, this, this.eglContext, this.deviceCallingServiceParams.getAvsDeviceFacade());
    }

    private static boolean disableWebRTCAudioProcessing() {
        String systemProperty = SystemProperty.getSystemProperty(WEBRTC_AA_PROPERTY_NAME, "disable");
        boolean z = systemProperty != null && systemProperty.equalsIgnoreCase("disable");
        CommsLogger commsLogger = log;
        commsLogger.i("disableWebRTCAudioProcessing:" + z);
        return z;
    }

    private void disposePeerConnectionFactory(PeerConnectionFactoryWrapper peerConnectionFactoryWrapper) {
        LooperExecutor looperExecutor = this.executor;
        looperExecutor.execute(log.getTag() + ":disposePeerConnectionFactory", new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.WebRTCMediaManager.3
            @Override // java.lang.Runnable
            public void run() {
                PeerConnectionFactoryWrapper.dispose();
                EglBaseWrapper.release();
                WebRTCMediaManager.log.i("PeerConnectionFactory disposed");
                WebRTCMediaManager.this.videoCodecHwSupportQueryHandler.webrtcFactoryShutdown();
            }
        });
    }

    private void initializeCachedSessionDisposer(int i) {
        int min = Math.min(i, WARMUP_ABSOLUTE_MAXIMUM_DURATION_MS);
        CommsLogger commsLogger = log;
        commsLogger.i("initializeCachedSessionDisposer: actualMaxDurationMs=" + min);
        this.mOrchestratorHandler.postDelayed(this.disposeCachedSessionRunnable, (long) min);
    }

    private void initializeWebRTC() {
        log.i("initializing WebRTC");
        if (this.executor != null) {
            log.i("WebRTC initialization already started");
            return;
        }
        this.executor = new LooperExecutor("WebRTCLooperExec");
        this.executor.requestStart();
        this.statsExecutor = new LooperExecutor("WebRTCStatsExec");
        this.statsExecutor.requestStart();
        if (this.videoCapable) {
            this.eglContext = EglBaseWrapper.getEglBaseContext();
        }
        createPeerConnectionFactory(this.videoCapable);
        log.i("WebRTC init started");
    }

    private void resetCachedSessionDisposer(int i) {
        cancelCachedSessionDisposer();
        initializeCachedSessionDisposer(i);
    }

    @Override // com.amazon.comms.ringservice.MediaManager
    public void close() {
        log.i("closing");
        if (this.executor != null) {
            disposeCachedMediaSession();
            disposePeerConnectionFactory(this.factoryWrapper);
            this.executor.requestStop();
            this.executor = null;
        }
        LooperExecutor looperExecutor = this.statsExecutor;
        if (looperExecutor != null) {
            looperExecutor.requestStop();
            this.statsExecutor = null;
        }
        this.eglContext = null;
        this.factoryWrapper = null;
    }

    @Override // com.amazon.comms.ringservice.MediaManager
    public void disposeCachedMediaSession() {
        if (this.cachedMediaSession == null) {
            return;
        }
        log.i("disposeCachedMediaSession");
        cancelCachedSessionDisposer();
        this.cachedMediaSession.stopMedia();
        this.cachedMediaSession = null;
    }

    @Override // com.amazon.comms.ringservice.MediaManager
    public EglBase.Context getEglContext() {
        return this.eglContext;
    }

    @Override // com.amazon.comms.ringservice.MediaManager
    public MediaSession getMediaSession(String str, boolean z, MediaSessionListener mediaSessionListener, EventTracer eventTracer, PeerConnectionClient.PeerConnectionParameters peerConnectionParameters, DataChannelListener dataChannelListener, RealTimeTextDataChannelListener realTimeTextDataChannelListener, boolean z2, String str2) {
        log.i("getMediaSession");
        if (this.cachedMediaSession != null) {
            log.i("getMediaSession cached media session exists.");
            cancelCachedSessionDisposer();
            WebRTCMediaSession webRTCMediaSession = this.cachedMediaSession;
            this.cachedMediaSession = null;
            webRTCMediaSession.attachCall(str, z, mediaSessionListener, eventTracer, peerConnectionParameters, dataChannelListener, realTimeTextDataChannelListener, z2);
            webRTCMediaSession.setCallProvider(str2);
            return webRTCMediaSession;
        }
        WebRTCMediaSession createMediaSession = createMediaSession(str, z, mediaSessionListener, eventTracer, peerConnectionParameters, dataChannelListener, realTimeTextDataChannelListener, z2);
        createMediaSession.setCallProvider(str2);
        return createMediaSession;
    }

    @Override // com.amazon.comms.ringservice.webrtc.WebRTCGlobalsProvider
    public PeerConnectionFactoryWrapper getPeerConnectionFactoryWrapper() {
        LooperExecutor looperExecutor = this.executor;
        if (looperExecutor != null && looperExecutor.checkOnLooperThread()) {
            return this.factoryWrapper;
        }
        throw new IllegalStateException("Wrong thread to call getPeerConnectionFactory");
    }

    @Override // com.amazon.comms.ringservice.webrtc.WebRTCGlobalsProvider
    public VideoCodecHwSupportChecker getVideoCodecHwSupportChecker() {
        return this.videoCodecHwSupportQueryHandler;
    }

    @Override // com.amazon.comms.ringservice.webrtc.WarmupListener
    public void onError(String str) {
        CommsLogger commsLogger = log;
        commsLogger.i("onError errorDescription - " + str);
        disposeCachedMediaSession();
    }

    @Override // com.amazon.comms.ringservice.MediaManager
    public void warmupMediaSession(int i, boolean z, boolean z2, boolean z3, boolean z4, DeviceCallingServiceParams deviceCallingServiceParams) {
        CommsLogger commsLogger = log;
        commsLogger.i("warmupMediaSession(maxWarmupDurationMs=" + i + ")");
        if (this.cachedMediaSession != null) {
            log.i("Unused cached media session already exists.");
            resetCachedSessionDisposer(i);
            return;
        }
        this.cachedMediaSession = createWarmupMediaSession(createWarmUpPeerConnectionParameters(z, z2, z3, z4, deviceCallingServiceParams));
        this.cachedMediaSession.warmupMedia();
        initializeCachedSessionDisposer(i);
    }
}
