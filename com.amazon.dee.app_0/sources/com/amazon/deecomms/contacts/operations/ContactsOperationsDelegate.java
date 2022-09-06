package com.amazon.deecomms.contacts.operations;

import android.content.Intent;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CommsDaggerWrapper;
/* loaded from: classes12.dex */
public class ContactsOperationsDelegate {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactsOperationsDelegate.class);
    private final ContactsOperationHandler mSyncHandler;

    public ContactsOperationsDelegate(ContactsOperationHandler contactsOperationHandler) {
        this.mSyncHandler = contactsOperationHandler;
    }

    public void onHandleIntent(Intent intent) {
        if (intent == null) {
            LOG.i("Not starting contact operation due to NULL intent");
        } else if (CommsDaggerWrapper.getComponent().getCommsInternal().isLowInternalStorage()) {
            LOG.i("Not starting contact operation due to low internal storage");
        } else {
            CommsProvisionStatus provisionStatus = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getProvisionStatus(true, "ContactsOperationsDelegate.onHandleIntent", false);
            if (!CommsProvisionStatus.DEPROVISIONED.equals(provisionStatus) && !CommsProvisionStatus.UNKNOWN.equals(provisionStatus)) {
                String action = intent.getAction();
                if (ContactsOperationsManager.CONTACTS_PULL_ACTION.equals(action)) {
                    this.mSyncHandler.pullContacts();
                    return;
                } else if (ContactsOperationsManager.HOME_GROUP_MEMBER_SYNC_ACTION.equals(action)) {
                    this.mSyncHandler.syncHomeGroupMembers(intent.getStringExtra("homeGroupId"));
                    return;
                } else if (!ContactsOperationsManager.CONTACTS_SYNC_ACTION.equals(action)) {
                    return;
                } else {
                    this.mSyncHandler.syncContacts();
                    return;
                }
            }
            LOG.i("Not starting contact sync operation as the user is not provisioned");
        }
    }
}
