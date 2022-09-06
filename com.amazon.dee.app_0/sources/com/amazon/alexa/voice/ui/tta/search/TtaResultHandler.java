package com.amazon.alexa.voice.ui.tta.search;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.tta.TtaMessageResponseListener;
/* loaded from: classes11.dex */
public interface TtaResultHandler {
    void onResultSelected(@NonNull ItemRoute itemRoute);

    void onResultSelected(@NonNull ResultItem resultItem);

    void setResponseListener(@NonNull TtaMessageResponseListener ttaMessageResponseListener);

    void setResultListener(@NonNull TtaResultListener ttaResultListener);
}
