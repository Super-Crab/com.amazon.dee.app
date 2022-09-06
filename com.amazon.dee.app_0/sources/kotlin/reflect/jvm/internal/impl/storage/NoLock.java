package kotlin.reflect.jvm.internal.impl.storage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
class NoLock implements Lock {
    public static final Lock INSTANCE = new NoLock();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "unit", "kotlin/reflect/jvm/internal/impl/storage/NoLock", "tryLock"));
    }

    private NoLock() {
    }

    @Override // java.util.concurrent.locks.Lock
    public void lock() {
    }

    @Override // java.util.concurrent.locks.Lock
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException("Should not be called");
    }

    @Override // java.util.concurrent.locks.Lock
    @NotNull
    public Condition newCondition() {
        throw new UnsupportedOperationException("Should not be called");
    }

    @Override // java.util.concurrent.locks.Lock
    public boolean tryLock() {
        throw new UnsupportedOperationException("Should not be called");
    }

    @Override // java.util.concurrent.locks.Lock
    public void unlock() {
    }

    @Override // java.util.concurrent.locks.Lock
    public boolean tryLock(long j, @NotNull TimeUnit timeUnit) throws InterruptedException {
        if (timeUnit == null) {
            $$$reportNull$$$0(0);
        }
        throw new UnsupportedOperationException("Should not be called");
    }
}
