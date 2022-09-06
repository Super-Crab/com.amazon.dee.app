package com.amazon.alexa.auto.navigation;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import dagger.Lazy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
class PreferredNavigationAppSettingManager {
    @VisibleForTesting
    static final String GOOGLE_MAPS_PACKAGENAME = "com.google.android.apps.maps";
    static final String PREFERRED_NAV_APP_PREFERENCE_KEY = "preferred_nav_app";
    private static final String TAG = "PreferredNavigationAppSettingManager";
    @VisibleForTesting
    static final String WAZE_PACKAGENAME = "com.waze";
    private final PackageManager packageManager;
    private final Lazy<PersistentStorage> persistentStorage;
    private static final Uri GEOLOCATION_QUERY = Uri.parse("geo:37.423156,-122.084917");
    @VisibleForTesting
    static final Intent DEFAULT_MAPS_INTENT = new Intent("android.intent.action.VIEW", GEOLOCATION_QUERY);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public PreferredNavigationAppSettingManager(PackageManager packageManager, @Named("navigation_store") Lazy<PersistentStorage> lazy) {
        this.packageManager = packageManager;
        this.persistentStorage = lazy;
    }

    private Set<String> getAvailableNavApps() {
        List<ResolveInfo> queryIntentActivities = this.packageManager.queryIntentActivities(DEFAULT_MAPS_INTENT, 0);
        HashSet hashSet = new HashSet();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            hashSet.add(resolveInfo.activityInfo.packageName);
        }
        return hashSet;
    }

    private String getPreferredNavAppFromPreferences() {
        String string = this.persistentStorage.mo358get().getString(PREFERRED_NAV_APP_PREFERENCE_KEY);
        if (string == null) {
            setDefaultNavAppInPreferences();
            return "com.google.android.apps.maps";
        }
        return string;
    }

    private void setDefaultNavAppInPreferences() {
        setPreferredNavAppPreference("com.google.android.apps.maps");
    }

    private void setPreferredNavAppPreference(String str) {
        this.persistentStorage.mo358get().edit().set(PREFERRED_NAV_APP_PREFERENCE_KEY, str).commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getPreferredNavigationApp() {
        String preferredNavAppFromPreferences = getPreferredNavAppFromPreferences();
        if (getAvailableNavApps().contains(preferredNavAppFromPreferences)) {
            return preferredNavAppFromPreferences;
        }
        String.format("preferred %s not availale. Default to google maps", preferredNavAppFromPreferences);
        setDefaultNavAppInPreferences();
        return "com.google.android.apps.maps";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPreferredNavigationApp(String str) {
        if (str == null) {
            return;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -660073534) {
            if (hashCode == 40719148 && str.equals("com.google.android.apps.maps")) {
                c = 1;
            }
        } else if (str.equals("com.waze")) {
            c = 0;
        }
        if (c != 0) {
            setPreferredNavAppPreference("com.google.android.apps.maps");
        } else {
            setPreferredNavAppPreference("com.waze");
        }
    }
}
