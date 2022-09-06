package io.ktor.network.sockets;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.network.selector.SelectableBase;
import io.ktor.network.selector.SelectorManager;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.io.ByteWriteChannel;
import kotlinx.coroutines.io.ByteWriteChannelKt;
import kotlinx.coroutines.io.ReaderJob;
import kotlinx.coroutines.io.WriterJob;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NIOSocket.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b \u0018\u0000*\u000e\b\u0000\u0010\u0001 \u0001*\u00020\u0002*\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006B%\u0012\u0006\u0010\u0007\u001a\u00028\u0000\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0002\u0010\rJ\n\u00100\u001a\u0004\u0018\u00010+H\u0002JE\u00101\u001a\u0002H2\"\b\b\u0001\u00102*\u00020'2\u0006\u00103\u001a\u0002042\u0006\u0010\u0007\u001a\u0002052\u000e\u00106\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H20\u001a2\f\u00107\u001a\b\u0012\u0004\u0012\u0002H208H\u0002¢\u0006\u0002\u00109J\u000e\u0010:\u001a\u00020$2\u0006\u0010\u0007\u001a\u000205J\u000e\u0010;\u001a\u00020\u001b2\u0006\u0010\u0007\u001a\u000205J\b\u0010<\u001a\u00020 H\u0002J\b\u0010=\u001a\u00020 H\u0016J\u001e\u0010>\u001a\u0004\u0018\u00010+2\b\u0010?\u001a\u0004\u0018\u00010+2\b\u0010@\u001a\u0004\u0018\u00010+H\u0002J\b\u0010A\u001a\u00020 H\u0016R\u0016\u0010\u0007\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0016\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010%\u001a\u00020&*\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010'0\u001a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R*\u0010*\u001a\u0004\u0018\u00010+*\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010'0\u001a8BX\u0082\u0004¢\u0006\f\u0012\u0004\b,\u0010-\u001a\u0004\b.\u0010/¨\u0006B"}, d2 = {"Lio/ktor/network/sockets/NIOSocketImpl;", ExifInterface.LATITUDE_SOUTH, "Ljava/nio/channels/ByteChannel;", "Ljava/nio/channels/SelectableChannel;", "Lio/ktor/network/sockets/ReadWriteSocket;", "Lio/ktor/network/selector/SelectableBase;", "Lkotlinx/coroutines/CoroutineScope;", "channel", "selector", "Lio/ktor/network/selector/SelectorManager;", "pool", "Lkotlinx/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "(Ljava/nio/channels/SelectableChannel;Lio/ktor/network/selector/SelectorManager;Lkotlinx/io/pool/ObjectPool;)V", "getChannel", "()Ljava/nio/channels/SelectableChannel;", "Ljava/nio/channels/SelectableChannel;", "closeFlag", "Ljava/util/concurrent/atomic/AtomicBoolean;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getPool", "()Lkotlinx/io/pool/ObjectPool;", "readerJob", "Ljava/util/concurrent/atomic/AtomicReference;", "Lkotlinx/coroutines/io/ReaderJob;", "getSelector", "()Lio/ktor/network/selector/SelectorManager;", "socketContext", "Lkotlinx/coroutines/CompletableDeferred;", "", "getSocketContext", "()Lkotlinx/coroutines/CompletableDeferred;", "writerJob", "Lkotlinx/coroutines/io/WriterJob;", "completedOrNotStarted", "", "Lkotlinx/coroutines/Job;", "getCompletedOrNotStarted", "(Ljava/util/concurrent/atomic/AtomicReference;)Z", ADMRegistrationConstants.CALL_EXCEPTION, "", "exception$annotations", "(Ljava/util/concurrent/atomic/AtomicReference;)V", "getException", "(Ljava/util/concurrent/atomic/AtomicReference;)Ljava/lang/Throwable;", "actualClose", "attachFor", "J", "name", "", "Lkotlinx/coroutines/io/ByteChannel;", "ref", "producer", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlinx/coroutines/io/ByteChannel;Ljava/util/concurrent/atomic/AtomicReference;Lkotlin/jvm/functions/Function0;)Lkotlinx/coroutines/Job;", "attachForReading", "attachForWriting", "checkChannels", "close", "combine", "e1", "e2", "dispose", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public abstract class NIOSocketImpl<S extends SelectableChannel & ByteChannel> extends SelectableBase implements ReadWriteSocket, CoroutineScope {
    @NotNull
    private final S channel;
    private final AtomicBoolean closeFlag;
    @Nullable
    private final ObjectPool<ByteBuffer> pool;
    private final AtomicReference<ReaderJob> readerJob;
    @NotNull
    private final SelectorManager selector;
    @NotNull
    private final CompletableDeferred<Unit> socketContext;
    private final AtomicReference<WriterJob> writerJob;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NIOSocketImpl(@NotNull S channel, @NotNull SelectorManager selector, @Nullable ObjectPool<ByteBuffer> objectPool) {
        super(channel);
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        this.channel = channel;
        this.selector = selector;
        this.pool = objectPool;
        this.closeFlag = new AtomicBoolean();
        this.readerJob = new AtomicReference<>();
        this.writerJob = new AtomicReference<>();
        this.socketContext = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
    }

    private final Throwable actualClose() {
        try {
            mo10306getChannel().close();
            super.close();
            mo10305getSocketContext().complete(Unit.INSTANCE);
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        this.selector.notifyClosed(this);
        return th;
    }

    private final <J extends Job> J attachFor(String str, kotlinx.coroutines.io.ByteChannel byteChannel, AtomicReference<J> atomicReference, Function0<? extends J> function0) {
        if (!this.closeFlag.get()) {
            J mo12560invoke = function0.mo12560invoke();
            if (atomicReference.compareAndSet(null, mo12560invoke)) {
                if (!this.closeFlag.get()) {
                    byteChannel.attachJob(mo12560invoke);
                    mo12560invoke.invokeOnCompletion(new NIOSocketImpl$attachFor$1(this));
                    return mo12560invoke;
                }
                Throwable closedChannelException = new ClosedChannelException();
                mo12560invoke.mo12309cancel();
                byteChannel.close(closedChannelException);
                throw closedChannelException;
            }
            IllegalStateException illegalStateException = new IllegalStateException(GeneratedOutlineSupport1.outline72(str, " channel has been already set"));
            mo12560invoke.mo12309cancel();
            throw illegalStateException;
        }
        Throwable closedChannelException2 = new ClosedChannelException();
        byteChannel.close(closedChannelException2);
        throw closedChannelException2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkChannels() {
        if (!this.closeFlag.get() || !getCompletedOrNotStarted(this.readerJob) || !getCompletedOrNotStarted(this.writerJob)) {
            return;
        }
        Throwable exception = getException(this.readerJob);
        Throwable exception2 = getException(this.writerJob);
        Throwable combine = combine(combine(exception, exception2), actualClose());
        if (combine == null) {
            mo10305getSocketContext().complete(Unit.INSTANCE);
        } else {
            mo10305getSocketContext().completeExceptionally(combine);
        }
    }

    private final Throwable combine(Throwable th, Throwable th2) {
        if (th == null) {
            return th2;
        }
        if (th2 == null || th == th2) {
            return th;
        }
        ExceptionsKt__ExceptionsKt.addSuppressed(th, th2);
        return th;
    }

    private static /* synthetic */ void exception$annotations(AtomicReference atomicReference) {
    }

    private final boolean getCompletedOrNotStarted(@NotNull AtomicReference<? extends Job> atomicReference) {
        Job job = atomicReference.get();
        return job == null || job.isCompleted();
    }

    private final Throwable getException(@NotNull AtomicReference<? extends Job> atomicReference) {
        CancellationException cancellationException;
        Job job = atomicReference.get();
        if (job != null) {
            if (job.isActive() || job.isCancelled()) {
                job = null;
            }
            if (job != null && (cancellationException = job.getCancellationException()) != null && cancellationException != null) {
                return cancellationException.getCause();
            }
            return null;
        }
        return null;
    }

    @Override // io.ktor.network.sockets.AReadable
    @NotNull
    public final WriterJob attachForReading(@NotNull kotlinx.coroutines.io.ByteChannel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        return (WriterJob) attachFor("reading", channel, this.writerJob, new NIOSocketImpl$attachForReading$1(this, channel));
    }

    @Override // io.ktor.network.sockets.AWritable
    @NotNull
    public final ReaderJob attachForWriting(@NotNull kotlinx.coroutines.io.ByteChannel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        return (ReaderJob) attachFor("writing", channel, this.readerJob, new NIOSocketImpl$attachForWriting$1(this, channel));
    }

    @Override // io.ktor.network.selector.SelectableBase, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ByteWriteChannel mo12310getChannel;
        if (this.closeFlag.compareAndSet(false, true)) {
            ReaderJob readerJob = this.readerJob.get();
            if (readerJob != null && (mo12310getChannel = readerJob.mo12310getChannel()) != null) {
                ByteWriteChannelKt.close(mo12310getChannel);
            }
            WriterJob writerJob = this.writerJob.get();
            if (writerJob != null) {
                writerJob.mo12309cancel();
            }
            checkChannels();
        }
    }

    @Override // io.ktor.network.selector.SelectableBase, kotlinx.coroutines.DisposableHandle
    public void dispose() {
        close();
    }

    @Override // io.ktor.network.selector.SelectableBase, io.ktor.network.selector.Selectable
    @NotNull
    /* renamed from: getChannel */
    public S mo10306getChannel() {
        return this.channel;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return mo10305getSocketContext();
    }

    @Nullable
    public final ObjectPool<ByteBuffer> getPool() {
        return this.pool;
    }

    @NotNull
    public final SelectorManager getSelector() {
        return this.selector;
    }

    @Override // io.ktor.network.sockets.ASocket
    @NotNull
    /* renamed from: getSocketContext */
    public CompletableDeferred<Unit> mo10305getSocketContext() {
        return this.socketContext;
    }
}
