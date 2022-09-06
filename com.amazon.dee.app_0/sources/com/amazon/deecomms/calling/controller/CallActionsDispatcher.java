package com.amazon.deecomms.calling.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.ui.CallActivity;
import com.amazon.deecomms.calling.ui.NewCallActivity;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.service.DeviceCallingAndroidService;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes12.dex */
public class CallActionsDispatcher {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallActionsDispatcher.class);
    private final CallInitiationAuthority callInitiationAuthority;
    private final CallManager callManager;
    private final CapabilitiesManager capabilitiesManager;
    private final Context context;
    private final SipClientState sipClientState;

    @Inject
    public CallActionsDispatcher(@NonNull Context context, @NonNull @Named("CurrentCall") SipClientState sipClientState, @NonNull CallManager callManager, @NonNull CallInitiationAuthority callInitiationAuthority, @NonNull CapabilitiesManager capabilitiesManager) {
        this.context = context;
        this.callManager = callManager;
        this.sipClientState = sipClientState;
        this.capabilitiesManager = capabilitiesManager;
        this.callInitiationAuthority = callInitiationAuthority;
    }

    private void addCallIdToMetric(CommsMetric commsMetric) {
        commsMetric.getMetadata().put(MetricKeys.META_COMMS_ITEM_ID, this.sipClientState.getCallId());
    }

    public void acceptCall(boolean z) {
        Intent intent;
        LOG.i("Accepting call");
        CounterMetric generateClickstream = CounterMetric.generateClickstream(MetricKeys.CALL_ANSWER);
        addCallIdToMetric(generateClickstream);
        MetricsHelper.recordCounterMetric(generateClickstream, Double.valueOf(1.0d));
        Intent intent2 = new Intent(this.context, DeviceCallingAndroidService.class);
        if (z) {
            intent2.setAction(Constants.ACCEPT_INCOMING_VIDEO_CALL);
        } else {
            intent2.setAction(Constants.ACCEPT_INCOMING_AUDIO_CALL);
        }
        this.context.startService(intent2);
        if (this.callInitiationAuthority.isNewCallInitiationUIFlowEnabled(Optional.absent(), Optional.absent())) {
            intent = new Intent(this.context, NewCallActivity.class);
        } else {
            intent = new Intent(this.context, CallActivity.class);
        }
        intent.putExtra(Constants.LAUNCH_FRAGMENT_KEY, Constants.FRAGMENT_ACTIVE_CALL_KEY);
        intent.setFlags(268566528);
        this.context.startActivity(intent);
    }

    public void cancelOutgoingCall() {
        LOG.i("Cancel outgoing call");
        CounterMetric generateClickstream = CounterMetric.generateClickstream(MetricKeys.CALL_END_CALL);
        addCallIdToMetric(generateClickstream);
        MetricsHelper.recordCounterMetric(generateClickstream, Double.valueOf(1.0d));
        Intent intent = new Intent(this.context, DeviceCallingAndroidService.class);
        intent.setAction(Constants.CANCEL_OUTGOING_CALL);
        this.context.startService(intent);
    }

    public void endActiveCall() {
        CounterMetric generateClickstream = CounterMetric.generateClickstream(MetricKeys.CALL_HANGUP);
        addCallIdToMetric(generateClickstream);
        MetricsHelper.recordCounterMetric(generateClickstream, Double.valueOf(1.0d));
        LOG.i("End active call");
        Intent intent = new Intent(this.context, DeviceCallingAndroidService.class);
        intent.setAction(Constants.END_ACTIVE_CALL);
        this.context.startService(intent);
    }

    public void initiateCall(Bundle bundle) {
        if (this.callManager.isAnyActiveCallPresent()) {
            LOG.e("There is already an active call. So not initiating a video call.");
        } else if (this.callInitiationAuthority.isNewCallInitiationUIFlowEnabled(Optional.absent(), Optional.absent())) {
            Intent intent = new Intent(this.context, DeviceCallingAndroidService.class);
            intent.setAction(Constants.Calling.MAKE_OUTGOING_CALL_WITH_NEW_ARCHITECTURE);
            intent.putExtra(Constants.CALL_START_TIME, bundle.getLong(Constants.CALL_START_TIME));
            this.context.startService(intent);
        } else if (bundle != null) {
            if (bundle.getString("COMMS_ID") == null) {
                return;
            }
            String string = bundle.getString("COMMS_ID");
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("CommsID for the Initiate Call Task ");
            outline1.append(LOG.sensitive(string));
            commsLogger.i(outline1.toString());
            CallType fromBundle = CallType.fromBundle(bundle, Constants.CALL_TYPE);
            String string2 = bundle.getString(Constants.DEVICE_GRUU);
            String fromBundle2 = CallProvider.fromBundle(bundle, Constants.CALL_PROVIDER);
            Intent intent2 = new Intent(this.context, DeviceCallingAndroidService.class);
            intent2.putExtras(bundle);
            intent2.setAction(Constants.MAKE_OUTGOING_CALL);
            intent2.putExtra(Constants.CALLEE_COMMS_ID, string);
            intent2.putExtra(Constants.CALL_TYPE, fromBundle.toString());
            intent2.putExtra(Constants.CALL_PROVIDER, fromBundle2);
            intent2.putExtra(Constants.DEVICE_GRUU, string2);
            intent2.putExtra(Constants.CALL_START_TIME, bundle.getLong(Constants.CALL_START_TIME));
            this.context.startService(intent2);
        } else {
            LOG.w("Required parameters to make the outgoing call have not been received");
        }
    }

    public void notifyIncomingCall() {
        LOG.i("[Comms-calling]: notifyIncomingCall");
        Intent intent = new Intent(this.context, DeviceCallingAndroidService.class);
        intent.setAction(Constants.NOTIFY_INCOMING_CALL);
        this.context.startService(intent);
    }

    public void rejectCall() {
        CounterMetric generateClickstream = CounterMetric.generateClickstream(MetricKeys.CALL_DECLINE);
        addCallIdToMetric(generateClickstream);
        MetricsHelper.recordCounterMetric(generateClickstream, Double.valueOf(1.0d));
        Intent intent = new Intent(this.context, DeviceCallingAndroidService.class);
        intent.setAction(Constants.REJECT_INCOMING_CALL);
        this.context.startService(intent);
    }
}
