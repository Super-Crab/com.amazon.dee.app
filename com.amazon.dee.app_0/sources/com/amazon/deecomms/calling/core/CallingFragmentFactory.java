package com.amazon.deecomms.calling.core;

import android.media.AudioManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.alexa.AlexaInterface;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract;
import com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract;
import com.amazon.deecomms.calling.contracts.incoming.IncomingCallPresenterContract;
import com.amazon.deecomms.calling.contracts.outgoing.OutgoingCallPresenterContract;
import com.amazon.deecomms.calling.controller.CallActionsDispatcher;
import com.amazon.deecomms.calling.controller.CallDowngradedObservable;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.datachannel.handler.CallingDataChannelEventHandler;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsState;
import com.amazon.deecomms.calling.incallexperiences.effects.ReactionsMenuPresenter;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.model.CallModel;
import com.amazon.deecomms.calling.presenters.active.ActiveEnhancedProcessingAudioPresenter;
import com.amazon.deecomms.calling.presenters.active.ActiveEnhancedProcessingVideoPresenter;
import com.amazon.deecomms.calling.presenters.incoming.IncomingEnhancedProcessingAudioPresenter;
import com.amazon.deecomms.calling.presenters.incoming.IncomingEnhancedProcessingVideoPresenter;
import com.amazon.deecomms.calling.presenters.outgoing.OutgoingEnhancedProcessingAudioPresenter;
import com.amazon.deecomms.calling.presenters.outgoing.OutgoingEnhancedProcessingVideoPresenter;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioManager;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.amazon.deecomms.calling.ui.EndCallFragment;
import com.amazon.deecomms.calling.ui.EnhancedProcessingStateObservable;
import com.amazon.deecomms.calling.ui.NameChangeObservable;
import com.amazon.deecomms.calling.ui.NewCallActivity;
import com.amazon.deecomms.calling.ui.PipVisibilityObservable;
import com.amazon.deecomms.calling.ui.ep.ActiveEnhancedProcessingVideoCallFragment;
import com.amazon.deecomms.calling.ui.ep.IncomingEnhancedProcessingVideoCallFragment;
import com.amazon.deecomms.calling.ui.ep.OutgoingEnhancedProcessingVideoCallFragment;
import com.amazon.deecomms.calling.ui.ep.audio.ActiveEnhancedProcessingAudioCallFragment;
import com.amazon.deecomms.calling.ui.ep.audio.IncomingEnhancedProcessingAudioCallFragment;
import com.amazon.deecomms.calling.ui.ep.audio.OutgoingEnhancedProcessingAudioCallFragment;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.media.audio.AudioStateObservable;
import com.amazon.deecomms.media.audio.CallMediaControlFacade;
import com.android.tools.r8.GeneratedOutlineSupport;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class CallingFragmentFactory {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallingFragmentFactory.class);
    @Inject
    AlexaInterface alexaInterface;
    @Inject
    AudioManager audioManager;
    @Inject
    AudioStateObservable audioStateObservable;
    @Inject
    CallActionsDispatcher callActionsDispatcher;
    @Inject
    CallDowngradedObservable callDowngradedObservable;
    @Inject
    CallHistoryHelper callHistoryHelper;
    @Inject
    CallManager callManager;
    @Inject
    CallMediaControlFacade callMediaControlFacade;
    private CallModel callModel;
    @Inject
    CallTimerManager callTimerManager;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    EffectsState effectsState;
    @Inject
    EnhancedProcessingStateObservable enhancedProcessingStateObservable;
    @Inject
    NameChangeObservable nameChangeObservable;
    @Inject
    PipVisibilityObservable pipVisibilityObservable;
    @Inject
    RealTimeTextEnablementAuthority realTimeTextEnablementAuthority;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState sipClientState;
    @Inject
    TelecomBridge telecomBridge;
    @Inject
    TelecomCallAudioManager telecomCallAudioManager;
    @Inject
    TelecomCallAudioRouteObservable telecomCallAudioRouteObservable;

    public CallingFragmentFactory(@NonNull CallModel callModel) {
        CommsDaggerWrapper.getComponent().inject(this);
        this.callModel = callModel;
    }

    public Fragment getFragment(@NonNull CallContext callContext, @NonNull String str, @NonNull Bundle bundle, @NonNull NewCallActivity newCallActivity) {
        if (callContext.isVideoCall() && !this.callManager.isCallDowngraded()) {
            return getFragmentForVideoCall(callContext, str, bundle, newCallActivity);
        }
        return getFragmentForAudioCall(callContext, str, bundle, newCallActivity);
    }

    @VisibleForTesting
    Fragment getFragmentForAudioCall(@NonNull CallContext callContext, @NonNull String str, @NonNull Bundle bundle, @NonNull NewCallActivity newCallActivity) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -411586040) {
            if (str.equals(Constants.FRAGMENT_ACTIVE_CALL_KEY)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == -302938767) {
            if (str.equals(Constants.FRAGMENT_END_CALL_KEY)) {
                c = 3;
            }
            c = 65535;
        } else if (hashCode != 1143886882) {
            if (hashCode == 1871462568 && str.equals(Constants.FRAGMENT_INCOMING_CALL_KEY)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(Constants.FRAGMENT_OUTGOING_CALL_KEY)) {
                c = 2;
            }
            c = 65535;
        }
        if (c == 0) {
            ActiveEnhancedProcessingAudioCallFragment activeEnhancedProcessingAudioCallFragment = new ActiveEnhancedProcessingAudioCallFragment();
            activeEnhancedProcessingAudioCallFragment.setPresenter((ActiveAudioCallPresenterContract) new ActiveEnhancedProcessingAudioPresenter(activeEnhancedProcessingAudioCallFragment, activeEnhancedProcessingAudioCallFragment, this.callModel, this.callActionsDispatcher, this.callHistoryHelper, this.sipClientState, this.callTimerManager, this.callManager, this.alexaInterface, this.callMediaControlFacade, this.audioStateObservable, this.nameChangeObservable, this.telecomCallAudioManager, this.telecomBridge, this.telecomCallAudioRouteObservable, this.realTimeTextEnablementAuthority));
            return activeEnhancedProcessingAudioCallFragment;
        } else if (c == 1) {
            IncomingEnhancedProcessingAudioCallFragment incomingEnhancedProcessingAudioCallFragment = new IncomingEnhancedProcessingAudioCallFragment();
            incomingEnhancedProcessingAudioCallFragment.setPresenter((IncomingCallPresenterContract) new IncomingEnhancedProcessingAudioPresenter(incomingEnhancedProcessingAudioCallFragment, this.callModel, bundle, this.callActionsDispatcher, this.nameChangeObservable, this.sipClientState));
            return incomingEnhancedProcessingAudioCallFragment;
        } else if (c == 2) {
            OutgoingEnhancedProcessingAudioCallFragment outgoingEnhancedProcessingAudioCallFragment = new OutgoingEnhancedProcessingAudioCallFragment();
            outgoingEnhancedProcessingAudioCallFragment.setPresenter((OutgoingCallPresenterContract) new OutgoingEnhancedProcessingAudioPresenter(outgoingEnhancedProcessingAudioCallFragment, this.callModel, bundle, this.sipClientState, this.callActionsDispatcher, this.audioStateObservable, this.nameChangeObservable, this.callMediaControlFacade));
            return outgoingEnhancedProcessingAudioCallFragment;
        } else if (c != 3) {
            GeneratedOutlineSupport.outline3("No Fragment to be displayed for Audio call with launch reason ", str, LOG);
            return null;
        } else {
            return new EndCallFragment();
        }
    }

    @VisibleForTesting
    Fragment getFragmentForVideoCall(@NonNull CallContext callContext, @NonNull String str, @NonNull Bundle bundle, @NonNull NewCallActivity newCallActivity) {
        char c;
        ReactionsMenuPresenter reactionsMenuPresenter;
        int hashCode = str.hashCode();
        if (hashCode == -411586040) {
            if (str.equals(Constants.FRAGMENT_ACTIVE_CALL_KEY)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == -302938767) {
            if (str.equals(Constants.FRAGMENT_END_CALL_KEY)) {
                c = 3;
            }
            c = 65535;
        } else if (hashCode != 1143886882) {
            if (hashCode == 1871462568 && str.equals(Constants.FRAGMENT_INCOMING_CALL_KEY)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(Constants.FRAGMENT_OUTGOING_CALL_KEY)) {
                c = 2;
            }
            c = 65535;
        }
        if (c == 0) {
            ActiveEnhancedProcessingVideoCallFragment activeEnhancedProcessingVideoCallFragment = new ActiveEnhancedProcessingVideoCallFragment();
            activeEnhancedProcessingVideoCallFragment.setPresenter((ActiveVideoCallPresenterContract) new ActiveEnhancedProcessingVideoPresenter(activeEnhancedProcessingVideoCallFragment, activeEnhancedProcessingVideoCallFragment, this.callModel, this.callActionsDispatcher, this.callHistoryHelper, newCallActivity, newCallActivity, this.sipClientState, this.callTimerManager, callContext, this.callMediaControlFacade, this.audioStateObservable, this.telecomCallAudioRouteObservable, this.alexaInterface, this.capabilitiesManager, this.nameChangeObservable, this.telecomCallAudioManager, this.telecomBridge, this.audioManager, this.enhancedProcessingStateObservable, this.pipVisibilityObservable, this.realTimeTextEnablementAuthority));
            activeEnhancedProcessingVideoCallFragment.setCallingDataChannelHandler(new CallingDataChannelEventHandler(this.callDowngradedObservable, this.pipVisibilityObservable, this.capabilitiesManager, this.sipClientState));
            if (this.capabilitiesManager.isReactionsEnabled()) {
                ReactionsMenuPresenter reactionsMenuPresenter2 = new ReactionsMenuPresenter(newCallActivity, this.sipClientState, callContext.isGroupCall(), this.sipClientState.getCaller().getId(), this.sipClientState.getCallee().getId(), this.effectsState, this.capabilitiesManager);
                activeEnhancedProcessingVideoCallFragment.setReactionsMenuPresenter(reactionsMenuPresenter2);
                reactionsMenuPresenter = reactionsMenuPresenter2;
            } else {
                reactionsMenuPresenter = null;
            }
            if (this.capabilitiesManager.isWorldsEnabled()) {
                activeEnhancedProcessingVideoCallFragment.setEffectsMenuPresenter(new EffectsMenuPresenter(newCallActivity, this.sipClientState, callContext.isGroupCall(), this.sipClientState.getCaller().getId(), this.sipClientState.getCallee().getId(), reactionsMenuPresenter, this.effectsState, this.capabilitiesManager));
            }
            return activeEnhancedProcessingVideoCallFragment;
        } else if (c == 1) {
            IncomingEnhancedProcessingVideoCallFragment incomingEnhancedProcessingVideoCallFragment = new IncomingEnhancedProcessingVideoCallFragment();
            incomingEnhancedProcessingVideoCallFragment.setPresenter((IncomingCallPresenterContract) new IncomingEnhancedProcessingVideoPresenter(incomingEnhancedProcessingVideoCallFragment, this.callModel, bundle, this.nameChangeObservable, this.callActionsDispatcher, newCallActivity, this.sipClientState));
            return incomingEnhancedProcessingVideoCallFragment;
        } else if (c == 2) {
            OutgoingEnhancedProcessingVideoCallFragment outgoingEnhancedProcessingVideoCallFragment = new OutgoingEnhancedProcessingVideoCallFragment();
            outgoingEnhancedProcessingVideoCallFragment.setPresenter((OutgoingCallPresenterContract) new OutgoingEnhancedProcessingVideoPresenter(outgoingEnhancedProcessingVideoCallFragment, newCallActivity, this.callModel, bundle, this.callActionsDispatcher, this.callMediaControlFacade, this.nameChangeObservable));
            return outgoingEnhancedProcessingVideoCallFragment;
        } else if (c != 3) {
            GeneratedOutlineSupport.outline3("No Fragment to be displayed for Video call with launch reason ", str, LOG);
            return null;
        } else {
            return new EndCallFragment();
        }
    }

    @VisibleForTesting
    public CallingFragmentFactory(@NonNull SipClientState sipClientState, @NonNull CallModel callModel, @NonNull CallActionsDispatcher callActionsDispatcher, @NonNull CallHistoryHelper callHistoryHelper, @NonNull CallTimerManager callTimerManager, @NonNull CallMediaControlFacade callMediaControlFacade, @NonNull AudioStateObservable audioStateObservable, @NonNull NameChangeObservable nameChangeObservable, @NonNull AlexaInterface alexaInterface, @NonNull CapabilitiesManager capabilitiesManager, @NonNull AudioManager audioManager, @NonNull TelecomCallAudioRouteObservable telecomCallAudioRouteObservable, @NonNull CallDowngradedObservable callDowngradedObservable, @NonNull PipVisibilityObservable pipVisibilityObservable, @NonNull CallManager callManager) {
        this.sipClientState = sipClientState;
        this.callModel = callModel;
        this.callActionsDispatcher = callActionsDispatcher;
        this.callHistoryHelper = callHistoryHelper;
        this.callTimerManager = callTimerManager;
        this.callMediaControlFacade = callMediaControlFacade;
        this.audioStateObservable = audioStateObservable;
        this.nameChangeObservable = nameChangeObservable;
        this.alexaInterface = alexaInterface;
        this.capabilitiesManager = capabilitiesManager;
        this.callManager = callManager;
        this.telecomCallAudioRouteObservable = telecomCallAudioRouteObservable;
        this.callDowngradedObservable = callDowngradedObservable;
        this.audioManager = audioManager;
        this.pipVisibilityObservable = pipVisibilityObservable;
        this.realTimeTextEnablementAuthority = new RealTimeTextEnablementAuthority(capabilitiesManager, sipClientState);
    }
}
