package com.amazon.alexa.voice.tta.typing;

import android.content.Context;
import com.amazon.alexa.voice.tta.statemachine.AlexaMediator;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypingModel_Factory implements Factory<TypingModel> {
    private final Provider<AlexaMediator> alexaMediatorProvider;
    private final Provider<Context> contextProvider;
    private final Provider<MessageHistoryManager> messageHistoryManagerProvider;

    public TypingModel_Factory(Provider<AlexaMediator> provider, Provider<MessageHistoryManager> provider2, Provider<Context> provider3) {
        this.alexaMediatorProvider = provider;
        this.messageHistoryManagerProvider = provider2;
        this.contextProvider = provider3;
    }

    public static TypingModel_Factory create(Provider<AlexaMediator> provider, Provider<MessageHistoryManager> provider2, Provider<Context> provider3) {
        return new TypingModel_Factory(provider, provider2, provider3);
    }

    public static TypingModel newTypingModel(AlexaMediator alexaMediator, MessageHistoryManager messageHistoryManager, Context context) {
        return new TypingModel(alexaMediator, messageHistoryManager, context);
    }

    public static TypingModel provideInstance(Provider<AlexaMediator> provider, Provider<MessageHistoryManager> provider2, Provider<Context> provider3) {
        return new TypingModel(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TypingModel mo10268get() {
        return provideInstance(this.alexaMediatorProvider, this.messageHistoryManagerProvider, this.contextProvider);
    }
}
