package com.amazon.alexa.redesign.repository;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.alexa.redesign.DismissedCardDataStore;
import com.amazon.alexa.redesign.entity.DismissIdentifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes10.dex */
public class DismissedCardDataStoreRepository implements DismissedCardDataStore {
    public static final String HOME_SHARED_PREFERENCES = "HOME_SHARED_PREFERENCES";
    private final SharedPreferences mPreferences;

    public DismissedCardDataStoreRepository(Context context) {
        this.mPreferences = context.getSharedPreferences(HOME_SHARED_PREFERENCES, 0);
    }

    private void applyStringSetChangesToDisk(String str, Set<String> set) {
        SharedPreferences sharedPreferences = this.mPreferences;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putStringSet(str, set);
            edit.apply();
        }
    }

    @Override // com.amazon.alexa.redesign.DismissedCardDataStore
    public void clearDismissedCardDataStore() {
        SharedPreferences sharedPreferences = this.mPreferences;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.clear();
            edit.apply();
        }
    }

    @Override // com.amazon.alexa.redesign.DismissedCardDataStore
    public void deleteDismissedCardIds(DismissIdentifier dismissIdentifier, String str) {
        if (this.mPreferences != null) {
            String dismissDataStoreId = dismissIdentifier.getDismissDataStoreId();
            HashSet hashSet = new HashSet(this.mPreferences.getStringSet(str, new HashSet()));
            hashSet.remove(dismissDataStoreId);
            applyStringSetChangesToDisk(str, hashSet);
        }
    }

    @Override // com.amazon.alexa.redesign.DismissedCardDataStore
    public List<DismissIdentifier> getDismissedCardIds(String str) {
        ArrayList arrayList = new ArrayList();
        SharedPreferences sharedPreferences = this.mPreferences;
        if (sharedPreferences != null) {
            for (String str2 : sharedPreferences.getStringSet(str, new HashSet())) {
                arrayList.add(new DismissIdentifier(str2));
            }
        }
        return arrayList;
    }

    @Override // com.amazon.alexa.redesign.DismissedCardDataStore
    public void putDismissedCardIds(DismissIdentifier dismissIdentifier, String str) {
        if (this.mPreferences != null) {
            String dismissDataStoreId = dismissIdentifier.getDismissDataStoreId();
            HashSet hashSet = new HashSet(this.mPreferences.getStringSet(str, new HashSet()));
            hashSet.add(dismissDataStoreId);
            applyStringSetChangesToDisk(str, hashSet);
        }
    }
}
