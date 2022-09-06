package com.amazon.alexa.biloba.routing;

import com.amazon.alexa.routing.api.RoutingService;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DeferredRoutingHelper_Factory implements Factory<DeferredRoutingHelper> {
    private final Provider<RoutingService> routingServiceProvider;

    public DeferredRoutingHelper_Factory(Provider<RoutingService> provider) {
        this.routingServiceProvider = provider;
    }

    public static DeferredRoutingHelper_Factory create(Provider<RoutingService> provider) {
        return new DeferredRoutingHelper_Factory(provider);
    }

    public static DeferredRoutingHelper newDeferredRoutingHelper() {
        return new DeferredRoutingHelper();
    }

    public static DeferredRoutingHelper provideInstance(Provider<RoutingService> provider) {
        DeferredRoutingHelper deferredRoutingHelper = new DeferredRoutingHelper();
        DeferredRoutingHelper_MembersInjector.injectRoutingService(deferredRoutingHelper, DoubleCheck.lazy(provider));
        return deferredRoutingHelper;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeferredRoutingHelper mo10268get() {
        return provideInstance(this.routingServiceProvider);
    }
}
