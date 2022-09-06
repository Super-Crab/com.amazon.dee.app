package com.amazon.alexa.voice.ui.onedesign.tta.items;

import com.amazon.alexa.voice.ui.onedesign.rv.ListItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.AutoValue_InChatHintTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.viewholder.TtaInChatHintViewHolder;
import com.amazon.alexa.voice.ui.tta.search.ItemPosition;
import com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard;
import com.google.auto.value.AutoValue;
@AutoValue
@ListItem(viewHolderClass = TtaInChatHintViewHolder.class)
/* loaded from: classes11.dex */
public abstract class InChatHintTtaItem {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract InChatHintTtaItem build();

        public abstract Builder itemId(String str);

        public abstract Builder itemPosition(ItemPosition itemPosition);

        public abstract Builder itemText(CharSequence charSequence);
    }

    public static Builder builder() {
        return new AutoValue_InChatHintTtaItem.Builder();
    }

    public static InChatHintTtaItem fromTtaInChatResultCard(TtaInChatResultCard ttaInChatResultCard) {
        return builder().itemId(ttaInChatResultCard.getId()).itemText(ttaInChatResultCard.getMessage()).itemPosition(ttaInChatResultCard.getItemPosition()).build();
    }

    public abstract String getItemId();

    public abstract ItemPosition getItemPosition();

    public abstract CharSequence getItemText();
}
