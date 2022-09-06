package com.amazon.alexa.voice.ui.onedesign.tta.items;

import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.onedesign.rv.ListItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.AutoValue_PillResultTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.viewholder.TtaPillResultViewHolder;
import com.amazon.alexa.voice.ui.tta.search.PillResultItem;
import com.google.auto.value.AutoValue;
@AutoValue
@ListItem(viewHolderClass = TtaPillResultViewHolder.class)
/* loaded from: classes11.dex */
public abstract class PillResultTtaItem {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract PillResultTtaItem build();

        public abstract Builder fromSimba(boolean z);

        public abstract Builder itemId(String str);

        public abstract Builder itemRoute(String str);

        public abstract Builder itemText(CharSequence charSequence);

        public abstract Builder itemType(int i);
    }

    public static Builder builder() {
        return new AutoValue_PillResultTtaItem.Builder();
    }

    public static PillResultTtaItem fromPillResultItem(PillResultItem pillResultItem) {
        return builder().itemId(pillResultItem.getId()).itemText(pillResultItem.getItemText()).itemType(pillResultItem.getType()).itemRoute(pillResultItem.getItemRoute()).fromSimba(pillResultItem.isFromSimba()).build();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PillResultTtaItem)) {
            return false;
        }
        return getItemId().equals(((PillResultTtaItem) obj).getItemId());
    }

    public abstract String getItemId();

    @Nullable
    public abstract String getItemRoute();

    public abstract CharSequence getItemText();

    public abstract int getItemType();

    public int hashCode() {
        return getItemId().hashCode();
    }

    public abstract boolean isFromSimba();
}
