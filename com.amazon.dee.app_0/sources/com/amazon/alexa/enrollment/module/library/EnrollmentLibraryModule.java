package com.amazon.alexa.enrollment.module.library;

import android.content.Context;
import com.amazon.alexa.api.compat.AlexaState;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.enrollment.alexaservices.AlexaStateInteractor;
import com.amazon.alexa.enrollment.alexaservices.AlexaStateTracker;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.model.EnrollmentGateway;
import com.amazon.alexa.enrollment.model.EnrollmentGatewayImpl;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentDialogHelper;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPopup;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingDialogHelper;
import com.amazon.alexa.enrollment.utils.AlexaAPIEndpointUtil;
import com.amazon.alexa.enrollment.utils.AnimationHelper;
import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import com.amazon.alexa.enrollment.utils.EnrollmentUtil;
import com.amazon.alexa.enrollment.utils.PermissionsHelper;
import com.amazon.alexa.enrollment.voiceSDK.audio.EnrollmentAudioSinkWrapper;
import com.amazon.alexa.enrollment.voiceSDK.client.AlexaVoiceSDKClient;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.EnrollmentEventBus;
import com.amazon.alexa.enrollment.voiceSDK.userSpeechProviders.EnrollmentUserSpeechProvider;
import com.amazon.alexa.enrollment.voiceSDK.util.EnrollmentAudioRecorderWrapper;
import com.amazon.alexa.enrollment.voiceSDK.util.EnrollmentHandlerUtil;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.dee.app.http.UrlResolver;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import java.util.Locale;
import java.util.concurrent.Executors;
import okhttp3.OkHttpClient;
import org.greenrobot.eventbus.EventBus;
@Module
/* loaded from: classes7.dex */
public class EnrollmentLibraryModule {
    private final Lazy<Context> context;
    private final Lazy<CoralService> coralService;
    private final Lazy<DeviceInformation> deviceInformation;
    private final EnrollmentEventBus enrollmentEventBus = new EnrollmentEventBus(new EventBus());
    private final Lazy<EnvironmentService> environmentService;
    private final Lazy<com.amazon.alexa.eventbus.api.EventBus> eventBus;
    private final Lazy<IdentityService> identityService;
    private final Lazy<Mobilytics> mobilytics;
    private final Lazy<PersonIdProvider> personIdProvider;
    private final Lazy<RoutingService> routingService;
    private final Lazy<UrlResolver> urlResolver;

    public EnrollmentLibraryModule(Lazy<Context> lazy, Lazy<CoralService> lazy2, Lazy<PersonIdProvider> lazy3, Lazy<Mobilytics> lazy4, Lazy<DeviceInformation> lazy5, Lazy<IdentityService> lazy6, Lazy<com.amazon.alexa.eventbus.api.EventBus> lazy7, Lazy<RoutingService> lazy8, Lazy<UrlResolver> lazy9, Lazy<EnvironmentService> lazy10) {
        this.context = lazy;
        this.coralService = lazy2;
        this.personIdProvider = lazy3;
        this.mobilytics = lazy4;
        this.deviceInformation = lazy5;
        this.identityService = lazy6;
        this.eventBus = lazy7;
        this.routingService = lazy8;
        this.urlResolver = lazy9;
        this.environmentService = lazy10;
    }

    @Provides
    public AlexaAPIEndpointUtil getAlexaAPIEndpointUtil() {
        return new AlexaAPIEndpointUtil();
    }

    @Provides
    public AlexaStateInteractor getAlexaStateInteractor(AlexaStateTracker alexaStateTracker, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        return new AlexaStateInteractor(getContext(), alexaStateTracker, enrollmentMetricsRecorder, EnrollmentUtil.getAlexaServicesConnection(getContext()));
    }

    @Provides
    public AlexaStateTracker getAlexaStateTracker(EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        return new AlexaStateTracker(this.eventBus.mo358get(), enrollmentMetricsRecorder, AlexaState.UNKNOWN);
    }

    @Provides
    public AlexaVoiceSDKClient getAlexaVoiceSDKClient() {
        return new AlexaVoiceSDKClient(getEnrollmentMetricsRecorder(), this.enrollmentEventBus, getEnrollmentUserSpeechProvider());
    }

    @Provides
    public AnimationHelper getAnimationHelper() {
        return new AnimationHelper();
    }

    @Provides
    public EnrollmentAudioRecorderWrapper getAudioRecorderWrapper() {
        return new EnrollmentAudioRecorderWrapper();
    }

    @Provides
    public Context getContext() {
        return this.context.mo358get();
    }

    @Provides
    public CoralService getCoralService() {
        return this.coralService.mo358get();
    }

    @Provides
    public DeviceInformation getDeviceInformation() {
        return (DeviceInformation) GeneratedOutlineSupport1.outline20(DeviceInformation.class);
    }

    @Provides
    public EnrollmentTrainingDialogHelper getDialogHelper() {
        return new EnrollmentTrainingDialogHelper();
    }

    @Provides
    public EnrollmentAudioSinkWrapper getEnrollmentAudioSinkWrapper() {
        return new EnrollmentAudioSinkWrapper(this.enrollmentEventBus);
    }

    @Provides
    public EnrollmentEventBus getEnrollmentEventBus() {
        return this.enrollmentEventBus;
    }

    @Provides
    public EnrollmentHandlerUtil getEnrollmentHandlerUtil() {
        return new EnrollmentHandlerUtil();
    }

    @Provides
    public EnrollmentMetricsRecorder getEnrollmentMetricsRecorder() {
        return new EnrollmentMetricsRecorder(this.mobilytics);
    }

    @Provides
    public EnrollmentThemeUtil getEnrollmentThemeUtil() {
        return new EnrollmentThemeUtil();
    }

    @Provides
    public EnrollmentUserSpeechProvider getEnrollmentUserSpeechProvider() {
        return new EnrollmentUserSpeechProvider(this.enrollmentEventBus, Executors.newSingleThreadExecutor(), getEnrollmentAudioSinkWrapper(), getEnrollmentHandlerUtil(), getAudioRecorderWrapper(), getEnrollmentMetricsRecorder());
    }

    @Provides
    public OkHttpClient getHttpClient() {
        return new OkHttpClient();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public KidsEnrollmentDialogHelper getKidsEnrollmentDialogHelper() {
        return new KidsEnrollmentDialogHelper();
    }

    @Provides
    public KidsEnrollmentPopup getKidsEnrollmentPopup() {
        return new KidsEnrollmentPopup();
    }

    @Provides
    public Locale getLocale() {
        return Locale.getDefault();
    }

    @Provides
    public Mobilytics getMobilytics() {
        return this.mobilytics.mo358get();
    }

    @Provides
    public PermissionsHelper getPermissionsHelper() {
        return new PermissionsHelper();
    }

    @Provides
    public PersonIdProvider getPersonIdProvider() {
        return this.personIdProvider.mo358get();
    }

    @Provides
    public EnrollmentGateway providesEnrollmentService() {
        return new EnrollmentGatewayImpl();
    }

    @Provides
    public EnvironmentService providesEnvironmentService() {
        return this.environmentService.mo358get();
    }

    @Provides
    public com.amazon.alexa.eventbus.api.EventBus providesEventBus() {
        return this.eventBus.mo358get();
    }

    @Provides
    public IdentityService providesIdentityService() {
        return this.identityService.mo358get();
    }

    @Provides
    public RoutingService providesRoutingService() {
        return this.routingService.mo358get();
    }

    @Provides
    public UrlResolver providesUrlResolver() {
        return this.urlResolver.mo358get();
    }
}
