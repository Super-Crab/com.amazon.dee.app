package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaAudioChannel;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlexaAudioPlaybackStatusListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H&¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaAudioPlaybackStatusListener;", "", "onAudioPlaybackStatusChanged", "", "audioPlaybackStatus", "", "Lcom/amazon/alexa/api/AlexaAudioChannel;", "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AlexaAudioPlaybackStatusListener {
    void onAudioPlaybackStatusChanged(@NotNull Map<AlexaAudioChannel, Boolean> map);
}
