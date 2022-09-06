package com.amazon.deecomms.common.service;

import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.controller.CommandProcessor;
import com.amazon.deecomms.calling.model.MediaRelayInfoModel;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.GetEndpointsForTurnServer;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.base.Strings;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class PerformCallReconnectTask extends AsyncTask<Void, Void, Void> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PerformCallReconnectTask.class);
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState clientState;
    @Inject
    CommandProcessor commandProcessor;
    private final String mMySipUri;
    private String remoteCommsId;
    private final String remoteUri;

    public PerformCallReconnectTask(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        this.mMySipUri = str;
        this.remoteCommsId = str2;
        this.remoteUri = str3;
        CommsDaggerWrapper.getComponent().inject(this);
    }

    private String getIdFromUri(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        String str2 = (split.length > 1 ? split[1] : "").split("@")[0];
        if (Strings.isNullOrEmpty(str2)) {
            return null;
        }
        return GeneratedOutlineSupport.outline0(Constants.COMMS_ID_PREFIX, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(Void... voidArr) {
        String execute;
        MediaRelayInfoModel constructMediaRelayInfoFromJSON;
        Call currentActiveCall = this.clientState.getCurrentActiveCall();
        if (currentActiveCall == null) {
            LOG.w("Requested to PerformCallReconnectTask but no call object found");
            return null;
        }
        String commsId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("PerformCallReconnectTask", false);
        if (!TextUtils.isEmpty(commsId) && !TextUtils.isEmpty(this.remoteCommsId) && !TextUtils.isEmpty(this.remoteUri)) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Original remoteCommsId: ");
            outline1.append(this.remoteCommsId);
            commsLogger.d(outline1.toString());
            String idFromUri = getIdFromUri(this.remoteUri);
            if (idFromUri == null) {
                idFromUri = this.remoteCommsId;
            }
            this.remoteCommsId = idFromUri;
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("New remoteCommsId: ");
            outline12.append(this.remoteCommsId);
            commsLogger2.d(outline12.toString());
            GetEndpointsForTurnServer getEndpointsForTurnServer = new GetEndpointsForTurnServer();
            try {
                String deviceGruu = this.clientState.getDeviceGruu();
                if (currentActiveCall.isDropInCall() && !TextUtils.isEmpty(deviceGruu)) {
                    LOG.i("Call was a drop-in and found the device gruu used");
                    execute = getEndpointsForTurnServer.executeWithGruu(commsId, this.remoteCommsId, deviceGruu);
                } else {
                    LOG.i("Calling createEndpoints");
                    execute = getEndpointsForTurnServer.execute(commsId, this.remoteCommsId);
                }
                LOG.d(String.format("Media relay info json %s", execute));
                constructMediaRelayInfoFromJSON = Utils.constructMediaRelayInfoFromJSON(execute);
            } catch (ServiceException e) {
                MetricsHelper.recordFailedToConnectToTurnMetric(this.clientState.getCallId(), e.getHttpResponseCode(), e.getRequestId());
                LOG.e("Error trying to call createEndpoints while performing a call reconnect", e);
            }
            if (constructMediaRelayInfoFromJSON == null) {
                LOG.w("Unable to reconnect call as media relay info was not available");
                return null;
            }
            this.commandProcessor.reconnectCall(this.mMySipUri, constructMediaRelayInfoFromJSON);
            return null;
        }
        LOG.w("Requested to PerformCallReconnectTask but missing comms id, remote comms id, or remote uri");
        return null;
    }
}
