package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.app.job.JobScheduler;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ContextModule_ProvidesJobSchedulerFactory implements Factory<JobScheduler> {
    private final Provider<Context> contextProvider;
    private final ContextModule module;

    public ContextModule_ProvidesJobSchedulerFactory(ContextModule contextModule, Provider<Context> provider) {
        this.module = contextModule;
        this.contextProvider = provider;
    }

    public static ContextModule_ProvidesJobSchedulerFactory create(ContextModule contextModule, Provider<Context> provider) {
        return new ContextModule_ProvidesJobSchedulerFactory(contextModule, provider);
    }

    public static JobScheduler provideInstance(ContextModule contextModule, Provider<Context> provider) {
        return proxyProvidesJobScheduler(contextModule, provider.mo10268get());
    }

    public static JobScheduler proxyProvidesJobScheduler(ContextModule contextModule, Context context) {
        return (JobScheduler) Preconditions.checkNotNull(contextModule.providesJobScheduler(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public JobScheduler mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
