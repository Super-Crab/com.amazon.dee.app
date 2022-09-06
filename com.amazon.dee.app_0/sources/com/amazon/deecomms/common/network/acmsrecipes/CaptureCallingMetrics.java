package com.amazon.deecomms.common.network.acmsrecipes;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.model.CallingMetricsModel;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.ServiceException;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class CaptureCallingMetrics {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CaptureCallingMetrics.class);
    private final ACMSClient client = new ACMSClient(MetricKeys.OP_BIZ_CAPTURE_CALLING);

    public String execute(String str, CallingMetricsModel callingMetricsModel) throws ServiceException {
        if (str == null || callingMetricsModel == null) {
            return null;
        }
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Sending calling metrics for ");
        outline1.append(callingMetricsModel.getCallId());
        commsLogger.d(outline1.toString());
        return this.client.request(MessageFormat.format(AppUrl.CAPTURE_CALLING_BIZ_METRICS, str)).authenticatedAsCurrentCommsUser().postJson(callingMetricsModel).mo3640execute().getBody();
    }

    public String executeSwallowException(String str, CallingMetricsModel callingMetricsModel) {
        try {
            return execute(str, callingMetricsModel);
        } catch (ServiceException e) {
            LOG.e("Error while CaptureCallingMetrics", e);
            return null;
        }
    }
}
