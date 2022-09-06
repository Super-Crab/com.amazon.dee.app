package kotlinx.coroutines.io.internal;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.charsets.UTFKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Strings.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0000\u001a$\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a9\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\u0082\b\u001a$\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a9\u0010\r\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\u0082\b\u001a(\u0010\u000e\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0000\u001a$\u0010\u000f\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a$\u0010\u0010\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002¨\u0006\u0011"}, d2 = {"decodeASCII", "", "Ljava/nio/ByteBuffer;", "out", "", "offset", "length", "decodeASCII3_array", "", "predicate", "Lkotlin/Function1;", "", "", "decodeASCII3_buffer", "decodeASCIILine", "decodeASCIILine_array", "decodeASCIILine_buffer", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class StringsKt {
    public static final int decodeASCII(@NotNull ByteBuffer receiver$0, @NotNull char[] out, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(out, "out");
        if (receiver$0.hasArray()) {
            return decodeASCII3_array(receiver$0, out, i, i2);
        }
        return decodeASCII3_buffer(receiver$0, out, i, i2);
    }

    public static /* synthetic */ int decodeASCII$default(ByteBuffer byteBuffer, char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return decodeASCII(byteBuffer, cArr, i, i2);
    }

    private static final int decodeASCII3_array(@NotNull ByteBuffer byteBuffer, char[] cArr, int i, int i2) {
        int i3;
        int i4 = i2 + i;
        byte[] array = byteBuffer.array();
        if (array == null) {
            Intrinsics.throwNpe();
        }
        int position = byteBuffer.position() + byteBuffer.arrayOffset();
        int remaining = byteBuffer.remaining() + position;
        if (i4 > cArr.length || remaining > array.length) {
            i3 = i;
        } else {
            i3 = i;
            while (position < remaining && i3 < i4) {
                byte b = array[position];
                if (b < 0) {
                    break;
                }
                cArr[i3] = (char) b;
                i3++;
                position++;
            }
            byteBuffer.position(position - byteBuffer.arrayOffset());
        }
        return i3 - i;
    }

    private static final int decodeASCII3_buffer(@NotNull ByteBuffer byteBuffer, char[] cArr, int i, int i2) {
        int i3;
        int i4 = i2 + i;
        boolean z = false;
        if (i4 <= cArr.length) {
            i3 = i;
            while (byteBuffer.hasRemaining()) {
                byte b = byteBuffer.get();
                if (b < 0 || i3 >= i4) {
                    z = true;
                    break;
                }
                cArr[i3] = (char) b;
                i3++;
            }
        } else {
            i3 = i;
        }
        if (z) {
            byteBuffer.position(byteBuffer.position() - 1);
        }
        return i3 - i;
    }

    public static final long decodeASCIILine(@NotNull ByteBuffer receiver$0, @NotNull char[] out, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(out, "out");
        return receiver$0.hasArray() ? decodeASCIILine_array(receiver$0, out, i, i2) : decodeASCIILine_buffer(receiver$0, out, i, i2);
    }

    public static /* synthetic */ long decodeASCIILine$default(ByteBuffer byteBuffer, char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return decodeASCIILine(byteBuffer, cArr, i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x005a, code lost:
        r11.position(r2 - r11.arrayOffset());
        r10 = r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0040 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final long decodeASCIILine_array(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r11, char[] r12, int r13, int r14) {
        /*
            int r14 = r14 + r13
            byte[] r0 = r11.array()
            if (r0 != 0) goto La
            kotlin.jvm.internal.Intrinsics.throwNpe()
        La:
            int r1 = r11.arrayOffset()
            int r2 = r11.position()
            int r2 = r2 + r1
            int r1 = r11.remaining()
            int r1 = r1 + r2
            int r3 = r12.length
            r4 = 13
            r5 = -1
            r6 = 0
            r7 = 1
            if (r14 > r3) goto L64
            int r3 = r0.length
            if (r1 > r3) goto L64
            r8 = r13
            r3 = r6
        L25:
            if (r2 >= r1) goto L5a
            r9 = r0[r2]
            if (r9 >= 0) goto L2c
            goto L5a
        L2c:
            char r9 = (char) r9
            if (r9 != r4) goto L31
            r3 = r7
            goto L36
        L31:
            r10 = 10
            if (r9 != r10) goto L38
            r3 = r6
        L36:
            r10 = r3
            goto L3e
        L38:
            r10 = r3
            if (r3 == 0) goto L3d
            r3 = r6
            goto L3e
        L3d:
            r3 = r7
        L3e:
            if (r3 != 0) goto L4e
            int r14 = r11.arrayOffset()
            int r2 = r2 - r14
            r11.position(r2)
            int r8 = r8 - r13
            long r13 = kotlinx.io.charsets.UTFKt.decodeUtf8Result(r8, r5)
            goto L6b
        L4e:
            if (r8 < r14) goto L52
            r3 = r10
            goto L5a
        L52:
            r12[r8] = r9
            int r8 = r8 + 1
            int r2 = r2 + 1
            r3 = r10
            goto L25
        L5a:
            int r14 = r11.arrayOffset()
            int r2 = r2 - r14
            r11.position(r2)
            r10 = r3
            goto L66
        L64:
            r8 = r13
            r10 = r6
        L66:
            int r8 = r8 - r13
            long r13 = kotlinx.io.charsets.UTFKt.decodeUtf8Result(r8, r6)
        L6b:
            r0 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r0 = r0 & r13
            int r0 = (int) r0
            r1 = 32
            if (r0 != r5) goto L95
            long r0 = r13 >> r1
            int r0 = (int) r0
            if (r10 == 0) goto L81
            int r0 = r0 - r7
            long r11 = kotlinx.io.charsets.UTFKt.decodeUtf8Result(r0, r5)
            return r11
        L81:
            int r1 = r11.position()
            int r1 = r1 + r7
            r11.position(r1)
            if (r0 <= 0) goto La9
            int r0 = r0 - r7
            char r11 = r12[r0]
            if (r11 != r4) goto La9
            long r11 = kotlinx.io.charsets.UTFKt.decodeUtf8Result(r0, r5)
            return r11
        L95:
            if (r10 == 0) goto La9
            long r12 = r13 >> r1
            int r12 = (int) r12
            int r13 = r11.position()
            int r13 = r13 - r7
            r11.position(r13)
            int r12 = r12 - r7
            r11 = 2
            long r11 = kotlinx.io.charsets.UTFKt.decodeUtf8Result(r12, r11)
            return r11
        La9:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.internal.StringsKt.decodeASCIILine_array(java.nio.ByteBuffer, char[], int, int):long");
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x003c, code lost:
        r5 = r0;
        r0 = r13;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x002b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final long decodeASCIILine_buffer(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r10, char[] r11, int r12, int r13) {
        /*
            int r13 = r13 + r12
            int r0 = r11.length
            r1 = 13
            r2 = 0
            r3 = 1
            if (r13 > r0) goto L3f
            r4 = r12
            r0 = r2
        La:
            boolean r5 = r10.hasRemaining()
            if (r5 == 0) goto L3b
            byte r5 = r10.get()
            if (r5 >= 0) goto L17
            goto L31
        L17:
            char r5 = (char) r5
            if (r5 != r1) goto L1c
            r0 = r3
            goto L21
        L1c:
            r6 = 10
            if (r5 != r6) goto L23
            r0 = r2
        L21:
            r6 = r0
            goto L29
        L23:
            r6 = r0
            if (r0 == 0) goto L28
            r0 = r2
            goto L29
        L28:
            r0 = r3
        L29:
            if (r0 != 0) goto L2e
            r13 = r3
            r0 = r6
            goto L3c
        L2e:
            if (r4 < r13) goto L35
            r0 = r6
        L31:
            r5 = r0
            r0 = r2
            r13 = r3
            goto L43
        L35:
            r11[r4] = r5
            int r4 = r4 + 1
            r0 = r6
            goto La
        L3b:
            r13 = r2
        L3c:
            r5 = r0
            r0 = r13
            goto L43
        L3f:
            r4 = r12
            r13 = r2
            r0 = r13
            r5 = r0
        L43:
            if (r13 == 0) goto L4d
            int r13 = r10.position()
            int r13 = r13 - r3
            r10.position(r13)
        L4d:
            int r4 = r4 - r12
            r12 = -1
            if (r0 == 0) goto L52
            r2 = r12
        L52:
            long r6 = kotlinx.io.charsets.UTFKt.decodeUtf8Result(r4, r2)
            r8 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r8 = r8 & r6
            int r13 = (int) r8
            r0 = 32
            if (r13 != r12) goto L88
            long r8 = r6 >> r0
            int r13 = (int) r8
            if (r5 == 0) goto L74
            int r11 = r10.position()
            int r11 = r11 - r3
            r10.position(r11)
            int r13 = r13 - r3
            long r10 = kotlinx.io.charsets.UTFKt.decodeUtf8Result(r13, r12)
            return r10
        L74:
            int r0 = r10.position()
            int r0 = r0 + r3
            r10.position(r0)
            if (r13 <= 0) goto L9c
            int r13 = r13 - r3
            char r10 = r11[r13]
            if (r10 != r1) goto L9c
            long r10 = kotlinx.io.charsets.UTFKt.decodeUtf8Result(r13, r12)
            return r10
        L88:
            if (r5 == 0) goto L9c
            long r11 = r6 >> r0
            int r11 = (int) r11
            int r12 = r10.position()
            int r12 = r12 - r3
            r10.position(r12)
            int r11 = r11 - r3
            r10 = 2
            long r10 = kotlinx.io.charsets.UTFKt.decodeUtf8Result(r11, r10)
            return r10
        L9c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.internal.StringsKt.decodeASCIILine_buffer(java.nio.ByteBuffer, char[], int, int):long");
    }

    private static final long decodeASCII3_buffer(@NotNull ByteBuffer byteBuffer, char[] cArr, int i, int i2, Function1<? super Character, Boolean> function1) {
        int i3;
        boolean z;
        boolean z2;
        int i4 = i2 + i;
        int i5 = 0;
        if (i4 <= cArr.length) {
            i3 = i;
            while (byteBuffer.hasRemaining()) {
                byte b = byteBuffer.get();
                if (b >= 0) {
                    char c = (char) b;
                    if (!function1.mo12165invoke(Character.valueOf(c)).booleanValue()) {
                        z = true;
                        break;
                    } else if (i3 < i4) {
                        cArr[i3] = c;
                        i3++;
                    }
                }
                z2 = false;
                z = true;
            }
        } else {
            i3 = i;
        }
        z = false;
        z2 = z;
        if (z) {
            byteBuffer.position(byteBuffer.position() - 1);
        }
        int i6 = i3 - i;
        if (z2) {
            i5 = -1;
        }
        return UTFKt.decodeUtf8Result(i6, i5);
    }

    private static final long decodeASCII3_array(@NotNull ByteBuffer byteBuffer, char[] cArr, int i, int i2, Function1<? super Character, Boolean> function1) {
        int i3;
        int i4 = i2 + i;
        byte[] array = byteBuffer.array();
        if (array == null) {
            Intrinsics.throwNpe();
        }
        int position = byteBuffer.position() + byteBuffer.arrayOffset();
        int remaining = byteBuffer.remaining() + position;
        if (i4 > cArr.length || remaining > array.length) {
            i3 = i;
        } else {
            i3 = i;
            while (position < remaining) {
                byte b = array[position];
                if (b < 0) {
                    break;
                }
                char c = (char) b;
                if (!function1.mo12165invoke(Character.valueOf(c)).booleanValue()) {
                    byteBuffer.position(position - byteBuffer.arrayOffset());
                    return UTFKt.decodeUtf8Result(i3 - i, -1);
                } else if (i3 >= i4) {
                    break;
                } else {
                    cArr[i3] = c;
                    i3++;
                    position++;
                }
            }
            byteBuffer.position(position - byteBuffer.arrayOffset());
        }
        return UTFKt.decodeUtf8Result(i3 - i, 0);
    }
}
