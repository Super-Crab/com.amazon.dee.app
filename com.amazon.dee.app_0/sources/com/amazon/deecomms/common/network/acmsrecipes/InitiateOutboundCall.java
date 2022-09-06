package com.amazon.deecomms.common.network.acmsrecipes;

import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.model.InitiateOutboundCallRequest;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import java.io.IOException;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class InitiateOutboundCall {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, InitiateOutboundCallRequest.class);
    private final ACMSClient mClient = new ACMSClient(MetricKeys.OP_INITIATE_OUTBOUND_CALL);
    private String mRequestId;

    public String execute(@NonNull String str, @NonNull InitiateOutboundCallRequest initiateOutboundCallRequest) throws ServiceException {
        IHttpClient.Request request = this.mClient.request(MessageFormat.format(AppUrl.INITIATE_OUTBOUND_CALL, str));
        this.mRequestId = request.getRequestId();
        try {
            IHttpClient.Response mo3640execute = request.authenticatedAsCurrentCommsUser().post("application/json", initiateOutboundCallRequest).mo3640execute();
            String body = mo3640execute.getBody();
            mo3640execute.close();
            return body;
        } catch (IOException unused) {
            LOG.e("IO Exception while initiating an outbound call");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.OP_GET_MPU_ENABLED_IOEXCEPTION);
            return null;
        }
    }

    public String getRequestId() {
        return this.mRequestId;
    }
}
