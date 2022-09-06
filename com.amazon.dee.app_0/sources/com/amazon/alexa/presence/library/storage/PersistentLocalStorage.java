package com.amazon.alexa.presence.library.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes9.dex */
public class PersistentLocalStorage {
    private static final int CLEAR_MAX_RETRY = 3;
    public static final String FILE_NAME = "presence_domain_preferences";
    public static final String PREFERENCE_KEY_PRESENCE_ENABLED = "presenceEnabled";
    private static final String TAG = "com.amazon.alexa.presence.library.storage.PersistentLocalStorage";

    /* loaded from: classes9.dex */
    public interface PresenceDataStore {
        boolean addDomain(String str, Context context);

        void clearStorage(Context context);

        Set<String> getDomains(Context context);

        boolean presenceRequested(Context context);

        boolean removeDomain(String str, Context context);
    }

    /* loaded from: classes9.dex */
    private static class Wrapper implements PresenceDataStore {
        private Wrapper() {
        }

        @Override // com.amazon.alexa.presence.library.storage.PersistentLocalStorage.PresenceDataStore
        public boolean addDomain(String str, Context context) {
            return PersistentLocalStorage.addDomain(str, context);
        }

        @Override // com.amazon.alexa.presence.library.storage.PersistentLocalStorage.PresenceDataStore
        public void clearStorage(Context context) {
            PersistentLocalStorage.clearStorage(context);
        }

        @Override // com.amazon.alexa.presence.library.storage.PersistentLocalStorage.PresenceDataStore
        public Set<String> getDomains(Context context) {
            return PersistentLocalStorage.getDomains(context);
        }

        @Override // com.amazon.alexa.presence.library.storage.PersistentLocalStorage.PresenceDataStore
        public boolean presenceRequested(Context context) {
            return PersistentLocalStorage.presenceRequested(context);
        }

        @Override // com.amazon.alexa.presence.library.storage.PersistentLocalStorage.PresenceDataStore
        public boolean removeDomain(String str, Context context) {
            return PersistentLocalStorage.removeDomain(str, context);
        }
    }

    private PersistentLocalStorage() {
    }

    public static boolean addDomain(String str, Context context) {
        boolean z;
        Set<String> singleton;
        GeneratedOutlineSupport1.outline163("Adding domain: ", str, TAG);
        Set<String> domains = getDomains(context);
        if (domains != null && !domains.isEmpty()) {
            singleton = new HashSet<>(domains);
            singleton.add(str);
            z = false;
        } else {
            z = true;
            singleton = Collections.singleton(str);
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(FILE_NAME, 0).edit();
        edit.putStringSet(PREFERENCE_KEY_PRESENCE_ENABLED, singleton);
        edit.apply();
        return z;
    }

    public static void clearStorage(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(FILE_NAME, 0).edit();
        edit.clear();
        for (int i = 0; i < 3 && !edit.commit(); i++) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Set<String> getDomains(Context context) {
        return Collections.unmodifiableSet(context.getSharedPreferences(FILE_NAME, 0).getStringSet(PREFERENCE_KEY_PRESENCE_ENABLED, new HashSet()));
    }

    public static PresenceDataStore getWrapper() {
        return new Wrapper();
    }

    public static boolean presenceRequested(Context context) {
        Set<String> domains = getDomains(context);
        return domains != null && !domains.isEmpty();
    }

    public static boolean removeDomain(String str, Context context) {
        Set<String> emptySet;
        boolean z;
        GeneratedOutlineSupport1.outline163("Removing domain: ", str, TAG);
        Set<String> domains = getDomains(context);
        if (domains != null && !domains.isEmpty()) {
            emptySet = new HashSet<>(domains);
            emptySet.remove(str);
            if (emptySet.isEmpty()) {
                z = true;
                SharedPreferences.Editor edit = context.getSharedPreferences(FILE_NAME, 0).edit();
                edit.putStringSet(PREFERENCE_KEY_PRESENCE_ENABLED, emptySet);
                edit.apply();
                return z;
            }
        } else {
            emptySet = Collections.emptySet();
        }
        z = false;
        SharedPreferences.Editor edit2 = context.getSharedPreferences(FILE_NAME, 0).edit();
        edit2.putStringSet(PREFERENCE_KEY_PRESENCE_ENABLED, emptySet);
        edit2.apply();
        return z;
    }
}
