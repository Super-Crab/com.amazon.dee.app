package com.amazon.deecomms.calling.controller;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.comms.calling.sipclient.RealTimeTextMetrics;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.enums.AssistCspId;
import com.amazon.deecomms.calling.enums.CallInitiationErrorState;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.enums.EnhancedProcessingState;
import com.amazon.deecomms.calling.model.BeginCallPayload;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.ui.NewCallActivity;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.acmsrecipes.GetMpuEnabled;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes12.dex */
public class ValidBeginCallPayloadHandler {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ValidBeginCallPayloadHandler.class);
    private BeginCallPayload beginCallPayload;
    private final CallContext callContext;
    private CallInitiationErrorState callErrorState;
    private final CallHelper callHelper;
    private final CallInitiationAuthority callInitiationAuthority;
    private long callStartTime;
    private final CommsIdentityManager commsIdentityManager;
    private final Context context;
    private final LocalBroadcastManager localBroadcastManager;
    private final SipClientState sipClientState;

    @VisibleForTesting
    public ValidBeginCallPayloadHandler(@NonNull Context context, @NonNull CallHelper callHelper, @NonNull CommsIdentityManager commsIdentityManager, @NonNull SipClientState sipClientState, @NonNull LocalBroadcastManager localBroadcastManager, @NonNull CallInitiationAuthority callInitiationAuthority, @NonNull CallContext callContext) {
        this.callErrorState = CallInitiationErrorState.NONE;
        this.context = context;
        this.callHelper = callHelper;
        this.sipClientState = sipClientState;
        this.commsIdentityManager = commsIdentityManager;
        this.callContext = callContext;
        this.localBroadcastManager = localBroadcastManager;
        this.callInitiationAuthority = callInitiationAuthority;
    }

    private CallInitiationErrorState canPlaceACall() {
        SipClientState.CallState callState = this.sipClientState.getCallState();
        if (callState != SipClientState.CallState.CALLING && callState != SipClientState.CallState.CALLING_INITIATED) {
            if (!Utils.isNetworkConnected()) {
                LOG.e("Offline. Cannot place a call");
                CallUtils.handleCallInitiationErrors();
                return CallInitiationErrorState.OFFLINE;
            } else if (!Utils.shouldAllowAlexaCall(this.context)) {
                this.callHelper.handleCanNotPlaceCall(null);
                return CallInitiationErrorState.PSTN_CALL_IN_PROGRESS;
            } else {
                return CallInitiationErrorState.NONE;
            }
        }
        LOG.i("Cannot place call, another call has already been initiated");
        return CallInitiationErrorState.ALEXA_CALL_ALREADY_IN_PROGRESS;
    }

    private CallType computeCallType() {
        BeginCallPayload.SipPayload sipPayload = this.beginCallPayload.sipCommand.payload;
        List<BeginCallPayload.MediaStream> list = sipPayload.offer.mediaStreams;
        String str = sipPayload.callProvider;
        boolean z = true;
        boolean z2 = isEnhancedProcessCall() || (str != null && (str.equalsIgnoreCase(CallProvider.A2A) || str.equalsIgnoreCase("acn")));
        Iterator<BeginCallPayload.MediaStream> it2 = list.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            } else if (it2.next().type.equalsIgnoreCase("VIDEO")) {
                break;
            }
        }
        return CallType.compute(this.beginCallPayload.sipCommand.payload.isDropIn.booleanValue(), z, false, z2);
    }

    private String computeLaunchFragmentKey(@NonNull CallInitiationErrorState callInitiationErrorState) {
        return callInitiationErrorState == CallInitiationErrorState.NONE ? Constants.FRAGMENT_OUTGOING_CALL_KEY : Constants.FRAGMENT_END_CALL_KEY;
    }

    private String computeName() {
        String str = this.beginCallPayload.displayInfo.calleePartyInfo.name;
        return !StringUtils.isEmpty(str) ? str.replace("<scrub>", "").replace("</scrub>", "").trim() : str;
    }

    private boolean isEnhancedProcessCall() {
        return EnhancedProcessingState.ON.getValue().equalsIgnoreCase(this.beginCallPayload.displayInfo.mediaSettingsInfo.enhancedProcessing);
    }

    private void measureTimeToRing() {
        this.callStartTime = System.currentTimeMillis();
        MetricsHelper.startTimerMetric(TimerMetric.generateClickstream(MetricKeys.CALL_TIME_TO_RING));
        if (computeCallType().isDropIn()) {
            MetricsHelper.startTimerMetric(TimerMetric.generateClickstream(MetricKeys.CALL_TIME_TO_DROP_IN));
        }
    }

    private void retrieveMPUSettingsIfNeeded(@NonNull Runnable runnable) {
        String homeGroupId = this.commsIdentityManager.getHomeGroupId("ValidBeginCallPayloadHandler.retrieveMPUSettingsIfNeeded", false);
        BeginCallPayload.SipPayload sipPayload = this.beginCallPayload.sipCommand.payload;
        new MPUSettingHandler(homeGroupId, this.sipClientState, Collections.singletonList(Constants.GET_SETTING), sipPayload.callProvider, sipPayload.callee.id, new GetMpuEnabled(), runnable).execute(new Void[0]);
    }

    private void setSipClientState() {
        this.sipClientState.setCallState(SipClientState.CallState.CALLING_INITIATED);
        this.sipClientState.setCallType(computeCallType());
        this.sipClientState.setCallProvider(this.beginCallPayload.sipCommand.payload.callProvider);
        this.sipClientState.setSrtpKey(this.beginCallPayload.sipCommand.payload.mediaInfo.srtp.keying);
        this.sipClientState.setEnhancedProcessingState(EnhancedProcessingState.valueOf(this.beginCallPayload.displayInfo.mediaSettingsInfo.enhancedProcessing));
        this.sipClientState.setRealTimeTextMetrics(new RealTimeTextMetrics(RealTimeTextConstants.RTT_NOT_REQUESTED, "DISABLED"));
        this.sipClientState.setBeginCallPayload(this.beginCallPayload);
    }

    private boolean shouldRetrieveMPUSetting() {
        return EnhancedProcessingState.ON.toString().equalsIgnoreCase(this.beginCallPayload.displayInfo.mediaSettingsInfo.enhancedProcessing);
    }

    public boolean computeIsAssistCall() {
        return AssistCspId.isValidCsp(this.beginCallPayload.getCspId());
    }

    @VisibleForTesting
    public boolean computeIsGroupCall() {
        for (Map<String, Object> map : this.beginCallPayload.sipCommand.payload.headers) {
            if (map.containsKey(Constants.GROUPID_HEADER)) {
                this.sipClientState.setGroupCall(true);
                return true;
            }
        }
        this.sipClientState.setGroupCall(false);
        return false;
    }

    public void handle(@NonNull BeginCallPayload beginCallPayload) {
        this.callErrorState = canPlaceACall();
        this.beginCallPayload = beginCallPayload;
        CallInitiationErrorState callInitiationErrorState = this.callErrorState;
        if (callInitiationErrorState == CallInitiationErrorState.NONE) {
            measureTimeToRing();
            setSipClientState();
            setOutgoingCallContext();
            final boolean z = computeIsGroupCall() || computeIsAssistCall();
            setPreviousCallStateForEndCallScreenHack();
            if (shouldRetrieveMPUSetting()) {
                retrieveMPUSettingsIfNeeded(new Runnable() { // from class: com.amazon.deecomms.calling.controller.-$$Lambda$ValidBeginCallPayloadHandler$oUBfbE00JLjFTnHOtYHuHWUd8Us
                    @Override // java.lang.Runnable
                    public final void run() {
                        ValidBeginCallPayloadHandler.this.lambda$handle$0$ValidBeginCallPayloadHandler(z);
                    }
                });
                return;
            } else if (z) {
                launchCallActivity(this.callErrorState);
                return;
            } else {
                launchNewCallActivity(Constants.Calling.OUTGOING_CALL_SCREEN);
                return;
            }
        }
        showErrorScreen(callInitiationErrorState.computeErrorTitle(), this.callErrorState.computeErrorMessage());
    }

    public /* synthetic */ void lambda$handle$0$ValidBeginCallPayloadHandler(boolean z) {
        if (z) {
            launchCallActivity(this.callErrorState);
        } else {
            launchNewCallActivity(Constants.Calling.OUTGOING_CALL_SCREEN);
        }
    }

    @VisibleForTesting
    public Intent launchCallActivity(@NonNull CallInitiationErrorState callInitiationErrorState) {
        Intent intent = new Intent();
        intent.putExtra("COMMS_ID", this.beginCallPayload.sipCommand.payload.callee.id);
        intent.putExtra(Constants.REMOTE_PARTICIPANT_NAME, computeName());
        intent.putExtra(Constants.CALL_START_TIME, this.callStartTime);
        intent.putExtra(Constants.LAUNCH_FRAGMENT_KEY, computeLaunchFragmentKey(callInitiationErrorState));
        intent.putExtra(Constants.CALLEE_COMMS_ID, this.beginCallPayload.sipCommand.payload.callee.id);
        intent.putExtra(Constants.CALLER_COMMS_ID, this.beginCallPayload.sipCommand.payload.caller.id);
        intent.putExtra(Constants.CALL_TYPE, computeCallType().toString());
        intent.putExtra(Constants.CALL_PROVIDER, this.beginCallPayload.sipCommand.payload.callProvider);
        intent.putExtra(Constants.KEY_RECIPIENT_PHONE_NUMBER, "");
        intent.putExtra(Constants.Calling.CSP_ID, this.beginCallPayload.getCspId());
        intent.putExtra(Constants.DEVICE_GRUU, "");
        intent.putExtra(Constants.KEY_CALL_INITIATION_SCREEN_NAME, "GroupContactCard");
        intent.putExtra(Constants.CALL_ID, this.beginCallPayload.sipCommand.payload.callId);
        intent.putExtra(Constants.GROUP_CALL, computeIsGroupCall());
        intent.putExtra(Constants.CALLING_NEW_ARCHITECTURE, true);
        intent.addFlags(268435456);
        intent.setAction(Constants.SHOW_CALL_UI);
        if (Constants.FRAGMENT_END_CALL_KEY.equals(computeLaunchFragmentKey(callInitiationErrorState))) {
            if (this.beginCallPayload.sipCommand.payload.isDropIn.booleanValue()) {
                intent.putExtra(Constants.KEY_DROP_IN_NOT_AVAILABLE, true);
            }
            intent.putExtra(Constants.CALL_END_STATUS, Utils.getStringFromResource(R.string.callee_unavailable));
            CallUtils.handleCallInitiationErrors();
        } else {
            intent.putExtra(Constants.SET_SIP_CLIENT_STATE, true);
        }
        this.localBroadcastManager.sendBroadcast(intent);
        return intent;
    }

    @VisibleForTesting
    public void launchNewCallActivity(@NonNull String str) {
        Intent intent = new Intent();
        intent.setClass(this.context, NewCallActivity.class);
        intent.putExtra(Constants.Calling.CSP_ID, this.beginCallPayload.getCspId());
        intent.putExtra(Constants.Calling.SCREEN_NAME, str);
        intent.setFlags(268435456);
        this.context.startActivity(intent);
    }

    @VisibleForTesting
    public void setBeginCallPayload(@NonNull BeginCallPayload beginCallPayload) {
        this.beginCallPayload = beginCallPayload;
    }

    @VisibleForTesting
    public void setOutgoingCallContext() {
        this.callContext.setBeginCallPayload(this.beginCallPayload);
        String computeName = computeName();
        if (computeName != null && computeName.length() > 0) {
            this.callContext.setRemoteParticipantName(computeName);
            this.sipClientState.setRemoteParticipantName(computeName);
        }
        this.sipClientState.setRTTSettingEnabled(this.callContext.isRTTEnabledByCaller());
        this.sipClientState.setCspId(this.beginCallPayload.getCspId());
    }

    @VisibleForTesting
    public void setPreviousCallStateForEndCallScreenHack() {
        if (!this.sipClientState.isGroupCall() && !AssistCspId.isValidCsp(this.sipClientState.getCspId())) {
            this.callInitiationAuthority.setPreviousCallState(false);
        } else {
            this.callInitiationAuthority.setPreviousCallState(true);
        }
    }

    @VisibleForTesting
    public void showErrorScreen(int i, int i2) {
        Intent intent = new Intent();
        intent.setClass(this.context, NewCallActivity.class);
        intent.putExtra(Constants.Calling.SCREEN_NAME, Constants.Calling.ERROR_DIALOG);
        intent.putExtra(Constants.Calling.ERROR_DIALOG_TITLE, i);
        intent.putExtra(Constants.Calling.ERROR_DIALOG_MESSAGE, i2);
        intent.setFlags(268435456);
        this.context.startActivity(intent);
    }

    @Inject
    public ValidBeginCallPayloadHandler(@NonNull Context context, @NonNull CommsIdentityManager commsIdentityManager, @NonNull @Named("CurrentCall") SipClientState sipClientState, @NonNull CallInitiationAuthority callInitiationAuthority, @NonNull CallContext callContext) {
        this.callErrorState = CallInitiationErrorState.NONE;
        this.context = context;
        this.commsIdentityManager = commsIdentityManager;
        this.callHelper = new CallHelper();
        this.sipClientState = sipClientState;
        this.callContext = callContext;
        this.localBroadcastManager = LocalBroadcastManager.getInstance(context);
        this.callInitiationAuthority = callInitiationAuthority;
    }
}
