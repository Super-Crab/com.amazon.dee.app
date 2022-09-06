package com.amazon.alexa.voice.ui.onedesign.list;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface ListItemModel {
    @NonNull
    CharSequence getName();

    @NonNull
    Object getTag();
}
