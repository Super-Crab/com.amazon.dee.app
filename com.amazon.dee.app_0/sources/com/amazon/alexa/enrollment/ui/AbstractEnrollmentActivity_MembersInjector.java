package com.amazon.alexa.enrollment.ui;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class AbstractEnrollmentActivity_MembersInjector implements MembersInjector<AbstractEnrollmentActivity> {
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<EnrollmentMetricsRecorder> enrollmentMetricsRecorderProvider;

    public AbstractEnrollmentActivity_MembersInjector(Provider<DeviceInformation> provider, Provider<EnrollmentMetricsRecorder> provider2) {
        this.deviceInformationProvider = provider;
        this.enrollmentMetricsRecorderProvider = provider2;
    }

    public static MembersInjector<AbstractEnrollmentActivity> create(Provider<DeviceInformation> provider, Provider<EnrollmentMetricsRecorder> provider2) {
        return new AbstractEnrollmentActivity_MembersInjector(provider, provider2);
    }

    public static void injectDeviceInformation(AbstractEnrollmentActivity abstractEnrollmentActivity, DeviceInformation deviceInformation) {
        abstractEnrollmentActivity.deviceInformation = deviceInformation;
    }

    public static void injectEnrollmentMetricsRecorder(AbstractEnrollmentActivity abstractEnrollmentActivity, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        abstractEnrollmentActivity.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AbstractEnrollmentActivity abstractEnrollmentActivity) {
        injectDeviceInformation(abstractEnrollmentActivity, this.deviceInformationProvider.mo10268get());
        injectEnrollmentMetricsRecorder(abstractEnrollmentActivity, this.enrollmentMetricsRecorderProvider.mo10268get());
    }
}
