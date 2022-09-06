package kotlinx.io.charsets;

import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.BytePacketBuilder;
import kotlinx.io.core.ByteReadPacket;
import kotlinx.io.core.ByteReadPacketBase;
import kotlinx.io.core.ExperimentalIoApi;
import kotlinx.io.core.Input;
import kotlinx.io.core.InputKt;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.core.Output;
import kotlinx.io.core.PacketJVMKt;
import kotlinx.io.core.internal.UnsafeKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Encoding.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\u001a\"\u0010\u0002\u001a\u00020\u0003*\u00060\u0004j\u0002`\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u001a,\u0010\n\u001a\u00020\u000b*\u00060\fj\u0002`\r2\u0006\u0010\u0006\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\tH\u0007\u001a0\u0010\n\u001a\u00020\u0011*\u00060\fj\u0002`\r2\u0006\u0010\u0006\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007\u001a\u0018\u0010\u0014\u001a\u00020\t*\u00060\fj\u0002`\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a,\u0010\u0015\u001a\u00020\u0001*\u00060\fj\u0002`\r2\u0006\u0010\u0006\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\tH\u0007\u001a,\u0010\u0016\u001a\u00020\u0001*\u00060\fj\u0002`\r2\u0006\u0010\u0006\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\tH\u0000\u001a0\u0010\u0017\u001a\u00020\t*\u00060\fj\u0002`\r2\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0000\u001a\u0018\u0010\u0019\u001a\u00020\u000b*\u00060\fj\u0002`\r2\u0006\u0010\u0006\u001a\u00020\u000bH\u0007\u001a\f\u0010\u001a\u001a\u00020\u001b*\u00020\u0007H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"EmptyByteArray", "", "decode", "", "Ljava/nio/charset/CharsetDecoder;", "Lkotlinx/io/charsets/CharsetDecoder;", "input", "Lkotlinx/io/core/Input;", ReactProperties.GEOFENCE_MAXIMUM_RANGE, "", "encode", "Lkotlinx/io/core/ByteReadPacket;", "Ljava/nio/charset/CharsetEncoder;", "Lkotlinx/io/charsets/CharsetEncoder;", "", "fromIndex", "toIndex", "", "dst", "Lkotlinx/io/core/Output;", "encodeCompleteImpl", "encodeToByteArrayImpl", "encodeToByteArrayImpl1", "encodeToImpl", "destination", "encodeUTF8", "sizeEstimate", "", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class EncodingKt {
    private static final byte[] EmptyByteArray = new byte[0];

    @ExperimentalIoApi
    @NotNull
    public static final String decode(@NotNull CharsetDecoder receiver$0, @NotNull Input input, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(input, "input");
        StringBuilder sb = new StringBuilder((int) Math.min(i, sizeEstimate(input)));
        CharsetJVMKt.decode(receiver$0, input, sb, i);
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    @ExperimentalIoApi
    @NotNull
    public static /* synthetic */ String decode$default(CharsetDecoder charsetDecoder, Input input, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return decode(charsetDecoder, input, i);
    }

    @Deprecated(message = "Use writeText on Output instead.", replaceWith = @ReplaceWith(expression = "dst.writeText(input, fromIndex, toIndex, charset)", imports = {"kotlinx.io.core.writeText"}))
    public static final void encode(@NotNull CharsetEncoder receiver$0, @NotNull CharSequence input, int i, int i2, @NotNull Output dst) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(input, "input");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        encodeToImpl(receiver$0, dst, input, i, i2);
    }

    @ExperimentalIoApi
    @NotNull
    public static /* synthetic */ ByteReadPacket encode$default(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return encode(charsetEncoder, charSequence, i, i2);
    }

    private static final int encodeCompleteImpl(@NotNull CharsetEncoder charsetEncoder, Output output) {
        IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        int i = 1;
        int i2 = 0;
        while (true) {
            try {
                int writeRemaining = prepareWriteHead.getWriteRemaining();
                i = CharsetJVMKt.encodeComplete(charsetEncoder, prepareWriteHead) ? 0 : i + 1;
                i2 += writeRemaining - prepareWriteHead.getWriteRemaining();
                if (!(i > 0)) {
                    return i2;
                }
                prepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, prepareWriteHead);
            } finally {
                UnsafeKt.afterHeadWrite(output, prepareWriteHead);
            }
        }
    }

    @Deprecated(message = "Internal API. Will be hidden in future releases. Use encodeToByteArray instead.", replaceWith = @ReplaceWith(expression = "encodeToByteArray(input, fromIndex, toIndex)", imports = {}))
    @NotNull
    public static final byte[] encodeToByteArrayImpl(@NotNull CharsetEncoder receiver$0, @NotNull CharSequence input, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(input, "input");
        return CharsetJVMKt.encodeToByteArray(receiver$0, input, i, i2);
    }

    @Deprecated(message = "Internal API. Will be hidden in future releases. Use encodeToByteArray instead.", replaceWith = @ReplaceWith(expression = "encodeToByteArray(input, fromIndex, toIndex)", imports = {}))
    @NotNull
    public static /* synthetic */ byte[] encodeToByteArrayImpl$default(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return encodeToByteArrayImpl(charsetEncoder, charSequence, i, i2);
    }

    @NotNull
    public static final byte[] encodeToByteArrayImpl1(@NotNull CharsetEncoder receiver$0, @NotNull CharSequence input, int i, int i2) {
        byte[] readBytes$default;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(input, "input");
        if (i >= i2) {
            return EmptyByteArray;
        }
        IoBuffer mo12351borrow = IoBuffer.Companion.getPool().mo12351borrow();
        try {
            IoBuffer.Companion.getNoPool();
            int encodeImpl = i + CharsetJVMKt.encodeImpl(receiver$0, input, i, i2, mo12351borrow);
            if (encodeImpl == i2) {
                readBytes$default = new byte[mo12351borrow.getReadRemaining()];
                InputKt.readFully$default((Input) mo12351borrow, readBytes$default, 0, 0, 6, (Object) null);
            } else {
                BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(0, IoBuffer.Companion.getPool());
                bytePacketBuilder.last$kotlinx_io(mo12351borrow.makeView());
                encodeToImpl(receiver$0, bytePacketBuilder, input, encodeImpl, i2);
                readBytes$default = kotlinx.io.core.StringsKt.readBytes$default(bytePacketBuilder.build(), 0, 1, null);
            }
            return readBytes$default;
        } finally {
            mo12351borrow.release(IoBuffer.Companion.getPool());
        }
    }

    @NotNull
    public static /* synthetic */ byte[] encodeToByteArrayImpl1$default(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return encodeToByteArrayImpl1(charsetEncoder, charSequence, i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0055, code lost:
        throw new java.lang.IllegalStateException("Check failed.".toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int encodeToImpl(@org.jetbrains.annotations.NotNull java.nio.charset.CharsetEncoder r7, @org.jetbrains.annotations.NotNull kotlinx.io.core.Output r8, @org.jetbrains.annotations.NotNull java.lang.CharSequence r9, int r10, int r11) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            java.lang.String r0 = "destination"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            java.lang.String r0 = "input"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            r0 = 0
            if (r10 < r11) goto L13
            return r0
        L13:
            r1 = 0
            r2 = 1
            kotlinx.io.core.IoBuffer r1 = kotlinx.io.core.internal.UnsafeKt.prepareWriteHead(r8, r2, r1)
            r3 = r0
        L1a:
            int r4 = r1.getWriteRemaining()     // Catch: java.lang.Throwable -> L56
            int r5 = kotlinx.io.charsets.CharsetJVMKt.encodeImpl(r7, r9, r10, r11, r1)     // Catch: java.lang.Throwable -> L56
            if (r5 < 0) goto L26
            r6 = r2
            goto L27
        L26:
            r6 = r0
        L27:
            if (r6 == 0) goto L4a
            int r10 = r10 + r5
            int r6 = r1.getWriteRemaining()     // Catch: java.lang.Throwable -> L56
            int r4 = r4 - r6
            int r3 = r3 + r4
            if (r10 < r11) goto L34
            r4 = r0
            goto L3a
        L34:
            if (r5 != 0) goto L39
            r4 = 8
            goto L3a
        L39:
            r4 = r2
        L3a:
            if (r4 > 0) goto L45
            kotlinx.io.core.internal.UnsafeKt.afterHeadWrite(r8, r1)
            int r7 = encodeCompleteImpl(r7, r8)
            int r3 = r3 + r7
            return r3
        L45:
            kotlinx.io.core.IoBuffer r1 = kotlinx.io.core.internal.UnsafeKt.prepareWriteHead(r8, r4, r1)     // Catch: java.lang.Throwable -> L56
            goto L1a
        L4a:
            java.lang.String r7 = "Check failed."
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L56
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L56
            r9.<init>(r7)     // Catch: java.lang.Throwable -> L56
            throw r9     // Catch: java.lang.Throwable -> L56
        L56:
            r7 = move-exception
            kotlinx.io.core.internal.UnsafeKt.afterHeadWrite(r8, r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.charsets.EncodingKt.encodeToImpl(java.nio.charset.CharsetEncoder, kotlinx.io.core.Output, java.lang.CharSequence, int, int):int");
    }

    @ExperimentalIoApi
    @NotNull
    public static final ByteReadPacket encodeUTF8(@NotNull CharsetEncoder receiver$0, @NotNull ByteReadPacket input) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(input, "input");
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(0);
        try {
            CharsetJVMKt.encodeUTF8(receiver$0, input, BytePacketBuilder);
            return BytePacketBuilder.build();
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }

    public static final long sizeEstimate(@NotNull Input receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (receiver$0 instanceof ByteReadPacket) {
            return ((ByteReadPacket) receiver$0).m12336getRemaining();
        }
        if (!(receiver$0 instanceof ByteReadPacketBase)) {
            return 16L;
        }
        return Math.max(((ByteReadPacketBase) receiver$0).m12336getRemaining(), 16L);
    }

    @ExperimentalIoApi
    @NotNull
    public static final ByteReadPacket encode(@NotNull CharsetEncoder receiver$0, @NotNull CharSequence input, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(input, "input");
        BytePacketBuilder BytePacketBuilder = PacketJVMKt.BytePacketBuilder(0);
        try {
            encodeToImpl(receiver$0, BytePacketBuilder, input, i, i2);
            return BytePacketBuilder.build();
        } catch (Throwable th) {
            BytePacketBuilder.release();
            throw th;
        }
    }
}
