package com.amazon.communication;

import amazon.communication.Message;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.utils.FailFast;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes12.dex */
public class MessageFragmentInputStream extends InputStream implements BetterInputStream {
    private static final int DEFAULT_MAX_BLOCKING_TIME_IN_MS = 5000;
    private static final DPLogger log = new DPLogger("TComm.MessageFragmentInputStream");
    private AtomicBoolean mFailedToReceiveAllFragments;
    private final Condition mFragmentArrivalSignal;
    private final Lock mFragmentListLock;
    private final List<InputStream> mFragments;
    private long mLastFragmentArrivalTimeInMillis;
    private final int mMaxBlockingTimeInMs;
    private boolean mMoreToCome;

    public MessageFragmentInputStream(Message message) {
        this(message, 5000);
    }

    private void waitForAtLeastOneFragment() throws InterruptedException, IOException {
        if (this.mFragments.size() == 0 && this.mMoreToCome) {
            log.warn("waitForAtLeastOneFragment", "wait for more fragments.", new Object[0]);
            if (!this.mFragmentArrivalSignal.await(this.mMaxBlockingTimeInMs, TimeUnit.MILLISECONDS)) {
                throw new IOException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("Didn't receive a message fragment in "), this.mMaxBlockingTimeInMs, " milliseconds"));
            }
            return;
        }
        log.debug("waitForAtLeastOneFragment", "no need to wait.", "remaining fragments", this.mFragments, "moreToCome", Boolean.valueOf(this.mMoreToCome));
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        int i;
        if (!this.mFailedToReceiveAllFragments.get()) {
            this.mFragmentListLock.lock();
            try {
                log.debug("available", "inside lock", new Object[0]);
                if (this.mFragments.size() != 0) {
                    i = 0;
                    for (InputStream inputStream : this.mFragments) {
                        i += inputStream.available();
                    }
                } else {
                    i = 0;
                }
                if (i == 0 && !this.mMoreToCome) {
                    log.debug("available", "end of stream", new Object[0]);
                    i = 1;
                }
                log.debug("available", "result", Integer.valueOf(i));
                return i;
            } finally {
                this.mFragmentListLock.unlock();
            }
        }
        throw new IOException("Failed to receive all fragments of a message");
    }

    public long getLastFragmentArrivalTimeInMillis() {
        return this.mLastFragmentArrivalTimeInMillis;
    }

    public void newFragmentArrived(Message message, boolean z) {
        if (!this.mFailedToReceiveAllFragments.get()) {
            this.mFragmentListLock.lock();
            try {
                log.debug("newFragmentArrived", "inside lock", "new fragment", message, "moreToCome", Boolean.valueOf(z));
                this.mFragments.add(message.getPayload());
                this.mLastFragmentArrivalTimeInMillis = GlobalTimeSource.INSTANCE.currentTimeMillis();
                this.mMoreToCome = z;
                this.mFragmentArrivalSignal.signal();
                return;
            } finally {
                this.mFragmentListLock.unlock();
            }
        }
        throw new IllegalStateException("Already stopped receiving fragments");
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        log.info("read()", "this method shouldn't be invoked by production code too many times.", new Object[0]);
        if (!this.mFailedToReceiveAllFragments.get()) {
            this.mFragmentListLock.lock();
            byte b = -1;
            while (this.mFragments.size() > 0 && (b = (byte) this.mFragments.get(0).read()) == -1) {
                try {
                    try {
                        this.mFragments.remove(0);
                        waitForAtLeastOneFragment();
                    } catch (InterruptedException e) {
                        throw new IOException(e);
                    }
                } finally {
                    this.mFragmentListLock.unlock();
                }
            }
            return b;
        }
        throw new IOException("Failed to receive all fragments of a message");
    }

    public void setFailedToReceiveAllFragments(boolean z) {
        this.mFailedToReceiveAllFragments.set(z);
    }

    public MessageFragmentInputStream(Message message, int i) {
        this.mFragments = new LinkedList();
        this.mFragmentListLock = new ReentrantLock();
        this.mFragmentArrivalSignal = this.mFragmentListLock.newCondition();
        this.mMaxBlockingTimeInMs = i;
        this.mMoreToCome = true;
        this.mFailedToReceiveAllFragments = new AtomicBoolean(false);
        newFragmentArrived(message, true);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        log.debug("read(byte[], off, len)", "outside lock", "array", bArr, "offset", Integer.valueOf(i), "length", Integer.valueOf(i2));
        if (!this.mFailedToReceiveAllFragments.get()) {
            if (bArr != null) {
                if (i < 0 || i >= bArr.length || i2 < 0 || i + i2 > bArr.length) {
                    throw new IndexOutOfBoundsException();
                }
                if (i2 == 0) {
                    return 0;
                }
                this.mFragmentListLock.lock();
                int i3 = 0;
                while (this.mFragments.size() > 0 && i3 < i2) {
                    try {
                        try {
                            InputStream inputStream = this.mFragments.get(0);
                            int i4 = i2 - i3;
                            int read = inputStream.read(bArr, i + i3, i4);
                            log.debug("read(byte[], off, len)", "finished read on current fragment", "bytes read", Integer.valueOf(read));
                            if (read == -1) {
                                log.debug("read(byte[], off, len)", "end-of-stream", "current fragment", inputStream);
                                this.mFragments.remove(0);
                                if (i3 == 0) {
                                    waitForAtLeastOneFragment();
                                }
                            } else {
                                FailFast.expectTrue(read > 0, "number of bytes read should never be 0 as len is not 0: " + i4);
                                i3 += read;
                            }
                        } catch (InterruptedException e) {
                            throw new IOException(e);
                        }
                    } finally {
                        this.mFragmentListLock.unlock();
                    }
                }
                if (i3 == 0 && !this.mMoreToCome) {
                    i3 = -1;
                }
                log.debug("read(byte[], off, len)", "finished read", "bytes read", Integer.valueOf(i3));
                return i3;
            }
            throw new NullPointerException("Array cannot be null");
        }
        throw new IOException("Failed to receive all fragments of a message");
    }
}
