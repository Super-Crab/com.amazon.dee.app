package com.amazon.alexa.mobilytics.dependencies;

import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ApplicationProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.DeviceProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.EventProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.MwsPivotsProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.SessionProtobufHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.UserProtobufHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class MobilyticsModule_ProvideProtobufProtobufHandlersFactory implements Factory<List<ProtobufHandler>> {
    private final Provider<ApplicationProtobufHandler> applicationDetailsProtobufHandlerProvider;
    private final Provider<DeviceProtobufHandler> deviceDetailsProtobufHandlerProvider;
    private final Provider<EventProtobufHandler> eventDetailsProtobufHandlerProvider;
    private final MobilyticsModule module;
    private final Provider<MwsPivotsProtobufHandler> mwsPivotsProtobufHandlerProvider;
    private final Provider<SessionProtobufHandler> sessionProtobufHandlerProvider;
    private final Provider<UserProtobufHandler> userDetailsProtobufHandlerProvider;

    public MobilyticsModule_ProvideProtobufProtobufHandlersFactory(MobilyticsModule mobilyticsModule, Provider<ApplicationProtobufHandler> provider, Provider<DeviceProtobufHandler> provider2, Provider<EventProtobufHandler> provider3, Provider<MwsPivotsProtobufHandler> provider4, Provider<UserProtobufHandler> provider5, Provider<SessionProtobufHandler> provider6) {
        this.module = mobilyticsModule;
        this.applicationDetailsProtobufHandlerProvider = provider;
        this.deviceDetailsProtobufHandlerProvider = provider2;
        this.eventDetailsProtobufHandlerProvider = provider3;
        this.mwsPivotsProtobufHandlerProvider = provider4;
        this.userDetailsProtobufHandlerProvider = provider5;
        this.sessionProtobufHandlerProvider = provider6;
    }

    public static MobilyticsModule_ProvideProtobufProtobufHandlersFactory create(MobilyticsModule mobilyticsModule, Provider<ApplicationProtobufHandler> provider, Provider<DeviceProtobufHandler> provider2, Provider<EventProtobufHandler> provider3, Provider<MwsPivotsProtobufHandler> provider4, Provider<UserProtobufHandler> provider5, Provider<SessionProtobufHandler> provider6) {
        return new MobilyticsModule_ProvideProtobufProtobufHandlersFactory(mobilyticsModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static List<ProtobufHandler> provideInstance(MobilyticsModule mobilyticsModule, Provider<ApplicationProtobufHandler> provider, Provider<DeviceProtobufHandler> provider2, Provider<EventProtobufHandler> provider3, Provider<MwsPivotsProtobufHandler> provider4, Provider<UserProtobufHandler> provider5, Provider<SessionProtobufHandler> provider6) {
        return proxyProvideProtobufProtobufHandlers(mobilyticsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    public static List<ProtobufHandler> proxyProvideProtobufProtobufHandlers(MobilyticsModule mobilyticsModule, ApplicationProtobufHandler applicationProtobufHandler, DeviceProtobufHandler deviceProtobufHandler, EventProtobufHandler eventProtobufHandler, MwsPivotsProtobufHandler mwsPivotsProtobufHandler, UserProtobufHandler userProtobufHandler, SessionProtobufHandler sessionProtobufHandler) {
        return (List) Preconditions.checkNotNull(mobilyticsModule.provideProtobufProtobufHandlers(applicationProtobufHandler, deviceProtobufHandler, eventProtobufHandler, mwsPivotsProtobufHandler, userProtobufHandler, sessionProtobufHandler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public List<ProtobufHandler> mo10268get() {
        return provideInstance(this.module, this.applicationDetailsProtobufHandlerProvider, this.deviceDetailsProtobufHandlerProvider, this.eventDetailsProtobufHandlerProvider, this.mwsPivotsProtobufHandlerProvider, this.userDetailsProtobufHandlerProvider, this.sessionProtobufHandlerProvider);
    }
}
