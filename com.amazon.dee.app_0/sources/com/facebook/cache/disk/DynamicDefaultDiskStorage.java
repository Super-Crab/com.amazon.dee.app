package com.facebook.cache.disk;

import androidx.annotation.VisibleForTesting;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileUtils;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class DynamicDefaultDiskStorage implements DiskStorage {
    private static final Class<?> TAG = DynamicDefaultDiskStorage.class;
    private final String mBaseDirectoryName;
    private final Supplier<File> mBaseDirectoryPathSupplier;
    private final CacheErrorLogger mCacheErrorLogger;
    @VisibleForTesting
    volatile State mCurrentState = new State(null, null);
    private final int mVersion;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class State {
        @Nullable
        public final DiskStorage delegate;
        @Nullable
        public final File rootDirectory;

        @VisibleForTesting
        State(@Nullable File rootDirectory, @Nullable DiskStorage delegate) {
            this.delegate = delegate;
            this.rootDirectory = rootDirectory;
        }
    }

    public DynamicDefaultDiskStorage(int version, Supplier<File> baseDirectoryPathSupplier, String baseDirectoryName, CacheErrorLogger cacheErrorLogger) {
        this.mVersion = version;
        this.mCacheErrorLogger = cacheErrorLogger;
        this.mBaseDirectoryPathSupplier = baseDirectoryPathSupplier;
        this.mBaseDirectoryName = baseDirectoryName;
    }

    private void createStorage() throws IOException {
        File file = new File(this.mBaseDirectoryPathSupplier.mo6895get(), this.mBaseDirectoryName);
        createRootDirectoryIfNecessary(file);
        this.mCurrentState = new State(file, new DefaultDiskStorage(file, this.mVersion, this.mCacheErrorLogger));
    }

    private boolean shouldCreateNewStorage() {
        File file;
        State state = this.mCurrentState;
        return state.delegate == null || (file = state.rootDirectory) == null || !file.exists();
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public void clearAll() throws IOException {
        get().clearAll();
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean contains(String resourceId, Object debugInfo) throws IOException {
        return get().contains(resourceId, debugInfo);
    }

    @VisibleForTesting
    void createRootDirectoryIfNecessary(File rootDirectory) throws IOException {
        try {
            FileUtils.mkdirs(rootDirectory);
            FLog.d(TAG, "Created cache directory %s", rootDirectory.getAbsolutePath());
        } catch (FileUtils.CreateDirectoryException e) {
            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_DIR, TAG, "createRootDirectoryIfNecessary", e);
            throw e;
        }
    }

    @VisibleForTesting
    void deleteOldStorageIfNecessary() {
        if (this.mCurrentState.delegate == null || this.mCurrentState.rootDirectory == null) {
            return;
        }
        FileTree.deleteRecursively(this.mCurrentState.rootDirectory);
    }

    @VisibleForTesting
    synchronized DiskStorage get() throws IOException {
        if (shouldCreateNewStorage()) {
            deleteOldStorageIfNecessary();
            createStorage();
        }
        return (DiskStorage) Preconditions.checkNotNull(this.mCurrentState.delegate);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public DiskStorage.DiskDumpInfo getDumpInfo() throws IOException {
        return get().getDumpInfo();
    }

    @Override // com.facebook.cache.disk.DiskStorage
    /* renamed from: getEntries */
    public Collection<DiskStorage.Entry> mo6853getEntries() throws IOException {
        return get().mo6853getEntries();
    }

    @Override // com.facebook.cache.disk.DiskStorage
    @Nullable
    public BinaryResource getResource(String resourceId, Object debugInfo) throws IOException {
        return get().getResource(resourceId, debugInfo);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public String getStorageName() {
        try {
            return get().getStorageName();
        } catch (IOException unused) {
            return "";
        }
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public DiskStorage.Inserter insert(String resourceId, Object debugInfo) throws IOException {
        return get().insert(resourceId, debugInfo);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean isEnabled() {
        try {
            return get().isEnabled();
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean isExternal() {
        try {
            return get().isExternal();
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public void purgeUnexpectedResources() {
        try {
            get().purgeUnexpectedResources();
        } catch (IOException e) {
            FLog.e(TAG, "purgeUnexpectedResources", e);
        }
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public long remove(DiskStorage.Entry entry) throws IOException {
        return get().remove(entry);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean touch(String resourceId, Object debugInfo) throws IOException {
        return get().touch(resourceId, debugInfo);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public long remove(String resourceId) throws IOException {
        return get().remove(resourceId);
    }
}
