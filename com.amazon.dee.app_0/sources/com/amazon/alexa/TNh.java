package com.amazon.alexa;

import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: ExecutorModule.java */
@Module
/* loaded from: classes.dex */
public class TNh {
    @Provides
    @Singleton
    @Named("shared_scheduler")
    public ScheduledExecutorService zZm() {
        return ManagedExecutorFactory.newScheduledExecutor(3, "shared_scheduler");
    }
}
