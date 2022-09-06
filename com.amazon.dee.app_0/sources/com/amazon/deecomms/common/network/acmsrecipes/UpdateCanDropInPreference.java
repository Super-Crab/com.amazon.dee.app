package com.amazon.deecomms.common.network.acmsrecipes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.contacts.model.DropInState;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.google.common.base.Strings;
import java.io.IOException;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class UpdateCanDropInPreference {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, UpdateCanDropInPreference.class);
    private final ACMSClient mACMSClient;

    public UpdateCanDropInPreference() {
        this.mACMSClient = new ACMSClient(MetricKeys.OP_PREF_UPDATE_PREF_CONTACT);
    }

    public boolean executeSwallowException(@Nullable String str, boolean z) {
        IHttpClient.Response mo3640execute;
        if (Strings.isNullOrEmpty(str)) {
            LOG.e("UpdateCanDropInPreference: parentHomeGroupId is null or empty");
            return false;
        }
        try {
            mo3640execute = this.mACMSClient.request(MessageFormat.format(AppUrl.PATCH_PREFERENCE, CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("UpdateCanDropInPreference", false), str)).addMetricMetadata("source", Constants.CONTACT_PREF_DROP_IN).authenticatedAsCurrentCommsUser().patchJson(z ? DropInState.ON : DropInState.OFF).mo3640execute();
            try {
            } finally {
            }
        } catch (ServiceException | IOException e) {
            LOG.e("Service Error occurred while fetching drop in.", e);
        }
        if (!mo3640execute.isSuccessful()) {
            mo3640execute.close();
            return false;
        }
        LOG.i("dropInState updated in server");
        mo3640execute.close();
        return true;
    }

    UpdateCanDropInPreference(@NonNull ACMSClient aCMSClient) {
        this.mACMSClient = aCMSClient;
    }
}
