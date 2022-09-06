package com.amazon.deecomms.core;

import android.media.AudioManager;
import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.media.audio.AudioInputController;
import com.amazon.deecomms.media.audio.AudioOutputController;
import com.amazon.deecomms.media.audio.AudioStateObservable;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes12.dex */
public class AudioModule {
    @Provides
    @Singleton
    public AudioOutputController providesAudioStateController(@NonNull AudioStateObservable audioStateObservable, @NonNull AudioManager audioManager, @NonNull @Named("CurrentCall") SipClientState sipClientState) {
        return new AudioOutputController(audioStateObservable, audioManager, sipClientState);
    }

    @Provides
    @Singleton
    public AudioStateObservable providesAudioStateObservable() {
        return new AudioStateObservable();
    }

    @Provides
    @Singleton
    public AudioInputController providesMicStateController(@NonNull AudioManager audioManager, @NonNull CallContext callContext) {
        return new AudioInputController(audioManager, callContext);
    }
}
