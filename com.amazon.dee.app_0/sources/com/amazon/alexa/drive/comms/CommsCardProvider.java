package com.amazon.alexa.drive.comms;

import android.content.Context;
import android.util.Log;
import android.view.ContextThemeWrapper;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.comms.contract.CommsLandingPageContract;
import com.amazon.alexa.drivemode.api.DefaultDriveModeCardsProvider;
import com.amazon.alexa.drivemode.api.DriveModeCard;
import com.amazon.alexa.drivemode.api.DriveModeCardChangeListener;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.drivemode.api.SingleIconCard;
/* loaded from: classes7.dex */
public class CommsCardProvider extends DefaultDriveModeCardsProvider {
    private static final String TAG = "CommsCardProvider";
    private SingleIconCard mCallingCard;
    private CommsLandingPageContract.Interactor mCommsLandingPageInteractor;
    private Context mContext;
    private DriveModeThemeManager mDriveModeThemeManager;
    private boolean mIsCommsCardAdded;

    public CommsCardProvider(Context context, CommsLandingPageContract.Interactor interactor, DriveModeThemeManager driveModeThemeManager) {
        this.mContext = context;
        this.mCommsLandingPageInteractor = interactor;
        this.mDriveModeThemeManager = driveModeThemeManager;
    }

    SingleIconCard createCallingCard() {
        Log.i(TAG, "getting new calling card");
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, this.mDriveModeThemeManager.getTheme());
        return new SingleIconCard(this.mContext.getResources().getString(R.string.dm_landing_page_comms_card_title), "", contextThemeWrapper.getResources().getDrawable(R.drawable.dm_ic_call, contextThemeWrapper.getTheme())) { // from class: com.amazon.alexa.drive.comms.CommsCardProvider.1
            @Override // com.amazon.alexa.drivemode.api.DriveModeCard
            public DriveModeCard.CardDomain getCardDomain() {
                return DriveModeCard.CardDomain.COMMS;
            }

            @Override // com.amazon.alexa.drivemode.api.DriveModeCard
            public int getCardId() {
                return 1;
            }

            @Override // com.amazon.alexa.drivemode.api.SingleIconCard
            public void onCardClicked() {
                Log.i(CommsCardProvider.TAG, "calling card clicked");
                CommsCardProvider.this.mCommsLandingPageInteractor.onCallCardClick();
            }
        };
    }

    SingleIconCard getCallingCard() {
        return this.mCallingCard;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCardsProvider
    public void provideCards() {
        Log.i(TAG, "provideCards");
        DriveModeCardChangeListener cardChangeListener = getCardChangeListener();
        if (cardChangeListener != null) {
            if (getCallingCard() == null) {
                this.mCallingCard = createCallingCard();
            }
            if (this.mIsCommsCardAdded) {
                cardChangeListener.updateCard(this.mCallingCard);
                return;
            }
            cardChangeListener.addCard(this.mCallingCard);
            this.mIsCommsCardAdded = true;
        }
    }
}
