package com.amazon.deecomms.contacts.operations;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.contacts.model.GetCommsIdentities;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import java.io.IOException;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class HomeGroupIdentitiesDownloader {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, HomeGroupIdentitiesDownloader.class);
    private final ACMSClient mACMSClient = new ACMSClient(MetricKeys.OP_GET_COMMS_ID_BY_HOMEGROUP);

    public GetCommsIdentities.Response downloadIdentities(String str) {
        if (TextUtils.isEmpty(str)) {
            LOG.e("Empty homegroupId");
            return null;
        }
        Context context = CommsDaggerWrapper.getComponent().getContext();
        if (ContactsProviderUtils.isCommsIdUngettable(context, str)) {
            LOG.i("HG Id ungettable");
            return null;
        }
        try {
            IHttpClient.Response mo3640execute = this.mACMSClient.request(MessageFormat.format(AppUrl.GET_HOMEGROUP_MEMBERS, str)).authenticated().get().mo3640execute();
            try {
                GetCommsIdentities.Response response = (GetCommsIdentities.Response) mo3640execute.convert(GetCommsIdentities.Response.class);
                mo3640execute.close();
                return response;
            } finally {
            }
        } catch (ServiceException e) {
            LOG.e("Error occurred while downloading members of homegroup", e);
            if (e.getHttpResponseCode() != null && e.getHttpResponseCode().intValue() == 404) {
                LOG.i("Marking homegroupId ungettable");
                ContactsProviderUtils.markCommsIdUngettable(context, str);
            }
            return null;
        } catch (IOException e2) {
            LOG.e("Error occurred while downloading members of homegroup", e2);
            return null;
        }
    }
}
