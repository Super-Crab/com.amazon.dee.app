package kotlinx.coroutines.sync;

import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Semaphore.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u001bB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ,\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0086\b¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0086\b¢\u0006\u0004\b\u0010\u0010\u0011J$\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0086\b¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0019\u001a\u00020\b8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreSegment;", "", "id", "prev", "<init>", "(JLkotlinx/coroutines/sync/SemaphoreSegment;)V", "", "index", "", DeviceStateModule.CANCEL, "(I)Z", "", "expected", "value", "cas", "(ILjava/lang/Object;Ljava/lang/Object;)Z", MetricsConstants.Method.CACHE_GET, "(I)Ljava/lang/Object;", "getAndSet", "(ILjava/lang/Object;)Ljava/lang/Object;", "", "toString", "()Ljava/lang/String;", "getRemoved", "()Z", "removed", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/Segment;"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SemaphoreSegment extends Segment<SemaphoreSegment> {
    private static final AtomicIntegerFieldUpdater cancelledSlots$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreSegment.class, "cancelledSlots");
    @NotNull
    AtomicReferenceArray acquirers;
    private volatile int cancelledSlots;

    public SemaphoreSegment(long j, @Nullable SemaphoreSegment semaphoreSegment) {
        super(j, semaphoreSegment);
        int i;
        i = SemaphoreKt.SEGMENT_SIZE;
        this.acquirers = new AtomicReferenceArray(i);
        this.cancelledSlots = 0;
    }

    public final boolean cancel(int i) {
        Symbol symbol;
        Symbol symbol2;
        int i2;
        symbol = SemaphoreKt.CANCELLED;
        Object andSet = this.acquirers.getAndSet(i, symbol);
        symbol2 = SemaphoreKt.RESUMED;
        boolean z = andSet != symbol2;
        int incrementAndGet = cancelledSlots$FU.incrementAndGet(this);
        i2 = SemaphoreKt.SEGMENT_SIZE;
        if (incrementAndGet == i2) {
            remove();
        }
        return z;
    }

    public final boolean cas(int i, @Nullable Object obj, @Nullable Object obj2) {
        return this.acquirers.compareAndSet(i, obj, obj2);
    }

    @Nullable
    public final Object get(int i) {
        return this.acquirers.get(i);
    }

    @Nullable
    public final Object getAndSet(int i, @Nullable Object obj) {
        return this.acquirers.getAndSet(i, obj);
    }

    @Override // kotlinx.coroutines.internal.Segment
    public boolean getRemoved() {
        int i;
        int i2 = this.cancelledSlots;
        i = SemaphoreKt.SEGMENT_SIZE;
        return i2 == i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SemaphoreSegment[id=");
        outline107.append(getId());
        outline107.append(", hashCode=");
        outline107.append(hashCode());
        outline107.append(JsonReaderKt.END_LIST);
        return outline107.toString();
    }
}
