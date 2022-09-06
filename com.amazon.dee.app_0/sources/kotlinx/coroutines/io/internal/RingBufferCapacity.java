package kotlinx.coroutines.io.internal;

import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RingBufferCapacity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\b\u0000\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H\u0002J \u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H\u0002J\u000e\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\tJ\u0006\u0010\u0014\u001a\u00020\u0012J\u0006\u0010\u0015\u001a\u00020\u0012J\u0006\u0010\u0016\u001a\u00020\tJ\u0006\u0010\u0017\u001a\u00020\tJ\u0006\u0010\u0018\u001a\u00020\u0012J\u000e\u0010\u0019\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003J\u000e\u0010\u001a\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u0003J\u000e\u0010\u001b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003J\u000e\u0010\u001c\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003J\u000e\u0010\u001d\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u0003R\u0012\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lkotlinx/coroutines/io/internal/RingBufferCapacity;", "", "totalCapacity", "", "(I)V", "availableForRead", "availableForWrite", "pendingToFlush", "completeRead", "", JsonReportFormat.COUNT, "completeReadOverflow", "", "pending", "remaining", "update", "completeWrite", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "", "forceLockForRelease", "isEmpty", "isFull", "resetForRead", "resetForWrite", "tryLockForRelease", "tryReadAtMost", "tryReadExact", "tryWriteAtLeast", "tryWriteAtMost", "tryWriteExact", "Companion", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class RingBufferCapacity {
    private static final AtomicIntegerFieldUpdater<RingBufferCapacity> AvailableForRead;
    private static final AtomicIntegerFieldUpdater<RingBufferCapacity> AvailableForWrite;
    public static final Companion Companion = new Companion(null);
    private static final AtomicIntegerFieldUpdater<RingBufferCapacity> PendingToFlush;
    @JvmField
    public volatile int availableForRead;
    @JvmField
    public volatile int availableForWrite;
    @JvmField
    public volatile int pendingToFlush;
    private final int totalCapacity;

    /* compiled from: RingBufferCapacity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/io/internal/RingBufferCapacity$Companion;", "", "()V", "AvailableForRead", "Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;", "Lkotlinx/coroutines/io/internal/RingBufferCapacity;", "AvailableForWrite", "PendingToFlush", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        AtomicIntegerFieldUpdater<RingBufferCapacity> newUpdater = AtomicIntegerFieldUpdater.newUpdater(RingBufferCapacity.class, RingBufferCapacity$Companion$AvailableForRead$1.INSTANCE.getName());
        Intrinsics.checkExpressionValueIsNotNull(newUpdater, "AtomicIntegerFieldUpdate…wner::class.java, p.name)");
        AvailableForRead = newUpdater;
        AtomicIntegerFieldUpdater<RingBufferCapacity> newUpdater2 = AtomicIntegerFieldUpdater.newUpdater(RingBufferCapacity.class, RingBufferCapacity$Companion$AvailableForWrite$1.INSTANCE.getName());
        Intrinsics.checkExpressionValueIsNotNull(newUpdater2, "AtomicIntegerFieldUpdate…wner::class.java, p.name)");
        AvailableForWrite = newUpdater2;
        AtomicIntegerFieldUpdater<RingBufferCapacity> newUpdater3 = AtomicIntegerFieldUpdater.newUpdater(RingBufferCapacity.class, RingBufferCapacity$Companion$PendingToFlush$1.INSTANCE.getName());
        Intrinsics.checkExpressionValueIsNotNull(newUpdater3, "AtomicIntegerFieldUpdate…wner::class.java, p.name)");
        PendingToFlush = newUpdater3;
    }

    public RingBufferCapacity(int i) {
        this.totalCapacity = i;
        this.availableForWrite = this.totalCapacity;
    }

    private final Void completeReadOverflow(int i, int i2, int i3) {
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Completed read overflow: ", i, " + ", i3, " = ");
        outline110.append(i2);
        outline110.append(" > ");
        outline110.append(this.totalCapacity);
        throw new IllegalArgumentException(outline110.toString());
    }

    public final void completeRead(int i) {
        int i2;
        int i3;
        int i4 = this.totalCapacity;
        AtomicIntegerFieldUpdater<RingBufferCapacity> atomicIntegerFieldUpdater = AvailableForWrite;
        do {
            i2 = this.availableForWrite;
            i3 = i2 + i;
            if (i3 > i4) {
                completeReadOverflow(i2, i3, i);
                throw null;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, i3));
    }

    public final void completeWrite(int i) {
        int i2;
        int i3;
        int i4 = this.totalCapacity;
        AtomicIntegerFieldUpdater<RingBufferCapacity> atomicIntegerFieldUpdater = PendingToFlush;
        do {
            i2 = this.pendingToFlush;
            i3 = i2 + i;
            if (i3 > i4) {
                completeReadOverflow(i2, i);
                throw null;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, i3));
    }

    public final boolean flush() {
        int i;
        int i2;
        AtomicIntegerFieldUpdater<RingBufferCapacity> atomicIntegerFieldUpdater = AvailableForRead;
        int andSet = PendingToFlush.getAndSet(this, 0);
        do {
            i = this.availableForRead;
            i2 = i + andSet;
            if (i == i2) {
                break;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i, i2));
        return i2 > 0;
    }

    public final void forceLockForRelease() {
        AvailableForWrite.getAndSet(this, 0);
    }

    public final boolean isEmpty() {
        return this.availableForWrite == this.totalCapacity;
    }

    public final boolean isFull() {
        return this.availableForWrite == 0;
    }

    public final void resetForRead() {
        this.availableForRead = this.totalCapacity;
        this.availableForWrite = 0;
        this.pendingToFlush = 0;
    }

    public final void resetForWrite() {
        this.availableForRead = 0;
        this.availableForWrite = this.totalCapacity;
        this.pendingToFlush = 0;
    }

    public final boolean tryLockForRelease() {
        int i;
        AtomicIntegerFieldUpdater<RingBufferCapacity> atomicIntegerFieldUpdater = AvailableForWrite;
        do {
            i = this.availableForWrite;
            if (this.pendingToFlush > 0 || this.availableForRead > 0 || i != this.totalCapacity) {
                return false;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i, 0));
        return true;
    }

    public final int tryReadAtMost(int i) {
        int i2;
        int min;
        AtomicIntegerFieldUpdater<RingBufferCapacity> atomicIntegerFieldUpdater = AvailableForRead;
        do {
            i2 = this.availableForRead;
            min = Math.min(i, i2);
            if (min == 0) {
                return 0;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, i2 - min));
        return min;
    }

    public final boolean tryReadExact(int i) {
        int i2;
        AtomicIntegerFieldUpdater<RingBufferCapacity> atomicIntegerFieldUpdater = AvailableForRead;
        do {
            i2 = this.availableForRead;
            if (i2 < i) {
                return false;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, i2 - i));
        return true;
    }

    public final int tryWriteAtLeast(int i) {
        int i2;
        AtomicIntegerFieldUpdater<RingBufferCapacity> atomicIntegerFieldUpdater = AvailableForWrite;
        do {
            i2 = this.availableForWrite;
            if (i2 < i) {
                return 0;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, 0));
        return i2;
    }

    public final int tryWriteAtMost(int i) {
        int i2;
        int min;
        AtomicIntegerFieldUpdater<RingBufferCapacity> atomicIntegerFieldUpdater = AvailableForWrite;
        do {
            i2 = this.availableForWrite;
            min = Math.min(i, i2);
            if (min == 0) {
                return 0;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, i2 - min));
        return min;
    }

    public final boolean tryWriteExact(int i) {
        int i2;
        AtomicIntegerFieldUpdater<RingBufferCapacity> atomicIntegerFieldUpdater = AvailableForWrite;
        do {
            i2 = this.availableForWrite;
            if (i2 < i) {
                return false;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, i2 - i));
        return true;
    }

    private final Void completeReadOverflow(int i, int i2) {
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Complete write overflow: ", i, " + ", i2, " > ");
        outline110.append(this.totalCapacity);
        throw new IllegalArgumentException(outline110.toString());
    }
}
