package com.amazon.alexa.accessory.persistence;

import android.database.sqlite.SQLiteDatabase;
/* loaded from: classes.dex */
public interface DatabaseScheme {
    int getVersion();

    void onCreate(SQLiteDatabase sQLiteDatabase);

    void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2);

    void onOpen(SQLiteDatabase sQLiteDatabase);

    void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2);
}
