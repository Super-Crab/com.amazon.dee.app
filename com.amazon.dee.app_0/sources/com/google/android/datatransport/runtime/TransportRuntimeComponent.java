package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.backends.BackendRegistryModule;
import com.google.android.datatransport.runtime.scheduling.SchedulingConfigModule;
import com.google.android.datatransport.runtime.scheduling.SchedulingModule;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule;
import com.google.android.datatransport.runtime.time.TimeModule;
import dagger.BindsInstance;
import dagger.Component;
import java.io.Closeable;
import java.io.IOException;
import javax.inject.Singleton;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
@Component(modules = {BackendRegistryModule.class, EventStoreModule.class, ExecutionModule.class, SchedulingModule.class, SchedulingConfigModule.class, TimeModule.class})
@Singleton
/* loaded from: classes2.dex */
public abstract class TransportRuntimeComponent implements Closeable {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    @Component.Builder
    /* loaded from: classes2.dex */
    public interface Builder {
        TransportRuntimeComponent build();

        @BindsInstance
        /* renamed from: setApplicationContext */
        Builder mo7264setApplicationContext(Context context);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        getEventStore().close();
    }

    abstract EventStore getEventStore();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract TransportRuntime getTransportRuntime();
}
