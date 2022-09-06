package com.amazon.alexa.enrollment.module.app;

import android.content.Context;
import com.amazon.alexa.enrollment.model.EnrollmentGateway;
import com.amazon.alexa.enrollment.route.KidsEnrollmentRoutingAdapter;
import com.amazon.alexa.identity.api.IdentityService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentModule_ProvideKidsEnrollmentRoutingAdapterFactory implements Factory<KidsEnrollmentRoutingAdapter> {
    private final Provider<Context> contextProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<EnrollmentGateway> kidsEnrollmentServiceProvider;
    private final EnrollmentModule module;

    public EnrollmentModule_ProvideKidsEnrollmentRoutingAdapterFactory(EnrollmentModule enrollmentModule, Provider<Context> provider, Provider<EnrollmentGateway> provider2, Provider<IdentityService> provider3) {
        this.module = enrollmentModule;
        this.contextProvider = provider;
        this.kidsEnrollmentServiceProvider = provider2;
        this.identityServiceProvider = provider3;
    }

    public static EnrollmentModule_ProvideKidsEnrollmentRoutingAdapterFactory create(EnrollmentModule enrollmentModule, Provider<Context> provider, Provider<EnrollmentGateway> provider2, Provider<IdentityService> provider3) {
        return new EnrollmentModule_ProvideKidsEnrollmentRoutingAdapterFactory(enrollmentModule, provider, provider2, provider3);
    }

    public static KidsEnrollmentRoutingAdapter provideInstance(EnrollmentModule enrollmentModule, Provider<Context> provider, Provider<EnrollmentGateway> provider2, Provider<IdentityService> provider3) {
        return proxyProvideKidsEnrollmentRoutingAdapter(enrollmentModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static KidsEnrollmentRoutingAdapter proxyProvideKidsEnrollmentRoutingAdapter(EnrollmentModule enrollmentModule, Lazy<Context> lazy, Lazy<EnrollmentGateway> lazy2, Lazy<IdentityService> lazy3) {
        return (KidsEnrollmentRoutingAdapter) Preconditions.checkNotNull(enrollmentModule.provideKidsEnrollmentRoutingAdapter(lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KidsEnrollmentRoutingAdapter mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.kidsEnrollmentServiceProvider, this.identityServiceProvider);
    }
}
