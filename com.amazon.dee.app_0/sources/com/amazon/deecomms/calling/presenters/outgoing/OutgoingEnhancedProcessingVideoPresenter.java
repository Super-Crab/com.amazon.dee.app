package com.amazon.deecomms.calling.presenters.outgoing;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.contracts.outgoing.OutgoingCallPresenterContract;
import com.amazon.deecomms.calling.contracts.outgoing.OutgoingVideoCallViewContract;
import com.amazon.deecomms.calling.controller.CallActionsDispatcher;
import com.amazon.deecomms.calling.enums.CallState;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.model.CallModel;
import com.amazon.deecomms.calling.ui.NameChangeObservable;
import com.amazon.deecomms.calling.ui.listener.VideoStreamingListener;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.media.audio.CallMediaControlFacade;
import com.amazon.deecomms.util.ThreadUtils;
import java.util.Observable;
import java.util.Observer;
/* loaded from: classes12.dex */
public class OutgoingEnhancedProcessingVideoPresenter implements Observer, OutgoingCallPresenterContract {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, OutgoingEnhancedProcessingVideoPresenter.class);
    private final CallActionsDispatcher callActionsDispatcher;
    private final Bundle callIntentExtras;
    private final CallMediaControlFacade callMediaControlFacade;
    private final CallModel callModel;
    private final NameChangeObservable nameChangeObservable;
    private final OutgoingVideoCallViewContract outgoingCallViewContract;
    private final VideoStreamingListener videoStreamingListener;

    public OutgoingEnhancedProcessingVideoPresenter(@NonNull OutgoingVideoCallViewContract outgoingVideoCallViewContract, @NonNull VideoStreamingListener videoStreamingListener, @NonNull CallModel callModel, @NonNull Bundle bundle, @NonNull CallActionsDispatcher callActionsDispatcher, @NonNull CallMediaControlFacade callMediaControlFacade, @NonNull NameChangeObservable nameChangeObservable) {
        this.outgoingCallViewContract = outgoingVideoCallViewContract;
        this.callActionsDispatcher = callActionsDispatcher;
        this.videoStreamingListener = videoStreamingListener;
        this.callModel = callModel;
        this.callIntentExtras = bundle;
        this.callMediaControlFacade = callMediaControlFacade;
        this.nameChangeObservable = nameChangeObservable;
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingCallPresenterContract
    public void endCall() {
        LOG.i("End call pressed");
        this.callActionsDispatcher.cancelOutgoingCall();
    }

    public /* synthetic */ void lambda$update$0$OutgoingEnhancedProcessingVideoPresenter(Object obj) {
        this.outgoingCallViewContract.showRemoteParticipantName((String) obj);
    }

    @Override // com.amazon.deecomms.calling.contracts.BasePresenterContract
    public void onViewCreated() {
        this.nameChangeObservable.addObserver(this);
        this.outgoingCallViewContract.showRemoteParticipantName(this.callModel.getRemoteParticipantName());
        this.outgoingCallViewContract.showCallStatus(this.callModel.getCallStatus());
        this.outgoingCallViewContract.hideCallScreenTitleMessage();
        this.outgoingCallViewContract.showOutgoingCallIsInProgress();
        this.outgoingCallViewContract.activateCallControls();
        this.outgoingCallViewContract.showMaximisedSelfView();
        if (CallUtils.isDropInCall()) {
            this.outgoingCallViewContract.hideRemoteParticipantName();
            this.outgoingCallViewContract.showCallControls(true);
        } else {
            this.outgoingCallViewContract.showCallControls(false);
        }
        this.callActionsDispatcher.initiateCall(this.callIntentExtras);
        this.videoStreamingListener.onStartStreamingVideo(CallState.OUTGOING, CallType.VIDEO);
    }

    @Override // com.amazon.deecomms.calling.contracts.BasePresenterContract
    public void onViewDestroyed() {
        LOG.i("View has been destroyed.");
    }

    @Override // com.amazon.deecomms.calling.contracts.outgoing.OutgoingCallPresenterContract
    public void toggleSpeaker() {
    }

    @Override // java.util.Observer
    public void update(Observable observable, final Object obj) {
        if (observable instanceof NameChangeObservable) {
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.presenters.outgoing.-$$Lambda$OutgoingEnhancedProcessingVideoPresenter$D86fSXumkjDYHNzemuH8XQBFhOo
                @Override // java.lang.Runnable
                public final void run() {
                    OutgoingEnhancedProcessingVideoPresenter.this.lambda$update$0$OutgoingEnhancedProcessingVideoPresenter(obj);
                }
            });
        }
    }
}
