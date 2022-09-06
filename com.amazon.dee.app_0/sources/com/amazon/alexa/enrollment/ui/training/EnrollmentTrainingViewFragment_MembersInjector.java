package com.amazon.alexa.enrollment.ui.training;

import com.amazon.alexa.enrollment.alexaservices.AlexaStateInteractor;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment_MembersInjector;
import com.amazon.alexa.enrollment.utils.AnimationHelper;
import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import com.amazon.alexa.enrollment.voiceSDK.client.AlexaVoiceSDKClient;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.EnrollmentEventBus;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentTrainingViewFragment_MembersInjector implements MembersInjector<EnrollmentTrainingViewFragment> {
    private final Provider<AlexaStateInteractor> alexaStateInteractorProvider;
    private final Provider<AlexaVoiceSDKClient> alexaVoiceSDKClientProvider;
    private final Provider<AnimationHelper> animationHelperProvider;
    private final Provider<EnrollmentTrainingDialogHelper> dialogHelperProvider;
    private final Provider<EnrollmentEventBus> enrollmentEventBusProvider;
    private final Provider<EnrollmentMetricsRecorder> enrollmentMetricsRecorderProvider;
    private final Provider<EnrollmentThemeUtil> enrollmentThemeUtilProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<EnrollmentTrainingViewModel> viewModelProvider;

    public EnrollmentTrainingViewFragment_MembersInjector(Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<EnrollmentTrainingDialogHelper> provider4, Provider<EnrollmentTrainingViewModel> provider5, Provider<AnimationHelper> provider6, Provider<EnrollmentThemeUtil> provider7, Provider<AlexaStateInteractor> provider8, Provider<EnrollmentEventBus> provider9, Provider<AlexaVoiceSDKClient> provider10) {
        this.routingServiceProvider = provider;
        this.eventBusProvider = provider2;
        this.enrollmentMetricsRecorderProvider = provider3;
        this.dialogHelperProvider = provider4;
        this.viewModelProvider = provider5;
        this.animationHelperProvider = provider6;
        this.enrollmentThemeUtilProvider = provider7;
        this.alexaStateInteractorProvider = provider8;
        this.enrollmentEventBusProvider = provider9;
        this.alexaVoiceSDKClientProvider = provider10;
    }

    public static MembersInjector<EnrollmentTrainingViewFragment> create(Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<EnrollmentTrainingDialogHelper> provider4, Provider<EnrollmentTrainingViewModel> provider5, Provider<AnimationHelper> provider6, Provider<EnrollmentThemeUtil> provider7, Provider<AlexaStateInteractor> provider8, Provider<EnrollmentEventBus> provider9, Provider<AlexaVoiceSDKClient> provider10) {
        return new EnrollmentTrainingViewFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static void injectAlexaStateInteractor(EnrollmentTrainingViewFragment enrollmentTrainingViewFragment, AlexaStateInteractor alexaStateInteractor) {
        enrollmentTrainingViewFragment.alexaStateInteractor = alexaStateInteractor;
    }

    public static void injectAlexaVoiceSDKClient(EnrollmentTrainingViewFragment enrollmentTrainingViewFragment, AlexaVoiceSDKClient alexaVoiceSDKClient) {
        enrollmentTrainingViewFragment.alexaVoiceSDKClient = alexaVoiceSDKClient;
    }

    public static void injectAnimationHelper(EnrollmentTrainingViewFragment enrollmentTrainingViewFragment, AnimationHelper animationHelper) {
        enrollmentTrainingViewFragment.animationHelper = animationHelper;
    }

    public static void injectDialogHelper(EnrollmentTrainingViewFragment enrollmentTrainingViewFragment, EnrollmentTrainingDialogHelper enrollmentTrainingDialogHelper) {
        enrollmentTrainingViewFragment.dialogHelper = enrollmentTrainingDialogHelper;
    }

    public static void injectEnrollmentEventBus(EnrollmentTrainingViewFragment enrollmentTrainingViewFragment, EnrollmentEventBus enrollmentEventBus) {
        enrollmentTrainingViewFragment.enrollmentEventBus = enrollmentEventBus;
    }

    public static void injectEnrollmentMetricsRecorder(EnrollmentTrainingViewFragment enrollmentTrainingViewFragment, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        enrollmentTrainingViewFragment.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }

    public static void injectEnrollmentThemeUtil(EnrollmentTrainingViewFragment enrollmentTrainingViewFragment, EnrollmentThemeUtil enrollmentThemeUtil) {
        enrollmentTrainingViewFragment.enrollmentThemeUtil = enrollmentThemeUtil;
    }

    public static void injectEventBus(EnrollmentTrainingViewFragment enrollmentTrainingViewFragment, EventBus eventBus) {
        enrollmentTrainingViewFragment.eventBus = eventBus;
    }

    public static void injectViewModel(EnrollmentTrainingViewFragment enrollmentTrainingViewFragment, EnrollmentTrainingViewModel enrollmentTrainingViewModel) {
        enrollmentTrainingViewFragment.viewModel = enrollmentTrainingViewModel;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EnrollmentTrainingViewFragment enrollmentTrainingViewFragment) {
        AbstractEnrollmentViewFragment_MembersInjector.injectRoutingService(enrollmentTrainingViewFragment, this.routingServiceProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectEventBus(enrollmentTrainingViewFragment, this.eventBusProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentTrainingViewFragment, this.enrollmentMetricsRecorderProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectDialogHelper(enrollmentTrainingViewFragment, this.dialogHelperProvider.mo10268get());
        injectViewModel(enrollmentTrainingViewFragment, this.viewModelProvider.mo10268get());
        injectDialogHelper(enrollmentTrainingViewFragment, this.dialogHelperProvider.mo10268get());
        injectAnimationHelper(enrollmentTrainingViewFragment, this.animationHelperProvider.mo10268get());
        injectEnrollmentThemeUtil(enrollmentTrainingViewFragment, this.enrollmentThemeUtilProvider.mo10268get());
        injectAlexaStateInteractor(enrollmentTrainingViewFragment, this.alexaStateInteractorProvider.mo10268get());
        injectEventBus(enrollmentTrainingViewFragment, this.eventBusProvider.mo10268get());
        injectEnrollmentEventBus(enrollmentTrainingViewFragment, this.enrollmentEventBusProvider.mo10268get());
        injectAlexaVoiceSDKClient(enrollmentTrainingViewFragment, this.alexaVoiceSDKClientProvider.mo10268get());
        injectEnrollmentMetricsRecorder(enrollmentTrainingViewFragment, this.enrollmentMetricsRecorderProvider.mo10268get());
    }
}
