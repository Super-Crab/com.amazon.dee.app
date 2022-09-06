package kotlinx.io.core;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.appmanager.lib.DefaultPreloadManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.internal.UnsafeKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Input.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0001\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0002H\u0007\u001a\u0014\u0010\t\u001a\u00020\b*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0006H\u0002\u001a&\u0010\u000b\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u001a&\u0010\u000b\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00102\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u001a&\u0010\u000b\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00112\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u001a&\u0010\u000b\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00122\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u001a&\u0010\u000b\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00132\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u001a&\u0010\u000b\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00142\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u001a\u001c\u0010\u000b\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00152\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u001a&\u0010\u0016\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u001a&\u0010\u0016\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00102\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u001a&\u0010\u0016\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00112\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u001a&\u0010\u0016\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00122\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u001a&\u0010\u0016\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00132\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u001a&\u0010\u0016\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00142\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u001a\u001c\u0010\u0016\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00152\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u001a!\u0010\u0017\u001a\u00020\u0004*\u00020\u00022\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u001a0\u0019H\u0086\b\u001a+\u0010\u001b\u001a\u00020\u0004*\u00020\u00022\b\b\u0002\u0010\u001c\u001a\u00020\u00062\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00060\u0019H\u0086\b¨\u0006\u001d"}, d2 = {"discard", "", "Lkotlinx/io/core/Input;", "discardExact", "", JsonReportFormat.COUNT, "", "peekCharUtf8", "", "peekCharUtf8Impl", DefaultPreloadManager.METRIC_PATH_FIRST, "readAvailable", "dst", "", "offset", "length", "", "", "", "", "", "Lkotlinx/io/core/IoBuffer;", "readFully", "takeWhile", "block", "Lkotlin/Function1;", "", "takeWhileSize", "initialSize", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class InputKt {
    public static final long discard(@NotNull Input receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.discard(Long.MAX_VALUE);
    }

    public static final void discardExact(@NotNull Input receiver$0, long j) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        long discard = receiver$0.discard(j);
        if (discard == j) {
            return;
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline87(GeneratedOutlineSupport1.outline111("Only ", discard, " bytes were discarded of "), j, " requested"));
    }

    @ExperimentalIoApi
    public static final char peekCharUtf8(@NotNull Input receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        int tryPeek = receiver$0.tryPeek();
        if ((tryPeek & 128) == 0) {
            return (char) tryPeek;
        }
        if (tryPeek != -1) {
            return peekCharUtf8Impl(receiver$0, tryPeek);
        }
        throw new EOFException("Failed to peek a char: end of input");
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x0030, code lost:
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x00b5, code lost:
        if (r1 == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x00b7, code lost:
        kotlinx.io.core.internal.UnsafeKt.completeReadHead(r11, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x00ba, code lost:
        r1 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final char peekCharUtf8Impl(@org.jetbrains.annotations.NotNull kotlinx.io.core.Input r11, int r12) {
        /*
            Method dump skipped, instructions count: 210
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.InputKt.peekCharUtf8Impl(kotlinx.io.core.Input, int):char");
    }

    public static final int readAvailable(@NotNull Input receiver$0, @NotNull byte[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        return receiver$0.readAvailable(dst, i, i2);
    }

    public static /* synthetic */ int readAvailable$default(Input input, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        return readAvailable(input, bArr, i, i2);
    }

    public static final void readFully(@NotNull Input receiver$0, @NotNull byte[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
    }

    public static /* synthetic */ void readFully$default(Input input, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        readFully(input, bArr, i, i2);
    }

    public static final void takeWhile(@NotNull Input receiver$0, @NotNull Function1<? super IoBuffer, Boolean> block) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        IoBuffer prepareReadFirstHead = UnsafeKt.prepareReadFirstHead(receiver$0, 1);
        if (prepareReadFirstHead != null) {
            while (true) {
                try {
                    if (!block.mo12165invoke(prepareReadFirstHead).booleanValue()) {
                        z = true;
                        break;
                    }
                    z = false;
                    try {
                        IoBuffer prepareReadNextHead = UnsafeKt.prepareReadNextHead(receiver$0, prepareReadFirstHead);
                        if (prepareReadNextHead == null) {
                            break;
                        }
                        prepareReadFirstHead = prepareReadNextHead;
                    } catch (Throwable th) {
                        th = th;
                        InlineMarker.finallyStart(1);
                        if (z) {
                            UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
                        }
                        InlineMarker.finallyEnd(1);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    z = true;
                }
            }
            InlineMarker.finallyStart(1);
            if (z) {
                UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void takeWhileSize(@NotNull Input receiver$0, int i, @NotNull Function1<? super IoBuffer, Integer> block) {
        boolean z;
        IoBuffer prepareReadNextHead;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        IoBuffer prepareReadFirstHead = UnsafeKt.prepareReadFirstHead(receiver$0, i);
        if (prepareReadFirstHead != null) {
            while (true) {
                z = false;
                try {
                    int readRemaining = prepareReadFirstHead.getReadRemaining();
                    if (readRemaining >= i) {
                        i = block.mo12165invoke(prepareReadFirstHead).intValue();
                        InlineMarker.finallyStart(1);
                        readRemaining = prepareReadFirstHead.getReadRemaining();
                        InlineMarker.finallyEnd(1);
                    }
                    if (readRemaining == 0) {
                        try {
                            prepareReadNextHead = UnsafeKt.prepareReadNextHead(receiver$0, prepareReadFirstHead);
                        } catch (Throwable th) {
                            th = th;
                            InlineMarker.finallyStart(1);
                            if (z) {
                                UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
                            }
                            InlineMarker.finallyEnd(1);
                            throw th;
                        }
                    } else {
                        if (readRemaining >= i && prepareReadFirstHead.getEndGap() >= IoBuffer.Companion.getReservedSize()) {
                            prepareReadNextHead = prepareReadFirstHead;
                        }
                        UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
                        prepareReadNextHead = UnsafeKt.prepareReadFirstHead(receiver$0, i);
                    }
                    if (prepareReadNextHead == null) {
                        break;
                    } else if (i <= 0) {
                        z = true;
                        prepareReadFirstHead = prepareReadNextHead;
                        break;
                    } else {
                        prepareReadFirstHead = prepareReadNextHead;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    z = true;
                }
            }
            InlineMarker.finallyStart(1);
            if (z) {
                UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public static /* synthetic */ void takeWhileSize$default(Input receiver$0, int i, Function1 block, int i2, Object obj) {
        boolean z;
        IoBuffer prepareReadNextHead;
        if ((i2 & 1) != 0) {
            i = 1;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        IoBuffer prepareReadFirstHead = UnsafeKt.prepareReadFirstHead(receiver$0, i);
        if (prepareReadFirstHead != null) {
            while (true) {
                z = false;
                try {
                    int readRemaining = prepareReadFirstHead.getReadRemaining();
                    if (readRemaining >= i) {
                        i = ((Number) block.mo12165invoke(prepareReadFirstHead)).intValue();
                        InlineMarker.finallyStart(1);
                        readRemaining = prepareReadFirstHead.getReadRemaining();
                        InlineMarker.finallyEnd(1);
                    }
                    if (readRemaining == 0) {
                        try {
                            prepareReadNextHead = UnsafeKt.prepareReadNextHead(receiver$0, prepareReadFirstHead);
                        } catch (Throwable th) {
                            th = th;
                            InlineMarker.finallyStart(1);
                            if (z) {
                                UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
                            }
                            InlineMarker.finallyEnd(1);
                            throw th;
                        }
                    } else {
                        if (readRemaining >= i && prepareReadFirstHead.getEndGap() >= IoBuffer.Companion.getReservedSize()) {
                            prepareReadNextHead = prepareReadFirstHead;
                        }
                        UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
                        prepareReadNextHead = UnsafeKt.prepareReadFirstHead(receiver$0, i);
                    }
                    if (prepareReadNextHead == null) {
                        break;
                    } else if (i <= 0) {
                        z = true;
                        prepareReadFirstHead = prepareReadNextHead;
                        break;
                    } else {
                        prepareReadFirstHead = prepareReadNextHead;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    z = true;
                }
            }
            InlineMarker.finallyStart(1);
            if (z) {
                UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public static final int readAvailable(@NotNull Input receiver$0, @NotNull short[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        return receiver$0.readAvailable(dst, i, i2);
    }

    public static /* synthetic */ int readAvailable$default(Input input, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        return readAvailable(input, sArr, i, i2);
    }

    public static final void readFully(@NotNull Input receiver$0, @NotNull short[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
    }

    public static /* synthetic */ void readFully$default(Input input, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        readFully(input, sArr, i, i2);
    }

    public static final void discardExact(@NotNull Input receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        discardExact(receiver$0, i);
    }

    public static final int readAvailable(@NotNull Input receiver$0, @NotNull int[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        return receiver$0.readAvailable(dst, i, i2);
    }

    public static /* synthetic */ int readAvailable$default(Input input, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        return readAvailable(input, iArr, i, i2);
    }

    public static final void readFully(@NotNull Input receiver$0, @NotNull int[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
    }

    public static /* synthetic */ void readFully$default(Input input, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        readFully(input, iArr, i, i2);
    }

    public static final int readAvailable(@NotNull Input receiver$0, @NotNull long[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        return receiver$0.readAvailable(dst, i, i2);
    }

    public static /* synthetic */ int readAvailable$default(Input input, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        return readAvailable(input, jArr, i, i2);
    }

    public static final void readFully(@NotNull Input receiver$0, @NotNull long[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
    }

    public static /* synthetic */ void readFully$default(Input input, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        readFully(input, jArr, i, i2);
    }

    public static final int readAvailable(@NotNull Input receiver$0, @NotNull float[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        return receiver$0.readAvailable(dst, i, i2);
    }

    public static /* synthetic */ int readAvailable$default(Input input, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        return readAvailable(input, fArr, i, i2);
    }

    public static final void readFully(@NotNull Input receiver$0, @NotNull float[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
    }

    public static /* synthetic */ void readFully$default(Input input, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        readFully(input, fArr, i, i2);
    }

    public static final int readAvailable(@NotNull Input receiver$0, @NotNull double[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        return receiver$0.readAvailable(dst, i, i2);
    }

    public static /* synthetic */ int readAvailable$default(Input input, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        return readAvailable(input, dArr, i, i2);
    }

    public static final void readFully(@NotNull Input receiver$0, @NotNull double[] dst, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i, i2);
    }

    public static /* synthetic */ void readFully$default(Input input, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        readFully(input, dArr, i, i2);
    }

    public static final int readAvailable(@NotNull Input receiver$0, @NotNull IoBuffer dst, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        return receiver$0.readAvailable(dst, i);
    }

    public static /* synthetic */ int readAvailable$default(Input input, IoBuffer ioBuffer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = ioBuffer.getWriteRemaining();
        }
        return readAvailable(input, ioBuffer, i);
    }

    public static final void readFully(@NotNull Input receiver$0, @NotNull IoBuffer dst, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        receiver$0.readFully(dst, i);
    }

    public static /* synthetic */ void readFully$default(Input input, IoBuffer ioBuffer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = ioBuffer.getWriteRemaining();
        }
        readFully(input, ioBuffer, i);
    }
}
