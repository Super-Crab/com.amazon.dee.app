package com.amazon.deecomms.contacts.operations;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.perms.PermissionsHelper;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public class ContactsOperationsManager {
    public static final String COMPLETE_IMPORT_FAILED_MSG = "COMPLETE_IMPORT_FAILED_MSG";
    public static final String CONTACTS_PULL_ACTION = "com.amazon.deecomms.contacts.pull_contacts";
    public static final String CONTACTS_SYNC_ACTION = "com.amazon.deecomms.contacts.sync_contacts";
    public static final String CONTACT_IMPORT_RESULT = "com.amazon.deecomms.contacts.contact_import_result";
    public static final String HOME_GROUP_ID_KEY = "homeGroupId";
    public static final String HOME_GROUP_MEMBER_SYNC_ACTION = "com.amazon.deecomms.contacts.home_group_member_sync";
    public static final String HOME_GROUP_MEMBER_SYNC_RESULT = "com.amazon.deecomms.contacts.home_group_member_result";
    public static final String IMPORT_SUCCESS = "IMPORT_SUCCESS";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactsOperationsManager.class);
    public static final String OPERATION_RESULT_MSG = "OPERATION_RESULT_MSG";
    public static final String OPERATION_RESULT_VALUE = "OPERATION_RESULT_VALUE";
    public static final String PARTIAL_IMPORT_FAILED_MSG = "PARTIAL_IMPORT_FAILED_MSG";
    public static final String PULL_CONTACT_RESULT = "com.amazon.deecomms.contacts.contact_pull_result";
    public static final String SYNC_CONTACT_RESULT = "com.amazon.deecomms.contacts.contact_sync_result";
    public static final int SYNC_GRACE_PERIOD_IN_SECONDS = 10;
    private final Context mContext;
    private Calendar mNextPullTime;
    private AtomicBoolean mPullInProgress = new AtomicBoolean(false);
    private AtomicBoolean mImportInProgress = new AtomicBoolean(false);
    private AtomicBoolean mSyncInProgress = new AtomicBoolean(false);

    public ContactsOperationsManager(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
    }

    private boolean canPerformContactOperations() {
        if (!CommsDaggerWrapper.getComponent().getCommsIdentityManager().isCommsIdPopulated("canPerformContactOperations", false)) {
            LOG.i("missing commsid");
            return false;
        } else if (!CommsDaggerWrapper.getComponent().getCommsConnectivityMonitor().isConnected()) {
            LOG.i("not connected");
            return false;
        } else if (!CommsInternal.getInstance().isLowInternalStorage()) {
            return true;
        } else {
            LOG.i("Not starting contact operation due to low internal storage");
            return false;
        }
    }

    private boolean shouldPullContacts() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(13, 10);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("currentTime + grace time: ");
        outline1.append(calendar.getTime());
        outline1.append(", nextPullTime:");
        Calendar calendar2 = this.mNextPullTime;
        outline1.append(calendar2 == null ? "not set" : calendar2.getTime());
        commsLogger.i(outline1.toString());
        Calendar calendar3 = this.mNextPullTime;
        if (calendar3 != null && calendar.before(calendar3)) {
            LOG.i("Last successful pull done within interval");
            return false;
        }
        LOG.i("Allowing pull contacts");
        return true;
    }

    public boolean isContactsPullInProgress() {
        return this.mPullInProgress.get();
    }

    public boolean isImportInProgress() {
        return this.mImportInProgress.get();
    }

    public boolean isInitialContactsDownloadDone() {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("SHARED_PREFS", 0);
        boolean z = sharedPreferences.getBoolean(Constants.INITIAL_CONTACTS_DOWNLOAD_SUCCESS, false);
        boolean z2 = sharedPreferences.getBoolean(Constants.REACT_NATIVE_CONTACTS_SYNC_COMPLETED, false);
        CommsLogger commsLogger = LOG;
        commsLogger.i("isInitialContactsDownloadDone? React:" + z2 + " - Native:" + z);
        return z || z2;
    }

    public boolean pullContacts(boolean z) {
        LOG.i("Getting contacts");
        if (!canPerformContactOperations()) {
            return false;
        }
        if (!z && !shouldPullContacts()) {
            return false;
        }
        if (this.mPullInProgress.getAndSet(true)) {
            LOG.i("Another contacts pull in progress");
            return true;
        }
        ContactsOperationsService.enqueueWork(this.mContext, new Intent(this.mContext, ContactsOperationsService.class).setAction(CONTACTS_PULL_ACTION));
        return true;
    }

    public void sendOperationResult(String str, boolean z, String str2) {
        CommsLogger commsLogger = LOG;
        commsLogger.d("Sending operation result, operation:" + str + ", success" + z);
        if (PULL_CONTACT_RESULT.equals(str)) {
            if (z) {
                ArcusConfig arcusConfig = CommsDaggerWrapper.getComponent().getArcusConfig();
                this.mNextPullTime = Calendar.getInstance();
                this.mNextPullTime.add(13, arcusConfig.getConfigInteger(RemoteConfigKeys.CONTACTS_REFRESH_INTERVAL_SEC).intValue());
            } else {
                this.mNextPullTime = null;
            }
            this.mPullInProgress.set(false);
        } else if (SYNC_CONTACT_RESULT.equals(str)) {
            this.mSyncInProgress.set(false);
        }
        Intent intent = new Intent(str);
        intent.putExtra(OPERATION_RESULT_VALUE, z);
        intent.putExtra(OPERATION_RESULT_MSG, str2);
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent);
    }

    public void syncContacts() {
        if (!canPerformContactOperations()) {
            LOG.i("can't sync.");
        } else if (!PermissionsHelper.checkPermission(this.mContext, "android.permission.READ_CONTACTS")) {
            LOG.i("can't sync, no contact read permission. Pulling Contacts");
            pullContacts(true);
        } else if (this.mSyncInProgress.getAndSet(true)) {
            LOG.i("sync ignored, another sync in progress.");
        } else {
            ContactsOperationsService.enqueueWork(this.mContext, new Intent(this.mContext, ContactsOperationsService.class).setAction(CONTACTS_SYNC_ACTION));
        }
    }

    public void syncHomeGroupMembers(String str) {
        ContactsOperationsService.enqueueWork(this.mContext, new Intent(this.mContext, ContactsOperationsService.class).setAction(HOME_GROUP_MEMBER_SYNC_ACTION).putExtra("homeGroupId", str));
    }
}
