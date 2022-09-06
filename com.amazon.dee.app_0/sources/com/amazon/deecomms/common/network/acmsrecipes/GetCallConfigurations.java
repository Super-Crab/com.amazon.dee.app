package com.amazon.deecomms.common.network.acmsrecipes;

import com.amazon.deecomms.calling.model.CallConfigurationsModel;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.ServiceException;
import java.io.IOException;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class GetCallConfigurations {
    private final ACMSClient mClient = new ACMSClient(MetricKeys.OP_GET_CALL_CONFIGURATIONS);

    public CallConfigurationsModel execute(String str, String str2) throws ServiceException, IOException {
        return (CallConfigurationsModel) this.mClient.request(MessageFormat.format(AppUrl.CALL_CONFIGURATIONS_URL, str)).addQueryParameter("network", str2).addQueryParameter(AppUrl.ACMS.QueryParam.Keys.DEVICE_CLIENT_TYPE, "Android").authenticatedAsCurrentCommsUser().get().mo3640execute().convert(CallConfigurationsModel.class);
    }
}
