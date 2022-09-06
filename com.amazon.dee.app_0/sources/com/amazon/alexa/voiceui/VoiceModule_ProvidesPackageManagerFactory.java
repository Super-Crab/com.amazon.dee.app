package com.amazon.alexa.voiceui;

import android.content.Context;
import android.content.pm.PackageManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesPackageManagerFactory implements Factory<PackageManager> {
    private final Provider<Context> contextProvider;
    private final VoiceModule module;

    public VoiceModule_ProvidesPackageManagerFactory(VoiceModule voiceModule, Provider<Context> provider) {
        this.module = voiceModule;
        this.contextProvider = provider;
    }

    public static VoiceModule_ProvidesPackageManagerFactory create(VoiceModule voiceModule, Provider<Context> provider) {
        return new VoiceModule_ProvidesPackageManagerFactory(voiceModule, provider);
    }

    public static PackageManager provideInstance(VoiceModule voiceModule, Provider<Context> provider) {
        return proxyProvidesPackageManager(voiceModule, provider.mo10268get());
    }

    public static PackageManager proxyProvidesPackageManager(VoiceModule voiceModule, Context context) {
        return (PackageManager) Preconditions.checkNotNull(voiceModule.providesPackageManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PackageManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
