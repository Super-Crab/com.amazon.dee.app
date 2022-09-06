package com.amazon.deecomms.contacts.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
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
import com.amazon.deecomms.contacts.database.ContactsDatabaseHelper;
import com.amazon.deecomms.contacts.model.Contact;
import com.amazon.deecomms.contacts.model.ContactForSync;
import com.amazon.deecomms.contacts.model.ContactUploadInfo;
import com.amazon.deecomms.contacts.model.Contacts;
import com.amazon.deecomms.contacts.model.SyncedContact;
import com.amazon.deecomms.contacts.model.api.SyncContacts;
import com.amazon.deecomms.contacts.model.enums.SyncErrorCode;
import com.amazon.deecomms.contacts.model.enums.SyncOperationType;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.amazon.deecomms.util.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
/* loaded from: classes12.dex */
public class ContactsSynchronizer {
    private static final int DEVICE_NOT_MASTER_STATUS_CODE = 204;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactsSynchronizer.class);
    private static String sDeviceId;
    private final Context mContext;
    private final DeviceContactsFetcher mDeviceContactsFetcher;
    private int mSyncBatchSize;

    /* renamed from: com.amazon.deecomms.contacts.util.ContactsSynchronizer$1  reason: invalid class name */
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

    public ContactsSynchronizer(Context context) {
        this.mContext = context;
        this.mDeviceContactsFetcher = new DeviceContactsFetcher(context);
        initSyncVariables();
    }

    private boolean areContactsDeleted(int i, int i2, int i3) {
        return i2 - i < i3;
    }

    private List<ContactForSync> getContactsForSync(List<ContactUploadInfo> list, SyncOperationType syncOperationType) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (ContactUploadInfo contactUploadInfo : list) {
                arrayList.add(new ContactForSync(contactUploadInfo, syncOperationType));
            }
        }
        return arrayList;
    }

    private void initSyncVariables() {
        this.mSyncBatchSize = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigInteger(RemoteConfigKeys.CONTACTS_UPLOAD_BATCH_SIZE).intValue();
        if (sDeviceId == null) {
            sDeviceId = new DeviceInfo().getUniqueDeviceId(this.mContext);
        }
    }

    public static void saveSyncData(Context context, long j, int i, int i2) {
        SharedPreferences.Editor edit = context.getSharedPreferences("SHARED_PREFS", 0).edit();
        edit.putLong(Constants.LAST_CONTACT_SYNCED_TIME_PREF, j);
        edit.putInt(Constants.LAST_SYNCED_CONTACT_ID_PREF, i);
        edit.putInt(Constants.LAST_SYNCED_DEVICE_CONTACT_COUNT_PREF, i2);
        edit.putLong(Constants.LAST_FORCED_CONTACT_UPDATE_VERSION, 29L);
        edit.apply();
    }

    private boolean uploadContactsDiff(List<ContactForSync> list) {
        ACMSClient aCMSClient = new ACMSClient(MetricKeys.OP_PATCH_CONTACT);
        String contactsURL = Utils.getContactsURL();
        LOG.i("getContactsDebugging: Assemble getContacts request URL in uploadContactsDiff");
        if (TextUtils.isEmpty(contactsURL)) {
            LOG.e("ACMS endpoint URL is empty/null when attempting to update contacts");
            return false;
        }
        HashMap outline133 = GeneratedOutlineSupport1.outline133("source", Constants.CONTACT_UPDATE);
        String str = null;
        for (List list2 : Lists.partition(list, this.mSyncBatchSize)) {
            try {
                IHttpClient.Response mo3640execute = aCMSClient.request(contactsURL).authenticatedAsCurrentCommsUser().patchJson(new SyncContacts.Request(sDeviceId, list2)).mo3640execute();
                int code = mo3640execute.code();
                String requestId = mo3640execute.getCall().getRequestId();
                if (code == 204) {
                    LOG.i("Sync failed. Device not master!");
                    MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.CONTACTS_UPDATE_DEVICE_NOT_MASTER);
                    MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.Operational, MetricKeys.CONTACTS_UPDATE, new DefaultMetricStats(Integer.valueOf(code), mo3640execute.getCallDuration(), requestId, HttpStatusCodeFamily.SUCCESS), outline133);
                    mo3640execute.close();
                    return false;
                }
                HttpStatusCodeFamily familyFromStatusCode = HttpStatusCodeFamily.familyFromStatusCode(Integer.valueOf(code));
                int ordinal = familyFromStatusCode.ordinal();
                if (ordinal != 0 && ordinal != 4 && ordinal != 5) {
                    SyncContacts.Response response = (SyncContacts.Response) mo3640execute.convert(SyncContacts.Response.class);
                    if (response == null) {
                        LOG.e("Sync Response is null.");
                        MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.Operational, MetricKeys.CONTACTS_UPDATE, new DefaultMetricStats(Integer.valueOf(code), mo3640execute.getCallDuration(), requestId, HttpStatusCodeFamily.UNKNOWN), outline133);
                        mo3640execute.close();
                        return false;
                    }
                    List<SyncedContact> successList = response.getSuccessList();
                    List<SyncedContact> failureList = response.getFailureList();
                    CommsLogger commsLogger = LOG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Sync response, success size :");
                    sb.append(successList == null ? 0 : successList.size());
                    sb.append(" , failure size: ");
                    sb.append(failureList == null ? 0 : failureList.size());
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
                                mo3640execute.close();
                                return false;
                            }
                        }
                    }
                    mo3640execute.close();
                    str = requestId;
                } else {
                    MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.Operational, MetricKeys.CONTACTS_UPDATE, new DefaultMetricStats(Integer.valueOf(code), mo3640execute.getCallDuration(), requestId, familyFromStatusCode), outline133);
                    mo3640execute.close();
                    return false;
                }
            } catch (ServiceException | IOException e) {
                LOG.e("Exception while uploading contacts diff", e);
                MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.Operational, MetricKeys.CONTACTS_UPDATE, e, outline133);
                return false;
            }
        }
        MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.Operational, MetricKeys.CONTACTS_UPDATE, new DefaultMetricStats(Integer.valueOf(SipStatusCode.OK.getCode()), null, str, HttpStatusCodeFamily.SUCCESS), outline133);
        return true;
    }

    public List<ContactForSync> getDeletedContacts(@Nullable List<Contact> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            Set allDeviceContactIds = this.mDeviceContactsFetcher.getAllDeviceContactIds();
            for (Contact contact : list) {
                if (contact.getDeviceContactId() != null && !allDeviceContactIds.contains(contact.getDeviceContactId())) {
                    ContactUploadInfo contactUploadInfo = new ContactUploadInfo();
                    contactUploadInfo.setDeviceContactId(contact.getDeviceContactId());
                    contactUploadInfo.setServerContactId(contact.getId());
                    arrayList.add(new ContactForSync(contactUploadInfo, SyncOperationType.DELETE));
                }
            }
            return arrayList;
        }
        LOG.i("server contacts are empty.");
        return arrayList;
    }

    public int getLastSyncDeviceContactsSize() {
        return this.mContext.getSharedPreferences("SHARED_PREFS", 0).getInt(Constants.LAST_SYNCED_DEVICE_CONTACT_COUNT_PREF, 0);
    }

    public int getLastSyncedContactId() {
        return this.mContext.getSharedPreferences("SHARED_PREFS", 0).getInt(Constants.LAST_SYNCED_CONTACT_ID_PREF, 0);
    }

    public long getLastSyncedTime() {
        return this.mContext.getSharedPreferences("SHARED_PREFS", 0).getLong(Constants.LAST_CONTACT_SYNCED_TIME_PREF, 0L);
    }

    public boolean requiresForceUpdatedAllContacts() {
        return ContactsDatabaseHelper.DATABASE_VERSIONS_THAT_REQUIRE_FORCE_UPDATE_SET.contains(29) && this.mContext.getSharedPreferences("SHARED_PREFS", 0).getLong(Constants.LAST_FORCED_CONTACT_UPDATE_VERSION, -1L) != 29;
    }

    public boolean syncContacts(Contacts contacts) {
        ArrayList arrayList = new ArrayList();
        int lastSyncedContactId = requiresForceUpdatedAllContacts() ? -1 : getLastSyncedContactId();
        long currentTimeMillis = System.currentTimeMillis();
        List<ContactUploadInfo> createdContacts = this.mDeviceContactsFetcher.getCreatedContacts(lastSyncedContactId);
        int size = createdContacts.size();
        arrayList.addAll(getContactsForSync(ContactsProviderUtils.trimContactsToMaxCloudLength(createdContacts), SyncOperationType.ADD));
        createdContacts.clear();
        List<ContactUploadInfo> updatedContacts = this.mDeviceContactsFetcher.getUpdatedContacts(lastSyncedContactId, getLastSyncedTime());
        arrayList.addAll(getContactsForSync(ContactsProviderUtils.trimContactsToMaxCloudLength(updatedContacts), SyncOperationType.UPDATE));
        updatedContacts.clear();
        int lastSyncDeviceContactsSize = getLastSyncDeviceContactsSize();
        int lastDeviceContactId = this.mDeviceContactsFetcher.getLastDeviceContactId();
        int deviceContactsCount = this.mDeviceContactsFetcher.getDeviceContactsCount();
        if (areContactsDeleted(size, deviceContactsCount, lastSyncDeviceContactsSize)) {
            if (contacts == null) {
                LOG.e("Unable to fetch bulk imported contacts in server, returning");
                return false;
            }
            arrayList.addAll(getDeletedContacts(contacts.getContacts()));
        }
        if (arrayList.isEmpty()) {
            LOG.i("No diff, ignoring sync");
            return false;
        } else if (!uploadContactsDiff(arrayList)) {
            return false;
        } else {
            saveSyncData(this.mContext, currentTimeMillis, lastDeviceContactId, deviceContactsCount);
            return true;
        }
    }
}
