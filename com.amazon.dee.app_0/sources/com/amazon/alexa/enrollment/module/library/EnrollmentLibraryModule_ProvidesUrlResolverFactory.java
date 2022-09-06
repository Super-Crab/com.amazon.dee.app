package com.amazon.alexa.enrollment.module.library;

import com.dee.app.http.UrlResolver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_ProvidesUrlResolverFactory implements Factory<UrlResolver> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_ProvidesUrlResolverFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_ProvidesUrlResolverFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_ProvidesUrlResolverFactory(enrollmentLibraryModule);
    }

    public static UrlResolver provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyProvidesUrlResolver(enrollmentLibraryModule);
    }

    public static UrlResolver proxyProvidesUrlResolver(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (UrlResolver) Preconditions.checkNotNull(enrollmentLibraryModule.providesUrlResolver(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UrlResolver mo10268get() {
        return provideInstance(this.module);
    }
}
