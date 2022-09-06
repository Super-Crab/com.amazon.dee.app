package com.amazon.alexa.voice.handsfree;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class HandsFreeSetup_Factory implements Factory<HandsFreeSetup> {
    private final Provider<Context> contextProvider;

    public HandsFreeSetup_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static HandsFreeSetup_Factory create(Provider<Context> provider) {
        return new HandsFreeSetup_Factory(provider);
    }

    public static HandsFreeSetup newHandsFreeSetup(Context context) {
        return new HandsFreeSetup(context);
    }

    public static HandsFreeSetup provideInstance(Provider<Context> provider) {
        return new HandsFreeSetup(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HandsFreeSetup mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
