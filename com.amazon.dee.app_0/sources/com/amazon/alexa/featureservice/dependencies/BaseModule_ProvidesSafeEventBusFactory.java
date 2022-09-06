package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.featureservice.util.SafeEventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class BaseModule_ProvidesSafeEventBusFactory implements Factory<SafeEventBus> {
    private final BaseModule module;

    public BaseModule_ProvidesSafeEventBusFactory(BaseModule baseModule) {
        this.module = baseModule;
    }

    public static BaseModule_ProvidesSafeEventBusFactory create(BaseModule baseModule) {
        return new BaseModule_ProvidesSafeEventBusFactory(baseModule);
    }

    public static SafeEventBus provideInstance(BaseModule baseModule) {
        return proxyProvidesSafeEventBus(baseModule);
    }

    public static SafeEventBus proxyProvidesSafeEventBus(BaseModule baseModule) {
        return (SafeEventBus) Preconditions.checkNotNull(baseModule.providesSafeEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SafeEventBus mo10268get() {
        return provideInstance(this.module);
    }
}
