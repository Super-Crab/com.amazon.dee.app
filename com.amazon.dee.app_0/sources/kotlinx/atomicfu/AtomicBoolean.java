package kotlinx.atomicfu;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: AtomicFU.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003J\u000e\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0004¨\u0006\u0014"}, d2 = {"Lkotlinx/atomicfu/AtomicBoolean;", "", "v", "", "(Z)V", "_value", "", "value", "getValue", "()Z", "setValue", "compareAndSet", "expect", "update", "getAndSet", "lazySet", "", "toString", "", "Companion", "atomicfu"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class AtomicBoolean {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final AtomicIntegerFieldUpdater<AtomicBoolean> FU = AtomicIntegerFieldUpdater.newUpdater(AtomicBoolean.class, "_value");
    private volatile int _value;

    /* compiled from: AtomicFU.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R2\u0010\u0003\u001a&\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005 \u0006*\u0012\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlinx/atomicfu/AtomicBoolean$Companion;", "", "()V", "FU", "Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;", "Lkotlinx/atomicfu/AtomicBoolean;", "kotlin.jvm.PlatformType", "atomicfu"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AtomicBoolean(boolean z) {
        this._value = z ? 1 : 0;
    }

    public final boolean compareAndSet(boolean z, boolean z2) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        boolean compareAndSet = FU.compareAndSet(this, z ? 1 : 0, z2 ? 1 : 0);
        if (compareAndSet) {
            InterceptorKt.getInterceptor().afterRMW(this, z, z2);
        }
        return compareAndSet;
    }

    public final boolean getAndSet(boolean z) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        int andSet = FU.getAndSet(this, z ? 1 : 0);
        InterceptorKt.getInterceptor().afterRMW(this, andSet == 1, z);
        return andSet == 1;
    }

    public final boolean getValue() {
        return this._value != 0;
    }

    public final void lazySet(boolean z) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        FU.lazySet(this, z ? 1 : 0);
        InterceptorKt.getInterceptor().afterSet(this, z);
    }

    public final void setValue(boolean z) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        this._value = z ? 1 : 0;
        InterceptorKt.getInterceptor().afterSet(this, z);
    }

    @NotNull
    public String toString() {
        return String.valueOf(getValue());
    }
}
