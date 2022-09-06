package com.amazon.dee.app.dependencies;

import com.amazon.alexa.voice.ui.player.PlayerCardUpdater;
import com.amazon.alexa.voice.ui.superbowl.AudioPlayerCardUpdater;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public final class EntertainmentModule {
    @Provides
    @ApplicationScope
    public PlayerCardUpdater providePlayerCardUpdater() {
        return new AudioPlayerCardUpdater();
    }
}
