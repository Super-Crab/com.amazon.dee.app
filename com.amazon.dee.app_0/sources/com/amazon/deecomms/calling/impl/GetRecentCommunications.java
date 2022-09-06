package com.amazon.deecomms.calling.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import java.io.IOException;
import java.text.MessageFormat;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes12.dex */
public class GetRecentCommunications {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetRecentCommunications.class);
    private final ACMSClient mClient = new ACMSClient(MetricKeys.OP_GET_DEVICE);
    private String mRequestId;

    public GetRecentCommunicationsResponse[] getRecentCommunications(@NonNull String str, int i, int i2, @Nullable String[] strArr) throws ServiceException, InterruptedException {
        if (!StringUtils.isEmpty(str)) {
            Integer valueOf = Integer.valueOf(i);
            boolean z = true;
            StringBuilder sb = new StringBuilder(MessageFormat.format(AppUrl.GET_RECENT_COMMUNICATIONS, str, valueOf, Integer.valueOf(i2)));
            boolean z2 = strArr != null;
            if (strArr.length <= 0) {
                z = false;
            }
            if (z2 & z) {
                for (String str2 : strArr) {
                    sb.append("&eventTypes=");
                    sb.append(str2);
                }
            }
            IHttpClient.Request request = this.mClient.request(sb.toString());
            this.mRequestId = request.getRequestId();
            try {
                IHttpClient.Response mo3640execute = request.authenticatedAsCurrentCommsUser().get().mo3640execute();
                GetRecentCommunicationsResponse[] getRecentCommunicationsResponseArr = (GetRecentCommunicationsResponse[]) mo3640execute.convert(GetRecentCommunicationsResponse[].class);
                mo3640execute.close();
                return getRecentCommunicationsResponseArr;
            } catch (IOException e) {
                LOG.e("IO Exception while retrieving recent communications list", e);
                return null;
            }
        }
        LOG.e("[getRecentCommunications] commsId is null");
        throw new ServiceException("Null commsId given");
    }

    public String getRequestId() {
        return this.mRequestId;
    }
}
