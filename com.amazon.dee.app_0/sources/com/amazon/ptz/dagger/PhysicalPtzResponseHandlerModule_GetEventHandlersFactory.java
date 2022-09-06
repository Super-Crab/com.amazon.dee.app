package com.amazon.ptz.dagger;

import com.amazon.ptz.physical.communication.responses.handlers.ChangeReportHandler;
import com.amazon.ptz.physical.communication.responses.handlers.ErrorResponseHandler;
import com.amazon.ptz.physical.communication.responses.handlers.ResponseHandler;
import com.amazon.ptz.physical.communication.responses.handlers.SafetyErrorResponseHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class PhysicalPtzResponseHandlerModule_GetEventHandlersFactory implements Factory<Set<ResponseHandler>> {
    private final Provider<ChangeReportHandler> changeReportHandlerProvider;
    private final Provider<ErrorResponseHandler> errorResponseHandlerProvider;
    private final PhysicalPtzResponseHandlerModule module;
    private final Provider<SafetyErrorResponseHandler> safetyErrorResponseHandlerProvider;

    public PhysicalPtzResponseHandlerModule_GetEventHandlersFactory(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<ErrorResponseHandler> provider, Provider<SafetyErrorResponseHandler> provider2, Provider<ChangeReportHandler> provider3) {
        this.module = physicalPtzResponseHandlerModule;
        this.errorResponseHandlerProvider = provider;
        this.safetyErrorResponseHandlerProvider = provider2;
        this.changeReportHandlerProvider = provider3;
    }

    public static PhysicalPtzResponseHandlerModule_GetEventHandlersFactory create(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<ErrorResponseHandler> provider, Provider<SafetyErrorResponseHandler> provider2, Provider<ChangeReportHandler> provider3) {
        return new PhysicalPtzResponseHandlerModule_GetEventHandlersFactory(physicalPtzResponseHandlerModule, provider, provider2, provider3);
    }

    public static Set<ResponseHandler> provideInstance(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, Provider<ErrorResponseHandler> provider, Provider<SafetyErrorResponseHandler> provider2, Provider<ChangeReportHandler> provider3) {
        return proxyGetEventHandlers(physicalPtzResponseHandlerModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static Set<ResponseHandler> proxyGetEventHandlers(PhysicalPtzResponseHandlerModule physicalPtzResponseHandlerModule, ErrorResponseHandler errorResponseHandler, SafetyErrorResponseHandler safetyErrorResponseHandler, ChangeReportHandler changeReportHandler) {
        return (Set) Preconditions.checkNotNull(physicalPtzResponseHandlerModule.getEventHandlers(errorResponseHandler, safetyErrorResponseHandler, changeReportHandler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Set<ResponseHandler> mo10268get() {
        return provideInstance(this.module, this.errorResponseHandlerProvider, this.safetyErrorResponseHandlerProvider, this.changeReportHandlerProvider);
    }
}
