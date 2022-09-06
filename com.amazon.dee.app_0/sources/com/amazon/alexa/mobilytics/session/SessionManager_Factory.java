package com.amazon.alexa.mobilytics.session;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class SessionManager_Factory implements Factory<SessionManager> {
    private final Provider<Context> contextProvider;
    private final Provider<String> installationIdProvider;
    private final Provider<SessionStorage> sessionStorageProvider;

    public SessionManager_Factory(Provider<Context> provider, Provider<String> provider2, Provider<SessionStorage> provider3) {
        this.contextProvider = provider;
        this.installationIdProvider = provider2;
        this.sessionStorageProvider = provider3;
    }

    public static SessionManager_Factory create(Provider<Context> provider, Provider<String> provider2, Provider<SessionStorage> provider3) {
        return new SessionManager_Factory(provider, provider2, provider3);
    }

    public static SessionManager newSessionManager(Context context, String str, SessionStorage sessionStorage) {
        return new SessionManager(context, str, sessionStorage);
    }

    public static SessionManager provideInstance(Provider<Context> provider, Provider<String> provider2, Provider<SessionStorage> provider3) {
        return new SessionManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SessionManager mo10268get() {
        return provideInstance(this.contextProvider, this.installationIdProvider, this.sessionStorageProvider);
    }
}
