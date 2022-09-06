package com.amazon.alexa.voice.ui.locale;

import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public interface LocaleUpdateCallback {
    void onFailure(@LocaleFailureCodes int i, @Nullable String str);

    void onSuccess();
}
