package com.amazon.alexa.voice.tta.typing;

import android.content.Context;
import com.amazon.alexa.voice.tta.sdk.AlexaRenderedCardReceiver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypingModule_ProvidesAlexaRenderedCardReceiverFactory implements Factory<AlexaRenderedCardReceiver> {
    private final Provider<Context> contextProvider;
    private final TypingModule module;

    public TypingModule_ProvidesAlexaRenderedCardReceiverFactory(TypingModule typingModule, Provider<Context> provider) {
        this.module = typingModule;
        this.contextProvider = provider;
    }

    public static TypingModule_ProvidesAlexaRenderedCardReceiverFactory create(TypingModule typingModule, Provider<Context> provider) {
        return new TypingModule_ProvidesAlexaRenderedCardReceiverFactory(typingModule, provider);
    }

    public static AlexaRenderedCardReceiver provideInstance(TypingModule typingModule, Provider<Context> provider) {
        return proxyProvidesAlexaRenderedCardReceiver(typingModule, provider.mo10268get());
    }

    public static AlexaRenderedCardReceiver proxyProvidesAlexaRenderedCardReceiver(TypingModule typingModule, Context context) {
        return (AlexaRenderedCardReceiver) Preconditions.checkNotNull(typingModule.providesAlexaRenderedCardReceiver(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaRenderedCardReceiver mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
