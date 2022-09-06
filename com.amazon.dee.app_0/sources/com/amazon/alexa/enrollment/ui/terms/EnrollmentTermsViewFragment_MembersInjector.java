package com.amazon.alexa.enrollment.ui.terms;

import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment_MembersInjector;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingDialogHelper;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentTermsViewFragment_MembersInjector implements MembersInjector<EnrollmentTermsViewFragment> {
    private final Provider<EnrollmentTrainingDialogHelper> dialogHelperProvider;
    private final Provider<EnrollmentMetricsRecorder> enrollmentMetricsRecorderProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public EnrollmentTermsViewFragment_MembersInjector(Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<EnrollmentTrainingDialogHelper> provider4, Provider<EnvironmentService> provider5) {
        this.routingServiceProvider = provider;
        this.eventBusProvider = provider2;
        this.enrollmentMetricsRecorderProvider = provider3;
        this.dialogHelperProvider = provider4;
        this.environmentServiceProvider = provider5;
    }

    public static MembersInjector<EnrollmentTermsViewFragment> create(Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<EnrollmentTrainingDialogHelper> provider4, Provider<EnvironmentService> provider5) {
        return new EnrollmentTermsViewFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectEnrollmentMetricsRecorder(EnrollmentTermsViewFragment enrollmentTermsViewFragment, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        enrollmentTermsViewFragment.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }

    public static void injectEnvironmentService(EnrollmentTermsViewFragment enrollmentTermsViewFragment, EnvironmentService environmentService) {
        enrollmentTermsViewFragment.environmentService = environmentService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EnrollmentTermsViewFragment enrollmentTermsViewFragment) {
        AbstractEnrollmentViewFragment_MembersInjector.injectRoutingService(enrollmentTermsViewFragment, this.routingServiceProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectEventBus(enrollmentTermsViewFragment, this.eventBusProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentTermsViewFragment, this.enrollmentMetricsRecorderProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectDialogHelper(enrollmentTermsViewFragment, this.dialogHelperProvider.mo10268get());
        injectEnrollmentMetricsRecorder(enrollmentTermsViewFragment, this.enrollmentMetricsRecorderProvider.mo10268get());
        injectEnvironmentService(enrollmentTermsViewFragment, this.environmentServiceProvider.mo10268get());
    }
}
