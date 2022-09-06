package com.amazon.alexa.enrollment.module.app;

import android.content.Context;
import com.amazon.alexa.enrollment.model.EnrollmentGateway;
import com.amazon.alexa.enrollment.route.EnrollmentRoutingAdapter;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentModule_ProvideEnrollmentRoutingAdapterFactory implements Factory<EnrollmentRoutingAdapter> {
    private final Provider<Context> contextProvider;
    private final Provider<EnrollmentGateway> enrollmentServiceProvider;
    private final EnrollmentModule module;

    public EnrollmentModule_ProvideEnrollmentRoutingAdapterFactory(EnrollmentModule enrollmentModule, Provider<Context> provider, Provider<EnrollmentGateway> provider2) {
        this.module = enrollmentModule;
        this.contextProvider = provider;
        this.enrollmentServiceProvider = provider2;
    }

    public static EnrollmentModule_ProvideEnrollmentRoutingAdapterFactory create(EnrollmentModule enrollmentModule, Provider<Context> provider, Provider<EnrollmentGateway> provider2) {
        return new EnrollmentModule_ProvideEnrollmentRoutingAdapterFactory(enrollmentModule, provider, provider2);
    }

    public static EnrollmentRoutingAdapter provideInstance(EnrollmentModule enrollmentModule, Provider<Context> provider, Provider<EnrollmentGateway> provider2) {
        return proxyProvideEnrollmentRoutingAdapter(enrollmentModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static EnrollmentRoutingAdapter proxyProvideEnrollmentRoutingAdapter(EnrollmentModule enrollmentModule, Lazy<Context> lazy, Lazy<EnrollmentGateway> lazy2) {
        return (EnrollmentRoutingAdapter) Preconditions.checkNotNull(enrollmentModule.provideEnrollmentRoutingAdapter(lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentRoutingAdapter mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.enrollmentServiceProvider);
    }
}
