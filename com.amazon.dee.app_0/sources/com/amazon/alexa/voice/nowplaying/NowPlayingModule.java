package com.amazon.alexa.voice.nowplaying;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceNowPlayingEventBusModule;
import com.amazon.alexa.voice.ui.player.PlayerCardUpdater;
import com.amazon.alexa.voice.ui.superbowl.AudioPlayerCardUpdater;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class NowPlayingModule {
    private NowPlayingModule() {
    }

    @Provides
    @Singleton
    public static AudioPlaybackController provideAudioPlaybackController(AlexaServicesConnection alexaServicesConnection) {
        return new AudioPlaybackController(alexaServicesConnection);
    }

    @Provides
    @Singleton
    public static DefaultNowPlayingCardManager provideDefaultNowPlayingCardManager(Context context, AlexaServicesConnection alexaServicesConnection, VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule) {
        return new DefaultNowPlayingCardManager(context, alexaServicesConnection, voiceNowPlayingEventBusModule);
    }

    @Provides
    @Singleton
    public static PlayerCardUpdater providePlayerCardUpdater() {
        return new AudioPlayerCardUpdater();
    }

    @Provides
    @Singleton
    public static VoiceNowPlayingEventBusModule provideVoiceNowPlayingBridge(AudioPlaybackController audioPlaybackController) {
        return new VoiceNowPlayingEventBusModule(audioPlaybackController);
    }
}
