package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.alexaservices.AlexaStateInteractor;
import com.amazon.alexa.enrollment.alexaservices.AlexaStateTracker;
import com.amazon.alexa.enrollment.api.EnrollmentAPI;
import com.amazon.alexa.enrollment.model.EnrollmentGateway;
import com.amazon.alexa.enrollment.ui.AbstractBottomSheetFragment_MembersInjector;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentActivity_MembersInjector;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment_MembersInjector;
import com.amazon.alexa.enrollment.ui.complete.EnrollmentCompleteViewFragment;
import com.amazon.alexa.enrollment.ui.complete.EnrollmentCompleteViewFragment_MembersInjector;
import com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionViewFragment;
import com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionViewFragment_MembersInjector;
import com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionViewModel;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentIntroductionActivity;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPopup;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPopup_MembersInjector;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPrimerViewFragment;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPrimerViewFragment_MembersInjector;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentViewModel;
import com.amazon.alexa.enrollment.ui.privacySettings.EnrollmentPrivacyTermsViewFragment;
import com.amazon.alexa.enrollment.ui.privacySettings.EnrollmentPrivacyTermsViewFragment_MembersInjector;
import com.amazon.alexa.enrollment.ui.terms.EnrollmentTermsViewFragment;
import com.amazon.alexa.enrollment.ui.terms.EnrollmentTermsViewFragment_MembersInjector;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingViewFragment;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingViewFragment_MembersInjector;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingViewModel;
import com.amazon.alexa.enrollment.ui.views.EnrollmentTrainingProgressBar;
import com.amazon.alexa.enrollment.ui.views.EnrollmentTrainingProgressBar_MembersInjector;
import com.amazon.alexa.enrollment.voiceSDK.client.AlexaVoiceSDKClient;
import com.amazon.alexa.enrollment.voiceSDK.userSpeechProviders.EnrollmentUserSpeechProvider;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class DaggerEnrollmentComponent implements EnrollmentComponent {
    private EnrollmentLibraryModule enrollmentLibraryModule;
    private EnrollmentViewModelModule enrollmentViewModelModule;

    /* loaded from: classes7.dex */
    public static final class Builder {
        private EnrollmentLibraryModule enrollmentLibraryModule;
        private EnrollmentViewModelModule enrollmentViewModelModule;

        public EnrollmentComponent build() {
            Preconditions.checkBuilderRequirement(this.enrollmentLibraryModule, EnrollmentLibraryModule.class);
            if (this.enrollmentViewModelModule == null) {
                this.enrollmentViewModelModule = new EnrollmentViewModelModule();
            }
            return new DaggerEnrollmentComponent(this);
        }

        public Builder enrollmentLibraryModule(EnrollmentLibraryModule enrollmentLibraryModule) {
            this.enrollmentLibraryModule = (EnrollmentLibraryModule) Preconditions.checkNotNull(enrollmentLibraryModule);
            return this;
        }

        public Builder enrollmentViewModelModule(EnrollmentViewModelModule enrollmentViewModelModule) {
            this.enrollmentViewModelModule = (EnrollmentViewModelModule) Preconditions.checkNotNull(enrollmentViewModelModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private AlexaStateInteractor getAlexaStateInteractor() {
        return EnrollmentLibraryModule_GetAlexaStateInteractorFactory.proxyGetAlexaStateInteractor(this.enrollmentLibraryModule, getAlexaStateTracker(), EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
    }

    private AlexaStateTracker getAlexaStateTracker() {
        EnrollmentLibraryModule enrollmentLibraryModule = this.enrollmentLibraryModule;
        return EnrollmentLibraryModule_GetAlexaStateTrackerFactory.proxyGetAlexaStateTracker(enrollmentLibraryModule, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(enrollmentLibraryModule));
    }

    private EnrollmentAPI getEnrollmentAPI() {
        return new EnrollmentAPI(EnrollmentLibraryModule_GetCoralServiceFactory.proxyGetCoralService(this.enrollmentLibraryModule), EnrollmentLibraryModule_GetDeviceInformationFactory.proxyGetDeviceInformation(this.enrollmentLibraryModule), EnrollmentLibraryModule_GetPersonIdProviderFactory.proxyGetPersonIdProvider(this.enrollmentLibraryModule), EnrollmentLibraryModule_ProvidesIdentityServiceFactory.proxyProvidesIdentityService(this.enrollmentLibraryModule), EnrollmentLibraryModule_GetLocaleFactory.proxyGetLocale(this.enrollmentLibraryModule), EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule), EnrollmentLibraryModule_ProvidesEnvironmentServiceFactory.proxyProvidesEnvironmentService(this.enrollmentLibraryModule), EnrollmentLibraryModule_GetAlexaAPIEndpointUtilFactory.proxyGetAlexaAPIEndpointUtil(this.enrollmentLibraryModule), EnrollmentLibraryModule_GetHttpClientFactory.proxyGetHttpClient(this.enrollmentLibraryModule));
    }

    private EnrollmentIntroductionViewModel getEnrollmentIntroductionViewModel() {
        return EnrollmentViewModelModule_ProvideEnrollmentIntroViewModelFactory.proxyProvideEnrollmentIntroViewModel(this.enrollmentViewModelModule, EnrollmentLibraryModule_GetContextFactory.proxyGetContext(this.enrollmentLibraryModule), getEnrollmentAPI(), EnrollmentLibraryModule_GetPermissionsHelperFactory.proxyGetPermissionsHelper(this.enrollmentLibraryModule));
    }

    private EnrollmentTrainingViewModel getEnrollmentTrainingViewModel() {
        return EnrollmentViewModelModule_ProvideEnrollmentTrainingViewModelFactory.proxyProvideEnrollmentTrainingViewModel(this.enrollmentViewModelModule, EnrollmentLibraryModule_GetContextFactory.proxyGetContext(this.enrollmentLibraryModule), getEnrollmentAPI(), EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
    }

    private KidsEnrollmentViewModel getKidsEnrollmentViewModel() {
        return EnrollmentViewModelModule_ProvideKidsEnrollmentViewModelFactory.proxyProvideKidsEnrollmentViewModel(this.enrollmentViewModelModule, EnrollmentLibraryModule_GetContextFactory.proxyGetContext(this.enrollmentLibraryModule), EnrollmentLibraryModule_GetPermissionsHelperFactory.proxyGetPermissionsHelper(this.enrollmentLibraryModule));
    }

    @CanIgnoreReturnValue
    private AbstractEnrollmentActivity injectAbstractEnrollmentActivity(AbstractEnrollmentActivity abstractEnrollmentActivity) {
        AbstractEnrollmentActivity_MembersInjector.injectDeviceInformation(abstractEnrollmentActivity, EnrollmentLibraryModule_GetDeviceInformationFactory.proxyGetDeviceInformation(this.enrollmentLibraryModule));
        AbstractEnrollmentActivity_MembersInjector.injectEnrollmentMetricsRecorder(abstractEnrollmentActivity, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        return abstractEnrollmentActivity;
    }

    @CanIgnoreReturnValue
    private EnrollmentCompleteViewFragment injectEnrollmentCompleteViewFragment(EnrollmentCompleteViewFragment enrollmentCompleteViewFragment) {
        AbstractEnrollmentViewFragment_MembersInjector.injectRoutingService(enrollmentCompleteViewFragment, EnrollmentLibraryModule_ProvidesRoutingServiceFactory.proxyProvidesRoutingService(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectEventBus(enrollmentCompleteViewFragment, EnrollmentLibraryModule_ProvidesEventBusFactory.proxyProvidesEventBus(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentCompleteViewFragment, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectDialogHelper(enrollmentCompleteViewFragment, EnrollmentLibraryModule_GetDialogHelperFactory.proxyGetDialogHelper(this.enrollmentLibraryModule));
        EnrollmentCompleteViewFragment_MembersInjector.injectAnimationHelper(enrollmentCompleteViewFragment, EnrollmentLibraryModule_GetAnimationHelperFactory.proxyGetAnimationHelper(this.enrollmentLibraryModule));
        EnrollmentCompleteViewFragment_MembersInjector.injectEnrollmentThemeUtil(enrollmentCompleteViewFragment, EnrollmentLibraryModule_GetEnrollmentThemeUtilFactory.proxyGetEnrollmentThemeUtil(this.enrollmentLibraryModule));
        EnrollmentCompleteViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentCompleteViewFragment, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        return enrollmentCompleteViewFragment;
    }

    @CanIgnoreReturnValue
    private EnrollmentIntroductionViewFragment injectEnrollmentIntroductionViewFragment(EnrollmentIntroductionViewFragment enrollmentIntroductionViewFragment) {
        AbstractEnrollmentViewFragment_MembersInjector.injectRoutingService(enrollmentIntroductionViewFragment, EnrollmentLibraryModule_ProvidesRoutingServiceFactory.proxyProvidesRoutingService(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectEventBus(enrollmentIntroductionViewFragment, EnrollmentLibraryModule_ProvidesEventBusFactory.proxyProvidesEventBus(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentIntroductionViewFragment, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectDialogHelper(enrollmentIntroductionViewFragment, EnrollmentLibraryModule_GetDialogHelperFactory.proxyGetDialogHelper(this.enrollmentLibraryModule));
        EnrollmentIntroductionViewFragment_MembersInjector.injectViewModel(enrollmentIntroductionViewFragment, getEnrollmentIntroductionViewModel());
        EnrollmentIntroductionViewFragment_MembersInjector.injectPermissionsHelper(enrollmentIntroductionViewFragment, EnrollmentLibraryModule_GetPermissionsHelperFactory.proxyGetPermissionsHelper(this.enrollmentLibraryModule));
        EnrollmentIntroductionViewFragment_MembersInjector.injectIdentityService(enrollmentIntroductionViewFragment, EnrollmentLibraryModule_ProvidesIdentityServiceFactory.proxyProvidesIdentityService(this.enrollmentLibraryModule));
        EnrollmentIntroductionViewFragment_MembersInjector.injectAnimationHelper(enrollmentIntroductionViewFragment, EnrollmentLibraryModule_GetAnimationHelperFactory.proxyGetAnimationHelper(this.enrollmentLibraryModule));
        EnrollmentIntroductionViewFragment_MembersInjector.injectDeviceInformation(enrollmentIntroductionViewFragment, EnrollmentLibraryModule_GetDeviceInformationFactory.proxyGetDeviceInformation(this.enrollmentLibraryModule));
        EnrollmentIntroductionViewFragment_MembersInjector.injectDialogHelper(enrollmentIntroductionViewFragment, EnrollmentLibraryModule_GetDialogHelperFactory.proxyGetDialogHelper(this.enrollmentLibraryModule));
        EnrollmentIntroductionViewFragment_MembersInjector.injectEnrollmentThemeUtil(enrollmentIntroductionViewFragment, EnrollmentLibraryModule_GetEnrollmentThemeUtilFactory.proxyGetEnrollmentThemeUtil(this.enrollmentLibraryModule));
        EnrollmentIntroductionViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentIntroductionViewFragment, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        return enrollmentIntroductionViewFragment;
    }

    @CanIgnoreReturnValue
    private EnrollmentPrivacyTermsViewFragment injectEnrollmentPrivacyTermsViewFragment(EnrollmentPrivacyTermsViewFragment enrollmentPrivacyTermsViewFragment) {
        AbstractEnrollmentViewFragment_MembersInjector.injectRoutingService(enrollmentPrivacyTermsViewFragment, EnrollmentLibraryModule_ProvidesRoutingServiceFactory.proxyProvidesRoutingService(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectEventBus(enrollmentPrivacyTermsViewFragment, EnrollmentLibraryModule_ProvidesEventBusFactory.proxyProvidesEventBus(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentPrivacyTermsViewFragment, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectDialogHelper(enrollmentPrivacyTermsViewFragment, EnrollmentLibraryModule_GetDialogHelperFactory.proxyGetDialogHelper(this.enrollmentLibraryModule));
        EnrollmentPrivacyTermsViewFragment_MembersInjector.injectEnrollmentThemeUtil(enrollmentPrivacyTermsViewFragment, EnrollmentLibraryModule_GetEnrollmentThemeUtilFactory.proxyGetEnrollmentThemeUtil(this.enrollmentLibraryModule));
        EnrollmentPrivacyTermsViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentPrivacyTermsViewFragment, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        return enrollmentPrivacyTermsViewFragment;
    }

    @CanIgnoreReturnValue
    private EnrollmentTermsViewFragment injectEnrollmentTermsViewFragment(EnrollmentTermsViewFragment enrollmentTermsViewFragment) {
        AbstractEnrollmentViewFragment_MembersInjector.injectRoutingService(enrollmentTermsViewFragment, EnrollmentLibraryModule_ProvidesRoutingServiceFactory.proxyProvidesRoutingService(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectEventBus(enrollmentTermsViewFragment, EnrollmentLibraryModule_ProvidesEventBusFactory.proxyProvidesEventBus(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentTermsViewFragment, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectDialogHelper(enrollmentTermsViewFragment, EnrollmentLibraryModule_GetDialogHelperFactory.proxyGetDialogHelper(this.enrollmentLibraryModule));
        EnrollmentTermsViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentTermsViewFragment, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        EnrollmentTermsViewFragment_MembersInjector.injectEnvironmentService(enrollmentTermsViewFragment, EnrollmentLibraryModule_ProvidesEnvironmentServiceFactory.proxyProvidesEnvironmentService(this.enrollmentLibraryModule));
        return enrollmentTermsViewFragment;
    }

    @CanIgnoreReturnValue
    private EnrollmentTrainingProgressBar injectEnrollmentTrainingProgressBar(EnrollmentTrainingProgressBar enrollmentTrainingProgressBar) {
        EnrollmentTrainingProgressBar_MembersInjector.injectEnrollmentThemeUtil(enrollmentTrainingProgressBar, EnrollmentLibraryModule_GetEnrollmentThemeUtilFactory.proxyGetEnrollmentThemeUtil(this.enrollmentLibraryModule));
        return enrollmentTrainingProgressBar;
    }

    @CanIgnoreReturnValue
    private EnrollmentTrainingViewFragment injectEnrollmentTrainingViewFragment(EnrollmentTrainingViewFragment enrollmentTrainingViewFragment) {
        AbstractEnrollmentViewFragment_MembersInjector.injectRoutingService(enrollmentTrainingViewFragment, EnrollmentLibraryModule_ProvidesRoutingServiceFactory.proxyProvidesRoutingService(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectEventBus(enrollmentTrainingViewFragment, EnrollmentLibraryModule_ProvidesEventBusFactory.proxyProvidesEventBus(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentTrainingViewFragment, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectDialogHelper(enrollmentTrainingViewFragment, EnrollmentLibraryModule_GetDialogHelperFactory.proxyGetDialogHelper(this.enrollmentLibraryModule));
        EnrollmentTrainingViewFragment_MembersInjector.injectViewModel(enrollmentTrainingViewFragment, getEnrollmentTrainingViewModel());
        EnrollmentTrainingViewFragment_MembersInjector.injectDialogHelper(enrollmentTrainingViewFragment, EnrollmentLibraryModule_GetDialogHelperFactory.proxyGetDialogHelper(this.enrollmentLibraryModule));
        EnrollmentTrainingViewFragment_MembersInjector.injectAnimationHelper(enrollmentTrainingViewFragment, EnrollmentLibraryModule_GetAnimationHelperFactory.proxyGetAnimationHelper(this.enrollmentLibraryModule));
        EnrollmentTrainingViewFragment_MembersInjector.injectEnrollmentThemeUtil(enrollmentTrainingViewFragment, EnrollmentLibraryModule_GetEnrollmentThemeUtilFactory.proxyGetEnrollmentThemeUtil(this.enrollmentLibraryModule));
        EnrollmentTrainingViewFragment_MembersInjector.injectAlexaStateInteractor(enrollmentTrainingViewFragment, getAlexaStateInteractor());
        EnrollmentTrainingViewFragment_MembersInjector.injectEventBus(enrollmentTrainingViewFragment, EnrollmentLibraryModule_ProvidesEventBusFactory.proxyProvidesEventBus(this.enrollmentLibraryModule));
        EnrollmentTrainingViewFragment_MembersInjector.injectEnrollmentEventBus(enrollmentTrainingViewFragment, EnrollmentLibraryModule_GetEnrollmentEventBusFactory.proxyGetEnrollmentEventBus(this.enrollmentLibraryModule));
        EnrollmentTrainingViewFragment_MembersInjector.injectAlexaVoiceSDKClient(enrollmentTrainingViewFragment, EnrollmentLibraryModule_GetAlexaVoiceSDKClientFactory.proxyGetAlexaVoiceSDKClient(this.enrollmentLibraryModule));
        EnrollmentTrainingViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(enrollmentTrainingViewFragment, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        return enrollmentTrainingViewFragment;
    }

    @CanIgnoreReturnValue
    private KidsEnrollmentIntroductionActivity injectKidsEnrollmentIntroductionActivity(KidsEnrollmentIntroductionActivity kidsEnrollmentIntroductionActivity) {
        AbstractEnrollmentActivity_MembersInjector.injectDeviceInformation(kidsEnrollmentIntroductionActivity, EnrollmentLibraryModule_GetDeviceInformationFactory.proxyGetDeviceInformation(this.enrollmentLibraryModule));
        AbstractEnrollmentActivity_MembersInjector.injectEnrollmentMetricsRecorder(kidsEnrollmentIntroductionActivity, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        return kidsEnrollmentIntroductionActivity;
    }

    @CanIgnoreReturnValue
    private KidsEnrollmentPopup injectKidsEnrollmentPopup(KidsEnrollmentPopup kidsEnrollmentPopup) {
        AbstractBottomSheetFragment_MembersInjector.injectEventBus(kidsEnrollmentPopup, EnrollmentLibraryModule_ProvidesEventBusFactory.proxyProvidesEventBus(this.enrollmentLibraryModule));
        AbstractBottomSheetFragment_MembersInjector.injectRoutingService(kidsEnrollmentPopup, EnrollmentLibraryModule_ProvidesRoutingServiceFactory.proxyProvidesRoutingService(this.enrollmentLibraryModule));
        AbstractBottomSheetFragment_MembersInjector.injectEnrollmentMetricsRecorder(kidsEnrollmentPopup, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        KidsEnrollmentPopup_MembersInjector.injectViewModel(kidsEnrollmentPopup, getKidsEnrollmentViewModel());
        KidsEnrollmentPopup_MembersInjector.injectPermissionsHelper(kidsEnrollmentPopup, EnrollmentLibraryModule_GetPermissionsHelperFactory.proxyGetPermissionsHelper(this.enrollmentLibraryModule));
        KidsEnrollmentPopup_MembersInjector.injectEnrollmentThemeUtil(kidsEnrollmentPopup, EnrollmentLibraryModule_GetEnrollmentThemeUtilFactory.proxyGetEnrollmentThemeUtil(this.enrollmentLibraryModule));
        KidsEnrollmentPopup_MembersInjector.injectEnrollmentMetricsRecorder(kidsEnrollmentPopup, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        return kidsEnrollmentPopup;
    }

    @CanIgnoreReturnValue
    private KidsEnrollmentPrimerViewFragment injectKidsEnrollmentPrimerViewFragment(KidsEnrollmentPrimerViewFragment kidsEnrollmentPrimerViewFragment) {
        AbstractEnrollmentViewFragment_MembersInjector.injectRoutingService(kidsEnrollmentPrimerViewFragment, EnrollmentLibraryModule_ProvidesRoutingServiceFactory.proxyProvidesRoutingService(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectEventBus(kidsEnrollmentPrimerViewFragment, EnrollmentLibraryModule_ProvidesEventBusFactory.proxyProvidesEventBus(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(kidsEnrollmentPrimerViewFragment, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        AbstractEnrollmentViewFragment_MembersInjector.injectDialogHelper(kidsEnrollmentPrimerViewFragment, EnrollmentLibraryModule_GetDialogHelperFactory.proxyGetDialogHelper(this.enrollmentLibraryModule));
        KidsEnrollmentPrimerViewFragment_MembersInjector.injectPopupFragment(kidsEnrollmentPrimerViewFragment, EnrollmentLibraryModule_GetKidsEnrollmentPopupFactory.proxyGetKidsEnrollmentPopup(this.enrollmentLibraryModule));
        KidsEnrollmentPrimerViewFragment_MembersInjector.injectAnimationHelper(kidsEnrollmentPrimerViewFragment, EnrollmentLibraryModule_GetAnimationHelperFactory.proxyGetAnimationHelper(this.enrollmentLibraryModule));
        KidsEnrollmentPrimerViewFragment_MembersInjector.injectDialogHelper(kidsEnrollmentPrimerViewFragment, EnrollmentLibraryModule_GetKidsEnrollmentDialogHelperFactory.proxyGetKidsEnrollmentDialogHelper(this.enrollmentLibraryModule));
        KidsEnrollmentPrimerViewFragment_MembersInjector.injectEnrollmentThemeUtil(kidsEnrollmentPrimerViewFragment, EnrollmentLibraryModule_GetEnrollmentThemeUtilFactory.proxyGetEnrollmentThemeUtil(this.enrollmentLibraryModule));
        KidsEnrollmentPrimerViewFragment_MembersInjector.injectEnrollmentDialogHelper(kidsEnrollmentPrimerViewFragment, EnrollmentLibraryModule_GetDialogHelperFactory.proxyGetDialogHelper(this.enrollmentLibraryModule));
        KidsEnrollmentPrimerViewFragment_MembersInjector.injectEnrollmentViewModel(kidsEnrollmentPrimerViewFragment, getEnrollmentIntroductionViewModel());
        KidsEnrollmentPrimerViewFragment_MembersInjector.injectIdentityService(kidsEnrollmentPrimerViewFragment, EnrollmentLibraryModule_ProvidesIdentityServiceFactory.proxyProvidesIdentityService(this.enrollmentLibraryModule));
        KidsEnrollmentPrimerViewFragment_MembersInjector.injectContext(kidsEnrollmentPrimerViewFragment, EnrollmentLibraryModule_GetContextFactory.proxyGetContext(this.enrollmentLibraryModule));
        KidsEnrollmentPrimerViewFragment_MembersInjector.injectEnrollmentMetricsRecorder(kidsEnrollmentPrimerViewFragment, EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory.proxyGetEnrollmentMetricsRecorder(this.enrollmentLibraryModule));
        return kidsEnrollmentPrimerViewFragment;
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public EnrollmentGateway enrollmentService() {
        return EnrollmentLibraryModule_ProvidesEnrollmentServiceFactory.proxyProvidesEnrollmentService(this.enrollmentLibraryModule);
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public void inject(AlexaStateInteractor alexaStateInteractor) {
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public void inject(AlexaStateTracker alexaStateTracker) {
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public void inject(EnrollmentIntroductionViewFragment enrollmentIntroductionViewFragment) {
        injectEnrollmentIntroductionViewFragment(enrollmentIntroductionViewFragment);
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public void inject(AlexaVoiceSDKClient alexaVoiceSDKClient) {
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public void inject(EnrollmentUserSpeechProvider enrollmentUserSpeechProvider) {
    }

    private DaggerEnrollmentComponent(Builder builder) {
        this.enrollmentLibraryModule = builder.enrollmentLibraryModule;
        this.enrollmentViewModelModule = builder.enrollmentViewModelModule;
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public void inject(EnrollmentTrainingViewFragment enrollmentTrainingViewFragment) {
        injectEnrollmentTrainingViewFragment(enrollmentTrainingViewFragment);
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public void inject(EnrollmentCompleteViewFragment enrollmentCompleteViewFragment) {
        injectEnrollmentCompleteViewFragment(enrollmentCompleteViewFragment);
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public void inject(EnrollmentTermsViewFragment enrollmentTermsViewFragment) {
        injectEnrollmentTermsViewFragment(enrollmentTermsViewFragment);
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public void inject(AbstractEnrollmentActivity abstractEnrollmentActivity) {
        injectAbstractEnrollmentActivity(abstractEnrollmentActivity);
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public void inject(KidsEnrollmentPrimerViewFragment kidsEnrollmentPrimerViewFragment) {
        injectKidsEnrollmentPrimerViewFragment(kidsEnrollmentPrimerViewFragment);
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public void inject(KidsEnrollmentPopup kidsEnrollmentPopup) {
        injectKidsEnrollmentPopup(kidsEnrollmentPopup);
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public void inject(KidsEnrollmentIntroductionActivity kidsEnrollmentIntroductionActivity) {
        injectKidsEnrollmentIntroductionActivity(kidsEnrollmentIntroductionActivity);
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public void inject(EnrollmentPrivacyTermsViewFragment enrollmentPrivacyTermsViewFragment) {
        injectEnrollmentPrivacyTermsViewFragment(enrollmentPrivacyTermsViewFragment);
    }

    @Override // com.amazon.alexa.enrollment.module.library.EnrollmentComponent
    public void inject(EnrollmentTrainingProgressBar enrollmentTrainingProgressBar) {
        injectEnrollmentTrainingProgressBar(enrollmentTrainingProgressBar);
    }
}
