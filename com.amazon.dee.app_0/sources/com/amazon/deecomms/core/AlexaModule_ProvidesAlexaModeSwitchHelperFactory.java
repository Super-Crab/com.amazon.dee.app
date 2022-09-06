package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.alexa.ModeSwitchHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AlexaModule_ProvidesAlexaModeSwitchHelperFactory implements Factory<ModeSwitchHelper> {
    private final Provider<Context> contextProvider;
    private final AlexaModule module;

    public AlexaModule_ProvidesAlexaModeSwitchHelperFactory(AlexaModule alexaModule, Provider<Context> provider) {
        this.module = alexaModule;
        this.contextProvider = provider;
    }

    public static AlexaModule_ProvidesAlexaModeSwitchHelperFactory create(AlexaModule alexaModule, Provider<Context> provider) {
        return new AlexaModule_ProvidesAlexaModeSwitchHelperFactory(alexaModule, provider);
    }

    public static ModeSwitchHelper provideInstance(AlexaModule alexaModule, Provider<Context> provider) {
        return (ModeSwitchHelper) Preconditions.checkNotNull(alexaModule.providesAlexaModeSwitchHelper(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ModeSwitchHelper proxyProvidesAlexaModeSwitchHelper(AlexaModule alexaModule, Context context) {
        return (ModeSwitchHelper) Preconditions.checkNotNull(alexaModule.providesAlexaModeSwitchHelper(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ModeSwitchHelper mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
