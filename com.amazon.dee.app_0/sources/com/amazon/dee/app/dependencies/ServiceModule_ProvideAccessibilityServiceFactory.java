package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.dee.app.services.accessibility.AccessibilityService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideAccessibilityServiceFactory implements Factory<AccessibilityService> {
    private final Provider<Context> contextProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideAccessibilityServiceFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<Mobilytics> provider2) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.mobilyticsProvider = provider2;
    }

    public static ServiceModule_ProvideAccessibilityServiceFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<Mobilytics> provider2) {
        return new ServiceModule_ProvideAccessibilityServiceFactory(serviceModule, provider, provider2);
    }

    public static AccessibilityService provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<Mobilytics> provider2) {
        return proxyProvideAccessibilityService(serviceModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static AccessibilityService proxyProvideAccessibilityService(ServiceModule serviceModule, Context context, Mobilytics mobilytics) {
        return (AccessibilityService) Preconditions.checkNotNull(serviceModule.provideAccessibilityService(context, mobilytics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccessibilityService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.mobilyticsProvider);
    }
}
