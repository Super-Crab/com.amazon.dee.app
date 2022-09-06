package com.amazon.alexa.voice.ui.provider;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.cards.CardCreationEventListener;
import com.amazon.alexa.voice.ui.cards.CardFactory;
import com.amazon.alexa.voice.ui.cards.StandardModeCardRendererControllerFactory;
import com.amazon.alexa.voice.ui.driveMode.DriveModeCardRendererControllerFactory;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public class CardRendererControllerFactoryWithMode implements CardFactoryWithMode {
    private CardCreationEventListenerProvider cardCreationEventListenerProvider;
    private DriveModeCardRendererControllerFactory driveModeFactory;
    private StandardModeCardRendererControllerFactory standardModeFactory;

    public CardRendererControllerFactoryWithMode(CardCreationEventListenerProvider cardCreationEventListenerProvider) {
        this.cardCreationEventListenerProvider = cardCreationEventListenerProvider;
    }

    static /* synthetic */ CardCreationEventListener lambda$new$0(CardCreationEventListener cardCreationEventListener, CardMode cardMode) {
        return cardCreationEventListener;
    }

    @Override // com.amazon.alexa.voice.ui.provider.CardFactoryWithMode
    @Deprecated
    public ViewController buildCard(@Nullable String str, CardMode cardMode) {
        return buildCard(str, cardMode, 0);
    }

    @VisibleForTesting
    CardFactory getDriveModeFactory(int i) {
        if (this.driveModeFactory == null) {
            this.driveModeFactory = new DriveModeCardRendererControllerFactory(i, this.cardCreationEventListenerProvider.getCreationEventListener(CardMode.DRIVE_MODE));
        }
        return this.driveModeFactory;
    }

    @VisibleForTesting
    CardFactory getStandardModeFactory() {
        if (this.standardModeFactory == null) {
            this.standardModeFactory = new StandardModeCardRendererControllerFactory(this.cardCreationEventListenerProvider.getCreationEventListener(CardMode.STANDARD));
        }
        return this.standardModeFactory;
    }

    @Override // com.amazon.alexa.voice.ui.provider.CardFactoryWithMode
    public ViewController buildCard(@Nullable String str, CardMode cardMode, int i) {
        CardFactory standardModeFactory;
        if (cardMode == CardMode.DRIVE_MODE) {
            standardModeFactory = getDriveModeFactory(i);
        } else {
            standardModeFactory = getStandardModeFactory();
        }
        return standardModeFactory.buildCard(str);
    }

    @Deprecated
    public CardRendererControllerFactoryWithMode(final CardCreationEventListener cardCreationEventListener) {
        this(new CardCreationEventListenerProvider() { // from class: com.amazon.alexa.voice.ui.provider.-$$Lambda$CardRendererControllerFactoryWithMode$cI50USrDc7fH86SPU_3n7xJxs6A
            @Override // com.amazon.alexa.voice.ui.provider.CardCreationEventListenerProvider
            public final CardCreationEventListener getCreationEventListener(CardMode cardMode) {
                return CardCreationEventListener.this;
            }
        });
    }

    @VisibleForTesting
    CardRendererControllerFactoryWithMode(CardCreationEventListenerProvider cardCreationEventListenerProvider, StandardModeCardRendererControllerFactory standardModeCardRendererControllerFactory, DriveModeCardRendererControllerFactory driveModeCardRendererControllerFactory) {
        this.cardCreationEventListenerProvider = cardCreationEventListenerProvider;
        this.standardModeFactory = standardModeCardRendererControllerFactory;
        this.driveModeFactory = driveModeCardRendererControllerFactory;
    }
}
