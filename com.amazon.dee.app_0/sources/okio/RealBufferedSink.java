package okio;

import com.amazon.alexa.presence.service.PresenceJobService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.callercontext.ContextChain;
import com.facebook.imagepipeline.producers.DecodeProducer;
import io.ktor.http.auth.HttpAuthHeader;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: RealBufferedSink.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0001H\u0016J\b\u0010\u0011\u001a\u00020\u0001H\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001eH\u0016J \u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001bH\u0016J\u0018\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\u001a\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#H\u0016J \u0010\u001a\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001bH\u0016J\u0018\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020$2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010%\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020$H\u0016J\u0010\u0010&\u001a\u00020\u00012\u0006\u0010'\u001a\u00020\u001bH\u0016J\u0010\u0010(\u001a\u00020\u00012\u0006\u0010)\u001a\u00020!H\u0016J\u0010\u0010*\u001a\u00020\u00012\u0006\u0010)\u001a\u00020!H\u0016J\u0010\u0010+\u001a\u00020\u00012\u0006\u0010,\u001a\u00020\u001bH\u0016J\u0010\u0010-\u001a\u00020\u00012\u0006\u0010,\u001a\u00020\u001bH\u0016J\u0010\u0010.\u001a\u00020\u00012\u0006\u0010)\u001a\u00020!H\u0016J\u0010\u0010/\u001a\u00020\u00012\u0006\u0010)\u001a\u00020!H\u0016J\u0010\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u001bH\u0016J\u0010\u00102\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u001bH\u0016J\u0018\u00103\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u00192\u0006\u00105\u001a\u000206H\u0016J(\u00103\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u00192\u0006\u00107\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u001b2\u0006\u00105\u001a\u000206H\u0016J\u0010\u00109\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u0019H\u0016J \u00109\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u00192\u0006\u00107\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u001bH\u0016J\u0010\u0010:\u001a\u00020\u00012\u0006\u0010;\u001a\u00020\u001bH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068Ö\u0002X\u0096\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lokio/RealBufferedSink;", "Lokio/BufferedSink;", "sink", "Lokio/Sink;", "(Lokio/Sink;)V", "buffer", "Lokio/Buffer;", "buffer$annotations", "()V", "getBuffer", "()Lokio/Buffer;", "bufferField", "closed", "", "close", "", "emit", "emitCompleteSegments", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "isOpen", "outputStream", "Ljava/io/OutputStream;", "timeout", "Lokio/Timeout;", "toString", "", "write", "", "source", "Ljava/nio/ByteBuffer;", "", "offset", DecodeProducer.EXTRA_BITMAP_BYTES, "", "byteString", "Lokio/ByteString;", "Lokio/Source;", "writeAll", "writeByte", "b", "writeDecimalLong", "v", "writeHexadecimalUnsignedLong", "writeInt", ContextChain.TAG_INFRA, "writeIntLe", "writeLong", "writeLongLe", "writeShort", "s", "writeShortLe", "writeString", "string", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "beginIndex", "endIndex", "writeUtf8", "writeUtf8CodePoint", "codePoint", "okio"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RealBufferedSink implements BufferedSink {
    @JvmField
    @NotNull
    public final Buffer bufferField;
    @JvmField
    public boolean closed;
    @JvmField
    @NotNull
    public final Sink sink;

    public RealBufferedSink(@NotNull Sink sink) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        this.sink = sink;
        this.bufferField = new Buffer();
    }

    public static /* synthetic */ void buffer$annotations() {
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer buffer() {
        return this.bufferField;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        Throwable th = null;
        try {
            if (this.bufferField.size() > 0) {
                this.sink.write(this.bufferField, this.bufferField.size());
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.sink.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.closed = true;
        if (th != null) {
            throw th;
        }
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: emit */
    public BufferedSink mo12589emit() {
        if (!this.closed) {
            long size = this.bufferField.size();
            if (size > 0) {
                this.sink.write(this.bufferField, size);
            }
            return this;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: emitCompleteSegments */
    public BufferedSink mo12590emitCompleteSegments() {
        if (!this.closed) {
            long completeSegmentByteCount = this.bufferField.completeSegmentByteCount();
            if (completeSegmentByteCount > 0) {
                this.sink.write(this.bufferField, completeSegmentByteCount);
            }
            return this;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
        if (!this.closed) {
            if (this.bufferField.size() > 0) {
                Sink sink = this.sink;
                Buffer buffer = this.bufferField;
                sink.write(buffer, buffer.size());
            }
            this.sink.flush();
            return;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer getBuffer() {
        return this.bufferField;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // okio.BufferedSink
    @NotNull
    public OutputStream outputStream() {
        return new OutputStream() { // from class: okio.RealBufferedSink$outputStream$1
            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                RealBufferedSink.this.close();
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
                RealBufferedSink realBufferedSink = RealBufferedSink.this;
                if (!realBufferedSink.closed) {
                    realBufferedSink.flush();
                }
            }

            @NotNull
            public String toString() {
                return RealBufferedSink.this + ".outputStream()";
            }

            @Override // java.io.OutputStream
            public void write(int i) {
                RealBufferedSink realBufferedSink = RealBufferedSink.this;
                if (!realBufferedSink.closed) {
                    realBufferedSink.bufferField.mo12596writeByte((int) ((byte) i));
                    RealBufferedSink.this.mo12590emitCompleteSegments();
                    return;
                }
                throw new IOException("closed");
            }

            @Override // java.io.OutputStream
            public void write(@NotNull byte[] data, int i, int i2) {
                Intrinsics.checkParameterIsNotNull(data, "data");
                RealBufferedSink realBufferedSink = RealBufferedSink.this;
                if (!realBufferedSink.closed) {
                    realBufferedSink.bufferField.mo12595write(data, i, i2);
                    RealBufferedSink.this.mo12590emitCompleteSegments();
                    return;
                }
                throw new IOException("closed");
            }
        };
    }

    @Override // okio.Sink
    @NotNull
    /* renamed from: timeout */
    public Timeout mo12584timeout() {
        return this.sink.mo12584timeout();
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("buffer(");
        outline107.append(this.sink);
        outline107.append(')');
        return outline107.toString();
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(@NotNull ByteBuffer source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (!this.closed) {
            int write = this.bufferField.write(source);
            mo12590emitCompleteSegments();
            return write;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    public long writeAll(@NotNull Source source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        long j = 0;
        while (true) {
            long read = source.read(this.bufferField, 8192);
            if (read == -1) {
                return j;
            }
            j += read;
            mo12590emitCompleteSegments();
        }
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: writeByte */
    public BufferedSink mo12596writeByte(int i) {
        if (!this.closed) {
            this.bufferField.mo12596writeByte(i);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: writeDecimalLong */
    public BufferedSink mo12597writeDecimalLong(long j) {
        if (!this.closed) {
            this.bufferField.mo12597writeDecimalLong(j);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: writeHexadecimalUnsignedLong */
    public BufferedSink mo12598writeHexadecimalUnsignedLong(long j) {
        if (!this.closed) {
            this.bufferField.mo12598writeHexadecimalUnsignedLong(j);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: writeInt */
    public BufferedSink mo12599writeInt(int i) {
        if (!this.closed) {
            this.bufferField.mo12599writeInt(i);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: writeIntLe */
    public BufferedSink mo12600writeIntLe(int i) {
        if (!this.closed) {
            this.bufferField.mo12600writeIntLe(i);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: writeLong */
    public BufferedSink mo12601writeLong(long j) {
        if (!this.closed) {
            this.bufferField.mo12601writeLong(j);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: writeLongLe */
    public BufferedSink mo12602writeLongLe(long j) {
        if (!this.closed) {
            this.bufferField.mo12602writeLongLe(j);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: writeShort */
    public BufferedSink mo12603writeShort(int i) {
        if (!this.closed) {
            this.bufferField.mo12603writeShort(i);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: writeShortLe */
    public BufferedSink mo12604writeShortLe(int i) {
        if (!this.closed) {
            this.bufferField.mo12604writeShortLe(i);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: writeString */
    public BufferedSink mo12606writeString(@NotNull String string, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        if (!this.closed) {
            this.bufferField.mo12606writeString(string, charset);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: writeUtf8 */
    public BufferedSink mo12607writeUtf8(@NotNull String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        if (!this.closed) {
            this.bufferField.mo12607writeUtf8(string);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: writeUtf8CodePoint */
    public BufferedSink mo12609writeUtf8CodePoint(int i) {
        if (!this.closed) {
            this.bufferField.mo12609writeUtf8CodePoint(i);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.Sink
    public void write(@NotNull Buffer source, long j) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (!this.closed) {
            this.bufferField.write(source, j);
            mo12590emitCompleteSegments();
            return;
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: writeString */
    public BufferedSink mo12605writeString(@NotNull String string, int i, int i2, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        if (!this.closed) {
            this.bufferField.mo12605writeString(string, i, i2, charset);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: writeUtf8 */
    public BufferedSink mo12608writeUtf8(@NotNull String string, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        if (!this.closed) {
            this.bufferField.mo12608writeUtf8(string, i, i2);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: write */
    public BufferedSink mo12591write(@NotNull ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(byteString, "byteString");
        if (!this.closed) {
            this.bufferField.mo12591write(byteString);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: write */
    public BufferedSink mo12592write(@NotNull ByteString byteString, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(byteString, "byteString");
        if (!this.closed) {
            this.bufferField.mo12592write(byteString, i, i2);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: write */
    public BufferedSink mo12594write(@NotNull byte[] source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (!this.closed) {
            this.bufferField.mo12594write(source);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: write */
    public BufferedSink mo12595write(@NotNull byte[] source, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (!this.closed) {
            this.bufferField.mo12595write(source, i, i2);
            return mo12590emitCompleteSegments();
        }
        throw new IllegalStateException("closed".toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    /* renamed from: write */
    public BufferedSink mo12593write(@NotNull Source source, long j) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        while (j > 0) {
            long read = source.read(this.bufferField, j);
            if (read != -1) {
                j -= read;
                mo12590emitCompleteSegments();
            } else {
                throw new EOFException();
            }
        }
        return this;
    }
}
