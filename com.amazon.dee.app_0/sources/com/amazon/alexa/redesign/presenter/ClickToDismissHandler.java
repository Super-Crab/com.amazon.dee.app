package com.amazon.alexa.redesign.presenter;

import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pair;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.DismissIdentifier;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.alexa.redesign.utils.DismissIdBuilder;
/* loaded from: classes10.dex */
public class ClickToDismissHandler {
    @VisibleForTesting
    final HomeContract.Interactor interactor;
    @VisibleForTesting
    final HomeContract.View view;

    public ClickToDismissHandler(HomeContract.View view, HomeContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleClickToDismiss(int i, CardModel cardModel) {
        if (cardModel.getClickToDismiss()) {
            String dismissCardId = cardModel.getDismissCardId();
            this.view.removeCardAt(i);
            this.interactor.saveDismissedCardId(new DismissIdentifier(System.currentTimeMillis(), dismissCardId), Constants.KEY_CLICK_TO_DISMISS_CARDS);
        }
    }

    public void handleClickToDismissForCustomCards(String str, String str2, String str3) {
        Integer num;
        Pair<Integer, CardModel> findDataByDismissId = this.view.findDataByDismissId(DismissIdBuilder.buildDismissId(str, str2, str3));
        if (findDataByDismissId == null || (num = findDataByDismissId.first) == null || findDataByDismissId.second == null) {
            return;
        }
        handleClickToDismiss(num.intValue(), findDataByDismissId.second);
    }
}
