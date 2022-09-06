package com.amazon.alexa.mobilytics.event.serializer.handlers;

import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.internal.JsonConverter;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class EventDataHandler_Factory implements Factory<EventDataHandler> {
    private final Provider<MobilyticsConfiguration> configurationProvider;
    private final Provider<JsonConverter> jsonConverterProvider;

    public EventDataHandler_Factory(Provider<MobilyticsConfiguration> provider, Provider<JsonConverter> provider2) {
        this.configurationProvider = provider;
        this.jsonConverterProvider = provider2;
    }

    public static EventDataHandler_Factory create(Provider<MobilyticsConfiguration> provider, Provider<JsonConverter> provider2) {
        return new EventDataHandler_Factory(provider, provider2);
    }

    public static EventDataHandler newEventDataHandler(MobilyticsConfiguration mobilyticsConfiguration, JsonConverter jsonConverter) {
        return new EventDataHandler(mobilyticsConfiguration, jsonConverter);
    }

    public static EventDataHandler provideInstance(Provider<MobilyticsConfiguration> provider, Provider<JsonConverter> provider2) {
        return new EventDataHandler(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventDataHandler mo10268get() {
        return provideInstance(this.configurationProvider, this.jsonConverterProvider);
    }
}
