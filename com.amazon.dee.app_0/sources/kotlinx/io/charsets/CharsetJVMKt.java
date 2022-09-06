package kotlinx.io.charsets;

import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.http.auth.HttpAuthHeader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.ByteReadPacketBase;
import kotlinx.io.core.Input;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.core.internal.RequireFailureCapture;
import kotlinx.io.internal.jvm.ErrorsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: CharsetJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0014\u001a\u00020\u0001*\u00060\tj\u0002`\n2\u0006\u0010\u0015\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\u0006\u0010\u001a\u001a\u00020\u0001\u001a\u001e\u0010\u001b\u001a\u00020\u0011*\u00060\tj\u0002`\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0001\u001a \u0010\u001d\u001a\u00020\u0011*\u00060\tj\u0002`\n2\u0006\u0010\u0015\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\u0001H\u0002\u001a \u0010\u001f\u001a\u00020\u0011*\u00060\tj\u0002`\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0001H\u0002\u001a\u0018\u0010 \u001a\u00020!*\u00060\rj\u0002`\u000e2\u0006\u0010\u0017\u001a\u00020\"H\u0000\u001a0\u0010#\u001a\u00020\u0001*\u00060\rj\u0002`\u000e2\u0006\u0010\u0015\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\"H\u0000\u001a*\u0010'\u001a\u00020(*\u00060\rj\u0002`\u000e2\u0006\u0010\u0015\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u00012\b\b\u0002\u0010&\u001a\u00020\u0001\u001a(\u0010)\u001a\u00020(*\u00060\rj\u0002`\u000e2\u0006\u0010\u0015\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u0001H\u0002\u001a\u001e\u0010*\u001a\u00020+*\u00060\rj\u0002`\u000e2\u0006\u0010\u0015\u001a\u00020,2\u0006\u0010\u0017\u001a\u00020-\u001a\f\u0010.\u001a\u00020+*\u00020/H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001d\u0010\u0006\u001a\u00060\u0007j\u0002`\b*\u00060\tj\u0002`\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u001d\u0010\u0006\u001a\u00060\u0007j\u0002`\b*\u00060\rj\u0002`\u000e8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u000f\"\u0019\u0010\u0010\u001a\u00020\u0011*\u00060\u0007j\u0002`\b8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013*\n\u00100\"\u00020\u00072\u00020\u0007*\n\u00101\"\u00020\t2\u00020\t*\n\u00102\"\u00020\r2\u00020\r*\n\u00103\"\u0002042\u000204¨\u00065"}, d2 = {"DECODE_CHAR_BUFFER_SIZE", "", "EmptyByteBuffer", "Ljava/nio/ByteBuffer;", "EmptyCharBuffer", "Ljava/nio/CharBuffer;", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", "Ljava/nio/charset/CharsetDecoder;", "Lkotlinx/io/charsets/CharsetDecoder;", "getCharset", "(Ljava/nio/charset/CharsetDecoder;)Ljava/nio/charset/Charset;", "Ljava/nio/charset/CharsetEncoder;", "Lkotlinx/io/charsets/CharsetEncoder;", "(Ljava/nio/charset/CharsetEncoder;)Ljava/nio/charset/Charset;", "name", "", "getName", "(Ljava/nio/charset/Charset;)Ljava/lang/String;", "decode", "input", "Lkotlinx/io/core/Input;", "dst", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", ReactProperties.GEOFENCE_MAXIMUM_RANGE, "decodeExactBytes", "inputLength", "decodeImplByteBuffer", "Lkotlinx/io/core/ByteReadPacketBase;", "decodeImplSlow", "encodeComplete", "", "Lkotlinx/io/core/IoBuffer;", "encodeImpl", "", "fromIndex", "toIndex", "encodeToByteArray", "", "encodeToByteArraySlow", "encodeUTF8", "", "Lkotlinx/io/core/ByteReadPacket;", "Lkotlinx/io/core/Output;", "throwExceptionWrapped", "Ljava/nio/charset/CoderResult;", "Charset", "CharsetDecoder", "CharsetEncoder", "Charsets", "Lkotlin/text/Charsets;", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CharsetJVMKt {
    private static final int DECODE_CHAR_BUFFER_SIZE = 8192;
    private static final ByteBuffer EmptyByteBuffer;
    private static final CharBuffer EmptyCharBuffer;

    static {
        CharBuffer allocate = CharBuffer.allocate(0);
        if (allocate == null) {
            Intrinsics.throwNpe();
        }
        EmptyCharBuffer = allocate;
        ByteBuffer allocate2 = ByteBuffer.allocate(0);
        if (allocate2 == null) {
            Intrinsics.throwNpe();
        }
        EmptyByteBuffer = allocate2;
    }

    public static /* synthetic */ void Charset$annotations() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x00ba, code lost:
        if (r4 == 0) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00bc, code lost:
        kotlinx.io.core.internal.UnsafeKt.completeReadHead(r13, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00bf, code lost:
        r4 = r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int decode(@org.jetbrains.annotations.NotNull java.nio.charset.CharsetDecoder r12, @org.jetbrains.annotations.NotNull kotlinx.io.core.Input r13, @org.jetbrains.annotations.NotNull java.lang.Appendable r14, int r15) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.charsets.CharsetJVMKt.decode(java.nio.charset.CharsetDecoder, kotlinx.io.core.Input, java.lang.Appendable, int):int");
    }

    @NotNull
    public static final String decodeExactBytes(@NotNull CharsetDecoder receiver$0, @NotNull Input input, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(input, "input");
        if (i == 0) {
            return "";
        }
        if (input instanceof ByteReadPacketBase) {
            ByteReadPacketBase byteReadPacketBase = (ByteReadPacketBase) input;
            if (byteReadPacketBase.getHeadRemaining() >= i) {
                if (byteReadPacketBase.getHead().readBuffer.hasArray()) {
                    ByteBuffer byteBuffer = byteReadPacketBase.getHead().readBuffer;
                    byte[] array = byteBuffer.array();
                    Intrinsics.checkExpressionValueIsNotNull(array, "bb.array()");
                    int arrayOffset = byteBuffer.arrayOffset();
                    Charset charset = receiver$0.charset();
                    Intrinsics.checkExpressionValueIsNotNull(charset, "charset()");
                    String str = new String(array, byteBuffer.position() + arrayOffset, i, charset);
                    byteReadPacketBase.discardExact(i);
                    return str;
                }
                return decodeImplByteBuffer(receiver$0, byteReadPacketBase, i);
            }
        }
        return decodeImplSlow(receiver$0, input, i);
    }

    private static final String decodeImplByteBuffer(@NotNull CharsetDecoder charsetDecoder, ByteReadPacketBase byteReadPacketBase, int i) {
        CharBuffer allocate = CharBuffer.allocate(i);
        ByteBuffer byteBuffer = byteReadPacketBase.getHead().readBuffer;
        int limit = byteBuffer.limit();
        byteBuffer.limit(byteBuffer.position() + i);
        CoderResult rc = charsetDecoder.decode(byteReadPacketBase.getHead().readBuffer, allocate, true);
        Intrinsics.checkExpressionValueIsNotNull(rc, "rc");
        if (rc.isMalformed() || rc.isUnmappable()) {
            throwExceptionWrapped(rc);
        }
        byteBuffer.limit(limit);
        allocate.flip();
        String charBuffer = allocate.toString();
        Intrinsics.checkExpressionValueIsNotNull(charBuffer, "cb.toString()");
        return charBuffer;
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x00c4, code lost:
        if (r7 == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00c6, code lost:
        kotlinx.io.core.internal.UnsafeKt.completeReadHead(r18, r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final java.lang.String decodeImplSlow(@org.jetbrains.annotations.NotNull java.nio.charset.CharsetDecoder r17, kotlinx.io.core.Input r18, int r19) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.charsets.CharsetJVMKt.decodeImplSlow(java.nio.charset.CharsetDecoder, kotlinx.io.core.Input, int):java.lang.String");
    }

    public static final boolean encodeComplete(@NotNull CharsetEncoder receiver$0, @NotNull IoBuffer dst) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        final int writeRemaining = dst.getWriteRemaining();
        if (writeRemaining >= 0) {
            ByteBuffer byteBuffer = dst.writeBuffer;
            int position = byteBuffer.position();
            CoderResult result = receiver$0.encode(EmptyCharBuffer, byteBuffer, true);
            Intrinsics.checkExpressionValueIsNotNull(result, "result");
            if (result.isMalformed() || result.isUnmappable()) {
                throwExceptionWrapped(result);
            }
            boolean isUnderflow = result.isUnderflow();
            int position2 = byteBuffer.position() - position;
            if (position2 >= 0 && position2 <= writeRemaining) {
                dst.readBuffer.limit(dst.writeBuffer.position());
                return isUnderflow;
            }
            ErrorsKt.wrongBufferPositionChangeError(position2, 0);
            throw null;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.charsets.CharsetJVMKt$writeDirect$$inlined$require$5
            @Override // kotlinx.io.core.internal.RequireFailureCapture
            @NotNull
            public Void doFail() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("size ");
                outline107.append(r1);
                outline107.append(" is greater than buffer's remaining capacity ");
                outline107.append(writeRemaining);
                throw new IllegalArgumentException(outline107.toString());
            }
        }.doFail();
        throw null;
    }

    public static final int encodeImpl(@NotNull CharsetEncoder receiver$0, @NotNull CharSequence input, int i, int i2, @NotNull IoBuffer dst) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(input, "input");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        CharBuffer wrap = CharBuffer.wrap(input, i, i2);
        int remaining = wrap.remaining();
        final int writeRemaining = dst.getWriteRemaining();
        if (writeRemaining >= 0) {
            ByteBuffer byteBuffer = dst.writeBuffer;
            int position = byteBuffer.position();
            CoderResult result = receiver$0.encode(wrap, byteBuffer, false);
            Intrinsics.checkExpressionValueIsNotNull(result, "result");
            if (result.isMalformed() || result.isUnmappable()) {
                throwExceptionWrapped(result);
            }
            int position2 = byteBuffer.position() - position;
            if (position2 >= 0 && position2 <= writeRemaining) {
                dst.readBuffer.limit(dst.writeBuffer.position());
                return remaining - wrap.remaining();
            }
            ErrorsKt.wrongBufferPositionChangeError(position2, 0);
            throw null;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.charsets.CharsetJVMKt$writeDirect$$inlined$require$1
            @Override // kotlinx.io.core.internal.RequireFailureCapture
            @NotNull
            public Void doFail() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("size ");
                outline107.append(r1);
                outline107.append(" is greater than buffer's remaining capacity ");
                outline107.append(writeRemaining);
                throw new IllegalArgumentException(outline107.toString());
            }
        }.doFail();
        throw null;
    }

    @NotNull
    public static final byte[] encodeToByteArray(@NotNull CharsetEncoder receiver$0, @NotNull CharSequence input, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(input, "input");
        if (input instanceof String) {
            if (i == 0 && i2 == input.length()) {
                byte[] bytes = ((String) input).getBytes(receiver$0.charset());
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(input as java.lang.String).getBytes(charset())");
                return bytes;
            }
            String substring = ((String) input).substring(i, i2);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            byte[] bytes2 = substring.getBytes(receiver$0.charset());
            Intrinsics.checkExpressionValueIsNotNull(bytes2, "(input.substring(fromInd…ring).getBytes(charset())");
            return bytes2;
        }
        return encodeToByteArraySlow(receiver$0, input, i, i2);
    }

    @NotNull
    public static /* synthetic */ byte[] encodeToByteArray$default(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return encodeToByteArray(charsetEncoder, charSequence, i, i2);
    }

    private static final byte[] encodeToByteArraySlow(@NotNull CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2) {
        byte[] array;
        ByteBuffer encode = charsetEncoder.encode(CharBuffer.wrap(charSequence, i, i2));
        byte[] bArr = null;
        if (encode.hasArray() && encode.arrayOffset() == 0 && (array = encode.array()) != null) {
            if (array.length == encode.remaining()) {
                bArr = array;
            }
        }
        if (bArr != null) {
            return bArr;
        }
        byte[] bArr2 = new byte[encode.remaining()];
        encode.get(bArr2);
        return bArr2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0182, code lost:
        r5 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0184, code lost:
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0185, code lost:
        if (r5 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0187, code lost:
        kotlinx.io.core.internal.UnsafeKt.afterHeadWrite(r20, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x018b, code lost:
        r8 = kotlinx.io.core.internal.UnsafeKt.prepareWriteHead(r20, r5, r8);
        r5 = r6;
        r6 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0192, code lost:
        kotlinx.io.internal.jvm.ErrorsKt.wrongBufferPositionChangeError(r13, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0196, code lost:
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0197, code lost:
        new kotlinx.io.charsets.CharsetJVMKt$writeDirect$$inlined$require$3(r5, r11).doFail();
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x01a0, code lost:
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x01a6, code lost:
        if (r17 <= 0) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x01a9, code lost:
        r6 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x022e, code lost:
        kotlinx.io.internal.jvm.ErrorsKt.wrongBufferPositionChangeError(r8, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0232, code lost:
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x010c, code lost:
        r11.pushBack(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x011a, code lost:
        r19.updateHeadRemaining(r11.getReadRemaining());
        r10.flip();
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0128, code lost:
        if (r10.hasRemaining() == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x012a, code lost:
        r8 = kotlinx.io.core.internal.UnsafeKt.prepareWriteHead(r20, r6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x012f, code lost:
        r5 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0130, code lost:
        r11 = r8.getWriteRemaining();
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0134, code lost:
        if (r5 > r11) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0136, code lost:
        r13 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0138, code lost:
        r13 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0139, code lost:
        if (r13 == 0) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x013b, code lost:
        r13 = r8.writeBuffer;
        r14 = r13.position();
        r6 = r18.encode(r10, r13, false);
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, "cr");
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x014d, code lost:
        if (r6.isUnmappable() != false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0153, code lost:
        if (r6.isMalformed() == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0155, code lost:
        throwExceptionWrapped(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x015c, code lost:
        if (r6.isOverflow() == false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0162, code lost:
        if (r13.hasRemaining() == false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0164, code lost:
        r6 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0167, code lost:
        r6 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0168, code lost:
        r13 = r13.position() - r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x016d, code lost:
        if (r13 < 0) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x016f, code lost:
        if (r13 > r11) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0171, code lost:
        r8.readBuffer.limit(r8.writeBuffer.position());
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0180, code lost:
        if (r10.hasRemaining() == false) goto L73;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void encodeUTF8(@org.jetbrains.annotations.NotNull java.nio.charset.CharsetEncoder r18, @org.jetbrains.annotations.NotNull kotlinx.io.core.ByteReadPacket r19, @org.jetbrains.annotations.NotNull kotlinx.io.core.Output r20) {
        /*
            Method dump skipped, instructions count: 600
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.charsets.CharsetJVMKt.encodeUTF8(java.nio.charset.CharsetEncoder, kotlinx.io.core.ByteReadPacket, kotlinx.io.core.Output):void");
    }

    @NotNull
    public static final Charset getCharset(@NotNull CharsetEncoder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Charset charset = receiver$0.charset();
        Intrinsics.checkExpressionValueIsNotNull(charset, "charset()");
        return charset;
    }

    @NotNull
    public static final String getName(@NotNull Charset receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        String name = receiver$0.name();
        Intrinsics.checkExpressionValueIsNotNull(name, "name()");
        return name;
    }

    private static final void throwExceptionWrapped(@NotNull CoderResult coderResult) {
        try {
            coderResult.throwException();
        } catch (java.nio.charset.MalformedInputException e) {
            String message = e.getMessage();
            if (message == null) {
                message = "Failed to decode bytes";
            }
            throw new MalformedInputException(message);
        }
    }

    @NotNull
    public static final Charset getCharset(@NotNull CharsetDecoder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Charset charset = receiver$0.charset();
        if (charset == null) {
            Intrinsics.throwNpe();
        }
        return charset;
    }
}
