package com.amazon.alexa.voice.tta.typing;

import android.content.Context;
import com.amazon.alexa.voice.tta.metrics.MetricEventProcessingService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypingModule_ProvidesMessageHistoryManagerFactory implements Factory<MessageHistoryManager> {
    private final Provider<Context> contextProvider;
    private final Provider<ConversationSessionTimer> conversationSessionTimerProvider;
    private final Provider<MetricEventProcessingService> eventProcessingServiceProvider;
    private final TypingModule module;
    private final Provider<PersistentStorage> persistentStorageProvider;

    public TypingModule_ProvidesMessageHistoryManagerFactory(TypingModule typingModule, Provider<Context> provider, Provider<PersistentStorage> provider2, Provider<ConversationSessionTimer> provider3, Provider<MetricEventProcessingService> provider4) {
        this.module = typingModule;
        this.contextProvider = provider;
        this.persistentStorageProvider = provider2;
        this.conversationSessionTimerProvider = provider3;
        this.eventProcessingServiceProvider = provider4;
    }

    public static TypingModule_ProvidesMessageHistoryManagerFactory create(TypingModule typingModule, Provider<Context> provider, Provider<PersistentStorage> provider2, Provider<ConversationSessionTimer> provider3, Provider<MetricEventProcessingService> provider4) {
        return new TypingModule_ProvidesMessageHistoryManagerFactory(typingModule, provider, provider2, provider3, provider4);
    }

    public static MessageHistoryManager provideInstance(TypingModule typingModule, Provider<Context> provider, Provider<PersistentStorage> provider2, Provider<ConversationSessionTimer> provider3, Provider<MetricEventProcessingService> provider4) {
        return proxyProvidesMessageHistoryManager(typingModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static MessageHistoryManager proxyProvidesMessageHistoryManager(TypingModule typingModule, Context context, PersistentStorage persistentStorage, ConversationSessionTimer conversationSessionTimer, MetricEventProcessingService metricEventProcessingService) {
        return (MessageHistoryManager) Preconditions.checkNotNull(typingModule.providesMessageHistoryManager(context, persistentStorage, conversationSessionTimer, metricEventProcessingService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessageHistoryManager mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.persistentStorageProvider, this.conversationSessionTimerProvider, this.eventProcessingServiceProvider);
    }
}
