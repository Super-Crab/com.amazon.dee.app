package com.amazon.alexa.mobilytics.event.serializer.protobufhandlers;

import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class UserProtobufHandler_Factory implements Factory<UserProtobufHandler> {
    private final Provider<MobilyticsUserProvider> userProvider;

    public UserProtobufHandler_Factory(Provider<MobilyticsUserProvider> provider) {
        this.userProvider = provider;
    }

    public static UserProtobufHandler_Factory create(Provider<MobilyticsUserProvider> provider) {
        return new UserProtobufHandler_Factory(provider);
    }

    public static UserProtobufHandler newUserProtobufHandler(MobilyticsUserProvider mobilyticsUserProvider) {
        return new UserProtobufHandler(mobilyticsUserProvider);
    }

    public static UserProtobufHandler provideInstance(Provider<MobilyticsUserProvider> provider) {
        return new UserProtobufHandler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UserProtobufHandler mo10268get() {
        return provideInstance(this.userProvider);
    }
}
