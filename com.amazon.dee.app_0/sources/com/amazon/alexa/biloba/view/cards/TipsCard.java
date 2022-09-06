package com.amazon.alexa.biloba.view.cards;

import android.content.Context;
import android.view.View;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.generated.models.Card;
import com.amazon.alexa.biloba.model.Action;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class TipsCard extends BaseRecyclerItem<CardData> implements DashboardCard {
    private static final ImmutableMap<Card.TypeEnum, Integer> COLOR_PICKER = new ImmutableMap.Builder().mo7828put(Card.TypeEnum.EC_SETUP_NEEDED, Integer.valueOf(R.attr.mosaicFill10)).mo7828put(Card.TypeEnum.EC_AND_COMMS_SETUP_DONE, Integer.valueOf(R.attr.mosaicFill20)).mo7828put(Card.TypeEnum.LEARN_ABOUT_TIMEZONE, Integer.valueOf(R.attr.mosaicFill50)).mo7828put(Card.TypeEnum.LEARN_ABOUT_NOTIFICATION, Integer.valueOf(R.attr.mosaicFill40)).mo7828put(Card.TypeEnum.REMOTE_MANAGEMENT, Integer.valueOf(R.attr.mosaicFill20)).mo7828put(Card.TypeEnum.CARE_PLUS_UPSELL_FOR_FD_SENSORS, Integer.valueOf(R.attr.mosaicFill10)).mo7828put(Card.TypeEnum.INVITE_CARE_GIVER, Integer.valueOf(R.attr.mosaicFill20)).mo7826build();

    public TipsCard(Card card, Map<Action, View.OnClickListener> map, View.OnClickListener onClickListener) {
        super(new CardData(card, map, onClickListener), R.layout.tips_card, BR.card);
    }

    public int getBackgroundColorAttribute(Context context) {
        int intValue;
        if (!COLOR_PICKER.containsKey(getCard().getType())) {
            intValue = R.attr.mosaicFill50;
        } else {
            intValue = COLOR_PICKER.mo7740get(getCard().getType()).intValue();
        }
        return ThemeUtil.getColorFromAttribute(context, intValue);
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

    public String getIconUrl() {
        return getCard().getIconUrl();
    }

    @Override // com.amazon.alexa.biloba.view.cards.DashboardCard
    public String getId() {
        return getCard().getId();
    }

    public CardButton getPrimaryAction() {
        Action action = getCard().getActions().get(0);
        return new CardButton(action.getLocalizedTitle(), getClickListeners().get(action));
    }

    public String getTitle() {
        return getCard().getLocalizedTitle();
    }

    @Override // com.amazon.alexa.biloba.view.cards.DashboardCard
    public int getType() {
        return 1;
    }
}
