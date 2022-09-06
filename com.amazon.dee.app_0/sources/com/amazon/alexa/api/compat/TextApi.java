package com.amazon.alexa.api.compat;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: TextApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/api/compat/TextApi;", "", "deregisterExpectTextListener", "", "alexaExpectTextListener", "Lcom/amazon/alexa/api/compat/AlexaExpectTextListener;", "deregisterTextResponseListener", "alexaTextResponseListener", "Lcom/amazon/alexa/api/compat/AlexaTextResponseListener;", "registerExpectTextListener", "registerTextResponseListener", "sendMessage", "message", "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface TextApi {
    void deregisterExpectTextListener(@NotNull AlexaExpectTextListener alexaExpectTextListener);

    void deregisterTextResponseListener(@NotNull AlexaTextResponseListener alexaTextResponseListener);

    void registerExpectTextListener(@NotNull AlexaExpectTextListener alexaExpectTextListener);

    void registerTextResponseListener(@NotNull AlexaTextResponseListener alexaTextResponseListener);

    void sendMessage(@NotNull String str);
}
