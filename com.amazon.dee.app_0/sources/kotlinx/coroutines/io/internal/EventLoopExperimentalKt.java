package kotlinx.coroutines.io.internal;

import kotlin.Metadata;
import kotlinx.coroutines.io.internal.CoroutinesEventLoop;
import org.jetbrains.annotations.NotNull;
/* compiled from: EventLoopExperimental.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\b\u0010\u0002\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0003"}, d2 = {"eventLoop", "Lkotlinx/coroutines/io/internal/CoroutinesEventLoop;", "detectEventLoop", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class EventLoopExperimentalKt {
    private static final CoroutinesEventLoop eventLoop;

    static {
        CoroutinesEventLoop.FutureReflectionImpl futureReflectionImpl = CoroutinesEventLoop.FutureReflectionImpl.INSTANCE;
        boolean isApplicable = futureReflectionImpl.isApplicable();
        CoroutinesEventLoop coroutinesEventLoop = futureReflectionImpl;
        if (!isApplicable) {
            coroutinesEventLoop = null;
        }
        if (coroutinesEventLoop == null) {
            coroutinesEventLoop = CoroutinesEventLoop.Stub.INSTANCE;
        }
        eventLoop = coroutinesEventLoop;
    }

    @NotNull
    public static final CoroutinesEventLoop detectEventLoop() {
        return eventLoop;
    }
}
