package com.amazon.alexa.voice.ui.onedesign.tta.items;

import com.amazon.alexa.voice.ui.onedesign.rv.ListItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.AutoValue_UserInputTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.viewholder.TtaUserRequestViewHolder;
import com.google.auto.value.AutoValue;
@AutoValue
@ListItem(viewHolderClass = TtaUserRequestViewHolder.class)
/* loaded from: classes11.dex */
public abstract class UserInputTtaItem {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract UserInputTtaItem build();

        public abstract Builder itemText(CharSequence charSequence);
    }

    public static Builder builder() {
        return new AutoValue_UserInputTtaItem.Builder();
    }

    public abstract CharSequence getItemText();
}
