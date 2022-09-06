package com.amazon.alexa.drive.navigation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
/* loaded from: classes7.dex */
public class PreferredNavigationAppContentResolver {
    private static final String AUTHORITY = "com.amazon.alexa.navigation.PreferredNavApp";
    static final String GOOGLE_MAPS = "com.google.android.apps.maps";
    private static final String PREFERRED_NAVIGATION_APP = "preferredNavApp";
    static final Uri PREFERRED_NAVIGATION_APP_CONTENT_URI = Uri.parse("content://com.amazon.alexa.navigation.PreferredNavApp/preferredNavApp");
    static final String PREFERRED_NAVIGATION_APP_PREFERENCE_KEY = "preferred_nav_app";
    private static final String TAG = "PreferredNavigationAppContentResolver";
    static final String WAZE = "com.waze";
    private final Context context;

    public PreferredNavigationAppContentResolver(Context context) {
        this.context = context;
    }

    public String getPreferredNavigationApp() {
        String str = "com.waze";
        Cursor query = this.context.getContentResolver().query(PREFERRED_NAVIGATION_APP_CONTENT_URI, null, null, null, null, null);
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
            }
            if (!str.equals(query.getString(columnIndex))) {
                str = "com.google.android.apps.maps";
            }
            return str;
        } finally {
            query.close();
        }
    }

    public void setPreferredNavigationApp(String str) {
        if (str == null) {
            Log.e(TAG, "Cannot set preferred nav app to null");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(PREFERRED_NAVIGATION_APP_PREFERENCE_KEY, str);
        this.context.getContentResolver().insert(PREFERRED_NAVIGATION_APP_CONTENT_URI, contentValues);
    }
}
