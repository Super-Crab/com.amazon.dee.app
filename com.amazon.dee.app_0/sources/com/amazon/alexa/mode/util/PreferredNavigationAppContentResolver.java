package com.amazon.alexa.mode.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mode.R;
/* loaded from: classes9.dex */
public class PreferredNavigationAppContentResolver {
    private static final String CONTENT_URI_PREFIX = "content://";
    private static final String CONTENT_URI_SEPARATOR = "/";
    private static final Uri DAY1_COORDINATES = Uri.parse("geo:47.615898,-122.339920");
    static final Intent DEFAULT_MAPS_INTENT = new Intent("android.intent.action.VIEW", DAY1_COORDINATES);
    static final String GOOGLE_MAPS = "com.google.android.apps.maps";
    private static final String PREFERRED_NAVIGATION_APP = "preferredNavApp";
    static final String PREFERRED_NAVIGATION_APP_PREFERENCE_KEY = "preferred_nav_app";
    private static final String TAG = "PreferredNavigationAppContentResolver";
    static final String WAZE = "com.waze";
    private final Context context;
    private final Uri preferredNavAppContentUri = getContentUri();

    public PreferredNavigationAppContentResolver(Context context) {
        this.context = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean checkIfPreferredNavAppExists(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String r0 = "checkIfPreferredNav | preferredNavApp: "
            com.android.tools.r8.GeneratedOutlineSupport1.outline158(r0, r4)
            r0 = 0
            if (r4 != 0) goto L9
            return r0
        L9:
            android.content.Context r4 = r3.context
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            android.content.Intent r1 = com.amazon.alexa.mode.util.PreferredNavigationAppContentResolver.DEFAULT_MAPS_INTENT
            java.util.List r4 = r4.queryIntentActivities(r1, r0)
            java.util.Iterator r4 = r4.iterator()
        L19:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L3b
            java.lang.Object r1 = r4.next()
            android.content.pm.ResolveInfo r1 = (android.content.pm.ResolveInfo) r1
            android.content.pm.ActivityInfo r1 = r1.activityInfo
            java.lang.String r1 = r1.packageName
            java.lang.String r2 = "com.waze"
            boolean r2 = r1.equals(r2)
            if (r2 != 0) goto L39
            java.lang.String r2 = "com.google.android.apps.maps"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L19
        L39:
            r4 = 1
            return r4
        L3b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mode.util.PreferredNavigationAppContentResolver.checkIfPreferredNavAppExists(java.lang.String):boolean");
    }

    @VisibleForTesting
    Uri getContentUri() {
        String string = this.context.getString(R.string.preferredNavAppContentProviderAuthoritiesId);
        return Uri.parse(CONTENT_URI_PREFIX + string + "/" + PREFERRED_NAVIGATION_APP);
    }

    @Nullable
    public String getPreferredNavigationApp() {
        String str = "com.waze";
        Cursor query = this.context.getContentResolver().query(this.preferredNavAppContentUri, null, null, null, null, null);
        if (query == null) {
            Log.e(TAG, "PreferredNavAppSettingContentProvider returns no preferred nav app data");
            return null;
        }
        try {
            if (!query.moveToFirst()) {
                Log.e(TAG, "PreferredNavAppSettingContentProvider returns no preferred nav app data");
                return null;
            }
            int columnIndex = query.getColumnIndex(PREFERRED_NAVIGATION_APP_PREFERENCE_KEY);
            if (columnIndex == -1) {
                Log.e(TAG, "PreferredNavAppSettingContentProvider returns no preferred nav app data");
                return null;
            } else if (!checkIfPreferredNavAppExists(query.getString(columnIndex))) {
                return null;
            } else {
                if (!str.equals(query.getString(columnIndex))) {
                    str = "com.google.android.apps.maps";
                }
                return str;
            }
        } finally {
            query.close();
        }
    }

    public void setPreferredNavigationApp(@NonNull String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PREFERRED_NAVIGATION_APP_PREFERENCE_KEY, str);
        this.context.getContentResolver().insert(this.preferredNavAppContentUri, contentValues);
    }
}
