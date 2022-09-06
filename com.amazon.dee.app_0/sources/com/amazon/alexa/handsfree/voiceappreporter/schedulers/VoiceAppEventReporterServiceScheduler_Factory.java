package com.amazon.alexa.handsfree.voiceappreporter.schedulers;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class VoiceAppEventReporterServiceScheduler_Factory implements Factory<VoiceAppEventReporterServiceScheduler> {
    private final Provider<Context> contextProvider;

    public VoiceAppEventReporterServiceScheduler_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static VoiceAppEventReporterServiceScheduler_Factory create(Provider<Context> provider) {
        return new VoiceAppEventReporterServiceScheduler_Factory(provider);
    }

    public static VoiceAppEventReporterServiceScheduler newVoiceAppEventReporterServiceScheduler(Context context) {
        return new VoiceAppEventReporterServiceScheduler(context);
    }

    public static VoiceAppEventReporterServiceScheduler provideInstance(Provider<Context> provider) {
        return new VoiceAppEventReporterServiceScheduler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceAppEventReporterServiceScheduler mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
