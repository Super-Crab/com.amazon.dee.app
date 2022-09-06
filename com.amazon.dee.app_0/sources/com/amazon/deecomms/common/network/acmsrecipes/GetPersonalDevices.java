package com.amazon.deecomms.common.network.acmsrecipes;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.contacts.model.GetPersonalDevicesResponse;
import java.io.IOException;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class GetPersonalDevices {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetPersonalDevices.class);
    private final ACMSClient mClient = new ACMSClient(MetricKeys.OP_GET_PERSONAL_DEVICES);
    private String mRequestId;

    public GetPersonalDevicesResponse getMasterDevice(String str, String str2) throws ServiceException, InterruptedException {
        IHttpClient.Request request = this.mClient.request(MessageFormat.format(AppUrl.GET_PERSONAL_DEVICES, str));
        this.mRequestId = request.getRequestId();
        try {
            IHttpClient.Response mo3640execute = request.authenticated(str2).get().mo3640execute();
            GetPersonalDevicesResponse getPersonalDevicesResponse = (GetPersonalDevicesResponse) mo3640execute.convert(GetPersonalDevicesResponse.class);
            mo3640execute.close();
            return getPersonalDevicesResponse;
        } catch (IOException e) {
            LOG.e("IO Exception while retrieving personal devices list", e);
            return null;
        }
    }

    public String getRequestId() {
        return this.mRequestId;
    }
}
