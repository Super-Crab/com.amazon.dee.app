package com.amazon.alexa.voice.ui.onedesign.scrim;

import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface ScrimParametersModel extends Parcelable {
    boolean getHideCancelButton();

    @Nullable
    String getHint();

    boolean getShowHint();

    boolean getShowTTAIngress();

    boolean getTransparentBackground();
}
