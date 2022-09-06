package com.amazon.ptz.dagger;

import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class SerializationModule_ProvideRcSerializerFactory implements Factory<RcSerializer> {
    private final SerializationModule module;

    public SerializationModule_ProvideRcSerializerFactory(SerializationModule serializationModule) {
        this.module = serializationModule;
    }

    public static SerializationModule_ProvideRcSerializerFactory create(SerializationModule serializationModule) {
        return new SerializationModule_ProvideRcSerializerFactory(serializationModule);
    }

    public static RcSerializer provideInstance(SerializationModule serializationModule) {
        return proxyProvideRcSerializer(serializationModule);
    }

    public static RcSerializer proxyProvideRcSerializer(SerializationModule serializationModule) {
        return (RcSerializer) Preconditions.checkNotNull(serializationModule.provideRcSerializer(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RcSerializer mo10268get() {
        return provideInstance(this.module);
    }
}
