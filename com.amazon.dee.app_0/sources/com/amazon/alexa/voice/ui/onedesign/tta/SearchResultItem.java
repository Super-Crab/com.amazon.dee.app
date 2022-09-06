package com.amazon.alexa.voice.ui.onedesign.tta;

import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.onedesign.tta.items.InChatHintTtaItem;
import com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem;
import com.amazon.alexa.voice.ui.tta.search.ItemRoute;
import com.amazon.alexa.voice.ui.tta.search.ResultItem;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes11.dex */
public abstract class SearchResultItem implements ResultItem {
    public static SearchResultItem create(InChatHintTtaItem inChatHintTtaItem) {
        return new AutoValue_SearchResultItem(inChatHintTtaItem.getItemId(), 2, inChatHintTtaItem.getItemText().toString(), null);
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.ResultItem
    public abstract String getId();

    @Nullable
    public abstract ItemRoute getItemRoute();

    public abstract String getItemText();

    @Override // com.amazon.alexa.voice.ui.tta.search.ResultItem
    public abstract int getType();

    public static SearchResultItem create(InChatLinkTtaItem inChatLinkTtaItem) {
        return new AutoValue_SearchResultItem(inChatLinkTtaItem.getItemId(), 1, inChatLinkTtaItem.getPrimaryText().toString(), inChatLinkTtaItem.getItemRoute());
    }

    public static SearchResultItem create(String str, String str2, ItemRoute itemRoute) {
        return new AutoValue_SearchResultItem(str, 1, str2, itemRoute);
    }
}
