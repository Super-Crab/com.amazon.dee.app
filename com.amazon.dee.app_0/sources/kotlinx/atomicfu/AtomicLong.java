package kotlinx.atomicfu;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: AtomicFU.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003J\u0006\u0010\u000e\u001a\u00020\u0003J\u000e\u0010\u000f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003J\u0006\u0010\u0010\u001a\u00020\u0003J\u0006\u0010\u0011\u001a\u00020\u0003J\u000e\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010\u0013\u001a\u00020\u0003J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u0003J\u0011\u0010\u0016\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\u0003H\u0086\nJ\u0011\u0010\u0017\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\u0003H\u0086\nJ\b\u0010\u0018\u001a\u00020\u0019H\u0016R$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u001b"}, d2 = {"Lkotlinx/atomicfu/AtomicLong;", "", "value", "", "(J)V", "getValue", "()J", "setValue", "addAndGet", "delta", "compareAndSet", "", "expect", "update", "decrementAndGet", "getAndAdd", "getAndDecrement", "getAndIncrement", "getAndSet", "incrementAndGet", "lazySet", "", "minusAssign", "plusAssign", "toString", "", "Companion", "atomicfu"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class AtomicLong {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final AtomicLongFieldUpdater<AtomicLong> FU = AtomicLongFieldUpdater.newUpdater(AtomicLong.class, "value");
    private volatile long value;

    /* compiled from: AtomicFU.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R2\u0010\u0003\u001a&\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005 \u0006*\u0012\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlinx/atomicfu/AtomicLong$Companion;", "", "()V", "FU", "Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;", "Lkotlinx/atomicfu/AtomicLong;", "kotlin.jvm.PlatformType", "atomicfu"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AtomicLong(long j) {
        this.value = j;
    }

    public final long addAndGet(long j) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        long addAndGet = FU.addAndGet(this, j);
        InterceptorKt.getInterceptor().afterRMW(this, addAndGet - j, addAndGet);
        return addAndGet;
    }

    public final boolean compareAndSet(long j, long j2) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        boolean compareAndSet = FU.compareAndSet(this, j, j2);
        if (compareAndSet) {
            InterceptorKt.getInterceptor().afterRMW(this, j, j2);
        }
        return compareAndSet;
    }

    public final long decrementAndGet() {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        long decrementAndGet = FU.decrementAndGet(this);
        InterceptorKt.getInterceptor().afterRMW(this, decrementAndGet + 1, decrementAndGet);
        return decrementAndGet;
    }

    public final long getAndAdd(long j) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        long andAdd = FU.getAndAdd(this, j);
        InterceptorKt.getInterceptor().afterRMW(this, andAdd, andAdd + j);
        return andAdd;
    }

    public final long getAndDecrement() {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        long andDecrement = FU.getAndDecrement(this);
        InterceptorKt.getInterceptor().afterRMW(this, andDecrement, andDecrement - 1);
        return andDecrement;
    }

    public final long getAndIncrement() {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        long andIncrement = FU.getAndIncrement(this);
        InterceptorKt.getInterceptor().afterRMW(this, andIncrement, andIncrement + 1);
        return andIncrement;
    }

    public final long getAndSet(long j) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        long andSet = FU.getAndSet(this, j);
        InterceptorKt.getInterceptor().afterRMW(this, andSet, j);
        return andSet;
    }

    public final long getValue() {
        return this.value;
    }

    public final long incrementAndGet() {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        long incrementAndGet = FU.incrementAndGet(this);
        InterceptorKt.getInterceptor().afterRMW(this, incrementAndGet - 1, incrementAndGet);
        return incrementAndGet;
    }

    public final void lazySet(long j) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        FU.lazySet(this, j);
        InterceptorKt.getInterceptor().afterSet(this, j);
    }

    public final void minusAssign(long j) {
        getAndAdd(-j);
    }

    public final void plusAssign(long j) {
        getAndAdd(j);
    }

    public final void setValue(long j) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        this.value = j;
        InterceptorKt.getInterceptor().afterSet(this, j);
    }

    @NotNull
    public String toString() {
        return String.valueOf(this.value);
    }
}
