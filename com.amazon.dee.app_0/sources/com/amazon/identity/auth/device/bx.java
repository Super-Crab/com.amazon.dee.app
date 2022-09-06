package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsService;
import java.util.ArrayList;
import java.util.List;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class bx {
    private static String hK;

    private static boolean a(Context context, Intent intent) {
        List<ResolveInfo> queryIntentActivities;
        try {
            queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
        } catch (Exception unused) {
            io.e("CustomTabsHelper", "Exception while getting specialized handlers");
        }
        if (queryIntentActivities.size() == 0) {
            return false;
        }
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            IntentFilter intentFilter = resolveInfo.filter;
            if (intentFilter != null && intentFilter.countDataAuthorities() != 0 && intentFilter.countDataPaths() != 0 && resolveInfo.activityInfo != null) {
                return true;
            }
        }
        return false;
    }

    public static synchronized String s(Context context) {
        synchronized (bx.class) {
            if (hK != null) {
                return hK;
            }
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.amazon-customtabtest.com"));
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
            String str = resolveActivity != null ? resolveActivity.activityInfo.packageName : null;
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
            ArrayList arrayList = new ArrayList();
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                Intent intent2 = new Intent();
                intent2.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
                intent2.setPackage(resolveInfo.activityInfo.packageName);
                if (packageManager.resolveService(intent2, 0) != null) {
                    arrayList.add(resolveInfo.activityInfo.packageName);
                }
            }
            if (arrayList.isEmpty()) {
                hK = null;
            } else if (arrayList.size() == 1) {
                hK = (String) arrayList.get(0);
            } else if (!TextUtils.isEmpty(str) && !a(context, intent) && arrayList.contains(str)) {
                hK = str;
            } else if (arrayList.contains("com.android.chrome")) {
                hK = "com.android.chrome";
            } else if (arrayList.contains("com.chrome.beta")) {
                hK = "com.chrome.beta";
            } else if (arrayList.contains("com.chrome.dev")) {
                hK = "com.chrome.dev";
            } else if (arrayList.contains("com.google.android.apps.chrome")) {
                hK = "com.google.android.apps.chrome";
            }
            return hK;
        }
    }
}
