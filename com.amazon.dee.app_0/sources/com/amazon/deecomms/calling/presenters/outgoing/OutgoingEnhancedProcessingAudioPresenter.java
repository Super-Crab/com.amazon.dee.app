package com.amazon.deecomms.calling.presenters.outgoing;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.contracts.outgoing.OutgoingAudioCallViewContract;
import com.amazon.deecomms.calling.contracts.outgoing.OutgoingCallPresenterContract;
import com.amazon.deecomms.calling.controller.CallActionsDispatcher;
import com.amazon.deecomms.calling.enums.AudioRoutes;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.model.CallModel;
import com.amazon.deecomms.calling.ui.NameChangeObservable;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.media.audio.AudioStateObservable;
import com.amazon.deecomms.media.audio.CallMediaControlFacade;
import com.amazon.deecomms.util.ThreadUtils;
import java.util.Observable;
import java.util.Observer;
/* loaded from: classes12.dex */
public class OutgoingEnhancedProcessingAudioPresenter implements OutgoingCallPresenterContract, Observer {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, OutgoingEnhancedProcessingAudioPresenter.class);
    private final AudioStateObservable audioStateObservable;
    private final CallActionsDispatcher callActionsDispatcher;
    private final Bundle callIntentExtras;
    private final CallMediaControlFacade callMediaControlFacade;
    private final CallModel callModel;
    private final NameChangeObservable nameChangeObservable;
    private final OutgoingAudioCallViewContract outgoingCallViewContract;
    private final SipClientState sipClientState;

    public OutgoingEnhancedProcessingAudioPresenter(@NonNull OutgoingAudioCallViewContract outgoingAudioCallViewContract, @NonNull CallModel callModel, @NonNull Bundle bundle, @NonNull SipClientState sipClientState, @NonNull CallActionsDispatcher callActionsDispatcher, @NonNull AudioStateObservable audioStateObservable, @NonNull NameChangeObservable nameChangeObservable, @NonNull CallMediaControlFacade callMediaControlFacade) {
        this.outgoingCallViewContract = outgoingAudioCallViewContract;
        this.callActionsDispatcher = callActionsDispatcher;
        this.callModel = callModel;
        this.sipClientState = sipClientState;
        this.callIntentExtras = bundle;
        this.audioStateObservable = audioStateObservable;
        this.callMediaControlFacade = callMediaControlFacade;
        this.nameChangeObservable = nameChangeObservable;
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingCallPresenterContract
    public void endCall() {
        LOG.i("End call pressed");
        this.callActionsDispatcher.cancelOutgoingCall();
    }

    public /* synthetic */ void lambda$update$0$OutgoingEnhancedProcessingAudioPresenter(Object obj) {
        this.outgoingCallViewContract.showRemoteParticipantName((String) obj);
    }

    @Override // com.amazon.deecomms.calling.contracts.BasePresenterContract
    public void onViewCreated() {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.SCREEN_CALL_OUT_SHOWN);
        this.outgoingCallViewContract.showRemoteParticipantName(this.callModel.getRemoteParticipantName());
        this.outgoingCallViewContract.showCallStatus(this.callModel.getCallStatus());
        this.outgoingCallViewContract.showOutgoingCallIsInProgress();
        this.outgoingCallViewContract.activateCallControls();
        if (this.sipClientState.getCallType() == CallType.PSTN) {
            this.outgoingCallViewContract.showDialpad();
        } else {
            this.outgoingCallViewContract.hideDialpad();
        }
        this.callActionsDispatcher.initiateCall(this.callIntentExtras);
        this.audioStateObservable.addObserver(this);
    }

    @Override // com.amazon.deecomms.calling.contracts.BasePresenterContract
    public void onViewDestroyed() {
        LOG.i("View has been destroyed.");
        this.audioStateObservable.deleteObserver(this);
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingCallPresenterContract
    public void toggleSpeaker() {
        LOG.i("Toggle speaker pressed");
        this.callMediaControlFacade.toggleSpeaker();
    }

    @Override // java.util.Observer
    public void update(Observable observable, final Object obj) {
        if (observable instanceof AudioStateObservable) {
            AudioRoutes audioRoutes = (AudioRoutes) obj;
            if (audioRoutes == AudioRoutes.SPEAKER) {
                this.outgoingCallViewContract.showSpeakerOn();
            } else if (audioRoutes != AudioRoutes.EARPIECE) {
            } else {
                this.outgoingCallViewContract.showSpeakerOff();
            }
        } else if (!(observable instanceof NameChangeObservable)) {
        } else {
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.presenters.outgoing.-$$Lambda$OutgoingEnhancedProcessingAudioPresenter$Mz1aEFt6zD6Bnmmi0eE5zb3AS7E
                @Override // java.lang.Runnable
                public final void run() {
                    OutgoingEnhancedProcessingAudioPresenter.this.lambda$update$0$OutgoingEnhancedProcessingAudioPresenter(obj);
                }
            });
        }
    }
}
