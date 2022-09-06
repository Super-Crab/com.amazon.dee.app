package kotlinx.io.streams;

import android.support.v4.media.session.PlaybackStateCompat;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.io.core.BytePacketBuilder;
import kotlinx.io.core.ByteReadPacket;
import kotlinx.io.core.PacketJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Streams.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0007\u001a\u0012\u0010\b\u001a\u00020\u0004*\u00020\u00032\u0006\u0010\t\u001a\u00020\n\u001a\u0012\u0010\u000b\u001a\u00020\u0004*\u00020\u00032\u0006\u0010\t\u001a\u00020\n\u001a\u0012\u0010\f\u001a\u00020\u0004*\u00020\u00032\u0006\u0010\t\u001a\u00020\n\u001a\u001c\u0010\r\u001a\u00020\u0004*\u00020\u00032\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\nH\u0002\u001a\n\u0010\u0010\u001a\u00020\u0011*\u00020\u0004\u001a#\u0010\u0012\u001a\u00020\u0013*\u00020\u00062\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00130\u0015¢\u0006\u0002\b\u0016\u001a\u0012\u0010\u0012\u001a\u00020\u0013*\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0004\u001a\n\u0010\u0018\u001a\u00020\u0019*\u00020\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"SkipBuffer", "", "inputStream", "Ljava/io/InputStream;", "Lkotlinx/io/core/ByteReadPacket;", "outputStream", "Ljava/io/OutputStream;", "Lkotlinx/io/core/BytePacketBuilder;", "readPacketAtLeast", JsonReportFormat.COUNT, "", "readPacketAtMost", "readPacketExact", "readPacketImpl", ReactProperties.GEOFENCE_MINIMUM_RANGE, ReactProperties.GEOFENCE_MAXIMUM_RANGE, "readerUTF8", "Ljava/io/Reader;", "writePacket", "", "builder", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "packet", "writerUTF8", "Ljava/io/Writer;", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class StreamsKt {
    private static final char[] SkipBuffer = new char[8192];

    @NotNull
    public static final InputStream inputStream(@NotNull final ByteReadPacket receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new InputStream() { // from class: kotlinx.io.streams.StreamsKt$inputStream$1
            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(ByteReadPacket.this.m12336getRemaining(), Integer.MAX_VALUE);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                ByteReadPacket.this.release();
            }

            @Override // java.io.InputStream
            public int read() {
                if (ByteReadPacket.this.isEmpty()) {
                    return -1;
                }
                return ByteReadPacket.this.readByte() & 255;
            }
        };
    }

    @NotNull
    public static final OutputStream outputStream(@NotNull final BytePacketBuilder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new OutputStream() { // from class: kotlinx.io.streams.StreamsKt$outputStream$1
            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.OutputStream
            public void write(int i) {
                BytePacketBuilder.this.writeByte((byte) i);
            }

            @Override // java.io.OutputStream
            public void write(@NotNull byte[] b, int i, int i2) {
                Intrinsics.checkParameterIsNotNull(b, "b");
                BytePacketBuilder.this.writeFully(b, i, i2);
            }
        };
    }

    @NotNull
    public static final ByteReadPacket readPacketAtLeast(@NotNull InputStream receiver$0, long j) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return readPacketImpl(receiver$0, j, Long.MAX_VALUE);
    }

    @NotNull
    public static final ByteReadPacket readPacketAtMost(@NotNull InputStream receiver$0, long j) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return readPacketImpl(receiver$0, 1L, j);
    }

    @NotNull
    public static final ByteReadPacket readPacketExact(@NotNull InputStream receiver$0, long j) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return readPacketImpl(receiver$0, j, j);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x006d, code lost:
        throw new java.io.EOFException("Premature end of stream: was read " + r0 + " bytes of " + r11);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final kotlinx.io.core.ByteReadPacket readPacketImpl(@org.jetbrains.annotations.NotNull java.io.InputStream r10, long r11, long r13) {
        /*
            r0 = 0
            int r2 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            r3 = 1
            r4 = 0
            if (r2 < 0) goto La
            r5 = r3
            goto Lb
        La:
            r5 = r4
        Lb:
            if (r5 == 0) goto L8c
            int r5 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r5 > 0) goto L13
            r5 = r3
            goto L14
        L13:
            r5 = r4
        L14:
            if (r5 == 0) goto L73
            r5 = 4096(0x1000, double:2.0237E-320)
            long r5 = kotlin.ranges.RangesKt.coerceAtMost(r13, r5)
            int r5 = (int) r5
            byte[] r5 = new byte[r5]
            r6 = 0
            kotlinx.io.core.BytePacketBuilder r3 = kotlinx.io.core.PacketJVMKt.BytePacketBuilder$default(r4, r3, r6)
        L24:
            int r6 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r6 < 0) goto L32
            if (r6 != 0) goto L2d
            if (r2 != 0) goto L2d
            goto L32
        L2d:
            kotlinx.io.core.ByteReadPacket r10 = r3.build()
            return r10
        L32:
            long r6 = r13 - r0
            r8 = 2147483647(0x7fffffff, float:NaN)
            long r8 = (long) r8
            long r6 = java.lang.Math.min(r6, r8)     // Catch: java.lang.Throwable -> L6e
            int r6 = (int) r6     // Catch: java.lang.Throwable -> L6e
            int r7 = r5.length     // Catch: java.lang.Throwable -> L6e
            int r6 = java.lang.Math.min(r6, r7)     // Catch: java.lang.Throwable -> L6e
            int r6 = r10.read(r5, r4, r6)     // Catch: java.lang.Throwable -> L6e
            r7 = -1
            if (r6 == r7) goto L4f
            long r7 = (long) r6     // Catch: java.lang.Throwable -> L6e
            long r0 = r0 + r7
            r3.writeFully(r5, r4, r6)     // Catch: java.lang.Throwable -> L6e
            goto L24
        L4f:
            java.io.EOFException r10 = new java.io.EOFException     // Catch: java.lang.Throwable -> L6e
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6e
            r13.<init>()     // Catch: java.lang.Throwable -> L6e
            java.lang.String r14 = "Premature end of stream: was read "
            r13.append(r14)     // Catch: java.lang.Throwable -> L6e
            r13.append(r0)     // Catch: java.lang.Throwable -> L6e
            java.lang.String r14 = " bytes of "
            r13.append(r14)     // Catch: java.lang.Throwable -> L6e
            r13.append(r11)     // Catch: java.lang.Throwable -> L6e
            java.lang.String r11 = r13.toString()     // Catch: java.lang.Throwable -> L6e
            r10.<init>(r11)     // Catch: java.lang.Throwable -> L6e
            throw r10     // Catch: java.lang.Throwable -> L6e
        L6e:
            r10 = move-exception
            r3.release()
            throw r10
        L73:
            java.lang.String r10 = "min shouldn't be greater than max: "
            java.lang.String r0 = " > "
            java.lang.StringBuilder r10 = com.android.tools.r8.GeneratedOutlineSupport1.outline111(r10, r11, r0)
            r10.append(r13)
            java.lang.String r10 = r10.toString()
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r10 = r10.toString()
            r11.<init>(r10)
            throw r11
        L8c:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r11 = "min shouldn't be negative"
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.streams.StreamsKt.readPacketImpl(java.io.InputStream, long, long):kotlinx.io.core.ByteReadPacket");
    }

    @NotNull
    public static final Reader readerUTF8(@NotNull final ByteReadPacket receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new Reader() { // from class: kotlinx.io.streams.StreamsKt$readerUTF8$1
            @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                ByteReadPacket.this.release();
            }

            @Override // java.io.Reader
            public int read(@NotNull char[] cbuf, int i, int i2) {
                Intrinsics.checkParameterIsNotNull(cbuf, "cbuf");
                return ByteReadPacket.this.readCbuf$kotlinx_io(cbuf, i, i2);
            }

            @Override // java.io.Reader
            public long skip(long j) {
                char[] cArr;
                int read;
                cArr = StreamsKt.SkipBuffer;
                int length = cArr.length;
                long j2 = 0;
                while (j2 < j && (read = read(cArr, 0, (int) Math.min(length, j - j2))) != -1) {
                    j2 += read;
                }
                return j2;
            }
        };
    }

    public static final void writePacket(@NotNull OutputStream receiver$0, @NotNull ByteReadPacket packet) {
        long coerceAtMost;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(packet, "packet");
        long m12336getRemaining = packet.m12336getRemaining();
        if (m12336getRemaining == 0) {
            return;
        }
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(m12336getRemaining, (long) PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
        byte[] bArr = new byte[(int) coerceAtMost];
        while (packet.isNotEmpty()) {
            try {
                receiver$0.write(bArr, 0, packet.readAvailable(bArr));
            } finally {
                packet.release();
            }
        }
    }

    @NotNull
    public static final Writer writerUTF8(@NotNull final BytePacketBuilder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new Writer() { // from class: kotlinx.io.streams.StreamsKt$writerUTF8$1
            @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.Writer, java.io.Flushable
            public void flush() {
            }

            @Override // java.io.Writer
            public void write(@NotNull char[] cbuf, int i, int i2) {
                Intrinsics.checkParameterIsNotNull(cbuf, "cbuf");
                BytePacketBuilder.this.append(cbuf, i, i2 + i);
            }
        };
    }

    public static final void writePacket(@NotNull OutputStream receiver$0, @NotNull Function1<? super BytePacketBuilder, Unit> builder) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(0);
        try {
            builder.mo12165invoke(BytePacketBuilder);
            writePacket(receiver$0, BytePacketBuilder.build());
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }
}
