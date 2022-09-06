package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class EnrollmentTypeResolverModule_ProvideVoxEnrollmentTypeResolverFactory implements Factory<EnrollmentTypeResolver> {
    private final Provider<Context> contextProvider;
    private final Provider<HandsFreeUserIdentityProvider> handsFreeUserIdentityProvider;
    private final EnrollmentTypeResolverModule module;
    private final Provider<VendorAPIWrapperProvider> vendorAPIWrapperProvider;

    public EnrollmentTypeResolverModule_ProvideVoxEnrollmentTypeResolverFactory(EnrollmentTypeResolverModule enrollmentTypeResolverModule, Provider<Context> provider, Provider<VendorAPIWrapperProvider> provider2, Provider<HandsFreeUserIdentityProvider> provider3) {
        this.module = enrollmentTypeResolverModule;
        this.contextProvider = provider;
        this.vendorAPIWrapperProvider = provider2;
        this.handsFreeUserIdentityProvider = provider3;
    }

    public static EnrollmentTypeResolverModule_ProvideVoxEnrollmentTypeResolverFactory create(EnrollmentTypeResolverModule enrollmentTypeResolverModule, Provider<Context> provider, Provider<VendorAPIWrapperProvider> provider2, Provider<HandsFreeUserIdentityProvider> provider3) {
        return new EnrollmentTypeResolverModule_ProvideVoxEnrollmentTypeResolverFactory(enrollmentTypeResolverModule, provider, provider2, provider3);
    }

    public static EnrollmentTypeResolver provideInstance(EnrollmentTypeResolverModule enrollmentTypeResolverModule, Provider<Context> provider, Provider<VendorAPIWrapperProvider> provider2, Provider<HandsFreeUserIdentityProvider> provider3) {
        return proxyProvideVoxEnrollmentTypeResolver(enrollmentTypeResolverModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static EnrollmentTypeResolver proxyProvideVoxEnrollmentTypeResolver(EnrollmentTypeResolverModule enrollmentTypeResolverModule, Context context, VendorAPIWrapperProvider vendorAPIWrapperProvider, HandsFreeUserIdentityProvider handsFreeUserIdentityProvider) {
        return (EnrollmentTypeResolver) Preconditions.checkNotNull(enrollmentTypeResolverModule.provideVoxEnrollmentTypeResolver(context, vendorAPIWrapperProvider, handsFreeUserIdentityProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentTypeResolver mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.vendorAPIWrapperProvider, this.handsFreeUserIdentityProvider);
    }
}
