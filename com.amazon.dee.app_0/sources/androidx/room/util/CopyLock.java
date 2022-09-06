package androidx.room.util;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class CopyLock {
    private static final Map<String, Lock> sThreadLocks = new HashMap();
    private final File mCopyLockFile;
    private final boolean mFileLevelLock;
    private FileChannel mLockChannel;
    private final Lock mThreadLock;

    public CopyLock(@NonNull String name, @NonNull File lockDir, boolean processLock) {
        this.mCopyLockFile = new File(lockDir, GeneratedOutlineSupport1.outline72(name, ".lck"));
        this.mThreadLock = getThreadLock(this.mCopyLockFile.getAbsolutePath());
        this.mFileLevelLock = processLock;
    }

    private static Lock getThreadLock(String key) {
        Lock lock;
        synchronized (sThreadLocks) {
            lock = sThreadLocks.get(key);
            if (lock == null) {
                lock = new ReentrantLock();
                sThreadLocks.put(key, lock);
            }
        }
        return lock;
    }

    public void lock() {
        this.mThreadLock.lock();
        if (this.mFileLevelLock) {
            try {
                this.mLockChannel = new FileOutputStream(this.mCopyLockFile).getChannel();
                this.mLockChannel.lock();
            } catch (IOException e) {
                throw new IllegalStateException("Unable to grab copy lock.", e);
            }
        }
    }

    public void unlock() {
        FileChannel fileChannel = this.mLockChannel;
        if (fileChannel != null) {
            try {
                fileChannel.close();
            } catch (IOException unused) {
            }
        }
        this.mThreadLock.unlock();
    }
}
