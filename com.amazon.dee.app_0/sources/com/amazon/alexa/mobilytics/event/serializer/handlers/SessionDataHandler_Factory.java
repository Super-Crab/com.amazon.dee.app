package com.amazon.alexa.mobilytics.event.serializer.handlers;

import com.amazon.alexa.mobilytics.session.SessionManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class SessionDataHandler_Factory implements Factory<SessionDataHandler> {
    private final Provider<SessionManager> sessionManagerProvider;

    public SessionDataHandler_Factory(Provider<SessionManager> provider) {
        this.sessionManagerProvider = provider;
    }

    public static SessionDataHandler_Factory create(Provider<SessionManager> provider) {
        return new SessionDataHandler_Factory(provider);
    }

    public static SessionDataHandler newSessionDataHandler(SessionManager sessionManager) {
        return new SessionDataHandler(sessionManager);
    }

    public static SessionDataHandler provideInstance(Provider<SessionManager> provider) {
        return new SessionDataHandler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SessionDataHandler mo10268get() {
        return provideInstance(this.sessionManagerProvider);
    }
}
