package com.google.android.exoplayer2.source;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManagerProvider;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import java.util.List;
/* loaded from: classes2.dex */
public interface MediaSourceFactory {
    @Deprecated
    /* renamed from: createMediaSource */
    default MediaSource mo7417createMediaSource(Uri uri) {
        return mo7418createMediaSource(MediaItem.fromUri(uri));
    }

    /* renamed from: createMediaSource */
    MediaSource mo7418createMediaSource(MediaItem mediaItem);

    int[] getSupportedTypes();

    @Deprecated
    /* renamed from: setDrmHttpDataSourceFactory */
    MediaSourceFactory mo7419setDrmHttpDataSourceFactory(@Nullable HttpDataSource.Factory factory);

    @Deprecated
    /* renamed from: setDrmSessionManager */
    MediaSourceFactory mo7420setDrmSessionManager(@Nullable DrmSessionManager drmSessionManager);

    /* renamed from: setDrmSessionManagerProvider */
    MediaSourceFactory mo7421setDrmSessionManagerProvider(@Nullable DrmSessionManagerProvider drmSessionManagerProvider);

    @Deprecated
    /* renamed from: setDrmUserAgent */
    MediaSourceFactory mo7422setDrmUserAgent(@Nullable String str);

    /* renamed from: setLoadErrorHandlingPolicy */
    MediaSourceFactory mo7423setLoadErrorHandlingPolicy(@Nullable LoadErrorHandlingPolicy loadErrorHandlingPolicy);

    @Deprecated
    /* renamed from: setStreamKeys */
    default MediaSourceFactory mo7424setStreamKeys(@Nullable List<StreamKey> list) {
        return this;
    }
}
