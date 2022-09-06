package kotlinx.io.core.internal;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imagepipeline.producers.DecodeProducer;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.IoBuffer;
import org.jetbrains.annotations.NotNull;
/* compiled from: UTF8.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0002\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0001\u001aK\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u000bj\u0002`\f2\u0006\u0010\r\u001a\u00020\u00012$\u0010\u000e\u001a \b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000fH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a\u0010\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u0001H\u0002\u001a!\u0010!\u001a\u00020\t*\u00020\"2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\t0$H\u0080\b\u001a!\u0010&\u001a\u00020\u0001*\u00020\"2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\t0$H\u0087\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"HighSurrogateMagic", "", "MaxCodePoint", "MinHighSurrogate", "MinLowSurrogate", "MinSupplementary", "byteCountUtf8", "firstByte", "decodeUTF8LineLoopSuspend", "", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", MetricsUtil.LegacyMetricTypes.LIMIT, "nextChunk", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "Lkotlinx/io/core/ByteReadPacketBase;", "", "(Ljava/lang/Appendable;ILkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "highSurrogate", "cp", "isBmpCodePoint", "isValidCodePoint", "codePoint", "lowSurrogate", "malformedByteCount", "", DecodeProducer.EXTRA_BITMAP_BYTES, "malformedCodePoint", "value", "prematureEndOfStreamUtf", "size", "decodeASCII", "Lkotlinx/io/core/IoBuffer;", "consumer", "Lkotlin/Function1;", "", "decodeUTF8", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class UTF8Kt {
    private static final int HighSurrogateMagic = 55232;
    private static final int MaxCodePoint = 1114111;
    private static final int MinHighSurrogate = 55296;
    private static final int MinLowSurrogate = 56320;
    private static final int MinSupplementary = 65536;

    @DangerousInternalIoApi
    public static final int byteCountUtf8(int i) {
        int i2 = 0;
        int i3 = 128;
        for (int i4 = 1; i4 <= 6 && (i & i3) != 0; i4++) {
            i &= ~i3;
            i3 >>= 1;
            i2++;
        }
        return i2;
    }

    public static final boolean decodeASCII(@NotNull IoBuffer receiver$0, @NotNull Function1<? super Character, Boolean> consumer) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(consumer, "consumer");
        int readRemaining = receiver$0.getReadRemaining();
        for (int i = 0; i < readRemaining; i++) {
            int readByte = receiver$0.readByte() & 255;
            if ((readByte & 128) != 0 || !consumer.mo12165invoke(Character.valueOf((char) readByte)).booleanValue()) {
                receiver$0.pushBack(1);
                return false;
            }
        }
        return true;
    }

    @DangerousInternalIoApi
    public static final int decodeUTF8(@NotNull IoBuffer receiver$0, @NotNull Function1<? super Character, Boolean> consumer) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(consumer, "consumer");
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (receiver$0.canRead()) {
            int readByte = receiver$0.readByte() & 255;
            if ((readByte & 128) == 0) {
                if (i == 0) {
                    if (!consumer.mo12165invoke(Character.valueOf((char) readByte)).booleanValue()) {
                        receiver$0.pushBack(1);
                        return -1;
                    }
                } else {
                    malformedByteCount(i);
                    throw null;
                }
            } else if (i == 0) {
                int i4 = 128;
                i3 = i;
                for (int i5 = 1; i5 <= 6 && (readByte & i4) != 0; i5++) {
                    readByte &= ~i4;
                    i4 >>= 1;
                    i3++;
                }
                i = i3 - 1;
                if (i > receiver$0.getReadRemaining()) {
                    receiver$0.pushBack(1);
                    return i3;
                }
                i2 = readByte;
            } else {
                i2 = (i2 << 6) | (readByte & 127);
                i--;
                if (i != 0) {
                    continue;
                } else {
                    if (isBmpCodePoint(i2)) {
                        if (!consumer.mo12165invoke(Character.valueOf((char) i2)).booleanValue()) {
                            receiver$0.pushBack(i3);
                            return -1;
                        }
                    } else if (isValidCodePoint(i2)) {
                        if (!consumer.mo12165invoke(Character.valueOf((char) highSurrogate(i2))).booleanValue() || !consumer.mo12165invoke(Character.valueOf((char) lowSurrogate(i2))).booleanValue()) {
                            receiver$0.pushBack(i3);
                            return -1;
                        }
                    } else {
                        malformedCodePoint(i2);
                        throw null;
                    }
                    i2 = 0;
                }
            }
        }
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:147:0x02b2, code lost:
        r13.pushBack(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x02cf, code lost:
        r8.element = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x02d1, code lost:
        if (r0 <= 0) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x02d3, code lost:
        r13.discardExact(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x02d8, code lost:
        if (r3.element == false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x02da, code lost:
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x02dc, code lost:
        r5 = kotlin.ranges.RangesKt___RangesKt.coerceAtLeast(r8.element, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x02e3, code lost:
        r8.element = r5;
        r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8.element).intValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x02ef, code lost:
        r15 = r13.getReadRemaining();
        r14 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x032b, code lost:
        if (r0 == false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x032d, code lost:
        kotlinx.io.core.internal.UnsafeKt.completeReadHead(r12, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0145, code lost:
        r0 = r16;
        r5 = -1;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x035a  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0142 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02bd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ac A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b1  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00aa -> B:28:0x00ad). Please submit an issue!!! */
    @kotlinx.io.core.internal.DangerousInternalIoApi
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object decodeUTF8LineLoopSuspend(@org.jetbrains.annotations.NotNull java.lang.Appendable r21, int r22, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super kotlin.coroutines.Continuation<? super kotlinx.io.core.ByteReadPacketBase>, ? extends java.lang.Object> r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r24) {
        /*
            Method dump skipped, instructions count: 889
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.internal.UTF8Kt.decodeUTF8LineLoopSuspend(java.lang.Appendable, int, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @PublishedApi
    public static final int highSurrogate(int i) {
        return (i >>> 10) + 55232;
    }

    @PublishedApi
    public static final boolean isBmpCodePoint(int i) {
        return (i >>> 16) == 0;
    }

    @PublishedApi
    public static final boolean isValidCodePoint(int i) {
        return i <= MaxCodePoint;
    }

    @PublishedApi
    public static final int lowSurrogate(int i) {
        return (i & 1023) + 56320;
    }

    @PublishedApi
    @NotNull
    public static final Void malformedByteCount(int i) {
        throw new MalformedUTF8InputException(GeneratedOutlineSupport1.outline52("Expected ", i, " more character bytes"));
    }

    @PublishedApi
    @NotNull
    public static final Void malformedCodePoint(int i) {
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Malformed code-point ", i, " found"));
    }

    private static final Void prematureEndOfStreamUtf(int i) {
        throw new EOFException(GeneratedOutlineSupport1.outline52("Premature end of stream: expected ", i, " bytes to decode UTF-8 char"));
    }
}
