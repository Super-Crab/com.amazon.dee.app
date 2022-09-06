package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.utils.AnimationHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetAnimationHelperFactory implements Factory<AnimationHelper> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetAnimationHelperFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetAnimationHelperFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetAnimationHelperFactory(enrollmentLibraryModule);
    }

    public static AnimationHelper provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetAnimationHelper(enrollmentLibraryModule);
    }

    public static AnimationHelper proxyGetAnimationHelper(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (AnimationHelper) Preconditions.checkNotNull(enrollmentLibraryModule.getAnimationHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AnimationHelper mo10268get() {
        return provideInstance(this.module);
    }
}
