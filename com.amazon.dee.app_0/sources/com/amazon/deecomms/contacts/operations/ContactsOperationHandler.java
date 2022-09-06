package com.amazon.deecomms.contacts.operations;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.database.DatabaseUtils;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.model.Contacts;
import com.amazon.deecomms.contacts.model.GetCommsIdentities;
import com.amazon.deecomms.contacts.model.Identity;
import com.amazon.deecomms.contacts.util.ContactDownloader;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.contacts.util.ContactsSynchronizer;
import com.amazon.deecomms.contacts.util.GetOrCreateContact;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.provider.MessagingProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class ContactsOperationHandler {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactsOperationHandler.class);
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    ContactsOperationsManager contactsManager;
    @Inject
    Context context;

    public ContactsOperationHandler() {
        CommsDaggerWrapper.getComponent().inject(this);
    }

    private void insertPulledContactsIntoDatabase(Contacts contacts) {
        boolean deleteAllContacts;
        LOG.i("inserting contacts");
        long currentTimeMillis = System.currentTimeMillis();
        if (contacts != null && contacts.getContacts() != null && !contacts.getContacts().isEmpty()) {
            deleteAllContacts = ContactsProviderUtils.deleteAndInsertContacts(this.context, contacts.getContacts());
        } else {
            deleteAllContacts = ContactsProviderUtils.deleteAllContacts(this.context);
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Time taken to insert contacts: ");
        outline1.append(currentTimeMillis2 - currentTimeMillis);
        commsLogger.d(outline1.toString());
        if (deleteAllContacts && !this.contactsManager.isInitialContactsDownloadDone()) {
            persistSuccessfulDownload();
        }
        Cursor query = this.context.getContentResolver().query(MessagingProviderContract.Participants.getParticipantsNotContactsUri(this.context), null, null, null, null);
        if (query != null) {
            try {
                GetOrCreateContact getOrCreateContact = new GetOrCreateContact("ContactsOperationHandler.insertPulledContactsIntoDatabase");
                while (query.moveToNext()) {
                    getOrCreateContact.getContactEntry(query.getString(query.getColumnIndex("participant_comms_id")), false);
                }
            } finally {
            }
        }
        if (query != null) {
            query.close();
        }
        this.contactsManager.sendOperationResult(ContactsOperationsManager.PULL_CONTACT_RESULT, deleteAllContacts, null);
    }

    @SuppressLint({"CommitPrefEdits"})
    private void persistSuccessfulDownload() {
        SharedPreferences.Editor edit = this.context.getSharedPreferences("SHARED_PREFS", 0).edit();
        edit.putBoolean(Constants.INITIAL_CONTACTS_DOWNLOAD_SUCCESS, true);
        edit.apply();
    }

    private boolean syncHomeGroupMembersInternal(Context context, HomeGroupIdentitiesDownloader homeGroupIdentitiesDownloader, String str) {
        if (TextUtils.isEmpty(str)) {
            LOG.e("HomeGroupID is empty");
            return false;
        }
        GetCommsIdentities.Response downloadIdentities = homeGroupIdentitiesDownloader.downloadIdentities(str);
        if (downloadIdentities == null) {
            LOG.e("Identities not present in response.");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (Identity identity : downloadIdentities.getIdentities()) {
            arrayList.add(identity.getCommsId());
        }
        ArrayList arrayList2 = new ArrayList();
        Cursor fetchHomeGroupMembers = ContactsProviderUtils.fetchHomeGroupMembers(context, str);
        while (fetchHomeGroupMembers.moveToNext()) {
            try {
                String string = fetchHomeGroupMembers.getString(fetchHomeGroupMembers.getColumnIndex("commsId"));
                if (!arrayList.contains(string)) {
                    arrayList2.add(string);
                } else {
                    arrayList.remove(string);
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (fetchHomeGroupMembers != null) {
                        try {
                            fetchHomeGroupMembers.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        fetchHomeGroupMembers.close();
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Home group members toInsert:");
        outline1.append(arrayList.size());
        outline1.append(" toRemove:");
        outline1.append(arrayList2.size());
        commsLogger.i(outline1.toString());
        if (!arrayList.isEmpty()) {
            updateParentHomeGroupId(context, str, arrayList);
        }
        if (arrayList2.isEmpty()) {
            return true;
        }
        updateParentHomeGroupId(context, null, arrayList2);
        return true;
    }

    public static void updateParentHomeGroupId(Context context, String str, List<String> list) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("commsId IN (");
        outline1.append(DatabaseUtils.getInListPlaceholder(list.size()));
        outline1.append(")");
        String sb = outline1.toString();
        ContentValues contentValues = new ContentValues();
        if (str != null) {
            contentValues.put(ContactProviderContract.PhoneNumberEntry.COLUMN_PARENT_HOME_GROUP, str);
        } else {
            contentValues.putNull(ContactProviderContract.PhoneNumberEntry.COLUMN_PARENT_HOME_GROUP);
        }
        context.getContentResolver().update(ContactProviderContract.getPhoneNumberUri(context), contentValues, sb, (String[]) list.toArray(new String[list.size()]));
    }

    public void pullContacts() {
        LOG.i("getContactsDebugging: download contacts in pullContacts");
        ContactDownloader contactDownloader = new ContactDownloader();
        if (contactDownloader.downloadContacts()) {
            insertPulledContactsIntoDatabase(contactDownloader.getContactsAndHomeGroups());
        }
    }

    public void syncContacts() {
        boolean z = false;
        String homeGroupId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("syncContacts", false);
        ContactDownloader contactDownloader = new ContactDownloader();
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(Constants.ACMS_CONTACTS_QUERY_PREFERENCE_LEVEL, Constants.ACMS_CONTACT_PREFERENCE_LEVEL_HG);
        arrayMap.put("homeGroupId", homeGroupId);
        arrayMap.put(Constants.ACMS_CONTACTS_QUERY_INCLUDE_HG, Boolean.TRUE.toString());
        arrayMap.put(ContactDownloader.ACMS_QUERY_PARAM_DEDUPE_MODE, ContactDownloader.ACMS_QUERY_PARAM_DEDUPE_MODE_REMOVE_CLOUD_ONLY_CONTACT_DUPLICATES);
        arrayMap.put(ContactDownloader.ACMS_QUERY_PARAM_INCLUDE_NON_ALEXA_CONTACTS, "true");
        arrayMap.put(Constants.ACMS_CONTACTS_QUERY_INCLUDE_MERGED_CONTACTS, (this.capabilitiesManager.isLightyearEnabled() ? Boolean.TRUE : Boolean.FALSE).toString());
        LOG.i("getContactsDebugging: download contacts in syncContacts");
        if (contactDownloader.downloadContacts(arrayMap)) {
            insertPulledContactsIntoDatabase(contactDownloader.getContactsAndHomeGroups());
            z = new ContactsSynchronizer(this.context).syncContacts(contactDownloader.getBulkImportedContacts());
        }
        this.contactsManager.sendOperationResult(ContactsOperationsManager.SYNC_CONTACT_RESULT, z, null);
        this.contactsManager.pullContacts(z);
    }

    public void syncHomeGroupMembers(String str) {
        this.contactsManager.sendOperationResult(ContactsOperationsManager.HOME_GROUP_MEMBER_SYNC_RESULT, syncHomeGroupMembersInternal(this.context, new HomeGroupIdentitiesDownloader(), str), null);
    }
}
