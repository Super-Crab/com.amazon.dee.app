package com.amazon.alexa.handsfree.metrics.mobilytics;

import android.content.Context;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.utils.ApplicationInformationProvider;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class MobilyticsMetadataProvider_Factory implements Factory<MobilyticsMetadataProvider> {
    private final Provider<ApplicationInformationProvider> applicationInformationProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeviceTypeInformationProvider> deviceTypeInformationProvider;
    private final Provider<EnrollmentTypeResolver> enrollmentTypeResolverLazyProvider;

    public MobilyticsMetadataProvider_Factory(Provider<Context> provider, Provider<ApplicationInformationProvider> provider2, Provider<DeviceTypeInformationProvider> provider3, Provider<EnrollmentTypeResolver> provider4) {
        this.contextProvider = provider;
        this.applicationInformationProvider = provider2;
        this.deviceTypeInformationProvider = provider3;
        this.enrollmentTypeResolverLazyProvider = provider4;
    }

    public static MobilyticsMetadataProvider_Factory create(Provider<Context> provider, Provider<ApplicationInformationProvider> provider2, Provider<DeviceTypeInformationProvider> provider3, Provider<EnrollmentTypeResolver> provider4) {
        return new MobilyticsMetadataProvider_Factory(provider, provider2, provider3, provider4);
    }

    public static MobilyticsMetadataProvider newMobilyticsMetadataProvider(Context context, ApplicationInformationProvider applicationInformationProvider, DeviceTypeInformationProvider deviceTypeInformationProvider, Lazy<EnrollmentTypeResolver> lazy) {
        return new MobilyticsMetadataProvider(context, applicationInformationProvider, deviceTypeInformationProvider, lazy);
    }

    public static MobilyticsMetadataProvider provideInstance(Provider<Context> provider, Provider<ApplicationInformationProvider> provider2, Provider<DeviceTypeInformationProvider> provider3, Provider<EnrollmentTypeResolver> provider4) {
        return new MobilyticsMetadataProvider(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), DoubleCheck.lazy(provider4));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MobilyticsMetadataProvider mo10268get() {
        return provideInstance(this.contextProvider, this.applicationInformationProvider, this.deviceTypeInformationProvider, this.enrollmentTypeResolverLazyProvider);
    }
}
