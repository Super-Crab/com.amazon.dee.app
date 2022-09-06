package com.amazon.alexa.mobilytics.event.serializer.handlers;

import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class UserDataHandler_Factory implements Factory<UserDataHandler> {
    private final Provider<MobilyticsUserProvider> userProvider;

    public UserDataHandler_Factory(Provider<MobilyticsUserProvider> provider) {
        this.userProvider = provider;
    }

    public static UserDataHandler_Factory create(Provider<MobilyticsUserProvider> provider) {
        return new UserDataHandler_Factory(provider);
    }

    public static UserDataHandler newUserDataHandler(MobilyticsUserProvider mobilyticsUserProvider) {
        return new UserDataHandler(mobilyticsUserProvider);
    }

    public static UserDataHandler provideInstance(Provider<MobilyticsUserProvider> provider) {
        return new UserDataHandler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UserDataHandler mo10268get() {
        return provideInstance(this.userProvider);
    }
}
