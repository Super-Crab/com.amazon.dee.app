package com.amazon.alexa.voice.ui.onedesign.empty;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel
/* loaded from: classes11.dex */
public interface EmptyCardModel extends Parcelable {
    @Nullable
    String getManageButtonDestination();

    @Nullable
    CharSequence getTitle();

    @NonNull
    String getType();
}
