package com.here.sdk.core.engine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Locale;
/* loaded from: classes3.dex */
interface LocaleFactory {
    @Nullable
    Locale getLocaleByBcp47(@NonNull String str);
}
