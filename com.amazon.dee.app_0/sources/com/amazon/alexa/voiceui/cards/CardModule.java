package com.amazon.alexa.voiceui.cards;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import com.amazon.alexa.voice.ui.cards.CardCreationEventListener;
import com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeCardMetricsInteractor;
import com.amazon.alexa.voice.ui.locale.GetLocaleAuthority;
import com.amazon.alexa.voice.ui.marketplace.MarketplaceAuthority;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.provider.CardCreationEventListenerProvider;
import com.amazon.alexa.voice.ui.provider.CardFactoryWithMode;
import com.amazon.alexa.voice.ui.provider.CardRendererControllerFactoryWithMode;
import com.amazon.alexa.voiceui.BackButtonPressHandler;
import com.amazon.alexa.voiceui.DefaultBackButtonPressHandler;
import com.amazon.alexa.voiceui.DefaultSaveInstanceStateHandler;
import com.amazon.alexa.voiceui.R;
import com.amazon.alexa.voiceui.RouterDelegate;
import com.amazon.alexa.voiceui.SaveInstanceStateHandler;
import com.amazon.alexa.voiceui.accessibility.AccessibilityDelegate;
import com.amazon.alexa.voiceui.accessibility.DefaultAccessibilityDelegate;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public class CardModule {
    static final String BACK_PRESS_HANDLER_CARDS = "cardsBackPress";
    private static final String CARDS_CONTAINER = "cards";
    static final String CARDS_ROUTER = "cardsRouter";
    static final String ROUTER_DELEGATE_CARDS = "cards";
    static final String SAVE_INSTANCE_STATE_HANDLER_CARDS = "cardsSaveHandler";
    private final Activity activity;
    private final Bundle savedInstanceState;

    public CardModule(Activity activity, Bundle bundle) {
        this.activity = activity;
        this.savedInstanceState = bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AccessibilityDelegate providesAccessibilityDelegate(@Named("cards") ViewGroup viewGroup) {
        return new DefaultAccessibilityDelegate(viewGroup);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named(BACK_PRESS_HANDLER_CARDS)
    public BackButtonPressHandler providesBackButtonPressHandler(@Named("cards") RouterDelegate routerDelegate) {
        return new DefaultBackButtonPressHandler(routerDelegate);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public CardCreationEventListenerProvider providesCardCreationEventListenerProvider(StandardCardCreationEventListener standardCardCreationEventListener, DriveModeCardCreationEventListener driveModeCardCreationEventListener) {
        return new DefaultCardCreationEventListenerProvider(standardCardCreationEventListener, driveModeCardCreationEventListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public CardFactoryWithMode providesCardFactoryWithMode(CardCreationEventListenerProvider cardCreationEventListenerProvider) {
        return new CardRendererControllerFactoryWithMode(cardCreationEventListenerProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public CardMetricsInteractor providesCardMetricsInteractor(StandardCardMetricsInteractor standardCardMetricsInteractor) {
        return standardCardMetricsInteractor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named("cards")
    public ViewGroup providesCardsContainer() {
        return (ViewGroup) this.activity.findViewById(R.id.cards_container);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named(CARDS_ROUTER)
    public Router providesCardsRouter(@Named("cards") ViewGroup viewGroup, Component component) {
        Activity activity = this.activity;
        Bundle bundle = this.savedInstanceState;
        Router router = new Router(activity, component, bundle != null ? (Bundle) bundle.getParcelable(CARDS_ROUTER) : null);
        router.attach(viewGroup);
        return router;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named("cards")
    public RouterDelegate providesCardsRouterDelegate(@Named("cardsRouter") Router router) {
        return new RouterDelegate(router);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public CardCreationEventListener providesDriveModeCardCreationEventListener(DriveModeCardCreationEventListener driveModeCardCreationEventListener) {
        return driveModeCardCreationEventListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public DriveModeCardMetricsInteractor providesDriveModeCardMetricsInteractor(DriveModeCardMetricsInteractorImpl driveModeCardMetricsInteractorImpl) {
        return driveModeCardMetricsInteractorImpl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public GetLocaleAuthority providesGetLocaleAuthority(DefaultLocaleAuthority defaultLocaleAuthority) {
        return defaultLocaleAuthority;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public MarketplaceAuthority providesMarketplaceAuthority(DefaultMarketplaceAuthority defaultMarketplaceAuthority) {
        return defaultMarketplaceAuthority;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named(SAVE_INSTANCE_STATE_HANDLER_CARDS)
    public SaveInstanceStateHandler providesSaveInstanceStateHandler(@Named("cards") RouterDelegate routerDelegate) {
        return new DefaultSaveInstanceStateHandler(routerDelegate);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public CardCreationEventListener providesStandardCardCreationEventListener(StandardCardCreationEventListener standardCardCreationEventListener) {
        return standardCardCreationEventListener;
    }
}
