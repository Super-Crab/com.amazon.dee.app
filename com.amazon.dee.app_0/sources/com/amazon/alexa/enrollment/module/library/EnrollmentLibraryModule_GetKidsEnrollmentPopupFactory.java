package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPopup;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetKidsEnrollmentPopupFactory implements Factory<KidsEnrollmentPopup> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetKidsEnrollmentPopupFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetKidsEnrollmentPopupFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetKidsEnrollmentPopupFactory(enrollmentLibraryModule);
    }

    public static KidsEnrollmentPopup provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetKidsEnrollmentPopup(enrollmentLibraryModule);
    }

    public static KidsEnrollmentPopup proxyGetKidsEnrollmentPopup(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (KidsEnrollmentPopup) Preconditions.checkNotNull(enrollmentLibraryModule.getKidsEnrollmentPopup(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KidsEnrollmentPopup mo10268get() {
        return provideInstance(this.module);
    }
}
