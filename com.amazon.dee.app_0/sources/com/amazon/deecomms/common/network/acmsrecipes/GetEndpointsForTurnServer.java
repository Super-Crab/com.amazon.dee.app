package com.amazon.deecomms.common.network.acmsrecipes;

import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class GetEndpointsForTurnServer {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetEndpointsForTurnServer.class);
    private final ACMSClient client;
    private String requestId;

    public GetEndpointsForTurnServer() {
        this.client = new ACMSClient(MetricKeys.OP_CREATE_CALL_ENDPOINTS);
    }

    private String executeInternal(String str, String str2, String str3, String str4) throws ServiceException {
        String format = MessageFormat.format(AppUrl.GET_TURN_CREDENTIALS_FROM_IDENTITY, str);
        ObjectNode mo7041createObjectNode = new ObjectMapper().mo7041createObjectNode();
        if (str2 != null) {
            mo7041createObjectNode.put(Constants.KEY_RECIPIENT_COMMS_ID, str2);
        }
        if (str4 != null) {
            mo7041createObjectNode.put(Constants.KEY_GRUU, str4);
        }
        if (str3 != null) {
            mo7041createObjectNode.put(Constants.KEY_RECIPIENT_PHONE_NUMBER, str3);
        }
        IHttpClient.Request request = this.client.request(format);
        this.requestId = request.getRequestId();
        try {
            IHttpClient.Response mo3640execute = request.authenticatedAsCurrentCommsUser().post("application/json", mo7041createObjectNode).mo3640execute();
            String body = mo3640execute.getBody();
            mo3640execute.close();
            return body;
        } catch (IOException e) {
            LOG.e("IO Exception while getEndpointsForTURNServer", e);
            return null;
        }
    }

    public String execute(String str, String str2) throws ServiceException {
        if (str == null || str2 == null) {
            return null;
        }
        return executeInternal(str, str2, null, null);
    }

    public String executeSwallowException(String str, String str2) {
        try {
            return execute(str, str2);
        } catch (ServiceException e) {
            LOG.e("Error while GetEndpointsForTurnServer", e);
            return null;
        }
    }

    public String executeWithGruu(String str, String str2, String str3) throws ServiceException {
        if (str == null || str2 == null || str3 == null) {
            return null;
        }
        return executeInternal(str, str2, null, str3);
    }

    public String executeWithPhoneNumber(String str, String str2) throws ServiceException {
        if (str == null || str2 == null) {
            return null;
        }
        return executeInternal(str, null, str2, null);
    }

    @Nullable
    public String getRequestId() {
        return this.requestId;
    }

    public GetEndpointsForTurnServer(OkHttpClientWrapper okHttpClientWrapper, String str, CommsLogger commsLogger) {
        this.client = new ACMSClient(okHttpClientWrapper, str, commsLogger, MetricKeys.OP_CREATE_CALL_ENDPOINTS);
    }
}
