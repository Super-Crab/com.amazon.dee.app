package com.amazon.alexa.voice.enablement;

import com.amazon.alexa.device.api.DeviceInformation;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class EnablementModule_ProvideVoiceEnablementFactory implements Factory<VoiceEnablement> {
    private final Provider<DeviceInformation> deviceInformationProvider;

    public EnablementModule_ProvideVoiceEnablementFactory(Provider<DeviceInformation> provider) {
        this.deviceInformationProvider = provider;
    }

    public static EnablementModule_ProvideVoiceEnablementFactory create(Provider<DeviceInformation> provider) {
        return new EnablementModule_ProvideVoiceEnablementFactory(provider);
    }

    public static VoiceEnablement provideInstance(Provider<DeviceInformation> provider) {
        return proxyProvideVoiceEnablement(provider.mo10268get());
    }

    public static VoiceEnablement proxyProvideVoiceEnablement(DeviceInformation deviceInformation) {
        return (VoiceEnablement) Preconditions.checkNotNull(EnablementModule.provideVoiceEnablement(deviceInformation), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceEnablement mo10268get() {
        return provideInstance(this.deviceInformationProvider);
    }
}
