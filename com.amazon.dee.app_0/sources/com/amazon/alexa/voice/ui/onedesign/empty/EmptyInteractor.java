package com.amazon.alexa.voice.ui.onedesign.empty;

import android.util.Log;
import com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract;
import com.amazon.alexa.voice.ui.onedesign.list.ListType;
import com.amazon.alexa.voice.ui.routing.Destination;
/* loaded from: classes11.dex */
public final class EmptyInteractor implements EmptyContract.Interactor {
    private static final String TAG = "EmptyInteractor";
    private final EmptyCard card;
    private final EmptyContract.Mediator mediator;

    public EmptyInteractor(EmptyCard emptyCard, EmptyContract.Mediator mediator) {
        this.card = emptyCard;
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.Interactor
    public void dismiss() {
        this.mediator.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.Interactor
    public EmptyCard getCard() {
        return this.card;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.Interactor
    public void navigateToManageDestination() {
        String manageButtonDestination = this.card.getManageButtonDestination();
        if (manageButtonDestination == null) {
            Log.e(TAG, "navigateToManageDestination was called but manageButtonDestination is null");
        } else if (manageButtonDestination.equals(Destination.LISTS)) {
            this.mediator.navigateToList(ListType.from(this.card.getType()));
        } else {
            this.mediator.navigateTo(manageButtonDestination);
        }
    }
}
