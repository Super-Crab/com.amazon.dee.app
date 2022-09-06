package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManagerProvider;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
@Deprecated
/* loaded from: classes2.dex */
public final class ExtractorMediaSource extends CompositeMediaSource<Void> {
    @Deprecated
    public static final int DEFAULT_LOADING_CHECK_INTERVAL_BYTES = 1048576;
    private final ProgressiveMediaSource progressiveMediaSource;

    @Deprecated
    /* loaded from: classes2.dex */
    public interface EventListener {
        void onLoadError(IOException iOException);
    }

    @Deprecated
    /* loaded from: classes2.dex */
    private static final class EventListenerWrapper implements MediaSourceEventListener {
        private final EventListener eventListener;

        public EventListenerWrapper(EventListener eventListener) {
            this.eventListener = (EventListener) Assertions.checkNotNull(eventListener);
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onLoadError(int i, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            this.eventListener.onLoadError(iOException);
        }
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public static final class Factory implements MediaSourceFactory {
        @Nullable
        private String customCacheKey;
        private final DataSource.Factory dataSourceFactory;
        @Nullable
        private Object tag;
        private ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
        private int continueLoadingCheckIntervalBytes = 1048576;

        public Factory(DataSource.Factory factory) {
            this.dataSourceFactory = factory;
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        public int[] getSupportedTypes() {
            return new int[]{3};
        }

        public Factory setContinueLoadingCheckIntervalBytes(int i) {
            this.continueLoadingCheckIntervalBytes = i;
            return this;
        }

        public Factory setCustomCacheKey(@Nullable String str) {
            this.customCacheKey = str;
            return this;
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        @Deprecated
        /* renamed from: setDrmHttpDataSourceFactory */
        public MediaSourceFactory mo7419setDrmHttpDataSourceFactory(@Nullable HttpDataSource.Factory factory) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        @Deprecated
        /* renamed from: setDrmUserAgent */
        public MediaSourceFactory mo7422setDrmUserAgent(@Nullable String str) {
            throw new UnsupportedOperationException();
        }

        public Factory setExtractorsFactory(@Nullable ExtractorsFactory extractorsFactory) {
            if (extractorsFactory == null) {
                extractorsFactory = new DefaultExtractorsFactory();
            }
            this.extractorsFactory = extractorsFactory;
            return this;
        }

        @Deprecated
        public Factory setTag(@Nullable Object obj) {
            this.tag = obj;
            return this;
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        @Deprecated
        /* renamed from: setDrmSessionManager */
        public Factory mo7420setDrmSessionManager(@Nullable DrmSessionManager drmSessionManager) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        @Deprecated
        /* renamed from: setDrmSessionManagerProvider */
        public Factory mo7421setDrmSessionManagerProvider(@Nullable DrmSessionManagerProvider drmSessionManagerProvider) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        /* renamed from: setLoadErrorHandlingPolicy */
        public Factory mo7423setLoadErrorHandlingPolicy(@Nullable LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            if (loadErrorHandlingPolicy == null) {
                loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
            }
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
            return this;
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        @Deprecated
        /* renamed from: createMediaSource */
        public ExtractorMediaSource mo7417createMediaSource(Uri uri) {
            return mo7418createMediaSource(new MediaItem.Builder().setUri(uri).build());
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceFactory
        /* renamed from: createMediaSource */
        public ExtractorMediaSource mo7418createMediaSource(MediaItem mediaItem) {
            Assertions.checkNotNull(mediaItem.playbackProperties);
            MediaItem.PlaybackProperties playbackProperties = mediaItem.playbackProperties;
            Uri uri = playbackProperties.uri;
            DataSource.Factory factory = this.dataSourceFactory;
            ExtractorsFactory extractorsFactory = this.extractorsFactory;
            LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.loadErrorHandlingPolicy;
            String str = this.customCacheKey;
            int i = this.continueLoadingCheckIntervalBytes;
            Object obj = playbackProperties.tag;
            if (obj == null) {
                obj = this.tag;
            }
            return new ExtractorMediaSource(uri, factory, extractorsFactory, loadErrorHandlingPolicy, str, i, obj);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    /* renamed from: createPeriod */
    public MediaPeriod mo7373createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        return this.progressiveMediaSource.mo7373createPeriod(mediaPeriodId, allocator, j);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaItem getMediaItem() {
        return this.progressiveMediaSource.getMediaItem();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    @Nullable
    @Deprecated
    public Object getTag() {
        return this.progressiveMediaSource.getTag();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.BaseMediaSource
    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        prepareChildSource(null, this.progressiveMediaSource);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        this.progressiveMediaSource.releasePeriod(mediaPeriod);
    }

    @Deprecated
    public ExtractorMediaSource(Uri uri, DataSource.Factory factory, ExtractorsFactory extractorsFactory, @Nullable Handler handler, @Nullable EventListener eventListener) {
        this(uri, factory, extractorsFactory, handler, eventListener, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    public void onChildSourceInfoRefreshed(@Nullable Void r1, MediaSource mediaSource, Timeline timeline) {
        refreshSourceInfo(timeline);
    }

    @Deprecated
    public ExtractorMediaSource(Uri uri, DataSource.Factory factory, ExtractorsFactory extractorsFactory, @Nullable Handler handler, @Nullable EventListener eventListener, @Nullable String str) {
        this(uri, factory, extractorsFactory, handler, eventListener, str, 1048576);
    }

    @Deprecated
    public ExtractorMediaSource(Uri uri, DataSource.Factory factory, ExtractorsFactory extractorsFactory, @Nullable Handler handler, @Nullable EventListener eventListener, @Nullable String str, int i) {
        this(uri, factory, extractorsFactory, new DefaultLoadErrorHandlingPolicy(), str, i, (Object) null);
        if (eventListener == null || handler == null) {
            return;
        }
        addEventListener(handler, new EventListenerWrapper(eventListener));
    }

    private ExtractorMediaSource(Uri uri, DataSource.Factory factory, ExtractorsFactory extractorsFactory, LoadErrorHandlingPolicy loadErrorHandlingPolicy, @Nullable String str, int i, @Nullable Object obj) {
        this.progressiveMediaSource = new ProgressiveMediaSource(new MediaItem.Builder().setUri(uri).setCustomCacheKey(str).setTag(obj).build(), factory, extractorsFactory, DrmSessionManager.DRM_UNSUPPORTED, loadErrorHandlingPolicy, i);
    }
}
