package com.amazon.dee.app.dependencies;

import com.amazon.alexa.voice.ui.player.PlayerCardUpdater;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class EntertainmentModule_ProvidePlayerCardUpdaterFactory implements Factory<PlayerCardUpdater> {
    private final EntertainmentModule module;

    public EntertainmentModule_ProvidePlayerCardUpdaterFactory(EntertainmentModule entertainmentModule) {
        this.module = entertainmentModule;
    }

    public static EntertainmentModule_ProvidePlayerCardUpdaterFactory create(EntertainmentModule entertainmentModule) {
        return new EntertainmentModule_ProvidePlayerCardUpdaterFactory(entertainmentModule);
    }

    public static PlayerCardUpdater provideInstance(EntertainmentModule entertainmentModule) {
        return proxyProvidePlayerCardUpdater(entertainmentModule);
    }

    public static PlayerCardUpdater proxyProvidePlayerCardUpdater(EntertainmentModule entertainmentModule) {
        return (PlayerCardUpdater) Preconditions.checkNotNull(entertainmentModule.providePlayerCardUpdater(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PlayerCardUpdater mo10268get() {
        return provideInstance(this.module);
    }
}
