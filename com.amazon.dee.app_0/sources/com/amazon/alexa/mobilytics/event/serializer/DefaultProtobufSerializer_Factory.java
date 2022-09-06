package com.amazon.alexa.mobilytics.event.serializer;

import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ProtobufHandler;
import dagger.internal.Factory;
import java.util.List;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DefaultProtobufSerializer_Factory implements Factory<DefaultProtobufSerializer> {
    private final Provider<List<ProtobufHandler>> handlersProvider;

    public DefaultProtobufSerializer_Factory(Provider<List<ProtobufHandler>> provider) {
        this.handlersProvider = provider;
    }

    public static DefaultProtobufSerializer_Factory create(Provider<List<ProtobufHandler>> provider) {
        return new DefaultProtobufSerializer_Factory(provider);
    }

    public static DefaultProtobufSerializer newDefaultProtobufSerializer(List<ProtobufHandler> list) {
        return new DefaultProtobufSerializer(list);
    }

    public static DefaultProtobufSerializer provideInstance(Provider<List<ProtobufHandler>> provider) {
        return new DefaultProtobufSerializer(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultProtobufSerializer mo10268get() {
        return provideInstance(this.handlersProvider);
    }
}
