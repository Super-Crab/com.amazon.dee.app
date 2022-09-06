package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes6.dex */
public interface CardsApi {
    void deregisterListener(@NonNull AlexaCardListener alexaCardListener);

    void deregisterListener(@NonNull AlexaPlayerInfoCardListener alexaPlayerInfoCardListener);

    void registerListener(@NonNull AlexaCardListener alexaCardListener);

    void registerListener(@NonNull AlexaPlayerInfoCardListener alexaPlayerInfoCardListener);
}
