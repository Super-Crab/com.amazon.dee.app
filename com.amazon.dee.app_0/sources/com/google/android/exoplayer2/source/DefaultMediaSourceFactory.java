package com.google.android.exoplayer2.source;

import android.content.Context;
import android.util.Pair;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManagerProvider;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.ads.AdsLoader;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes2.dex */
public final class DefaultMediaSourceFactory implements MediaSourceFactory {
    private static final String TAG = "DefaultMediaSourceFactory";
    @Nullable
    private AdsLoader.AdViewProvider adViewProvider;
    @Nullable
    private AdsLoaderProvider adsLoaderProvider;
    private final DataSource.Factory dataSourceFactory;
    private long liveMaxOffsetMs;
    private float liveMaxSpeed;
    private long liveMinOffsetMs;
    private float liveMinSpeed;
    private long liveTargetOffsetMs;
    @Nullable
    private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final SparseArray<MediaSourceFactory> mediaSourceFactories;
    private final int[] supportedTypes;

    /* loaded from: classes2.dex */
    public interface AdsLoaderProvider {
        @Nullable
        AdsLoader getAdsLoader(MediaItem.AdsConfiguration adsConfiguration);
    }

    public DefaultMediaSourceFactory(Context context) {
        this(new DefaultDataSourceFactory(context));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static SparseArray<MediaSourceFactory> loadDelegates(DataSource.Factory factory, ExtractorsFactory extractorsFactory) {
        SparseArray<MediaSourceFactory> sparseArray = new SparseArray<>();
        try {
            sparseArray.put(0, Class.forName("com.google.android.exoplayer2.source.dash.DashMediaSource$Factory").asSubclass(MediaSourceFactory.class).getConstructor(DataSource.Factory.class).newInstance(factory));
        } catch (Exception unused) {
        }
        try {
            sparseArray.put(1, Class.forName("com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource$Factory").asSubclass(MediaSourceFactory.class).getConstructor(DataSource.Factory.class).newInstance(factory));
        } catch (Exception unused2) {
        }
        try {
            sparseArray.put(2, Class.forName("com.google.android.exoplayer2.source.hls.HlsMediaSource$Factory").asSubclass(MediaSourceFactory.class).getConstructor(DataSource.Factory.class).newInstance(factory));
        } catch (Exception unused3) {
        }
        sparseArray.put(3, new ProgressiveMediaSource.Factory(factory, extractorsFactory));
        return sparseArray;
    }

    private static MediaSource maybeClipMediaSource(MediaItem mediaItem, MediaSource mediaSource) {
        MediaItem.ClippingProperties clippingProperties = mediaItem.clippingProperties;
        if (clippingProperties.startPositionMs == 0 && clippingProperties.endPositionMs == Long.MIN_VALUE && !clippingProperties.relativeToDefaultPosition) {
            return mediaSource;
        }
        long msToUs = C.msToUs(mediaItem.clippingProperties.startPositionMs);
        long msToUs2 = C.msToUs(mediaItem.clippingProperties.endPositionMs);
        MediaItem.ClippingProperties clippingProperties2 = mediaItem.clippingProperties;
        return new ClippingMediaSource(mediaSource, msToUs, msToUs2, !clippingProperties2.startsAtKeyFrame, clippingProperties2.relativeToLiveWindow, clippingProperties2.relativeToDefaultPosition);
    }

    private MediaSource maybeWrapWithAdsMediaSource(MediaItem mediaItem, MediaSource mediaSource) {
        Assertions.checkNotNull(mediaItem.playbackProperties);
        MediaItem.AdsConfiguration adsConfiguration = mediaItem.playbackProperties.adsConfiguration;
        if (adsConfiguration == null) {
            return mediaSource;
        }
        AdsLoaderProvider adsLoaderProvider = this.adsLoaderProvider;
        AdsLoader.AdViewProvider adViewProvider = this.adViewProvider;
        if (adsLoaderProvider != null && adViewProvider != null) {
            AdsLoader adsLoader = adsLoaderProvider.getAdsLoader(adsConfiguration);
            if (adsLoader == null) {
                Log.w(TAG, "Playing media without ads, as no AdsLoader was provided.");
                return mediaSource;
            }
            DataSpec dataSpec = new DataSpec(adsConfiguration.adTagUri);
            Object obj = adsConfiguration.adsId;
            return new AdsMediaSource(mediaSource, dataSpec, obj != null ? obj : Pair.create(mediaItem.mediaId, adsConfiguration.adTagUri), this, adsLoader, adViewProvider);
        }
        Log.w(TAG, "Playing media without ads. Configure ad support by calling setAdsLoaderProvider and setAdViewProvider.");
        return mediaSource;
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceFactory
    /* renamed from: createMediaSource */
    public MediaSource mo7418createMediaSource(MediaItem mediaItem) {
        Assertions.checkNotNull(mediaItem.playbackProperties);
        MediaItem.PlaybackProperties playbackProperties = mediaItem.playbackProperties;
        int inferContentTypeForUriAndMimeType = Util.inferContentTypeForUriAndMimeType(playbackProperties.uri, playbackProperties.mimeType);
        MediaSourceFactory mediaSourceFactory = this.mediaSourceFactories.get(inferContentTypeForUriAndMimeType);
        Assertions.checkNotNull(mediaSourceFactory, "No suitable media source factory found for content type: " + inferContentTypeForUriAndMimeType);
        if ((mediaItem.liveConfiguration.targetOffsetMs == C.TIME_UNSET && this.liveTargetOffsetMs != C.TIME_UNSET) || ((mediaItem.liveConfiguration.minPlaybackSpeed == -3.4028235E38f && this.liveMinSpeed != -3.4028235E38f) || ((mediaItem.liveConfiguration.maxPlaybackSpeed == -3.4028235E38f && this.liveMaxSpeed != -3.4028235E38f) || ((mediaItem.liveConfiguration.minOffsetMs == C.TIME_UNSET && this.liveMinOffsetMs != C.TIME_UNSET) || (mediaItem.liveConfiguration.maxOffsetMs == C.TIME_UNSET && this.liveMaxOffsetMs != C.TIME_UNSET))))) {
            MediaItem.Builder buildUpon = mediaItem.buildUpon();
            long j = mediaItem.liveConfiguration.targetOffsetMs;
            if (j == C.TIME_UNSET) {
                j = this.liveTargetOffsetMs;
            }
            MediaItem.Builder liveTargetOffsetMs = buildUpon.setLiveTargetOffsetMs(j);
            float f = mediaItem.liveConfiguration.minPlaybackSpeed;
            if (f == -3.4028235E38f) {
                f = this.liveMinSpeed;
            }
            MediaItem.Builder liveMinPlaybackSpeed = liveTargetOffsetMs.setLiveMinPlaybackSpeed(f);
            float f2 = mediaItem.liveConfiguration.maxPlaybackSpeed;
            if (f2 == -3.4028235E38f) {
                f2 = this.liveMaxSpeed;
            }
            MediaItem.Builder liveMaxPlaybackSpeed = liveMinPlaybackSpeed.setLiveMaxPlaybackSpeed(f2);
            long j2 = mediaItem.liveConfiguration.minOffsetMs;
            if (j2 == C.TIME_UNSET) {
                j2 = this.liveMinOffsetMs;
            }
            MediaItem.Builder liveMinOffsetMs = liveMaxPlaybackSpeed.setLiveMinOffsetMs(j2);
            long j3 = mediaItem.liveConfiguration.maxOffsetMs;
            if (j3 == C.TIME_UNSET) {
                j3 = this.liveMaxOffsetMs;
            }
            mediaItem = liveMinOffsetMs.setLiveMaxOffsetMs(j3).build();
        }
        MediaSource mo7418createMediaSource = mediaSourceFactory.mo7418createMediaSource(mediaItem);
        List<MediaItem.Subtitle> list = ((MediaItem.PlaybackProperties) Util.castNonNull(mediaItem.playbackProperties)).subtitles;
        if (!list.isEmpty()) {
            MediaSource[] mediaSourceArr = new MediaSource[list.size() + 1];
            int i = 0;
            mediaSourceArr[0] = mo7418createMediaSource;
            SingleSampleMediaSource.Factory loadErrorHandlingPolicy = new SingleSampleMediaSource.Factory(this.dataSourceFactory).setLoadErrorHandlingPolicy(this.loadErrorHandlingPolicy);
            while (i < list.size()) {
                int i2 = i + 1;
                mediaSourceArr[i2] = loadErrorHandlingPolicy.createMediaSource(list.get(i), C.TIME_UNSET);
                i = i2;
            }
            mo7418createMediaSource = new MergingMediaSource(mediaSourceArr);
        }
        return maybeWrapWithAdsMediaSource(mediaItem, maybeClipMediaSource(mediaItem, mo7418createMediaSource));
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceFactory
    public int[] getSupportedTypes() {
        int[] iArr = this.supportedTypes;
        return Arrays.copyOf(iArr, iArr.length);
    }

    public DefaultMediaSourceFactory setAdViewProvider(@Nullable AdsLoader.AdViewProvider adViewProvider) {
        this.adViewProvider = adViewProvider;
        return this;
    }

    public DefaultMediaSourceFactory setAdsLoaderProvider(@Nullable AdsLoaderProvider adsLoaderProvider) {
        this.adsLoaderProvider = adsLoaderProvider;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMaxOffsetMs(long j) {
        this.liveMaxOffsetMs = j;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMaxSpeed(float f) {
        this.liveMaxSpeed = f;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMinOffsetMs(long j) {
        this.liveMinOffsetMs = j;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMinSpeed(float f) {
        this.liveMinSpeed = f;
        return this;
    }

    public DefaultMediaSourceFactory setLiveTargetOffsetMs(long j) {
        this.liveTargetOffsetMs = j;
        return this;
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceFactory
    @Deprecated
    /* renamed from: setStreamKeys  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ MediaSourceFactory mo7424setStreamKeys(@Nullable List list) {
        return mo7424setStreamKeys((List<StreamKey>) list);
    }

    public DefaultMediaSourceFactory(Context context, ExtractorsFactory extractorsFactory) {
        this(new DefaultDataSourceFactory(context), extractorsFactory);
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceFactory
    /* renamed from: setDrmHttpDataSourceFactory */
    public DefaultMediaSourceFactory mo7419setDrmHttpDataSourceFactory(@Nullable HttpDataSource.Factory factory) {
        for (int i = 0; i < this.mediaSourceFactories.size(); i++) {
            this.mediaSourceFactories.valueAt(i).mo7419setDrmHttpDataSourceFactory(factory);
        }
        return this;
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceFactory
    /* renamed from: setDrmSessionManager */
    public DefaultMediaSourceFactory mo7420setDrmSessionManager(@Nullable DrmSessionManager drmSessionManager) {
        for (int i = 0; i < this.mediaSourceFactories.size(); i++) {
            this.mediaSourceFactories.valueAt(i).mo7420setDrmSessionManager(drmSessionManager);
        }
        return this;
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceFactory
    /* renamed from: setDrmSessionManagerProvider */
    public DefaultMediaSourceFactory mo7421setDrmSessionManagerProvider(@Nullable DrmSessionManagerProvider drmSessionManagerProvider) {
        for (int i = 0; i < this.mediaSourceFactories.size(); i++) {
            this.mediaSourceFactories.valueAt(i).mo7421setDrmSessionManagerProvider(drmSessionManagerProvider);
        }
        return this;
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceFactory
    /* renamed from: setDrmUserAgent */
    public DefaultMediaSourceFactory mo7422setDrmUserAgent(@Nullable String str) {
        for (int i = 0; i < this.mediaSourceFactories.size(); i++) {
            this.mediaSourceFactories.valueAt(i).mo7422setDrmUserAgent(str);
        }
        return this;
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceFactory
    /* renamed from: setLoadErrorHandlingPolicy */
    public DefaultMediaSourceFactory mo7423setLoadErrorHandlingPolicy(@Nullable LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        for (int i = 0; i < this.mediaSourceFactories.size(); i++) {
            this.mediaSourceFactories.valueAt(i).mo7423setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
        }
        return this;
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceFactory
    @Deprecated
    /* renamed from: setStreamKeys */
    public DefaultMediaSourceFactory mo7424setStreamKeys(@Nullable List<StreamKey> list) {
        for (int i = 0; i < this.mediaSourceFactories.size(); i++) {
            this.mediaSourceFactories.valueAt(i).mo7424setStreamKeys(list);
        }
        return this;
    }

    public DefaultMediaSourceFactory(DataSource.Factory factory) {
        this(factory, new DefaultExtractorsFactory());
    }

    public DefaultMediaSourceFactory(DataSource.Factory factory, ExtractorsFactory extractorsFactory) {
        this.dataSourceFactory = factory;
        this.mediaSourceFactories = loadDelegates(factory, extractorsFactory);
        this.supportedTypes = new int[this.mediaSourceFactories.size()];
        for (int i = 0; i < this.mediaSourceFactories.size(); i++) {
            this.supportedTypes[i] = this.mediaSourceFactories.keyAt(i);
        }
        this.liveTargetOffsetMs = C.TIME_UNSET;
        this.liveMinOffsetMs = C.TIME_UNSET;
        this.liveMaxOffsetMs = C.TIME_UNSET;
        this.liveMinSpeed = -3.4028235E38f;
        this.liveMaxSpeed = -3.4028235E38f;
    }
}
