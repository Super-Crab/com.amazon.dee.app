package androidx.room;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.room.util.CopyLock;
import androidx.room.util.DBUtil;
import androidx.room.util.FileUtil;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.cache.disk.DefaultDiskStorage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.Callable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SQLiteCopyOpenHelper implements SupportSQLiteOpenHelper, DelegatingOpenHelper {
    @NonNull
    private final Context mContext;
    @Nullable
    private final String mCopyFromAssetPath;
    @Nullable
    private final File mCopyFromFile;
    @Nullable
    private final Callable<InputStream> mCopyFromInputStream;
    @Nullable
    private DatabaseConfiguration mDatabaseConfiguration;
    private final int mDatabaseVersion;
    @NonNull
    private final SupportSQLiteOpenHelper mDelegate;
    private boolean mVerified;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLiteCopyOpenHelper(@NonNull Context context, @Nullable String copyFromAssetPath, @Nullable File copyFromFile, @Nullable Callable<InputStream> copyFromInputStream, int databaseVersion, @NonNull SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        this.mContext = context;
        this.mCopyFromAssetPath = copyFromAssetPath;
        this.mCopyFromFile = copyFromFile;
        this.mCopyFromInputStream = copyFromInputStream;
        this.mDatabaseVersion = databaseVersion;
        this.mDelegate = supportSQLiteOpenHelper;
    }

    private void copyDatabaseFile(File destinationFile, boolean writable) throws IOException {
        ReadableByteChannel newChannel;
        if (this.mCopyFromAssetPath != null) {
            newChannel = Channels.newChannel(this.mContext.getAssets().open(this.mCopyFromAssetPath));
        } else {
            File file = this.mCopyFromFile;
            if (file != null) {
                newChannel = new FileInputStream(file).getChannel();
            } else {
                Callable<InputStream> callable = this.mCopyFromInputStream;
                if (callable != null) {
                    try {
                        newChannel = Channels.newChannel(callable.call());
                    } catch (Exception e) {
                        throw new IOException("inputStreamCallable exception on call", e);
                    }
                } else {
                    throw new IllegalStateException("copyFromAssetPath, copyFromFile and copyFromInputStream are all null!");
                }
            }
        }
        File createTempFile = File.createTempFile("room-copy-helper", DefaultDiskStorage.FileType.TEMP, this.mContext.getCacheDir());
        createTempFile.deleteOnExit();
        FileUtil.copy(newChannel, new FileOutputStream(createTempFile).getChannel());
        File parentFile = destinationFile.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to create directories for ");
            outline107.append(destinationFile.getAbsolutePath());
            throw new IOException(outline107.toString());
        }
        dispatchOnOpenPrepackagedDatabase(createTempFile, writable);
        if (createTempFile.renameTo(destinationFile)) {
            return;
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Failed to move intermediate file (");
        outline1072.append(createTempFile.getAbsolutePath());
        outline1072.append(") to destination (");
        outline1072.append(destinationFile.getAbsolutePath());
        outline1072.append(").");
        throw new IOException(outline1072.toString());
    }

    private SupportSQLiteOpenHelper createFrameworkOpenHelper(File databaseFile) {
        try {
            return new FrameworkSQLiteOpenHelperFactory().mo201create(SupportSQLiteOpenHelper.Configuration.builder(this.mContext).name(databaseFile.getName()).callback(new SupportSQLiteOpenHelper.Callback(DBUtil.readVersion(databaseFile)) { // from class: androidx.room.SQLiteCopyOpenHelper.1
                @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                }

                @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
                public void onUpgrade(@NonNull SupportSQLiteDatabase db, int oldVersion, int newVersion) {
                }
            }).build());
        } catch (IOException e) {
            throw new RuntimeException("Malformed database file, unable to read version.", e);
        }
    }

    private void dispatchOnOpenPrepackagedDatabase(File databaseFile, boolean writable) {
        SupportSQLiteDatabase readableDatabase;
        DatabaseConfiguration databaseConfiguration = this.mDatabaseConfiguration;
        if (databaseConfiguration == null || databaseConfiguration.prepackagedDatabaseCallback == null) {
            return;
        }
        SupportSQLiteOpenHelper createFrameworkOpenHelper = createFrameworkOpenHelper(databaseFile);
        try {
            if (writable) {
                readableDatabase = createFrameworkOpenHelper.getWritableDatabase();
            } else {
                readableDatabase = createFrameworkOpenHelper.getReadableDatabase();
            }
            this.mDatabaseConfiguration.prepackagedDatabaseCallback.onOpenPrepackagedDatabase(readableDatabase);
        } finally {
            createFrameworkOpenHelper.close();
        }
    }

    private void verifyDatabaseFile(boolean writable) {
        String databaseName = getDatabaseName();
        File databasePath = this.mContext.getDatabasePath(databaseName);
        DatabaseConfiguration databaseConfiguration = this.mDatabaseConfiguration;
        CopyLock copyLock = new CopyLock(databaseName, this.mContext.getFilesDir(), databaseConfiguration == null || databaseConfiguration.multiInstanceInvalidation);
        try {
            copyLock.lock();
            if (!databasePath.exists()) {
                try {
                    copyDatabaseFile(databasePath, writable);
                    copyLock.unlock();
                    return;
                } catch (IOException e) {
                    throw new RuntimeException("Unable to copy database file.", e);
                }
            } else if (this.mDatabaseConfiguration == null) {
                copyLock.unlock();
                return;
            } else {
                try {
                    int readVersion = DBUtil.readVersion(databasePath);
                    if (readVersion == this.mDatabaseVersion) {
                        copyLock.unlock();
                        return;
                    } else if (this.mDatabaseConfiguration.isMigrationRequired(readVersion, this.mDatabaseVersion)) {
                        copyLock.unlock();
                        return;
                    } else {
                        if (this.mContext.deleteDatabase(databaseName)) {
                            try {
                                copyDatabaseFile(databasePath, writable);
                            } catch (IOException e2) {
                                Log.w("ROOM", "Unable to copy database file.", e2);
                            }
                        } else {
                            Log.w("ROOM", "Failed to delete database file (" + databaseName + ") for a copy destructive migration.");
                        }
                        copyLock.unlock();
                        return;
                    }
                } catch (IOException e3) {
                    Log.w("ROOM", "Unable to read database version.", e3);
                    copyLock.unlock();
                    return;
                }
            }
        } catch (Throwable th) {
            copyLock.unlock();
            throw th;
        }
        copyLock.unlock();
        throw th;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.mDelegate.close();
        this.mVerified = false;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return this.mDelegate.getDatabaseName();
    }

    @Override // androidx.room.DelegatingOpenHelper
    @NonNull
    public SupportSQLiteOpenHelper getDelegate() {
        return this.mDelegate;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public synchronized SupportSQLiteDatabase getReadableDatabase() {
        if (!this.mVerified) {
            verifyDatabaseFile(false);
            this.mVerified = true;
        }
        return this.mDelegate.getReadableDatabase();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public synchronized SupportSQLiteDatabase getWritableDatabase() {
        if (!this.mVerified) {
            verifyDatabaseFile(true);
            this.mVerified = true;
        }
        return this.mDelegate.getWritableDatabase();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDatabaseConfiguration(@Nullable DatabaseConfiguration databaseConfiguration) {
        this.mDatabaseConfiguration = databaseConfiguration;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    @RequiresApi(api = 16)
    public void setWriteAheadLoggingEnabled(boolean enabled) {
        this.mDelegate.setWriteAheadLoggingEnabled(enabled);
    }
}
