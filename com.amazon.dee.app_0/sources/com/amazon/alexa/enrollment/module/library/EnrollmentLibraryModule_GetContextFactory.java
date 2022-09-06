package com.amazon.alexa.enrollment.module.library;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetContextFactory implements Factory<Context> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetContextFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetContextFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetContextFactory(enrollmentLibraryModule);
    }

    public static Context provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetContext(enrollmentLibraryModule);
    }

    public static Context proxyGetContext(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (Context) Preconditions.checkNotNull(enrollmentLibraryModule.getContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
