package com.amazon.communication;

import android.content.Context;
import android.os.PowerManager;
import android.os.SystemClock;
import com.amazon.communication.PowerManagerWrapper;
import com.amazon.dp.logger.DPLogger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.aspectj.lang.JoinPoint;
/* loaded from: classes12.dex */
public class PowerManagerWrapperImpl implements PowerManagerWrapper {
    public static final long WAKE_LOCK_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(30);
    private final PowerManager mPowerManager;

    public PowerManagerWrapperImpl(Context context) {
        this.mPowerManager = (PowerManager) context.getApplicationContext().getSystemService("power");
    }

    @Override // com.amazon.communication.PowerManagerWrapper
    public PowerManagerWrapper.WakeLock newWakeLock(int i, String str) {
        return new WakeLockImpl(this.mPowerManager.newWakeLock(i, str), str);
    }

    /* loaded from: classes12.dex */
    private static final class WakeLockImpl implements PowerManagerWrapper.WakeLock {
        private static final DPLogger log = new DPLogger("TComm.WakeLockImpl");
        private final PowerManager.WakeLock mLock;
        private final String mTag;
        private final AtomicInteger mRefCount = new AtomicInteger(0);
        private final AtomicLong mAcquiredTimeMs = new AtomicLong();

        WakeLockImpl(PowerManager.WakeLock wakeLock, String str) {
            if (wakeLock != null) {
                this.mLock = wakeLock;
                this.mTag = str;
                return;
            }
            throw new IllegalArgumentException("Lock cannot be null");
        }

        @Override // com.amazon.communication.PowerManagerWrapper.WakeLock
        public void acquire() {
            int incrementAndGet = this.mRefCount.incrementAndGet();
            if (incrementAndGet == 1) {
                this.mAcquiredTimeMs.set(SystemClock.elapsedRealtime());
            }
            this.mLock.acquire();
            log.info("acquire", "acquire WakeLock", "refCount", Integer.valueOf(incrementAndGet), "tag", this.mTag, JoinPoint.SYNCHRONIZATION_LOCK, this.mLock);
        }

        public boolean equals(Object obj) {
            return this.mLock.equals(obj);
        }

        public int hashCode() {
            return this.mLock.hashCode();
        }

        @Override // com.amazon.communication.PowerManagerWrapper.WakeLock
        public boolean isHeld() {
            return this.mLock.isHeld();
        }

        @Override // com.amazon.communication.PowerManagerWrapper.WakeLock
        public void release() {
            int decrementAndGet = this.mRefCount.decrementAndGet();
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.mAcquiredTimeMs.get();
            if (this.mLock.isHeld()) {
                this.mLock.release();
            } else {
                log.info("release", "release Wakelock", "msg", "Wakelock has already expired");
            }
            log.info("release", "release WakeLock", "refCount", Integer.valueOf(decrementAndGet), "duration", Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(elapsedRealtime)), "tag", this.mTag, JoinPoint.SYNCHRONIZATION_LOCK, this.mLock);
        }

        @Override // com.amazon.communication.PowerManagerWrapper.WakeLock
        public void setReferenceCounted(boolean z) {
            log.debug("setReferenceCounted", "set WakeLock mode", "value", Boolean.valueOf(z), "refCount", Integer.valueOf(this.mRefCount.get()), "tag", this.mTag, JoinPoint.SYNCHRONIZATION_LOCK, this.mLock);
            this.mLock.setReferenceCounted(z);
        }

        public String toString() {
            return this.mLock.toString();
        }

        @Override // com.amazon.communication.PowerManagerWrapper.WakeLock
        public void acquire(long j) {
            int incrementAndGet = this.mRefCount.incrementAndGet();
            if (incrementAndGet == 1) {
                this.mAcquiredTimeMs.set(SystemClock.elapsedRealtime());
            }
            this.mLock.acquire(j);
            log.info("acquire", "acquire WakeLock with timeout", "refCount", Integer.valueOf(incrementAndGet), "tag", this.mTag, JoinPoint.SYNCHRONIZATION_LOCK, this.mLock, "timeout", Long.valueOf(j));
        }
    }
}
