package com.amazon.alexa.voice.wakeword;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class WakeWordModule_ProvideWakewordPreferenceFactory implements Factory<WakewordPreference> {
    private final Provider<Context> applicationContextProvider;

    public WakeWordModule_ProvideWakewordPreferenceFactory(Provider<Context> provider) {
        this.applicationContextProvider = provider;
    }

    public static WakeWordModule_ProvideWakewordPreferenceFactory create(Provider<Context> provider) {
        return new WakeWordModule_ProvideWakewordPreferenceFactory(provider);
    }

    public static WakewordPreference provideInstance(Provider<Context> provider) {
        return proxyProvideWakewordPreference(provider.mo10268get());
    }

    public static WakewordPreference proxyProvideWakewordPreference(Context context) {
        return (WakewordPreference) Preconditions.checkNotNull(WakeWordModule.provideWakewordPreference(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WakewordPreference mo10268get() {
        return provideInstance(this.applicationContextProvider);
    }
}
