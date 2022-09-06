package com.amazon.alexa.voice.handsfree.initialization;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceAppProfileRemover_Factory implements Factory<VoiceAppProfileRemover> {
    private final Provider<Context> contextProvider;

    public VoiceAppProfileRemover_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static VoiceAppProfileRemover_Factory create(Provider<Context> provider) {
        return new VoiceAppProfileRemover_Factory(provider);
    }

    public static VoiceAppProfileRemover newVoiceAppProfileRemover(Context context) {
        return new VoiceAppProfileRemover(context);
    }

    public static VoiceAppProfileRemover provideInstance(Provider<Context> provider) {
        return new VoiceAppProfileRemover(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceAppProfileRemover mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
