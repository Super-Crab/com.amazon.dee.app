package kotlinx.io.pool;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.uimanager.ViewProps;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.pool.ObjectPool;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.jetbrains.annotations.NotNull;
/* compiled from: DefaultPool.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b&\u0018\u0000 $*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001$B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u0011\u001a\u00028\u0000¢\u0006\u0002\u0010\u0012J\u0015\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J\u0015\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u0005H\u0002J\r\u0010\u001b\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0005H\u0002J\u0013\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00028\u0000¢\u0006\u0002\u0010\u0019J\u000f\u0010\u001f\u001a\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0002\u0010\u0012J\u0015\u0010 \u001a\u00020!2\u0006\u0010\u0014\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\"J\u0015\u0010#\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lkotlinx/io/pool/DefaultPool;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/io/pool/ObjectPool;", "capacity", "", "(I)V", "getCapacity", "()I", "instances", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "maxIndex", "next", "", "shift", ViewProps.TOP, "", "borrow", "()Ljava/lang/Object;", "clearInstance", "instance", "(Ljava/lang/Object;)Ljava/lang/Object;", "dispose", "", "disposeInstance", "(Ljava/lang/Object;)V", "popTop", "produceInstance", "pushTop", "index", "recycle", "tryPop", "tryPush", "", "(Ljava/lang/Object;)Z", "validateInstance", "Companion", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class DefaultPool<T> implements ObjectPool<T> {
    public static final Companion Companion = new Companion(null);
    private static final AtomicLongFieldUpdater<DefaultPool<?>> Top;
    private final int capacity;
    private final AtomicReferenceArray<T> instances;
    private final int maxIndex;
    private final int[] next;
    private final int shift;
    private volatile long top;

    /* compiled from: DefaultPool.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0018\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlinx/io/pool/DefaultPool$Companion;", "", "()V", "Top", "Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;", "Lkotlinx/io/pool/DefaultPool;", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        AtomicLongFieldUpdater<DefaultPool<?>> newUpdater = AtomicLongFieldUpdater.newUpdater(DefaultPool.class, DefaultPool$Companion$Top$1.INSTANCE.getName());
        Intrinsics.checkExpressionValueIsNotNull(newUpdater, "AtomicLongFieldUpdater.n…wner::class.java, p.name)");
        Top = newUpdater;
    }

    public DefaultPool(int i) {
        this.capacity = i;
        boolean z = false;
        if (this.capacity > 0) {
            if (this.capacity <= 536870911 ? true : z) {
                this.maxIndex = Integer.highestOneBit((this.capacity * 4) - 1) * 2;
                this.shift = Integer.numberOfLeadingZeros(this.maxIndex) + 1;
                this.instances = new AtomicReferenceArray<>(this.maxIndex + 1);
                this.next = new int[this.maxIndex + 1];
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("capacity should be less or equal to 536870911 but it is ");
            outline107.append(this.capacity);
            throw new IllegalArgumentException(outline107.toString().toString());
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("capacity should be positive but it is ");
        outline1072.append(this.capacity);
        throw new IllegalArgumentException(outline1072.toString().toString());
    }

    private final int popTop() {
        long j;
        long j2;
        int i;
        do {
            j = this.top;
            if (j == 0) {
                return 0;
            }
            j2 = ((j >> 32) & BodyPartID.bodyIdMax) + 1;
            i = (int) (BodyPartID.bodyIdMax & j);
            if (i == 0) {
                return 0;
            }
        } while (!Top.compareAndSet(this, j, (j2 << 32) | this.next[i]));
        return i;
    }

    private final void pushTop(int i) {
        long j;
        long j2;
        if (i > 0) {
            do {
                j = this.top;
                j2 = i | ((((j >> 32) & BodyPartID.bodyIdMax) + 1) << 32);
                this.next[i] = (int) (BodyPartID.bodyIdMax & j);
            } while (!Top.compareAndSet(this, j, j2));
            return;
        }
        throw new IllegalArgumentException("index should be positive".toString());
    }

    private final T tryPop() {
        int popTop = popTop();
        if (popTop == 0) {
            return null;
        }
        return this.instances.getAndSet(popTop, null);
    }

    private final boolean tryPush(T t) {
        int identityHashCode = ((System.identityHashCode(t) * (-1640531527)) >>> this.shift) + 1;
        for (int i = 0; i < 8; i++) {
            if (this.instances.compareAndSet(identityHashCode, null, t)) {
                pushTop(identityHashCode);
                return true;
            }
            identityHashCode--;
            if (identityHashCode == 0) {
                identityHashCode = this.maxIndex;
            }
        }
        return false;
    }

    @Override // kotlinx.io.pool.ObjectPool
    @NotNull
    /* renamed from: borrow */
    public final T mo12351borrow() {
        T clearInstance;
        T tryPop = tryPop();
        return (tryPop == null || (clearInstance = clearInstance(tryPop)) == null) ? produceInstance() : clearInstance;
    }

    @NotNull
    protected T clearInstance(@NotNull T instance) {
        Intrinsics.checkParameterIsNotNull(instance, "instance");
        return instance;
    }

    @Override // kotlinx.io.pool.ObjectPool, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ObjectPool.DefaultImpls.close(this);
    }

    @Override // kotlinx.io.pool.ObjectPool
    public final void dispose() {
        while (true) {
            T tryPop = tryPop();
            if (tryPop != null) {
                disposeInstance(tryPop);
            } else {
                return;
            }
        }
    }

    protected void disposeInstance(@NotNull T instance) {
        Intrinsics.checkParameterIsNotNull(instance, "instance");
    }

    @Override // kotlinx.io.pool.ObjectPool
    public final int getCapacity() {
        return this.capacity;
    }

    @NotNull
    protected abstract T produceInstance();

    @Override // kotlinx.io.pool.ObjectPool
    public final void recycle(@NotNull T instance) {
        Intrinsics.checkParameterIsNotNull(instance, "instance");
        validateInstance(instance);
        if (!tryPush(instance)) {
            disposeInstance(instance);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateInstance(@NotNull T instance) {
        Intrinsics.checkParameterIsNotNull(instance, "instance");
    }
}
