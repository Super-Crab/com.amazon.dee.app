package com.amazon.deecomms.calling.presenters.active;

import android.annotation.TargetApi;
import android.telecom.CallAudioState;
import android.telecom.Connection;
import androidx.annotation.NonNull;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.MediaListener;
import com.amazon.comms.calling.service.MediaStateChangeTrigger;
import com.amazon.comms.calling.service.MediaStatus;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.alexa.AlexaInterface;
import com.amazon.deecomms.alexa.HalloEvent;
import com.amazon.deecomms.alexa.HalloEventType;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.accessibility.RealTimeTextPresenter;
import com.amazon.deecomms.calling.accessibility.RealTimeTextViewContract;
import com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract;
import com.amazon.deecomms.calling.contracts.active.ActiveAudioCallViewContract;
import com.amazon.deecomms.calling.controller.CallActionsDispatcher;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.enums.AudioRoutes;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.model.CallModel;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioManager;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.amazon.deecomms.calling.ui.NameChangeObservable;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.calling.util.TelecomUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.media.audio.AudioStateObservable;
import com.amazon.deecomms.media.audio.CallMediaControlFacade;
import com.amazon.deecomms.perms.PermissionsHelper;
import com.amazon.deecomms.util.ThreadUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Observable;
import java.util.Observer;
/* loaded from: classes12.dex */
public class ActiveEnhancedProcessingAudioPresenter implements ActiveAudioCallPresenterContract, CallTimerManager.NotificationUpdateListener, MediaListener, Observer {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ActiveEnhancedProcessingVideoPresenter.class);
    private final Call activeCall;
    private final AlexaInterface alexaInterface;
    private final AudioStateObservable audioStateObservable;
    private final CallActionsDispatcher callActionsDispatcher;
    private final CallHistoryHelper callHistoryHelper;
    private final CallManager callManager;
    private final CallMediaControlFacade callMediaControlFacade;
    private final CallModel callModel;
    private final CallTimerManager callTimerManager;
    private final NameChangeObservable nameChangeObservable;
    private final RealTimeTextEnablementAuthority realTimeTextEnablementAuthority;
    private final RealTimeTextPresenter realTimeTextPresenter;
    private final RealTimeTextViewContract realTimeTextViewContract;
    private final SipClientState sipClientState;
    private final TelecomBridge telecomBridge;
    private final TelecomCallAudioManager telecomCallAudioManager;
    private final TelecomCallAudioRouteObservable telecomCallAudioRouteObservable;
    private final ActiveAudioCallViewContract viewContract;

    public ActiveEnhancedProcessingAudioPresenter(@NonNull ActiveAudioCallViewContract activeAudioCallViewContract, @NonNull RealTimeTextViewContract realTimeTextViewContract, @NonNull CallModel callModel, @NonNull CallActionsDispatcher callActionsDispatcher, @NonNull CallHistoryHelper callHistoryHelper, @NonNull SipClientState sipClientState, @NonNull CallTimerManager callTimerManager, @NonNull CallManager callManager, @NonNull AlexaInterface alexaInterface, @NonNull CallMediaControlFacade callMediaControlFacade, @NonNull AudioStateObservable audioStateObservable, @NonNull NameChangeObservable nameChangeObservable, @NonNull TelecomCallAudioManager telecomCallAudioManager, @NonNull TelecomBridge telecomBridge, @NonNull TelecomCallAudioRouteObservable telecomCallAudioRouteObservable, @NonNull RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        this.viewContract = activeAudioCallViewContract;
        this.realTimeTextViewContract = realTimeTextViewContract;
        this.callModel = callModel;
        this.callActionsDispatcher = callActionsDispatcher;
        this.callHistoryHelper = callHistoryHelper;
        this.sipClientState = sipClientState;
        this.callTimerManager = callTimerManager;
        this.callManager = callManager;
        this.alexaInterface = alexaInterface;
        this.activeCall = sipClientState.getCurrentActiveCall();
        this.callMediaControlFacade = callMediaControlFacade;
        this.audioStateObservable = audioStateObservable;
        this.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
        this.nameChangeObservable = nameChangeObservable;
        this.telecomCallAudioManager = telecomCallAudioManager;
        this.telecomCallAudioRouteObservable = telecomCallAudioRouteObservable;
        this.telecomBridge = telecomBridge;
        this.realTimeTextPresenter = new RealTimeTextPresenter(realTimeTextViewContract, sipClientState);
    }

    private void registerObservers() {
        this.audioStateObservable.addObserver(this);
        this.nameChangeObservable.addObserver(this);
        this.telecomCallAudioRouteObservable.addObserver(this);
    }

    private void setCurrentAudioState() {
        if (TelecomUtils.isAudioPickerEnabled() && Utils.isMarshMallowAndAbove()) {
            Connection currentConnection = this.telecomBridge.getCurrentConnection();
            if (currentConnection == null || currentConnection.getCallAudioState() == null) {
                return;
            }
            setSpeakerIconState(currentConnection.getCallAudioState());
            return;
        }
        this.callMediaControlFacade.setCurrentAudioOutputState();
    }

    @TargetApi(23)
    private void setSpeakerIconState(CallAudioState callAudioState) {
        int route = callAudioState.getRoute();
        if (route == 2) {
            this.viewContract.showBluetoothHeadsetOn();
        } else if (route != 8) {
            this.viewContract.showSpeakerOff();
        } else {
            this.viewContract.showSpeakerOn();
        }
    }

    private void setupRTT() {
        if (this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
            LOG.i("Setting up RTT");
            this.realTimeTextPresenter.registerForRTTChanges();
            this.viewContract.setKeyboardPanMechanism();
            return;
        }
        LOG.i("Showing RTT Incapable toast");
        this.viewContract.showRealTimeTextIncapable();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract
    public void endCall() {
        LOG.i("End call button pressed");
        this.alexaInterface.sendCommsEvent(new HalloEvent(HalloEventType.TERMINATE_CALL_FOR_ALL, this.activeCall.getCallId(), this.sipClientState));
        this.callActionsDispatcher.endActiveCall();
        this.viewContract.hideErrorMessage();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract
    public RealTimeTextEnablementAuthority getRealTimeTextEnablementAuthority() {
        return this.realTimeTextEnablementAuthority;
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract
    public RealTimeTextPresenter getRealTimeTextPresenter() {
        return this.realTimeTextPresenter;
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract
    public String getRemoteParticipantName() {
        return this.callModel.getRemoteParticipantName();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract
    public TelecomCallAudioManager getTelecomCallAudioManager() {
        return this.telecomCallAudioManager;
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract
    public boolean isRTTEnabled() {
        return this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall();
    }

    public /* synthetic */ void lambda$update$0$ActiveEnhancedProcessingAudioPresenter(Object obj) {
        this.viewContract.showRemoteParticipantName((String) obj);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract
    public void onCallDowngradeMessageExpiry() {
        this.viewContract.showRemoteParticipantName(this.callModel.getRemoteParticipantName());
        this.viewContract.showCallStatus(this.callModel.getCallStatus());
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onCameraSwitchDone(boolean z) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onCameraSwitchError(String str) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onDtmfTonesSent(Call call, String str, int i, int i2) {
        LOG.d(String.format("Call (%s) sent dtmf tones (%s)", call.getCallId(), str));
    }

    @Override // com.amazon.deecomms.calling.controller.CallTimerManager.NotificationUpdateListener
    public void onDurationUpdated(String str) {
        this.viewContract.showCallDuration(str);
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onFirstFrameReceived(Call.Side side) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onFirstFrameRendered(Call.Side side) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onLocalFrameResolutionChanged(int i, int i2, int i3) {
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
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Media State Change : localAudioEnabled ");
        outline1.append(mediaStatus.isLocalAudioEnabled());
        outline1.append(" callSide ");
        outline1.append(side);
        outline1.append(" updateTrigger ");
        outline1.append(mediaStateChangeTrigger);
        commsLogger.i(outline1.toString());
        if (!call.getCallId().equals(this.sipClientState.getCallId()) || side != Call.Side.Local) {
            return;
        }
        if (!mediaStatus.isLocalAudioEnabled()) {
            this.viewContract.showMicMuted();
        } else {
            this.viewContract.showMicUnMuted();
        }
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onRemoteFrameResolutionChanged(int i, int i2, int i3) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onVideoEffectChanged(Call call) {
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract
    public void onViewBackgrounded() {
        this.callTimerManager.removeListener(this);
    }

    @Override // com.amazon.deecomms.calling.contracts.BasePresenterContract
    public void onViewCreated() {
        MetricsHelper.startCallDurationTimer(this.activeCall);
        MetricsHelper.recordCallConnectedMetrics(this.activeCall);
        this.viewContract.showRemoteParticipantName(this.callModel.getRemoteParticipantName());
        this.viewContract.showCallStatus(this.callModel.getCallStatus());
        this.viewContract.activateCallControls();
        Call call = this.activeCall;
        if (call != null) {
            call.registerMediaListener(this);
        }
        if (this.sipClientState.getCallType() == CallType.PSTN) {
            this.viewContract.showDialpad();
        } else {
            this.viewContract.hideDialpad();
        }
        if (PermissionsHelper.filterGrantedPermissions(PermissionsHelper.getPermissionListForAudio()).length > 0) {
            this.viewContract.showErrorMessage();
        } else {
            this.viewContract.hideErrorMessage();
        }
        Call call2 = this.activeCall;
        if (call2 != null && Call.Side.Local.equals(call2.getOrigin()) && this.callManager.isCallDowngraded()) {
            this.viewContract.showCallVideoDowngradedUI();
        }
        registerObservers();
        setCurrentAudioState();
        setupRTT();
    }

    @Override // com.amazon.deecomms.calling.contracts.BasePresenterContract
    public void onViewDestroyed() {
        Call call = this.activeCall;
        if (call != null) {
            call.unregisterMediaListener(this);
            MetricsHelper.stopCallDurationTimer(this.activeCall);
        }
        this.audioStateObservable.deleteObserver(this);
        this.telecomCallAudioRouteObservable.deleteObserver(this);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract
    public void onViewForegrounded() {
        this.callTimerManager.addListener(this);
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract
    public void toggleMic() {
        LOG.i("Toggle mic pressed");
        this.callMediaControlFacade.toggleMic();
    }

    @Override // com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract
    public void toggleSpeaker() {
        LOG.i("Toggle speaker button is pressed");
        if (Utils.isOreoAndAbove() && TelecomUtils.isAudioPickerEnabled()) {
            int size = this.telecomCallAudioManager.getSupportedAudioRoutes().size();
            if (size > 2) {
                this.viewContract.showAudioPickerPopup();
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

    @Override // java.util.Observer
    public void update(Observable observable, final Object obj) {
        if (observable instanceof AudioStateObservable) {
            AudioRoutes audioRoutes = (AudioRoutes) obj;
            if (audioRoutes == AudioRoutes.SPEAKER) {
                this.viewContract.showSpeakerOn();
            } else if (audioRoutes != AudioRoutes.EARPIECE) {
            } else {
                this.viewContract.showSpeakerOff();
            }
        } else if (observable instanceof NameChangeObservable) {
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.presenters.active.-$$Lambda$ActiveEnhancedProcessingAudioPresenter$doKrxVCFuRNQJMmf_Z2XiATcSVk
                @Override // java.lang.Runnable
                public final void run() {
                    ActiveEnhancedProcessingAudioPresenter.this.lambda$update$0$ActiveEnhancedProcessingAudioPresenter(obj);
                }
            });
        } else if (!(observable instanceof TelecomCallAudioRouteObservable) || !Utils.isMarshMallowAndAbove()) {
        } else {
            setSpeakerIconState((CallAudioState) obj);
        }
    }
}
