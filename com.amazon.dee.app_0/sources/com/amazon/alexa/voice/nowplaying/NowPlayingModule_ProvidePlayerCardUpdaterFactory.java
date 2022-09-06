package com.amazon.alexa.voice.nowplaying;

import com.amazon.alexa.voice.ui.player.PlayerCardUpdater;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class NowPlayingModule_ProvidePlayerCardUpdaterFactory implements Factory<PlayerCardUpdater> {
    private static final NowPlayingModule_ProvidePlayerCardUpdaterFactory INSTANCE = new NowPlayingModule_ProvidePlayerCardUpdaterFactory();

    public static NowPlayingModule_ProvidePlayerCardUpdaterFactory create() {
        return INSTANCE;
    }

    public static PlayerCardUpdater provideInstance() {
        return proxyProvidePlayerCardUpdater();
    }

    public static PlayerCardUpdater proxyProvidePlayerCardUpdater() {
        return (PlayerCardUpdater) Preconditions.checkNotNull(NowPlayingModule.providePlayerCardUpdater(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PlayerCardUpdater mo10268get() {
        return provideInstance();
    }
}
