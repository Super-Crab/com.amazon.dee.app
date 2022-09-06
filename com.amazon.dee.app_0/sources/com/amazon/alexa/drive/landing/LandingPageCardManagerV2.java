package com.amazon.alexa.drive.landing;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.drive.service.DefaultDriveModeService;
import com.amazon.alexa.drivemode.api.DriveModeCard;
import com.amazon.alexa.drivemode.api.DriveModeCardChangeListener;
import com.amazon.alexa.drivemode.api.DriveModeCardsProvider;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Set;
/* loaded from: classes7.dex */
public final class LandingPageCardManagerV2 {
    private static final String TAG = "LandingPageCardManagerV2";
    private final LandingPageCardAdapterV2 cardAdapter;
    private DriveModeCardChangeListener cardChangeListener = new DriveModeCardChangeListener() { // from class: com.amazon.alexa.drive.landing.LandingPageCardManagerV2.1
        private void addOrUpdateCard(DriveModeCard driveModeCard) {
            int cardPosition = getCardPosition(driveModeCard);
            if (cardPosition == -1) {
                String unused = LandingPageCardManagerV2.TAG;
                LandingPageCardManagerV2.this.cardAdapter.addCard(driveModeCard);
                return;
            }
            String unused2 = LandingPageCardManagerV2.TAG;
            LandingPageCardManagerV2.this.cardAdapter.updateCard(cardPosition, driveModeCard);
        }

        private int getCardPosition(DriveModeCard driveModeCard) {
            String stringId = getStringId(driveModeCard);
            for (int i = 0; i < LandingPageCardManagerV2.this.cardAdapter.cards.size(); i++) {
                if (stringId.equals(getStringId(LandingPageCardManagerV2.this.cardAdapter.cards.get(i)))) {
                    return i;
                }
            }
            return -1;
        }

        private String getStringId(DriveModeCard driveModeCard) {
            return driveModeCard.getCardDomain().name() + "_" + driveModeCard.getCardId();
        }

        @Override // com.amazon.alexa.drivemode.api.DriveModeCardChangeListener
        public void addCard(DriveModeCard driveModeCard) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                addOrUpdateCard(driveModeCard);
                LandingPageCardManagerV2.this.ttcfRecordOnce.markComplete(TTCFRecordOnce.HOME);
                return;
            }
            throw new IllegalStateException("Add card callback should be called on UI thread");
        }

        @Override // com.amazon.alexa.drivemode.api.DriveModeCardChangeListener
        public void removeCard(DriveModeCard driveModeCard) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                String unused = LandingPageCardManagerV2.TAG;
                int cardPosition = getCardPosition(driveModeCard);
                if (cardPosition == -1) {
                    return;
                }
                LandingPageCardManagerV2.this.cardAdapter.removeCard(cardPosition);
                return;
            }
            throw new IllegalStateException("Remove card callback should be called on UI thread");
        }

        @Override // com.amazon.alexa.drivemode.api.DriveModeCardChangeListener
        public void updateCard(DriveModeCard driveModeCard) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                addOrUpdateCard(driveModeCard);
                return;
            }
            throw new IllegalStateException("Update card callback should be called on UI thread");
        }
    };
    private Set<DriveModeCardsProvider> cardsProviders;
    private DriveModeService driveModeService;
    TTCFRecordOnce ttcfRecordOnce;

    public LandingPageCardManagerV2(Context context, TTCFRecordOnce tTCFRecordOnce, DriveModeThemeManager driveModeThemeManager) {
        this.ttcfRecordOnce = tTCFRecordOnce;
        this.cardAdapter = new LandingPageCardAdapterV2(context, driveModeThemeManager);
    }

    public void clearCards() {
        this.cardAdapter.clearCards();
    }

    public LandingPageCardAdapterV2 getAdapter() {
        return this.cardAdapter;
    }

    public void getCards(Set<DriveModeCardsProvider> set) {
        for (DriveModeCardsProvider driveModeCardsProvider : set) {
            driveModeCardsProvider.addCardChangeListener(this.cardChangeListener);
            driveModeCardsProvider.provideCards();
        }
    }

    public void getCardsFromProviders() {
        this.cardsProviders = getProvidersFromDriveModeService();
        Set<DriveModeCardsProvider> set = this.cardsProviders;
        if (set != null) {
            getCards(set);
        } else {
            Log.e(TAG, "Failed to get domain providers...");
        }
    }

    public Set<DriveModeCardsProvider> getProvidersFromDriveModeService() {
        this.driveModeService = (DriveModeService) GeneratedOutlineSupport1.outline21(DriveModeService.class);
        DriveModeService driveModeService = this.driveModeService;
        if (driveModeService != null) {
            return ((DefaultDriveModeService) driveModeService).getProviders();
        }
        Log.e(TAG, "Failed to get drive mode service...");
        return null;
    }

    public void removeCardChangedListenerFromProviders() {
        for (DriveModeCardsProvider driveModeCardsProvider : this.cardsProviders) {
            driveModeCardsProvider.removeCardChangeListener();
        }
    }
}
