package com.amazon.alexa.voiceui.cards;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaCardExtras;
import com.amazon.alexa.api.AlexaCardListener;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.voiceui.AlexaServicesApis;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
/* JADX INFO: Access modifiers changed from: package-private */
@Singleton
/* loaded from: classes11.dex */
public class CardModel {
    private final AlexaServicesApis alexaServicesApis;
    private final DefaultLocaleAuthority localeAuthority;
    private final DefaultMarketplaceAuthority marketplaceAuthority;
    private final List<CardListener> cardListeners = new ArrayList();
    @VisibleForTesting
    final ConnectionListener connectionListener = new ConnectionListener();
    @VisibleForTesting
    final InternalCardListener cardListener = new InternalCardListener();

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes11.dex */
    public class ConnectionListener implements AlexaServicesConnection.ConnectionListener {
        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            CardModel.this.alexaServicesApis.registerCardListener(CardModel.this.cardListener);
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
            CardModel.this.alexaServicesApis.deregisterCardListener(CardModel.this.cardListener);
        }

        private ConnectionListener() {
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class InternalCardListener implements AlexaCardListener {
        @Override // com.amazon.alexa.api.AlexaCardListener
        public void onReceivedRenderCard(@NonNull String str, @NonNull AlexaCardExtras alexaCardExtras) {
            CardModel.this.marketplaceAuthority.setMarketplace(alexaCardExtras.getMarketplace());
            CardModel.this.localeAuthority.setLocale(alexaCardExtras.getLocale());
            synchronized (CardModel.this.cardListeners) {
                for (CardListener cardListener : CardModel.this.cardListeners) {
                    cardListener.onAlexaCard(str);
                }
            }
        }

        private InternalCardListener() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public CardModel(AlexaServicesApis alexaServicesApis, DefaultMarketplaceAuthority defaultMarketplaceAuthority, DefaultLocaleAuthority defaultLocaleAuthority) {
        this.alexaServicesApis = alexaServicesApis;
        this.marketplaceAuthority = defaultMarketplaceAuthority;
        this.localeAuthority = defaultLocaleAuthority;
    }

    public void addCardListener(CardListener cardListener) {
        synchronized (this.cardListeners) {
            this.cardListeners.add(cardListener);
        }
    }

    public void initialize() {
        this.alexaServicesApis.registerConnectionListener(this.connectionListener);
    }

    public void release() {
        this.alexaServicesApis.deregisterConnectionListener(this.connectionListener);
    }

    public void removeCardListener(CardListener cardListener) {
        synchronized (this.cardListeners) {
            this.cardListeners.remove(cardListener);
        }
    }
}
