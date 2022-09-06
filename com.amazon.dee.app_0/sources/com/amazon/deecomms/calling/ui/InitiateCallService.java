package com.amazon.deecomms.calling.ui;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.sipclient.SipStatusCode;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.util.SetupCallHelper;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.GetEndpointsForTurnServer;
import com.amazon.deecomms.common.service.CommsJobIntentService;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class InitiateCallService extends CommsJobIntentService {
    public static final String ERROR_CODE = "ERROR_CODE";
    public static final int ERROR_CODE_INVALID_MEDIA_RELAY = -4;
    public static final int ERROR_CODE_MISSING_COMMS_ID = -1;
    public static final int ERROR_CODE_MISSING_RESPONSE_CODE = -3;
    public static final int ERROR_CODE_MISSING_SIP_URI = -2;
    public static final int ERROR_CODE_UNKNOWN = 0;
    public static final String INTENT_MESSAGE_KEY = "MESSAGE";
    public static final String MAKE_CALL_MESSAGE = "MAKE_CALL_MESSAGE";
    public static final String TRIGGER_MAKE_CALL = "TRIGGER_MAKE_CALL";
    public static final String TRIGGER_MAKE_CALL_RESULT = "TRIGGER_MAKE_CALL_RESULT";
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState sipClientState;
    private static final int JOB_ID = CommsJobIntentService.generateJobId(InitiateCallService.class);
    public static final String TAG = "InitiateCallService";
    private static final CommsLogger LOG = CommsLogger.getLogger(TAG, InitiateCallService.class);

    public InitiateCallService() {
        CommsDaggerWrapper.getComponent().inject(this);
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, InitiateCallService.class, JOB_ID, intent);
    }

    private void handleCallInitException(Intent intent, CallType callType, GetEndpointsForTurnServer getEndpointsForTurnServer, ServiceException serviceException) {
        LOG.e("Exception occurred while contacting service", serviceException);
        MetricsHelper.recordFailedToConnectToTurnMetric(this.sipClientState.getCallId(), serviceException.getHttpResponseCode(), serviceException.getRequestId());
        int intValue = serviceException.getHttpResponseCode() != null ? serviceException.getHttpResponseCode().intValue() : -3;
        intent.putExtra(ERROR_CODE, intValue);
        intent.putExtra("requestId", getEndpointsForTurnServer.getRequestId());
        intent.putExtra("source", GetEndpointsForTurnServer.class.getSimpleName());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        SetupCallHelper.MetadataBuilder withSource = SetupCallHelper.MetadataBuilder.newBuilder().withCallType(callType).withCallOrigin(Call.Side.Local).withSource(SetupCallHelper.Source.FetchStunTurnIce);
        if (intValue == SipStatusCode.FORBIDDEN.getCode()) {
            SetupCallHelper.recordInitiationMetrics(this.sipClientState.getCallId(), this.sipClientState.getCspId(), SetupCallHelper.ResultType.SUCCESS, Integer.valueOf(intValue), withSource.withReason("Call was blocked"));
        } else {
            SetupCallHelper.recordInitiationMetrics(this.sipClientState.getCallId(), this.sipClientState.getCspId(), intValue, withSource.withReason("Error getting endpoints"));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0141 A[Catch: ServiceException -> 0x020b, TryCatch #2 {ServiceException -> 0x020b, blocks: (B:47:0x012b, B:50:0x0136, B:51:0x013b, B:53:0x0141, B:55:0x0148), top: B:72:0x012b }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01b5  */
    @Override // androidx.core.app.JobIntentService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onHandleWork(android.content.Intent r23) {
        /*
            Method dump skipped, instructions count: 590
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.calling.ui.InitiateCallService.onHandleWork(android.content.Intent):void");
    }
}
