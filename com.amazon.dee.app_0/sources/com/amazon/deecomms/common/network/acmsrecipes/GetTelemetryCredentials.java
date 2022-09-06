package com.amazon.deecomms.common.network.acmsrecipes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.telemetry.TelemetryCredentials;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class GetTelemetryCredentials {
    private static final CommsLogger LOG = CommsLogger.getLogger(GetTelemetryCredentials.class);
    private final ACMSClient client = new ACMSClient(MetricKeys.OP_TELEMETRY_CREDENTIALS);

    public TelemetryCredentials execute(@NonNull String str) throws ServiceException {
        GeneratedOutlineSupport1.outline159("retrieving telemetry credentials for  ", str, LOG);
        try {
            IHttpClient.Response mo3640execute = this.client.request(MessageFormat.format(AppUrl.GET_TELEMETRY_CREDENTIALS, str)).authenticatedAsCurrentCommsUser().get().mo3640execute();
            TelemetryCredentials telemetryCredentials = (TelemetryCredentials) mo3640execute.convert(TelemetryCredentials.class);
            mo3640execute.close();
            return telemetryCredentials;
        } catch (IOException e) {
            LOG.e("Exception while retrieving telemetry credentials", e);
            return null;
        }
    }

    @Nullable
    public TelemetryCredentials executeSwallowException(@NonNull String str) {
        try {
            return execute(str);
        } catch (ServiceException e) {
            LOG.e("Received the following exception", e);
            return null;
        }
    }
}
