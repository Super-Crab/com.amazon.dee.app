package com.amazon.alexa.dnssd.dependencies;

import com.amazon.alexa.dnssd.util.ObjectMapperFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class DnssdModule_ProvideObjectMapperFactoryFactory implements Factory<ObjectMapperFactory> {
    private final DnssdModule module;

    public DnssdModule_ProvideObjectMapperFactoryFactory(DnssdModule dnssdModule) {
        this.module = dnssdModule;
    }

    public static DnssdModule_ProvideObjectMapperFactoryFactory create(DnssdModule dnssdModule) {
        return new DnssdModule_ProvideObjectMapperFactoryFactory(dnssdModule);
    }

    public static ObjectMapperFactory provideInstance(DnssdModule dnssdModule) {
        return proxyProvideObjectMapperFactory(dnssdModule);
    }

    public static ObjectMapperFactory proxyProvideObjectMapperFactory(DnssdModule dnssdModule) {
        return (ObjectMapperFactory) Preconditions.checkNotNull(dnssdModule.provideObjectMapperFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ObjectMapperFactory mo10268get() {
        return provideInstance(this.module);
    }
}
