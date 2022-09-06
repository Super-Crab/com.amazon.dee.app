package com.amazon.alexa.mobilytics.dependencies;

import com.amazon.alexa.mobilytics.event.serializer.handlers.ApplicationDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.DataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.DeviceDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.EventDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.MwsPivotsDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.SessionDataHandler;
import com.amazon.alexa.mobilytics.event.serializer.handlers.UserDataHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class MobilyticsModule_ProvideDefaultDataHandlersFactory implements Factory<List<DataHandler>> {
    private final Provider<ApplicationDataHandler> applicationDetailsDataHandlerProvider;
    private final Provider<DeviceDataHandler> deviceDetailsDataHandlerProvider;
    private final Provider<EventDataHandler> eventDetailsDataHandlerProvider;
    private final MobilyticsModule module;
    private final Provider<MwsPivotsDataHandler> mwsPivotsDataHandlerProvider;
    private final Provider<SessionDataHandler> sessionDataHandlerProvider;
    private final Provider<UserDataHandler> userDetailsDataHandlerProvider;

    public MobilyticsModule_ProvideDefaultDataHandlersFactory(MobilyticsModule mobilyticsModule, Provider<ApplicationDataHandler> provider, Provider<DeviceDataHandler> provider2, Provider<EventDataHandler> provider3, Provider<MwsPivotsDataHandler> provider4, Provider<UserDataHandler> provider5, Provider<SessionDataHandler> provider6) {
        this.module = mobilyticsModule;
        this.applicationDetailsDataHandlerProvider = provider;
        this.deviceDetailsDataHandlerProvider = provider2;
        this.eventDetailsDataHandlerProvider = provider3;
        this.mwsPivotsDataHandlerProvider = provider4;
        this.userDetailsDataHandlerProvider = provider5;
        this.sessionDataHandlerProvider = provider6;
    }

    public static MobilyticsModule_ProvideDefaultDataHandlersFactory create(MobilyticsModule mobilyticsModule, Provider<ApplicationDataHandler> provider, Provider<DeviceDataHandler> provider2, Provider<EventDataHandler> provider3, Provider<MwsPivotsDataHandler> provider4, Provider<UserDataHandler> provider5, Provider<SessionDataHandler> provider6) {
        return new MobilyticsModule_ProvideDefaultDataHandlersFactory(mobilyticsModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static List<DataHandler> provideInstance(MobilyticsModule mobilyticsModule, Provider<ApplicationDataHandler> provider, Provider<DeviceDataHandler> provider2, Provider<EventDataHandler> provider3, Provider<MwsPivotsDataHandler> provider4, Provider<UserDataHandler> provider5, Provider<SessionDataHandler> provider6) {
        return proxyProvideDefaultDataHandlers(mobilyticsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    public static List<DataHandler> proxyProvideDefaultDataHandlers(MobilyticsModule mobilyticsModule, ApplicationDataHandler applicationDataHandler, DeviceDataHandler deviceDataHandler, EventDataHandler eventDataHandler, MwsPivotsDataHandler mwsPivotsDataHandler, UserDataHandler userDataHandler, SessionDataHandler sessionDataHandler) {
        return (List) Preconditions.checkNotNull(mobilyticsModule.provideDefaultDataHandlers(applicationDataHandler, deviceDataHandler, eventDataHandler, mwsPivotsDataHandler, userDataHandler, sessionDataHandler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public List<DataHandler> mo10268get() {
        return provideInstance(this.module, this.applicationDetailsDataHandlerProvider, this.deviceDetailsDataHandlerProvider, this.eventDetailsDataHandlerProvider, this.mwsPivotsDataHandlerProvider, this.userDetailsDataHandlerProvider, this.sessionDataHandlerProvider);
    }
}
