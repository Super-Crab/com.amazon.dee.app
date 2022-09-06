package com.amazon.deecomms.calling.presenters.incoming;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.calling.contracts.incoming.IncomingAudioCallViewContract;
import com.amazon.deecomms.calling.contracts.incoming.IncomingCallPresenterContract;
import com.amazon.deecomms.calling.controller.CallActionsDispatcher;
import com.amazon.deecomms.calling.model.CallModel;
import com.amazon.deecomms.calling.ui.NameChangeObservable;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.perms.PermissionsHelper;
import com.amazon.deecomms.util.ThreadUtils;
import java.util.Observable;
import java.util.Observer;
/* loaded from: classes12.dex */
public class IncomingEnhancedProcessingAudioPresenter implements IncomingCallPresenterContract, Observer {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, IncomingEnhancedProcessingAudioPresenter.class);
    private final CallActionsDispatcher callActionsDispatcher;
    private final Bundle callIntentExtras;
    private final CallModel callModel;
    private boolean hasAcceptedCall;
    private boolean hasRejectedCall;
    private final IncomingAudioCallViewContract incomingCallViewContract;
    private final NameChangeObservable nameChangeObservable;
    private final SipClientState sipClientState;

    public IncomingEnhancedProcessingAudioPresenter(@NonNull IncomingAudioCallViewContract incomingAudioCallViewContract, @NonNull CallModel callModel, @NonNull Bundle bundle, @NonNull CallActionsDispatcher callActionsDispatcher, @NonNull NameChangeObservable nameChangeObservable, @NonNull SipClientState sipClientState) {
        this.incomingCallViewContract = incomingAudioCallViewContract;
        this.callModel = callModel;
        this.callActionsDispatcher = callActionsDispatcher;
        this.nameChangeObservable = nameChangeObservable;
        this.callIntentExtras = bundle;
        this.sipClientState = sipClientState;
        this.hasAcceptedCall = bundle.getBoolean(Constants.KEY_HAVE_ANSWERED_CALL, false);
        this.hasRejectedCall = bundle.getBoolean(Constants.KEY_HAVE_DECLINED_CALL, false);
    }

    private void processIntentActions() {
        if (this.hasAcceptedCall) {
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.HEADUP_NOTIFICATION_ANSWER_CALL);
        } else if (this.hasRejectedCall) {
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.HEADUP_NOTIFICATION_DECLINE_CALL);
        }
        if (this.sipClientState.getCallState() == SipClientState.CallState.INBOUND_RINGING) {
            if (this.hasAcceptedCall) {
                checkForPermissionsAndAcceptCall();
            } else if (!this.hasRejectedCall) {
            } else {
                rejectCall();
            }
        }
    }

    @Override // com.amazon.deecomms.calling.contracts.incoming.IncomingCallPresenterContract
    public void checkForPermissionsAndAcceptCall() {
        MetricsHelper.startTimerMetric(TimerMetric.generateClickstream(MetricKeys.CALL_TIME_TO_ANSWER));
        String[] filterGrantedPermissions = PermissionsHelper.filterGrantedPermissions(PermissionsHelper.getPermissionListForAudio());
        if (filterGrantedPermissions.length > 0) {
            LOG.i("Permissions required to accept audio call");
            this.incomingCallViewContract.showIncomingCallPermissions(filterGrantedPermissions);
            return;
        }
        LOG.i("Accepting incoming audio call");
        this.callActionsDispatcher.acceptCall(true);
    }

    public /* synthetic */ void lambda$update$0$IncomingEnhancedProcessingAudioPresenter(Object obj) {
        this.incomingCallViewContract.showRemoteParticipantName((String) obj);
    }

    @Override // com.amazon.deecomms.calling.contracts.BasePresenterContract
    public void onViewCreated() {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.SCREEN_CALL_IN_SHOWN);
        this.incomingCallViewContract.showRemoteParticipantName(this.callModel.getRemoteParticipantName());
        this.incomingCallViewContract.showCallStatus(this.callModel.getCallStatus());
        if (this.hasAcceptedCall || this.hasRejectedCall) {
            this.incomingCallViewContract.hideCallControls();
        }
        this.incomingCallViewContract.activateCallControls();
        this.callActionsDispatcher.notifyIncomingCall();
        this.nameChangeObservable.addObserver(this);
        processIntentActions();
    }

    @Override // com.amazon.deecomms.calling.contracts.BasePresenterContract
    public void onViewDestroyed() {
        LOG.i("Incoming audio call view is destroyed.");
    }

    @Override // com.amazon.deecomms.calling.contracts.incoming.IncomingCallPresenterContract
    public void rejectCall() {
        LOG.i("Rejecting incoming audio call");
        this.callActionsDispatcher.rejectCall();
    }

    @Override // java.util.Observer
    public void update(Observable observable, final Object obj) {
        if (observable instanceof NameChangeObservable) {
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.presenters.incoming.-$$Lambda$IncomingEnhancedProcessingAudioPresenter$-SKQRUPGhnumUbxcn08sqPeY9R8
                @Override // java.lang.Runnable
                public final void run() {
                    IncomingEnhancedProcessingAudioPresenter.this.lambda$update$0$IncomingEnhancedProcessingAudioPresenter(obj);
                }
            });
        }
    }
}
