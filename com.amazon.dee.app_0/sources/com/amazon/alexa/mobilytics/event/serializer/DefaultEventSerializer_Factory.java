package com.amazon.alexa.mobilytics.event.serializer;

import com.amazon.alexa.mobilytics.event.serializer.handlers.DataHandler;
import dagger.internal.Factory;
import java.util.List;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DefaultEventSerializer_Factory implements Factory<DefaultEventSerializer> {
    private final Provider<List<DataHandler>> handlersProvider;

    public DefaultEventSerializer_Factory(Provider<List<DataHandler>> provider) {
        this.handlersProvider = provider;
    }

    public static DefaultEventSerializer_Factory create(Provider<List<DataHandler>> provider) {
        return new DefaultEventSerializer_Factory(provider);
    }

    public static DefaultEventSerializer newDefaultEventSerializer(List<DataHandler> list) {
        return new DefaultEventSerializer(list);
    }

    public static DefaultEventSerializer provideInstance(Provider<List<DataHandler>> provider) {
        return new DefaultEventSerializer(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultEventSerializer mo10268get() {
        return provideInstance(this.handlersProvider);
    }
}
