package kotlinx.coroutines.io;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.io.core.ExperimentalIoApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReadSession.kt */
@ExperimentalIoApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/io/SuspendableReadSession;", "Lkotlinx/coroutines/io/ReadSession;", "await", "", "atLeast", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface SuspendableReadSession extends ReadSession {

    /* compiled from: ReadSession.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        @Nullable
        public static /* synthetic */ Object await$default(SuspendableReadSession suspendableReadSession, int i, Continuation continuation, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 1) != 0) {
                    i = 1;
                }
                return suspendableReadSession.await(i, continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: await");
        }
    }

    @Nullable
    Object await(int i, @NotNull Continuation<? super Boolean> continuation);
}
