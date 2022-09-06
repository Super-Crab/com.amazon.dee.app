package com.amazon.alexa.enrollment.ui.introduction;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment_MembersInjector;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingDialogHelper;
import com.amazon.alexa.enrollment.utils.AnimationHelper;
import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import com.amazon.alexa.enrollment.utils.PermissionsHelper;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentIntroductionViewFragment_MembersInjector implements MembersInjector<EnrollmentIntroductionViewFragment> {
    private final Provider<AnimationHelper> animationHelperProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<EnrollmentTrainingDialogHelper> dialogHelperProvider;
    private final Provider<EnrollmentMetricsRecorder> enrollmentMetricsRecorderProvider;
    private final Provider<EnrollmentThemeUtil> enrollmentThemeUtilProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<PermissionsHelper> permissionsHelperProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<EnrollmentIntroductionViewModel> viewModelProvider;

    public EnrollmentIntroductionViewFragment_MembersInjector(Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<EnrollmentTrainingDialogHelper> provider4, Provider<EnrollmentIntroductionViewModel> provider5, Provider<PermissionsHelper> provider6, Provider<IdentityService> provider7, Provider<AnimationHelper> provider8, Provider<DeviceInformation> provider9, Provider<EnrollmentThemeUtil> provider10) {
        this.routingServiceProvider = provider;
        this.eventBusProvider = provider2;
        this.enrollmentMetricsRecorderProvider = provider3;
        this.dialogHelperProvider = provider4;
        this.viewModelProvider = provider5;
        this.permissionsHelperProvider = provider6;
        this.identityServiceProvider = provider7;
        this.animationHelperProvider = provider8;
        this.deviceInformationProvider = provider9;
        this.enrollmentThemeUtilProvider = provider10;
    }

    public static MembersInjector<EnrollmentIntroductionViewFragment> create(Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<EnrollmentTrainingDialogHelper> provider4, Provider<EnrollmentIntroductionViewModel> provider5, Provider<PermissionsHelper> provider6, Provider<IdentityService> provider7, Provider<AnimationHelper> provider8, Provider<DeviceInformation> provider9, Provider<EnrollmentThemeUtil> provider10) {
        return new EnrollmentIntroductionViewFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static void injectAnimationHelper(EnrollmentIntroductionViewFragment enrollmentIntroductionViewFragment, AnimationHelper animationHelper) {
        enrollmentIntroductionViewFragment.animationHelper = animationHelper;
    }

    public static void injectDeviceInformation(EnrollmentIntroductionViewFragment enrollmentIntroductionViewFragment, DeviceInformation deviceInformation) {
        enrollmentIntroductionViewFragment.deviceInformation = deviceInformation;
    }

    public static void injectDialogHelper(EnrollmentIntroductionViewFragment enrollmentIntroductionViewFragment, EnrollmentTrainingDialogHelper enrollmentTrainingDialogHelper) {
        enrollmentIntroductionViewFragment.dialogHelper = enrollmentTrainingDialogHelper;
    }

    public static void injectEnrollmentMetricsRecorder(EnrollmentIntroductionViewFragment enrollmentIntroductionViewFragment, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        enrollmentIntroductionViewFragment.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }

    public static void injectEnrollmentThemeUtil(EnrollmentIntroductionViewFragment enrollmentIntroductionViewFragment, EnrollmentThemeUtil enrollmentThemeUtil) {
        enrollmentIntroductionViewFragment.enrollmentThemeUtil = enrollmentThemeUtil;
    }

    public static void injectIdentityService(EnrollmentIntroductionViewFragment enrollmentIntroductionViewFragment, IdentityService identityService) {
        enrollmentIntroductionViewFragment.identityService = identityService;
    }

    public static void injectPermissionsHelper(EnrollmentIntroductionViewFragment enrollmentIntroductionViewFragment, PermissionsHelper permissionsHelper) {
        enrollmentIntroductionViewFragment.permissionsHelper = permissionsHelper;
    }

    public static void injectViewModel(EnrollmentIntroductionViewFragment enrollmentIntroductionViewFragment, EnrollmentIntroductionViewModel enrollmentIntroductionViewModel) {
        enrollmentIntroductionViewFragment.viewModel = enrollmentIntroductionViewModel;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EnrollmentIntroductionViewFragment enrollmentIntroductionViewFragment) {
        AbstractEnrollmentViewFragment_MembersInjector.injectRoutingService(enrollmentIntroductionViewFragment, this.routingServiceProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectEventBus(enrollmentIntroductionViewFragment, this.eventBusProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentIntroductionViewFragment, this.enrollmentMetricsRecorderProvider.mo10268get());
        AbstractEnrollmentViewFragment_MembersInjector.injectDialogHelper(enrollmentIntroductionViewFragment, this.dialogHelperProvider.mo10268get());
        injectViewModel(enrollmentIntroductionViewFragment, this.viewModelProvider.mo10268get());
        injectPermissionsHelper(enrollmentIntroductionViewFragment, this.permissionsHelperProvider.mo10268get());
        injectIdentityService(enrollmentIntroductionViewFragment, this.identityServiceProvider.mo10268get());
        injectAnimationHelper(enrollmentIntroductionViewFragment, this.animationHelperProvider.mo10268get());
        injectDeviceInformation(enrollmentIntroductionViewFragment, this.deviceInformationProvider.mo10268get());
        injectDialogHelper(enrollmentIntroductionViewFragment, this.dialogHelperProvider.mo10268get());
        injectEnrollmentThemeUtil(enrollmentIntroductionViewFragment, this.enrollmentThemeUtilProvider.mo10268get());
        injectEnrollmentMetricsRecorder(enrollmentIntroductionViewFragment, this.enrollmentMetricsRecorderProvider.mo10268get());
    }
}
