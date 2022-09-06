package com.amazon.alexa.accessory.notificationpublisher.audiofocus;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.announcewithcontent.AnnounceWithContentCapability;
import com.amazon.alexa.api.AlexaAudioChannel;
import com.amazon.alexa.api.AlexaAudioInteraction;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public final class AnnounceWithContentAudioInteraction implements AlexaAudioInteraction {
    private static final String TAG = "AnnounceWithContentAudioInteraction";
    private final AudioFocusChangeListener audioFocusChangeListener;
    private volatile AtomicBoolean foreground = new AtomicBoolean(false);

    public AnnounceWithContentAudioInteraction(@NonNull AudioFocusChangeListener audioFocusChangeListener) {
        this.audioFocusChangeListener = audioFocusChangeListener;
    }

    private void onBackgroundState() {
        this.foreground.set(false);
        this.audioFocusChangeListener.audioFocusLost();
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteraction
    public AlexaAudioChannel getAlexaAudioChannel() {
        return AlexaAudioChannel.ALERTS;
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteraction
    public String getInteractionComponentName() {
        return AnnounceWithContentCapability.INTERFACE_NAME;
    }

    public boolean isForeground() {
        return this.foreground.get();
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteraction
    public void onBackground() {
        onBackgroundState();
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteraction
    public void onForeground() {
        this.foreground.set(true);
        this.audioFocusChangeListener.audioFocusAcquired();
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteraction
    public void onPause() {
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteraction
    public void onResume() {
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteraction
    public void onStop() {
        onBackgroundState();
    }
}
