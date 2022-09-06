package com.amazon.alexa.handsfree.latencyreporter.db;
/* loaded from: classes8.dex */
public final class TimestampTable {
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_TIMESTAMP_IDENTIFIER = "timestamp_identifier";
    public static final String COLUMN_TIMESTAMP_NAME = "timestamp_name";
    public static final String COLUMN_TIMESTAMP_TYPE = "timestamp_type";
    public static final String TABLE_CREATE = "CREATE TABLE timestamp (_id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp_name TEXT NOT NULL, timestamp_identifier TEXT,timestamp_type INTEGER NOT NULL,timestamp INTEGER NOT NULL)";
    public static final String TABLE_DELETE = "DROP TABLE IF EXISTS timestamp";
    public static final String TABLE_TIMESTAMP = "timestamp";

    private TimestampTable() {
    }
}
