package com.amazon.alexa.voice.ui.cards;

import androidx.annotation.Nullable;
import com.amazon.regulator.ViewController;
import java.util.Locale;
/* loaded from: classes11.dex */
public interface CardFactory {
    @Deprecated
    ViewController buildCard(@Nullable String str);

    ViewController buildCard(@Nullable String str, Locale locale);
}
