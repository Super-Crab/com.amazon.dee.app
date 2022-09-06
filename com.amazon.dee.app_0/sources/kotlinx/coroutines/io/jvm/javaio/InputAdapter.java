package kotlinx.coroutines.io.jvm.javaio;

import com.amazon.alexa.routing.api.RouteParameter;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.ByteReadChannelKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Blocking.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\b\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016J\"\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/io/jvm/javaio/InputAdapter;", "Ljava/io/InputStream;", RouteParameter.PARENT, "Lkotlinx/coroutines/Job;", "channel", "Lkotlinx/coroutines/io/ByteReadChannel;", "(Lkotlinx/coroutines/Job;Lkotlinx/coroutines/io/ByteReadChannel;)V", "loop", "kotlinx/coroutines/io/jvm/javaio/InputAdapter$loop$1", "Lkotlinx/coroutines/io/jvm/javaio/InputAdapter$loop$1;", "single", "", "available", "", "close", "", "read", "b", "off", "len", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class InputAdapter extends InputStream {
    private final ByteReadChannel channel;
    private final InputAdapter$loop$1 loop;
    private byte[] single;

    public InputAdapter(@Nullable Job job, @NotNull ByteReadChannel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        this.channel = channel;
        this.loop = new InputAdapter$loop$1(this, job, job);
    }

    @Override // java.io.InputStream
    public int available() {
        return this.channel.getAvailableForRead();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        super.close();
        ByteReadChannelKt.cancel(this.channel);
        this.loop.shutdown();
    }

    @Override // java.io.InputStream
    public synchronized int read() {
        byte[] bArr = this.single;
        if (bArr == null) {
            bArr = new byte[1];
            this.single = bArr;
        }
        int submitAndAwait = this.loop.submitAndAwait(bArr, 0, 1);
        if (submitAndAwait == -1) {
            return -1;
        }
        if (submitAndAwait == 1) {
            return bArr[0] & 255;
        }
        throw new IllegalStateException(("rc should be 1 or -1 but got " + submitAndAwait).toString());
    }

    @Override // java.io.InputStream
    public synchronized int read(@Nullable byte[] bArr, int i, int i2) {
        InputAdapter$loop$1 inputAdapter$loop$1;
        inputAdapter$loop$1 = this.loop;
        if (bArr == null) {
            Intrinsics.throwNpe();
        }
        return inputAdapter$loop$1.submitAndAwait(bArr, i, i2);
    }
}
