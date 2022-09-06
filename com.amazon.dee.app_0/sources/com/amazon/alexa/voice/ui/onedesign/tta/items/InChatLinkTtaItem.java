package com.amazon.alexa.voice.ui.onedesign.tta.items;

import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.onedesign.rv.ListItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.AutoValue_InChatLinkTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.viewholder.TtaInChatLinkVewHolder;
import com.amazon.alexa.voice.ui.tta.search.ItemPosition;
import com.amazon.alexa.voice.ui.tta.search.ItemRoute;
import com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard;
import com.google.auto.value.AutoValue;
@AutoValue
@ListItem(viewHolderClass = TtaInChatLinkVewHolder.class)
/* loaded from: classes11.dex */
public abstract class InChatLinkTtaItem {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract InChatLinkTtaItem build();

        public abstract Builder itemId(String str);

        public abstract Builder itemPosition(ItemPosition itemPosition);

        public abstract Builder itemRoute(ItemRoute itemRoute);

        public abstract Builder primaryText(CharSequence charSequence);

        public abstract Builder secondText(CharSequence charSequence);
    }

    public static Builder builder() {
        return new AutoValue_InChatLinkTtaItem.Builder();
    }

    public static InChatLinkTtaItem fromTtaInChatResultCard(TtaInChatResultCard ttaInChatResultCard) {
        return builder().itemId(ttaInChatResultCard.getId()).primaryText(ttaInChatResultCard.getMessage()).secondText(ttaInChatResultCard.getSecondMessage()).itemRoute(ttaInChatResultCard.getItemRoute()).itemPosition(ttaInChatResultCard.getItemPosition()).build();
    }

    public abstract String getItemId();

    public abstract ItemPosition getItemPosition();

    public abstract ItemRoute getItemRoute();

    public abstract CharSequence getPrimaryText();

    @Nullable
    public abstract CharSequence getSecondText();
}
