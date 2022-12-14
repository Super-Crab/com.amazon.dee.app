package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.time.Clock;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public final class SQLiteEventStore_Factory implements Factory<SQLiteEventStore> {
    private final Provider<Clock> clockProvider;
    private final Provider<EventStoreConfig> configProvider;
    private final Provider<SchemaManager> schemaManagerProvider;
    private final Provider<Clock> wallClockProvider;

    public SQLiteEventStore_Factory(Provider<Clock> provider, Provider<Clock> provider2, Provider<EventStoreConfig> provider3, Provider<SchemaManager> provider4) {
        this.wallClockProvider = provider;
        this.clockProvider = provider2;
        this.configProvider = provider3;
        this.schemaManagerProvider = provider4;
    }

    public static SQLiteEventStore_Factory create(Provider<Clock> provider, Provider<Clock> provider2, Provider<EventStoreConfig> provider3, Provider<SchemaManager> provider4) {
        return new SQLiteEventStore_Factory(provider, provider2, provider3, provider4);
    }

    public static SQLiteEventStore newInstance(Clock clock, Clock clock2, Object obj, Object obj2) {
        return new SQLiteEventStore(clock, clock2, (EventStoreConfig) obj, (SchemaManager) obj2);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SQLiteEventStore mo10268get() {
        return new SQLiteEventStore(this.wallClockProvider.mo10268get(), this.clockProvider.mo10268get(), this.configProvider.mo10268get(), this.schemaManagerProvider.mo10268get());
    }
}
