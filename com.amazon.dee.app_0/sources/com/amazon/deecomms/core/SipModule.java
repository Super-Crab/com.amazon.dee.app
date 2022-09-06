package com.amazon.deecomms.core;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.comms.calling.instrumentation.EventTracerConfig;
import com.amazon.comms.calling.service.AcousticParams;
import com.amazon.comms.calling.service.AudioConfig;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.DeviceCallingServiceParams;
import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.amazon.comms.calling.service.MediaParams;
import com.amazon.comms.calling.service.PlatformVoIPSelection;
import com.amazon.comms.calling.service.VideoConfiguration;
import com.amazon.comms.calling.service.VideoConstraints;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.config.DeviceConfig;
import com.amazon.comms.config.alexacompanionapp.A2TF17PFR55MTBConfig;
import com.amazon.comms.device.AvsDeviceFacade;
import com.amazon.comms.instrumentation.EventTracerFactory;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.metrics.MetricsDestination;
import com.amazon.comms.metrics.TachyonMetricsFactoryImpl;
import com.amazon.comms.ringservice.dagger.RingService;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.controller.CallInitiationOrchestrator;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallPayloadValidator;
import com.amazon.deecomms.calling.controller.CallingContentProvider;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.controller.CommandProcessor;
import com.amazon.deecomms.calling.controller.DefaultCallingFacade;
import com.amazon.deecomms.calling.service.BluetoothHeadsetHelper;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.util.DeviceInfo;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import org.webrtc.voiceengine.WebRtcAudioUtils;
@Module
/* loaded from: classes12.dex */
public class SipModule {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SipModule.class);

    private static String getApplicationVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            LOG.w("Exception getting PackageInfo", e);
            return String.valueOf(Long.MAX_VALUE);
        }
    }

    private AudioConfig loadAudioDeviceConfig(DeviceConfig deviceConfig) {
        return new AudioConfig(deviceConfig.isAudioCaptureSampleRateOverriden(), deviceConfig.getAudioCaptureSampleRateToUse(), deviceConfig.isAudioRenderSampleRateOverriden(), deviceConfig.getAudioRenderSampleRateToUse(), deviceConfig.getFastAudioRenderPath(), deviceConfig.getAudioRenderBufferSizeBytes());
    }

    private VideoConstraints loadNonH264VideoConstraints(DeviceConfig deviceConfig) {
        return new VideoConstraints(deviceConfig.getNonH264VideoWidth(), deviceConfig.getNonH264VideoHeight(), deviceConfig.getNonH264VideoFps());
    }

    private VideoConfiguration loadVideoConfiguration(DeviceConfig deviceConfig) {
        return VideoConfiguration.builder().videoHeight(deviceConfig.getVideoHeight()).videoWidth(deviceConfig.getVideoWidth()).videoFps(deviceConfig.getVideoFps()).captureToTexture(deviceConfig.captureToTexture()).camera1APIPreferred(deviceConfig.preferCamera1API()).camera2APIForced(deviceConfig.forceCamera2API()).updateCameraHalFramerateAllowed(deviceConfig.updateCameraHalFramerateAllowed()).provideCallingServiceHalParameter(deviceConfig.provideCallingServiceHalParameter()).videoStartBitrate(deviceConfig.getVideoStartBitrate()).videoMaxBitrate(deviceConfig.getVideoMaxBitrate()).simulateFirstFrameReceived(deviceConfig.simulateFirstFrameReceived()).ignoreCameraEvictionError(deviceConfig.ignoreCameraEvictionError()).build();
    }

    private VideoConstraints loadVideoConstraintsUntilIceConnection(DeviceConfig deviceConfig) {
        return new VideoConstraints(deviceConfig.getVideoWidthBeforeIceConnection(), deviceConfig.getVideoHeightBeforeIceConnection(), deviceConfig.getVideoFramerateBeforeIceConnection());
    }

    @Provides
    @Singleton
    public BluetoothHeadsetHelper providesBluetoothHeadsetHelper(Context context, @Named("CurrentCall") SipClientState sipClientState) {
        return new BluetoothHeadsetHelper(context, sipClientState);
    }

    @Provides
    public CallPayloadValidator providesCallPayloadValidator() {
        return new CallPayloadValidator(ComponentRegistry.getInstance().getLazy(Mobilytics.class));
    }

    @Provides
    @Singleton
    public CallingContentProvider providesCallingContentProvider() {
        return new CallingContentProvider();
    }

    @Provides
    public CallingFacade providesCallingFacade(@NonNull CallInitiationOrchestrator callInitiationOrchestrator) {
        return new DefaultCallingFacade(callInitiationOrchestrator);
    }

    @Provides
    @Singleton
    public CommandProcessor providesCommandProcessor(DeviceCallingService deviceCallingService, EventTracerFactory eventTracerFactory, @Named("CurrentCall") SipClientState sipClientState, CallManager callManager, CapabilitiesManager capabilitiesManager, RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        return new CommandProcessor(deviceCallingService, eventTracerFactory, sipClientState, callManager, capabilitiesManager, realTimeTextEnablementAuthority);
    }

    @Provides
    @Singleton
    public DeviceCallingService providesDeviceCallingService() {
        return RingService.INSTANCE.getDeviceCallingService();
    }

    @Provides
    @Singleton
    public DeviceCallingServiceParams providesDeviceCallingServiceParams(Context context, SipHeaders sipHeaders) {
        A2TF17PFR55MTBConfig deviceConfigInstance = A2TF17PFR55MTBConfig.getDeviceConfigInstance();
        AvsDeviceFacade avsDeviceFacade = deviceConfigInstance.getAvsDeviceFacade(context);
        CapabilitiesManager capabilitiesManager = CommsDaggerWrapper.getComponent().getCapabilitiesManager();
        if (!deviceConfigInstance.supportsInsights(context) || !capabilitiesManager.isPublishingMetricsToInsightsEnabled()) {
            TachyonMetricsFactoryImpl.setMetricsDestination(MetricsDestination.DCM);
        }
        TachyonMetricsFactoryImpl.setUniqueIdentifier(deviceConfigInstance.getUniqueIdentifier().get());
        PlatformVoIPSelection platformVoIPSelection = deviceConfigInstance.getPlatformVoIPSelection();
        if (Utils.shouldUseNormalAudioMode() && platformVoIPSelection == null) {
            WebRtcAudioUtils.setDefaultStreamType(3);
        }
        DynamicAcousticParams.ConfigPath configPath = (platformVoIPSelection == null || !platformVoIPSelection.isEnableLibasp()) ? DynamicAcousticParams.ConfigPath.NONE : DynamicAcousticParams.ConfigPath.LIBASP;
        VideoConstraints loadNonH264VideoConstraints = loadNonH264VideoConstraints(deviceConfigInstance);
        boolean z = loadNonH264VideoConstraints.compareTo(new VideoConstraints(0, 0, 0)) > 0;
        boolean shouldConstraintVideoBeforeIceConnection = deviceConfigInstance.shouldConstraintVideoBeforeIceConnection();
        DeviceCallingServiceParams.DeviceCallingServiceParamsBuilder buildVersion = DeviceCallingServiceParams.builder().acousticParams(platformVoIPSelection == null ? AcousticParams.enabled() : AcousticParams.disabled()).relayOnlyIceTransport(false).registrationHeaders(sipHeaders).avsDeviceFacade(avsDeviceFacade).requireCallAcknowledgement(deviceConfigInstance.requiresCallAcknowledgement()).builtInSpeakerPresent(deviceConfigInstance.hasBuiltInSpeaker()).cameraShutterPresent(deviceConfigInstance.isCameraShutterPresent()).renderRemoteVideoSupported(deviceConfigInstance.supportsOneWayVideoCalling()).buildVersion(deviceConfigInstance.getBuildVersion());
        MediaParams.LastFrameClearOption lastFrameClearOption = MediaParams.LastFrameClearOption.REINITIALIZE_SURFACE;
        return buildVersion.mediaParams(new MediaParams(lastFrameClearOption, lastFrameClearOption, 1.0f)).audioStartBitrateInKbps(deviceConfigInstance.getAudioStartBitrateKbps()).reduceVideoResolutionOnNoH264Remote(z).maxVideoConstraintsOnReducedResolution(loadNonH264VideoConstraints).videoConfiguration(loadVideoConfiguration(deviceConfigInstance)).supportedVideoResolutions(deviceConfigInstance.getSupportedResolutions()).maxVideoConstraintsToRequestFromRemote(deviceConfigInstance.getMaxVideoConstraintsToRequestFromRemote()).webRTCStatsPollingFrequency(deviceConfigInstance.getWebRTCStatsPollingFrequency()).webRTCStatsLoggingFrequency(deviceConfigInstance.getWebRTCStatsLoggingFrequency()).constraintVideoUntilIceConnection(shouldConstraintVideoBeforeIceConnection).videoConstraintsUntilIceConnection(shouldConstraintVideoBeforeIceConnection ? loadVideoConstraintsUntilIceConnection(deviceConfigInstance) : null).audioCaptureAndRenderConfig(loadAudioDeviceConfig(deviceConfigInstance)).screenSize(DeviceInfo.getScreenDimensions(context, new Point())).screenShape(deviceConfigInstance.getLocalSurfaceViewShape()).presencePublishCapable(deviceConfigInstance.isPresencePublishCapable()).userAgentStackVersionCodePrepend("Android").userAgentStackVersionCode(getApplicationVersion(context)).useAlarmManagerAuthTokenManager(true).realTimeTextCapable(true).webRTCAcousticEchoCancelerPreferred(deviceConfigInstance.isWebRTCAcousticEchoCancelerPreferred()).dynamicAcousticParamsConfigPath(configPath).platformVoIPSelection(platformVoIPSelection).webRTCFieldTrials(deviceConfigInstance.getWebRTCFieldTrials()).build();
    }

    @Provides
    @Singleton
    public EventTracerFactory providesEventTracerFactory(Context context) {
        if (EventTracerConfig.METRICS_DOMAINS.contains(context.getPackageName())) {
            return new EventTracerFactory(EventTracerConfig.Event.values(), EventTracerConfig.Interval.values(), EventTracerConfig.LOG_TAG, context, context.getPackageName(), EventTracerConfig.METRICS_SOURCE);
        }
        return new EventTracerFactory(EventTracerConfig.Event.values(), EventTracerConfig.Interval.values(), EventTracerConfig.LOG_TAG);
    }

    @Provides
    @Singleton
    @Named(Constants.Dagger.PREVIOUS_CALL_SIPSTATE)
    public SipClientState providesPreviousSipClientState() {
        return new SipClientState();
    }

    @Provides
    @Singleton
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    public SipClientState providesSipClientState() {
        return new SipClientState();
    }

    @Provides
    @Singleton
    public SipHeaders providesSipHeaders(@Named("AppVersion") String str) {
        SipHeaders sipHeaders = new SipHeaders();
        sipHeaders.put(SipHeaders.SIP_HEADER_DEVICE_TYPE, "A2TF17PFR55MTB");
        sipHeaders.put(Constants.ABOVE_MIN_VERSION, str);
        sipHeaders.put(Constants.DEVICE_VERSION, str);
        return sipHeaders;
    }
}
