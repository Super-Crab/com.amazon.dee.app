package com.amazon.deecomms.contacts.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.database.DatabaseUtils;
import com.amazon.deecomms.contacts.model.ContactUploadInfo;
import com.amazon.deecomms.contacts.model.EmailAddressType;
import com.amazon.deecomms.contacts.model.PhoneNumberType;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes12.dex */
public class DeviceContactsFetcher {
    private static final int DEFAULT_BATCH_SIZE = 100;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DeviceContactsFetcher.class);
    private final Context mContext;

    /* loaded from: classes12.dex */
    public interface ContactFetchCallback {
        void onContactsFetched(List<ContactUploadInfo> list);
    }

    public DeviceContactsFetcher(Context context) {
        this.mContext = context;
    }

    @NonNull
    private List<ContactUploadInfo> fetchDeviceContactsInternal(ContactFetchCallback contactFetchCallback, int i, String str, String[] strArr) {
        int count;
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        Cursor query = this.mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id"}, str, strArr, null);
        if (query != null) {
            try {
                count = query.getCount();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        } else {
            count = 0;
        }
        if (count == 0) {
            if (contactFetchCallback != null) {
                contactFetchCallback.onContactsFetched(arrayList);
            }
            if (query != null) {
                query.close();
            }
            return arrayList;
        }
        long j = 0;
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("_id"));
            ContactUploadInfo contactUploadInfo = new ContactUploadInfo();
            contactUploadInfo.setDeviceContactId(string);
            hashMap.put(string, contactUploadInfo);
            if (hashMap.size() == i) {
                fetchNamesAndCompany(hashMap);
                fetchPhoneNumbers(hashMap);
                fetchEmailAddresses(hashMap);
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                j += currentTimeMillis2;
                CommsLogger commsLogger = LOG;
                commsLogger.d("Contacts read: " + i + ". Time taken: " + currentTimeMillis2);
                if (contactFetchCallback != null) {
                    contactFetchCallback.onContactsFetched(new ArrayList(hashMap.values()));
                } else {
                    arrayList.addAll(hashMap.values());
                }
                hashMap.clear();
                currentTimeMillis = System.currentTimeMillis();
            }
        }
        if (!hashMap.isEmpty()) {
            fetchNamesAndCompany(hashMap);
            fetchPhoneNumbers(hashMap);
            fetchEmailAddresses(hashMap);
            long currentTimeMillis3 = System.currentTimeMillis() - currentTimeMillis;
            j += currentTimeMillis3;
            CommsLogger commsLogger2 = LOG;
            commsLogger2.d("Contacts read: " + i + ". Time taken: " + currentTimeMillis3);
            if (contactFetchCallback != null) {
                contactFetchCallback.onContactsFetched(new ArrayList(hashMap.values()));
            } else {
                arrayList.addAll(hashMap.values());
            }
            hashMap.clear();
        }
        query.close();
        CommsLogger commsLogger3 = LOG;
        commsLogger3.d("Time taken to fetch " + count + "contacts is " + j + "ms");
        return arrayList;
    }

    private void fetchEmailAddresses(Map<String, ContactUploadInfo> map) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("contact_id IN (");
        outline1.append(DatabaseUtils.getInListPlaceholder(map.size()));
        outline1.append(")");
        Cursor query = this.mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, new String[]{Constants.BUNDLE_KEY_CONTACT_ID, "data1", "data2", "data3"}, outline1.toString(), (String[]) map.keySet().toArray(new String[map.size()]), null);
        if (query != null) {
            try {
                if (query.getCount() != 0) {
                    query.moveToFirst();
                    do {
                        String string = query.getString(query.getColumnIndex(Constants.BUNDLE_KEY_CONTACT_ID));
                        map.get(string).addEmailAddress(query.getString(query.getColumnIndex("data1")), getEmailAddressType(query.getInt(query.getColumnIndex("data2")), query.getString(query.getColumnIndex("data3"))));
                    } while (query.moveToNext());
                    query.close();
                    return;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
        }
    }

    @TargetApi(21)
    private void fetchNamesAndCompany(Map<String, ContactUploadInfo> map) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("name_raw_contact_id = raw_contact_id AND contact_id IN (");
        outline1.append(DatabaseUtils.getInListPlaceholder(map.size()));
        outline1.append(")");
        String sb = outline1.toString();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(map.keySet());
        linkedHashSet.clear();
        Cursor query = this.mContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, sb, (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]), null);
        while (query.moveToNext()) {
            try {
                ContactUploadInfo contactUploadInfo = map.get(query.getString(query.getColumnIndex(Constants.BUNDLE_KEY_CONTACT_ID)));
                String string = query.getString(query.getColumnIndex("mimetype"));
                char c = 65535;
                int hashCode = string.hashCode();
                if (hashCode != -1079224304) {
                    if (hashCode != 689862072) {
                        if (hashCode != 1409846529) {
                            if (hashCode == 2034973555 && string.equals("vnd.android.cursor.item/nickname")) {
                                c = 3;
                            }
                        } else if (string.equals("vnd.android.cursor.item/relation")) {
                            c = 1;
                        }
                    } else if (string.equals("vnd.android.cursor.item/organization")) {
                        c = 2;
                    }
                } else if (string.equals("vnd.android.cursor.item/name")) {
                    c = 0;
                }
                if (c != 0) {
                    String str = null;
                    if (c == 1) {
                        String string2 = query.getString(query.getColumnIndex("data1"));
                        String string3 = query.getString(query.getColumnIndex("data3"));
                        CharSequence typeLabel = ContactsContract.CommonDataKinds.Relation.getTypeLabel(this.mContext.getResources(), query.getInt(query.getColumnIndex("data2")), "Others");
                        if (!TextUtils.isEmpty(typeLabel)) {
                            str = String.valueOf(typeLabel);
                        }
                        if (StringUtils.isNotEmpty(string2)) {
                            string3 = string2;
                        }
                        contactUploadInfo.addRelationShip(string3, str);
                    } else if (c == 2) {
                        contactUploadInfo.setCompany(query.getString(query.getColumnIndex("data1")));
                    } else if (c != 3) {
                        LOG.d("The cursor does not hold information required for names/company");
                    } else {
                        String string4 = query.getString(query.getColumnIndex("data1"));
                        if (contactUploadInfo.getName() == null) {
                            contactUploadInfo.setContactName(null, null, string4);
                        } else {
                            contactUploadInfo.getName().setNickName(string4);
                        }
                    }
                } else {
                    String string5 = query.getString(query.getColumnIndex("data2"));
                    String string6 = query.getString(query.getColumnIndex("data3"));
                    String string7 = query.getString(query.getColumnIndex("data7"));
                    String string8 = query.getString(query.getColumnIndex("data9"));
                    if (contactUploadInfo.getName() == null) {
                        contactUploadInfo.setContactName(string5, string6, string7, string8, null);
                    } else {
                        contactUploadInfo.getName().setFirstName(string5);
                        contactUploadInfo.getName().setLastName(string6);
                        contactUploadInfo.getName().setPhoneticFirstName(string7);
                        contactUploadInfo.getName().setPhoneticLastName(string8);
                    }
                }
            } finally {
            }
        }
        query.close();
    }

    private void fetchPhoneNumbers(Map<String, ContactUploadInfo> map) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("contact_id IN (");
        outline1.append(DatabaseUtils.getInListPlaceholder(map.size()));
        outline1.append(")");
        Cursor query = this.mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{Constants.BUNDLE_KEY_CONTACT_ID, "data1", "data2", "data3"}, outline1.toString(), (String[]) map.keySet().toArray(new String[map.size()]), null);
        if (query != null) {
            try {
                if (query.getCount() != 0) {
                    query.moveToFirst();
                    do {
                        String string = query.getString(query.getColumnIndex(Constants.BUNDLE_KEY_CONTACT_ID));
                        map.get(string).addPhoneNumber(query.getString(query.getColumnIndex("data1")), getPhoneNumberType(query.getInt(query.getColumnIndex("data2")), query.getString(query.getColumnIndex("data3"))));
                    } while (query.moveToNext());
                    query.close();
                    return;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
        }
    }

    public void fetchAllDeviceContactsBatch(ContactFetchCallback contactFetchCallback, int i) {
        if (contactFetchCallback == null) {
            LOG.w("Callback is null for fetching contacts in batch.");
        } else {
            fetchDeviceContactsInternal(contactFetchCallback, i, null, null);
        }
    }

    public Set getAllDeviceContactIds() {
        HashSet hashSet = new HashSet();
        Cursor query = this.mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id"}, null, null, null);
        while (query.moveToNext()) {
            try {
                hashSet.add(query.getString(query.getColumnIndex("_id")));
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        query.close();
        return hashSet;
    }

    public List<ContactUploadInfo> getCreatedContacts(int i) {
        return fetchDeviceContactsInternal(null, 100, "_id > ? ", new String[]{String.valueOf(i)});
    }

    @NonNull
    public List<ContactUploadInfo> getDeviceContacts() {
        return fetchDeviceContactsInternal(null, 100, "_id > ? ", new String[]{String.valueOf(-1)});
    }

    public int getDeviceContactsCount() {
        Cursor query = this.mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id"}, null, null, null);
        try {
            int count = query.getCount();
            query.close();
            return count;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    String getEmailAddressType(int i, String str) {
        EmailAddressType fromAddressBookType = EmailAddressType.fromAddressBookType(i);
        if (fromAddressBookType == EmailAddressType.Custom) {
            return str != null ? str : "";
        }
        return fromAddressBookType.getAcmsType();
    }

    public int getLastDeviceContactId() {
        Cursor query = this.mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id"}, null, null, "_id DESC LIMIT 1");
        if (query != null) {
            try {
                if (query.moveToNext()) {
                    int i = query.getInt(query.getColumnIndex("_id"));
                    query.close();
                    return i;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
            return 0;
        }
        return 0;
    }

    String getPhoneNumberType(int i, String str) {
        PhoneNumberType fromAddressBookType = PhoneNumberType.fromAddressBookType(i);
        if (fromAddressBookType == PhoneNumberType.Custom) {
            return str != null ? str : "";
        }
        return fromAddressBookType.getAcmsType();
    }

    public List<ContactUploadInfo> getUpdatedContacts(int i, long j) {
        return fetchDeviceContactsInternal(null, 100, "_id <= ? AND contact_last_updated_timestamp > ?", new String[]{String.valueOf(i), String.valueOf(j)});
    }
}
