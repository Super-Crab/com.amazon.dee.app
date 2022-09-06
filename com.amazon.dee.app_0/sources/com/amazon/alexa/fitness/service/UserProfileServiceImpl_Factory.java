package com.amazon.alexa.fitness.service;

import com.amazon.alexa.fitness.api.afx.UserProfileRepository;
import com.amazon.alexa.fitness.configuration.UserProfileServiceConfigurationProvider;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class UserProfileServiceImpl_Factory implements Factory<UserProfileServiceImpl> {
    private final Provider<UserProfileServiceConfigurationProvider> configurationProvider;
    private final Provider<IdentityManager> identityManagerProvider;
    private final Provider<ILog> logProvider;
    private final Provider<UserProfileRepository> userProfileRepositoryProvider;

    public UserProfileServiceImpl_Factory(Provider<UserProfileServiceConfigurationProvider> provider, Provider<IdentityManager> provider2, Provider<UserProfileRepository> provider3, Provider<ILog> provider4) {
        this.configurationProvider = provider;
        this.identityManagerProvider = provider2;
        this.userProfileRepositoryProvider = provider3;
        this.logProvider = provider4;
    }

    public static UserProfileServiceImpl_Factory create(Provider<UserProfileServiceConfigurationProvider> provider, Provider<IdentityManager> provider2, Provider<UserProfileRepository> provider3, Provider<ILog> provider4) {
        return new UserProfileServiceImpl_Factory(provider, provider2, provider3, provider4);
    }

    public static UserProfileServiceImpl newUserProfileServiceImpl(UserProfileServiceConfigurationProvider userProfileServiceConfigurationProvider, IdentityManager identityManager, UserProfileRepository userProfileRepository, ILog iLog) {
        return new UserProfileServiceImpl(userProfileServiceConfigurationProvider, identityManager, userProfileRepository, iLog);
    }

    public static UserProfileServiceImpl provideInstance(Provider<UserProfileServiceConfigurationProvider> provider, Provider<IdentityManager> provider2, Provider<UserProfileRepository> provider3, Provider<ILog> provider4) {
        return new UserProfileServiceImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UserProfileServiceImpl mo10268get() {
        return provideInstance(this.configurationProvider, this.identityManagerProvider, this.userProfileRepositoryProvider, this.logProvider);
    }
}
