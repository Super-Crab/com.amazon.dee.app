package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$12 implements SQLiteEventStore.Function {
    private static final SQLiteEventStore$$Lambda$12 instance = new SQLiteEventStore$$Lambda$12();

    private SQLiteEventStore$$Lambda$12() {
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$loadActiveContexts$9((SQLiteDatabase) obj);
    }
}
