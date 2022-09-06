package com.amazon.alexa.voice.ui.onedesign.tta.items;

import com.amazon.alexa.voice.ui.onedesign.rv.ListItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.AutoValue_AlexaResponseTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.viewholder.TtaAlexaResponseViewHolder;
import com.google.auto.value.AutoValue;
@AutoValue
@ListItem(viewHolderClass = TtaAlexaResponseViewHolder.class)
/* loaded from: classes11.dex */
public abstract class AlexaResponseTtaItem {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract AlexaResponseTtaItem build();

        public abstract Builder itemId(String str);

        public abstract Builder itemText(CharSequence charSequence);
    }

    public static Builder builder() {
        return new AutoValue_AlexaResponseTtaItem.Builder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AlexaResponseTtaItem)) {
            return false;
        }
        return getItemId().equals(((AlexaResponseTtaItem) obj).getItemId());
    }

    public abstract String getItemId();

    public abstract CharSequence getItemText();

    public int hashCode() {
        return getItemId().hashCode();
    }
}
