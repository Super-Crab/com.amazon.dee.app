package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.UiThread;
/* loaded from: classes6.dex */
public interface AlexaTemplateCardListener {
    @UiThread
    void onReceivedInfoCard(@NonNull String str);
}
