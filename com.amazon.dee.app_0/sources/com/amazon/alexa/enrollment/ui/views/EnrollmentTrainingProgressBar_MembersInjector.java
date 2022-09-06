package com.amazon.alexa.enrollment.ui.views;

import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentTrainingProgressBar_MembersInjector implements MembersInjector<EnrollmentTrainingProgressBar> {
    private final Provider<EnrollmentThemeUtil> enrollmentThemeUtilProvider;

    public EnrollmentTrainingProgressBar_MembersInjector(Provider<EnrollmentThemeUtil> provider) {
        this.enrollmentThemeUtilProvider = provider;
    }

    public static MembersInjector<EnrollmentTrainingProgressBar> create(Provider<EnrollmentThemeUtil> provider) {
        return new EnrollmentTrainingProgressBar_MembersInjector(provider);
    }

    public static void injectEnrollmentThemeUtil(EnrollmentTrainingProgressBar enrollmentTrainingProgressBar, EnrollmentThemeUtil enrollmentThemeUtil) {
        enrollmentTrainingProgressBar.enrollmentThemeUtil = enrollmentThemeUtil;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EnrollmentTrainingProgressBar enrollmentTrainingProgressBar) {
        injectEnrollmentThemeUtil(enrollmentTrainingProgressBar, this.enrollmentThemeUtilProvider.mo10268get());
    }
}
