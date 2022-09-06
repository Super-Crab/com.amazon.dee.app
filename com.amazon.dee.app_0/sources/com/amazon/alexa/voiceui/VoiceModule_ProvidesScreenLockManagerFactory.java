package com.amazon.alexa.voiceui;

import android.os.PowerManager;
import com.amazon.alexa.voice.ui.speech.AttentionSystemContract;
import com.amazon.alexa.voiceui.driveMode.DriveModeStateProvider;
import com.amazon.alexa.voiceui.screen.ScreenLockManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesScreenLockManagerFactory implements Factory<ScreenLockManager> {
    private final Provider<AttentionSystemContract> attentionSystemContractProvider;
    private final Provider<DriveModeStateProvider> driveModeStateProvider;
    private final VoiceModule module;
    private final Provider<PowerManager> powerManagerProvider;

    public VoiceModule_ProvidesScreenLockManagerFactory(VoiceModule voiceModule, Provider<PowerManager> provider, Provider<AttentionSystemContract> provider2, Provider<DriveModeStateProvider> provider3) {
        this.module = voiceModule;
        this.powerManagerProvider = provider;
        this.attentionSystemContractProvider = provider2;
        this.driveModeStateProvider = provider3;
    }

    public static VoiceModule_ProvidesScreenLockManagerFactory create(VoiceModule voiceModule, Provider<PowerManager> provider, Provider<AttentionSystemContract> provider2, Provider<DriveModeStateProvider> provider3) {
        return new VoiceModule_ProvidesScreenLockManagerFactory(voiceModule, provider, provider2, provider3);
    }

    public static ScreenLockManager provideInstance(VoiceModule voiceModule, Provider<PowerManager> provider, Provider<AttentionSystemContract> provider2, Provider<DriveModeStateProvider> provider3) {
        return proxyProvidesScreenLockManager(voiceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static ScreenLockManager proxyProvidesScreenLockManager(VoiceModule voiceModule, PowerManager powerManager, AttentionSystemContract attentionSystemContract, DriveModeStateProvider driveModeStateProvider) {
        return (ScreenLockManager) Preconditions.checkNotNull(voiceModule.providesScreenLockManager(powerManager, attentionSystemContract, driveModeStateProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ScreenLockManager mo10268get() {
        return provideInstance(this.module, this.powerManagerProvider, this.attentionSystemContractProvider, this.driveModeStateProvider);
    }
}
