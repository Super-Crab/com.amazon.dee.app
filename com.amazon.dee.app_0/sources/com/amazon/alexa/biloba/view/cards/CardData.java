package com.amazon.alexa.biloba.view.cards;

import android.view.View;
import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.generated.models.Card;
import com.amazon.alexa.biloba.model.Action;
import java.util.Map;
/* loaded from: classes6.dex */
public class CardData {
    private final Card card;
    private final Map<Action, View.OnClickListener> clickListeners;
    private final View.OnClickListener dismissListener;

    public CardData(Card card, Map<Action, View.OnClickListener> map, View.OnClickListener onClickListener) {
        this.card = card;
        this.clickListeners = map;
        this.dismissListener = onClickListener;
    }

    @NonNull
    public Card getCard() {
        return this.card;
    }

    public Map<Action, View.OnClickListener> getClickListeners() {
        return this.clickListeners;
    }

    public View.OnClickListener getDismissListener() {
        return this.dismissListener;
    }
}
