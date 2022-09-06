package com.amazon.dee.app.ui.external;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.services.useragent.UserAgentService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ExternalUIViewModel_MembersInjector implements MembersInjector<ExternalUIViewModel> {
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<UserAgentService> userAgentServiceProvider;

    public ExternalUIViewModel_MembersInjector(Provider<EnvironmentService> provider, Provider<UserAgentService> provider2) {
        this.environmentServiceProvider = provider;
        this.userAgentServiceProvider = provider2;
    }

    public static MembersInjector<ExternalUIViewModel> create(Provider<EnvironmentService> provider, Provider<UserAgentService> provider2) {
        return new ExternalUIViewModel_MembersInjector(provider, provider2);
    }

    public static void injectEnvironmentService(ExternalUIViewModel externalUIViewModel, EnvironmentService environmentService) {
        externalUIViewModel.environmentService = environmentService;
    }

    public static void injectUserAgentService(ExternalUIViewModel externalUIViewModel, UserAgentService userAgentService) {
        externalUIViewModel.userAgentService = userAgentService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ExternalUIViewModel externalUIViewModel) {
        injectEnvironmentService(externalUIViewModel, this.environmentServiceProvider.mo10268get());
        injectUserAgentService(externalUIViewModel, this.userAgentServiceProvider.mo10268get());
    }
}
