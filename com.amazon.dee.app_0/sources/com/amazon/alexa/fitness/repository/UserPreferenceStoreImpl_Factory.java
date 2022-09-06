package com.amazon.alexa.fitness.repository;

import android.content.Context;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class UserPreferenceStoreImpl_Factory implements Factory<UserPreferenceStoreImpl> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<IdentityManager> identityManagerProvider;
    private final Provider<ILog> logProvider;

    public UserPreferenceStoreImpl_Factory(Provider<ComponentRegistry> provider, Provider<Context> provider2, Provider<IdentityManager> provider3, Provider<ILog> provider4) {
        this.componentRegistryProvider = provider;
        this.contextProvider = provider2;
        this.identityManagerProvider = provider3;
        this.logProvider = provider4;
    }

    public static UserPreferenceStoreImpl_Factory create(Provider<ComponentRegistry> provider, Provider<Context> provider2, Provider<IdentityManager> provider3, Provider<ILog> provider4) {
        return new UserPreferenceStoreImpl_Factory(provider, provider2, provider3, provider4);
    }

    public static UserPreferenceStoreImpl newUserPreferenceStoreImpl(ComponentRegistry componentRegistry, Context context, IdentityManager identityManager, ILog iLog) {
        return new UserPreferenceStoreImpl(componentRegistry, context, identityManager, iLog);
    }

    public static UserPreferenceStoreImpl provideInstance(Provider<ComponentRegistry> provider, Provider<Context> provider2, Provider<IdentityManager> provider3, Provider<ILog> provider4) {
        return new UserPreferenceStoreImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UserPreferenceStoreImpl mo10268get() {
        return provideInstance(this.componentRegistryProvider, this.contextProvider, this.identityManagerProvider, this.logProvider);
    }
}
