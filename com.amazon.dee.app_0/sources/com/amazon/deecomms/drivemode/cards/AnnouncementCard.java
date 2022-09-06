package com.amazon.deecomms.drivemode.cards;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.drivemode.api.DriveModeCard;
import com.amazon.alexa.drivemode.api.SingleIconCard;
import com.amazon.deecomms.R;
/* loaded from: classes12.dex */
public class AnnouncementCard extends SingleIconCard {
    public AnnouncementCard(@NonNull Context context) {
        super(context.getResources().getString(R.string.annoucement_card_title), context.getResources().getString(R.string.annoucement_card_description), ContextCompat.getDrawable(context, R.drawable.ic_announcement_24dp));
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public DriveModeCard.CardDomain getCardDomain() {
        return DriveModeCard.CardDomain.COMMS;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public int getCardId() {
        return 0;
    }

    @Override // com.amazon.alexa.drivemode.api.SingleIconCard
    public void onCardClicked() {
    }
}
