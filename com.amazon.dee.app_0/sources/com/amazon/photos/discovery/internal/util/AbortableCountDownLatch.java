package com.amazon.photos.discovery.internal.util;

import amazon.speech.simclient.settings.Settings;
import com.amazon.dee.app.elements.bridges.MetricsModule;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AbortableCountDownLatch.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007J\b\u0010\u000b\u001a\u00020\tH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u0004\u0018\u00010\u0007J\u0006\u0010\u0012\u001a\u00020\fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/AbortableCountDownLatch;", "Ljava/util/concurrent/CountDownLatch;", "count", "", "(I)V", "abortReason", "Ljava/util/concurrent/atomic/AtomicReference;", "", MetricsModule.TimelineState.ABORT, "", Settings.ListeningSettings.EXTRA_REASON, "await", "", "timeout", "", "unit", "Ljava/util/concurrent/TimeUnit;", "getAbortReason", "isAborted", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AbortableCountDownLatch extends CountDownLatch {
    private final AtomicReference<Throwable> abortReason;

    public AbortableCountDownLatch(int i) {
        super(i);
        this.abortReason = new AtomicReference<>();
    }

    public final void abort(@NotNull Throwable reason) {
        Intrinsics.checkParameterIsNotNull(reason, "reason");
        if (this.abortReason.compareAndSet(null, reason)) {
            while (getCount() > 0) {
                countDown();
            }
        }
    }

    @Override // java.util.concurrent.CountDownLatch
    public void await() throws InterruptedException {
        if (this.abortReason.get() == null) {
            super.await();
            if (this.abortReason.get() != null) {
                throw new InterruptedException("Aborted");
            }
            return;
        }
        throw new InterruptedException("Aborted");
    }

    @Nullable
    public final Throwable getAbortReason() {
        return this.abortReason.get();
    }

    public final boolean isAborted() {
        return this.abortReason.get() != null;
    }

    @Override // java.util.concurrent.CountDownLatch
    public boolean await(long j, @NotNull TimeUnit unit) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        if (this.abortReason.get() == null) {
            boolean await = super.await(j, unit);
            if (this.abortReason.get() != null) {
                throw new InterruptedException("Aborted");
            }
            return await;
        }
        throw new InterruptedException("Aborted");
    }
}
