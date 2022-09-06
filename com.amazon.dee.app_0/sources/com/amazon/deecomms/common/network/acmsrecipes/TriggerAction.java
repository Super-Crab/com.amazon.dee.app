package com.amazon.deecomms.common.network.acmsrecipes;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.incallexperiences.model.TriggerActionResponse;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class TriggerAction {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, TriggerAction.class);
    private final ACMSClient mClient = new ACMSClient(MetricKeys.OP_TRIGGER_ACTION);
    private String mRequestId;

    public String getRequestId() {
        return this.mRequestId;
    }

    public TriggerActionResponse triggerAction(String str, String str2, String str3) throws ServiceException {
        String format = MessageFormat.format(AppUrl.TRIGGER_ACTION, str2);
        GeneratedOutlineSupport1.outline159("Calling: ", format, LOG);
        IHttpClient.Request request = this.mClient.request(format);
        this.mRequestId = request.getRequestId();
        ObjectNode mo7041createObjectNode = new ObjectMapper().mo7041createObjectNode();
        mo7041createObjectNode.put("participantId", str);
        mo7041createObjectNode.put("actionId", str3);
        try {
            IHttpClient.Response mo3640execute = request.authenticatedAsCurrentCommsUser().postJson(mo7041createObjectNode).mo3640execute();
            TriggerActionResponse triggerActionResponse = (TriggerActionResponse) mo3640execute.convert(TriggerActionResponse.class);
            mo3640execute.close();
            return triggerActionResponse;
        } catch (IOException e) {
            LOG.e("IO Exception while retrieving devices list", e);
            return null;
        }
    }
}
