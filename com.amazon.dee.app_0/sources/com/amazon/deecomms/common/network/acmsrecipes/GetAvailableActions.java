package com.amazon.deecomms.common.network.acmsrecipes;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.incallexperiences.model.GetAvailableActionsResponse;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class GetAvailableActions {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetAvailableActions.class);
    private final ACMSClient mClient = new ACMSClient(MetricKeys.OP_GET_AVAILABLE_ACTIONS);
    private String mRequestId;

    public GetAvailableActionsResponse getAvailableActions(String str, String str2) throws ServiceException {
        String format = MessageFormat.format(AppUrl.AVAILABLE_ACTIONS, str2, str);
        GeneratedOutlineSupport1.outline159("Calling: ", format, LOG);
        IHttpClient.Request request = this.mClient.request(format);
        this.mRequestId = request.getRequestId();
        try {
            IHttpClient.Response mo3640execute = request.authenticatedAsCurrentCommsUser().get().mo3640execute();
            GetAvailableActionsResponse getAvailableActionsResponse = (GetAvailableActionsResponse) mo3640execute.convert(GetAvailableActionsResponse.class);
            mo3640execute.close();
            return getAvailableActionsResponse;
        } catch (IOException e) {
            LOG.e("IO Exception while retrieving devices list", e);
            return null;
        }
    }

    public String getRequestId() {
        return this.mRequestId;
    }
}
