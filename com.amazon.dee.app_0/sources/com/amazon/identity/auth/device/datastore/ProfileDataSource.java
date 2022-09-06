package com.amazon.identity.auth.device.datastore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.identity.auth.device.dataobject.Profile;
import com.amazon.identity.auth.device.utils.MAPUtils;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class ProfileDataSource extends AbstractDataSource<Profile> {
    private static final String[] ALL_COLUMNS = Profile.ALL_COLUMNS;
    private static ProfileDataSource INSTANCE = null;
    private static final String LOG_TAG = "com.amazon.identity.auth.device.datastore.ProfileDataSource";

    public ProfileDataSource(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    public static synchronized ProfileDataSource getInstance(Context context) {
        ProfileDataSource profileDataSource;
        synchronized (ProfileDataSource.class) {
            if (INSTANCE == null) {
                INSTANCE = new ProfileDataSource(MAPUtils.getMAPdatabase(context));
            }
            profileDataSource = INSTANCE;
        }
        return profileDataSource;
    }

    public static void resetInstance() {
        INSTANCE = null;
        MAPUtils.resetDatabaseInstance();
    }

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public String[] getAllColumns() {
        return ALL_COLUMNS;
    }

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public String getLogTag() {
        return LOG_TAG;
    }

    public Profile getProfile(String str) {
        return findOneRowBySingleColumn("AppId", str);
    }

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public String getTableName() {
        return DatabaseHelper.profileTable;
    }

    @Override // com.amazon.identity.auth.device.datastore.AbstractDataSource
    public Profile cursorToObject(Cursor cursor) {
        if (cursor != null && cursor.getCount() != 0) {
            try {
                Profile profile = new Profile();
                profile.setId(cursor.getLong(getColumnIndex(cursor, Profile.COL_INDEX.ID.colId)));
                profile.setAppId(cursor.getString(getColumnIndex(cursor, Profile.COL_INDEX.APP_ID.colId)));
                profile.setExpirationTime(DatabaseHelper.getDateFormat().parse(cursor.getString(getColumnIndex(cursor, Profile.COL_INDEX.EXPIRATION_TIME.colId))));
                profile.setData(cursor.getString(getColumnIndex(cursor, Profile.COL_INDEX.DATA.colId)));
                return profile;
            } catch (Exception e) {
                String str = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("");
                outline107.append(e.getMessage());
                MAPLog.e(str, outline107.toString(), e);
            }
        }
        return null;
    }
}
