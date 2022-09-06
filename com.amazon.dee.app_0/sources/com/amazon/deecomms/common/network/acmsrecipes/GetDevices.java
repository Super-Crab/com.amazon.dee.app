package com.amazon.deecomms.common.network.acmsrecipes;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.ndt.model.GetDevicesResponse;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import java.io.IOException;
import java.text.MessageFormat;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes12.dex */
public class GetDevices {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetDevices.class);
    private final ACMSClient mClient = new ACMSClient(MetricKeys.OP_GET_DEVICE);
    private String mRequestId;

    public GetDevicesResponse getDevices(String str) throws ServiceException, InterruptedException {
        boolean isFireOS = Utils.isFireOS();
        String str2 = AppUrl.GET_DEVICES;
        if (isFireOS) {
            try {
                String value = DeviceDataStore.getInstance(CommsDaggerWrapper.getComponent().getContext()).getValue(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append("&deviceSerialNumber=");
                sb.append(value);
                str2 = sb.toString();
            } catch (DeviceDataStoreException e) {
                LOG.e("Unable to retrieve the device serial number", e);
            }
        }
        if (!StringUtils.isEmpty(str)) {
            IHttpClient.Request request = this.mClient.request(MessageFormat.format(str2, str));
            this.mRequestId = request.getRequestId();
            try {
                IHttpClient.Response mo3640execute = request.authenticatedAsCurrentCommsUser().get().mo3640execute();
                GetDevicesResponse getDevicesResponse = (GetDevicesResponse) mo3640execute.convert(GetDevicesResponse.class);
                mo3640execute.close();
                return getDevicesResponse;
            } catch (IOException e2) {
                LOG.e("IO Exception while retrieving devices list", e2);
                return null;
            }
        }
        LOG.e("[GetDevices] commsId is null");
        throw new ServiceException("Null commsId given");
    }

    public String getRequestId() {
        return this.mRequestId;
    }
}
