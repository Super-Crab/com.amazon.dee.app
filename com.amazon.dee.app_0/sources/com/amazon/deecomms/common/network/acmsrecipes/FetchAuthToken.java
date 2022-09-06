package com.amazon.deecomms.common.network.acmsrecipes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.sip.SipAuthTokenResponse;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class FetchAuthToken {
    private static final String KEY_TYPE = "type";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, FetchAuthToken.class);
    private final ACMSClient client = new ACMSClient(MetricKeys.OP_CREATE_AUTH_TOKEN);
    private String requestId;
    private final String tokenType;

    public FetchAuthToken(@NonNull String str) {
        this.tokenType = str;
    }

    public SipAuthTokenResponse execute(String str) throws ServiceException {
        if (str == null) {
            return null;
        }
        String format = MessageFormat.format(AppUrl.GET_SIP_AUTH_TOKEN, str);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Fetching token with type ");
        outline1.append(this.tokenType);
        commsLogger.d(outline1.toString());
        ObjectNode mo7041createObjectNode = new ObjectMapper().mo7041createObjectNode();
        mo7041createObjectNode.put("type", this.tokenType);
        try {
            IHttpClient.Request request = this.client.request(format);
            this.requestId = request.getRequestId();
            SipAuthTokenResponse sipAuthTokenResponse = (SipAuthTokenResponse) request.authenticatedAsCurrentCommsUser().post("application/json", mo7041createObjectNode).mo3640execute().convert(SipAuthTokenResponse.class);
            CommsLogger commsLogger2 = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Fetched token with type ");
            sb.append(this.tokenType);
            commsLogger2.i(sb.toString());
            return sipAuthTokenResponse;
        } catch (ServiceException e) {
            CommsLogger commsLogger3 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Failed fetching token with type ");
            outline12.append(this.tokenType);
            commsLogger3.e(outline12.toString(), e);
            throw e;
        }
    }

    @Nullable
    public String getRequestId() {
        return this.requestId;
    }
}
