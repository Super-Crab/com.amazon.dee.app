package com.amazon.deecomms.calling.telecom.audiomanagement;

import android.media.AudioManager;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class TelecomCallAudioManager_Factory implements Factory<TelecomCallAudioManager> {
    private final Provider<AudioManager> audioManagerProvider;
    private final Provider<TelecomBridge> telecomBridgeProvider;

    public TelecomCallAudioManager_Factory(Provider<TelecomBridge> provider, Provider<AudioManager> provider2) {
        this.telecomBridgeProvider = provider;
        this.audioManagerProvider = provider2;
    }

    public static TelecomCallAudioManager_Factory create(Provider<TelecomBridge> provider, Provider<AudioManager> provider2) {
        return new TelecomCallAudioManager_Factory(provider, provider2);
    }

    public static TelecomCallAudioManager newTelecomCallAudioManager(TelecomBridge telecomBridge, AudioManager audioManager) {
        return new TelecomCallAudioManager(telecomBridge, audioManager);
    }

    public static TelecomCallAudioManager provideInstance(Provider<TelecomBridge> provider, Provider<AudioManager> provider2) {
        return new TelecomCallAudioManager(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TelecomCallAudioManager mo10268get() {
        return provideInstance(this.telecomBridgeProvider, this.audioManagerProvider);
    }
}
