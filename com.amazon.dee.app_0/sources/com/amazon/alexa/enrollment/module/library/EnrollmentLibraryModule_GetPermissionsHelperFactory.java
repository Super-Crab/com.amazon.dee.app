package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.utils.PermissionsHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetPermissionsHelperFactory implements Factory<PermissionsHelper> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetPermissionsHelperFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetPermissionsHelperFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetPermissionsHelperFactory(enrollmentLibraryModule);
    }

    public static PermissionsHelper provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetPermissionsHelper(enrollmentLibraryModule);
    }

    public static PermissionsHelper proxyGetPermissionsHelper(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (PermissionsHelper) Preconditions.checkNotNull(enrollmentLibraryModule.getPermissionsHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PermissionsHelper mo10268get() {
        return provideInstance(this.module);
    }
}
