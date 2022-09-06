package com.amazon.alexa.enrollment.api;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.utils.AlexaAPIEndpointUtil;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import java.util.Locale;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes7.dex */
public final class EnrollmentAPI_Factory implements Factory<EnrollmentAPI> {
    private final Provider<AlexaAPIEndpointUtil> alexaAPIEndpointUtilProvider;
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<OkHttpClient> httpClientProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<Locale> localeProvider;
    private final Provider<EnrollmentMetricsRecorder> metricsRecorderProvider;
    private final Provider<PersonIdProvider> personIdProvider;

    public EnrollmentAPI_Factory(Provider<CoralService> provider, Provider<DeviceInformation> provider2, Provider<PersonIdProvider> provider3, Provider<IdentityService> provider4, Provider<Locale> provider5, Provider<EnrollmentMetricsRecorder> provider6, Provider<EnvironmentService> provider7, Provider<AlexaAPIEndpointUtil> provider8, Provider<OkHttpClient> provider9) {
        this.coralServiceProvider = provider;
        this.deviceInformationProvider = provider2;
        this.personIdProvider = provider3;
        this.identityServiceProvider = provider4;
        this.localeProvider = provider5;
        this.metricsRecorderProvider = provider6;
        this.environmentServiceProvider = provider7;
        this.alexaAPIEndpointUtilProvider = provider8;
        this.httpClientProvider = provider9;
    }

    public static EnrollmentAPI_Factory create(Provider<CoralService> provider, Provider<DeviceInformation> provider2, Provider<PersonIdProvider> provider3, Provider<IdentityService> provider4, Provider<Locale> provider5, Provider<EnrollmentMetricsRecorder> provider6, Provider<EnvironmentService> provider7, Provider<AlexaAPIEndpointUtil> provider8, Provider<OkHttpClient> provider9) {
        return new EnrollmentAPI_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static EnrollmentAPI newEnrollmentAPI(CoralService coralService, DeviceInformation deviceInformation, PersonIdProvider personIdProvider, IdentityService identityService, Locale locale, EnrollmentMetricsRecorder enrollmentMetricsRecorder, EnvironmentService environmentService, AlexaAPIEndpointUtil alexaAPIEndpointUtil, OkHttpClient okHttpClient) {
        return new EnrollmentAPI(coralService, deviceInformation, personIdProvider, identityService, locale, enrollmentMetricsRecorder, environmentService, alexaAPIEndpointUtil, okHttpClient);
    }

    public static EnrollmentAPI provideInstance(Provider<CoralService> provider, Provider<DeviceInformation> provider2, Provider<PersonIdProvider> provider3, Provider<IdentityService> provider4, Provider<Locale> provider5, Provider<EnrollmentMetricsRecorder> provider6, Provider<EnvironmentService> provider7, Provider<AlexaAPIEndpointUtil> provider8, Provider<OkHttpClient> provider9) {
        return new EnrollmentAPI(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentAPI mo10268get() {
        return provideInstance(this.coralServiceProvider, this.deviceInformationProvider, this.personIdProvider, this.identityServiceProvider, this.localeProvider, this.metricsRecorderProvider, this.environmentServiceProvider, this.alexaAPIEndpointUtilProvider, this.httpClientProvider);
    }
}
