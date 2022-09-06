package com.amazon.alexa.voice.ui.onedesign.ftue.language;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface LanguageModel extends Parcelable {
    @NonNull
    String getCountry();

    @NonNull
    CharSequence getDisplayCountry();

    @NonNull
    CharSequence getDisplayLanguage();

    @NonNull
    String getLanguage();
}
