package com.amazon.alexa.tarazed.service;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.tarazed.account.AccountMetadataProviderAlexaMobile;
import com.amazon.alexa.tarazed.account.AztecTokenProviderAlexaMobile;
import com.amazon.alexa.tarazed.account.IdentityEventListener;
import com.amazon.alexa.tarazed.dmps.DMPSHandler;
import com.amazon.alexa.tarazed.utils.FeatureChecker;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import dagger.MembersInjector;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
/* loaded from: classes10.dex */
public final class DefaultAlexaTarazedService_MembersInjector implements MembersInjector<DefaultAlexaTarazedService> {
    private final Provider<AccountMetadataProviderAlexaMobile> accountMetadataProvider;
    private final Provider<DeviceInformation> alexaMobileDeviceInformationProvider;
    private final Provider<AztecTokenProviderAlexaMobile> aztecTokenProvider;
    private final Provider<DMPSHandler> dmpsHandlerProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<FeatureChecker> featureCheckerProvider;
    private final Provider<IdentityEventListener> identityEventListenerProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<CoroutineScope> mainScopeProvider;
    private final Provider<TarazedSessionNotifier> sessionNotifierProvider;

    public DefaultAlexaTarazedService_MembersInjector(Provider<TarazedSessionLogger> provider, Provider<TarazedSessionNotifier> provider2, Provider<FeatureChecker> provider3, Provider<DMPSHandler> provider4, Provider<IdentityService> provider5, Provider<EventBus> provider6, Provider<IdentityEventListener> provider7, Provider<AztecTokenProviderAlexaMobile> provider8, Provider<AccountMetadataProviderAlexaMobile> provider9, Provider<DeviceInformation> provider10, Provider<CoroutineScope> provider11) {
        this.loggerProvider = provider;
        this.sessionNotifierProvider = provider2;
        this.featureCheckerProvider = provider3;
        this.dmpsHandlerProvider = provider4;
        this.identityServiceProvider = provider5;
        this.eventBusProvider = provider6;
        this.identityEventListenerProvider = provider7;
        this.aztecTokenProvider = provider8;
        this.accountMetadataProvider = provider9;
        this.alexaMobileDeviceInformationProvider = provider10;
        this.mainScopeProvider = provider11;
    }

    public static MembersInjector<DefaultAlexaTarazedService> create(Provider<TarazedSessionLogger> provider, Provider<TarazedSessionNotifier> provider2, Provider<FeatureChecker> provider3, Provider<DMPSHandler> provider4, Provider<IdentityService> provider5, Provider<EventBus> provider6, Provider<IdentityEventListener> provider7, Provider<AztecTokenProviderAlexaMobile> provider8, Provider<AccountMetadataProviderAlexaMobile> provider9, Provider<DeviceInformation> provider10, Provider<CoroutineScope> provider11) {
        return new DefaultAlexaTarazedService_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static void injectAccountMetadataProvider(DefaultAlexaTarazedService defaultAlexaTarazedService, Provider<AccountMetadataProviderAlexaMobile> provider) {
        defaultAlexaTarazedService.accountMetadataProvider = provider;
    }

    public static void injectAlexaMobileDeviceInformation(DefaultAlexaTarazedService defaultAlexaTarazedService, DeviceInformation deviceInformation) {
        defaultAlexaTarazedService.alexaMobileDeviceInformation = deviceInformation;
    }

    public static void injectAztecTokenProvider(DefaultAlexaTarazedService defaultAlexaTarazedService, Provider<AztecTokenProviderAlexaMobile> provider) {
        defaultAlexaTarazedService.aztecTokenProvider = provider;
    }

    public static void injectDmpsHandler(DefaultAlexaTarazedService defaultAlexaTarazedService, DMPSHandler dMPSHandler) {
        defaultAlexaTarazedService.dmpsHandler = dMPSHandler;
    }

    public static void injectEventBus(DefaultAlexaTarazedService defaultAlexaTarazedService, EventBus eventBus) {
        defaultAlexaTarazedService.eventBus = eventBus;
    }

    public static void injectFeatureChecker(DefaultAlexaTarazedService defaultAlexaTarazedService, FeatureChecker featureChecker) {
        defaultAlexaTarazedService.featureChecker = featureChecker;
    }

    public static void injectIdentityEventListener(DefaultAlexaTarazedService defaultAlexaTarazedService, IdentityEventListener identityEventListener) {
        defaultAlexaTarazedService.identityEventListener = identityEventListener;
    }

    public static void injectIdentityService(DefaultAlexaTarazedService defaultAlexaTarazedService, IdentityService identityService) {
        defaultAlexaTarazedService.identityService = identityService;
    }

    public static void injectLogger(DefaultAlexaTarazedService defaultAlexaTarazedService, TarazedSessionLogger tarazedSessionLogger) {
        defaultAlexaTarazedService.logger = tarazedSessionLogger;
    }

    public static void injectMainScope(DefaultAlexaTarazedService defaultAlexaTarazedService, CoroutineScope coroutineScope) {
        defaultAlexaTarazedService.mainScope = coroutineScope;
    }

    public static void injectSessionNotifier(DefaultAlexaTarazedService defaultAlexaTarazedService, TarazedSessionNotifier tarazedSessionNotifier) {
        defaultAlexaTarazedService.sessionNotifier = tarazedSessionNotifier;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DefaultAlexaTarazedService defaultAlexaTarazedService) {
        injectLogger(defaultAlexaTarazedService, this.loggerProvider.mo10268get());
        injectSessionNotifier(defaultAlexaTarazedService, this.sessionNotifierProvider.mo10268get());
        injectFeatureChecker(defaultAlexaTarazedService, this.featureCheckerProvider.mo10268get());
        injectDmpsHandler(defaultAlexaTarazedService, this.dmpsHandlerProvider.mo10268get());
        injectIdentityService(defaultAlexaTarazedService, this.identityServiceProvider.mo10268get());
        injectEventBus(defaultAlexaTarazedService, this.eventBusProvider.mo10268get());
        injectIdentityEventListener(defaultAlexaTarazedService, this.identityEventListenerProvider.mo10268get());
        injectAztecTokenProvider(defaultAlexaTarazedService, this.aztecTokenProvider);
        injectAccountMetadataProvider(defaultAlexaTarazedService, this.accountMetadataProvider);
        injectAlexaMobileDeviceInformation(defaultAlexaTarazedService, this.alexaMobileDeviceInformationProvider.mo10268get());
        injectMainScope(defaultAlexaTarazedService, this.mainScopeProvider.mo10268get());
    }
}
