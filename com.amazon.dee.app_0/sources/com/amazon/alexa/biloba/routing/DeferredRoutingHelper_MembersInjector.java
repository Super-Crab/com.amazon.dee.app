package com.amazon.alexa.biloba.routing;

import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DeferredRoutingHelper_MembersInjector implements MembersInjector<DeferredRoutingHelper> {
    private final Provider<RoutingService> routingServiceProvider;

    public DeferredRoutingHelper_MembersInjector(Provider<RoutingService> provider) {
        this.routingServiceProvider = provider;
    }

    public static MembersInjector<DeferredRoutingHelper> create(Provider<RoutingService> provider) {
        return new DeferredRoutingHelper_MembersInjector(provider);
    }

    public static void injectRoutingService(DeferredRoutingHelper deferredRoutingHelper, Lazy<RoutingService> lazy) {
        deferredRoutingHelper.routingService = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DeferredRoutingHelper deferredRoutingHelper) {
        injectRoutingService(deferredRoutingHelper, DoubleCheck.lazy(this.routingServiceProvider));
    }
}
