package kotlinx.io.charsets;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Strings.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0000\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0080\b\u001a$\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a9\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0082\b\u001a$\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a9\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0082\b¨\u0006\r"}, d2 = {"decodeASCII", "", "Ljava/nio/ByteBuffer;", "out", "", "offset", "length", "predicate", "Lkotlin/Function1;", "", "", "decodeASCII3_array", "decodeASCII3_buffer", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class StringsKt {
    public static final int decodeASCII(@NotNull ByteBuffer receiver$0, @NotNull char[] out, int i, int i2, @NotNull Function1<? super Character, Boolean> predicate) {
        int i3;
        int i4;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(out, "out");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        if (receiver$0.hasArray()) {
            int i5 = i2 + i;
            byte[] array = receiver$0.array();
            if (array == null) {
                Intrinsics.throwNpe();
            }
            int position = receiver$0.position() + receiver$0.arrayOffset();
            int remaining = receiver$0.remaining() + position;
            if (i5 > out.length || remaining > array.length) {
                i4 = i;
            } else {
                i4 = i;
                while (true) {
                    if (position >= remaining || i4 >= i5) {
                        break;
                    }
                    byte b = array[position];
                    if (b < 0) {
                        break;
                    }
                    char c = (char) b;
                    if (!predicate.mo12165invoke(Character.valueOf(c)).booleanValue()) {
                        position--;
                        break;
                    }
                    out[i4] = c;
                    i4++;
                    position++;
                }
                receiver$0.position(position - receiver$0.arrayOffset());
            }
            return i4 - i;
        }
        int i6 = i2 + i;
        boolean z = false;
        if (i6 <= out.length) {
            i3 = i;
            while (receiver$0.hasRemaining()) {
                byte b2 = receiver$0.get();
                if (b2 >= 0 && i3 < i6) {
                    char c2 = (char) b2;
                    if (predicate.mo12165invoke(Character.valueOf(c2)).booleanValue()) {
                        out[i3] = c2;
                        i3++;
                    }
                }
                z = true;
            }
        } else {
            i3 = i;
        }
        if (z) {
            receiver$0.position(receiver$0.position() - 1);
        }
        return i3 - i;
    }

    public static /* synthetic */ int decodeASCII$default(ByteBuffer receiver$0, char[] out, int i, int i2, Function1 predicate, int i3, Object obj) {
        int i4;
        int i5;
        boolean z = false;
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = out.length;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(out, "out");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        if (receiver$0.hasArray()) {
            int i6 = i2 + i;
            byte[] array = receiver$0.array();
            if (array == null) {
                Intrinsics.throwNpe();
            }
            int position = receiver$0.position() + receiver$0.arrayOffset();
            int remaining = receiver$0.remaining() + position;
            if (i6 > out.length || remaining > array.length) {
                i5 = i;
            } else {
                i5 = i;
                while (true) {
                    if (position >= remaining || i5 >= i6) {
                        break;
                    }
                    byte b = array[position];
                    if (b < 0) {
                        break;
                    }
                    char c = (char) b;
                    if (!((Boolean) predicate.mo12165invoke(Character.valueOf(c))).booleanValue()) {
                        position--;
                        break;
                    }
                    out[i5] = c;
                    i5++;
                    position++;
                }
                receiver$0.position(position - receiver$0.arrayOffset());
            }
            return i5 - i;
        }
        int i7 = i2 + i;
        if (i7 <= out.length) {
            i4 = i;
            while (receiver$0.hasRemaining()) {
                byte b2 = receiver$0.get();
                if (b2 >= 0 && i4 < i7) {
                    char c2 = (char) b2;
                    if (((Boolean) predicate.mo12165invoke(Character.valueOf(c2))).booleanValue()) {
                        out[i4] = c2;
                        i4++;
                    }
                }
                z = true;
            }
        } else {
            i4 = i;
        }
        if (z) {
            receiver$0.position(receiver$0.position() - 1);
        }
        return i4 - i;
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final int decodeASCII3_buffer(@NotNull ByteBuffer byteBuffer, char[] cArr, int i, int i2, Function1<? super Character, Boolean> function1) {
        int i3;
        int i4 = i2 + i;
        boolean z = false;
        if (i4 <= cArr.length) {
            i3 = i;
            while (byteBuffer.hasRemaining()) {
                byte b = byteBuffer.get();
                if (b >= 0 && i3 < i4) {
                    char c = (char) b;
                    if (function1.mo12165invoke(Character.valueOf(c)).booleanValue()) {
                        cArr[i3] = c;
                        i3++;
                    }
                }
                z = true;
            }
        } else {
            i3 = i;
        }
        if (z) {
            byteBuffer.position(byteBuffer.position() - 1);
        }
        return i3 - i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int decodeASCII3_array(@NotNull ByteBuffer byteBuffer, char[] cArr, int i, int i2, Function1<? super Character, Boolean> function1) {
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
            while (true) {
                if (position >= remaining || i3 >= i4) {
                    break;
                }
                byte b = array[position];
                if (b < 0) {
                    break;
                }
                char c = (char) b;
                if (!function1.mo12165invoke(Character.valueOf(c)).booleanValue()) {
                    position--;
                    break;
                }
                cArr[i3] = c;
                i3++;
                position++;
            }
            byteBuffer.position(position - byteBuffer.arrayOffset());
        }
        return i3 - i;
    }

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
}
