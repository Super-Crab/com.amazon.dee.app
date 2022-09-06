package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.device.api.DeviceInformation;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetDeviceInformationFactory implements Factory<DeviceInformation> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetDeviceInformationFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetDeviceInformationFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetDeviceInformationFactory(enrollmentLibraryModule);
    }

    public static DeviceInformation provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetDeviceInformation(enrollmentLibraryModule);
    }

    public static DeviceInformation proxyGetDeviceInformation(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (DeviceInformation) Preconditions.checkNotNull(enrollmentLibraryModule.getDeviceInformation(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceInformation mo10268get() {
        return provideInstance(this.module);
    }
}
