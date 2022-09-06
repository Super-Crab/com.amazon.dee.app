package com.amazon.alexa.biloba.view.cards;

import android.content.Context;
import android.view.View;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.generated.models.Card;
import com.amazon.alexa.biloba.model.Action;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public class NotificationCard extends BaseRecyclerItem<CardData> implements DashboardCard {
    private static final Set<Card.TypeEnum> emergencyNotificationCards = ImmutableSet.of(Card.TypeEnum.EMERGENCY_DETECTED, Card.TypeEnum.VERIFIED_FALL, Card.TypeEnum.VERIFIED_NO_FALL, Card.TypeEnum.UNVERIFIED_FALL, Card.TypeEnum.EMERGENCY_HELPLINE_CALL_STARTED, Card.TypeEnum.EMERGENCY_HELPLINE_CALL_ENDED, new Card.TypeEnum[0]);

    public NotificationCard(Card card, Map<Action, View.OnClickListener> map, View.OnClickListener onClickListener) {
        super(new CardData(card, map, onClickListener), R.layout.notification_card, BR.card);
    }

    public int getBackgroundColorAttribute(Context context) {
        if (emergencyNotificationCards.contains(getCard().getType())) {
            return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicFill10);
        }
        return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicFill50);
    }

    public CardButton getCallButton() {
        List<Action> actions = getCard().getActions();
        if (actions == null || actions.get(0) == null) {
            return null;
        }
        return new CardButton(actions.get(0).getLocalizedTitle(), getClickListeners().get(actions.get(0)));
    }

    public Card getCard() {
        return getData().getCard();
    }

    public Map<Action, View.OnClickListener> getClickListeners() {
        return getData().getClickListeners();
    }

    public String getDescription() {
        return getCard().getLocalizedDescription();
    }

    public View.OnClickListener getDismissClickListener() {
        return getData().getDismissListener();
    }

    public CardButton getDropInButton() {
        List<Action> actions = getCard().getActions();
        if (actions == null || actions.size() <= 1 || actions.get(1) == null) {
            return null;
        }
        return new CardButton(actions.get(1).getLocalizedTitle(), getClickListeners().get(actions.get(1)));
    }

    public int getDropInVisibility() {
        return getDropInButton() == null ? 8 : 0;
    }

    public String getHeader() {
        return String.format("%s %s %s %s", getCard().getLocalizedDateTime().getDate(), "·", getCard().getLocalizedDateTime().getTime(), getCard().getLocalizedDateTime().getZone());
    }

    @Override // com.amazon.alexa.biloba.view.cards.DashboardCard
    public String getId() {
        return getCard().getId();
    }

    public String getTitle() {
        return getCard().getLocalizedTitle();
    }

    @Override // com.amazon.alexa.biloba.view.cards.DashboardCard
    public int getType() {
        return 2;
    }
}
