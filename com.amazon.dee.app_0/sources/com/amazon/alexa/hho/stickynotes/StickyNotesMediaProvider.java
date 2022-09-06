package com.amazon.alexa.hho.stickynotes;

import android.util.Log;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.hho.cache.MediaFileCacheManager;
import com.amazon.alexa.hho.metrics.HHOMetricsConstants;
import com.amazon.alexa.hho.metrics.HHOMetricsService;
import com.amazon.alexa.hho.stickynotes.IdentitySubscriber;
import com.amazon.alexa.hho.stickynotes.StickyNotesEventsSubscriber;
import com.amazon.alexa.hho.utils.Primitives;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Predicate;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes8.dex */
public class StickyNotesMediaProvider implements StickyNotesEventsSubscriber.Handler, IdentitySubscriber.Handler {
    private static final long BYTES_IN_MB = 1048576;
    private static final int CACHE_CLEANUP_DELAY_SEC = 2;
    private static final int CACHE_CLEANUP_OLD_FILES_THRESHOLD_DAYS = 21;
    private static final String CACHE_EVICTED_EVENT_TYPE = "hho::sticky::media_cache::evicted";
    private static final long CACHE_MAX_SIZE_IN_BYTES = 104857600;
    private static final String DOWNLOAD_FAILED_EVENT_TYPE = "hho::sticky::media_download::failed";
    private static final String DOWNLOAD_FINISHED_EVENT_TYPE = "hho::sticky::media_download::finished";
    private final MediaFileCacheManager cacheManager;
    private final EventBus eventBus;
    private final IdentityService identityService;
    private final StickyNotesMediaDownloader mediaDownloader;
    private final HHOMetricsService metrics;
    private static final Predicate<String> VIDEO_FILE_PREDICATE = $$Lambda$StickyNotesMediaProvider$0XriCRA1pwVqatDiRWbnmGbAhY.INSTANCE;
    private static final String TAG = StickyNotesMediaProvider.class.getSimpleName();
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final StickyNotesEventsSubscriber mediaRequestsSubscriber = new StickyNotesEventsSubscriber(this);
    private final IdentitySubscriber identitySubscriber = new IdentitySubscriber(this);
    private final ErrorMapper errorMapper = new ErrorMapper();

    /* renamed from: com.amazon.alexa.hho.stickynotes.StickyNotesMediaProvider$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$hho$stickynotes$StickyNoteMediaType = new int[StickyNoteMediaType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$hho$stickynotes$StickyNoteMediaType[StickyNoteMediaType.VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$hho$stickynotes$StickyNoteMediaType[StickyNoteMediaType.THUMBNAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public StickyNotesMediaProvider(@Nonnull StickyNotesMediaDownloader stickyNotesMediaDownloader, @Nonnull MediaFileCacheManager mediaFileCacheManager, @Nonnull EventBus eventBus, @Nonnull IdentityService identityService, @Nonnull HHOMetricsService hHOMetricsService) {
        this.mediaDownloader = stickyNotesMediaDownloader;
        this.cacheManager = mediaFileCacheManager;
        this.eventBus = eventBus;
        this.identityService = identityService;
        this.metrics = hHOMetricsService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nonnull
    /* renamed from: createFileId */
    public String lambda$handleDeleteRequest$7$StickyNotesMediaProvider(@Nonnull StickyNoteMediaType stickyNoteMediaType, @Nonnull String str) {
        return stickyNoteMediaType.name() + ProcessIdUtil.DEFAULT_PROCESSID + str;
    }

    @Nonnull
    private Completable deleteMemoryExceedingFiles(int i) {
        return this.cacheManager.trimCacheSizeDown(104857600L, i, VIDEO_FILE_PREDICATE).flatMapCompletable(new Function() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$0pofdkp_2KNpe8IlsqwekPc_vf4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return StickyNotesMediaProvider.this.lambda$deleteMemoryExceedingFiles$26$StickyNotesMediaProvider((List) obj);
            }
        });
    }

    @Nonnull
    private Completable deleteOldFiles() {
        return Completable.defer(new Supplier() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$-jbqlhzr_iY7BvwbcaLWHjO09TE
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return StickyNotesMediaProvider.this.lambda$deleteOldFiles$24$StickyNotesMediaProvider();
            }
        });
    }

    @Nonnull
    private Maybe<String> downloadAndCacheMedia(@Nonnull final MediaDownloadRequest mediaDownloadRequest) {
        final HHOMetricsService.Timer createTimer = this.metrics.createTimer(HHOMetricsConstants.Event.GET_MEDIA, HHOMetricsConstants.Component.STICKY_NOTES, HHOMetricsConstants.Subcomponent.PROVIDER);
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$wr73AUkbClpyWBASK5_ieHBC8WU
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return StickyNotesMediaProvider.this.lambda$downloadAndCacheMedia$16$StickyNotesMediaProvider(mediaDownloadRequest, createTimer);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$jDWPs7ZtJIhmUlTslCmnaJyRBFs
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return StickyNotesMediaProvider.this.lambda$downloadAndCacheMedia$17$StickyNotesMediaProvider(mediaDownloadRequest, (String) obj);
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$Q8r5IyT4lpz2v2UXio62n3FH7b0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StickyNotesMediaProvider.this.lambda$downloadAndCacheMedia$18$StickyNotesMediaProvider(createTimer, (String) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$xN1tfUbCWJFVv7pb6vkxSDEtchU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StickyNotesMediaProvider.this.lambda$downloadAndCacheMedia$19$StickyNotesMediaProvider(createTimer, mediaDownloadRequest, (Throwable) obj);
            }
        }).toMaybe();
    }

    @Nonnull
    private Maybe<String> downloadMediaIfNotInProgress(@Nonnull final MediaDownloadRequest mediaDownloadRequest) {
        if (mediaDownloadRequest.prefetch) {
            return Maybe.defer(new Supplier() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$H0U113IBJwM4a0EvVbpNl9-lVMk
                @Override // io.reactivex.rxjava3.functions.Supplier
                /* renamed from: get */
                public final Object mo10365get() {
                    return StickyNotesMediaProvider.this.lambda$downloadMediaIfNotInProgress$12$StickyNotesMediaProvider(mediaDownloadRequest);
                }
            });
        }
        return Maybe.defer(new Supplier() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$rmXvBc2FuNBMe1kSlODHlxInMpY
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return StickyNotesMediaProvider.this.lambda$downloadMediaIfNotInProgress$15$StickyNotesMediaProvider(mediaDownloadRequest);
            }
        });
    }

    @Nonnull
    private Single<String> downloadVideo(@Nonnull final MediaDownloadRequest mediaDownloadRequest) {
        Predicate<Long> predicate;
        if (mediaDownloadRequest.prefetch) {
            predicate = new Predicate() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$YyqutiNrMj1aAq7n2CXoHsjPXB0
                @Override // com.google.common.base.Predicate
                public final boolean apply(Object obj) {
                    return StickyNotesMediaProvider.this.lambda$downloadVideo$20$StickyNotesMediaProvider(mediaDownloadRequest, (Long) obj);
                }
            };
        } else {
            predicate = new Predicate() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$VH8Awi6myNj-1uGTdm3lc5B4zTA
                @Override // com.google.common.base.Predicate
                public final boolean apply(Object obj) {
                    return StickyNotesMediaProvider.this.lambda$downloadVideo$21$StickyNotesMediaProvider(mediaDownloadRequest, (Long) obj);
                }
            };
        }
        return this.mediaDownloader.downloadVideo(mediaDownloadRequest.mediaId, predicate);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MaybeSource lambda$null$10(Boolean bool) throws Throwable {
        return bool.booleanValue() ? Maybe.empty() : Maybe.just(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MaybeSource lambda$null$13(Boolean bool) throws Throwable {
        return bool.booleanValue() ? Maybe.just(true) : Maybe.empty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$0(String str) {
        if (str != null) {
            if (str.contains(StickyNoteMediaType.VIDEO.name() + ProcessIdUtil.DEFAULT_PROCESSID)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendCacheEvictedMessages */
    public void lambda$null$25$StickyNotesMediaProvider(@Nonnull List<String> list) {
        for (String str : list) {
            GeneratedOutlineSupport1.outline158("Send cache evicted event: ", str);
            this.metrics.recordOperationalEvent(HHOMetricsConstants.Event.CACHE_EVICTED, HHOMetricsConstants.Component.STICKY_NOTES, HHOMetricsConstants.Subcomponent.PROVIDER);
            this.eventBus.publish(new Message.Builder().setEventType(CACHE_EVICTED_EVENT_TYPE).setPayload(str).build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendDownloadFailedMessage */
    public void lambda$handleDownloadRequest$5$StickyNotesMediaProvider(@Nonnull String str, @Nullable Throwable th) {
        String str2 = TAG;
        Log.e(str2, "Send download failed event: " + str, th);
        String map = this.errorMapper.map(th);
        this.metrics.recordOperationalEvent(HHOMetricsConstants.Event.GET_MEDIA_FAILURE, HHOMetricsConstants.Component.STICKY_NOTES, HHOMetricsConstants.Subcomponent.PROVIDER);
        HHOMetricsService hHOMetricsService = this.metrics;
        hHOMetricsService.recordOperationalEvent("EXCEPTION_" + map, HHOMetricsConstants.Component.STICKY_NOTES, HHOMetricsConstants.Subcomponent.PROVIDER);
        Message.Builder eventType = new Message.Builder().setEventType(DOWNLOAD_FAILED_EVENT_TYPE);
        this.eventBus.publish(eventType.setPayload(str + ":" + map).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendDownloadFinishedMessage */
    public void lambda$handleDownloadRequest$3$StickyNotesMediaProvider(@Nonnull String str, @Nonnull String str2) {
        String outline75 = GeneratedOutlineSupport1.outline75(str, ":file://", str2);
        GeneratedOutlineSupport1.outline158("Send download finished event: ", outline75);
        this.metrics.recordOperationalEvent(HHOMetricsConstants.Event.GET_MEDIA_SUCCESS, HHOMetricsConstants.Component.STICKY_NOTES, HHOMetricsConstants.Subcomponent.PROVIDER);
        this.eventBus.publish(new Message.Builder().setEventType(DOWNLOAD_FINISHED_EVENT_TYPE).setPayload(outline75).build());
    }

    public void cleanupMediaCache() {
        this.disposable.add(Observable.timer(2L, TimeUnit.SECONDS).flatMapCompletable(new Function() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$0xJ6Kue5LS8I2yaPGUHfQ4kz-bQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return StickyNotesMediaProvider.this.lambda$cleanupMediaCache$2$StickyNotesMediaProvider((Long) obj);
            }
        }).andThen(deleteMemoryExceedingFiles(1)).andThen(Completable.fromAction(new Action() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$1r8EI1JP6hmwCVeIp-GjqnrATas
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                StickyNotesMediaProvider.this.lambda$cleanupMediaCache$1$StickyNotesMediaProvider();
            }
        })).subscribe());
    }

    @Override // com.amazon.alexa.hho.stickynotes.StickyNotesEventsSubscriber.Handler
    public void handleDeleteRequest(@Nonnull final String str) {
        GeneratedOutlineSupport1.outline158("Handle media delete request: ", str);
        this.metrics.recordOperationalEvent(HHOMetricsConstants.Event.REMOVE_MEDIA, HHOMetricsConstants.Component.STICKY_NOTES, HHOMetricsConstants.Subcomponent.PROVIDER);
        this.disposable.add(Observable.fromArray(StickyNoteMediaType.values()).map(new Function() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$U8r7B1ys2ZUmBl58rEMk-UiQLCA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return StickyNotesMediaProvider.this.lambda$handleDeleteRequest$7$StickyNotesMediaProvider(str, (StickyNoteMediaType) obj);
            }
        }).flatMapCompletable(new Function() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$q7mmZwzocYmcRzbadoztcUMe__Y
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return StickyNotesMediaProvider.this.lambda$handleDeleteRequest$9$StickyNotesMediaProvider((String) obj);
            }
        }).subscribe());
    }

    @Override // com.amazon.alexa.hho.stickynotes.StickyNotesEventsSubscriber.Handler
    public void handleDownloadRequest(@Nonnull MediaDownloadRequest mediaDownloadRequest) {
        final String createFileId = createFileId(mediaDownloadRequest);
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Handle media download request: ", createFileId, " prefetch: ");
        outline115.append(mediaDownloadRequest.prefetch);
        outline115.toString();
        if (mediaDownloadRequest.prefetch) {
            this.metrics.recordOperationalEvent(HHOMetricsConstants.Event.PREFETCH_MEDIA, HHOMetricsConstants.Component.STICKY_NOTES, HHOMetricsConstants.Subcomponent.PROVIDER);
        } else {
            this.metrics.recordOperationalEvent(HHOMetricsConstants.Event.GET_MEDIA, HHOMetricsConstants.Component.STICKY_NOTES, HHOMetricsConstants.Subcomponent.PROVIDER);
        }
        final HHOMetricsService.Timer createTimer = this.metrics.createTimer(HHOMetricsConstants.Event.GET_MEDIA, HHOMetricsConstants.Component.STICKY_NOTES, HHOMetricsConstants.Subcomponent.PROVIDER);
        this.disposable.add(this.cacheManager.get(createFileId).switchIfEmpty(downloadMediaIfNotInProgress(mediaDownloadRequest)).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$sIF9_RZV5gxGvBdp0gWLthfAmZ0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StickyNotesMediaProvider.this.lambda$handleDownloadRequest$3$StickyNotesMediaProvider(createFileId, (String) obj);
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$GwGlVZcl4KVDf3Fwe-FJPLt36e8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StickyNotesMediaProvider.this.lambda$handleDownloadRequest$4$StickyNotesMediaProvider(createTimer, (String) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$-Gdkui3nsCiGYK-AZIUkgZByx0Y
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StickyNotesMediaProvider.this.lambda$handleDownloadRequest$5$StickyNotesMediaProvider(createFileId, (Throwable) obj);
            }
        }).onErrorComplete().doOnComplete(new Action() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$yyEr5urgTo7Hj0V3w9fFTcSh3lU
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                StickyNotesMediaProvider.this.lambda$handleDownloadRequest$6$StickyNotesMediaProvider(createTimer);
            }
        }).subscribe());
    }

    @Override // com.amazon.alexa.hho.stickynotes.IdentitySubscriber.Handler
    public void handleIdentityChanged() {
        if (this.identityService.getUser(TAG) == null) {
            this.metrics.recordOperationalEvent(HHOMetricsConstants.Event.REMOVE_ALL_MEDIA, HHOMetricsConstants.Component.STICKY_NOTES, HHOMetricsConstants.Subcomponent.PROVIDER);
            this.disposable.add(this.cacheManager.deleteFiles(-1).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$WxZS3gt76qLqev1HZhkXhOosQuc
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    StickyNotesMediaProvider.this.lambda$null$25$StickyNotesMediaProvider((List) obj);
                }
            }).subscribe());
        }
    }

    public /* synthetic */ void lambda$cleanupMediaCache$1$StickyNotesMediaProvider() throws Throwable {
        this.metrics.recordOperationalEvent(HHOMetricsConstants.Event.CLEAN_UP_MEDIA, OperationalEventType.COUNTER, HHOMetricsConstants.Component.STICKY_NOTES, HHOMetricsConstants.Subcomponent.PROVIDER);
    }

    public /* synthetic */ CompletableSource lambda$cleanupMediaCache$2$StickyNotesMediaProvider(Long l) throws Throwable {
        return deleteOldFiles();
    }

    public /* synthetic */ CompletableSource lambda$deleteMemoryExceedingFiles$26$StickyNotesMediaProvider(final List list) throws Throwable {
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$8NR5xB6Iw0BWcss2EtetisE3PFM
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                StickyNotesMediaProvider.this.lambda$null$25$StickyNotesMediaProvider(list);
            }
        });
    }

    public /* synthetic */ CompletableSource lambda$deleteOldFiles$24$StickyNotesMediaProvider() throws Throwable {
        return this.cacheManager.deleteFiles(21).flatMapCompletable(new Function() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$nJO4GiuKFFuxiMINgGykjjGYfV8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return StickyNotesMediaProvider.this.lambda$null$23$StickyNotesMediaProvider((List) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$downloadAndCacheMedia$16$StickyNotesMediaProvider(MediaDownloadRequest mediaDownloadRequest, HHOMetricsService.Timer timer) throws Throwable {
        int ordinal = mediaDownloadRequest.mediaType.ordinal();
        if (ordinal == 0) {
            timer.setEventName(HHOMetricsConstants.Event.GET_VIDEO);
            return downloadVideo(mediaDownloadRequest);
        } else if (ordinal != 1) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Media type is not supported: ");
            outline107.append(mediaDownloadRequest.mediaType);
            return Single.error(new IllegalArgumentException(outline107.toString()));
        } else {
            timer.setEventName(HHOMetricsConstants.Event.GET_THUMBNAIL);
            return this.mediaDownloader.downloadVideoThumbnail(mediaDownloadRequest.mediaId);
        }
    }

    public /* synthetic */ SingleSource lambda$downloadAndCacheMedia$17$StickyNotesMediaProvider(MediaDownloadRequest mediaDownloadRequest, String str) throws Throwable {
        return this.cacheManager.moveToCache(createFileId(mediaDownloadRequest), str);
    }

    public /* synthetic */ void lambda$downloadAndCacheMedia$18$StickyNotesMediaProvider(HHOMetricsService.Timer timer, String str) throws Throwable {
        this.metrics.recordTimer(timer);
    }

    public /* synthetic */ void lambda$downloadAndCacheMedia$19$StickyNotesMediaProvider(HHOMetricsService.Timer timer, MediaDownloadRequest mediaDownloadRequest, Throwable th) throws Throwable {
        this.metrics.recordTimer(timer);
        this.disposable.add(this.cacheManager.freeReservedSpace(createFileId(mediaDownloadRequest)).subscribe());
    }

    public /* synthetic */ MaybeSource lambda$downloadMediaIfNotInProgress$12$StickyNotesMediaProvider(final MediaDownloadRequest mediaDownloadRequest) throws Throwable {
        return this.cacheManager.isSpaceReserved(createFileId(mediaDownloadRequest)).flatMapMaybe($$Lambda$StickyNotesMediaProvider$KMh8VsDS_g5Nynd8Am2MYhL0hV0.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$8A5pgJAICFOe9ftDI_wIVhKTQq8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return StickyNotesMediaProvider.this.lambda$null$11$StickyNotesMediaProvider(mediaDownloadRequest, (Boolean) obj);
            }
        });
    }

    public /* synthetic */ MaybeSource lambda$downloadMediaIfNotInProgress$15$StickyNotesMediaProvider(final MediaDownloadRequest mediaDownloadRequest) throws Throwable {
        return this.cacheManager.attemptToReserveSpace(createFileId(mediaDownloadRequest), 0L, Long.MAX_VALUE, VIDEO_FILE_PREDICATE).flatMapMaybe($$Lambda$StickyNotesMediaProvider$QpWTUyxWrP9D9dnLdcqH55WvhM.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$tR9KVFEydQIYOf3CT1Z2-IzBg70
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return StickyNotesMediaProvider.this.lambda$null$14$StickyNotesMediaProvider(mediaDownloadRequest, (Boolean) obj);
            }
        });
    }

    public /* synthetic */ boolean lambda$downloadVideo$20$StickyNotesMediaProvider(MediaDownloadRequest mediaDownloadRequest, Long l) {
        return this.cacheManager.attemptToReserveSpace(createFileId(mediaDownloadRequest), Primitives.unBox(l, Long.MAX_VALUE), 104857600L, VIDEO_FILE_PREDICATE).blockingGet().booleanValue();
    }

    public /* synthetic */ boolean lambda$downloadVideo$21$StickyNotesMediaProvider(MediaDownloadRequest mediaDownloadRequest, Long l) {
        return ((Boolean) this.cacheManager.reserveSpace(createFileId(mediaDownloadRequest), Primitives.unBox(l, 0L)).andThen(deleteMemoryExceedingFiles(0)).andThen(Single.just(true)).blockingGet()).booleanValue();
    }

    public /* synthetic */ CompletableSource lambda$handleDeleteRequest$9$StickyNotesMediaProvider(String str) throws Throwable {
        return this.cacheManager.delete(str).doOnError($$Lambda$StickyNotesMediaProvider$lBucenmJRjsTM2MIsUqZbZoaE.INSTANCE).onErrorComplete();
    }

    public /* synthetic */ void lambda$handleDownloadRequest$4$StickyNotesMediaProvider(HHOMetricsService.Timer timer, String str) throws Throwable {
        this.metrics.recordTimer(timer);
    }

    public /* synthetic */ void lambda$handleDownloadRequest$6$StickyNotesMediaProvider(HHOMetricsService.Timer timer) throws Throwable {
        this.metrics.recordTimer(timer);
        this.disposable.add(deleteOldFiles().onErrorComplete().subscribe());
    }

    public /* synthetic */ MaybeSource lambda$null$11$StickyNotesMediaProvider(MediaDownloadRequest mediaDownloadRequest, Boolean bool) throws Throwable {
        return downloadAndCacheMedia(mediaDownloadRequest);
    }

    public /* synthetic */ MaybeSource lambda$null$14$StickyNotesMediaProvider(MediaDownloadRequest mediaDownloadRequest, Boolean bool) throws Throwable {
        return downloadAndCacheMedia(mediaDownloadRequest);
    }

    public /* synthetic */ CompletableSource lambda$null$23$StickyNotesMediaProvider(final List list) throws Throwable {
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$yRoQVlOZZCI1zlaYtajah0tlzf4
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                StickyNotesMediaProvider.this.lambda$null$22$StickyNotesMediaProvider(list);
            }
        });
    }

    public void startListeningEventBus() {
        this.eventBus.subscribe(this.mediaRequestsSubscriber);
        this.eventBus.subscribe(this.identitySubscriber);
    }

    public void stopListeningEventBus() {
        this.eventBus.unsubscribe(this.mediaRequestsSubscriber);
        this.eventBus.unsubscribe(this.identitySubscriber);
        this.disposable.dispose();
    }

    @Nonnull
    private String createFileId(@Nonnull MediaDownloadRequest mediaDownloadRequest) {
        return lambda$handleDeleteRequest$7$StickyNotesMediaProvider(mediaDownloadRequest.mediaType, mediaDownloadRequest.mediaId);
    }
}
