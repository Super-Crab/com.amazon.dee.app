package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentDialogHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetKidsEnrollmentDialogHelperFactory implements Factory<KidsEnrollmentDialogHelper> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetKidsEnrollmentDialogHelperFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetKidsEnrollmentDialogHelperFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetKidsEnrollmentDialogHelperFactory(enrollmentLibraryModule);
    }

    public static KidsEnrollmentDialogHelper provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetKidsEnrollmentDialogHelper(enrollmentLibraryModule);
    }

    public static KidsEnrollmentDialogHelper proxyGetKidsEnrollmentDialogHelper(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (KidsEnrollmentDialogHelper) Preconditions.checkNotNull(enrollmentLibraryModule.getKidsEnrollmentDialogHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KidsEnrollmentDialogHelper mo10268get() {
        return provideInstance(this.module);
    }
}
