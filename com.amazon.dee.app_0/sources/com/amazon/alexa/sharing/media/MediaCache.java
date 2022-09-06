package com.amazon.alexa.sharing.media;

import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.media.model.IMediaCache;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.disklrucache.DiskLruCache;
import java.io.File;
import java.io.IOException;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
/* loaded from: classes10.dex */
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
        } catch (Exception e) {
            this.mDiskCache = null;
            LOG.e("Error occurred while initializing disk cache", e);
        }
    }

    private boolean isInvalid() {
        DiskLruCache diskLruCache = this.mDiskCache;
        return diskLruCache == null || diskLruCache.isClosed();
    }

    @Override // com.amazon.alexa.sharing.media.model.IMediaCache
    public void clearAll() throws IOException {
        this.mDiskCache.delete();
    }

    void ensureUsable() {
        if (isInvalid()) {
            createDiskCache();
        }
    }

    @Override // com.amazon.alexa.sharing.media.model.IMediaCache
    public boolean exists(String str) throws IOException {
        if (isInvalid()) {
            return false;
        }
        String sanitizeKey = sanitizeKey(str);
        CommsLogger commsLogger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("In MediaCache exists, key: ");
        outline107.append(LOG.sensitive(sanitizeKey));
        commsLogger.d(outline107.toString());
        return this.mDiskCache.get(sanitizeKey) != null;
    }

    @Override // com.amazon.alexa.sharing.media.model.IMediaCache
    public MediaFileContent get(String str) throws IOException {
        if (isInvalid()) {
            return null;
        }
        String sanitizeKey = sanitizeKey(str);
        CommsLogger commsLogger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("In MediaCache get, key: ");
        outline107.append(LOG.sensitive(sanitizeKey));
        commsLogger.i(outline107.toString());
        DiskLruCache.Value value = this.mDiskCache.get(sanitizeKey);
        if (value == null) {
            LOG.i("Snapshot is null for key: ");
            return null;
        }
        MediaFileContent mediaFileContent = new MediaFileContent();
        mediaFileContent.setMediaId(str);
        setCacheContentType(mediaFileContent, value.getFile(0));
        mediaFileContent.setFile(value.getFile(1));
        return mediaFileContent;
    }

    @Override // com.amazon.alexa.sharing.media.model.IMediaCache
    public File getDirectory() {
        return this.mDiskCache.getDirectory();
    }

    public DiskLruCache getDiskLruCache() {
        return this.mDiskCache;
    }

    @Override // com.amazon.alexa.sharing.media.model.IMediaCache
    public void put(String str, MediaFileContent mediaFileContent, boolean z) throws IOException {
        put(str, mediaFileContent.getContentType(), Okio.source(mediaFileContent.getFile()), z);
    }

    @Override // com.amazon.alexa.sharing.media.model.IMediaCache
    public boolean remove(String str) throws IOException {
        if (isInvalid()) {
            return false;
        }
        String sanitizeKey = sanitizeKey(str);
        CommsLogger commsLogger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("In MediaCache remove, key: ");
        outline107.append(LOG.sensitive(sanitizeKey));
        commsLogger.d(outline107.toString());
        return !exists(str) || this.mDiskCache.remove(sanitizeKey);
    }

    String sanitizeKey(String str) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Before sanitizing, key: ");
        outline107.append(LOG.sensitive(str));
        commsLogger.d(outline107.toString());
        if (str != null && !str.isEmpty()) {
            String replaceAll = str.replaceAll(REGEX_PATTERN, "_");
            String substring = replaceAll.toLowerCase().substring(0, Math.min(replaceAll.length(), 120));
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("After sanitizing, result: ");
            outline1072.append(LOG.sensitive(substring));
            commsLogger2.d(outline1072.toString());
            return substring;
        }
        throw new IllegalArgumentException("Key cannot be of size 0");
    }

    void setCacheContentType(MediaFileContent mediaFileContent, File file) throws IOException {
        BufferedSource buffer = Okio.buffer(Okio.source(file));
        try {
            mediaFileContent.setContentType(buffer.readUtf8Line());
            CommsLogger commsLogger = LOG;
            commsLogger.i("MediaType is: " + LOG.sensitive(mediaFileContent.getContentType()));
            buffer.close();
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

    void writeContentSource(DiskLruCache.Editor editor, Source source) throws IOException {
        BufferedSink buffer = Okio.buffer(Okio.sink(editor.getFile(1)));
        try {
            buffer.writeAll(source);
            if (source != null) {
                source.close();
            }
            buffer.close();
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

    void writeContentType(DiskLruCache.Editor editor, String str) throws IOException {
        BufferedSink buffer = Okio.buffer(Okio.sink(editor.getFile(0)));
        try {
            buffer.mo12607writeUtf8(str).mo12596writeByte(10);
            CommsLogger commsLogger = LOG;
            commsLogger.d("Setting media type to: " + LOG.sensitive(str));
            buffer.close();
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

    void put(String str, String str2, Source source, boolean z) throws IOException {
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Putting in cache, filteredKey: ");
        outline107.append(LOG.sensitive(sanitizeKey));
        commsLogger.d(outline107.toString());
        DiskLruCache.Editor editor = null;
        try {
            DiskLruCache.Editor edit = this.mDiskCache.edit(sanitizeKey);
            if (edit == null) {
                CommsLogger commsLogger2 = LOG;
                commsLogger2.e("Editor is null for the key: " + str);
                if (edit == null) {
                    return;
                }
                edit.abort();
                return;
            }
            writeContentType(edit, str2);
            writeContentSource(edit, source);
            edit.commit();
        } catch (Throwable th) {
            if (0 != 0) {
                editor.abort();
            }
            throw th;
        }
    }

    public MediaCache(File file, long j, DiskLruCache diskLruCache) {
        this.directory = file;
        this.size = j;
        this.mDiskCache = diskLruCache;
    }
}
