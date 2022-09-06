package com.amazon.alexa.sharing.media;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.media.model.ContentManagerInterface;
import com.amazon.alexa.sharing.media.model.IMediaCache;
import com.amazon.alexa.sharing.util.ThreadUtils;
import com.amazon.comms.log.CommsLogger;
import java.io.File;
import java.io.IOException;
/* loaded from: classes10.dex */
public class MediaContentManager implements ContentManagerInterface {
    private static final String CACHE_DIR_NAME = "media_cache";
    private static final long CACHE_SIZE = 629145600;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MediaContentManager.class);
    private static final long MAX_PHOTO_SIZE = 125829120;
    @NonNull
    private final IMediaCache mediaCache;
    private final ThreadUtils threadUtils;

    public MediaContentManager(@NonNull Context context) {
        this.mediaCache = new MediaCache(new File(context.getFilesDir(), CACHE_DIR_NAME), CACHE_SIZE);
        this.threadUtils = new ThreadUtils();
    }

    @Override // com.amazon.alexa.sharing.media.model.ContentManagerInterface
    public synchronized void clearMediaCache() {
        LOG.i("clearing media disk cache");
        try {
            this.mediaCache.clearAll();
        } catch (IOException e) {
            LOG.e("IO exception while clearing media cache.", e);
        }
    }

    @Override // com.amazon.alexa.sharing.media.model.ContentManagerInterface
    public synchronized boolean existsInCache(String str) {
        this.threadUtils.checkNotMainThread();
        try {
        } catch (IOException e) {
            LOG.e("Call to existsInCache failed", e);
            return false;
        }
        return this.mediaCache.exists(str);
    }

    @Override // com.amazon.alexa.sharing.media.model.ContentManagerInterface
    public synchronized MediaFileContent getFromCache(String str) {
        this.threadUtils.checkNotMainThread();
        try {
        } catch (IOException e) {
            LOG.e("Getting media from cache failed", e);
            return null;
        }
        return this.mediaCache.get(str);
    }

    @Override // com.amazon.alexa.sharing.media.model.ContentManagerInterface
    public synchronized void putInCache(MediaFileContent mediaFileContent, String str, boolean z) {
        this.threadUtils.checkNotMainThread();
        try {
            this.mediaCache.put(str, mediaFileContent, z);
        } catch (IOException e) {
            LOG.e("Putting media into cache failed", e);
        }
    }

    @Override // com.amazon.alexa.sharing.media.model.ContentManagerInterface
    public synchronized boolean removeFromCache(String str) {
        this.threadUtils.checkNotMainThread();
        try {
        } catch (IOException e) {
            LOG.e("Removing media from cache failed", e);
            return false;
        }
        return this.mediaCache.remove(str);
    }

    @VisibleForTesting
    public MediaContentManager(MediaCache mediaCache, ThreadUtils threadUtils) {
        this.mediaCache = mediaCache;
        this.threadUtils = threadUtils;
    }
}
