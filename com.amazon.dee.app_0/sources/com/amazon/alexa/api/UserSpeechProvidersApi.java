package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes6.dex */
public interface UserSpeechProvidersApi {
    void deregister(@NonNull AlexaUserSpeechProvider alexaUserSpeechProvider);

    void register(@NonNull AlexaUserSpeechProvider alexaUserSpeechProvider, @NonNull AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata);

    void requestDialog(@NonNull AlexaUserSpeechProvider alexaUserSpeechProvider, @NonNull AlexaDialogRequest alexaDialogRequest);
}
