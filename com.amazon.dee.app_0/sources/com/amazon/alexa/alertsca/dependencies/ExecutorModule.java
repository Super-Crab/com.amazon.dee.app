package com.amazon.alexa.alertsca.dependencies;

import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public class ExecutorModule {
    public static final String ALEXA_CONNECTION_EXECUTOR = "alexa_connection_executor";
    public static final String SHARED_SCHEDULER = "shared_scheduler";

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named(ALEXA_CONNECTION_EXECUTOR)
    public ScheduledExecutorService providesConnectionExecutor() {
        return ManagedExecutorFactory.newSingleThreadScheduledExecutor(ALEXA_CONNECTION_EXECUTOR, ManagedExecutorFactory.Group.SERVICE_CONNECTION);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named("shared_scheduler")
    public ScheduledExecutorService providesSharedScheduler() {
        return ManagedExecutorFactory.newSingleThreadScheduledExecutor("shared_scheduler");
    }
}
