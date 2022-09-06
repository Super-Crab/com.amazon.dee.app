package com.amazon.alexa.dnssd.dependencies;

import com.amazon.alexa.tasks.api.TaskManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class DnssdModule_ProvideTaskManagerFactory implements Factory<TaskManager> {
    private final DnssdModule module;

    public DnssdModule_ProvideTaskManagerFactory(DnssdModule dnssdModule) {
        this.module = dnssdModule;
    }

    public static DnssdModule_ProvideTaskManagerFactory create(DnssdModule dnssdModule) {
        return new DnssdModule_ProvideTaskManagerFactory(dnssdModule);
    }

    public static TaskManager provideInstance(DnssdModule dnssdModule) {
        return proxyProvideTaskManager(dnssdModule);
    }

    public static TaskManager proxyProvideTaskManager(DnssdModule dnssdModule) {
        return (TaskManager) Preconditions.checkNotNull(dnssdModule.provideTaskManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TaskManager mo10268get() {
        return provideInstance(this.module);
    }
}
