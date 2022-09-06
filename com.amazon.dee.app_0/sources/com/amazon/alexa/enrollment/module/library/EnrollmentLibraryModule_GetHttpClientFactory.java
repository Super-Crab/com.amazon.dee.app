package com.amazon.alexa.enrollment.module.library;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.OkHttpClient;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetHttpClientFactory implements Factory<OkHttpClient> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetHttpClientFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetHttpClientFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetHttpClientFactory(enrollmentLibraryModule);
    }

    public static OkHttpClient provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetHttpClient(enrollmentLibraryModule);
    }

    public static OkHttpClient proxyGetHttpClient(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (OkHttpClient) Preconditions.checkNotNull(enrollmentLibraryModule.getHttpClient(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public OkHttpClient mo10268get() {
        return provideInstance(this.module);
    }
}
