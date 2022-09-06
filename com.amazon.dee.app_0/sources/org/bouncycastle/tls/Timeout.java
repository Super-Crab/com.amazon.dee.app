package org.bouncycastle.tls;
/* loaded from: classes5.dex */
class Timeout {
    private long durationMillis;
    private long startMillis;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Timeout(long j) {
        this(j, System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Timeout(long j, long j2) {
        this.durationMillis = Math.max(0L, j);
        this.startMillis = Math.max(0L, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int constrainWaitMillis(int i, Timeout timeout, long j) {
        int waitMillis;
        if (i >= 0 && (waitMillis = getWaitMillis(timeout, j)) >= 0) {
            return i == 0 ? waitMillis : waitMillis == 0 ? i : Math.min(i, waitMillis);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Timeout forWaitMillis(int i) {
        return forWaitMillis(i, System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Timeout forWaitMillis(int i, long j) {
        if (i >= 0) {
            if (i <= 0) {
                return null;
            }
            return new Timeout(i, j);
        }
        throw new IllegalArgumentException("'waitMillis' cannot be negative");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getWaitMillis(Timeout timeout, long j) {
        if (timeout == null) {
            return 0;
        }
        long remainingMillis = timeout.remainingMillis(j);
        if (remainingMillis < 1) {
            return -1;
        }
        if (remainingMillis <= 2147483647L) {
            return (int) remainingMillis;
        }
        return Integer.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasExpired(Timeout timeout, long j) {
        return timeout != null && timeout.remainingMillis(j) < 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long remainingMillis(long j) {
        if (this.startMillis > j) {
            this.startMillis = j;
            return this.durationMillis;
        }
        long j2 = this.durationMillis - (j - this.startMillis);
        if (j2 > 0) {
            return j2;
        }
        this.durationMillis = 0L;
        return 0L;
    }
}
