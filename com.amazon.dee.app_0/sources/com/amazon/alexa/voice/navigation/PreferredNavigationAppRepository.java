package com.amazon.alexa.voice.navigation;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.navigation.MappingApplication;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class PreferredNavigationAppRepository {
    private static final Comparator<NavigationAppInfo> APP_DISPLAY_NAME_COMPARATOR = $$Lambda$PreferredNavigationAppRepository$HI6bCpjxq197od8KULwHzeZmi9o.INSTANCE;
    private static final Uri DAY1_COORDINATES = Uri.parse("geo:47.615898,-122.339920");
    @VisibleForTesting
    static final Intent DEFAULT_MAPS_INTENT = new Intent("android.intent.action.VIEW", DAY1_COORDINATES);
    private static final String TAG = "PreferredNavigationAppRepository";
    private final PackageManager packageManager;
    private final PreferredNavigationAppContentAccessor preferredNavigationAppContentAccessor;

    public PreferredNavigationAppRepository(PackageManager packageManager, PreferredNavigationAppContentAccessor preferredNavigationAppContentAccessor) {
        this.packageManager = packageManager;
        this.preferredNavigationAppContentAccessor = preferredNavigationAppContentAccessor;
    }

    private String getPreferredNavigationAppFromPreferences() {
        String preferredNavigationApp = this.preferredNavigationAppContentAccessor.getPreferredNavigationApp();
        if (preferredNavigationApp == null) {
            setDefaultNavigationAppInPreferences();
            return MappingApplication.GOOGLE_MAPS_PACKAGENAME;
        }
        return preferredNavigationApp;
    }

    private Map<String, NavigationAppInfo> getSupportedNavigationApps(String str) {
        NavigationAppInfo navigationAppInfo;
        List<ResolveInfo> queryIntentActivities = this.packageManager.queryIntentActivities(DEFAULT_MAPS_INTENT, 0);
        HashMap hashMap = new HashMap();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            String str2 = activityInfo.packageName;
            if (str2.equals(MappingApplication.WAZE_PACKAGENAME) || str2.equals(MappingApplication.GOOGLE_MAPS_PACKAGENAME)) {
                hashMap.put(str2, NavigationAppInfo.create(activityInfo.loadLabel(this.packageManager).toString(), str2, str2.equals(str)));
            }
        }
        if (!hashMap.containsKey(str) && (navigationAppInfo = (NavigationAppInfo) hashMap.get(MappingApplication.GOOGLE_MAPS_PACKAGENAME)) != null) {
            hashMap.put(MappingApplication.GOOGLE_MAPS_PACKAGENAME, NavigationAppInfo.create(navigationAppInfo.getAppDisplayName(), navigationAppInfo.getAppFullName(), true, navigationAppInfo.getAppIconAsset()));
            setDefaultNavigationAppInPreferences();
        }
        return hashMap;
    }

    private void setDefaultNavigationAppInPreferences() {
        this.preferredNavigationAppContentAccessor.setPreferredNavigationApp(MappingApplication.GOOGLE_MAPS_PACKAGENAME);
    }

    public List<NavigationAppInfo> getAllNavigationApps() {
        ArrayList arrayList = new ArrayList(getSupportedNavigationApps(getPreferredNavigationAppFromPreferences()).values());
        Collections.sort(arrayList, APP_DISPLAY_NAME_COMPARATOR);
        return arrayList;
    }

    public void setPreferredNavigationApp(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != -660073534) {
            if (hashCode == 40719148 && str.equals(MappingApplication.GOOGLE_MAPS_PACKAGENAME)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(MappingApplication.WAZE_PACKAGENAME)) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            this.preferredNavigationAppContentAccessor.setPreferredNavigationApp(MappingApplication.GOOGLE_MAPS_PACKAGENAME);
        } else {
            this.preferredNavigationAppContentAccessor.setPreferredNavigationApp(MappingApplication.WAZE_PACKAGENAME);
        }
    }
}
