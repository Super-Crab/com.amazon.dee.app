package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaEvent;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: EventSenderApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH&¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/api/compat/EventSenderApi;", "", "send", "", "alexaEvent", "Lcom/amazon/alexa/api/AlexaEvent;", "eventRequiresContexts", "", "apiCallbacks", "Lcom/amazon/alexa/api/AlexaApiCallbacks;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface EventSenderApi {
    void send(@NotNull AlexaEvent alexaEvent);

    void send(@NotNull AlexaEvent alexaEvent, boolean z);

    void send(@NotNull AlexaEvent alexaEvent, boolean z, @Nullable AlexaApiCallbacks alexaApiCallbacks);
}
