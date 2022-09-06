package com.amazon.alexa.hho.api;

import com.amazon.alexa.hho.stickynotes.KalamFontDownloader;
import com.amazon.alexa.hho.stickynotes.StickyNotesMediaProvider;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class HHOServiceImpl_MembersInjector implements MembersInjector<HHOServiceImpl> {
    private final Provider<KalamFontDownloader> kalamFontDownloaderProvider;
    private final Provider<StickyNotesMediaProvider> stickyNotesMediaProvider;

    public HHOServiceImpl_MembersInjector(Provider<StickyNotesMediaProvider> provider, Provider<KalamFontDownloader> provider2) {
        this.stickyNotesMediaProvider = provider;
        this.kalamFontDownloaderProvider = provider2;
    }

    public static MembersInjector<HHOServiceImpl> create(Provider<StickyNotesMediaProvider> provider, Provider<KalamFontDownloader> provider2) {
        return new HHOServiceImpl_MembersInjector(provider, provider2);
    }

    public static void injectKalamFontDownloader(HHOServiceImpl hHOServiceImpl, KalamFontDownloader kalamFontDownloader) {
        hHOServiceImpl.kalamFontDownloader = kalamFontDownloader;
    }

    public static void injectStickyNotesMediaProvider(HHOServiceImpl hHOServiceImpl, StickyNotesMediaProvider stickyNotesMediaProvider) {
        hHOServiceImpl.stickyNotesMediaProvider = stickyNotesMediaProvider;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(HHOServiceImpl hHOServiceImpl) {
        injectStickyNotesMediaProvider(hHOServiceImpl, this.stickyNotesMediaProvider.mo10268get());
        injectKalamFontDownloader(hHOServiceImpl, this.kalamFontDownloaderProvider.mo10268get());
    }
}
