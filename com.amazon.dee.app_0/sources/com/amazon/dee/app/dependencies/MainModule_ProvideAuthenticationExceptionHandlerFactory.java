package com.amazon.dee.app.dependencies;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.ui.main.AuthenticationExceptionHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvideAuthenticationExceptionHandlerFactory implements Factory<AuthenticationExceptionHandler> {
    private final Provider<Mobilytics> mobilyticsProvider;
    private final MainModule module;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<UserMessageService> userMessageServiceProvider;

    public MainModule_ProvideAuthenticationExceptionHandlerFactory(MainModule mainModule, Provider<Mobilytics> provider, Provider<UserMessageService> provider2, Provider<RoutingService> provider3) {
        this.module = mainModule;
        this.mobilyticsProvider = provider;
        this.userMessageServiceProvider = provider2;
        this.routingServiceProvider = provider3;
    }

    public static MainModule_ProvideAuthenticationExceptionHandlerFactory create(MainModule mainModule, Provider<Mobilytics> provider, Provider<UserMessageService> provider2, Provider<RoutingService> provider3) {
        return new MainModule_ProvideAuthenticationExceptionHandlerFactory(mainModule, provider, provider2, provider3);
    }

    public static AuthenticationExceptionHandler provideInstance(MainModule mainModule, Provider<Mobilytics> provider, Provider<UserMessageService> provider2, Provider<RoutingService> provider3) {
        return proxyProvideAuthenticationExceptionHandler(mainModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static AuthenticationExceptionHandler proxyProvideAuthenticationExceptionHandler(MainModule mainModule, Mobilytics mobilytics, UserMessageService userMessageService, RoutingService routingService) {
        return (AuthenticationExceptionHandler) Preconditions.checkNotNull(mainModule.provideAuthenticationExceptionHandler(mobilytics, userMessageService, routingService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AuthenticationExceptionHandler mo10268get() {
        return provideInstance(this.module, this.mobilyticsProvider, this.userMessageServiceProvider, this.routingServiceProvider);
    }
}
