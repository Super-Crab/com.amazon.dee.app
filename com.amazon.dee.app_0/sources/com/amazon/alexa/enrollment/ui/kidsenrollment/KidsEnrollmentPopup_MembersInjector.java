package com.amazon.alexa.enrollment.ui.kidsenrollment;

import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.ui.AbstractBottomSheetFragment_MembersInjector;
import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import com.amazon.alexa.enrollment.utils.PermissionsHelper;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class KidsEnrollmentPopup_MembersInjector implements MembersInjector<KidsEnrollmentPopup> {
    private final Provider<EnrollmentMetricsRecorder> enrollmentMetricsRecorderProvider;
    private final Provider<EnrollmentThemeUtil> enrollmentThemeUtilProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<PermissionsHelper> permissionsHelperProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<KidsEnrollmentViewModel> viewModelProvider;

    public KidsEnrollmentPopup_MembersInjector(Provider<EventBus> provider, Provider<RoutingService> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<KidsEnrollmentViewModel> provider4, Provider<PermissionsHelper> provider5, Provider<EnrollmentThemeUtil> provider6) {
        this.eventBusProvider = provider;
        this.routingServiceProvider = provider2;
        this.enrollmentMetricsRecorderProvider = provider3;
        this.viewModelProvider = provider4;
        this.permissionsHelperProvider = provider5;
        this.enrollmentThemeUtilProvider = provider6;
    }

    public static MembersInjector<KidsEnrollmentPopup> create(Provider<EventBus> provider, Provider<RoutingService> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<KidsEnrollmentViewModel> provider4, Provider<PermissionsHelper> provider5, Provider<EnrollmentThemeUtil> provider6) {
        return new KidsEnrollmentPopup_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectEnrollmentMetricsRecorder(KidsEnrollmentPopup kidsEnrollmentPopup, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        kidsEnrollmentPopup.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }

    public static void injectEnrollmentThemeUtil(KidsEnrollmentPopup kidsEnrollmentPopup, EnrollmentThemeUtil enrollmentThemeUtil) {
        kidsEnrollmentPopup.enrollmentThemeUtil = enrollmentThemeUtil;
    }

    public static void injectPermissionsHelper(KidsEnrollmentPopup kidsEnrollmentPopup, PermissionsHelper permissionsHelper) {
        kidsEnrollmentPopup.permissionsHelper = permissionsHelper;
    }

    public static void injectViewModel(KidsEnrollmentPopup kidsEnrollmentPopup, KidsEnrollmentViewModel kidsEnrollmentViewModel) {
        kidsEnrollmentPopup.viewModel = kidsEnrollmentViewModel;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(KidsEnrollmentPopup kidsEnrollmentPopup) {
        AbstractBottomSheetFragment_MembersInjector.injectEventBus(kidsEnrollmentPopup, this.eventBusProvider.mo10268get());
        AbstractBottomSheetFragment_MembersInjector.injectRoutingService(kidsEnrollmentPopup, this.routingServiceProvider.mo10268get());
        AbstractBottomSheetFragment_MembersInjector.injectEnrollmentMetricsRecorder(kidsEnrollmentPopup, this.enrollmentMetricsRecorderProvider.mo10268get());
        injectViewModel(kidsEnrollmentPopup, this.viewModelProvider.mo10268get());
        injectPermissionsHelper(kidsEnrollmentPopup, this.permissionsHelperProvider.mo10268get());
        injectEnrollmentThemeUtil(kidsEnrollmentPopup, this.enrollmentThemeUtilProvider.mo10268get());
        injectEnrollmentMetricsRecorder(kidsEnrollmentPopup, this.enrollmentMetricsRecorderProvider.mo10268get());
    }
}
