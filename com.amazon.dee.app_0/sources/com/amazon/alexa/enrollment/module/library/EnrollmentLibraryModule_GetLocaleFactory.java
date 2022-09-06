package com.amazon.alexa.enrollment.module.library;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Locale;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetLocaleFactory implements Factory<Locale> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetLocaleFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetLocaleFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetLocaleFactory(enrollmentLibraryModule);
    }

    public static Locale provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetLocale(enrollmentLibraryModule);
    }

    public static Locale proxyGetLocale(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (Locale) Preconditions.checkNotNull(enrollmentLibraryModule.getLocale(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Locale mo10268get() {
        return provideInstance(this.module);
    }
}
