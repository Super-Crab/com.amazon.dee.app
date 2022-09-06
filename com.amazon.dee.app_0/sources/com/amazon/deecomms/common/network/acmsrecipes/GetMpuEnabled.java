package com.amazon.deecomms.common.network.acmsrecipes;

import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.model.GetMPUEnabledModel;
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
public class GetMpuEnabled {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetMpuEnabled.class);
    private final ACMSClient mClient = new ACMSClient(MetricKeys.OP_GET_MPU_ENABLED);
    private String mRequestId;

    public GetMPUEnabledModel getMpuEnabled(@NonNull String str, @NonNull String str2) throws ServiceException {
        IHttpClient.Request request = this.mClient.request(MessageFormat.format(AppUrl.MPU_ENABLED, str, str2));
        this.mRequestId = request.getRequestId();
        try {
            IHttpClient.Response mo3640execute = request.authenticatedAsCurrentCommsUser().get().mo3640execute();
            GetMPUEnabledModel getMPUEnabledModel = (GetMPUEnabledModel) mo3640execute.convert(GetMPUEnabledModel.class);
            mo3640execute.close();
            return getMPUEnabledModel;
        } catch (IOException unused) {
            LOG.e("IO Exception while retrieving MPU Enabled status");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.OP_GET_MPU_ENABLED_IOEXCEPTION);
            return null;
        }
    }

    public String getRequestId() {
        return this.mRequestId;
    }
}
