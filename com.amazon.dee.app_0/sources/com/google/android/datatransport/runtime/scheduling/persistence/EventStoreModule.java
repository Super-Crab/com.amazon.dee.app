package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
@Module
/* loaded from: classes2.dex */
public abstract class EventStoreModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Named("SCHEMA_VERSION")
    public static int schemaVersion() {
        return SchemaManager.SCHEMA_VERSION;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public static EventStoreConfig storeConfig() {
        return EventStoreConfig.DEFAULT;
    }

    @Binds
    abstract EventStore eventStore(SQLiteEventStore sQLiteEventStore);

    @Binds
    abstract SynchronizationGuard synchronizationGuard(SQLiteEventStore sQLiteEventStore);
}
