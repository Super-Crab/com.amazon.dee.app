package com.amazon.alexa.voice.ui.onedesign.standard;

import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import java.util.List;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface StandardCardModel extends Parcelable {
    CharSequence getCardType();

    CharSequence getContent();

    CharSequence getContentImageAttribution();

    CharSequence getContentImageUrl();

    List<CharSequence> getDomains();

    CharSequence getLinkText();

    CharSequence getLinkUrl();

    CharSequence getSubTitle();

    @Nullable
    CharSequence getTitle();
}
