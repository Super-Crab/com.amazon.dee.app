package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class HandsFreeStateProvider_Factory implements Factory<HandsFreeStateProvider> {
    private final Provider<Context> contextProvider;

    public HandsFreeStateProvider_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static HandsFreeStateProvider_Factory create(Provider<Context> provider) {
        return new HandsFreeStateProvider_Factory(provider);
    }

    public static HandsFreeStateProvider newHandsFreeStateProvider(Context context) {
        return new HandsFreeStateProvider(context);
    }

    public static HandsFreeStateProvider provideInstance(Provider<Context> provider) {
        return new HandsFreeStateProvider(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HandsFreeStateProvider mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
