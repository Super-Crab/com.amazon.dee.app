package com.amazon.alexa.handsfree.notification.notifiers;

import android.content.Context;
import com.amazon.alexa.handsfree.notification.IntentActionParser;
import com.amazon.alexa.handsfree.notification.api.UVRSettingsProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class VoiceProfileSetupNotifier_Factory implements Factory<VoiceProfileSetupNotifier> {
    private final Provider<Context> contextProvider;
    private final Provider<IntentActionParser> intentActionParserProvider;
    private final Provider<UVRSettingsProvider> uvrSettingsProvider;

    public VoiceProfileSetupNotifier_Factory(Provider<Context> provider, Provider<IntentActionParser> provider2, Provider<UVRSettingsProvider> provider3) {
        this.contextProvider = provider;
        this.intentActionParserProvider = provider2;
        this.uvrSettingsProvider = provider3;
    }

    public static VoiceProfileSetupNotifier_Factory create(Provider<Context> provider, Provider<IntentActionParser> provider2, Provider<UVRSettingsProvider> provider3) {
        return new VoiceProfileSetupNotifier_Factory(provider, provider2, provider3);
    }

    public static VoiceProfileSetupNotifier newVoiceProfileSetupNotifier(Context context, IntentActionParser intentActionParser, UVRSettingsProvider uVRSettingsProvider) {
        return new VoiceProfileSetupNotifier(context, intentActionParser, uVRSettingsProvider);
    }

    public static VoiceProfileSetupNotifier provideInstance(Provider<Context> provider, Provider<IntentActionParser> provider2, Provider<UVRSettingsProvider> provider3) {
        return new VoiceProfileSetupNotifier(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceProfileSetupNotifier mo10268get() {
        return provideInstance(this.contextProvider, this.intentActionParserProvider, this.uvrSettingsProvider);
    }
}
