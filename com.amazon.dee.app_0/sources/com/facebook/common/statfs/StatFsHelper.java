package com.facebook.common.statfs;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.facebook.common.internal.Throwables;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import org.aspectj.lang.JoinPoint;
@ThreadSafe
/* loaded from: classes2.dex */
public class StatFsHelper {
    public static final long DEFAULT_DISK_OLIVE_LEVEL_IN_BYTES = 1048576000;
    public static final long DEFAULT_DISK_RED_LEVEL_IN_BYTES = 104857600;
    public static final int DEFAULT_DISK_RED_LEVEL_IN_MB = 100;
    public static final long DEFAULT_DISK_YELLOW_LEVEL_IN_BYTES = 419430400;
    public static final int DEFAULT_DISK_YELLOW_LEVEL_IN_MB = 400;
    private static final long RESTAT_INTERVAL_MS = TimeUnit.MINUTES.toMillis(2);
    private static StatFsHelper sStatsFsHelper;
    @Nullable
    private volatile File mExternalPath;
    @Nullable
    private volatile File mInternalPath;
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private long mLastRestatTime;
    @Nullable
    private volatile StatFs mInternalStatFs = null;
    @Nullable
    private volatile StatFs mExternalStatFs = null;
    private volatile boolean mInitialized = false;
    private final Lock lock = new ReentrantLock();

    /* loaded from: classes2.dex */
    public enum StorageType {
        INTERNAL,
        EXTERNAL
    }

    protected StatFsHelper() {
    }

    protected static StatFs createStatFs(String path) {
        return new StatFs(path);
    }

    private void ensureInitialized() {
        if (!this.mInitialized) {
            this.lock.lock();
            try {
                if (!this.mInitialized) {
                    this.mInternalPath = Environment.getDataDirectory();
                    this.mExternalPath = Environment.getExternalStorageDirectory();
                    updateStats();
                    this.mInitialized = true;
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    public static synchronized StatFsHelper getInstance() {
        StatFsHelper statFsHelper;
        synchronized (StatFsHelper.class) {
            if (sStatsFsHelper == null) {
                sStatsFsHelper = new StatFsHelper();
            }
            statFsHelper = sStatsFsHelper;
        }
        return statFsHelper;
    }

    private void maybeUpdateStats() {
        if (this.lock.tryLock()) {
            try {
                if (SystemClock.uptimeMillis() - this.mLastRestatTime > RESTAT_INTERVAL_MS) {
                    updateStats();
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private void updateStats() {
        this.mInternalStatFs = updateStatsHelper(this.mInternalStatFs, this.mInternalPath);
        this.mExternalStatFs = updateStatsHelper(this.mExternalStatFs, this.mExternalPath);
        this.mLastRestatTime = SystemClock.uptimeMillis();
    }

    @Nullable
    private StatFs updateStatsHelper(@Nullable StatFs statfs, @Nullable File dir) {
        StatFs statFs = null;
        if (dir == null || !dir.exists()) {
            return null;
        }
        try {
            if (statfs == null) {
                statfs = createStatFs(dir.getAbsolutePath());
            } else {
                statfs.restat(dir.getAbsolutePath());
            }
            statFs = statfs;
            return statFs;
        } catch (IllegalArgumentException unused) {
            return statFs;
        } catch (Throwable th) {
            throw Throwables.propagate(th);
        }
    }

    @SuppressLint({"DeprecatedMethod"})
    public long getAvailableStorageSpace(StorageType storageType) {
        ensureInitialized();
        maybeUpdateStats();
        StatFs statFs = storageType == StorageType.INTERNAL ? this.mInternalStatFs : this.mExternalStatFs;
        if (statFs != null) {
            int i = Build.VERSION.SDK_INT;
            return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
        }
        return 0L;
    }

    @SuppressLint({"DeprecatedMethod"})
    public long getFreeStorageSpace(StorageType storageType) {
        ensureInitialized();
        maybeUpdateStats();
        StatFs statFs = storageType == StorageType.INTERNAL ? this.mInternalStatFs : this.mExternalStatFs;
        if (statFs != null) {
            int i = Build.VERSION.SDK_INT;
            return statFs.getBlockSizeLong() * statFs.getFreeBlocksLong();
        }
        return -1L;
    }

    @SuppressLint({"DeprecatedMethod"})
    public long getTotalStorageSpace(StorageType storageType) {
        ensureInitialized();
        maybeUpdateStats();
        StatFs statFs = storageType == StorageType.INTERNAL ? this.mInternalStatFs : this.mExternalStatFs;
        if (statFs != null) {
            int i = Build.VERSION.SDK_INT;
            return statFs.getBlockSizeLong() * statFs.getBlockCountLong();
        }
        return -1L;
    }

    public boolean isHighSpaceCondition() {
        return getAvailableStorageSpace(StorageType.INTERNAL) > DEFAULT_DISK_OLIVE_LEVEL_IN_BYTES;
    }

    public boolean isLowSpaceCondition() {
        return getAvailableStorageSpace(StorageType.INTERNAL) < DEFAULT_DISK_YELLOW_LEVEL_IN_BYTES;
    }

    public boolean isVeryLowSpaceCondition() {
        return getAvailableStorageSpace(StorageType.INTERNAL) < DEFAULT_DISK_RED_LEVEL_IN_BYTES;
    }

    public void resetStats() {
        if (this.lock.tryLock()) {
            try {
                ensureInitialized();
                updateStats();
            } finally {
                this.lock.unlock();
            }
        }
    }

    public boolean testLowDiskSpace(StorageType storageType, long freeSpaceThreshold) {
        ensureInitialized();
        long availableStorageSpace = getAvailableStorageSpace(storageType);
        return availableStorageSpace <= 0 || availableStorageSpace < freeSpaceThreshold;
    }
}
