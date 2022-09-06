package com.amazon.alexa.voice.ui.provider;

import androidx.annotation.Nullable;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public interface CardFactoryWithMode {
    @Deprecated
    ViewController buildCard(@Nullable String str, CardMode cardMode);

    ViewController buildCard(@Nullable String str, CardMode cardMode, int i);
}
