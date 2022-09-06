package com.facebook.react.modules.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
/* loaded from: classes2.dex */
public class ReactDatabaseSupplier extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "RKStorage";
    private static final int DATABASE_VERSION = 1;
    static final String KEY_COLUMN = "key";
    private static final int SLEEP_TIME_MS = 30;
    static final String TABLE_CATALYST = "catalystLocalStorage";
    static final String VALUE_COLUMN = "value";
    static final String VERSION_TABLE_CREATE = "CREATE TABLE catalystLocalStorage (key TEXT PRIMARY KEY, value TEXT NOT NULL)";
    @Nullable
    private static ReactDatabaseSupplier sReactDatabaseSupplierInstance;
    private Context mContext;
    @Nullable
    private SQLiteDatabase mDb;
    private long mMaximumDatabaseSize;

    private ReactDatabaseSupplier(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.mMaximumDatabaseSize = 6291456L;
        this.mContext = context;
    }

    private synchronized void closeDatabase() {
        if (this.mDb != null && this.mDb.isOpen()) {
            this.mDb.close();
            this.mDb = null;
        }
    }

    private synchronized boolean deleteDatabase() {
        closeDatabase();
        return this.mContext.deleteDatabase(DATABASE_NAME);
    }

    public static void deleteInstance() {
        sReactDatabaseSupplierInstance = null;
    }

    public static ReactDatabaseSupplier getInstance(Context context) {
        if (sReactDatabaseSupplierInstance == null) {
            sReactDatabaseSupplierInstance = new ReactDatabaseSupplier(context.getApplicationContext());
        }
        return sReactDatabaseSupplierInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void clear() {
        get().delete(TABLE_CATALYST, null, null);
    }

    public synchronized void clearAndCloseDatabase() throws RuntimeException {
        try {
            clear();
            closeDatabase();
            FLog.d(ReactConstants.TAG, "Cleaned RKStorage");
        } catch (Exception unused) {
            if (deleteDatabase()) {
                FLog.d(ReactConstants.TAG, "Deleted Local Database RKStorage");
                return;
            }
            throw new RuntimeException("Clearing and deleting database RKStorage failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean ensureDatabase() {
        if (this.mDb == null || !this.mDb.isOpen()) {
            SQLiteException e = null;
            for (int i = 0; i < 2; i++) {
                if (i > 0) {
                    try {
                        deleteDatabase();
                    } catch (SQLiteException e2) {
                        e = e2;
                        try {
                            Thread.sleep(30L);
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                this.mDb = getWritableDatabase();
            }
            if (this.mDb != null) {
                this.mDb.setMaximumSize(this.mMaximumDatabaseSize);
                return true;
            }
            throw e;
        }
        return true;
    }

    public synchronized SQLiteDatabase get() {
        ensureDatabase();
        return this.mDb;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(VERSION_TABLE_CREATE);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i != i2) {
            deleteDatabase();
            onCreate(sQLiteDatabase);
        }
    }

    public synchronized void setMaximumSize(long j) {
        this.mMaximumDatabaseSize = j;
        if (this.mDb != null) {
            this.mDb.setMaximumSize(this.mMaximumDatabaseSize);
        }
    }
}
