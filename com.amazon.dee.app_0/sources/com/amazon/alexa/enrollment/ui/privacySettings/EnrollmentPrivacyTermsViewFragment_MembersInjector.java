package com.amazon.alexa.enrollment.ui.privacySettings;

import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment_MembersInjector;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingDialogHelper;
import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentPrivacyTermsViewFragment_MembersInjector implements MembersInjector<EnrollmentPrivacyTermsViewFragment> {
    private final Provider<EnrollmentTrainingDialogHelper> dialogHelperProvider;
    private final Provider<EnrollmentMetricsRecorder> enrollmentMetricsRecorderProvider;
    private final Provider<EnrollmentThemeUtil> enrollmentThemeUtilProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public EnrollmentPrivacyTermsViewFragment_MembersInjector(Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<EnrollmentTrainingDialogHelper> provider4, Provider<EnrollmentThemeUtil> provider5) {
        this.routingServiceProvider = provider;
        this.eventBusProvider = provider2;
        this.enrollmentMetricsRecorderProvider = provider3;
        this.dialogHelperProvider = provider4;
        this.enrollmentThemeUtilProvider = provider5;
    }

    public static MembersInjector<EnrollmentPrivacyTermsViewFragment> create(Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<EnrollmentTrainingDialogHelper> provider4, Provider<EnrollmentThemeUtil> provider5) {
        return new EnrollmentPrivacyTermsViewFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectEnrollmentMetricsRecorder(EnrollmentPrivacyTermsViewFragment enrollmentPrivacyTermsViewFragment, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        enrollmentPrivacyTermsViewFragment.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }

    public static void injectEnrollmentThemeUtil(EnrollmentPrivacyTermsViewFragment enrollmentPrivacyTermsViewFragment, EnrollmentThemeUtil enrollmentThemeUtil) {
        enrollmentPrivacyTermsViewFragment.enrollmentThemeUtil = enrollmentThemeUtil;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EnrollmentPrivacyTermsViewFragment enrollmentPrivacyTermsViewFragment) {
        AbstractEnrollmentViewFragment_MembersInjector.injectRoutingService(enrollmentPrivacyTermsViewFragment, this.routingServiceProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectEventBus(enrollmentPrivacyTermsViewFragment, this.eventBusProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentPrivacyTermsViewFragment, this.enrollmentMetricsRecorderProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectDialogHelper(enrollmentPrivacyTermsViewFragment, this.dialogHelperProvider.mo10268get());
        injectEnrollmentThemeUtil(enrollmentPrivacyTermsViewFragment, this.enrollmentThemeUtilProvider.mo10268get());
        injectEnrollmentMetricsRecorder(enrollmentPrivacyTermsViewFragment, this.enrollmentMetricsRecorderProvider.mo10268get());
    }
}
