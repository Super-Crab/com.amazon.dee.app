package com.amazon.alexa.voice.ui.onedesign.tta;

import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem;
import com.amazon.alexa.voice.ui.tta.search.PillResultItem;
import com.google.auto.value.AutoValue;
import java.util.UUID;
@AutoValue
/* loaded from: classes11.dex */
public abstract class TextPillResultItem implements PillResultItem {
    public static TextPillResultItem create(PillResultTtaItem pillResultTtaItem) {
        return new AutoValue_TextPillResultItem(pillResultTtaItem.isFromSimba(), pillResultTtaItem.getItemText().toString(), pillResultTtaItem.getItemRoute(), pillResultTtaItem.getItemType(), pillResultTtaItem.getItemId());
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.ResultItem
    public abstract String getId();

    @Override // com.amazon.alexa.voice.ui.tta.search.PillResultItem
    @Nullable
    public abstract String getItemRoute();

    @Override // com.amazon.alexa.voice.ui.tta.search.PillResultItem
    public abstract String getItemText();

    @Override // com.amazon.alexa.voice.ui.tta.search.ResultItem
    public abstract int getType();

    public static TextPillResultItem create(String str) {
        return new AutoValue_TextPillResultItem(true, str, null, 2, UUID.randomUUID().toString());
    }

    public static TextPillResultItem create(String str, String str2) {
        return new AutoValue_TextPillResultItem(true, str, str2, 1, UUID.randomUUID().toString());
    }

    public static TextPillResultItem create(String str, String str2, int i) {
        return new AutoValue_TextPillResultItem(true, str, str2, i, UUID.randomUUID().toString());
    }
}
