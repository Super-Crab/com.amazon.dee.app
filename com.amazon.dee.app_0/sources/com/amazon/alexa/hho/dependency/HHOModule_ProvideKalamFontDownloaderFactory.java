package com.amazon.alexa.hho.dependency;

import com.amazon.alexa.hho.stickynotes.KalamFontDownloader;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class HHOModule_ProvideKalamFontDownloaderFactory implements Factory<KalamFontDownloader> {
    private final HHOModule module;

    public HHOModule_ProvideKalamFontDownloaderFactory(HHOModule hHOModule) {
        this.module = hHOModule;
    }

    public static HHOModule_ProvideKalamFontDownloaderFactory create(HHOModule hHOModule) {
        return new HHOModule_ProvideKalamFontDownloaderFactory(hHOModule);
    }

    public static KalamFontDownloader provideInstance(HHOModule hHOModule) {
        return proxyProvideKalamFontDownloader(hHOModule);
    }

    public static KalamFontDownloader proxyProvideKalamFontDownloader(HHOModule hHOModule) {
        return (KalamFontDownloader) Preconditions.checkNotNull(hHOModule.provideKalamFontDownloader(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KalamFontDownloader mo10268get() {
        return provideInstance(this.module);
    }
}
