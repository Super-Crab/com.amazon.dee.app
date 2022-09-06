package com.amazon.deecomms.core;

import com.amazon.deecomms.calling.controller.CallingContentProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class SipModule_ProvidesCallingContentProviderFactory implements Factory<CallingContentProvider> {
    private final SipModule module;

    public SipModule_ProvidesCallingContentProviderFactory(SipModule sipModule) {
        this.module = sipModule;
    }

    public static SipModule_ProvidesCallingContentProviderFactory create(SipModule sipModule) {
        return new SipModule_ProvidesCallingContentProviderFactory(sipModule);
    }

    public static CallingContentProvider provideInstance(SipModule sipModule) {
        return (CallingContentProvider) Preconditions.checkNotNull(sipModule.providesCallingContentProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CallingContentProvider proxyProvidesCallingContentProvider(SipModule sipModule) {
        return (CallingContentProvider) Preconditions.checkNotNull(sipModule.providesCallingContentProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallingContentProvider mo10268get() {
        return provideInstance(this.module);
    }
}
