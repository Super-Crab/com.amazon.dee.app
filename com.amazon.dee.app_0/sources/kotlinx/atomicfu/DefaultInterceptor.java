package kotlinx.atomicfu;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: Interceptor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lkotlinx/atomicfu/DefaultInterceptor;", "Lkotlinx/atomicfu/AtomicOperationInterceptor;", "()V", "toString", "", "atomicfu"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class DefaultInterceptor extends AtomicOperationInterceptor {
    public static final DefaultInterceptor INSTANCE = new DefaultInterceptor();

    private DefaultInterceptor() {
    }

    @NotNull
    public String toString() {
        return "DefaultInterceptor";
    }
}
