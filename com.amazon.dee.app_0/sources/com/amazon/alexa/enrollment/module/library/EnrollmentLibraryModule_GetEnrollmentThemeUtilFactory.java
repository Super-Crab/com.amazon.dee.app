package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetEnrollmentThemeUtilFactory implements Factory<EnrollmentThemeUtil> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetEnrollmentThemeUtilFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetEnrollmentThemeUtilFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetEnrollmentThemeUtilFactory(enrollmentLibraryModule);
    }

    public static EnrollmentThemeUtil provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetEnrollmentThemeUtil(enrollmentLibraryModule);
    }

    public static EnrollmentThemeUtil proxyGetEnrollmentThemeUtil(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (EnrollmentThemeUtil) Preconditions.checkNotNull(enrollmentLibraryModule.getEnrollmentThemeUtil(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentThemeUtil mo10268get() {
        return provideInstance(this.module);
    }
}
