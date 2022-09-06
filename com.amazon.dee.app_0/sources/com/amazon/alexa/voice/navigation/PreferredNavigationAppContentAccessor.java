package com.amazon.alexa.voice.navigation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.navigation.MappingApplication;
import com.amazon.alexa.voice.R;
/* loaded from: classes11.dex */
class PreferredNavigationAppContentAccessor {
    @VisibleForTesting
    static final String PREFERRED_NAVIGATION_APP_PREFERENCE_KEY = "preferred_nav_app";
    private static final String TAG = "PreferredNavigationAppContentAccessor";
    private final Context context;
    private final Uri preferredNavAppContentUri = getContentUri();

    /* JADX INFO: Access modifiers changed from: package-private */
    public PreferredNavigationAppContentAccessor(Context context) {
        this.context = context;
    }

    @VisibleForTesting
    Uri getContentUri() {
        String string = this.context.getString(R.string.preferredNavAppContentProviderAuthoritiesId);
        return Uri.parse("content://" + string + "/preferredNavApp");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getPreferredNavigationApp() {
        String str = MappingApplication.WAZE_PACKAGENAME;
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
            }
            if (!str.equals(query.getString(columnIndex))) {
                str = MappingApplication.GOOGLE_MAPS_PACKAGENAME;
            }
            return str;
        } finally {
            query.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPreferredNavigationApp(String str) {
        if (str == null) {
            Log.e(TAG, "Cannot set preferred nav app to null");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(PREFERRED_NAVIGATION_APP_PREFERENCE_KEY, str);
        this.context.getContentResolver().insert(this.preferredNavAppContentUri, contentValues);
    }
}
