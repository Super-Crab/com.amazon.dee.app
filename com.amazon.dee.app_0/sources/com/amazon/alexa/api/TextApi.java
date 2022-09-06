package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes6.dex */
public interface TextApi {
    void deregisterListener(@NonNull AlexaExpectTextListener alexaExpectTextListener);

    void deregisterListener(@NonNull AlexaTextResponseListener alexaTextResponseListener);

    void registerListener(@NonNull AlexaExpectTextListener alexaExpectTextListener);

    void registerListener(@NonNull AlexaTextResponseListener alexaTextResponseListener);

    @Deprecated
    void sendMessage(@NonNull String str);

    void sendMessage(@NonNull String str, TextAlexaDialogExtras textAlexaDialogExtras);
}
