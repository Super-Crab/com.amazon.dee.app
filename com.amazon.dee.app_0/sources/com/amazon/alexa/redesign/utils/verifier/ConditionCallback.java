package com.amazon.alexa.redesign.utils.verifier;

import com.amazon.alexa.redesign.entity.CardModel;
/* loaded from: classes10.dex */
public interface ConditionCallback {
    void doThis(CardModel cardModel);

    boolean isTrueWhen(CardModel cardModel);

    void onComplete();
}
