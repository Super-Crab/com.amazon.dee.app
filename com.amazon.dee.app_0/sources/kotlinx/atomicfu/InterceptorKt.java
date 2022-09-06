package kotlinx.atomicfu;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Interceptor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001H\u0000\"\u001e\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0000\u001a\u00020\u0001@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"<set-?>", "Lkotlinx/atomicfu/AtomicOperationInterceptor;", "interceptor", "getInterceptor", "()Lkotlinx/atomicfu/AtomicOperationInterceptor;", "interceptorLock", "Ljava/util/concurrent/locks/ReentrantLock;", "lockAndSetInterceptor", "", "impl", "unlockAndResetInterceptor", "atomicfu"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class InterceptorKt {
    @NotNull
    private static AtomicOperationInterceptor interceptor = DefaultInterceptor.INSTANCE;
    private static final ReentrantLock interceptorLock = new ReentrantLock();

    @NotNull
    public static final AtomicOperationInterceptor getInterceptor() {
        return interceptor;
    }

    public static final void lockAndSetInterceptor(@NotNull AtomicOperationInterceptor impl) {
        Intrinsics.checkParameterIsNotNull(impl, "impl");
        if (interceptorLock.tryLock() && interceptor == DefaultInterceptor.INSTANCE) {
            interceptor = impl;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Interceptor is locked by another test: ");
        outline107.append(interceptor);
        throw new IllegalStateException(outline107.toString().toString());
    }

    public static final void unlockAndResetInterceptor(@NotNull AtomicOperationInterceptor impl) {
        Intrinsics.checkParameterIsNotNull(impl, "impl");
        if (interceptor == impl) {
            interceptor = DefaultInterceptor.INSTANCE;
            interceptorLock.unlock();
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected interceptor found: ");
        outline107.append(interceptor);
        throw new IllegalStateException(outline107.toString().toString());
    }
}
