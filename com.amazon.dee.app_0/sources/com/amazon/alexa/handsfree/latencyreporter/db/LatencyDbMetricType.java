package com.amazon.alexa.handsfree.latencyreporter.db;

import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public enum LatencyDbMetricType {
    DATABASE_CREATION("DatabaseCreation"),
    DATABASE_UPDATE("DatabaseUpdate"),
    DATABASE_OPEN("DatabaseOpen"),
    DATA_INSERT("DataInsert"),
    DATA_DELETE("DataDelete"),
    DATA_PARSE("DataParse"),
    DATA_OVERRIDE("DataOverride:%s"),
    DATA_EXPIRE_REMOVED("DataExpiredRemoved:%s"),
    DATA_RECEIVED("DataReceived"),
    VALID_LATENCY("ValidLatency:%s");
    
    private final String mValue;

    LatencyDbMetricType(@NonNull String str) {
        this.mValue = str;
    }

    @NonNull
    public String getValue() {
        return this.mValue;
    }
}
