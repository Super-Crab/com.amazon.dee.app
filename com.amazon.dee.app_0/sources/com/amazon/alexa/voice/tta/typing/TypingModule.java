package com.amazon.alexa.voice.tta.typing;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import com.amazon.alexa.voice.tta.R;
import com.amazon.alexa.voice.tta.RouterDelegate;
import com.amazon.alexa.voice.tta.metrics.EventClock;
import com.amazon.alexa.voice.tta.metrics.MetricEventProcessingService;
import com.amazon.alexa.voice.tta.sdk.AlexaRenderedCardReceiver;
import com.amazon.alexa.voice.tta.search.SearchInteractor;
import com.amazon.alexa.voice.tta.suggestions.TtaSuggestionsInteractor;
import com.amazon.alexa.voice.tta.typing.IngressParameters;
import com.amazon.alexa.voice.ui.tta.TtaMessageHandler;
import com.amazon.alexa.voice.ui.tta.TtaNavigator;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public class TypingModule {
    private static final String TYPING_CONTAINER = "typingContainer";
    private static final String TYPING_ROUTER = "typingRouter";
    static final String TYPING_ROUTER_DELEGATE = "typingRouterDelegate";
    private final Activity activity;
    private final Bundle savedInstanceState;

    public TypingModule(Activity activity, Bundle bundle) {
        this.activity = activity;
        this.savedInstanceState = bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public AlexaRenderedCardReceiver providesAlexaRenderedCardReceiver(Context context) {
        return new AlexaRenderedCardReceiver(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public ConversationSessionTimer providesConversationSessionTimer() {
        return new ConversationSessionTimer();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public DefaultTtaMessageHandler providesDefaultTtaMessageHandler(TtaMessageSanitizer ttaMessageSanitizer) {
        return new DefaultTtaMessageHandler(ttaMessageSanitizer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public IngressParameters.Provider providesIngressParametersProvider(EventClock eventClock) {
        return new DefaultIngressParametersProvider(eventClock);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public MessageHistoryManager providesMessageHistoryManager(Context context, PersistentStorage persistentStorage, ConversationSessionTimer conversationSessionTimer, MetricEventProcessingService metricEventProcessingService) {
        return new MessageHistoryManager(context, persistentStorage, conversationSessionTimer, metricEventProcessingService);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public PersistentStorage providesPersistentStorage(Context context) {
        return new PersistentStorage(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public TtaInteractorProvider providesTtaInteractorProvider(TypingInteractor typingInteractor, SearchInteractor searchInteractor, TtaSuggestionsInteractor ttaSuggestionsInteractor) {
        return new TtaInteractorProvider(typingInteractor, searchInteractor, ttaSuggestionsInteractor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public TtaMessageHandler providesTtaMessageHandler(DefaultTtaMessageHandler defaultTtaMessageHandler) {
        return defaultTtaMessageHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public TtaMessageSanitizer providesTtaMessageSanitizer() {
        return new TtaMessageSanitizer();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public TtaNavigator providesTtaNavigator() {
        return new DefaultTtaNavigator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named(TYPING_CONTAINER)
    public ViewGroup providesTypingContainer() {
        return (ViewGroup) this.activity.findViewById(R.id.typing_view_container);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named(TYPING_ROUTER)
    public Router providesTypingRouter(@Named("typingContainer") ViewGroup viewGroup, Component component) {
        Activity activity = this.activity;
        Bundle bundle = this.savedInstanceState;
        Router router = new Router(activity, component, bundle != null ? (Bundle) bundle.getParcelable(TYPING_ROUTER) : null);
        router.attach(viewGroup);
        return router;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named(TYPING_ROUTER_DELEGATE)
    public RouterDelegate providesTypingRouterDelegate(@Named("typingRouter") Router router) {
        return new RouterDelegate(router);
    }
}
