package com.amazon.dee.app.services.datastore;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class DefaultDataStoreService implements DataStoreService, com.amazon.alexa.protocols.datastore.DataStoreService {
    private static final String TAG = "DefaultDataStoreService";
    private final SQLiteDatabase sqLiteDatabase;

    public DefaultDataStoreService(SQLiteDatabase sQLiteDatabase) {
        this.sqLiteDatabase = sQLiteDatabase;
    }

    private void insertDataItem(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", str);
        contentValues.put("value", str2);
        this.sqLiteDatabase.insert("DataItem", null, contentValues);
    }

    private int updateDataItem(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", str2);
        return this.sqLiteDatabase.update("DataItem", contentValues, "key = ?", new String[]{str});
    }

    @Override // com.amazon.dee.app.services.datastore.DataStoreService, com.amazon.alexa.protocols.datastore.DataStoreService
    public void clear() {
        if (this.sqLiteDatabase.isOpen()) {
            this.sqLiteDatabase.delete("DataItem", null, null);
        }
    }

    @Override // com.amazon.dee.app.services.datastore.DataStoreService, com.amazon.alexa.protocols.datastore.DataStoreService
    public String retrieveValue(String str) {
        if (!this.sqLiteDatabase.isOpen()) {
            String str2 = TAG;
            Log.e(str2, "Attempted to get " + str + " after the database was closed.");
            return null;
        }
        Cursor query = this.sqLiteDatabase.query("DataItem", new String[]{"key", "value"}, "key = ?", new String[]{str}, null, null, null);
        try {
            if (query.getCount() == 0) {
                query.close();
                return null;
            }
            query.moveToFirst();
            String string = query.getString(query.getColumnIndexOrThrow("value"));
            query.close();
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.dee.app.services.datastore.DataStoreService, com.amazon.alexa.protocols.datastore.DataStoreService
    public void storeValue(String str, String str2) {
        if (this.sqLiteDatabase.isOpen()) {
            if (updateDataItem(str, str2) != 0) {
                return;
            }
            insertDataItem(str, str2);
            return;
        }
        Log.e(TAG, GeneratedOutlineSupport1.outline77("Could not store {", str, ":", str2, "} because app is shutting down."));
    }
}
