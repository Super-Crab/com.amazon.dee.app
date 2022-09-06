package com.amazon.alexa.enrollment.ui.kidsenrollment;

import android.content.Context;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment_MembersInjector;
import com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionViewModel;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingDialogHelper;
import com.amazon.alexa.enrollment.utils.AnimationHelper;
import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class KidsEnrollmentPrimerViewFragment_MembersInjector implements MembersInjector<KidsEnrollmentPrimerViewFragment> {
    private final Provider<AnimationHelper> animationHelperProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EnrollmentTrainingDialogHelper> dialogHelperAndEnrollmentDialogHelperProvider;
    private final Provider<KidsEnrollmentDialogHelper> dialogHelperProvider;
    private final Provider<EnrollmentMetricsRecorder> enrollmentMetricsRecorderProvider;
    private final Provider<EnrollmentThemeUtil> enrollmentThemeUtilProvider;
    private final Provider<EnrollmentIntroductionViewModel> enrollmentViewModelProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<KidsEnrollmentPopup> popupFragmentProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public KidsEnrollmentPrimerViewFragment_MembersInjector(Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<EnrollmentTrainingDialogHelper> provider4, Provider<KidsEnrollmentPopup> provider5, Provider<AnimationHelper> provider6, Provider<KidsEnrollmentDialogHelper> provider7, Provider<EnrollmentThemeUtil> provider8, Provider<EnrollmentIntroductionViewModel> provider9, Provider<IdentityService> provider10, Provider<Context> provider11) {
        this.routingServiceProvider = provider;
        this.eventBusProvider = provider2;
        this.enrollmentMetricsRecorderProvider = provider3;
        this.dialogHelperAndEnrollmentDialogHelperProvider = provider4;
        this.popupFragmentProvider = provider5;
        this.animationHelperProvider = provider6;
        this.dialogHelperProvider = provider7;
        this.enrollmentThemeUtilProvider = provider8;
        this.enrollmentViewModelProvider = provider9;
        this.identityServiceProvider = provider10;
        this.contextProvider = provider11;
    }

    public static MembersInjector<KidsEnrollmentPrimerViewFragment> create(Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<EnrollmentTrainingDialogHelper> provider4, Provider<KidsEnrollmentPopup> provider5, Provider<AnimationHelper> provider6, Provider<KidsEnrollmentDialogHelper> provider7, Provider<EnrollmentThemeUtil> provider8, Provider<EnrollmentIntroductionViewModel> provider9, Provider<IdentityService> provider10, Provider<Context> provider11) {
        return new KidsEnrollmentPrimerViewFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static void injectAnimationHelper(KidsEnrollmentPrimerViewFragment kidsEnrollmentPrimerViewFragment, AnimationHelper animationHelper) {
        kidsEnrollmentPrimerViewFragment.animationHelper = animationHelper;
    }

    public static void injectContext(KidsEnrollmentPrimerViewFragment kidsEnrollmentPrimerViewFragment, Context context) {
        kidsEnrollmentPrimerViewFragment.context = context;
    }

    public static void injectDialogHelper(KidsEnrollmentPrimerViewFragment kidsEnrollmentPrimerViewFragment, KidsEnrollmentDialogHelper kidsEnrollmentDialogHelper) {
        kidsEnrollmentPrimerViewFragment.dialogHelper = kidsEnrollmentDialogHelper;
    }

    public static void injectEnrollmentDialogHelper(KidsEnrollmentPrimerViewFragment kidsEnrollmentPrimerViewFragment, EnrollmentTrainingDialogHelper enrollmentTrainingDialogHelper) {
        kidsEnrollmentPrimerViewFragment.enrollmentDialogHelper = enrollmentTrainingDialogHelper;
    }

    public static void injectEnrollmentMetricsRecorder(KidsEnrollmentPrimerViewFragment kidsEnrollmentPrimerViewFragment, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        kidsEnrollmentPrimerViewFragment.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }

    public static void injectEnrollmentThemeUtil(KidsEnrollmentPrimerViewFragment kidsEnrollmentPrimerViewFragment, EnrollmentThemeUtil enrollmentThemeUtil) {
        kidsEnrollmentPrimerViewFragment.enrollmentThemeUtil = enrollmentThemeUtil;
    }

    public static void injectEnrollmentViewModel(KidsEnrollmentPrimerViewFragment kidsEnrollmentPrimerViewFragment, EnrollmentIntroductionViewModel enrollmentIntroductionViewModel) {
        kidsEnrollmentPrimerViewFragment.enrollmentViewModel = enrollmentIntroductionViewModel;
    }

    public static void injectIdentityService(KidsEnrollmentPrimerViewFragment kidsEnrollmentPrimerViewFragment, IdentityService identityService) {
        kidsEnrollmentPrimerViewFragment.identityService = identityService;
    }

    public static void injectPopupFragment(KidsEnrollmentPrimerViewFragment kidsEnrollmentPrimerViewFragment, KidsEnrollmentPopup kidsEnrollmentPopup) {
        kidsEnrollmentPrimerViewFragment.popupFragment = kidsEnrollmentPopup;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(KidsEnrollmentPrimerViewFragment kidsEnrollmentPrimerViewFragment) {
        AbstractEnrollmentViewFragment_MembersInjector.injectRoutingService(kidsEnrollmentPrimerViewFragment, this.routingServiceProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectEventBus(kidsEnrollmentPrimerViewFragment, this.eventBusProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(kidsEnrollmentPrimerViewFragment, this.enrollmentMetricsRecorderProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectDialogHelper(kidsEnrollmentPrimerViewFragment, this.dialogHelperAndEnrollmentDialogHelperProvider.mo10268get());
        injectPopupFragment(kidsEnrollmentPrimerViewFragment, this.popupFragmentProvider.mo10268get());
        injectAnimationHelper(kidsEnrollmentPrimerViewFragment, this.animationHelperProvider.mo10268get());
        injectDialogHelper(kidsEnrollmentPrimerViewFragment, this.dialogHelperProvider.mo10268get());
        injectEnrollmentThemeUtil(kidsEnrollmentPrimerViewFragment, this.enrollmentThemeUtilProvider.mo10268get());
        injectEnrollmentDialogHelper(kidsEnrollmentPrimerViewFragment, this.dialogHelperAndEnrollmentDialogHelperProvider.mo10268get());
        injectEnrollmentViewModel(kidsEnrollmentPrimerViewFragment, this.enrollmentViewModelProvider.mo10268get());
        injectIdentityService(kidsEnrollmentPrimerViewFragment, this.identityServiceProvider.mo10268get());
        injectContext(kidsEnrollmentPrimerViewFragment, this.contextProvider.mo10268get());
        injectEnrollmentMetricsRecorder(kidsEnrollmentPrimerViewFragment, this.enrollmentMetricsRecorderProvider.mo10268get());
    }
}
