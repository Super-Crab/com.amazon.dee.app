package com.facebook.cache.disk;

import android.os.Environment;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.onedesign.util.image.ImageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileTreeVisitor;
import com.facebook.common.file.FileUtils;
import com.facebook.common.internal.CountingOutputStream;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.time.Clock;
import com.facebook.common.time.SystemClock;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class DefaultDiskStorage implements DiskStorage {
    private static final String CONTENT_FILE_EXTENSION = ".cnt";
    private static final String DEFAULT_DISK_STORAGE_VERSION_PREFIX = "v2";
    private static final int SHARDING_BUCKET_COUNT = 100;
    private static final String TEMP_FILE_EXTENSION = ".tmp";
    private final CacheErrorLogger mCacheErrorLogger;
    private final Clock mClock;
    private final boolean mIsExternal;
    private final File mRootDirectory;
    private final File mVersionDirectory;
    private static final Class<?> TAG = DefaultDiskStorage.class;
    static final long TEMP_FILE_LIFETIME_MS = TimeUnit.MINUTES.toMillis(30);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class EntriesCollector implements FileTreeVisitor {
        private final List<DiskStorage.Entry> result;

        private EntriesCollector() {
            this.result = new ArrayList();
        }

        public List<DiskStorage.Entry> getEntries() {
            return Collections.unmodifiableList(this.result);
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void postVisitDirectory(File directory) {
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void preVisitDirectory(File directory) {
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void visitFile(File file) {
            FileInfo shardFileInfo = DefaultDiskStorage.this.getShardFileInfo(file);
            if (shardFileInfo == null || shardFileInfo.type != ".cnt") {
                return;
            }
            this.result.add(new EntryImpl(shardFileInfo.resourceId, file));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class EntryImpl implements DiskStorage.Entry {
        private final String id;
        private final FileBinaryResource resource;
        private long size;
        private long timestamp;

        @Override // com.facebook.cache.disk.DiskStorage.Entry
        public String getId() {
            return this.id;
        }

        @Override // com.facebook.cache.disk.DiskStorage.Entry
        public long getSize() {
            if (this.size < 0) {
                this.size = this.resource.size();
            }
            return this.size;
        }

        @Override // com.facebook.cache.disk.DiskStorage.Entry
        public long getTimestamp() {
            if (this.timestamp < 0) {
                this.timestamp = this.resource.getFile().lastModified();
            }
            return this.timestamp;
        }

        private EntryImpl(String id, File cachedFile) {
            Preconditions.checkNotNull(cachedFile);
            this.id = (String) Preconditions.checkNotNull(id);
            this.resource = FileBinaryResource.create(cachedFile);
            this.size = -1L;
            this.timestamp = -1L;
        }

        @Override // com.facebook.cache.disk.DiskStorage.Entry
        /* renamed from: getResource  reason: collision with other method in class */
        public FileBinaryResource mo6854getResource() {
            return this.resource;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class FileInfo {
        public final String resourceId;
        @FileType
        public final String type;

        @Nullable
        public static FileInfo fromFile(File file) {
            String fileTypefromExtension;
            String name = file.getName();
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf > 0 && (fileTypefromExtension = DefaultDiskStorage.getFileTypefromExtension(name.substring(lastIndexOf))) != null) {
                String substring = name.substring(0, lastIndexOf);
                if (fileTypefromExtension.equals(".tmp")) {
                    int lastIndexOf2 = substring.lastIndexOf(46);
                    if (lastIndexOf2 <= 0) {
                        return null;
                    }
                    substring = substring.substring(0, lastIndexOf2);
                }
                return new FileInfo(fileTypefromExtension, substring);
            }
            return null;
        }

        public File createTempFile(File parent) throws IOException {
            return File.createTempFile(this.resourceId + ".", ".tmp", parent);
        }

        public String toPath(String parentPath) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(parentPath);
            outline107.append(File.separator);
            outline107.append(this.resourceId);
            outline107.append(this.type);
            return outline107.toString();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.type);
            sb.append("(");
            return GeneratedOutlineSupport1.outline91(sb, this.resourceId, ")");
        }

        private FileInfo(@FileType String type, String resourceId) {
            this.type = type;
            this.resourceId = resourceId;
        }
    }

    /* loaded from: classes2.dex */
    public @interface FileType {
        public static final String CONTENT = ".cnt";
        public static final String TEMP = ".tmp";
    }

    /* loaded from: classes2.dex */
    private static class IncompleteFileException extends IOException {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public IncompleteFileException(long r3, long r5) {
            /*
                r2 = this;
                java.lang.String r0 = "File was not written completely. Expected: "
                java.lang.String r1 = ", found: "
                java.lang.StringBuilder r3 = com.android.tools.r8.GeneratedOutlineSupport1.outline111(r0, r3, r1)
                r3.append(r5)
                java.lang.String r3 = r3.toString()
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.cache.disk.DefaultDiskStorage.IncompleteFileException.<init>(long, long):void");
        }
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    class InserterImpl implements DiskStorage.Inserter {
        private final String mResourceId;
        @VisibleForTesting
        final File mTemporaryFile;

        public InserterImpl(String resourceId, File temporaryFile) {
            this.mResourceId = resourceId;
            this.mTemporaryFile = temporaryFile;
        }

        @Override // com.facebook.cache.disk.DiskStorage.Inserter
        public boolean cleanUp() {
            return !this.mTemporaryFile.exists() || this.mTemporaryFile.delete();
        }

        @Override // com.facebook.cache.disk.DiskStorage.Inserter
        public BinaryResource commit(Object debugInfo) throws IOException {
            return commit(debugInfo, DefaultDiskStorage.this.mClock.now());
        }

        @Override // com.facebook.cache.disk.DiskStorage.Inserter
        public void writeData(WriterCallback callback, Object debugInfo) throws IOException {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(this.mTemporaryFile);
                try {
                    CountingOutputStream countingOutputStream = new CountingOutputStream(fileOutputStream);
                    callback.write(countingOutputStream);
                    countingOutputStream.flush();
                    long count = countingOutputStream.getCount();
                    fileOutputStream.close();
                    if (this.mTemporaryFile.length() != count) {
                        throw new IncompleteFileException(count, this.mTemporaryFile.length());
                    }
                } catch (Throwable th) {
                    fileOutputStream.close();
                    throw th;
                }
            } catch (FileNotFoundException e) {
                DefaultDiskStorage.this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_UPDATE_FILE_NOT_FOUND, DefaultDiskStorage.TAG, "updateResource", e);
                throw e;
            }
        }

        @Override // com.facebook.cache.disk.DiskStorage.Inserter
        public BinaryResource commit(Object debugInfo, long time) throws IOException {
            CacheErrorLogger.CacheErrorCategory cacheErrorCategory;
            File contentFileFor = DefaultDiskStorage.this.getContentFileFor(this.mResourceId);
            try {
                FileUtils.rename(this.mTemporaryFile, contentFileFor);
                if (contentFileFor.exists()) {
                    contentFileFor.setLastModified(time);
                }
                return FileBinaryResource.create(contentFileFor);
            } catch (FileUtils.RenameException e) {
                Throwable cause = e.getCause();
                if (cause != null) {
                    if (!(cause instanceof FileUtils.ParentDirNotFoundException)) {
                        if (cause instanceof FileNotFoundException) {
                            cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.WRITE_RENAME_FILE_TEMPFILE_NOT_FOUND;
                        } else {
                            cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.WRITE_RENAME_FILE_OTHER;
                        }
                    } else {
                        cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.WRITE_RENAME_FILE_TEMPFILE_PARENT_NOT_FOUND;
                    }
                } else {
                    cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.WRITE_RENAME_FILE_OTHER;
                }
                DefaultDiskStorage.this.mCacheErrorLogger.logError(cacheErrorCategory, DefaultDiskStorage.TAG, "commit", e);
                throw e;
            }
        }
    }

    /* loaded from: classes2.dex */
    private class PurgingVisitor implements FileTreeVisitor {
        private boolean insideBaseDirectory;

        private PurgingVisitor() {
        }

        private boolean isExpectedFile(File file) {
            FileInfo shardFileInfo = DefaultDiskStorage.this.getShardFileInfo(file);
            boolean z = false;
            if (shardFileInfo == null) {
                return false;
            }
            String str = shardFileInfo.type;
            if (str == ".tmp") {
                return isRecentFile(file);
            }
            if (str == ".cnt") {
                z = true;
            }
            Preconditions.checkState(z);
            return true;
        }

        private boolean isRecentFile(File file) {
            return file.lastModified() > DefaultDiskStorage.this.mClock.now() - DefaultDiskStorage.TEMP_FILE_LIFETIME_MS;
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void postVisitDirectory(File directory) {
            if (!DefaultDiskStorage.this.mRootDirectory.equals(directory) && !this.insideBaseDirectory) {
                directory.delete();
            }
            if (!this.insideBaseDirectory || !directory.equals(DefaultDiskStorage.this.mVersionDirectory)) {
                return;
            }
            this.insideBaseDirectory = false;
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void preVisitDirectory(File directory) {
            if (this.insideBaseDirectory || !directory.equals(DefaultDiskStorage.this.mVersionDirectory)) {
                return;
            }
            this.insideBaseDirectory = true;
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void visitFile(File file) {
            if (!this.insideBaseDirectory || !isExpectedFile(file)) {
                file.delete();
            }
        }
    }

    public DefaultDiskStorage(File rootDirectory, int version, CacheErrorLogger cacheErrorLogger) {
        Preconditions.checkNotNull(rootDirectory);
        this.mRootDirectory = rootDirectory;
        this.mIsExternal = isExternal(rootDirectory, cacheErrorLogger);
        this.mVersionDirectory = new File(this.mRootDirectory, getVersionSubdirectoryName(version));
        this.mCacheErrorLogger = cacheErrorLogger;
        recreateDirectoryIfVersionChanges();
        this.mClock = SystemClock.get();
    }

    private long doRemove(final File contentFile) {
        if (!contentFile.exists()) {
            return 0L;
        }
        long length = contentFile.length();
        if (!contentFile.delete()) {
            return -1L;
        }
        return length;
    }

    private DiskStorage.DiskDumpInfoEntry dumpCacheEntry(DiskStorage.Entry entry) throws IOException {
        EntryImpl entryImpl = (EntryImpl) entry;
        byte[] read = entryImpl.mo6854getResource().read();
        String typeOfBytes = typeOfBytes(read);
        return new DiskStorage.DiskDumpInfoEntry(entryImpl.getId(), entryImpl.mo6854getResource().getFile().getPath(), typeOfBytes, (float) entryImpl.getSize(), (!typeOfBytes.equals("undefined") || read.length < 4) ? "" : String.format(null, "0x%02X 0x%02X 0x%02X 0x%02X", Byte.valueOf(read[0]), Byte.valueOf(read[1]), Byte.valueOf(read[2]), Byte.valueOf(read[3])));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @FileType
    @Nullable
    public static String getFileTypefromExtension(String extension) {
        if (".cnt".equals(extension)) {
            return ".cnt";
        }
        if (!".tmp".equals(extension)) {
            return null;
        }
        return ".tmp";
    }

    private String getFilename(String resourceId) {
        FileInfo fileInfo = new FileInfo(".cnt", resourceId);
        return fileInfo.toPath(getSubdirectoryPath(fileInfo.resourceId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public FileInfo getShardFileInfo(File file) {
        FileInfo fromFile = FileInfo.fromFile(file);
        if (fromFile != null && getSubdirectory(fromFile.resourceId).equals(file.getParentFile())) {
            return fromFile;
        }
        return null;
    }

    private File getSubdirectory(String resourceId) {
        return new File(getSubdirectoryPath(resourceId));
    }

    private String getSubdirectoryPath(String resourceId) {
        String valueOf = String.valueOf(Math.abs(resourceId.hashCode() % 100));
        StringBuilder sb = new StringBuilder();
        sb.append(this.mVersionDirectory);
        return GeneratedOutlineSupport1.outline91(sb, File.separator, valueOf);
    }

    @VisibleForTesting
    static String getVersionSubdirectoryName(int version) {
        return String.format(null, "%s.ols%d.%d", DEFAULT_DISK_STORAGE_VERSION_PREFIX, 100, Integer.valueOf(version));
    }

    private static boolean isExternal(File directory, CacheErrorLogger cacheErrorLogger) {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory == null) {
                return false;
            }
            try {
            } catch (IOException e) {
                e = e;
            }
            try {
                return directory.getCanonicalPath().contains(externalStorageDirectory.toString());
            } catch (IOException e2) {
                e = e2;
                CacheErrorLogger.CacheErrorCategory cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.OTHER;
                Class<?> cls = TAG;
                cacheErrorLogger.logError(cacheErrorCategory, cls, "failed to read folder to check if external: " + ((String) null), e);
                return false;
            }
        } catch (Exception e3) {
            cacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.OTHER, TAG, "failed to get the external storage directory!", e3);
            return false;
        }
    }

    private void mkdirs(File directory, String message) throws IOException {
        try {
            FileUtils.mkdirs(directory);
        } catch (FileUtils.CreateDirectoryException e) {
            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_DIR, TAG, message, e);
            throw e;
        }
    }

    private boolean query(String resourceId, boolean touch) {
        File contentFileFor = getContentFileFor(resourceId);
        boolean exists = contentFileFor.exists();
        if (touch && exists) {
            contentFileFor.setLastModified(this.mClock.now());
        }
        return exists;
    }

    private void recreateDirectoryIfVersionChanges() {
        boolean z = true;
        if (this.mRootDirectory.exists()) {
            if (!this.mVersionDirectory.exists()) {
                FileTree.deleteRecursively(this.mRootDirectory);
            } else {
                z = false;
            }
        }
        if (z) {
            try {
                FileUtils.mkdirs(this.mVersionDirectory);
            } catch (FileUtils.CreateDirectoryException unused) {
                CacheErrorLogger cacheErrorLogger = this.mCacheErrorLogger;
                CacheErrorLogger.CacheErrorCategory cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_DIR;
                Class<?> cls = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("version directory could not be created: ");
                outline107.append(this.mVersionDirectory);
                cacheErrorLogger.logError(cacheErrorCategory, cls, outline107.toString(), null);
            }
        }
    }

    private String typeOfBytes(byte[] bytes) {
        return bytes.length >= 2 ? (bytes[0] == -1 && bytes[1] == -40) ? ImageType.JPG : (bytes[0] == -119 && bytes[1] == 80) ? ImageType.PNG : (bytes[0] == 82 && bytes[1] == 73) ? "webp" : (bytes[0] == 71 && bytes[1] == 73) ? "gif" : "undefined" : "undefined";
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public void clearAll() {
        FileTree.deleteContents(this.mRootDirectory);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean contains(String resourceId, Object debugInfo) {
        return query(resourceId, false);
    }

    @VisibleForTesting
    File getContentFileFor(String resourceId) {
        return new File(getFilename(resourceId));
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public DiskStorage.DiskDumpInfo getDumpInfo() throws IOException {
        List<DiskStorage.Entry> mo6853getEntries = mo6853getEntries();
        DiskStorage.DiskDumpInfo diskDumpInfo = new DiskStorage.DiskDumpInfo();
        for (DiskStorage.Entry entry : mo6853getEntries) {
            DiskStorage.DiskDumpInfoEntry dumpCacheEntry = dumpCacheEntry(entry);
            String str = dumpCacheEntry.type;
            Integer num = diskDumpInfo.typeCounts.get(str);
            if (num == null) {
                diskDumpInfo.typeCounts.put(str, 1);
            } else {
                diskDumpInfo.typeCounts.put(str, Integer.valueOf(num.intValue() + 1));
            }
            diskDumpInfo.entries.add(dumpCacheEntry);
        }
        return diskDumpInfo;
    }

    @Override // com.facebook.cache.disk.DiskStorage
    @Nullable
    public BinaryResource getResource(String resourceId, Object debugInfo) {
        File contentFileFor = getContentFileFor(resourceId);
        if (contentFileFor.exists()) {
            contentFileFor.setLastModified(this.mClock.now());
            return FileBinaryResource.createOrNull(contentFileFor);
        }
        return null;
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public String getStorageName() {
        String absolutePath = this.mRootDirectory.getAbsolutePath();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("_");
        outline107.append(absolutePath.substring(absolutePath.lastIndexOf(47) + 1, absolutePath.length()));
        outline107.append("_");
        outline107.append(absolutePath.hashCode());
        return outline107.toString();
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public DiskStorage.Inserter insert(String resourceId, Object debugInfo) throws IOException {
        FileInfo fileInfo = new FileInfo(".tmp", resourceId);
        File subdirectory = getSubdirectory(fileInfo.resourceId);
        if (!subdirectory.exists()) {
            mkdirs(subdirectory, "insert");
        }
        try {
            return new InserterImpl(resourceId, fileInfo.createTempFile(subdirectory));
        } catch (IOException e) {
            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_TEMPFILE, TAG, "insert", e);
            throw e;
        }
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean isEnabled() {
        return true;
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public void purgeUnexpectedResources() {
        FileTree.walkFileTree(this.mRootDirectory, new PurgingVisitor());
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public long remove(DiskStorage.Entry entry) {
        return doRemove(((EntryImpl) entry).mo6854getResource().getFile());
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean touch(String resourceId, Object debugInfo) {
        return query(resourceId, true);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    /* renamed from: getEntries  reason: collision with other method in class */
    public List<DiskStorage.Entry> mo6853getEntries() throws IOException {
        EntriesCollector entriesCollector = new EntriesCollector();
        FileTree.walkFileTree(this.mVersionDirectory, entriesCollector);
        return entriesCollector.getEntries();
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public long remove(final String resourceId) {
        return doRemove(getContentFileFor(resourceId));
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean isExternal() {
        return this.mIsExternal;
    }
}
