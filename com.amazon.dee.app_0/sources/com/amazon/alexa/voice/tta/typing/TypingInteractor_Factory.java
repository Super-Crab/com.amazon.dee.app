package com.amazon.alexa.voice.tta.typing;

import android.app.Activity;
import com.amazon.alexa.voice.tta.features.FeatureChecker;
import com.amazon.alexa.voice.tta.metrics.MetricEventProcessingService;
import com.amazon.alexa.voice.tta.typing.IngressParameters;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypingInteractor_Factory implements Factory<TypingInteractor> {
    private final Provider<Activity> activityProvider;
    private final Provider<DefaultTtaMessageHandler> defaultTtaMessageHandlerProvider;
    private final Provider<MetricEventProcessingService> eventProcessingServiceProvider;
    private final Provider<FeatureChecker> featureCheckerProvider;
    private final Provider<IngressParameters.Provider> ingressParametersProvider;
    private final Provider<MessageHistoryManager> messageHistoryManagerProvider;
    private final Provider<TypingModel> typingModelProvider;

    public TypingInteractor_Factory(Provider<Activity> provider, Provider<TypingModel> provider2, Provider<DefaultTtaMessageHandler> provider3, Provider<MessageHistoryManager> provider4, Provider<MetricEventProcessingService> provider5, Provider<IngressParameters.Provider> provider6, Provider<FeatureChecker> provider7) {
        this.activityProvider = provider;
        this.typingModelProvider = provider2;
        this.defaultTtaMessageHandlerProvider = provider3;
        this.messageHistoryManagerProvider = provider4;
        this.eventProcessingServiceProvider = provider5;
        this.ingressParametersProvider = provider6;
        this.featureCheckerProvider = provider7;
    }

    public static TypingInteractor_Factory create(Provider<Activity> provider, Provider<TypingModel> provider2, Provider<DefaultTtaMessageHandler> provider3, Provider<MessageHistoryManager> provider4, Provider<MetricEventProcessingService> provider5, Provider<IngressParameters.Provider> provider6, Provider<FeatureChecker> provider7) {
        return new TypingInteractor_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static TypingInteractor newTypingInteractor(Activity activity, TypingModel typingModel, Object obj, MessageHistoryManager messageHistoryManager, MetricEventProcessingService metricEventProcessingService, IngressParameters.Provider provider, FeatureChecker featureChecker) {
        return new TypingInteractor(activity, typingModel, (DefaultTtaMessageHandler) obj, messageHistoryManager, metricEventProcessingService, provider, featureChecker);
    }

    public static TypingInteractor provideInstance(Provider<Activity> provider, Provider<TypingModel> provider2, Provider<DefaultTtaMessageHandler> provider3, Provider<MessageHistoryManager> provider4, Provider<MetricEventProcessingService> provider5, Provider<IngressParameters.Provider> provider6, Provider<FeatureChecker> provider7) {
        return new TypingInteractor(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TypingInteractor mo10268get() {
        return provideInstance(this.activityProvider, this.typingModelProvider, this.defaultTtaMessageHandlerProvider, this.messageHistoryManagerProvider, this.eventProcessingServiceProvider, this.ingressParametersProvider, this.featureCheckerProvider);
    }
}
