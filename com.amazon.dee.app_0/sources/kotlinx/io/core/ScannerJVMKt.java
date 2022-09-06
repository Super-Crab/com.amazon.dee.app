package kotlinx.io.core;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin._Assertions;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.internal.UnsafeKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: ScannerJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u001a9\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0082\b\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\u000bH\u0082\b\u001a9\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0082\b\u001a)\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\u000bH\u0082\b\u001a\u0014\u0010\r\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0000\u001a\u0014\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0005H\u0002\u001a\u0014\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0005H\u0002\u001a\u001c\u0010\u0012\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0000\u001a\u001c\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0002\u001a\u001c\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0002\u001a,\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0002\u001a\u001c\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u000bH\u0000\u001a,\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0002\u001a\u001c\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u000bH\u0000\u001a,\u0010\u0019\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0000\u001a\u001c\u0010\u0019\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u000bH\u0000\u001a4\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0002\u001a$\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u000bH\u0000\u001a4\u0010\u001b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0002\u001a$\u0010\u001b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u000bH\u0000\u001a4\u0010\u001c\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0000\u001a$\u0010\u001c\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u000bH\u0000¨\u0006\u001d"}, d2 = {"copyUntilArrays", "", "Ljava/nio/ByteBuffer;", "predicate", "Lkotlin/Function1;", "", "", "dst", "", "offset", "length", "Lkotlinx/io/core/Output;", "copyUntilDirect", "discardUntilDelimiterImpl", "Lkotlinx/io/core/IoBuffer;", TtmlNode.RUBY_DELIMITER, "discardUntilDelimiterImplArrays", "discardUntilDelimiterImplDirect", "discardUntilDelimitersImpl", "delimiter1", "delimiter2", "discardUntilDelimitersImplArrays", "discardUntilDelimitersImplDirect", "readUntilDelimiterArrays", "readUntilDelimiterDirect", "readUntilDelimiterImpl", "readUntilDelimitersArrays", "readUntilDelimitersDirect", "readUntilDelimitersImpl", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ScannerJVMKt {
    private static final int copyUntilArrays(@NotNull ByteBuffer byteBuffer, Function1<? super Byte, Boolean> function1, byte[] bArr, int i, int i2) {
        int i3;
        byte[] array = byteBuffer.array();
        if (array == null) {
            Intrinsics.throwNpe();
        }
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        int min = Math.min(i2, byteBuffer.remaining()) + arrayOffset;
        if (min <= array.length) {
            i3 = arrayOffset;
            while (i3 < min && !function1.mo12165invoke(Byte.valueOf(array[i3])).booleanValue()) {
                i3++;
            }
        } else {
            i3 = arrayOffset;
        }
        int i4 = i3 - arrayOffset;
        System.arraycopy(array, arrayOffset, bArr, i, i4);
        byteBuffer.position(i3 - byteBuffer.arrayOffset());
        return i4;
    }

    private static final int copyUntilDirect(@NotNull ByteBuffer byteBuffer, Function1<? super Byte, Boolean> function1, byte[] bArr, int i, int i2) {
        int position = byteBuffer.position();
        int i3 = i2 + position;
        int i4 = position;
        while (i4 < byteBuffer.limit() && i4 < i3 && !function1.mo12165invoke(Byte.valueOf(byteBuffer.get(i4))).booleanValue()) {
            i4++;
        }
        int i5 = i4 - position;
        byteBuffer.get(bArr, i, i5);
        return i5;
    }

    public static final int discardUntilDelimiterImpl(@NotNull IoBuffer receiver$0, byte b) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ByteBuffer byteBuffer = receiver$0.readBuffer;
        return byteBuffer.hasArray() ? discardUntilDelimiterImplArrays(byteBuffer, b) : discardUntilDelimiterImplDirect(byteBuffer, b);
    }

    private static final int discardUntilDelimiterImplArrays(@NotNull ByteBuffer byteBuffer, byte b) {
        int i;
        byte[] array = byteBuffer.array();
        if (array == null) {
            Intrinsics.throwNpe();
        }
        int position = byteBuffer.position() + byteBuffer.arrayOffset();
        int remaining = byteBuffer.remaining() + position;
        if (remaining <= array.length) {
            i = position;
            while (i < remaining && array[i] != b) {
                i++;
            }
        } else {
            i = position;
        }
        byteBuffer.position(i - byteBuffer.arrayOffset());
        return i - position;
    }

    private static final int discardUntilDelimiterImplDirect(@NotNull ByteBuffer byteBuffer, byte b) {
        int position = byteBuffer.position();
        int i = position;
        while (i < byteBuffer.limit() && byteBuffer.get(i) != b) {
            i++;
        }
        byteBuffer.position(i);
        return i - position;
    }

    public static final int discardUntilDelimitersImpl(@NotNull IoBuffer receiver$0, byte b, byte b2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ByteBuffer byteBuffer = receiver$0.readBuffer;
        return byteBuffer.hasArray() ? discardUntilDelimitersImplArrays(byteBuffer, b, b2) : discardUntilDelimitersImplDirect(byteBuffer, b, b2);
    }

    private static final int discardUntilDelimitersImplArrays(@NotNull ByteBuffer byteBuffer, byte b, byte b2) {
        int i;
        byte[] array = byteBuffer.array();
        if (array == null) {
            Intrinsics.throwNpe();
        }
        int position = byteBuffer.position() + byteBuffer.arrayOffset();
        int remaining = byteBuffer.remaining() + position;
        if (remaining <= array.length) {
            i = position;
            while (i < remaining) {
                byte b3 = array[i];
                if (b3 == b || b3 == b2) {
                    break;
                }
                i++;
            }
        } else {
            i = position;
        }
        byteBuffer.position(i - byteBuffer.arrayOffset());
        return i - position;
    }

    private static final int discardUntilDelimitersImplDirect(@NotNull ByteBuffer byteBuffer, byte b, byte b2) {
        byte b3;
        int position = byteBuffer.position();
        int i = position;
        while (i < byteBuffer.limit() && (b3 = byteBuffer.get(i)) != b && b3 != b2) {
            i++;
        }
        byteBuffer.position(i);
        return i - position;
    }

    private static final int readUntilDelimiterArrays(@NotNull ByteBuffer byteBuffer, byte b, byte[] bArr, int i, int i2) {
        int i3;
        byte[] array = byteBuffer.array();
        if (array == null) {
            Intrinsics.throwNpe();
        }
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        int min = Math.min(i2, byteBuffer.remaining()) + arrayOffset;
        if (min <= array.length) {
            i3 = arrayOffset;
            while (i3 < min) {
                if (array[i3] == b) {
                    break;
                }
                i3++;
            }
        } else {
            i3 = arrayOffset;
        }
        int i4 = i3 - arrayOffset;
        System.arraycopy(array, arrayOffset, bArr, i, i4);
        byteBuffer.position(i3 - byteBuffer.arrayOffset());
        return i4;
    }

    private static final int readUntilDelimiterDirect(@NotNull ByteBuffer byteBuffer, byte b, byte[] bArr, int i, int i2) {
        int position = byteBuffer.position();
        int i3 = i2 + position;
        int i4 = position;
        while (i4 < byteBuffer.limit() && i4 < i3) {
            if (byteBuffer.get(i4) == b) {
                break;
            }
            i4++;
        }
        int i5 = i4 - position;
        byteBuffer.get(bArr, i, i5);
        return i5;
    }

    public static final int readUntilDelimiterImpl(@NotNull IoBuffer receiver$0, byte b, @NotNull byte[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        boolean z = true;
        boolean z2 = i >= 0;
        if (!_Assertions.ENABLED || z2) {
            boolean z3 = i2 >= 0;
            if (_Assertions.ENABLED && !z3) {
                throw new AssertionError("Assertion failed");
            }
            if (i + i2 > dst.length) {
                z = false;
            }
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError("Assertion failed");
            }
            ByteBuffer byteBuffer = receiver$0.readBuffer;
            return byteBuffer.hasArray() ? readUntilDelimiterArrays(byteBuffer, b, dst, i, i2) : readUntilDelimiterDirect(byteBuffer, b, dst, i, i2);
        }
        throw new AssertionError("Assertion failed");
    }

    private static final int readUntilDelimitersArrays(@NotNull ByteBuffer byteBuffer, byte b, byte b2, byte[] bArr, int i, int i2) {
        int i3;
        byte[] array = byteBuffer.array();
        if (array == null) {
            Intrinsics.throwNpe();
        }
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        int min = Math.min(i2, byteBuffer.remaining()) + arrayOffset;
        if (min <= array.length) {
            i3 = arrayOffset;
            while (i3 < min) {
                byte b3 = array[i3];
                if (b3 == b || b3 == b2) {
                    break;
                }
                i3++;
            }
        } else {
            i3 = arrayOffset;
        }
        int i4 = i3 - arrayOffset;
        System.arraycopy(array, arrayOffset, bArr, i, i4);
        byteBuffer.position(i3 - byteBuffer.arrayOffset());
        return i4;
    }

    private static final int readUntilDelimitersDirect(@NotNull ByteBuffer byteBuffer, byte b, byte b2, byte[] bArr, int i, int i2) {
        int position = byteBuffer.position();
        int i3 = i2 + position;
        int i4 = position;
        while (i4 < byteBuffer.limit() && i4 < i3) {
            byte b3 = byteBuffer.get(i4);
            if (b3 == b || b3 == b2) {
                break;
            }
            i4++;
        }
        int i5 = i4 - position;
        byteBuffer.get(bArr, i, i5);
        return i5;
    }

    public static final int readUntilDelimitersImpl(@NotNull IoBuffer receiver$0, byte b, byte b2, @NotNull byte[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        boolean z = true;
        boolean z2 = i >= 0;
        if (!_Assertions.ENABLED || z2) {
            boolean z3 = i2 >= 0;
            if (_Assertions.ENABLED && !z3) {
                throw new AssertionError("Assertion failed");
            }
            boolean z4 = i + i2 <= dst.length;
            if (_Assertions.ENABLED && !z4) {
                throw new AssertionError("Assertion failed");
            }
            if (b == b2) {
                z = false;
            }
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError("Assertion failed");
            }
            ByteBuffer byteBuffer = receiver$0.readBuffer;
            return byteBuffer.hasArray() ? readUntilDelimitersArrays(byteBuffer, b, b2, dst, i, i2) : readUntilDelimitersDirect(byteBuffer, b, b2, dst, i, i2);
        }
        throw new AssertionError("Assertion failed");
    }

    private static final int copyUntilDirect(@NotNull ByteBuffer byteBuffer, Function1<? super Byte, Boolean> function1, Output output) {
        int position = byteBuffer.position();
        IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        int i = 0;
        while (true) {
            try {
                ByteBuffer byteBuffer2 = prepareWriteHead.writeBuffer;
                int remaining = byteBuffer2.remaining() + position;
                int i2 = position;
                while (i2 < byteBuffer.limit() && i2 < remaining && !function1.mo12165invoke(Byte.valueOf(byteBuffer.get(i2))).booleanValue()) {
                    i2++;
                }
                int limit = byteBuffer.limit();
                byteBuffer.position(position);
                byteBuffer.limit(i2);
                byteBuffer2.put(byteBuffer);
                byteBuffer.limit(limit);
                prepareWriteHead.readBuffer.limit(prepareWriteHead.writeBuffer.position());
                i += i2 - position;
                if (!(!byteBuffer2.hasRemaining() && i2 < byteBuffer.limit())) {
                    InlineMarker.finallyStart(1);
                    UnsafeKt.afterHeadWrite(output, prepareWriteHead);
                    InlineMarker.finallyEnd(1);
                    byteBuffer.position(i2);
                    return i;
                }
                prepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, prepareWriteHead);
                position = i2;
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                UnsafeKt.afterHeadWrite(output, prepareWriteHead);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
    }

    public static final int readUntilDelimiterDirect(@NotNull ByteBuffer receiver$0, byte b, @NotNull Output dst) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        int position = receiver$0.position();
        IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(dst, 1, null);
        int i = 0;
        while (true) {
            try {
                ByteBuffer byteBuffer = prepareWriteHead.writeBuffer;
                int remaining = byteBuffer.remaining() + position;
                int i2 = position;
                while (i2 < receiver$0.limit() && i2 < remaining) {
                    if (receiver$0.get(i2) == b) {
                        break;
                    }
                    i2++;
                }
                int limit = receiver$0.limit();
                receiver$0.position(position);
                receiver$0.limit(i2);
                byteBuffer.put(receiver$0);
                receiver$0.limit(limit);
                prepareWriteHead.readBuffer.limit(prepareWriteHead.writeBuffer.position());
                i += i2 - position;
                if (!(!byteBuffer.hasRemaining() && i2 < receiver$0.limit())) {
                    UnsafeKt.afterHeadWrite(dst, prepareWriteHead);
                    receiver$0.position(i2);
                    return i;
                }
                prepareWriteHead = UnsafeKt.prepareWriteHead(dst, 1, prepareWriteHead);
                position = i2;
            } catch (Throwable th) {
                UnsafeKt.afterHeadWrite(dst, prepareWriteHead);
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0035 A[Catch: all -> 0x0074, LOOP:1: B:4:0x001e->B:15:0x0035, LOOP_END, TryCatch #0 {all -> 0x0074, blocks: (B:3:0x0016, B:4:0x001e, B:7:0x0026, B:15:0x0035, B:16:0x0038, B:18:0x005c, B:25:0x006e), top: B:30:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0034 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int readUntilDelimitersDirect(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r9, byte r10, byte r11, @org.jetbrains.annotations.NotNull kotlinx.io.core.Output r12) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            int r0 = r9.position()
            r1 = 1
            r2 = 0
            kotlinx.io.core.IoBuffer r2 = kotlinx.io.core.internal.UnsafeKt.prepareWriteHead(r12, r1, r2)
            r3 = 0
            r4 = r3
        L16:
            java.nio.ByteBuffer r5 = r2.writeBuffer     // Catch: java.lang.Throwable -> L74
            int r6 = r5.remaining()     // Catch: java.lang.Throwable -> L74
            int r6 = r6 + r0
            r7 = r0
        L1e:
            int r8 = r9.limit()     // Catch: java.lang.Throwable -> L74
            if (r7 >= r8) goto L38
            if (r7 >= r6) goto L38
            byte r8 = r9.get(r7)     // Catch: java.lang.Throwable -> L74
            if (r8 == r10) goto L31
            if (r8 != r11) goto L2f
            goto L31
        L2f:
            r8 = r3
            goto L32
        L31:
            r8 = r1
        L32:
            if (r8 == 0) goto L35
            goto L38
        L35:
            int r7 = r7 + 1
            goto L1e
        L38:
            int r6 = r7 - r0
            int r8 = r9.limit()     // Catch: java.lang.Throwable -> L74
            r9.position(r0)     // Catch: java.lang.Throwable -> L74
            r9.limit(r7)     // Catch: java.lang.Throwable -> L74
            r5.put(r9)     // Catch: java.lang.Throwable -> L74
            r9.limit(r8)     // Catch: java.lang.Throwable -> L74
            java.nio.ByteBuffer r0 = r2.readBuffer     // Catch: java.lang.Throwable -> L74
            java.nio.ByteBuffer r8 = r2.writeBuffer     // Catch: java.lang.Throwable -> L74
            int r8 = r8.position()     // Catch: java.lang.Throwable -> L74
            r0.limit(r8)     // Catch: java.lang.Throwable -> L74
            int r4 = r4 + r6
            boolean r0 = r5.hasRemaining()     // Catch: java.lang.Throwable -> L74
            if (r0 != 0) goto L64
            int r0 = r9.limit()     // Catch: java.lang.Throwable -> L74
            if (r7 >= r0) goto L64
            r0 = r1
            goto L65
        L64:
            r0 = r3
        L65:
            if (r0 != 0) goto L6e
            kotlinx.io.core.internal.UnsafeKt.afterHeadWrite(r12, r2)
            r9.position(r7)
            return r4
        L6e:
            kotlinx.io.core.IoBuffer r2 = kotlinx.io.core.internal.UnsafeKt.prepareWriteHead(r12, r1, r2)     // Catch: java.lang.Throwable -> L74
            r0 = r7
            goto L16
        L74:
            r9 = move-exception
            kotlinx.io.core.internal.UnsafeKt.afterHeadWrite(r12, r2)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.ScannerJVMKt.readUntilDelimitersDirect(java.nio.ByteBuffer, byte, byte, kotlinx.io.core.Output):int");
    }

    public static final int readUntilDelimiterImpl(@NotNull IoBuffer receiver$0, byte b, @NotNull Output dst) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        ByteBuffer byteBuffer = receiver$0.readBuffer;
        return byteBuffer.hasArray() ? readUntilDelimiterArrays(byteBuffer, b, dst) : readUntilDelimiterDirect(byteBuffer, b, dst);
    }

    private static final int copyUntilArrays(@NotNull ByteBuffer byteBuffer, Function1<? super Byte, Boolean> function1, Output output) {
        int i;
        byte[] array = byteBuffer.array();
        if (array == null) {
            Intrinsics.throwNpe();
        }
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        int i2 = 0;
        while (true) {
            try {
                ByteBuffer byteBuffer2 = prepareWriteHead.writeBuffer;
                int min = Math.min(byteBuffer2.remaining() + arrayOffset, byteBuffer.limit() + byteBuffer.arrayOffset());
                if (min <= array.length) {
                    i = arrayOffset;
                    while (i < min && !function1.mo12165invoke(Byte.valueOf(array[i])).booleanValue()) {
                        i++;
                    }
                } else {
                    i = arrayOffset;
                }
                int i3 = i - arrayOffset;
                int limit = byteBuffer.limit();
                byteBuffer.position(arrayOffset - byteBuffer.arrayOffset());
                byteBuffer.limit(byteBuffer.position() + i3);
                byteBuffer2.put(byteBuffer);
                byteBuffer.limit(limit);
                prepareWriteHead.readBuffer.limit(prepareWriteHead.writeBuffer.position());
                i2 += i3;
                if (!(!byteBuffer2.hasRemaining() && byteBuffer.hasRemaining())) {
                    InlineMarker.finallyStart(1);
                    UnsafeKt.afterHeadWrite(output, prepareWriteHead);
                    InlineMarker.finallyEnd(1);
                    byteBuffer.position(i);
                    return i2;
                }
                prepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, prepareWriteHead);
                arrayOffset = i;
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                UnsafeKt.afterHeadWrite(output, prepareWriteHead);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
    }

    public static final int readUntilDelimiterArrays(@NotNull ByteBuffer receiver$0, byte b, @NotNull Output dst) {
        int i;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        byte[] array = receiver$0.array();
        if (array == null) {
            Intrinsics.throwNpe();
        }
        int arrayOffset = receiver$0.arrayOffset() + receiver$0.position();
        IoBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(dst, 1, null);
        int i2 = 0;
        while (true) {
            try {
                ByteBuffer byteBuffer = prepareWriteHead.writeBuffer;
                int min = Math.min(byteBuffer.remaining() + arrayOffset, receiver$0.limit() + receiver$0.arrayOffset());
                if (min <= array.length) {
                    i = arrayOffset;
                    while (i < min) {
                        if (array[i] == b) {
                            break;
                        }
                        i++;
                    }
                } else {
                    i = arrayOffset;
                }
                int i3 = i - arrayOffset;
                int limit = receiver$0.limit();
                receiver$0.position(arrayOffset - receiver$0.arrayOffset());
                receiver$0.limit(receiver$0.position() + i3);
                byteBuffer.put(receiver$0);
                receiver$0.limit(limit);
                prepareWriteHead.readBuffer.limit(prepareWriteHead.writeBuffer.position());
                i2 += i3;
                if (!(!byteBuffer.hasRemaining() && receiver$0.hasRemaining())) {
                    UnsafeKt.afterHeadWrite(dst, prepareWriteHead);
                    receiver$0.position(i);
                    return i2;
                }
                prepareWriteHead = UnsafeKt.prepareWriteHead(dst, 1, prepareWriteHead);
                arrayOffset = i;
            } catch (Throwable th) {
                UnsafeKt.afterHeadWrite(dst, prepareWriteHead);
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004b A[Catch: all -> 0x0095, LOOP:1: B:9:0x003c->B:18:0x004b, LOOP_END, TryCatch #0 {all -> 0x0095, blocks: (B:6:0x0024, B:10:0x003e, B:18:0x004b, B:20:0x004f, B:22:0x007d, B:29:0x008f), top: B:34:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x004a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int readUntilDelimitersArrays(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r11, byte r12, byte r13, @org.jetbrains.annotations.NotNull kotlinx.io.core.Output r14) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r0)
            byte[] r0 = r11.array()
            if (r0 != 0) goto L13
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L13:
            int r1 = r11.position()
            int r2 = r11.arrayOffset()
            int r2 = r2 + r1
            r1 = 0
            r3 = 1
            kotlinx.io.core.IoBuffer r1 = kotlinx.io.core.internal.UnsafeKt.prepareWriteHead(r14, r3, r1)
            r4 = 0
            r5 = r4
        L24:
            java.nio.ByteBuffer r6 = r1.writeBuffer     // Catch: java.lang.Throwable -> L95
            int r7 = r6.remaining()     // Catch: java.lang.Throwable -> L95
            int r7 = r7 + r2
            int r8 = r11.limit()     // Catch: java.lang.Throwable -> L95
            int r9 = r11.arrayOffset()     // Catch: java.lang.Throwable -> L95
            int r8 = r8 + r9
            int r7 = java.lang.Math.min(r7, r8)     // Catch: java.lang.Throwable -> L95
            int r8 = r0.length     // Catch: java.lang.Throwable -> L95
            if (r7 > r8) goto L4e
            r8 = r2
        L3c:
            if (r8 >= r7) goto L4f
            r9 = r0[r8]     // Catch: java.lang.Throwable -> L95
            if (r9 == r12) goto L47
            if (r9 != r13) goto L45
            goto L47
        L45:
            r9 = r4
            goto L48
        L47:
            r9 = r3
        L48:
            if (r9 == 0) goto L4b
            goto L4f
        L4b:
            int r8 = r8 + 1
            goto L3c
        L4e:
            r8 = r2
        L4f:
            int r7 = r8 - r2
            int r9 = r11.limit()     // Catch: java.lang.Throwable -> L95
            int r10 = r11.arrayOffset()     // Catch: java.lang.Throwable -> L95
            int r2 = r2 - r10
            r11.position(r2)     // Catch: java.lang.Throwable -> L95
            int r2 = r11.position()     // Catch: java.lang.Throwable -> L95
            int r2 = r2 + r7
            r11.limit(r2)     // Catch: java.lang.Throwable -> L95
            r6.put(r11)     // Catch: java.lang.Throwable -> L95
            r11.limit(r9)     // Catch: java.lang.Throwable -> L95
            java.nio.ByteBuffer r2 = r1.readBuffer     // Catch: java.lang.Throwable -> L95
            java.nio.ByteBuffer r9 = r1.writeBuffer     // Catch: java.lang.Throwable -> L95
            int r9 = r9.position()     // Catch: java.lang.Throwable -> L95
            r2.limit(r9)     // Catch: java.lang.Throwable -> L95
            int r5 = r5 + r7
            boolean r2 = r6.hasRemaining()     // Catch: java.lang.Throwable -> L95
            if (r2 != 0) goto L85
            boolean r2 = r11.hasRemaining()     // Catch: java.lang.Throwable -> L95
            if (r2 == 0) goto L85
            r2 = r3
            goto L86
        L85:
            r2 = r4
        L86:
            if (r2 != 0) goto L8f
            kotlinx.io.core.internal.UnsafeKt.afterHeadWrite(r14, r1)
            r11.position(r8)
            return r5
        L8f:
            kotlinx.io.core.IoBuffer r1 = kotlinx.io.core.internal.UnsafeKt.prepareWriteHead(r14, r3, r1)     // Catch: java.lang.Throwable -> L95
            r2 = r8
            goto L24
        L95:
            r11 = move-exception
            kotlinx.io.core.internal.UnsafeKt.afterHeadWrite(r14, r1)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.ScannerJVMKt.readUntilDelimitersArrays(java.nio.ByteBuffer, byte, byte, kotlinx.io.core.Output):int");
    }

    public static final int readUntilDelimitersImpl(@NotNull IoBuffer receiver$0, byte b, byte b2, @NotNull Output dst) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        boolean z = b != b2;
        if (!_Assertions.ENABLED || z) {
            ByteBuffer byteBuffer = receiver$0.readBuffer;
            return byteBuffer.hasArray() ? readUntilDelimitersArrays(byteBuffer, b, b2, dst) : readUntilDelimitersDirect(byteBuffer, b, b2, dst);
        }
        throw new AssertionError("Assertion failed");
    }
}
