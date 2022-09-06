package com.amazon.deecomms.contacts.util;

import android.text.TextUtils;
import com.amazon.comms.calling.sipclient.SipStatusCode;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.calling.util.HttpStatusCodeFamily;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.DefaultMetricStats;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.ContactForSync;
import com.amazon.deecomms.contacts.model.SyncedContact;
import com.amazon.deecomms.contacts.model.api.SyncContacts;
import com.amazon.deecomms.contacts.model.enums.SyncErrorCode;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes12.dex */
public class CloudContactsSynchronizer {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CloudContactsSynchronizer.class);

    /* renamed from: com.amazon.deecomms.contacts.util.CloudContactsSynchronizer$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily = new int[HttpStatusCodeFamily.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily[HttpStatusCodeFamily.CLIENT_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily[HttpStatusCodeFamily.SERVER_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily[HttpStatusCodeFamily.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public IHttpClient.Response uploadContactsDiff(ContactForSync contactForSync, String str) {
        ACMSClient aCMSClient = new ACMSClient(MetricKeys.OP_GET_CONTACTS);
        String contactsURLForOwner = Utils.getContactsURLForOwner(str);
        LOG.i("getContactsDebugging: Assemble getContacts request URL for owner in CloudContactsSynchronizer");
        if (TextUtils.isEmpty(contactsURLForOwner)) {
            LOG.e("ACMS endpoint URL is empty/null when attempting to update contacts");
            return null;
        }
        HashMap outline133 = GeneratedOutlineSupport1.outline133("source", Constants.CONTACT_UPDATE);
        ArrayList arrayList = new ArrayList();
        arrayList.add(contactForSync);
        SyncContacts.Request request = new SyncContacts.Request(null, arrayList);
        IHttpClient.Request request2 = aCMSClient.request(contactsURLForOwner);
        String requestId = request2.getRequestId();
        try {
            IHttpClient.Response mo3640execute = request2.authenticatedAsCurrentCommsUser().patchJson(request).mo3640execute();
            int code = mo3640execute.code();
            HttpStatusCodeFamily familyFromStatusCode = HttpStatusCodeFamily.familyFromStatusCode(Integer.valueOf(code));
            int ordinal = familyFromStatusCode.ordinal();
            if (ordinal != 0 && ordinal != 4 && ordinal != 5) {
                SyncContacts.Response response = (SyncContacts.Response) mo3640execute.convert(SyncContacts.Response.class);
                if (response == null) {
                    LOG.e("Sync Response is null.");
                    MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.Operational, MetricKeys.CONTACTS_UPDATE, new DefaultMetricStats(Integer.valueOf(code), mo3640execute.getCallDuration(), requestId, HttpStatusCodeFamily.UNKNOWN), outline133);
                    return mo3640execute;
                }
                List<SyncedContact> successList = response.getSuccessList();
                List<SyncedContact> failureList = response.getFailureList();
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("Sync response, success size :");
                int i = 0;
                sb.append(successList == null ? 0 : successList.size());
                sb.append(" , failure size: ");
                if (failureList != null) {
                    i = failureList.size();
                }
                sb.append(i);
                commsLogger.i(sb.toString());
                if (failureList != null) {
                    for (SyncedContact syncedContact : failureList) {
                        CommsLogger commsLogger2 = LOG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Failed to sync contact id:");
                        sb2.append(syncedContact.getDeviceContactId());
                        sb2.append(" error:");
                        sb2.append(syncedContact.getSyncErrorCode());
                        commsLogger2.e(sb2.toString());
                        if (syncedContact.getSyncErrorCode() == SyncErrorCode.RETRYABLE_OPERATIONAL_ERROR) {
                            MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.Operational, MetricKeys.CONTACTS_UPDATE, new DefaultMetricStats(Integer.valueOf(code), mo3640execute.getCallDuration(), requestId, HttpStatusCodeFamily.SERVER_ERROR), outline133);
                            return mo3640execute;
                        }
                    }
                }
                MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.Operational, MetricKeys.CONTACTS_UPDATE, new DefaultMetricStats(Integer.valueOf(SipStatusCode.OK.getCode()), null, requestId, HttpStatusCodeFamily.SUCCESS), outline133);
                return mo3640execute;
            }
            MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.Operational, MetricKeys.CONTACTS_UPDATE, new DefaultMetricStats(Integer.valueOf(code), mo3640execute.getCallDuration(), requestId, familyFromStatusCode), outline133);
            return mo3640execute;
        } catch (ServiceException e) {
            LOG.e("Exception while uploading contacts diff", e);
            MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.Operational, MetricKeys.CONTACTS_UPDATE, e, outline133);
            return null;
        }
    }
}
