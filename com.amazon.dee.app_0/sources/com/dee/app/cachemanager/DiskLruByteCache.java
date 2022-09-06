package com.dee.app.cachemanager;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.function.Callback;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.jakewharton.disklrucache.DiskLruCache;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
/* loaded from: classes9.dex */
public class DiskLruByteCache implements ByteCache {
    private static final String LOG_TAG = "DiskLruByteCache";
    private static final int VALUE_COUNT = 1;
    private static final int VALUE_INDEX = 0;
    private final int appVersion;
    @Nullable
    private DiskLruCache cache;
    private final Context context;
    private final int maxSizeBytes;
    private final String name;

    public DiskLruByteCache(@NonNull Context context, @NonNull final String str, int i, int i2, Executor executor) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(str);
        Preconditions.checkArgument(i2 > 0);
        this.context = context;
        this.name = str;
        this.appVersion = i;
        this.maxSizeBytes = i2 * 1000 * 1000;
        executor.execute(new Runnable() { // from class: com.dee.app.cachemanager.-$$Lambda$DiskLruByteCache$NID0ABw7z4g2ylCtEMh9gQwA-ns
            @Override // java.lang.Runnable
            public final void run() {
                DiskLruByteCache.this.lambda$new$0$DiskLruByteCache(str);
            }
        });
    }

    private static File getCacheDir(@NonNull Context context, @NonNull String str) {
        int i = Build.VERSION.SDK_INT;
        return new File(context.getNoBackupFilesDir().getAbsolutePath(), str);
    }

    private byte[] readEntryFromInputStream(InputStream inputStream) throws IOException {
        BufferedSource buffer = Okio.buffer(Okio.source(inputStream));
        try {
            byte[] readByteArray = buffer.readByteArray();
            buffer.close();
            return readByteArray;
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

    private void writeEntryToOutputStream(byte[] bArr, OutputStream outputStream) throws IOException {
        BufferedSink buffer = Okio.buffer(Okio.sink(outputStream));
        try {
            buffer.mo12594write(bArr);
            buffer.flush();
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

    @Override // com.dee.app.cachemanager.ByteCache
    public void clear() throws CacheException {
        try {
            if (this.cache != null) {
                this.cache.close();
                this.cache.delete();
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Cache failed to close properly.", e);
        }
        try {
            try {
                File cacheDir = getCacheDir(this.context, this.name);
                if (cacheDir.delete()) {
                    return;
                }
                Log.e(LOG_TAG, "Failed to delete cache directory.");
                if (cacheDir.exists()) {
                    if (cacheDir.isDirectory() && cacheDir.list().length > 0) {
                        throw new CacheException("Failed to clear cache - " + cacheDir.list().length + "files still exist in the directory");
                    }
                    throw new CacheException("Failed to clear cache - unknown reason");
                }
                throw new CacheException("Failed to clear cache - no directory exists");
            } catch (SecurityException e2) {
                Log.e(LOG_TAG, "Failed to clear cache due to permissions.", e2);
                throw new CacheException("Failed to clear cache due to permissions", e2);
            }
        } finally {
            this.cache = null;
        }
    }

    @Override // com.dee.app.cachemanager.ByteCache
    public void forEach(@NonNull Callback<byte[]> callback) throws CacheException {
        File[] listFiles = this.cache.getDirectory().listFiles();
        new ArrayList();
        for (File file : listFiles) {
            String str = file.getName().split(ArcusConfig.PATH_SEPARATOR)[0];
            if (!str.equals(okhttp3.internal.cache.DiskLruCache.JOURNAL_FILE)) {
                Optional<byte[]> optional = get(str);
                if (optional.isPresent()) {
                    callback.accept(optional.get());
                }
            }
        }
    }

    @Override // com.dee.app.cachemanager.ByteCache
    public Optional<byte[]> get(@NonNull String str) throws CacheException {
        Preconditions.checkNotNull(str);
        try {
            DiskLruCache.Snapshot snapshot = getCache().get(str);
            if (snapshot == null) {
                return Optional.absent();
            }
            InputStream inputStream = snapshot.getInputStream(0);
            if (inputStream != null) {
                Optional<byte[]> of = Optional.of(readEntryFromInputStream(inputStream));
                inputStream.close();
                return of;
            }
            Optional<byte[]> absent = Optional.absent();
            if (inputStream != null) {
                inputStream.close();
            }
            return absent;
        } catch (IOException e) {
            Log.e(LOG_TAG, "IOException", e);
            throw new CacheException("IOException while get", e);
        }
    }

    @NonNull
    protected final DiskLruCache getCache() {
        if (this.cache == null) {
            File cacheDir = getCacheDir(this.context, this.name);
            try {
                this.cache = DiskLruCache.open(cacheDir, this.appVersion, 1, this.maxSizeBytes);
            } catch (IOException e) {
                File file = new File(cacheDir, okhttp3.internal.cache.DiskLruCache.JOURNAL_FILE);
                File file2 = new File(cacheDir, okhttp3.internal.cache.DiskLruCache.JOURNAL_FILE_TEMP);
                throw new CacheException(this.name + " cache failed to open. cacheDir canRead: " + cacheDir.canRead() + ", canWrite: " + cacheDir.canWrite() + "; journal file canRead: " + file.canRead() + ", canWrite: " + file.canWrite() + ", exists: " + file.exists() + ", journal tmp canRead: " + file2.canRead() + ", canWrite: " + file2.canWrite(), e);
            }
        }
        return this.cache;
    }

    public /* synthetic */ void lambda$new$0$DiskLruByteCache(String str) {
        try {
            getCache();
        } catch (CacheException unused) {
            GeneratedOutlineSupport1.outline162("DiskLruByteCache constructor get CacheException in ", str, LOG_TAG);
        }
    }

    @Override // com.dee.app.cachemanager.ByteCache
    public void put(@NonNull String str, @NonNull byte[] bArr) throws CacheException {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(bArr);
        try {
            DiskLruCache.Editor edit = getCache().edit(str);
            if (edit != null) {
                try {
                    OutputStream newOutputStream = edit.newOutputStream(0);
                    if (newOutputStream != null) {
                        writeEntryToOutputStream(bArr, newOutputStream);
                        edit.commit();
                    } else {
                        Log.e(LOG_TAG, "outputStream was null while putting value.");
                        edit.abort();
                    }
                    if (newOutputStream == null) {
                        return;
                    }
                    newOutputStream.close();
                    return;
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Exception occurring while writing & committing value. Attempting abort.", e);
                    edit.abortUnlessCommitted();
                    return;
                }
            }
            throw new CacheException("Inconsistency in underlying snapshot storage.");
        } catch (IOException e2) {
            Log.e(LOG_TAG, "IOException occurred while putting data.", e2);
            throw new CacheException("IOException during edit", e2);
        }
    }

    @Override // com.dee.app.cachemanager.ByteCache
    public void remove(@NonNull String str) throws CacheException {
        Preconditions.checkNotNull(str);
        try {
            getCache().remove(str);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Failed to remove key due to IOException", e);
            throw new CacheException(e);
        }
    }
}
