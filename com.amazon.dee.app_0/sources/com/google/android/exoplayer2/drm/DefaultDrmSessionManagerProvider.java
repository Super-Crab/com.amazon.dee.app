package com.google.android.exoplayer2.drm;

import android.net.Uri;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.common.primitives.Ints;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
/* loaded from: classes2.dex */
public final class DefaultDrmSessionManagerProvider implements DrmSessionManagerProvider {
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private MediaItem.DrmConfiguration drmConfiguration;
    @Nullable
    private HttpDataSource.Factory drmHttpDataSourceFactory;
    private final Object lock = new Object();
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private DrmSessionManager manager;
    @Nullable
    private String userAgent;

    @RequiresApi(18)
    private DrmSessionManager createManager(MediaItem.DrmConfiguration drmConfiguration) {
        HttpDataSource.Factory factory = this.drmHttpDataSourceFactory;
        if (factory == null) {
            factory = new DefaultHttpDataSource.Factory().setUserAgent(this.userAgent);
        }
        Uri uri = drmConfiguration.licenseUri;
        HttpMediaDrmCallback httpMediaDrmCallback = new HttpMediaDrmCallback(uri == null ? null : uri.toString(), drmConfiguration.forceDefaultLicenseUri, factory);
        for (Map.Entry<String, String> entry : drmConfiguration.requestHeaders.entrySet()) {
            httpMediaDrmCallback.setKeyRequestProperty(entry.getKey(), entry.getValue());
        }
        DefaultDrmSessionManager build = new DefaultDrmSessionManager.Builder().setUuidAndExoMediaDrmProvider(drmConfiguration.uuid, FrameworkMediaDrm.DEFAULT_PROVIDER).setMultiSession(drmConfiguration.multiSession).setPlayClearSamplesWithoutKeys(drmConfiguration.playClearContentWithoutKey).setUseDrmSessionsForClearContent(Ints.toArray(drmConfiguration.sessionForClearTypes)).build(httpMediaDrmCallback);
        build.setMode(0, drmConfiguration.getKeySetId());
        return build;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSessionManagerProvider
    public DrmSessionManager get(MediaItem mediaItem) {
        DrmSessionManager drmSessionManager;
        Assertions.checkNotNull(mediaItem.playbackProperties);
        MediaItem.DrmConfiguration drmConfiguration = mediaItem.playbackProperties.drmConfiguration;
        if (drmConfiguration != null && Util.SDK_INT >= 18) {
            synchronized (this.lock) {
                if (!Util.areEqual(drmConfiguration, this.drmConfiguration)) {
                    this.drmConfiguration = drmConfiguration;
                    this.manager = createManager(drmConfiguration);
                }
                drmSessionManager = (DrmSessionManager) Assertions.checkNotNull(this.manager);
            }
            return drmSessionManager;
        }
        return DrmSessionManager.DRM_UNSUPPORTED;
    }

    public void setDrmHttpDataSourceFactory(@Nullable HttpDataSource.Factory factory) {
        this.drmHttpDataSourceFactory = factory;
    }

    public void setDrmUserAgent(@Nullable String str) {
        this.userAgent = str;
    }
}
