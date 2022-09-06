package com.amazon.alexa.voice.ui.onedesign.tta.items;

import com.amazon.alexa.voice.ui.onedesign.rv.ListItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.AutoValue_CardTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.viewholder.TtaCardViewHolder;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import com.google.auto.value.AutoValue;
@AutoValue
@ListItem(viewHolderClass = TtaCardViewHolder.class)
/* loaded from: classes11.dex */
public abstract class CardTtaItem {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract CardTtaItem build();

        public abstract Builder cardType(String str);

        public abstract Builder itemDescription(String str);

        public abstract Builder itemId(String str);

        public abstract Builder itemText(CharSequence charSequence);

        public abstract Builder linkUrl(String str);
    }

    public static Builder builder() {
        return new AutoValue_CardTtaItem.Builder();
    }

    public static CardTtaItem fromTtaResponseCard(TtaResponseCard ttaResponseCard) {
        return builder().itemId(ttaResponseCard.getId()).itemText(ttaResponseCard.getMessage()).itemDescription(ttaResponseCard.getDescription()).linkUrl(ttaResponseCard.getLinkUrl()).cardType(ttaResponseCard.getCardType()).build();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CardTtaItem)) {
            return false;
        }
        return getItemId().equals(((CardTtaItem) obj).getItemId());
    }

    public abstract String getCardType();

    public abstract String getItemDescription();

    public abstract String getItemId();

    public abstract CharSequence getItemText();

    public abstract String getLinkUrl();

    public int hashCode() {
        return getItemId().hashCode();
    }
}
