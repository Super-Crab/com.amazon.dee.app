package com.amazon.alexa.voice.ui.onedesign.tta;

import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface TtaScreenParametersModel extends Parcelable {
    boolean getAppSearchEnabled();

    @Nullable
    String getHintText();
}
