package com.amazon.alexa.smarthomecameras.presenter;

import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.audio.AudioManager;
import com.amazon.alexa.smarthomecameras.constants.MobilyticsConstants;
import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import com.amazon.alexa.smarthomecameras.constants.RoutingConstants;
import com.amazon.alexa.smarthomecameras.constants.ViewConstants;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.model.SessionId;
import com.amazon.alexa.smarthomecameras.model.camerpreferences.CameraPreferences;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.service.CamerasNetworkingService;
import com.amazon.alexa.smarthomecameras.session.CameraSessionListener;
import com.amazon.alexa.smarthomecameras.session.CameraSessionManager;
import com.amazon.alexa.smarthomecameras.session.CameraSessionState;
import com.amazon.alexa.smarthomecameras.session.MediaConnectionState;
import com.amazon.alexa.smarthomecameras.session.VideoRendererSide;
import com.amazon.alexa.smarthomecameras.util.AppLifecycleListener;
import com.amazon.alexa.smarthomecameras.util.AppLifecycleOwner;
import com.amazon.alexa.smarthomecameras.util.CamerasLogger;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsFreeConstants;
import com.amazon.rtcsc.interfaces.RtcscMediaType;
import com.amazon.rtcsc.interfaces.RtcscSide;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Map;
import rx.functions.Action1;
/* loaded from: classes10.dex */
public class CameraViewPresenter implements CameraViewContract.Presenter, CameraSessionListener, AppLifecycleListener {
    @VisibleForTesting
    static final String ABODE_OSPREY_WEBLAB = "ELEMENTS_OSPREY_GLACIER";
    @VisibleForTesting
    static final String ARLO_OSPREY_WEBLAB = "ELEMENTS_OSPREY_GRANDCANYON";
    @VisibleForTesting
    static final String GOOGLE_OSPREY_WEBLAB = "ELEMENTS_OSPREY_REDWOOD";
    @VisibleForTesting
    static final String PILLOW_WEBLAB = "ELEMENTS_PILLOW";
    @VisibleForTesting
    static final String RING_OSPREY_WEBLAB = "ELEMENTS_OSPREY_YOSEMITE";
    private static final String TAG = "CameraViewPresenter";
    @VisibleForTesting
    static final String TRIVECTA_WEBLAB = "ELEMENTS_TRIVECTA";
    private static MobilyticsMetricsTimer activeSessionTimer;
    private MobilyticsMetricsTimer activeTwoWaySessionTimer;
    private final AppLifecycleOwner appLifecycleOwner;
    private final AudioManager audioManager;
    private final CameraSessionManager cameraSessionManager;
    private CameraViewContract.View cameraView;
    private final CameraViewContract.Model cameraViewModel;
    private final DevicePayload devicePayload;
    private final EntityId entityId;
    private final FeatureServiceV2 featureServiceV2;
    private final Gson gson = new Gson();
    private volatile boolean isCameraViewInitialized;
    private boolean isViewBackgrounded;
    private final CamerasMobilyticsService mobilyticsService;
    @VisibleForTesting
    public CamerasNetworkingService networkingService;
    private final RoutingService routingService;
    private MobilyticsMetricsTimer streamStartLatencyTimer;

    /* renamed from: com.amazon.alexa.smarthomecameras.presenter.CameraViewPresenter$1  reason: invalid class name */
    /* loaded from: classes10.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$smarthomecameras$session$MediaConnectionState = new int[MediaConnectionState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$smarthomecameras$session$MediaConnectionState[MediaConnectionState.MEDIA_CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$smarthomecameras$session$MediaConnectionState[MediaConnectionState.MEDIA_CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$smarthomecameras$session$MediaConnectionState[MediaConnectionState.MEDIA_DISCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$smarthomecameras$session$MediaConnectionState[MediaConnectionState.UNKNOWN_MEDIA_CONNECTION_STATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public CameraViewPresenter(EntityId entityId, CameraViewContract.Model model, CameraSessionManager cameraSessionManager, AudioManager audioManager, AppLifecycleOwner appLifecycleOwner, CamerasMobilyticsService camerasMobilyticsService, RoutingService routingService, FeatureServiceV2 featureServiceV2, DevicePayload devicePayload, CoralService coralService) {
        Preconditions.checkArgument(!TextUtils.isEmpty(entityId.getValue()), "EntityId cannot be empty");
        Preconditions.checkNotNull(model, "Camera View Model cannot be null");
        Preconditions.checkNotNull(cameraSessionManager, "CameraSessionManager cannot be null");
        Preconditions.checkNotNull(audioManager, "AudioManager cannot be null");
        Preconditions.checkNotNull(camerasMobilyticsService, "MobilyticsService cannot be null");
        Preconditions.checkNotNull(routingService, "RoutingService cannot be null");
        Preconditions.checkNotNull(featureServiceV2, "FeatureServiceV2 cannot be null");
        Preconditions.checkNotNull(coralService, "CoralService cannot be null");
        this.entityId = entityId;
        this.cameraViewModel = model;
        this.cameraSessionManager = cameraSessionManager;
        this.audioManager = audioManager;
        this.appLifecycleOwner = appLifecycleOwner;
        this.mobilyticsService = camerasMobilyticsService;
        this.routingService = routingService;
        this.featureServiceV2 = featureServiceV2;
        this.devicePayload = devicePayload;
        this.networkingService = new CamerasNetworkingService(coralService);
        appLifecycleOwner.registerListener(this);
    }

    private void fetchCameraPreferences() {
        if (!this.cameraViewModel.getCameraPreferencesFetched()) {
            this.networkingService.getPreferencesForEntityId(this.entityId.getValue()).subscribe(new Action1() { // from class: com.amazon.alexa.smarthomecameras.presenter.-$$Lambda$CameraViewPresenter$8_k8fYvUQrStLAW_MRyTgir4aO4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CameraViewPresenter.this.lambda$fetchCameraPreferences$0$CameraViewPresenter((JsonObject) obj);
                }
            }, new Action1() { // from class: com.amazon.alexa.smarthomecameras.presenter.-$$Lambda$CameraViewPresenter$vlDWOUyxt7Ug-NJq5hyldbUp1CQ
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CameraViewPresenter.this.lambda$fetchCameraPreferences$1$CameraViewPresenter((Throwable) obj);
                }
            });
            return;
        }
        CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "Not fetching CameraPreferences. Already available.");
        this.cameraView.notifyPreferencesFetched();
    }

    private void initializeCameraView() {
        SessionId activeSessionId = this.cameraSessionManager.getActiveSessionId();
        if (activeSessionId != null && !this.isCameraViewInitialized) {
            this.cameraView.initializeCameraView(activeSessionId);
            this.isCameraViewInitialized = true;
            return;
        }
        if (this.devicePayload.is1P()) {
            this.streamStartLatencyTimer = this.mobilyticsService.startTimedOperationalEvent(MobilyticsConstants.ALVMetrics.STREAM_START_LATENCY);
        } else {
            this.streamStartLatencyTimer = this.mobilyticsService.startTimedOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.STREAM_START_LATENCY, this.devicePayload.getManufacturerName());
        }
        this.cameraSessionManager.requestSessionForDevice(this.entityId);
    }

    private void releaseCameraView(boolean z) {
        MobilyticsMetricsTimer mobilyticsMetricsTimer;
        if (z && (mobilyticsMetricsTimer = activeSessionTimer) != null) {
            this.mobilyticsService.recordTimedOperationalEvent(mobilyticsMetricsTimer);
            activeSessionTimer = null;
        }
        this.isCameraViewInitialized = false;
        this.cameraView.releaseCameraView();
    }

    public boolean isByocvAvailable() {
        boolean supportsOds = this.devicePayload.supportsOds();
        boolean z = this.featureServiceV2.hasAccess("ELEMENTS_OSPREY_YOSEMITE", false) && this.devicePayload.getManufacturerName().equals(ViewConstants.SmartAlertsRingManufacturerName);
        boolean z2 = this.featureServiceV2.hasAccess("ELEMENTS_OSPREY_GLACIER", false) && this.devicePayload.getManufacturerName().equals(ViewConstants.SmartAlertsAbodeManufacturerName);
        boolean z3 = this.featureServiceV2.hasAccess("ELEMENTS_OSPREY_GRANDCANYON", false) && this.devicePayload.getManufacturerName().equals(ViewConstants.SmartAlertsArloManufacturerName);
        boolean z4 = this.featureServiceV2.hasAccess("ELEMENTS_OSPREY_REDWOOD", false) && this.devicePayload.getManufacturerName().equals(ViewConstants.SmartAlertsGoogleManufacturerName);
        if (supportsOds) {
            return z2 || z || z3 || z4;
        }
        return false;
    }

    public boolean isSmartAlertsAvailable() {
        return (this.featureServiceV2.hasAccess("ELEMENTS_TRIVECTA", false) && this.devicePayload.getManufacturerName().equals(ViewConstants.SmartAlertsAbodeManufacturerName)) || (this.featureServiceV2.hasAccess("ELEMENTS_PILLOW", false) && this.devicePayload.getManufacturerName().equals(ViewConstants.SmartAlertsRingManufacturerName));
    }

    public /* synthetic */ void lambda$fetchCameraPreferences$0$CameraViewPresenter(JsonObject jsonObject) {
        CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "GetCameraPreferences succeeded!");
        this.cameraViewModel.setCameraPreferencesFetched(true);
        CameraPreferences cameraPreferences = (CameraPreferences) this.gson.fromJson(jsonObject.get(NetworkConstants.PREFERENCES_KEY).getAsString(), (Class<Object>) CameraPreferences.class);
        Map<String, Object> smartAlertsPreferencesForEntityId = cameraPreferences.getSmartAlertsPreferencesForEntityId(this.entityId.getValue());
        this.cameraViewModel.setSubfeatureState(NetworkConstants.PERSON_DETECTION_KEY, cameraPreferences.isSubfeatureEnabled(smartAlertsPreferencesForEntityId, NetworkConstants.PERSON_DETECTION_KEY));
        this.cameraViewModel.setSubfeatureState(NetworkConstants.PET_DETECTION_KEY, cameraPreferences.isSubfeatureEnabled(smartAlertsPreferencesForEntityId, NetworkConstants.PET_DETECTION_KEY));
        this.cameraViewModel.setSubfeatureState(NetworkConstants.PACKAGE_DETECTION_KEY, cameraPreferences.isSubfeatureEnabled(smartAlertsPreferencesForEntityId, NetworkConstants.PACKAGE_DETECTION_KEY));
        this.cameraView.notifyPreferencesFetched();
    }

    public /* synthetic */ void lambda$fetchCameraPreferences$1$CameraViewPresenter(Throwable th) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetCameraPreferences failed: ");
        outline107.append(th.getMessage());
        CamerasLogger.logInfo(CamerasLogger.LVC_TAG, outline107.toString());
        this.cameraViewModel.setCameraPreferencesFetched(true);
        this.cameraViewModel.setSubfeatureState("", true);
        this.cameraView.notifyPreferencesFetched();
    }

    @Override // com.amazon.alexa.smarthomecameras.util.AppLifecycleListener
    public void onAppBackground() {
        this.isViewBackgrounded = true;
        this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.BACKGROUNDED);
        this.cameraView.setMicrophoneToggleState(false);
        this.cameraView.setSpeakerToggleState(false);
        this.cameraSessionManager.didBackground();
    }

    @Override // com.amazon.alexa.smarthomecameras.util.AppLifecycleListener
    public void onAppForeground() {
        this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.FOREGROUNDED);
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Presenter
    public void onBackButtonClicked() {
        this.routingService.navigateBackward();
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionListener
    public void onError(String str, String str2) {
        this.mobilyticsService.recordAlvError(str, str2);
        this.cameraView.setErrorVisible(true, str, str2);
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionListener
    public void onFirstFrameRendered(SessionId sessionId, VideoRendererSide videoRendererSide) {
        CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "First frame rendered on " + videoRendererSide + " renderer");
        this.mobilyticsService.recordTimedOperationalEvent(this.streamStartLatencyTimer);
        if (this.devicePayload.is1P()) {
            this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.SUCCESSFUL_SESSION_START_1P);
            if (activeSessionTimer == null) {
                activeSessionTimer = this.mobilyticsService.startTimedOperationalEvent(MobilyticsConstants.ALVMetrics.SESSION_DURATION_1P);
            }
        } else {
            this.mobilyticsService.recordOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.SUCCESSFUL_SESSION_START_1P, this.devicePayload.getManufacturerName());
            if (activeSessionTimer == null) {
                activeSessionTimer = this.mobilyticsService.startTimedOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.SESSION_DURATION_1P, this.devicePayload.getManufacturerName());
            }
        }
        this.cameraView.setLoadingVisible(false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001f, code lost:
        if (r2 != 3) goto L9;
     */
    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMediaConnectionStateChanged(com.amazon.alexa.smarthomecameras.model.SessionId r2, com.amazon.alexa.smarthomecameras.session.MediaConnectionState r3) {
        /*
            r1 = this;
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r0 = "onMediaConnectionStateChanged: "
            r2.append(r0)
            r2.append(r3)
            r2.toString()
            int r2 = r3.ordinal()
            r3 = 1
            java.lang.String r0 = "LVC"
            if (r2 == 0) goto L39
            if (r2 == r3) goto L33
            r3 = 2
            if (r2 == r3) goto L22
            r3 = 3
            if (r2 == r3) goto L27
            goto L2c
        L22:
            java.lang.String r2 = "MediaDisconnected"
            com.amazon.alexa.smarthomecameras.util.CamerasLogger.logInfo(r0, r2)
        L27:
            java.lang.String r2 = "UnknownMediaConnectionState"
            com.amazon.alexa.smarthomecameras.util.CamerasLogger.logInfo(r0, r2)
        L2c:
            com.amazon.alexa.smarthomecameras.CameraViewContract$View r2 = r1.cameraView
            r3 = 0
            r2.setLoadingVisible(r3)
            goto L43
        L33:
            java.lang.String r2 = "MediaConnected"
            com.amazon.alexa.smarthomecameras.util.CamerasLogger.logInfo(r0, r2)
            goto L43
        L39:
            java.lang.String r2 = "MediaConnecting"
            com.amazon.alexa.smarthomecameras.util.CamerasLogger.logInfo(r0, r2)
            com.amazon.alexa.smarthomecameras.CameraViewContract$View r2 = r1.cameraView
            r2.setLoadingVisible(r3)
        L43:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.smarthomecameras.presenter.CameraViewPresenter.onMediaConnectionStateChanged(com.amazon.alexa.smarthomecameras.model.SessionId, com.amazon.alexa.smarthomecameras.session.MediaConnectionState):void");
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionListener
    public void onMediaStatusChanged(String str, RtcscSide rtcscSide, RtcscMediaType rtcscMediaType, boolean z) {
        if (rtcscMediaType == RtcscMediaType.AUDIO) {
            if (rtcscSide == RtcscSide.LOCAL) {
                if (!this.cameraView.isMicEnabled()) {
                    return;
                }
                this.cameraView.setMicrophoneToggleState(z);
                this.cameraViewModel.setMicActive(this.entityId, z);
            } else if (!this.cameraView.isSpeakerEnabled()) {
            } else {
                this.cameraView.setSpeakerToggleState(z);
                this.cameraViewModel.setSpeakerActive(this.entityId, z);
            }
        }
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Presenter
    public void onMicToggleClicked() {
        boolean isMicActive = this.cameraViewModel.isMicActive(this.entityId);
        SessionId activeSessionId = this.cameraSessionManager.getActiveSessionId();
        if (activeSessionId != null) {
            this.audioManager.setMicrophoneMute(isMicActive, activeSessionId.getValue());
            if (isMicActive) {
                this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.MIC_DISABLED);
                if (this.devicePayload.is1P()) {
                    this.activeTwoWaySessionTimer = this.mobilyticsService.startTimedOperationalEvent(MobilyticsConstants.ALVMetrics.TWO_WAY_SESSION_DURATION_1P);
                    return;
                } else {
                    this.activeTwoWaySessionTimer = this.mobilyticsService.startTimedOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.TWO_WAY_SESSION_DURATION_1P, this.devicePayload.getManufacturerName());
                    return;
                }
            }
            if (this.devicePayload.is1P()) {
                this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.SWITCH_TO_2WAY_1P);
            } else {
                this.mobilyticsService.recordOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.SWITCH_TO_2WAY_1P, this.devicePayload.getManufacturerName());
            }
            this.mobilyticsService.recordTimedOperationalEvent(this.activeTwoWaySessionTimer);
        }
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Presenter
    public void onNetworkDisconnected() {
        this.mobilyticsService.recordNetworkError();
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Presenter
    public void onPermissionsChanged() {
        if (this.cameraView.areRequiredPermissionsGranted()) {
            initializeCameraView();
        }
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Presenter
    public void onPlayButtonClicked() {
        this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.PLAY_BUTTON_TAPPED);
        this.cameraView.setPlayButtonVisible(false);
        this.cameraView.setLoadingVisible(true);
        initializeCameraView();
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionListener
    public void onSessionConnected(SessionId sessionId) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onSessionConnected: ");
        outline107.append(sessionId.getValue());
        outline107.toString();
        initializeCameraView();
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionListener
    public void onSessionDisconnected(SessionId sessionId) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onSessionDisconnected: ");
        outline107.append(sessionId.getValue());
        outline107.toString();
        releaseCameraView(true);
    }

    @Override // com.amazon.alexa.smarthomecameras.session.CameraSessionListener
    public void onSessionStateChanged(SessionId sessionId, CameraSessionState cameraSessionState) {
        String str = "onSessionChanged: " + cameraSessionState;
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Presenter
    public void onSmartAlertsIngressClicked() {
        if (!this.cameraViewModel.getCameraPreferencesFetched()) {
            CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "Fetching failed. Routing to SA dashboard.");
            this.routingService.route("v2/cameras/smart-alerts/dashboard-page").navigate();
        } else if (!this.cameraViewModel.getEnabledSubfeatures().isEmpty()) {
            CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "Subfeatures enabled. Routing to SA dashboard.");
            this.routingService.route("v2/cameras/smart-alerts/dashboard-page").navigate();
        } else {
            CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "No subfeatures enabled. Routing to Setup flow.");
            this.routingService.route(RoutingConstants.SA_LANDING_PAGE_ROUTE).with("subfeature", NetworkConstants.PERSON_DETECTION_KEY).with(HandsFreeConstants.CANCEL_ROUTE, "devices-channel/index").navigate();
        }
        this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.SMART_ALERTS_TAPPED);
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Presenter
    public void onSpeakerToggleClicked() {
        boolean isSpeakerActive = this.cameraViewModel.isSpeakerActive(this.entityId);
        SessionId activeSessionId = this.cameraSessionManager.getActiveSessionId();
        if (activeSessionId != null) {
            this.audioManager.setSpeakerOn(!isSpeakerActive, activeSessionId.getValue());
            if (isSpeakerActive) {
                this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.SPEAKER_DISABLED);
            } else {
                this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.SPEAKER_ENABLED);
            }
        }
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Presenter
    public void onViewAttached() {
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Presenter
    public void onViewDetached() {
        onViewPaused(true);
        this.appLifecycleOwner.deregisterListener(this);
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Presenter
    public void onViewHidden() {
        MobilyticsMetricsTimer mobilyticsMetricsTimer = activeSessionTimer;
        if (mobilyticsMetricsTimer != null) {
            this.mobilyticsService.recordTimedOperationalEvent(mobilyticsMetricsTimer);
            activeSessionTimer = null;
        }
        this.cameraSessionManager.disconnectSession(this.entityId);
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Presenter
    public void onViewPaused(boolean z) {
        this.cameraSessionManager.deregisterSessionListener(this.entityId, this);
        releaseCameraView(z);
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Presenter
    public void onViewStarted() {
        this.cameraSessionManager.registerSessionListener(this.entityId, this);
        SessionId activeSessionId = this.cameraSessionManager.getActiveSessionId();
        if (activeSessionId != null) {
            this.audioManager.setMicrophoneMute(!this.cameraViewModel.isMicActive(this.entityId), activeSessionId.getValue());
            this.cameraView.setMicrophoneToggleState(this.cameraViewModel.isMicActive(this.entityId));
            this.audioManager.setSpeakerOn(this.cameraViewModel.isSpeakerActive(this.entityId), activeSessionId.getValue());
            this.cameraView.setSpeakerToggleState(this.cameraViewModel.isSpeakerActive(this.entityId));
        }
        this.cameraView.setSmartAlertsIngressVisible(isSmartAlertsAvailable() || isByocvAvailable());
        if (isSmartAlertsAvailable() || isByocvAvailable()) {
            fetchCameraPreferences();
        }
        this.cameraView.setMicAndSpeakerLabelsGone();
        if (this.devicePayload.isiRobot()) {
            this.cameraView.hideStatusText();
        } else if (this.cameraView.areRequiredPermissionsGranted()) {
            this.cameraView.hideStatusText();
            this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.MIC_ALREADY_ENABLED);
        } else {
            this.cameraView.requestPermissions();
            this.mobilyticsService.recordOperationalEvent(MobilyticsConstants.ALVMetrics.MIC_PROMPT_SHOWN);
            return;
        }
        if (this.isViewBackgrounded && this.cameraSessionManager.getActiveSessionId() == null) {
            this.cameraView.setPlayButtonVisible(true);
        } else {
            initializeCameraView();
        }
        this.isViewBackgrounded = false;
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Presenter
    public void sendData(String str) {
        this.cameraSessionManager.sendData(this.entityId, str);
    }

    @Override // com.amazon.alexa.smarthomecameras.CameraViewContract.Presenter
    public void setCameraView(CameraViewContract.View view) {
        Preconditions.checkNotNull(view, "CameraView cannot be null");
        this.cameraView = view;
    }
}
