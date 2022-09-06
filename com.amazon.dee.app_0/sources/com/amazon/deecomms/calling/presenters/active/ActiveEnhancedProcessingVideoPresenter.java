package com.amazon.deecomms.calling.presenters.active;

import android.annotation.TargetApi;
import android.media.AudioManager;
import android.telecom.CallAudioState;
import android.telecom.Connection;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.MediaListener;
import com.amazon.comms.calling.service.MediaStateChangeTrigger;
import com.amazon.comms.calling.service.MediaStatus;
import com.amazon.comms.calling.service.WebRTCViewRenderer;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.alexa.AlexaInterface;
import com.amazon.deecomms.alexa.HalloEvent;
import com.amazon.deecomms.alexa.HalloEventType;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.accessibility.RealTimeTextPresenter;
import com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract;
import com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract;
import com.amazon.deecomms.calling.contracts.active.ActiveVideoCallViewContract;
import com.amazon.deecomms.calling.controller.CallActionsDispatcher;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.datachannel.payload.PipEvent;
import com.amazon.deecomms.calling.enums.AudioRoutes;
import com.amazon.deecomms.calling.enums.CallState;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.model.CallModel;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioManager;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.amazon.deecomms.calling.ui.EnhancedProcessingStateObservable;
import com.amazon.deecomms.calling.ui.NameChangeObservable;
import com.amazon.deecomms.calling.ui.PipVisibilityObservable;
import com.amazon.deecomms.calling.ui.listener.VideoDisplayListener;
import com.amazon.deecomms.calling.ui.listener.VideoStreamingListener;
import com.amazon.deecomms.calling.util.AmcCallUtil;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.calling.util.TelecomUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.media.audio.AudioStateObservable;
import com.amazon.deecomms.media.audio.CallMediaControlFacade;
import com.amazon.deecomms.util.ThreadUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
/* loaded from: classes12.dex */
public class ActiveEnhancedProcessingVideoPresenter implements ActiveVideoCallPresenterContract, MediaListener, CallTimerManager.NotificationUpdateListener, Observer {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ActiveEnhancedProcessingVideoPresenter.class);
    private final Call activeCall;
    private final ActiveVideoCallViewContract activeVideoCallViewContract;
    private final AlexaInterface alexaInterface;
    private final AudioManager audioManager;
    private final AudioStateObservable audioStateObservable;
    private final CallActionsDispatcher callActionsDispatcher;
    private final CallContext callContext;
    private final CallHistoryHelper callHistoryHelper;
    private final CallMediaControlFacade callMediaControlFacade;
    private final CallModel callModel;
    private final CallTimerManager callTimerManager;
    private final CapabilitiesManager capabilitiesManager;
    private WebRTCViewRenderer.ScalingType currentScalingType;
    private final EnhancedProcessingStateObservable enhancedProcessingStateObservable;
    private boolean isLocalPipShowing = true;
    private int localOrientation;
    private final NameChangeObservable nameChangeObservable;
    private final PipVisibilityObservable pipVisibilityObservable;
    private final RealTimeTextEnablementAuthority realTimeTextEnablementAuthority;
    private RealTimeTextPresenter realTimeTextPresenter;
    private final RealTimeTextViewContract realTimeTextViewContract;
    private int remoteOrientation;
    private final SipClientState sipClientState;
    private final TelecomBridge telecomBridge;
    private final TelecomCallAudioManager telecomCallAudioManager;
    private final TelecomCallAudioRouteObservable telecomCallAudioRouteObservable;
    private final VideoDisplayListener videoDisplayListener;
    private final VideoStreamingListener videoStreamingListener;

    public ActiveEnhancedProcessingVideoPresenter(@NonNull ActiveVideoCallViewContract activeVideoCallViewContract, @NonNull RealTimeTextViewContract realTimeTextViewContract, @NonNull CallModel callModel, @NonNull CallActionsDispatcher callActionsDispatcher, @NonNull CallHistoryHelper callHistoryHelper, @NonNull VideoStreamingListener videoStreamingListener, @NonNull VideoDisplayListener videoDisplayListener, @NonNull SipClientState sipClientState, @NonNull CallTimerManager callTimerManager, @NonNull CallContext callContext, @NonNull CallMediaControlFacade callMediaControlFacade, @NonNull AudioStateObservable audioStateObservable, @NonNull TelecomCallAudioRouteObservable telecomCallAudioRouteObservable, @NonNull AlexaInterface alexaInterface, @NonNull CapabilitiesManager capabilitiesManager, @NonNull NameChangeObservable nameChangeObservable, @NonNull TelecomCallAudioManager telecomCallAudioManager, @NonNull TelecomBridge telecomBridge, @NonNull AudioManager audioManager, @NonNull EnhancedProcessingStateObservable enhancedProcessingStateObservable, @NonNull PipVisibilityObservable pipVisibilityObservable, @NonNull RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        this.activeVideoCallViewContract = activeVideoCallViewContract;
        this.realTimeTextViewContract = realTimeTextViewContract;
        this.callModel = callModel;
        this.callActionsDispatcher = callActionsDispatcher;
        this.callHistoryHelper = callHistoryHelper;
        this.videoStreamingListener = videoStreamingListener;
        this.sipClientState = sipClientState;
        this.callTimerManager = callTimerManager;
        this.alexaInterface = alexaInterface;
        this.activeCall = sipClientState.getCurrentActiveCall();
        this.callMediaControlFacade = callMediaControlFacade;
        this.audioStateObservable = audioStateObservable;
        this.telecomCallAudioRouteObservable = telecomCallAudioRouteObservable;
        this.capabilitiesManager = capabilitiesManager;
        this.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
        this.nameChangeObservable = nameChangeObservable;
        this.enhancedProcessingStateObservable = enhancedProcessingStateObservable;
        this.videoDisplayListener = videoDisplayListener;
        this.telecomCallAudioManager = telecomCallAudioManager;
        this.audioManager = audioManager;
        this.callContext = callContext;
        this.telecomBridge = telecomBridge;
        this.pipVisibilityObservable = pipVisibilityObservable;
        this.realTimeTextPresenter = new RealTimeTextPresenter(realTimeTextViewContract, sipClientState);
    }

    private int getOrientation(int i, int i2, int i3) {
        if (i % 180 != 0) {
            i3 = i2;
            i2 = i3;
        }
        return i2 > i3 ? 2 : 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideSelfviewPIPIfNeeded() {
        if (this.capabilitiesManager.isMPUDynamicPipEnabled() || !this.callContext.isEnhancedProcessingCall()) {
            return;
        }
        LOG.i("Hiding self-view PIP");
        this.videoDisplayListener.hidePip(true);
    }

    private void recordMetrics() {
        MetricsHelper.startCallDurationTimer(this.activeCall);
        MetricsHelper.recordCallConnectedMetrics(this.activeCall);
    }

    private void reflectCurrentAudioOutputState() {
        if (Utils.isOreoAndAbove() && TelecomUtils.isAudioPickerEnabled()) {
            Connection currentConnection = this.telecomBridge.getCurrentConnection();
            if (currentConnection == null || currentConnection.getCallAudioState() == null) {
                return;
            }
            setSpeakerIconState(currentConnection.getCallAudioState());
        } else if (this.audioManager.isSpeakerphoneOn()) {
            this.activeVideoCallViewContract.showSpeakerOn();
        } else {
            this.activeVideoCallViewContract.showSpeakerOff();
        }
    }

    private void registerObservers() {
        this.audioStateObservable.addObserver(this);
        this.nameChangeObservable.addObserver(this);
        this.enhancedProcessingStateObservable.addObserver(this);
        this.pipVisibilityObservable.addObserver(this);
        this.telecomCallAudioRouteObservable.addObserver(this);
    }

    private void setLocalOrientation() {
        int currentRotation = this.activeVideoCallViewContract.getCurrentRotation();
        if (currentRotation != 1 && currentRotation != 3) {
            this.localOrientation = 1;
        } else {
            this.localOrientation = 2;
        }
        setRemoteViewScalingType();
    }

    private void setRemoteViewScalingType() {
        WebRTCViewRenderer.ScalingType scalingType;
        if (this.localOrientation == this.remoteOrientation) {
            scalingType = WebRTCViewRenderer.ScalingType.SCALE_ASPECT_FILL;
        } else {
            scalingType = WebRTCViewRenderer.ScalingType.SCALE_ASPECT_FIT;
        }
        if (this.currentScalingType != scalingType) {
            this.currentScalingType = scalingType;
            this.videoDisplayListener.setScalingType(scalingType, false);
        }
    }

    @TargetApi(23)
    private void setSpeakerIconState(CallAudioState callAudioState) {
        int route = callAudioState.getRoute();
        if (route == 2) {
            this.activeVideoCallViewContract.showBluetoothHeadsetOn();
        } else if (route != 8) {
            this.activeVideoCallViewContract.showSpeakerOff();
        } else {
            this.activeVideoCallViewContract.showSpeakerOn();
        }
    }

    private void setupRTT() {
        if (this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
            LOG.i("Setting up RTT");
            this.activeVideoCallViewContract.setKeyboardPanMechanism();
            this.realTimeTextPresenter.registerForRTTChanges();
            return;
        }
        LOG.i("RTT incapable");
        this.activeVideoCallViewContract.showRealTimeTextIncapable();
    }

    private void startAudioVideoFeed() {
        updateUIOnLocalMediaStateChange(this.activeCall.getMediaStatus().isLocalVideoEnabled(), this.activeCall.getMediaStatus().isLocalAudioEnabled());
        updateUIOnRemoteMediaStateChange(this.activeCall.getMediaStatus().isRemoteVideoEnabled());
        this.activeCall.registerMediaListener(this);
        this.videoStreamingListener.onStartStreamingVideo(CallState.ACTIVE, CallType.VIDEO);
        this.videoDisplayListener.minimizeVideo(0, true);
        setLocalOrientation();
        setRemoteViewScalingType();
        setDefaultAudioRoute();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updatePipVisibility */
    public void lambda$update$1$ActiveEnhancedProcessingVideoPresenter(Object obj) {
        if (obj.equals(PipEvent.Visibility.VISIBLE.name())) {
            LOG.i("Showing self-view PIP");
            this.videoDisplayListener.showPip(true);
            return;
        }
        LOG.i("Hiding self-view PIP");
        this.videoDisplayListener.hideSelfView();
    }

    private void updateUIOnRemoteMediaStateChange(boolean z) {
        if (z) {
            this.activeVideoCallViewContract.updateRemoteViewBackground(true);
            this.activeVideoCallViewContract.hideRemoteParticipantName();
            this.activeVideoCallViewContract.hideCallStatus();
            return;
        }
        this.activeVideoCallViewContract.updateRemoteViewBackground(false);
        this.activeVideoCallViewContract.showRemoteParticipantNameIfRequired(this.callModel.getRemoteParticipantName(), true);
        this.activeVideoCallViewContract.showVideoOffCallStatus();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void endCall() {
        LOG.i("End call button pressed");
        this.alexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.TERMINATE_CALL_FOR_ALL, this.activeCall.getCallId(), this.sipClientState));
        this.callActionsDispatcher.endActiveCall();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public RealTimeTextEnablementAuthority getRealTimeTextEnablementAuthority() {
        return this.realTimeTextEnablementAuthority;
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public RealTimeTextPresenter getRealTimeTextPresenter() {
        return this.realTimeTextPresenter;
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public String getRemoteParticipantName() {
        return this.callModel.getRemoteParticipantName();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public TelecomCallAudioManager getTelecomCallAudioManager() {
        return this.telecomCallAudioManager;
    }

    @VisibleForTesting
    void initializeUIWidgets() {
        this.activeVideoCallViewContract.showRemoteParticipantNameIfRequired(this.callModel.getRemoteParticipantName(), true);
        this.activeVideoCallViewContract.showCallStatus(this.callModel.getCallStatus());
        this.activeVideoCallViewContract.showToggleCamera();
        this.activeVideoCallViewContract.activateCallControls();
        CapabilitiesManager capabilitiesManager = this.capabilitiesManager;
        if (capabilitiesManager != null && capabilitiesManager.isWorldsEnabled() && !this.callContext.isGroupCall()) {
            this.activeVideoCallViewContract.showEffectsMenuButton();
        }
        setupRTT();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public boolean isRTTEnabled() {
        return this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall();
    }

    public /* synthetic */ void lambda$update$0$ActiveEnhancedProcessingVideoPresenter(Object obj) {
        this.activeVideoCallViewContract.showRemoteParticipantNameIfRequired((String) obj, false);
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onCameraSwitchDone(boolean z) {
        if (z) {
            this.videoStreamingListener.onCameraToggledToFront(CallState.ACTIVE, CallType.VIDEO);
            this.activeVideoCallViewContract.showFrontSelfVideoFeed();
            return;
        }
        this.videoStreamingListener.onCameraToggledToRear(CallState.ACTIVE, CallType.VIDEO);
        this.activeVideoCallViewContract.showRearSelfVideoFeed();
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onCameraSwitchError(String str) {
        GeneratedOutlineSupport.outline3("onCameraSwitchError", str, LOG);
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onDtmfTonesSent(Call call, String str, int i, int i2) {
        LOG.d(String.format("Call (%s) sent dtmf tones (%s)", call.getCallId(), str));
    }

    @Override // com.amazon.deecomms.calling.controller.CallTimerManager.NotificationUpdateListener
    public void onDurationUpdated(String str) {
        this.activeVideoCallViewContract.showCallDuration(str);
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onFirstFrameReceived(Call.Side side) {
        MediaStatus mediaStatus = this.sipClientState.getCurrentActiveCall().getMediaStatus();
        if (mediaStatus != null) {
            if (side == Call.Side.Local) {
                updateUIOnLocalMediaStateChange(mediaStatus.isLocalVideoEnabled(), mediaStatus.isLocalAudioEnabled());
                setLocalOrientation();
                return;
            }
            updateUIOnRemoteMediaStateChange(mediaStatus.isRemoteVideoEnabled());
        }
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onFirstFrameRendered(Call.Side side) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onLocalFrameResolutionChanged(int i, int i2, int i3) {
        LOG.i(String.format(Locale.US, "LocalFrameResolutionChanged - videoWidth = %d, videoHeight = %d, rotation = %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        this.localOrientation = getOrientation(i3, i, i2);
        setRemoteViewScalingType();
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onMediaStatsUpdated(Call call) {
        if (this.callHistoryHelper.setCallConnectionType(call)) {
            MetricsHelper.recordCallConnectionTypeMetric(call);
        }
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onMediaStatusUpdated(Call call, Call.Side side, MediaStatus mediaStatus, MediaStateChangeTrigger mediaStateChangeTrigger) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1(" onMediaStateChange localVideoEnabled ");
        outline1.append(mediaStatus.isLocalVideoEnabled());
        outline1.append(" localAudioEnabled ");
        outline1.append(mediaStatus.isLocalAudioEnabled());
        outline1.append(" remoteVideoEnabled ");
        outline1.append(mediaStatus.isRemoteVideoEnabled());
        outline1.append(" remoteAudioEnabled ");
        outline1.append(mediaStatus.isRemoteAudioEnabled());
        outline1.append(" updateTrigger ");
        outline1.append(mediaStateChangeTrigger);
        outline1.append(" callSide ");
        outline1.append(side);
        commsLogger.i(outline1.toString());
        if (call.getCallId().equals(this.sipClientState.getCallId())) {
            if (side == Call.Side.Local) {
                updateUIOnLocalMediaStateChange(mediaStatus.isLocalVideoEnabled(), mediaStatus.isLocalAudioEnabled());
                setLocalOrientation();
                return;
            }
            updateUIOnRemoteMediaStateChange(mediaStatus.isRemoteVideoEnabled());
            return;
        }
        LOG.e(" callId doesn't match with current active call ");
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void onOrientationSetup(int i) {
        MetricsHelper.recordOrientationMetric(MetricKeys.CALL_SCREEN_ORIENTATION_OPEN, i);
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onRemoteFrameResolutionChanged(int i, int i2, int i3) {
        LOG.i(String.format(Locale.US, "RemoteFrameResolutionChanged - videoWidth = %d, videoHeight = %d, rotation = %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        this.remoteOrientation = getOrientation(i3, i, i2);
        setRemoteViewScalingType();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void onRotation(int i, int i2) {
        MetricsHelper.recordOrientationMetric(MetricKeys.CALL_SCREEN_ORIENTATION_ROTATE, i2);
        if (this.callContext.isEnhancedProcessingCall()) {
            AmcCallUtil.sendOrientationChangeEvent(this.callContext.getCurrentCall(), i, i2);
        }
        this.activeVideoCallViewContract.showToggleCamera();
        updateUIOnLocalMediaStateChange(this.activeCall.getMediaStatus().isLocalVideoEnabled(), this.activeCall.getMediaStatus().isLocalAudioEnabled());
        updateUIOnRemoteMediaStateChange(this.activeCall.getMediaStatus().isRemoteVideoEnabled());
        reflectCurrentAudioOutputState();
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onVideoEffectChanged(Call call) {
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void onViewBackgrounded() {
        this.callTimerManager.removeListener(this);
    }

    @Override // com.amazon.deecomms.calling.contracts.BasePresenterContract
    public void onViewCreated() {
        recordMetrics();
        initializeUIWidgets();
        registerObservers();
        startAudioVideoFeed();
        hideSelfviewPIPIfNeeded();
        setRotationSupport();
    }

    @Override // com.amazon.deecomms.calling.contracts.BasePresenterContract
    public void onViewDestroyed() {
        LOG.i("Active video call destroyed.");
        this.audioStateObservable.deleteObserver(this);
        this.nameChangeObservable.deleteObserver(this);
        this.enhancedProcessingStateObservable.deleteObserver(this);
        this.pipVisibilityObservable.deleteObserver(this);
        this.telecomCallAudioRouteObservable.deleteObserver(this);
        this.activeCall.unregisterMediaListener(this);
        MetricsHelper.stopCallDurationTimer(this.activeCall);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void onViewForegrounded() {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.SCREEN_CALL_ACTIVE_SHOWN);
        this.callTimerManager.addListener(this);
    }

    @VisibleForTesting
    public void setDefaultAudioRoute() {
        Connection currentConnection;
        if (TelecomUtils.isAudioPickerEnabled() && Utils.isMarshMallowAndAbove() && (currentConnection = this.telecomBridge.getCurrentConnection()) != null && currentConnection.getCallAudioState() != null) {
            this.telecomCallAudioManager.setDefaultAudioRoute(true);
            setSpeakerIconState(currentConnection.getCallAudioState());
        } else if (this.callMediaControlFacade.isHeadphonesPlugged()) {
        } else {
            toggleSpeaker();
        }
    }

    @VisibleForTesting
    void setRotationSupport() {
        RealTimeTextEnablementAuthority realTimeTextEnablementAuthority = this.realTimeTextEnablementAuthority;
        if (realTimeTextEnablementAuthority == null || realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
            return;
        }
        if (this.callContext.isEnhancedProcessingCall() && !this.capabilitiesManager.isMPUCallOrientationEnabled()) {
            return;
        }
        this.activeVideoCallViewContract.setRotationSupported();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void toggleCamera() {
        LOG.i("Switching camera");
        this.activeCall.switchCamera();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void toggleMic() {
        LOG.i("Toggle mic button pressed");
        this.callMediaControlFacade.toggleMic();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void toggleSpeaker() {
        LOG.i("Toggle speaker button is pressed");
        if (Utils.isOreoAndAbove() && TelecomUtils.isAudioPickerEnabled()) {
            int size = this.telecomCallAudioManager.getSupportedAudioRoutes().size();
            if (size > 2) {
                this.activeVideoCallViewContract.showAudioPickerPopup();
                return;
            } else if (size > 0) {
                this.telecomCallAudioManager.toggleSpeaker();
                return;
            } else {
                this.callMediaControlFacade.toggleSpeaker();
                return;
            }
        }
        this.callMediaControlFacade.toggleSpeaker();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract
    public void toggleVideo() {
        LOG.i("Toggle video button pressed");
        this.callMediaControlFacade.toggleVideo();
    }

    @Override // java.util.Observer
    public void update(Observable observable, final Object obj) {
        if (observable instanceof AudioStateObservable) {
            AudioRoutes audioRoutes = (AudioRoutes) obj;
            if (audioRoutes == AudioRoutes.SPEAKER) {
                this.activeVideoCallViewContract.showSpeakerOn();
            } else if (audioRoutes != AudioRoutes.EARPIECE) {
            } else {
                this.activeVideoCallViewContract.showSpeakerOff();
            }
        } else if (observable instanceof NameChangeObservable) {
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.presenters.active.-$$Lambda$ActiveEnhancedProcessingVideoPresenter$cON6RI-HYM-s_DcsiXAlSv9_WIg
                @Override // java.lang.Runnable
                public final void run() {
                    ActiveEnhancedProcessingVideoPresenter.this.lambda$update$0$ActiveEnhancedProcessingVideoPresenter(obj);
                }
            });
        } else if (observable instanceof TelecomCallAudioRouteObservable) {
            if (!Utils.isMarshMallowAndAbove()) {
                return;
            }
            setSpeakerIconState((CallAudioState) obj);
        } else if (observable instanceof EnhancedProcessingStateObservable) {
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.presenters.active.-$$Lambda$ActiveEnhancedProcessingVideoPresenter$VwQorEOo_ZnsPHHzEmeVmOIhLBY
                @Override // java.lang.Runnable
                public final void run() {
                    ActiveEnhancedProcessingVideoPresenter.this.hideSelfviewPIPIfNeeded();
                }
            });
        } else if (!(observable instanceof PipVisibilityObservable)) {
        } else {
            this.isLocalPipShowing = !obj.equals(PipEvent.Visibility.INVISIBLE.name());
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.presenters.active.-$$Lambda$ActiveEnhancedProcessingVideoPresenter$NI-e02KJQgaSirYCs0W46Fv6Gls
                @Override // java.lang.Runnable
                public final void run() {
                    ActiveEnhancedProcessingVideoPresenter.this.lambda$update$1$ActiveEnhancedProcessingVideoPresenter(obj);
                }
            });
        }
    }

    @VisibleForTesting
    void updateUIOnLocalMediaStateChange(boolean z, boolean z2) {
        if (z) {
            this.activeVideoCallViewContract.showVideoOn();
        } else {
            this.activeVideoCallViewContract.showVideoOff();
        }
        this.videoDisplayListener.onSetBackground(z, true, this.isLocalPipShowing);
        if (z2) {
            this.activeVideoCallViewContract.showMicUnMuted();
        } else {
            this.activeVideoCallViewContract.showMicMuted();
        }
    }
}
