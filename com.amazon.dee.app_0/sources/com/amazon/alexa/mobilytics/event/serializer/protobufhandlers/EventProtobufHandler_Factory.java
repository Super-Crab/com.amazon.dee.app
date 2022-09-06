package com.amazon.alexa.mobilytics.event.serializer.protobufhandlers;

import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class EventProtobufHandler_Factory implements Factory<EventProtobufHandler> {
    private final Provider<MobilyticsConfiguration> configurationProvider;

    public EventProtobufHandler_Factory(Provider<MobilyticsConfiguration> provider) {
        this.configurationProvider = provider;
    }

    public static EventProtobufHandler_Factory create(Provider<MobilyticsConfiguration> provider) {
        return new EventProtobufHandler_Factory(provider);
    }

    public static EventProtobufHandler newEventProtobufHandler(MobilyticsConfiguration mobilyticsConfiguration) {
        return new EventProtobufHandler(mobilyticsConfiguration);
    }

    public static EventProtobufHandler provideInstance(Provider<MobilyticsConfiguration> provider) {
        return new EventProtobufHandler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventProtobufHandler mo10268get() {
        return provideInstance(this.configurationProvider);
    }
}
