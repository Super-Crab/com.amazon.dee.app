package com.amazon.dee.app.services.datastore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/* loaded from: classes12.dex */
public class DataStoreHelper extends SQLiteOpenHelper {
    private static final String COMMA_SEP = ",";
    public static final String DATABASE_NAME = "DataStore.db";
    public static final int DATABASE_VERSION = 1;
    static final String SQL_CREATE_DATASTORE = "CREATE TABLE DataItem (key TEXT PRIMARY KEY,value TEXT )";
    private static final String TEXT_TYPE = " TEXT";
    private static final String TEXT_TYPE_PK = " TEXT PRIMARY KEY";

    public DataStoreHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(SQL_CREATE_DATASTORE);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
