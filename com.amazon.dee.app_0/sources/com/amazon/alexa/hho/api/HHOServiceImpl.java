package com.amazon.alexa.hho.api;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.hho.dependency.ApplicationModule;
import com.amazon.alexa.hho.dependency.DaggerHHOComponent;
import com.amazon.alexa.hho.dependency.HHOModule;
import com.amazon.alexa.hho.stickynotes.KalamFontDownloader;
import com.amazon.alexa.hho.stickynotes.StickyNotesMediaProvider;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class HHOServiceImpl implements HHOService {
    private static final int FONT_DOWNLOAD_DELAY_MS = 2000;
    private static final String TAG = "HHOServiceImpl";
    @Inject
    KalamFontDownloader kalamFontDownloader;
    @Inject
    StickyNotesMediaProvider stickyNotesMediaProvider;

    public HHOServiceImpl(@NonNull ComponentGetter componentGetter, @NonNull Context context) {
        DaggerHHOComponent.builder().applicationModule(new ApplicationModule(componentGetter)).hHOModule(new HHOModule(context)).build().inject(this);
    }

    @Override // com.amazon.alexa.component.api.ServiceLifecycle
    public void start() {
        this.kalamFontDownloader.startFontDownload(2000L);
        this.stickyNotesMediaProvider.cleanupMediaCache();
        this.stickyNotesMediaProvider.startListeningEventBus();
    }

    @Override // com.amazon.alexa.component.api.ServiceLifecycle
    public void stop() {
        this.stickyNotesMediaProvider.stopListeningEventBus();
    }
}
