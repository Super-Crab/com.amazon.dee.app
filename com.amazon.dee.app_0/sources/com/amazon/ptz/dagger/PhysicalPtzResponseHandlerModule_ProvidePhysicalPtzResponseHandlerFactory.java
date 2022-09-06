package com.amazon.ptz.dagger;

import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import com.amazon.ptz.physical.communication.PhysicalPtzCommandCache;
import com.amazon.ptz.physical.communication.PhysicalPtzResponseHandler;
import com.amazon.ptz.physical.communication.responses.handlers.ResponseHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class PhysicalPtzResponseHandlerModule_ProvidePhysicalPtzResponseHandlerFactory implements Factory<PhysicalPtzResponseHandler> {
    private final PhysicalPtzResponseHandlerModule module;
    private final Provider<PhysicalPtzCommandCache> physicalPtzCommandCacheProvider;
    private final Provider<RcSerializer> rcSerializerProvider;
    private final Provider<Set<ResponseHandler>> responseHandlersProvider;

    public PhysicalPtzResponseHandlerModule_ProvidePhysicalPtzResponseHandlerFactory(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<RcSerializer> provider, Provider<PhysicalPtzCommandCache> provider2, Provider<Set<ResponseHandler>> provider3) {
        this.module = physicalPtzResponseHandlerModule;
        this.rcSerializerProvider = provider;
        this.physicalPtzCommandCacheProvider = provider2;
        this.responseHandlersProvider = provider3;
    }

    public static PhysicalPtzResponseHandlerModule_ProvidePhysicalPtzResponseHandlerFactory create(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<RcSerializer> provider, Provider<PhysicalPtzCommandCache> provider2, Provider<Set<ResponseHandler>> provider3) {
        return new PhysicalPtzResponseHandlerModule_ProvidePhysicalPtzResponseHandlerFactory(physicalPtzResponseHandlerModule, provider, provider2, provider3);
    }

    public static PhysicalPtzResponseHandler provideInstance(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<RcSerializer> provider, Provider<PhysicalPtzCommandCache> provider2, Provider<Set<ResponseHandler>> provider3) {
        return proxyProvidePhysicalPtzResponseHandler(physicalPtzResponseHandlerModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static PhysicalPtzResponseHandler proxyProvidePhysicalPtzResponseHandler(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, RcSerializer rcSerializer, PhysicalPtzCommandCache physicalPtzCommandCache, Set<ResponseHandler> set) {
        return (PhysicalPtzResponseHandler) Preconditions.checkNotNull(physicalPtzResponseHandlerModule.providePhysicalPtzResponseHandler(rcSerializer, physicalPtzCommandCache, set), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhysicalPtzResponseHandler mo10268get() {
        return provideInstance(this.module, this.rcSerializerProvider, this.physicalPtzCommandCacheProvider, this.responseHandlersProvider);
    }
}
