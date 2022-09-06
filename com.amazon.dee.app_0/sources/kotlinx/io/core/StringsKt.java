package kotlinx.io.core;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.http.auth.HttpAuthHeader;
import java.io.EOFException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.Charsets;
import kotlinx.io.charsets.CharsetJVMKt;
import kotlinx.io.charsets.EncodingKt;
import kotlinx.io.core.internal.MalformedUTF8InputException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Strings.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0003H\u0002\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0003H\u0002\u001a\r\u0010\b\u001a\u00020\t*\u00020\nH\u0082\b\u001a\u0014\u0010\u000b\u001a\u00020\f*\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u001a\n\u0010\u000b\u001a\u00020\f*\u00020\u000f\u001a\u0012\u0010\u000b\u001a\u00020\f*\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u0003\u001a\u001e\u0010\u0010\u001a\u00020\f*\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u001a.\u0010\u0013\u001a\u00020\u0003*\u00020\u000f2\n\u0010\u0014\u001a\u00060\u0015j\u0002`\u00162\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u001a.\u0010\u0013\u001a\u00020\u0003*\u00020\u000f2\n\u0010\u0014\u001a\u00060\u0015j\u0002`\u00162\n\u0010\u001a\u001a\u00060\u001bj\u0002`\u001c2\b\b\u0002\u0010\u0012\u001a\u00020\u0003H\u0007\u001a\"\u0010\u0013\u001a\u00020\u001d*\u00020\u000f2\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u001a\"\u0010\u0013\u001a\u00020\u001d*\u00020\u000f2\n\u0010\u001a\u001a\u00060\u001bj\u0002`\u001c2\b\b\u0002\u0010\u0012\u001a\u00020\u0003H\u0007\u001a\"\u0010\u001e\u001a\u00020\u001d*\u00020\u000f2\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\u0006\u0010\u000e\u001a\u00020\u0003H\u0007\u001a \u0010\u001f\u001a\u00020\u001d*\u00020\u000f2\u0006\u0010 \u001a\u00020\u00032\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019\u001a\"\u0010\u001f\u001a\u00020\u001d*\u00020\u000f2\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\u0006\u0010!\u001a\u00020\u0003H\u0007\u001a \u0010\"\u001a\u00020\u001d*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00032\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019\u001a \u0010#\u001a\u0004\u0018\u00010\u001d*\u00020\r2\b\b\u0002\u0010$\u001a\u00020\u00032\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a \u0010#\u001a\u0004\u0018\u00010\u001d*\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020\u00032\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a\u001e\u0010%\u001a\u00020\t*\u00020\u000f2\n\u0010\u0014\u001a\u00060\u0015j\u0002`\u00162\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u001c\u0010&\u001a\u00020\u001d*\u00020\u000f2\u0006\u0010'\u001a\u00020\u001d2\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a(\u0010(\u001a\u00020\u0003*\u00020\u000f2\n\u0010\u0014\u001a\u00060\u0015j\u0002`\u00162\u0006\u0010'\u001a\u00020\u001d2\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a&\u0010(\u001a\u00020\u0003*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020)2\u0006\u0010'\u001a\u00020\u001d2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a$\u0010(\u001a\u00020\u0003*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020*2\u0006\u0010'\u001a\u00020\u001d2\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a0\u0010+\u001a\u00020\u0003*\u00020\u000f2\n\u0010\u0014\u001a\u00060\u0015j\u0002`\u00162\u0006\u0010'\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u0003H\u0002\u001a,\u0010+\u001a\u00020\u0003*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020*2\u0006\u0010'\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u0003H\u0002\u001a$\u0010-\u001a\u00020\u0003*\u00020\u000f2\u0006\u0010'\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020*H\u0002\u001a\u001b\u0010.\u001a\u00020\f*\u00020\u001d2\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019H\u0086\b\u001a4\u0010/\u001a\u000200*\u00020*2\u0006\u00101\u001a\u0002022\b\b\u0002\u00103\u001a\u00020\u00032\b\b\u0002\u00104\u001a\u00020\u00032\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019\u001a4\u0010/\u001a\u000200*\u00020*2\u0006\u00101\u001a\u0002022\b\b\u0002\u00103\u001a\u00020\u00032\b\b\u0002\u00104\u001a\u00020\u00032\n\u00105\u001a\u000606j\u0002`7H\u0007¨\u00068"}, d2 = {"bufferLimitExceeded", "", MetricsUtil.LegacyMetricTypes.LIMIT, "", "prematureEndOfStream", "size", "prematureEndOfStreamToReadChars", "charactersCount", "isAsciiChar", "", "", "readBytes", "", "Lkotlinx/io/core/ByteReadPacket;", JsonReportFormat.COUNT, "Lkotlinx/io/core/Input;", "readBytesOf", ReactProperties.GEOFENCE_MINIMUM_RANGE, ReactProperties.GEOFENCE_MAXIMUM_RANGE, "readText", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", "decoder", "Ljava/nio/charset/CharsetDecoder;", "Lkotlinx/io/charsets/CharsetDecoder;", "", "readTextExact", "readTextExactBytes", "bytesCount", "bytes", "readTextExactCharacters", "readUTF8Line", "estimate", "readUTF8LineTo", "readUTF8UntilDelimiter", "delimiters", "readUTF8UntilDelimiterTo", "Lkotlinx/io/core/BytePacketBuilderBase;", "Lkotlinx/io/core/Output;", "readUTF8UntilDelimiterToSlowUtf8", "decoded0", "readUTFUntilDelimiterToSlowAscii", "toByteArray", "writeText", "", "text", "", "fromIndex", "toIndex", "encoder", "Ljava/nio/charset/CharsetEncoder;", "Lkotlinx/io/charsets/CharsetEncoder;", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class StringsKt {
    private static final Void bufferLimitExceeded(int i) {
        throw new BufferLimitExceededException(GeneratedOutlineSupport1.outline52("Too many characters before delimiter: limit ", i, " exceeded"));
    }

    private static final boolean isAsciiChar(char c) {
        return c <= 127;
    }

    private static final Void prematureEndOfStream(int i) {
        throw new MalformedUTF8InputException(GeneratedOutlineSupport1.outline52("Premature end of stream: expected ", i, " bytes"));
    }

    private static final Void prematureEndOfStreamToReadChars(int i) {
        throw new EOFException(GeneratedOutlineSupport1.outline52("Not enough input bytes to read ", i, " characters."));
    }

    @NotNull
    public static final byte[] readBytes(@NotNull ByteReadPacket receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        byte[] bArr = new byte[i];
        receiver$0.readFully(bArr, 0, i);
        return bArr;
    }

    @NotNull
    public static /* synthetic */ byte[] readBytes$default(ByteReadPacket byteReadPacket, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            long m12336getRemaining = byteReadPacket.m12336getRemaining();
            if (m12336getRemaining > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Unable to convert to a ByteArray: packet is too big");
            }
            i = (int) m12336getRemaining;
        }
        return readBytes(byteReadPacket, i);
    }

    @NotNull
    public static final byte[] readBytesOf(@NotNull Input receiver$0, int i, int i2) {
        long coerceAtMost;
        long coerceAtLeast;
        int readAvailable;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        int i3 = 0;
        if (i == i2) {
            byte[] bArr = new byte[i];
            receiver$0.readFully(bArr, 0, i);
            return bArr;
        }
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(i2, EncodingKt.sizeEstimate(receiver$0));
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(coerceAtMost, i);
        byte[] bArr2 = new byte[(int) coerceAtLeast];
        while (i3 < i2 && (readAvailable = receiver$0.readAvailable(bArr2, i3, Math.min(i2, bArr2.length) - i3)) > 0) {
            i3 += readAvailable;
            if (bArr2.length == i3) {
                bArr2 = Arrays.copyOf(bArr2, i3 * 2);
                Intrinsics.checkExpressionValueIsNotNull(bArr2, "java.util.Arrays.copyOf(this, newSize)");
            }
        }
        if (i3 >= i) {
            if (i3 == bArr2.length) {
                return bArr2;
            }
            byte[] copyOf = Arrays.copyOf(bArr2, i3);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
            return copyOf;
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Not enough bytes available to read ", i, " bytes: ");
        outline109.append(i - i3);
        outline109.append(" more required");
        throw new EOFException(outline109.toString());
    }

    @NotNull
    public static /* synthetic */ byte[] readBytesOf$default(Input input, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return readBytesOf(input, i, i2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use CharsetDecoder.decode instead", replaceWith = @ReplaceWith(expression = "decoder.decode(this, out, max)", imports = {"kotlinx.io.charsets.decode"}))
    public static final int readText(@NotNull Input receiver$0, @NotNull Appendable out, @NotNull CharsetDecoder decoder, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(out, "out");
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        return CharsetJVMKt.decode(decoder, receiver$0, out, i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use CharsetDecoder.decode instead", replaceWith = @ReplaceWith(expression = "decoder.decode(this, out, max)", imports = {"kotlinx.io.charsets.decode"}))
    public static /* synthetic */ int readText$default(Input input, Appendable appendable, CharsetDecoder charsetDecoder, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(input, appendable, charsetDecoder, i);
    }

    @Deprecated(message = "Use readTextExactCharacters instead.", replaceWith = @ReplaceWith(expression = "readTextExactCharacters(n, charset)", imports = {}))
    @NotNull
    public static final String readTextExact(@NotNull Input receiver$0, @NotNull Charset charset, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return readTextExactCharacters(receiver$0, i, charset);
    }

    @Deprecated(message = "Use readTextExactCharacters instead.", replaceWith = @ReplaceWith(expression = "readTextExactCharacters(n, charset)", imports = {}))
    @NotNull
    public static /* synthetic */ String readTextExact$default(Input input, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readTextExact(input, charset, i);
    }

    @Deprecated(message = "Parameters order is changed.", replaceWith = @ReplaceWith(expression = "readTextExactBytes(bytes, charset)", imports = {}))
    @NotNull
    public static final String readTextExactBytes(@NotNull Input receiver$0, @NotNull Charset charset, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return readTextExactBytes(receiver$0, i, charset);
    }

    @Deprecated(message = "Parameters order is changed.", replaceWith = @ReplaceWith(expression = "readTextExactBytes(bytes, charset)", imports = {}))
    @NotNull
    public static /* synthetic */ String readTextExactBytes$default(Input input, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readTextExactBytes(input, charset, i);
    }

    @NotNull
    public static final String readTextExactCharacters(@NotNull Input receiver$0, int i, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        String readText = readText(receiver$0, charset, i);
        if (readText.length() >= i) {
            return readText;
        }
        prematureEndOfStreamToReadChars(i);
        throw null;
    }

    @NotNull
    public static /* synthetic */ String readTextExactCharacters$default(Input input, int i, Charset charset, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        return readTextExactCharacters(input, i, charset);
    }

    @Nullable
    public static final String readUTF8Line(@NotNull ByteReadPacket receiver$0, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (receiver$0.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder(i);
        if (!readUTF8LineTo(receiver$0, sb, i2)) {
            return null;
        }
        return sb.toString();
    }

    @Nullable
    public static /* synthetic */ String readUTF8Line$default(ByteReadPacket byteReadPacket, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 16;
        }
        if ((i3 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return readUTF8Line(byteReadPacket, i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:296:0x0172, code lost:
        if (r0 == false) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:297:0x0174, code lost:
        kotlinx.io.core.internal.UnsafeKt.completeReadHead(r17, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x0177, code lost:
        r3 = 1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:250:0x00f4 A[Catch: all -> 0x0142, TryCatch #1 {all -> 0x017d, blocks: (B:175:0x001d, B:278:0x0138, B:178:0x002a, B:180:0x0030, B:183:0x0040, B:198:0x0061, B:274:0x012c, B:277:0x0133, B:189:0x004b, B:190:0x0052, B:191:0x0055, B:200:0x0068, B:201:0x006b, B:206:0x0075, B:208:0x0079, B:209:0x0082, B:211:0x008a, B:213:0x0095, B:215:0x009e, B:217:0x00a4, B:232:0x00c5, B:223:0x00af, B:224:0x00b6, B:225:0x00b9, B:233:0x00c9, B:235:0x00cf, B:250:0x00f4, B:256:0x0103, B:257:0x010a, B:258:0x010d, B:267:0x011c, B:241:0x00de, B:242:0x00e5, B:243:0x00e8, B:268:0x0121, B:269:0x0124), top: B:316:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0119 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:330:0x00c5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0061 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:332:0x011c A[EDGE_INSN: B:332:0x011c->B:267:0x011c ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0126 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean readUTF8LineTo(@org.jetbrains.annotations.NotNull kotlinx.io.core.Input r17, @org.jetbrains.annotations.NotNull java.lang.Appendable r18, int r19) {
        /*
            Method dump skipped, instructions count: 414
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.StringsKt.readUTF8LineTo(kotlinx.io.core.Input, java.lang.Appendable, int):boolean");
    }

    @NotNull
    public static final String readUTF8UntilDelimiter(@NotNull Input receiver$0, @NotNull String delimiters, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(delimiters, "delimiters");
        StringBuilder sb = new StringBuilder();
        readUTF8UntilDelimiterTo(receiver$0, sb, delimiters, i);
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public static /* synthetic */ String readUTF8UntilDelimiter$default(Input input, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readUTF8UntilDelimiter(input, str, i);
    }

    public static final int readUTF8UntilDelimiterTo(@NotNull Input receiver$0, @NotNull Output out, @NotNull String delimiters, int i) {
        long readUntilDelimiters;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(out, "out");
        Intrinsics.checkParameterIsNotNull(delimiters, "delimiters");
        int length = delimiters.length();
        if (length == 1) {
            if (delimiters.charAt(0) <= 127) {
                readUntilDelimiters = ScannerKt.readUntilDelimiter(receiver$0, (byte) delimiters.charAt(0), out);
                return (int) readUntilDelimiters;
            }
        }
        if (length == 2) {
            if (delimiters.charAt(0) <= 127) {
                if (delimiters.charAt(1) <= 127) {
                    readUntilDelimiters = ScannerKt.readUntilDelimiters(receiver$0, (byte) delimiters.charAt(0), (byte) delimiters.charAt(1), out);
                    return (int) readUntilDelimiters;
                }
            }
        }
        return readUTFUntilDelimiterToSlowAscii(receiver$0, delimiters, i, out);
    }

    public static /* synthetic */ int readUTF8UntilDelimiterTo$default(Input input, Appendable appendable, String str, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readUTF8UntilDelimiterTo(input, appendable, str, i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:166:0x004b, code lost:
        r11 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x00df, code lost:
        r4.pushBack(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x0141, code lost:
        if (r9 == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x0143, code lost:
        kotlinx.io.core.internal.UnsafeKt.completeReadHead(r17, r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int readUTF8UntilDelimiterToSlowUtf8(@org.jetbrains.annotations.NotNull kotlinx.io.core.Input r17, kotlinx.io.core.Output r18, java.lang.String r19, int r20, int r21) {
        /*
            Method dump skipped, instructions count: 349
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.StringsKt.readUTF8UntilDelimiterToSlowUtf8(kotlinx.io.core.Input, kotlinx.io.core.Output, java.lang.String, int, int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:74:0x003f, code lost:
        bufferLimitExceeded(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0042, code lost:
        throw null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int readUTFUntilDelimiterToSlowAscii(@org.jetbrains.annotations.NotNull kotlinx.io.core.Input r11, java.lang.String r12, int r13, kotlinx.io.core.Output r14) {
        /*
            r0 = 1
            kotlinx.io.core.IoBuffer r1 = kotlinx.io.core.internal.UnsafeKt.prepareReadFirstHead(r11, r0)
            r2 = 0
            if (r1 == 0) goto L75
            r3 = r2
            r4 = r3
        La:
            int r5 = r1.getReadRemaining()     // Catch: java.lang.Throwable -> L6e
            int r6 = r1.getReadRemaining()     // Catch: java.lang.Throwable -> L6e
            r7 = r4
            r4 = r3
            r3 = r2
        L15:
            if (r3 >= r6) goto L48
            byte r8 = r1.readByte()     // Catch: java.lang.Throwable -> L6e
            r8 = r8 & 255(0xff, float:3.57E-43)
            r9 = r8 & 128(0x80, float:1.794E-43)
            if (r9 != 0) goto L43
            char r8 = (char) r8     // Catch: java.lang.Throwable -> L6e
            r9 = 2
            r10 = 0
            boolean r8 = kotlin.text.StringsKt.contains$default(r12, r8, r2, r9, r10)     // Catch: java.lang.Throwable -> L6e
            if (r8 == 0) goto L2e
            r8 = r0
            r7 = r4
            r4 = r2
            goto L35
        L2e:
            if (r4 == r13) goto L3f
            int r4 = r4 + 1
            r8 = r7
            r7 = r4
            r4 = r0
        L35:
            if (r4 != 0) goto L3a
            r4 = r7
            r7 = r8
            goto L43
        L3a:
            int r3 = r3 + 1
            r4 = r7
            r7 = r8
            goto L15
        L3f:
            bufferLimitExceeded(r13)     // Catch: java.lang.Throwable -> L6e
            throw r10     // Catch: java.lang.Throwable -> L6e
        L43:
            r1.pushBack(r0)     // Catch: java.lang.Throwable -> L6e
            r3 = r2
            goto L49
        L48:
            r3 = r0
        L49:
            int r6 = r1.getReadRemaining()     // Catch: java.lang.Throwable -> L6e
            int r5 = r5 - r6
            if (r5 <= 0) goto L56
            r1.pushBack(r5)     // Catch: java.lang.Throwable -> L6e
            r14.writeFully(r1, r5)     // Catch: java.lang.Throwable -> L6e
        L56:
            if (r3 != 0) goto L59
            goto L64
        L59:
            kotlinx.io.core.IoBuffer r3 = kotlinx.io.core.internal.UnsafeKt.prepareReadNextHead(r11, r1)     // Catch: java.lang.Throwable -> L6b
            if (r3 == 0) goto L63
            r1 = r3
            r3 = r4
            r4 = r7
            goto La
        L63:
            r0 = r2
        L64:
            if (r0 == 0) goto L69
            kotlinx.io.core.internal.UnsafeKt.completeReadHead(r11, r1)
        L69:
            r2 = r7
            goto L76
        L6b:
            r12 = move-exception
            r0 = r2
            goto L6f
        L6e:
            r12 = move-exception
        L6f:
            if (r0 == 0) goto L74
            kotlinx.io.core.internal.UnsafeKt.completeReadHead(r11, r1)
        L74:
            throw r12
        L75:
            r4 = r2
        L76:
            if (r2 != 0) goto L82
            boolean r0 = r11.getEndOfInput()
            if (r0 != 0) goto L82
            int r4 = readUTF8UntilDelimiterToSlowUtf8(r11, r14, r12, r13, r4)
        L82:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.StringsKt.readUTFUntilDelimiterToSlowAscii(kotlinx.io.core.Input, java.lang.String, int, kotlinx.io.core.Output):int");
    }

    @NotNull
    public static final byte[] toByteArray(@NotNull String receiver$0, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkExpressionValueIsNotNull(newEncoder, "charset.newEncoder()");
        return CharsetJVMKt.encodeToByteArray(newEncoder, receiver$0, 0, receiver$0.length());
    }

    @NotNull
    public static /* synthetic */ byte[] toByteArray$default(String receiver$0, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkExpressionValueIsNotNull(newEncoder, "charset.newEncoder()");
        return CharsetJVMKt.encodeToByteArray(newEncoder, receiver$0, 0, receiver$0.length());
    }

    @Deprecated(message = "Use the implementation with Charset instead", replaceWith = @ReplaceWith(expression = "writeText(text, fromIndex, toIndex, encoder.charset)", imports = {"kotlinx.io.charsets.charset"}))
    public static final void writeText(@NotNull Output receiver$0, @NotNull CharSequence text, int i, int i2, @NotNull CharsetEncoder encoder) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        EncodingKt.encodeToImpl(encoder, receiver$0, text, i, i2);
    }

    @Deprecated(message = "Use the implementation with Charset instead", replaceWith = @ReplaceWith(expression = "writeText(text, fromIndex, toIndex, encoder.charset)", imports = {"kotlinx.io.charsets.charset"}))
    public static /* synthetic */ void writeText$default(Output output, CharSequence charSequence, int i, int i2, CharsetEncoder charsetEncoder, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        writeText(output, charSequence, i, i2, charsetEncoder);
    }

    @NotNull
    public static final byte[] readBytes(@NotNull Input receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return readBytesOf(receiver$0, i, i);
    }

    public static final int readText(@NotNull Input receiver$0, @NotNull Appendable out, @NotNull Charset charset, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(out, "out");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        CharsetDecoder newDecoder = charset.newDecoder();
        Intrinsics.checkExpressionValueIsNotNull(newDecoder, "charset.newDecoder()");
        return CharsetJVMKt.decode(newDecoder, receiver$0, out, i);
    }

    public static /* synthetic */ int readText$default(Input input, Appendable appendable, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 4) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(input, appendable, charset, i);
    }

    @NotNull
    public static final String readTextExactBytes(@NotNull Input receiver$0, int i, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        CharsetDecoder newDecoder = charset.newDecoder();
        Intrinsics.checkExpressionValueIsNotNull(newDecoder, "charset.newDecoder()");
        return CharsetJVMKt.decodeExactBytes(newDecoder, receiver$0, i);
    }

    @NotNull
    public static /* synthetic */ String readTextExactBytes$default(Input input, int i, Charset charset, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        return readTextExactBytes(input, i, charset);
    }

    @Nullable
    public static /* synthetic */ String readUTF8Line$default(Input input, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 16;
        }
        if ((i3 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return readUTF8Line(input, i, i2);
    }

    public static /* synthetic */ int readUTF8UntilDelimiterTo$default(Input input, Output output, String str, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readUTF8UntilDelimiterTo(input, output, str, i);
    }

    public static final void writeText(@NotNull Output receiver$0, @NotNull CharSequence text, int i, int i2, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkExpressionValueIsNotNull(newEncoder, "charset.newEncoder()");
        EncodingKt.encodeToImpl(newEncoder, receiver$0, text, i, i2);
    }

    public static /* synthetic */ void writeText$default(Output output, CharSequence charSequence, int i, int i2, Charset charset, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        if ((i3 & 8) != 0) {
            charset = Charsets.UTF_8;
        }
        writeText(output, charSequence, i, i2, charset);
    }

    @NotNull
    public static final byte[] readBytes(@NotNull Input receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return readBytesOf$default(receiver$0, 0, 0, 3, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use CharsetDecoder.decode instead", replaceWith = @ReplaceWith(expression = "decoder.decode(this, max)", imports = {"kotlinx.io.charsets.decode"}))
    @NotNull
    public static final String readText(@NotNull Input receiver$0, @NotNull CharsetDecoder decoder, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        return EncodingKt.decode(decoder, receiver$0, i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use CharsetDecoder.decode instead", replaceWith = @ReplaceWith(expression = "decoder.decode(this, max)", imports = {"kotlinx.io.charsets.decode"}))
    @NotNull
    public static /* synthetic */ String readText$default(Input input, CharsetDecoder charsetDecoder, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(input, charsetDecoder, i);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use Output version instead")
    public static /* synthetic */ int readUTF8UntilDelimiterTo$default(Input input, BytePacketBuilderBase bytePacketBuilderBase, String str, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readUTF8UntilDelimiterTo(input, bytePacketBuilderBase, str, i);
    }

    @NotNull
    public static final String readText(@NotNull Input receiver$0, @NotNull Charset charset, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        CharsetDecoder newDecoder = charset.newDecoder();
        Intrinsics.checkExpressionValueIsNotNull(newDecoder, "charset.newDecoder()");
        return EncodingKt.decode(newDecoder, receiver$0, i);
    }

    @NotNull
    public static /* synthetic */ String readText$default(Input input, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(input, charset, i);
    }

    @Nullable
    public static final String readUTF8Line(@NotNull Input receiver$0, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        StringBuilder sb = new StringBuilder(i);
        if (readUTF8LineTo(receiver$0, sb, i2)) {
            return sb.toString();
        }
        return null;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use Output version instead")
    public static final /* synthetic */ int readUTF8UntilDelimiterTo(@NotNull Input receiver$0, @NotNull BytePacketBuilderBase out, @NotNull String delimiters, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(out, "out");
        Intrinsics.checkParameterIsNotNull(delimiters, "delimiters");
        return readUTF8UntilDelimiterTo(receiver$0, (Output) out, delimiters, i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x004d, code lost:
        bufferLimitExceeded(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0050, code lost:
        throw null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int readUTF8UntilDelimiterTo(@org.jetbrains.annotations.NotNull kotlinx.io.core.Input r10, @org.jetbrains.annotations.NotNull java.lang.Appendable r11, @org.jetbrains.annotations.NotNull java.lang.String r12, int r13) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
            java.lang.String r0 = "out"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            java.lang.String r0 = "delimiters"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            r0 = 1
            kotlinx.io.core.IoBuffer r1 = kotlinx.io.core.internal.UnsafeKt.prepareReadFirstHead(r10, r0)
            r2 = 0
            if (r1 == 0) goto L76
            r3 = r2
            r4 = r3
        L19:
            int r5 = r1.getReadRemaining()     // Catch: java.lang.Throwable -> L6f
            r6 = r4
            r4 = r3
            r3 = r2
        L20:
            if (r3 >= r5) goto L56
            byte r7 = r1.readByte()     // Catch: java.lang.Throwable -> L6f
            r7 = r7 & 255(0xff, float:3.57E-43)
            r8 = r7 & 128(0x80, float:1.794E-43)
            if (r8 != 0) goto L51
            char r7 = (char) r7     // Catch: java.lang.Throwable -> L6f
            r8 = 2
            r9 = 0
            boolean r8 = kotlin.text.StringsKt.contains$default(r12, r7, r2, r8, r9)     // Catch: java.lang.Throwable -> L6f
            if (r8 == 0) goto L39
            r7 = r0
            r6 = r4
            r4 = r2
            goto L43
        L39:
            if (r4 == r13) goto L4d
            int r4 = r4 + 1
            r11.append(r7)     // Catch: java.lang.Throwable -> L6f
            r7 = r6
            r6 = r4
            r4 = r0
        L43:
            if (r4 != 0) goto L48
            r4 = r6
            r6 = r7
            goto L51
        L48:
            int r3 = r3 + 1
            r4 = r6
            r6 = r7
            goto L20
        L4d:
            bufferLimitExceeded(r13)     // Catch: java.lang.Throwable -> L6f
            throw r9     // Catch: java.lang.Throwable -> L6f
        L51:
            r1.pushBack(r0)     // Catch: java.lang.Throwable -> L6f
            r3 = r2
            goto L57
        L56:
            r3 = r0
        L57:
            if (r3 != 0) goto L5a
            goto L65
        L5a:
            kotlinx.io.core.IoBuffer r3 = kotlinx.io.core.internal.UnsafeKt.prepareReadNextHead(r10, r1)     // Catch: java.lang.Throwable -> L6c
            if (r3 == 0) goto L64
            r1 = r3
            r3 = r4
            r4 = r6
            goto L19
        L64:
            r0 = r2
        L65:
            if (r0 == 0) goto L6a
            kotlinx.io.core.internal.UnsafeKt.completeReadHead(r10, r1)
        L6a:
            r2 = r6
            goto L77
        L6c:
            r11 = move-exception
            r0 = r2
            goto L70
        L6f:
            r11 = move-exception
        L70:
            if (r0 == 0) goto L75
            kotlinx.io.core.internal.UnsafeKt.completeReadHead(r10, r1)
        L75:
            throw r11
        L76:
            r4 = r2
        L77:
            if (r2 != 0) goto L7d
            int r4 = readUTF8UntilDelimiterToSlowUtf8(r10, r11, r12, r13, r4)
        L7d:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.StringsKt.readUTF8UntilDelimiterTo(kotlinx.io.core.Input, java.lang.Appendable, java.lang.String, int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:162:0x0043, code lost:
        r6 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x00e0, code lost:
        r1.pushBack(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:239:0x012a, code lost:
        if (r5 == false) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x012c, code lost:
        kotlinx.io.core.internal.UnsafeKt.completeReadHead(r11, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:241:0x012f, code lost:
        r15 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int readUTF8UntilDelimiterToSlowUtf8(@org.jetbrains.annotations.NotNull kotlinx.io.core.Input r11, java.lang.Appendable r12, java.lang.String r13, int r14, int r15) {
        /*
            Method dump skipped, instructions count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.StringsKt.readUTF8UntilDelimiterToSlowUtf8(kotlinx.io.core.Input, java.lang.Appendable, java.lang.String, int, int):int");
    }
}
