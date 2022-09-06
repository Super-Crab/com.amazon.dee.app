package kotlinx.coroutines;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.jetbrains.annotations.NotNull;
/* compiled from: ThreadPoolDispatcher.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0005H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/ThreadPoolDispatcher;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcherBase;", "nThreads", "", "name", "", "(ILjava/lang/String;)V", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "threadNo", "Ljava/util/concurrent/atomic/AtomicInteger;", "close", "", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ThreadPoolDispatcher extends ExecutorCoroutineDispatcherBase {
    @NotNull
    private final Executor executor;
    private final int nThreads;
    private final String name;
    private final AtomicInteger threadNo = new AtomicInteger();

    public ThreadPoolDispatcher(int i, @NotNull String str) {
        this.nThreads = i;
        this.name = str;
        this.executor = Executors.newScheduledThreadPool(this.nThreads, new ThreadFactory() { // from class: kotlinx.coroutines.ThreadPoolDispatcher$executor$1
            @Override // java.util.concurrent.ThreadFactory
            @NotNull
            public final PoolThread newThread(Runnable runnable) {
                int i2;
                String str2;
                AtomicInteger atomicInteger;
                String sb;
                ThreadPoolDispatcher threadPoolDispatcher = ThreadPoolDispatcher.this;
                i2 = threadPoolDispatcher.nThreads;
                if (i2 == 1) {
                    sb = ThreadPoolDispatcher.this.name;
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    str2 = ThreadPoolDispatcher.this.name;
                    sb2.append(str2);
                    sb2.append(ProcessIdUtil.DEFAULT_PROCESSID);
                    atomicInteger = ThreadPoolDispatcher.this.threadNo;
                    sb2.append(atomicInteger.incrementAndGet());
                    sb = sb2.toString();
                }
                return new PoolThread(threadPoolDispatcher, runnable, sb);
            }
        });
        initFutureCancellation$kotlinx_coroutines_core();
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcherBase, kotlinx.coroutines.ExecutorCoroutineDispatcher, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Executor executor = getExecutor();
        if (executor != null) {
            ((ExecutorService) executor).shutdown();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.concurrent.ExecutorService");
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher
    @NotNull
    public Executor getExecutor() {
        return this.executor;
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcherBase, kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ThreadPoolDispatcher[");
        outline107.append(this.nThreads);
        outline107.append(", ");
        return GeneratedOutlineSupport1.outline89(outline107, this.name, JsonReaderKt.END_LIST);
    }
}
