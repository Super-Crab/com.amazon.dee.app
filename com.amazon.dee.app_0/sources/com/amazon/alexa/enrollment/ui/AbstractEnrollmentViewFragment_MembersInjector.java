package com.amazon.alexa.enrollment.ui;

import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingDialogHelper;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class AbstractEnrollmentViewFragment_MembersInjector implements MembersInjector<AbstractEnrollmentViewFragment> {
    private final Provider<EnrollmentTrainingDialogHelper> dialogHelperProvider;
    private final Provider<EnrollmentMetricsRecorder> enrollmentMetricsRecorderProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public AbstractEnrollmentViewFragment_MembersInjector(Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<EnrollmentTrainingDialogHelper> provider4) {
        this.routingServiceProvider = provider;
        this.eventBusProvider = provider2;
        this.enrollmentMetricsRecorderProvider = provider3;
        this.dialogHelperProvider = provider4;
    }

    public static MembersInjector<AbstractEnrollmentViewFragment> create(Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<EnrollmentTrainingDialogHelper> provider4) {
        return new AbstractEnrollmentViewFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectDialogHelper(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment, EnrollmentTrainingDialogHelper enrollmentTrainingDialogHelper) {
        abstractEnrollmentViewFragment.dialogHelper = enrollmentTrainingDialogHelper;
    }

    public static void injectEnrollmentMetricsRecorder(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        abstractEnrollmentViewFragment.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }

    public static void injectEventBus(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment, EventBus eventBus) {
        abstractEnrollmentViewFragment.eventBus = eventBus;
    }

    public static void injectRoutingService(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment, RoutingService routingService) {
        abstractEnrollmentViewFragment.routingService = routingService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AbstractEnrollmentViewFragment abstractEnrollmentViewFragment) {
        injectRoutingService(abstractEnrollmentViewFragment, this.routingServiceProvider.mo10268get());
        injectEventBus(abstractEnrollmentViewFragment, this.eventBusProvider.mo10268get());
        injectEnrollmentMetricsRecorder(abstractEnrollmentViewFragment, this.enrollmentMetricsRecorderProvider.mo10268get());
        injectDialogHelper(abstractEnrollmentViewFragment, this.dialogHelperProvider.mo10268get());
    }
}
