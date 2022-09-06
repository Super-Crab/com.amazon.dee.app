package com.amazon.alexa.enrollment.ui.complete;

import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment_MembersInjector;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingDialogHelper;
import com.amazon.alexa.enrollment.utils.AnimationHelper;
import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentCompleteViewFragment_MembersInjector implements MembersInjector<EnrollmentCompleteViewFragment> {
    private final Provider<AnimationHelper> animationHelperProvider;
    private final Provider<EnrollmentTrainingDialogHelper> dialogHelperProvider;
    private final Provider<EnrollmentMetricsRecorder> enrollmentMetricsRecorderProvider;
    private final Provider<EnrollmentThemeUtil> enrollmentThemeUtilProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public EnrollmentCompleteViewFragment_MembersInjector(Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<EnrollmentTrainingDialogHelper> provider4, Provider<AnimationHelper> provider5, Provider<EnrollmentThemeUtil> provider6) {
        this.routingServiceProvider = provider;
        this.eventBusProvider = provider2;
        this.enrollmentMetricsRecorderProvider = provider3;
        this.dialogHelperProvider = provider4;
        this.animationHelperProvider = provider5;
        this.enrollmentThemeUtilProvider = provider6;
    }

    public static MembersInjector<EnrollmentCompleteViewFragment> create(Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<EnrollmentTrainingDialogHelper> provider4, Provider<AnimationHelper> provider5, Provider<EnrollmentThemeUtil> provider6) {
        return new EnrollmentCompleteViewFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectAnimationHelper(EnrollmentCompleteViewFragment enrollmentCompleteViewFragment, AnimationHelper animationHelper) {
        enrollmentCompleteViewFragment.animationHelper = animationHelper;
    }

    public static void injectEnrollmentMetricsRecorder(EnrollmentCompleteViewFragment enrollmentCompleteViewFragment, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        enrollmentCompleteViewFragment.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }

    public static void injectEnrollmentThemeUtil(EnrollmentCompleteViewFragment enrollmentCompleteViewFragment, EnrollmentThemeUtil enrollmentThemeUtil) {
        enrollmentCompleteViewFragment.enrollmentThemeUtil = enrollmentThemeUtil;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EnrollmentCompleteViewFragment enrollmentCompleteViewFragment) {
        AbstractEnrollmentViewFragment_MembersInjector.injectRoutingService(enrollmentCompleteViewFragment, this.routingServiceProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectEventBus(enrollmentCompleteViewFragment, this.eventBusProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentCompleteViewFragment, this.enrollmentMetricsRecorderProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectDialogHelper(enrollmentCompleteViewFragment, this.dialogHelperProvider.mo10268get());
        injectAnimationHelper(enrollmentCompleteViewFragment, this.animationHelperProvider.mo10268get());
        injectEnrollmentThemeUtil(enrollmentCompleteViewFragment, this.enrollmentThemeUtilProvider.mo10268get());
        injectEnrollmentMetricsRecorder(enrollmentCompleteViewFragment, this.enrollmentMetricsRecorderProvider.mo10268get());
    }
}
