package com.amazon.deecomms.common.network.acmsrecipes;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.model.CallQualityMetricsModel;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.ServiceException;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class CaptureCallQualityMetrics {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CaptureCallQualityMetrics.class);
    private final ACMSClient client = new ACMSClient(MetricKeys.OP_BIZ_CAPTURE_CALL_QUALITY);

    public String execute(String str, CallQualityMetricsModel callQualityMetricsModel) throws ServiceException {
        if (str == null || callQualityMetricsModel == null) {
            return null;
        }
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Sending call quality metrics for ");
        outline1.append(callQualityMetricsModel.getCallId());
        commsLogger.d(outline1.toString());
        return this.client.request(MessageFormat.format(AppUrl.CAPTURE_CALL_QUALITY_BIZ_METRICS, str)).authenticatedAsCurrentCommsUser().postJson(callQualityMetricsModel).mo3640execute().getBody();
    }

    public String executeSwallowException(String str, CallQualityMetricsModel callQualityMetricsModel) {
        try {
            return execute(str, callQualityMetricsModel);
        } catch (ServiceException e) {
            LOG.e("Error while CaptureCallQualityMetrics", e);
            return null;
        }
    }
}
