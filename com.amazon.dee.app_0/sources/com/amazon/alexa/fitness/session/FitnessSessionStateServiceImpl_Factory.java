package com.amazon.alexa.fitness.session;

import com.amazon.alexa.fitness.sdk.SessionManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class FitnessSessionStateServiceImpl_Factory implements Factory<FitnessSessionStateServiceImpl> {
    private final Provider<SessionManager> sessionManagerProvider;

    public FitnessSessionStateServiceImpl_Factory(Provider<SessionManager> provider) {
        this.sessionManagerProvider = provider;
    }

    public static FitnessSessionStateServiceImpl_Factory create(Provider<SessionManager> provider) {
        return new FitnessSessionStateServiceImpl_Factory(provider);
    }

    public static FitnessSessionStateServiceImpl newFitnessSessionStateServiceImpl(SessionManager sessionManager) {
        return new FitnessSessionStateServiceImpl(sessionManager);
    }

    public static FitnessSessionStateServiceImpl provideInstance(Provider<SessionManager> provider) {
        return new FitnessSessionStateServiceImpl(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FitnessSessionStateServiceImpl mo10268get() {
        return provideInstance(this.sessionManagerProvider);
    }
}
