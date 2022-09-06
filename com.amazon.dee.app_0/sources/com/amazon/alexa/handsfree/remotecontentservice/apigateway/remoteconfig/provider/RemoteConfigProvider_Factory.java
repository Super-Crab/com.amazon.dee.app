package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class RemoteConfigProvider_Factory implements Factory<RemoteConfigProvider> {
    private final Provider<Context> contextProvider;

    public RemoteConfigProvider_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static RemoteConfigProvider_Factory create(Provider<Context> provider) {
        return new RemoteConfigProvider_Factory(provider);
    }

    public static RemoteConfigProvider newRemoteConfigProvider(Context context) {
        return new RemoteConfigProvider(context);
    }

    public static RemoteConfigProvider provideInstance(Provider<Context> provider) {
        return new RemoteConfigProvider(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RemoteConfigProvider mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
