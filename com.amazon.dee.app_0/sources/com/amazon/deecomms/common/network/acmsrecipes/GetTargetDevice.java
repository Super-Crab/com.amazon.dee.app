package com.amazon.deecomms.common.network.acmsrecipes;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.model.TargetDeviceModel;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import java.io.IOException;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class GetTargetDevice {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetTargetDevice.class);
    private final ACMSClient client = new ACMSClient(MetricKeys.OP_GET_TARGET_DEVICE);
    private String mRequestId;

    public TargetDeviceModel execute(String str) throws ServiceException {
        if (str == null) {
            return null;
        }
        LOG.i("Attempting to retrieve target device");
        IHttpClient.Request request = this.client.request(MessageFormat.format(AppUrl.GET_TARGET_DEVICE, str));
        this.mRequestId = request.getRequestId();
        try {
            IHttpClient.Response mo3640execute = request.authenticatedAsCurrentCommsUser().get().mo3640execute();
            TargetDeviceModel targetDeviceModel = (TargetDeviceModel) mo3640execute.convert(TargetDeviceModel.class);
            mo3640execute.close();
            return targetDeviceModel;
        } catch (IOException e) {
            LOG.e(" IO Exception while retrieving targetDevice", e);
            return null;
        }
    }

    public String getRequestId() {
        return this.mRequestId;
    }
}
