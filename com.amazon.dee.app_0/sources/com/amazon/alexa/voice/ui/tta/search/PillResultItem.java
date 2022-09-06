package com.amazon.alexa.voice.ui.tta.search;

import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public interface PillResultItem extends ResultItem {
    @Nullable
    String getItemRoute();

    String getItemText();

    boolean isFromSimba();
}
