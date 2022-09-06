package com.amazon.alexa.biloba.view.infoModal;

import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class InfoModalViewModel_MembersInjector implements MembersInjector<InfoModalViewModel> {
    private final Provider<RoutingService> routingServiceProvider;

    public InfoModalViewModel_MembersInjector(Provider<RoutingService> provider) {
        this.routingServiceProvider = provider;
    }

    public static MembersInjector<InfoModalViewModel> create(Provider<RoutingService> provider) {
        return new InfoModalViewModel_MembersInjector(provider);
    }

    public static void injectRoutingService(InfoModalViewModel infoModalViewModel, Lazy<RoutingService> lazy) {
        infoModalViewModel.routingService = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(InfoModalViewModel infoModalViewModel) {
        injectRoutingService(infoModalViewModel, DoubleCheck.lazy(this.routingServiceProvider));
    }
}
