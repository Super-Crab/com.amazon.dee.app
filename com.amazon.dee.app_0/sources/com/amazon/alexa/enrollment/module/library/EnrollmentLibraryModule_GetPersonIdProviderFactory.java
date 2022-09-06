package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.identity.api.PersonIdProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetPersonIdProviderFactory implements Factory<PersonIdProvider> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetPersonIdProviderFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetPersonIdProviderFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetPersonIdProviderFactory(enrollmentLibraryModule);
    }

    public static PersonIdProvider provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetPersonIdProvider(enrollmentLibraryModule);
    }

    public static PersonIdProvider proxyGetPersonIdProvider(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (PersonIdProvider) Preconditions.checkNotNull(enrollmentLibraryModule.getPersonIdProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PersonIdProvider mo10268get() {
        return provideInstance(this.module);
    }
}
