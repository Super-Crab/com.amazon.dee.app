package com.amazon.alexa.enrollment.ui;

import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class AbstractBottomSheetFragment_MembersInjector implements MembersInjector<AbstractBottomSheetFragment> {
    private final Provider<EnrollmentMetricsRecorder> enrollmentMetricsRecorderProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public AbstractBottomSheetFragment_MembersInjector(Provider<EventBus> provider, Provider<RoutingService> provider2, Provider<EnrollmentMetricsRecorder> provider3) {
        this.eventBusProvider = provider;
        this.routingServiceProvider = provider2;
        this.enrollmentMetricsRecorderProvider = provider3;
    }

    public static MembersInjector<AbstractBottomSheetFragment> create(Provider<EventBus> provider, Provider<RoutingService> provider2, Provider<EnrollmentMetricsRecorder> provider3) {
        return new AbstractBottomSheetFragment_MembersInjector(provider, provider2, provider3);
    }

    public static void injectEnrollmentMetricsRecorder(AbstractBottomSheetFragment abstractBottomSheetFragment, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        abstractBottomSheetFragment.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }

    public static void injectEventBus(AbstractBottomSheetFragment abstractBottomSheetFragment, EventBus eventBus) {
        abstractBottomSheetFragment.eventBus = eventBus;
    }

    public static void injectRoutingService(AbstractBottomSheetFragment abstractBottomSheetFragment, RoutingService routingService) {
        abstractBottomSheetFragment.routingService = routingService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AbstractBottomSheetFragment abstractBottomSheetFragment) {
        injectEventBus(abstractBottomSheetFragment, this.eventBusProvider.mo10268get());
        injectRoutingService(abstractBottomSheetFragment, this.routingServiceProvider.mo10268get());
        injectEnrollmentMetricsRecorder(abstractBottomSheetFragment, this.enrollmentMetricsRecorderProvider.mo10268get());
    }
}
