package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
import java.util.List;
/* loaded from: classes6.dex */
public interface WakeWordApi {
    void deregisterWakeWordListener(@NonNull AlexaWakeWordListener alexaWakeWordListener);

    @Deprecated
    void disable();

    @Deprecated
    void disable(@NonNull String str);

    @Deprecated
    void enable();

    @Deprecated
    void enable(@NonNull AlexaDialogExtras alexaDialogExtras);

    @Deprecated
    void enable(@NonNull String str);

    @Deprecated
    void enable(@NonNull String str, @NonNull AlexaDialogExtras alexaDialogExtras);

    void registerWakeWordListener(@NonNull AlexaWakeWordListener alexaWakeWordListener);

    void setWakeWords(@NonNull List<String> list);

    void setWakeWords(@NonNull List<String> list, @NonNull AlexaApiCallbacks alexaApiCallbacks);

    void startListening();

    void startListening(@NonNull AlexaApiCallbacks alexaApiCallbacks);

    void startListening(@NonNull AlexaDialogExtras alexaDialogExtras);

    void startListening(@NonNull AlexaDialogExtras alexaDialogExtras, @NonNull AlexaApiCallbacks alexaApiCallbacks);

    void stopListening();
}
