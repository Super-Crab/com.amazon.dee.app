package com.amazon.deecomms.contacts.presence;

import android.content.Context;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.presence.model.PresenceDocument;
import com.amazon.deecomms.contacts.presence.model.PresenceDocumentList;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class PresenceCache {
    private long CACHE_TIMEOUT;
    private PresenceDocument self;
    long lastUpdated = 0;
    private int activeCount = 0;
    private final ConcurrentMap<String, PresenceDocument> presenceMap = new ConcurrentHashMap();

    public PresenceCache() {
        setCacheTimeOut();
    }

    private void setCacheTimeOut() {
        this.CACHE_TIMEOUT = TimeUnit.SECONDS.toMillis(CommsDaggerWrapper.getComponent().getArcusConfig().getConfigInteger(RemoteConfigKeys.PRESENCE_CACHE_TIMEOUT).intValue());
    }

    public void cacheDocuments(@NonNull Context context, @NonNull PresenceDocumentList presenceDocumentList) {
        PresenceDocument[] documents;
        clear();
        this.lastUpdated = System.currentTimeMillis();
        String[] strArr = {"commsId", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_WITH_TABLE_NAME_SERVER_CONTACT_ID, "firstName", "lastName"};
        String homeGroupId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("PresenceCache.cacheDocuments", false);
        Cursor query = context.getContentResolver().query(ContactProviderContract.getContactJoinPhoneNumberUri(context), strArr, "canIDropInOnHim = ? AND commsId != ?", new String[]{String.valueOf(1), ""}, null);
        while (query != null) {
            try {
                if (!query.moveToNext()) {
                    break;
                }
                PresenceDocument presenceDocument = new PresenceDocument();
                presenceDocument.setContactHomegroupId(query.getString(query.getColumnIndex("commsId")));
                presenceDocument.setContactId(query.getString(query.getColumnIndex("serverContactId")));
                presenceDocument.setContactName(query.getString(query.getColumnIndex("firstName")));
                presenceDocument.setContactLastName(query.getString(query.getColumnIndex("lastName")));
                presenceDocument.setPresenceStatus("INACTIVE");
                if (homeGroupId == null || !homeGroupId.equalsIgnoreCase(presenceDocument.getContactHomegroupId())) {
                    this.presenceMap.put(presenceDocument.getContactHomegroupId(), presenceDocument);
                }
            } finally {
            }
        }
        if (query != null) {
            query.close();
        }
        for (PresenceDocument presenceDocument2 : presenceDocumentList.getDocuments()) {
            if (homeGroupId != null && homeGroupId.equalsIgnoreCase(presenceDocument2.getContactHomegroupId())) {
                this.self = presenceDocument2;
            } else if (this.presenceMap.containsKey(presenceDocument2.getContactHomegroupId())) {
                this.activeCount = (presenceDocument2.isActive() ? 1 : 0) + this.activeCount;
                this.presenceMap.get(presenceDocument2.getContactHomegroupId()).setPresenceStatus(presenceDocument2.getPresenceStatus());
            }
        }
    }

    public void clear() {
        this.lastUpdated = 0L;
        this.activeCount = 0;
        this.presenceMap.clear();
        this.self = null;
    }

    @NonNull
    public List<PresenceDocument> getActiveContacts() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.presenceMap.keySet()) {
            PresenceDocument presenceDocument = this.presenceMap.get(str);
            if (presenceDocument.isActive()) {
                arrayList.add(presenceDocument);
            }
        }
        return arrayList;
    }

    public int getActiveCount() {
        return this.activeCount;
    }

    @NonNull
    public List<PresenceDocument> getAllContacts() {
        return new ArrayList(this.presenceMap.values());
    }

    @Nullable
    public PresenceDocument getContact(@NonNull String str) {
        return this.presenceMap.get(str);
    }

    public int getTotalCount() {
        return this.presenceMap.size();
    }

    public boolean is5SecondsExpired() {
        return System.currentTimeMillis() > this.lastUpdated + 5000;
    }

    public boolean isContactActive(@NonNull String str) {
        String homeGroupId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("PresenceCache.isContactActive", false);
        if (homeGroupId != null && homeGroupId.equalsIgnoreCase(str)) {
            PresenceDocument presenceDocument = this.self;
            return presenceDocument != null && presenceDocument.isActive();
        }
        PresenceDocument presenceDocument2 = this.presenceMap.get(str);
        return presenceDocument2 != null && presenceDocument2.isActive();
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > this.lastUpdated + this.CACHE_TIMEOUT;
    }
}
