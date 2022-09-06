package kotlinx.coroutines.io.jvm.javaio;

import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.alexa.routing.api.RouteParameter;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.io.ByteWriteChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Blocking.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\b\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\"\u0010\u000f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0012H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/io/jvm/javaio/OutputAdapter;", "Ljava/io/OutputStream;", RouteParameter.PARENT, "Lkotlinx/coroutines/Job;", "channel", "Lkotlinx/coroutines/io/ByteWriteChannel;", "(Lkotlinx/coroutines/Job;Lkotlinx/coroutines/io/ByteWriteChannel;)V", "loop", "kotlinx/coroutines/io/jvm/javaio/OutputAdapter$loop$1", "Lkotlinx/coroutines/io/jvm/javaio/OutputAdapter$loop$1;", "single", "", "close", "", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "write", "b", "off", "", "len", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class OutputAdapter extends OutputStream {
    private final ByteWriteChannel channel;
    private final OutputAdapter$loop$1 loop;
    private byte[] single;

    public OutputAdapter(@Nullable Job job, @NotNull ByteWriteChannel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        this.channel = channel;
        this.loop = new OutputAdapter$loop$1(this, job, job);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        Object obj;
        try {
            OutputAdapter$loop$1 outputAdapter$loop$1 = this.loop;
            obj = BlockingKt.CloseToken;
            outputAdapter$loop$1.submitAndAwait(obj);
            this.loop.shutdown();
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public synchronized void flush() {
        Object obj;
        OutputAdapter$loop$1 outputAdapter$loop$1 = this.loop;
        obj = BlockingKt.FlushToken;
        outputAdapter$loop$1.submitAndAwait(obj);
    }

    @Override // java.io.OutputStream
    public synchronized void write(int i) {
        byte[] bArr = this.single;
        if (bArr == null) {
            bArr = new byte[1];
            this.single = bArr;
        }
        bArr[0] = (byte) i;
        this.loop.submitAndAwait(bArr, 0, 1);
    }

    @Override // java.io.OutputStream
    public synchronized void write(@Nullable byte[] bArr, int i, int i2) {
        OutputAdapter$loop$1 outputAdapter$loop$1 = this.loop;
        if (bArr == null) {
            Intrinsics.throwNpe();
        }
        outputAdapter$loop$1.submitAndAwait(bArr, i, i2);
    }
}
