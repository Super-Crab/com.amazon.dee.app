package com.amazon.deecomms.common.database;

import net.sqlcipher.database.SQLiteDatabase;
/* loaded from: classes12.dex */
public interface ICommsDatabase {
    void onCreate(SQLiteDatabase sQLiteDatabase);

    void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2);
}
