package com.amazon.alexa.voice.ui.onedesign.tta.items;

import com.amazon.alexa.voice.ui.onedesign.rv.ListItem;
import com.amazon.alexa.voice.ui.onedesign.tta.viewholder.TtaThinkingStateViewHolder;
import com.google.auto.value.AutoValue;
@AutoValue
@ListItem(viewHolderClass = TtaThinkingStateViewHolder.class)
/* loaded from: classes11.dex */
public abstract class ThinkingTtaItem {
    public static ThinkingTtaItem create() {
        return new AutoValue_ThinkingTtaItem();
    }
}
