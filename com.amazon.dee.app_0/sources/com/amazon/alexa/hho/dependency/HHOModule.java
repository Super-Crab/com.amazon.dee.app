package com.amazon.alexa.hho.dependency;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.hho.cache.MediaFileCacheManager;
import com.amazon.alexa.hho.metrics.HHOMetricsService;
import com.amazon.alexa.hho.stickynotes.KalamFontDownloader;
import com.amazon.alexa.hho.stickynotes.StickyNotesMediaDownloader;
import com.amazon.alexa.hho.stickynotes.StickyNotesMediaProvider;
import com.amazon.alexa.hho.utils.FileManager;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.dee.app.http.CoralService;
import dagger.Module;
import dagger.Provides;
import javax.annotation.Nonnull;
import javax.inject.Singleton;
@Module
/* loaded from: classes8.dex */
public class HHOModule {
    private static final String CACHE_DIRECTORY_NAME = "sticky_notes_media";
    private static final String TEMP_FILES_DIRECTORY_NAME = "temp_files";
    private final Context context;

    public HHOModule(@Nonnull Context context) {
        this.context = context;
    }

    @Provides
    @Nonnull
    public KalamFontDownloader provideKalamFontDownloader() {
        return new KalamFontDownloader(this.context, new Handler(Looper.getMainLooper()));
    }

    @Provides
    @Singleton
    @Nonnull
    public HHOMetricsService provideMetrics(LazyComponent<Mobilytics> lazyComponent) {
        return new HHOMetricsService(lazyComponent);
    }

    @Provides
    @Nonnull
    public StickyNotesMediaDownloader provideStickyNotesMediaDownloader(CoralService coralService, HHOMetricsService hHOMetricsService) {
        return new StickyNotesMediaDownloader(coralService, this.context.getFilesDir() + "/" + TEMP_FILES_DIRECTORY_NAME, new FileManager(), hHOMetricsService);
    }

    @Provides
    @Singleton
    @Nonnull
    public StickyNotesMediaProvider provideStickyNotesMediaProvider(StickyNotesMediaDownloader stickyNotesMediaDownloader, EventBus eventBus, IdentityService identityService, HHOMetricsService hHOMetricsService) {
        return new StickyNotesMediaProvider(stickyNotesMediaDownloader, new MediaFileCacheManager(this.context.getCacheDir().getAbsolutePath() + "/" + CACHE_DIRECTORY_NAME, new FileManager()), eventBus, identityService, hHOMetricsService);
    }
}
