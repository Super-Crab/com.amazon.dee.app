package com.amazon.alexa.enrollment.module.app;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.enrollment.model.EnrollmentGateway;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import com.dee.app.http.CoralService;
import com.dee.app.http.UrlResolver;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentModule_ProvideEnrollmentServiceFactory implements Factory<EnrollmentGateway> {
    private final Provider<Context> contextProvider;
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final EnrollmentModule module;
    private final Provider<PersonIdProvider> personIdProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<UrlResolver> urlResolverProvider;

    public EnrollmentModule_ProvideEnrollmentServiceFactory(EnrollmentModule enrollmentModule, Provider<Context> provider, Provider<CoralService> provider2, Provider<PersonIdProvider> provider3, Provider<Mobilytics> provider4, Provider<DeviceInformation> provider5, Provider<IdentityService> provider6, Provider<EventBus> provider7, Provider<RoutingService> provider8, Provider<UrlResolver> provider9, Provider<EnvironmentService> provider10) {
        this.module = enrollmentModule;
        this.contextProvider = provider;
        this.coralServiceProvider = provider2;
        this.personIdProvider = provider3;
        this.mobilyticsProvider = provider4;
        this.deviceInformationProvider = provider5;
        this.identityServiceProvider = provider6;
        this.eventBusProvider = provider7;
        this.routingServiceProvider = provider8;
        this.urlResolverProvider = provider9;
        this.environmentServiceProvider = provider10;
    }

    public static EnrollmentModule_ProvideEnrollmentServiceFactory create(EnrollmentModule enrollmentModule, Provider<Context> provider, Provider<CoralService> provider2, Provider<PersonIdProvider> provider3, Provider<Mobilytics> provider4, Provider<DeviceInformation> provider5, Provider<IdentityService> provider6, Provider<EventBus> provider7, Provider<RoutingService> provider8, Provider<UrlResolver> provider9, Provider<EnvironmentService> provider10) {
        return new EnrollmentModule_ProvideEnrollmentServiceFactory(enrollmentModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static EnrollmentGateway provideInstance(EnrollmentModule enrollmentModule, Provider<Context> provider, Provider<CoralService> provider2, Provider<PersonIdProvider> provider3, Provider<Mobilytics> provider4, Provider<DeviceInformation> provider5, Provider<IdentityService> provider6, Provider<EventBus> provider7, Provider<RoutingService> provider8, Provider<UrlResolver> provider9, Provider<EnvironmentService> provider10) {
        return proxyProvideEnrollmentService(enrollmentModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10));
    }

    public static EnrollmentGateway proxyProvideEnrollmentService(EnrollmentModule enrollmentModule, Lazy<Context> lazy, Lazy<CoralService> lazy2, Lazy<PersonIdProvider> lazy3, Lazy<Mobilytics> lazy4, Lazy<DeviceInformation> lazy5, Lazy<IdentityService> lazy6, Lazy<EventBus> lazy7, Lazy<RoutingService> lazy8, Lazy<UrlResolver> lazy9, Lazy<EnvironmentService> lazy10) {
        return (EnrollmentGateway) Preconditions.checkNotNull(enrollmentModule.provideEnrollmentService(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentGateway mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.coralServiceProvider, this.personIdProvider, this.mobilyticsProvider, this.deviceInformationProvider, this.identityServiceProvider, this.eventBusProvider, this.routingServiceProvider, this.urlResolverProvider, this.environmentServiceProvider);
    }
}
