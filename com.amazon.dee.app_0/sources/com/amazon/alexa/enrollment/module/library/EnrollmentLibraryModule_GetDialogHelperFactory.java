package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingDialogHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetDialogHelperFactory implements Factory<EnrollmentTrainingDialogHelper> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetDialogHelperFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetDialogHelperFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetDialogHelperFactory(enrollmentLibraryModule);
    }

    public static EnrollmentTrainingDialogHelper provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetDialogHelper(enrollmentLibraryModule);
    }

    public static EnrollmentTrainingDialogHelper proxyGetDialogHelper(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (EnrollmentTrainingDialogHelper) Preconditions.checkNotNull(enrollmentLibraryModule.getDialogHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentTrainingDialogHelper mo10268get() {
        return provideInstance(this.module);
    }
}
