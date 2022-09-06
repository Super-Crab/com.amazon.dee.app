package kotlinx.atomicfu;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: AtomicFU.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u0014*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0014B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J\u001b\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0000¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0016R&\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00028\u0000@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\b\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0015"}, d2 = {"Lkotlinx/atomicfu/AtomicRef;", ExifInterface.GPS_DIRECTION_TRUE, "", "value", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "setValue", "Ljava/lang/Object;", "compareAndSet", "", "expect", "update", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "getAndSet", "(Ljava/lang/Object;)Ljava/lang/Object;", "lazySet", "", "toString", "", "Companion", "atomicfu"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class AtomicRef<T> {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final AtomicReferenceFieldUpdater<AtomicRef<?>, Object> FU = AtomicReferenceFieldUpdater.newUpdater(AtomicRef.class, Object.class, "value");
    private volatile T value;

    /* compiled from: AtomicFU.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R^\u0010\u0003\u001aR\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00010\u0001 \u0006*(\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00010\u0001\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlinx/atomicfu/AtomicRef$Companion;", "", "()V", "FU", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lkotlinx/atomicfu/AtomicRef;", "kotlin.jvm.PlatformType", "atomicfu"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AtomicRef(T t) {
        this.value = t;
    }

    public final boolean compareAndSet(T t, T t2) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        boolean compareAndSet = FU.compareAndSet(this, t, t2);
        if (compareAndSet) {
            InterceptorKt.getInterceptor().afterRMW(this, t, t2);
        }
        return compareAndSet;
    }

    public final T getAndSet(T t) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        T t2 = (T) FU.getAndSet(this, t);
        InterceptorKt.getInterceptor().afterRMW(this, t2, t);
        return t2;
    }

    public final T getValue() {
        return this.value;
    }

    public final void lazySet(T t) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        FU.lazySet(this, t);
        InterceptorKt.getInterceptor().afterSet((AtomicRef<AtomicRef<T>>) this, (AtomicRef<T>) t);
    }

    public final void setValue(T t) {
        InterceptorKt.getInterceptor().beforeUpdate(this);
        this.value = t;
        InterceptorKt.getInterceptor().afterSet((AtomicRef<AtomicRef<T>>) this, (AtomicRef<T>) t);
    }

    @NotNull
    public String toString() {
        return String.valueOf(this.value);
    }
}
