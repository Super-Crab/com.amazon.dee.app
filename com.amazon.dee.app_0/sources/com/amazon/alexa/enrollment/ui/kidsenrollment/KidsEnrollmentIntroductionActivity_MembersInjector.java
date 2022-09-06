package com.amazon.alexa.enrollment.ui.kidsenrollment;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity_MembersInjector;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class KidsEnrollmentIntroductionActivity_MembersInjector implements MembersInjector<KidsEnrollmentIntroductionActivity> {
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<EnrollmentMetricsRecorder> enrollmentMetricsRecorderProvider;

    public KidsEnrollmentIntroductionActivity_MembersInjector(Provider<DeviceInformation> provider, Provider<EnrollmentMetricsRecorder> provider2) {
        this.deviceInformationProvider = provider;
        this.enrollmentMetricsRecorderProvider = provider2;
    }

    public static MembersInjector<KidsEnrollmentIntroductionActivity> create(Provider<DeviceInformation> provider, Provider<EnrollmentMetricsRecorder> provider2) {
        return new KidsEnrollmentIntroductionActivity_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(KidsEnrollmentIntroductionActivity kidsEnrollmentIntroductionActivity) {
        AbstractEnrollmentActivity_MembersInjector.injectDeviceInformation(kidsEnrollmentIntroductionActivity, this.deviceInformationProvider.mo10268get());
        AbstractEnrollmentActivity_MembersInjector.injectEnrollmentMetricsRecorder(kidsEnrollmentIntroductionActivity, this.enrollmentMetricsRecorderProvider.mo10268get());
    }
}
