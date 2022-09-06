package kotlinx.io.charsets;

import com.amazon.alexa.presence.bleconn.service.protocols.relationships.RequesterRelationshipsPacketV1;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.io.core.ExperimentalIoApi;
import kotlinx.io.core.internal.DangerousInternalIoApi;
import okio.Utf8;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.jetbrains.annotations.NotNull;
/* compiled from: UTF.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0002\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0007\u001a\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0007H\u0000\u001a\u0018\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\u0007\u001a\u0010\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0001H\u0002\u001a \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!H\u0002\u001a$\u0010\"\u001a\u00020\u0007*\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0007\u001a(\u0010&\u001a\u00020\u0007*\u00020#2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u0014\u001a\u00020\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u0001H\u0007\u001a$\u0010'\u001a\u00020\u0007*\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0002\u001a$\u0010(\u001a\u00020\u0007*\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0002\u001a$\u0010)\u001a\u00020\u0007*\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0002\u001a9\u0010)\u001a\u00020\u0007*\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u00180+H\u0082\b\u001a$\u0010-\u001a\u00020\u0007*\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0002\u001a9\u0010-\u001a\u00020\u0007*\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u00180+H\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"HighSurrogateMagic", "", "MaxCodePoint", "MinHighSurrogate", "MinLowSurrogate", "MinSupplementary", "decodeUtf8Result", "", "numberOfChars", "requireBytes", "decodeUtf8ResultAcc", "predecoded", "result", "decodeUtf8ResultCombine", "prev", "next", "highSurrogate", "cp", "indexOutOfBounds", "", "offset", "length", "arrayLength", "isBmpCodePoint", "", "isValidCodePoint", "codePoint", "lowSurrogate", "malformedCodePoint", "", "value", "unsupportedByteCount", "b", "", "decodeUTF", "Ljava/nio/ByteBuffer;", "out", "", "decodeUTF8Line", "decodeUTF8Line_array", "decodeUTF8Line_buffer", "decodeUTF8_array", "predicate", "Lkotlin/Function1;", "", "decodeUTF8_buffer", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class UTFKt {
    private static final int HighSurrogateMagic = 55232;
    private static final int MaxCodePoint = 1114111;
    private static final int MinHighSurrogate = 55296;
    private static final int MinLowSurrogate = 56320;
    private static final int MinSupplementary = 65536;

    @ExperimentalIoApi
    public static final long decodeUTF(@NotNull ByteBuffer receiver$0, @NotNull char[] out, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(out, "out");
        int decodeASCII = StringsKt.decodeASCII(receiver$0, out, i, i2);
        if (!receiver$0.hasRemaining() || decodeASCII == i2) {
            return decodeUtf8Result(decodeASCII, 0);
        }
        return receiver$0.hasArray() ? decodeUtf8ResultAcc(decodeASCII, decodeUTF8_array(receiver$0, out, i + decodeASCII, i2 - decodeASCII)) : decodeUtf8ResultAcc(decodeASCII, decodeUTF8_buffer(receiver$0, out, i + decodeASCII, i2 - decodeASCII));
    }

    @ExperimentalIoApi
    public static final long decodeUTF8Line(@NotNull ByteBuffer receiver$0, @NotNull char[] out, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(out, "out");
        return receiver$0.hasArray() ? decodeUTF8Line_array(receiver$0, out, i, i2) : decodeUTF8Line_buffer(receiver$0, out, i, i2);
    }

    @ExperimentalIoApi
    public static /* synthetic */ long decodeUTF8Line$default(ByteBuffer byteBuffer, char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return decodeUTF8Line(byteBuffer, cArr, i, i2);
    }

    private static final long decodeUTF8Line_array(@NotNull ByteBuffer byteBuffer, char[] cArr, int i, int i2) {
        long decodeUtf8Result;
        boolean z;
        boolean z2;
        int i3;
        boolean z3;
        char c;
        boolean z4;
        boolean z5;
        boolean z6;
        byte[] array = byteBuffer.array();
        if (array == null) {
            Intrinsics.throwNpe();
        }
        int position = byteBuffer.position() + byteBuffer.arrayOffset();
        int remaining = byteBuffer.remaining() + position;
        if (position <= remaining) {
            if (remaining <= array.length) {
                int i4 = i + i2;
                if (i4 > cArr.length) {
                    throw indexOutOfBounds(i, i2, cArr.length);
                }
                boolean z7 = false;
                int i5 = i;
                while (position < remaining && i5 < i4) {
                    int i6 = position + 1;
                    byte b = array[position];
                    if (b >= 0) {
                        char c2 = (char) b;
                        if (c2 == '\r') {
                            z2 = true;
                            z = true;
                        } else if (c2 == '\n') {
                            z2 = false;
                            z = false;
                        } else if (z7) {
                            z = z7;
                            z2 = false;
                        } else {
                            z = z7;
                            z2 = true;
                        }
                        if (!z2) {
                            byteBuffer.position(i6 - 1);
                            decodeUtf8Result = decodeUtf8Result(i5 - i, -1);
                            z7 = z;
                            break;
                        }
                        i3 = i5 + 1;
                        cArr[i5] = c2;
                        i5 = i3;
                        z7 = z;
                        position = i6;
                    } else if ((b & 224) == 192) {
                        if (i6 >= remaining) {
                            byteBuffer.position(i6 - 1);
                            decodeUtf8Result = decodeUtf8Result(i5 - i, 2);
                            break;
                        }
                        int i7 = i6 + 1;
                        char c3 = (char) (((b & 31) << 6) | (array[i6] & Utf8.REPLACEMENT_BYTE));
                        if (c3 == '\r') {
                            z6 = true;
                            z = true;
                        } else if (c3 == '\n') {
                            z6 = false;
                            z = false;
                        } else if (z7) {
                            z = z7;
                            z6 = false;
                        } else {
                            z = z7;
                            z6 = true;
                        }
                        if (!z6) {
                            byteBuffer.position(i7 - 2);
                            decodeUtf8Result = decodeUtf8Result(i5 - i, -1);
                            z7 = z;
                            break;
                        }
                        cArr[i5] = c3;
                        i5++;
                        z7 = z;
                        position = i7;
                    } else if ((b & RequesterRelationshipsPacketV1.HEADER_MASK_VERSION) != 224) {
                        if ((b & 248) != 240) {
                            unsupportedByteCount(b);
                            throw null;
                        } else if (remaining - i6 < 3) {
                            byteBuffer.position(i6 - 1);
                            decodeUtf8Result = decodeUtf8Result(i5 - i, 4);
                            break;
                        } else {
                            int i8 = i6 + 1;
                            byte b2 = array[i6];
                            int i9 = i8 + 1;
                            int i10 = i9 + 1;
                            int i11 = ((b & 7) << 18) | ((b2 & Utf8.REPLACEMENT_BYTE) << 12) | ((array[i8] & Utf8.REPLACEMENT_BYTE) << 6) | (array[i9] & Utf8.REPLACEMENT_BYTE);
                            if (!isValidCodePoint(i11)) {
                                malformedCodePoint(i11);
                                throw null;
                            } else if (i4 - i5 >= 2) {
                                char highSurrogate = (char) highSurrogate(i11);
                                char lowSurrogate = (char) lowSurrogate(i11);
                                if (highSurrogate == '\r') {
                                    z4 = true;
                                    c = '\n';
                                    z7 = true;
                                } else {
                                    c = '\n';
                                    if (highSurrogate == '\n') {
                                        z4 = false;
                                        z7 = false;
                                    } else {
                                        z4 = !z7;
                                    }
                                }
                                if (z4) {
                                    if (lowSurrogate == '\r') {
                                        z7 = true;
                                        z5 = true;
                                    } else if (lowSurrogate == c) {
                                        z5 = false;
                                        z7 = false;
                                    } else {
                                        z5 = !z7;
                                    }
                                    if (z5) {
                                        int i12 = i5 + 1;
                                        cArr[i5] = highSurrogate;
                                        i5 = i12 + 1;
                                        cArr[i12] = lowSurrogate;
                                        position = i10;
                                    }
                                }
                                byteBuffer.position(i10 - 4);
                                decodeUtf8Result = decodeUtf8Result(i5 - i, -1);
                                break;
                            } else {
                                byteBuffer.position(i10 - 4);
                                decodeUtf8Result = decodeUtf8Result(i5 - i, 0);
                                break;
                            }
                        }
                    } else if (remaining - i6 < 2) {
                        byteBuffer.position(i6 - 1);
                        decodeUtf8Result = decodeUtf8Result(i5 - i, 3);
                        break;
                    } else {
                        int i13 = i6 + 1;
                        byte b3 = array[i6];
                        i6 = i13 + 1;
                        int i14 = b & 15;
                        int i15 = (array[i13] & Utf8.REPLACEMENT_BYTE) | ((b3 & Utf8.REPLACEMENT_BYTE) << 6) | (i14 << 12);
                        if (i14 != 0 && !isBmpCodePoint(i15)) {
                            malformedCodePoint(i15);
                            throw null;
                        }
                        char c4 = (char) i15;
                        if (c4 == '\r') {
                            z3 = true;
                            z = true;
                        } else if (c4 == '\n') {
                            z3 = false;
                            z = false;
                        } else if (z7) {
                            z = z7;
                            z3 = false;
                        } else {
                            z = z7;
                            z3 = true;
                        }
                        if (!z3) {
                            byteBuffer.position(i6 - 4);
                            decodeUtf8Result = decodeUtf8Result(i5 - i, -1);
                            z7 = z;
                            break;
                        }
                        i3 = i5 + 1;
                        cArr[i5] = c4;
                        i5 = i3;
                        z7 = z;
                        position = i6;
                    }
                }
                byteBuffer.position(position);
                decodeUtf8Result = decodeUtf8Result(i5 - i, 0);
                int i16 = (int) (BodyPartID.bodyIdMax & decodeUtf8Result);
                if (i16 == -1) {
                    int i17 = (int) (decodeUtf8Result >> 32);
                    if (z7) {
                        return decodeUtf8Result(i17 - 1, -1);
                    }
                    byteBuffer.position(byteBuffer.position() + 1);
                    if (i17 > 0) {
                        int i18 = i17 - 1;
                        if (cArr[i18] == '\r') {
                            return decodeUtf8Result(i18, -1);
                        }
                    }
                } else if (i16 == 0 && z7) {
                    byteBuffer.position(byteBuffer.position() - 1);
                    return decodeUtf8Result(((int) (decodeUtf8Result >> 32)) - 1, 2);
                }
                return decodeUtf8Result;
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x0086 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0037 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x00eb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x017a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x017a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x016f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final long decodeUTF8Line_buffer(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r18, char[] r19, int r20, int r21) {
        /*
            Method dump skipped, instructions count: 490
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.charsets.UTFKt.decodeUTF8Line_buffer(java.nio.ByteBuffer, char[], int, int):long");
    }

    private static final long decodeUTF8_array(@NotNull ByteBuffer byteBuffer, char[] cArr, int i, int i2) {
        byte[] array = byteBuffer.array();
        if (array == null) {
            Intrinsics.throwNpe();
        }
        int position = byteBuffer.position() + byteBuffer.arrayOffset();
        int remaining = byteBuffer.remaining() + position;
        if (position <= remaining) {
            if (remaining <= array.length) {
                int i3 = i + i2;
                if (i3 <= cArr.length) {
                    int i4 = i;
                    while (position < remaining && i4 < i3) {
                        int i5 = position + 1;
                        byte b = array[position];
                        if (b >= 0) {
                            cArr[i4] = (char) b;
                            position = i5;
                            i4++;
                        } else if ((b & 224) == 192) {
                            if (i5 >= remaining) {
                                byteBuffer.position(i5 - 1);
                                return decodeUtf8Result(i4 - i, 2);
                            }
                            cArr[i4] = (char) (((b & 31) << 6) | (array[i5] & Utf8.REPLACEMENT_BYTE));
                            position = i5 + 1;
                            i4++;
                        } else if ((b & RequesterRelationshipsPacketV1.HEADER_MASK_VERSION) == 224) {
                            if (remaining - i5 < 2) {
                                byteBuffer.position(i5 - 1);
                                return decodeUtf8Result(i4 - i, 3);
                            }
                            int i6 = i5 + 1;
                            int i7 = i6 + 1;
                            int i8 = b & 15;
                            int i9 = ((array[i5] & Utf8.REPLACEMENT_BYTE) << 6) | (i8 << 12) | (array[i6] & Utf8.REPLACEMENT_BYTE);
                            if (i8 != 0 && !isBmpCodePoint(i9)) {
                                malformedCodePoint(i9);
                                throw null;
                            }
                            cArr[i4] = (char) i9;
                            i4++;
                            position = i7;
                        } else if ((b & 248) != 240) {
                            unsupportedByteCount(b);
                            throw null;
                        } else if (remaining - i5 < 3) {
                            byteBuffer.position(i5 - 1);
                            return decodeUtf8Result(i4 - i, 4);
                        } else {
                            int i10 = i5 + 1;
                            int i11 = i10 + 1;
                            int i12 = i11 + 1;
                            int i13 = ((b & 7) << 18) | ((array[i5] & Utf8.REPLACEMENT_BYTE) << 12) | ((array[i10] & Utf8.REPLACEMENT_BYTE) << 6) | (array[i11] & Utf8.REPLACEMENT_BYTE);
                            if (!isValidCodePoint(i13)) {
                                malformedCodePoint(i13);
                                throw null;
                            } else if (i3 - i4 >= 2) {
                                int highSurrogate = highSurrogate(i13);
                                int lowSurrogate = lowSurrogate(i13);
                                int i14 = i4 + 1;
                                cArr[i4] = (char) highSurrogate;
                                i4 = i14 + 1;
                                cArr[i14] = (char) lowSurrogate;
                                position = i12;
                            } else {
                                byteBuffer.position(i12 - 4);
                                return decodeUtf8Result(i4 - i, 0);
                            }
                        }
                    }
                    byteBuffer.position(position);
                    return decodeUtf8Result(i4 - i, 0);
                }
                throw indexOutOfBounds(i, i2, cArr.length);
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private static final long decodeUTF8_buffer(@NotNull ByteBuffer byteBuffer, char[] cArr, int i, int i2) {
        int i3 = i + i2;
        if (i3 <= cArr.length) {
            int i4 = i;
            while (byteBuffer.hasRemaining() && i4 < i3) {
                byte b = byteBuffer.get();
                if (b >= 0) {
                    cArr[i4] = (char) b;
                    i4++;
                } else if ((b & 224) == 192) {
                    if (byteBuffer.hasRemaining()) {
                        byteBuffer.position(byteBuffer.position() - 1);
                        return decodeUtf8Result(i4 - i, 2);
                    }
                    cArr[i4] = (char) (((b & 31) << 6) | (byteBuffer.get() & Utf8.REPLACEMENT_BYTE));
                    i4++;
                } else if ((b & RequesterRelationshipsPacketV1.HEADER_MASK_VERSION) == 224) {
                    if (byteBuffer.remaining() < 2) {
                        byteBuffer.position(byteBuffer.position() - 1);
                        return decodeUtf8Result(i4 - i, 3);
                    }
                    byte b2 = byteBuffer.get();
                    byte b3 = byteBuffer.get();
                    int i5 = b & 15;
                    int i6 = ((b2 & Utf8.REPLACEMENT_BYTE) << 6) | (i5 << 12) | (b3 & Utf8.REPLACEMENT_BYTE);
                    if (i5 != 0 && !isBmpCodePoint(i6)) {
                        malformedCodePoint(i6);
                        throw null;
                    }
                    cArr[i4] = (char) i6;
                    i4++;
                } else if ((b & 248) == 240) {
                    if (byteBuffer.remaining() < 3) {
                        byteBuffer.position(byteBuffer.position() - 1);
                        return decodeUtf8Result(i4 - i, 4);
                    }
                    int i7 = ((b & 7) << 18) | ((byteBuffer.get() & Utf8.REPLACEMENT_BYTE) << 12) | ((byteBuffer.get() & Utf8.REPLACEMENT_BYTE) << 6) | (byteBuffer.get() & Utf8.REPLACEMENT_BYTE);
                    if (!isValidCodePoint(i7)) {
                        malformedCodePoint(i7);
                        throw null;
                    } else if (i3 - i4 >= 2) {
                        int highSurrogate = highSurrogate(i7);
                        int lowSurrogate = lowSurrogate(i7);
                        int i8 = i4 + 1;
                        cArr[i4] = (char) highSurrogate;
                        i4 = i8 + 1;
                        cArr[i8] = (char) lowSurrogate;
                    } else {
                        byteBuffer.position(byteBuffer.position() - 4);
                        return decodeUtf8Result(i4 - i, 0);
                    }
                } else {
                    unsupportedByteCount(b);
                    throw null;
                }
            }
            return decodeUtf8Result(i4 - i, 0);
        }
        throw indexOutOfBounds(i, i2, cArr.length);
    }

    @DangerousInternalIoApi
    public static final long decodeUtf8Result(int i, int i2) {
        return (i2 & BodyPartID.bodyIdMax) | (i << 32);
    }

    public static final long decodeUtf8ResultAcc(int i, long j) {
        return decodeUtf8Result(i + ((int) (j >> 32)), (int) (j & BodyPartID.bodyIdMax));
    }

    @DangerousInternalIoApi
    public static final long decodeUtf8ResultCombine(long j, long j2) {
        return ((j & (-4294967296L)) + ((-4294967296L) & j2)) | (j2 & BodyPartID.bodyIdMax);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int highSurrogate(int i) {
        return (i >>> 10) + 55232;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Throwable indexOutOfBounds(int i, int i2, int i3) {
        return new IndexOutOfBoundsException(i + " (offset) + " + i2 + " (length) > " + i3 + " (array.length)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isBmpCodePoint(int i) {
        return (i >>> 16) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isValidCodePoint(int i) {
        return i <= MaxCodePoint;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int lowSurrogate(int i) {
        return (i & 1023) + 56320;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void malformedCodePoint(int i) {
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline33(i, GeneratedOutlineSupport1.outline107("Malformed code-point "), " found"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void unsupportedByteCount(byte b) {
        int checkRadix;
        String padStart;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported byte code, first byte is 0x");
        checkRadix = CharsKt__CharJVMKt.checkRadix(16);
        String num = Integer.toString(b & 255, checkRadix);
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        padStart = StringsKt__StringsKt.padStart(num, 2, '0');
        outline107.append(padStart);
        throw new IllegalStateException(outline107.toString().toString());
    }

    private static final long decodeUTF8_buffer(@NotNull ByteBuffer byteBuffer, char[] cArr, int i, int i2, Function1<? super Character, Boolean> function1) {
        int i3;
        int i4 = i + i2;
        if (i4 > cArr.length) {
            throw indexOutOfBounds(i, i2, cArr.length);
        }
        int i5 = i;
        while (byteBuffer.hasRemaining() && i5 < i4) {
            byte b = byteBuffer.get();
            if (b >= 0) {
                char c = (char) b;
                if (!function1.mo12165invoke(Character.valueOf(c)).booleanValue()) {
                    byteBuffer.position(byteBuffer.position() - 1);
                    return decodeUtf8Result(i5 - i, -1);
                }
                i3 = i5 + 1;
                cArr[i5] = c;
            } else if ((b & 224) == 192) {
                if (!byteBuffer.hasRemaining()) {
                    byteBuffer.position(byteBuffer.position() - 1);
                    return decodeUtf8Result(i5 - i, 2);
                }
                char c2 = (char) (((b & 31) << 6) | (byteBuffer.get() & Utf8.REPLACEMENT_BYTE));
                if (!function1.mo12165invoke(Character.valueOf(c2)).booleanValue()) {
                    byteBuffer.position(byteBuffer.position() - 2);
                    return decodeUtf8Result(i5 - i, -1);
                }
                i3 = i5 + 1;
                cArr[i5] = c2;
            } else if ((b & RequesterRelationshipsPacketV1.HEADER_MASK_VERSION) == 224) {
                if (byteBuffer.remaining() < 2) {
                    byteBuffer.position(byteBuffer.position() - 1);
                    return decodeUtf8Result(i5 - i, 3);
                }
                byte b2 = byteBuffer.get();
                byte b3 = byteBuffer.get();
                int i6 = b & 15;
                int i7 = ((b2 & Utf8.REPLACEMENT_BYTE) << 6) | (i6 << 12) | (b3 & Utf8.REPLACEMENT_BYTE);
                if (i6 != 0 && !isBmpCodePoint(i7)) {
                    malformedCodePoint(i7);
                    throw null;
                }
                char c3 = (char) i7;
                if (!function1.mo12165invoke(Character.valueOf(c3)).booleanValue()) {
                    byteBuffer.position(byteBuffer.position() - 3);
                    return decodeUtf8Result(i5 - i, -1);
                }
                i3 = i5 + 1;
                cArr[i5] = c3;
            } else if ((b & 248) != 240) {
                unsupportedByteCount(b);
                throw null;
            } else if (byteBuffer.remaining() < 3) {
                byteBuffer.position(byteBuffer.position() - 1);
                return decodeUtf8Result(i5 - i, 4);
            } else {
                int i8 = ((b & 7) << 18) | ((byteBuffer.get() & Utf8.REPLACEMENT_BYTE) << 12) | ((byteBuffer.get() & Utf8.REPLACEMENT_BYTE) << 6) | (byteBuffer.get() & Utf8.REPLACEMENT_BYTE);
                if (!isValidCodePoint(i8)) {
                    malformedCodePoint(i8);
                    throw null;
                } else if (i4 - i5 >= 2) {
                    char highSurrogate = (char) highSurrogate(i8);
                    char lowSurrogate = (char) lowSurrogate(i8);
                    if (function1.mo12165invoke(Character.valueOf(highSurrogate)).booleanValue() && function1.mo12165invoke(Character.valueOf(lowSurrogate)).booleanValue()) {
                        int i9 = i5 + 1;
                        cArr[i5] = highSurrogate;
                        i5 = i9 + 1;
                        cArr[i9] = lowSurrogate;
                    } else {
                        byteBuffer.position(byteBuffer.position() - 4);
                        return decodeUtf8Result(i5 - i, -1);
                    }
                } else {
                    byteBuffer.position(byteBuffer.position() - 4);
                    return decodeUtf8Result(i5 - i, 0);
                }
            }
            i5 = i3;
        }
        return decodeUtf8Result(i5 - i, 0);
    }

    private static final long decodeUTF8_array(@NotNull ByteBuffer byteBuffer, char[] cArr, int i, int i2, Function1<? super Character, Boolean> function1) {
        int i3;
        int i4;
        byte[] array = byteBuffer.array();
        if (array == null) {
            Intrinsics.throwNpe();
        }
        int position = byteBuffer.position() + byteBuffer.arrayOffset();
        int remaining = byteBuffer.remaining() + position;
        if (position <= remaining) {
            if (remaining <= array.length) {
                int i5 = i + i2;
                if (i5 > cArr.length) {
                    throw indexOutOfBounds(i, i2, cArr.length);
                }
                int i6 = i;
                while (position < remaining && i6 < i5) {
                    int i7 = position + 1;
                    byte b = array[position];
                    if (b >= 0) {
                        char c = (char) b;
                        if (!function1.mo12165invoke(Character.valueOf(c)).booleanValue()) {
                            byteBuffer.position(i7 - 1);
                            return decodeUtf8Result(i6 - i, -1);
                        }
                        cArr[i6] = c;
                        position = i7;
                        i6++;
                    } else {
                        if ((b & 224) == 192) {
                            if (i7 >= remaining) {
                                byteBuffer.position(i7 - 1);
                                return decodeUtf8Result(i6 - i, 2);
                            }
                            i3 = i7 + 1;
                            char c2 = (char) (((b & 31) << 6) | (array[i7] & Utf8.REPLACEMENT_BYTE));
                            if (!function1.mo12165invoke(Character.valueOf(c2)).booleanValue()) {
                                byteBuffer.position(i3 - 2);
                                return decodeUtf8Result(i6 - i, -1);
                            }
                            i4 = i6 + 1;
                            cArr[i6] = c2;
                        } else if ((b & RequesterRelationshipsPacketV1.HEADER_MASK_VERSION) == 224) {
                            if (remaining - i7 < 2) {
                                byteBuffer.position(i7 - 1);
                                return decodeUtf8Result(i6 - i, 3);
                            }
                            int i8 = i7 + 1;
                            i3 = i8 + 1;
                            int i9 = b & 15;
                            int i10 = ((array[i7] & Utf8.REPLACEMENT_BYTE) << 6) | (i9 << 12) | (array[i8] & Utf8.REPLACEMENT_BYTE);
                            if (i9 != 0 && !isBmpCodePoint(i10)) {
                                malformedCodePoint(i10);
                                throw null;
                            }
                            char c3 = (char) i10;
                            if (!function1.mo12165invoke(Character.valueOf(c3)).booleanValue()) {
                                byteBuffer.position(i3 - 4);
                                return decodeUtf8Result(i6 - i, -1);
                            }
                            i4 = i6 + 1;
                            cArr[i6] = c3;
                        } else if ((b & 248) != 240) {
                            unsupportedByteCount(b);
                            throw null;
                        } else if (remaining - i7 >= 3) {
                            int i11 = i7 + 1;
                            int i12 = i11 + 1;
                            int i13 = i12 + 1;
                            int i14 = ((b & 7) << 18) | ((array[i7] & Utf8.REPLACEMENT_BYTE) << 12) | ((array[i11] & Utf8.REPLACEMENT_BYTE) << 6) | (array[i12] & Utf8.REPLACEMENT_BYTE);
                            if (!isValidCodePoint(i14)) {
                                malformedCodePoint(i14);
                                throw null;
                            } else if (i5 - i6 >= 2) {
                                char highSurrogate = (char) highSurrogate(i14);
                                char lowSurrogate = (char) lowSurrogate(i14);
                                if (function1.mo12165invoke(Character.valueOf(highSurrogate)).booleanValue() && function1.mo12165invoke(Character.valueOf(lowSurrogate)).booleanValue()) {
                                    int i15 = i6 + 1;
                                    cArr[i6] = highSurrogate;
                                    i6 = i15 + 1;
                                    cArr[i15] = lowSurrogate;
                                    position = i13;
                                } else {
                                    byteBuffer.position(i13 - 4);
                                    return decodeUtf8Result(i6 - i, -1);
                                }
                            } else {
                                byteBuffer.position(i13 - 4);
                                return decodeUtf8Result(i6 - i, 0);
                            }
                        } else {
                            byteBuffer.position(i7 - 1);
                            return decodeUtf8Result(i6 - i, 4);
                        }
                        i6 = i4;
                        position = i3;
                    }
                }
                byteBuffer.position(position);
                return decodeUtf8Result(i6 - i, 0);
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }
}
