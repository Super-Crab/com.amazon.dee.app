package com.amazon.deecomms.contacts.util;

import android.content.Context;
import com.amazon.comms.calling.sipclient.SipStatusCode;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.calling.util.HttpStatusCodeFamily;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.DefaultMetricStats;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.contacts.model.ContactUploadInfo;
import com.amazon.deecomms.contacts.operations.ContactsOperationsManager;
import com.amazon.deecomms.contacts.util.DeviceContactsFetcher;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.amazon.deecomms.util.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/* loaded from: classes12.dex */
public class ContactsImporter {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactsImporter.class);
    private int mImportedContactsCount;
    private int batchFailed = 0;
    private int batchFaulted = 0;
    private int batchUnknown = 0;
    private int batchSucceeded = 0;

    /* renamed from: com.amazon.deecomms.contacts.util.ContactsImporter$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily = new int[HttpStatusCodeFamily.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily[HttpStatusCodeFamily.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily[HttpStatusCodeFamily.CLIENT_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$util$HttpStatusCodeFamily[HttpStatusCodeFamily.SERVER_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes12.dex */
    private class UploaderCallable implements Callable<Integer> {
        final List<ContactUploadInfo> contactToBeUploaded;
        final ContactUploader contactUploader;

        public UploaderCallable(List<ContactUploadInfo> list, ContactUploader contactUploader) {
            this.contactToBeUploaded = list;
            this.contactUploader = contactUploader;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        /* renamed from: call */
        public Integer mo3671call() throws Exception {
            return Integer.valueOf(this.contactUploader.uploadContacts(ContactsProviderUtils.trimContactsToMaxCloudLength(this.contactToBeUploaded)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchResult(List<Future<Integer>> list) {
        for (Future<Integer> future : list) {
            try {
                int ordinal = HttpStatusCodeFamily.familyFromStatusCode(future.get()).ordinal();
                if (ordinal == 2) {
                    this.batchSucceeded = 1;
                } else if (ordinal == 4) {
                    this.batchFailed = 1;
                } else if (ordinal != 5) {
                    this.batchUnknown = 1;
                } else {
                    this.batchFaulted = 1;
                }
            } catch (InterruptedException | ExecutionException e) {
                LOG.e("Exception while trying to fetch the upload task result", e);
                this.batchUnknown = 1;
            }
        }
    }

    public String importContacts(int i, TimerMetric timerMetric) {
        String str;
        final int intValue = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigInteger(RemoteConfigKeys.CONTACTS_MAX_CONCURRENT_UPLOAD).intValue();
        Context context = CommsDaggerWrapper.getComponent().getContext();
        final ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(intValue);
        final ArrayList arrayList = new ArrayList(intValue);
        final ContactUploader contactUploader = new ContactUploader(new DeviceInfo().getUniqueDeviceId(context));
        DeviceContactsFetcher deviceContactsFetcher = new DeviceContactsFetcher(context);
        this.mImportedContactsCount = 0;
        long currentTimeMillis = System.currentTimeMillis();
        int lastDeviceContactId = deviceContactsFetcher.getLastDeviceContactId();
        int deviceContactsCount = deviceContactsFetcher.getDeviceContactsCount();
        deviceContactsFetcher.fetchAllDeviceContactsBatch(new DeviceContactsFetcher.ContactFetchCallback() { // from class: com.amazon.deecomms.contacts.util.ContactsImporter.1
            @Override // com.amazon.deecomms.contacts.util.DeviceContactsFetcher.ContactFetchCallback
            public void onContactsFetched(List<ContactUploadInfo> list) {
                if (arrayList.size() == intValue) {
                    ContactsImporter.this.fetchResult(arrayList);
                    arrayList.clear();
                }
                arrayList.add(newFixedThreadPool.submit(new UploaderCallable(new ArrayList(list), contactUploader)));
                ContactsImporter contactsImporter = ContactsImporter.this;
                contactsImporter.mImportedContactsCount = list.size() + contactsImporter.mImportedContactsCount;
            }
        }, i);
        fetchResult(arrayList);
        newFixedThreadPool.shutdown();
        timerMetric.stopTimer();
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Imported contacts count: ");
        outline1.append(this.mImportedContactsCount);
        outline1.append(" and time taken is ");
        outline1.append(timerMetric.getTimeDelta());
        commsLogger.i(outline1.toString());
        int i2 = this.mImportedContactsCount;
        if (i2 <= 200) {
            timerMetric.setMetricName(MetricKeys.CONTACTS_IMPORT_0_TO_200);
        } else if (i2 <= 1000) {
            timerMetric.setMetricName(MetricKeys.CONTACTS_IMPORT_201_TO_1000);
        } else if (i2 <= 3000) {
            timerMetric.setMetricName(MetricKeys.CONTACTS_IMPORT_1001_TO_3000);
        } else {
            timerMetric.setMetricName(MetricKeys.CONTACTS_IMPORT_3000_PLUS);
        }
        MetricsHelper.recordTimerMetric(timerMetric);
        boolean z = (this.batchFaulted + this.batchUnknown) + this.batchFailed == 0;
        DefaultMetricStats defaultMetricStats = new DefaultMetricStats(z ? Integer.valueOf(SipStatusCode.OK.getCode()) : null, null, this.batchFailed, this.batchFaulted, this.batchUnknown, this.batchSucceeded);
        HashMap outline133 = GeneratedOutlineSupport1.outline133("source", Constants.CONTACT_IMPORT);
        if (z) {
            ContactsSynchronizer.saveSyncData(context, currentTimeMillis, lastDeviceContactId, deviceContactsCount);
            str = ContactsOperationsManager.IMPORT_SUCCESS;
        } else {
            str = this.batchSucceeded > 0 ? ContactsOperationsManager.PARTIAL_IMPORT_FAILED_MSG : ContactsOperationsManager.COMPLETE_IMPORT_FAILED_MSG;
        }
        MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.Operational, MetricKeys.CONTACTS_IMPORT, defaultMetricStats, outline133);
        return str;
    }
}
