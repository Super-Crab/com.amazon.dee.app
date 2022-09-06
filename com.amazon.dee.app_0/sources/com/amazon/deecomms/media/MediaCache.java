package com.amazon.deecomms.media;

import android.text.TextUtils;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.media.model.MediaFileContent;
import com.amazon.deecomms.media.model.MediaStreamContent;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.bumptech.glide.disklrucache.DiskLruCache;
import java.io.File;
import java.io.IOException;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
/* loaded from: classes12.dex */
public class MediaCache implements IMediaCache {
    private static final int CACHE_CONTENT = 1;
    private static final int CACHE_CONTENT_TYPE = 0;
    private static final int CACHE_VALUE_LENGTH = 2;
    private static final int CACHE_VERSION = 1;
    private static final String DEFAULT_REPLACEMENT_CHAR = "_";
    private static final int KEY_MAX_LENGTH = 120;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MediaCache.class);
    private static final String REGEX_PATTERN = "[^\\w\\d_-]";
    private final File directory;
    private DiskLruCache mDiskCache;
    private final long size;

    public MediaCache(File file, long j) {
        this.directory = file;
        this.size = j;
        createDiskCache();
    }

    private void createDiskCache() {
        try {
            this.mDiskCache = DiskLruCache.open(this.directory, 1, 2, this.size);
        } catch (IOException e) {
            this.mDiskCache = null;
            LOG.e("Error occurred while initializing disk cache", e);
        }
    }

    private void ensureUsable() throws IOException {
        if (isInvalid()) {
            createDiskCache();
        }
    }

    private boolean isInvalid() throws IOException {
        DiskLruCache diskLruCache = this.mDiskCache;
        return diskLruCache == null || diskLruCache.isClosed();
    }

    private String sanitizeKey(String str) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Before sanitizing, key: ");
        outline1.append(LOG.sensitive(str));
        commsLogger.d(outline1.toString());
        if (!TextUtils.isEmpty(str)) {
            String replaceAll = str.replaceAll(REGEX_PATTERN, "_");
            String substring = replaceAll.toLowerCase().substring(0, Math.min(replaceAll.length(), 120));
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("After sanitizing, result: ");
            outline12.append(LOG.sensitive(substring));
            commsLogger2.d(outline12.toString());
            return substring;
        }
        throw new IllegalArgumentException("Key cannot be of size 0");
    }

    @Override // com.amazon.deecomms.media.IMediaCache
    public void clearAll() throws IOException {
        this.mDiskCache.delete();
    }

    @Override // com.amazon.deecomms.media.IMediaCache
    public boolean exists(String str) throws IOException {
        if (isInvalid()) {
            return false;
        }
        String sanitizeKey = sanitizeKey(str);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("In MediaCache exists, key: ");
        outline1.append(LOG.sensitive(sanitizeKey));
        commsLogger.d(outline1.toString());
        return this.mDiskCache.get(sanitizeKey) != null;
    }

    @Override // com.amazon.deecomms.media.IMediaCache
    public MediaFileContent get(String str) throws IOException {
        if (isInvalid()) {
            return null;
        }
        String sanitizeKey = sanitizeKey(str);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("In MediaCache get, key: ");
        outline1.append(LOG.sensitive(sanitizeKey));
        commsLogger.i(outline1.toString());
        DiskLruCache.Value value = this.mDiskCache.get(sanitizeKey);
        if (value == null) {
            LOG.i("Snapshot is null for key: ");
            return null;
        }
        MediaFileContent mediaFileContent = new MediaFileContent();
        mediaFileContent.setMediaId(str);
        BufferedSource buffer = Okio.buffer(Okio.source(value.getFile(0)));
        try {
            mediaFileContent.setContentType(buffer.readUtf8Line());
            CommsLogger commsLogger2 = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("MediaType is: ");
            sb.append(LOG.sensitive(mediaFileContent.getContentType()));
            commsLogger2.i(sb.toString());
            buffer.close();
            mediaFileContent.setFile(value.getFile(1));
            return mediaFileContent;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (buffer != null) {
                    try {
                        buffer.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.media.IMediaCache
    public File getDirectory() {
        return this.mDiskCache.getDirectory();
    }

    @Override // com.amazon.deecomms.media.IMediaCache
    public void put(String str, MediaStreamContent mediaStreamContent, boolean z) throws IOException {
        put(str, mediaStreamContent.getContentType(), Okio.source(mediaStreamContent.getInputStream()), z);
    }

    @Override // com.amazon.deecomms.media.IMediaCache
    public boolean remove(String str) throws IOException {
        if (isInvalid()) {
            return false;
        }
        String sanitizeKey = sanitizeKey(str);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("In MediaCache remove, key: ");
        outline1.append(LOG.sensitive(sanitizeKey));
        commsLogger.d(outline1.toString());
        return !exists(str) || this.mDiskCache.remove(sanitizeKey);
    }

    @Override // com.amazon.deecomms.media.IMediaCache
    public void put(String str, MediaFileContent mediaFileContent, boolean z) throws IOException {
        put(str, mediaFileContent.getContentType(), Okio.source(mediaFileContent.getFile()), z);
    }

    private void put(String str, String str2, Source source, boolean z) throws IOException {
        if (exists(str)) {
            if (!z) {
                LOG.i("Media already in cache");
                return;
            }
            LOG.i("Overwriting content in cache");
        }
        if (str2 == null) {
            LOG.e("Content type is null");
            return;
        }
        ensureUsable();
        String sanitizeKey = sanitizeKey(str);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Putting in cache, filteredKey: ");
        outline1.append(LOG.sensitive(sanitizeKey));
        commsLogger.d(outline1.toString());
        DiskLruCache.Editor editor = null;
        try {
            DiskLruCache.Editor edit = this.mDiskCache.edit(sanitizeKey);
            if (edit == null) {
                CommsLogger commsLogger2 = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("Editor is null for the key: ");
                sb.append(str);
                commsLogger2.e(sb.toString());
                if (edit == null) {
                    return;
                }
                edit.abort();
                return;
            }
            BufferedSink buffer = Okio.buffer(Okio.sink(edit.getFile(0)));
            buffer.mo12607writeUtf8(str2).mo12596writeByte(10);
            CommsLogger commsLogger3 = LOG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Setting media type to: ");
            sb2.append(LOG.sensitive(str2));
            commsLogger3.d(sb2.toString());
            buffer.close();
            BufferedSink buffer2 = Okio.buffer(Okio.sink(edit.getFile(1)));
            buffer2.writeAll(source);
            if (source != null) {
                source.close();
            }
            buffer2.close();
            edit.commit();
        } catch (Throwable th) {
            if (0 != 0) {
                editor.abort();
            }
            throw th;
        }
    }
}
