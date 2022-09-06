package com.amazon.alexa.redesign.utils.verifier;

import com.amazon.alexa.redesign.entity.AlertBannerModel;
import com.amazon.alexa.redesign.entity.CardModel;
import java.util.List;
/* loaded from: classes10.dex */
public final class Verifier {
    private Verifier() {
    }

    public static boolean hasOutageAlert(List<CardModel> list) {
        for (CardModel cardModel : list) {
            if (cardModel instanceof AlertBannerModel) {
                return true;
            }
        }
        return false;
    }

    public static void verify(List<CardModel> list, List<ConditionCallback> list2) {
        for (CardModel cardModel : list) {
            for (ConditionCallback conditionCallback : list2) {
                if (conditionCallback.isTrueWhen(cardModel)) {
                    conditionCallback.doThis(cardModel);
                }
            }
        }
        for (ConditionCallback conditionCallback2 : list2) {
            conditionCallback2.onComplete();
        }
    }
}
