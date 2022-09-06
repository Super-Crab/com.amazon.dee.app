package com.here.sdk.core.engine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.C;
import java.util.Locale;
/* loaded from: classes3.dex */
class AndroidLocaleFactory implements LocaleFactory {
    @Override // com.here.sdk.core.engine.LocaleFactory
    @Nullable
    public Locale getLocaleByBcp47(@NonNull String str) {
        Locale forLanguageTag = Locale.forLanguageTag(str);
        if (forLanguageTag.toLanguageTag().equals(C.LANGUAGE_UNDETERMINED)) {
            return null;
        }
        return forLanguageTag;
    }
}
