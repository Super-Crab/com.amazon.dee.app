package com.amazon.alexa.mobilytics.event.serializer.protobufhandlers;

import com.amazon.alexa.mobilytics.session.SessionManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class SessionProtobufHandler_Factory implements Factory<SessionProtobufHandler> {
    private final Provider<SessionManager> sessionManagerProvider;

    public SessionProtobufHandler_Factory(Provider<SessionManager> provider) {
        this.sessionManagerProvider = provider;
    }

    public static SessionProtobufHandler_Factory create(Provider<SessionManager> provider) {
        return new SessionProtobufHandler_Factory(provider);
    }

    public static SessionProtobufHandler newSessionProtobufHandler(SessionManager sessionManager) {
        return new SessionProtobufHandler(sessionManager);
    }

    public static SessionProtobufHandler provideInstance(Provider<SessionManager> provider) {
        return new SessionProtobufHandler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SessionProtobufHandler mo10268get() {
        return provideInstance(this.sessionManagerProvider);
    }
}
