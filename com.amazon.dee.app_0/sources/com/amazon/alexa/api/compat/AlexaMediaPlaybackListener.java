package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaMediaPlaybackMetadata;
import com.amazon.alexa.api.AlexaMediaPlaybackState;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlexaMediaPlaybackListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaMediaPlaybackListener;", "", "onMediaMetadata", "", "mediaPlaybackData", "Lcom/amazon/alexa/api/AlexaMediaPlaybackMetadata;", "onMediaPlaybackStateUpdate", "mediaPlaybackState", "Lcom/amazon/alexa/api/AlexaMediaPlaybackState;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AlexaMediaPlaybackListener {
    void onMediaMetadata(@NotNull AlexaMediaPlaybackMetadata alexaMediaPlaybackMetadata);

    void onMediaPlaybackStateUpdate(@NotNull AlexaMediaPlaybackState alexaMediaPlaybackState);
}
