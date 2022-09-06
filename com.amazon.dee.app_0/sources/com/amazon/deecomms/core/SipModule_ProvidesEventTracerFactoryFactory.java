package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.comms.instrumentation.EventTracerFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class SipModule_ProvidesEventTracerFactoryFactory implements Factory<EventTracerFactory> {
    private final Provider<Context> applicationContextProvider;
    private final SipModule module;

    public SipModule_ProvidesEventTracerFactoryFactory(SipModule sipModule, Provider<Context> provider) {
        this.module = sipModule;
        this.applicationContextProvider = provider;
    }

    public static SipModule_ProvidesEventTracerFactoryFactory create(SipModule sipModule, Provider<Context> provider) {
        return new SipModule_ProvidesEventTracerFactoryFactory(sipModule, provider);
    }

    public static EventTracerFactory provideInstance(SipModule sipModule, Provider<Context> provider) {
        return (EventTracerFactory) Preconditions.checkNotNull(sipModule.providesEventTracerFactory(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static EventTracerFactory proxyProvidesEventTracerFactory(SipModule sipModule, Context context) {
        return (EventTracerFactory) Preconditions.checkNotNull(sipModule.providesEventTracerFactory(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventTracerFactory mo10268get() {
        return provideInstance(this.module, this.applicationContextProvider);
    }
}
