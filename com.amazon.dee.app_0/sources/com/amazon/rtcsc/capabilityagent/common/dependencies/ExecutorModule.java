package com.amazon.rtcsc.capabilityagent.common.dependencies;

import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes13.dex */
public class ExecutorModule {
    public static final String ALEXA_CONNECTION_EXECUTOR = "alexa connection executor";
    public static final String SHARED_SCHEDULER = "shared_scheduler";

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named(ALEXA_CONNECTION_EXECUTOR)
    public ScheduledExecutorService providesConnectionExecutor() {
        return ExecutorFactory.newSingleThreadScheduledExecutor(ALEXA_CONNECTION_EXECUTOR);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named("shared_scheduler")
    public ScheduledExecutorService providesSharedScheduler() {
        return ExecutorFactory.newSingleThreadScheduledExecutor("shared_scheduler");
    }
}
