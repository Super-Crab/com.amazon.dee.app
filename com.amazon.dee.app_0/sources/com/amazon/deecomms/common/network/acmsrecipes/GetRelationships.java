package com.amazon.deecomms.common.network.acmsrecipes;

import android.text.TextUtils;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.contacts.model.LocalizedRelationshp;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class GetRelationships {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetRelationships.class);
    private final ACMSClient mClient = new ACMSClient(MetricKeys.OP_GET_RELATIONSHIPS);
    private final String mCurrentCommsId;

    public GetRelationships(String str) {
        this.mCurrentCommsId = str;
    }

    public LocalizedRelationshp[] getRelationships() {
        if (TextUtils.isEmpty(this.mCurrentCommsId)) {
            LOG.e("CommsId is null or empty.");
            return null;
        }
        try {
            LocalizedRelationshp[] localizedRelationshpArr = (LocalizedRelationshp[]) this.mClient.request(MessageFormat.format(AppUrl.GET_RELATIONSHIPS, this.mCurrentCommsId)).authenticatedAsCurrentCommsUser().get().mo3640execute().convert(LocalizedRelationshp[].class);
            if (localizedRelationshpArr != null && localizedRelationshpArr.length > 0) {
                return localizedRelationshpArr;
            }
            LOG.e("The relationship map returned from ACMS is empty or null");
            return null;
        } catch (ServiceException e) {
            LOG.e("Get relationships failed", e);
            return null;
        }
    }
}
