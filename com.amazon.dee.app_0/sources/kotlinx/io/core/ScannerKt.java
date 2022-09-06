package kotlinx.io.core;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.internal.UnsafeKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Scanner.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001a\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004\u001a.\u0010\b\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t\u001a\u001a\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000e\u001a6\u0010\u000f\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t\u001a\"\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000e¨\u0006\u0010"}, d2 = {"discardUntilDelimiter", "", "Lkotlinx/io/core/Input;", TtmlNode.RUBY_DELIMITER, "", "discardUntilDelimiters", "delimiter1", "delimiter2", "readUntilDelimiter", "", "dst", "", "offset", "length", "Lkotlinx/io/core/Output;", "readUntilDelimiters", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ScannerKt {
    public static final long discardUntilDelimiter(@NotNull Input receiver$0, byte b) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        boolean z = true;
        IoBuffer prepareReadFirstHead = UnsafeKt.prepareReadFirstHead(receiver$0, 1);
        long j = 0;
        if (prepareReadFirstHead != null) {
            while (true) {
                try {
                    int discardUntilDelimiterImpl = ScannerJVMKt.discardUntilDelimiterImpl(prepareReadFirstHead, b);
                    j += discardUntilDelimiterImpl;
                    if (!(discardUntilDelimiterImpl > 0 && !prepareReadFirstHead.canRead())) {
                        break;
                    }
                    try {
                        IoBuffer prepareReadNextHead = UnsafeKt.prepareReadNextHead(receiver$0, prepareReadFirstHead);
                        if (prepareReadNextHead == null) {
                            z = false;
                            break;
                        }
                        prepareReadFirstHead = prepareReadNextHead;
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        if (z) {
                            UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
            }
        }
        return j;
    }

    public static final long discardUntilDelimiters(@NotNull Input receiver$0, byte b, byte b2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        boolean z = true;
        IoBuffer prepareReadFirstHead = UnsafeKt.prepareReadFirstHead(receiver$0, 1);
        long j = 0;
        if (prepareReadFirstHead != null) {
            while (true) {
                try {
                    int discardUntilDelimitersImpl = ScannerJVMKt.discardUntilDelimitersImpl(prepareReadFirstHead, b, b2);
                    j += discardUntilDelimitersImpl;
                    if (!(discardUntilDelimitersImpl > 0 && !prepareReadFirstHead.canRead())) {
                        break;
                    }
                    try {
                        IoBuffer prepareReadNextHead = UnsafeKt.prepareReadNextHead(receiver$0, prepareReadFirstHead);
                        if (prepareReadNextHead == null) {
                            z = false;
                            break;
                        }
                        prepareReadFirstHead = prepareReadNextHead;
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        if (z) {
                            UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
            }
        }
        return j;
    }

    public static final int readUntilDelimiter(@NotNull Input receiver$0, byte b, @NotNull byte[] dst, int i, int i2) {
        int i3;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        boolean z = true;
        IoBuffer prepareReadFirstHead = UnsafeKt.prepareReadFirstHead(receiver$0, 1);
        if (prepareReadFirstHead != null) {
            int i4 = i2;
            i3 = i;
            while (true) {
                try {
                    int readUntilDelimiterImpl = ScannerJVMKt.readUntilDelimiterImpl(prepareReadFirstHead, b, dst, i3, i4);
                    i3 += readUntilDelimiterImpl;
                    i4 -= readUntilDelimiterImpl;
                    if (!(i4 > 0 && !prepareReadFirstHead.canRead())) {
                        break;
                    }
                    try {
                        IoBuffer prepareReadNextHead = UnsafeKt.prepareReadNextHead(receiver$0, prepareReadFirstHead);
                        if (prepareReadNextHead == null) {
                            z = false;
                            break;
                        }
                        prepareReadFirstHead = prepareReadNextHead;
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        if (z) {
                            UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
            }
        } else {
            i3 = i;
        }
        return i3 - i;
    }

    public static /* synthetic */ int readUntilDelimiter$default(Input input, byte b, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = bArr.length;
        }
        return readUntilDelimiter(input, b, bArr, i, i2);
    }

    public static final int readUntilDelimiters(@NotNull Input receiver$0, byte b, byte b2, @NotNull byte[] dst, int i, int i2) {
        int i3;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        if (b == b2) {
            return readUntilDelimiter(receiver$0, b, dst, i, i2);
        }
        boolean z = true;
        IoBuffer prepareReadFirstHead = UnsafeKt.prepareReadFirstHead(receiver$0, 1);
        if (prepareReadFirstHead != null) {
            int i4 = i2;
            i3 = i;
            while (true) {
                try {
                    int readUntilDelimitersImpl = ScannerJVMKt.readUntilDelimitersImpl(prepareReadFirstHead, b, b2, dst, i3, i4);
                    i3 += readUntilDelimitersImpl;
                    i4 -= readUntilDelimitersImpl;
                    if (!(!prepareReadFirstHead.canRead() && i4 > 0)) {
                        break;
                    }
                    try {
                        IoBuffer prepareReadNextHead = UnsafeKt.prepareReadNextHead(receiver$0, prepareReadFirstHead);
                        if (prepareReadNextHead == null) {
                            z = false;
                            break;
                        }
                        prepareReadFirstHead = prepareReadNextHead;
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        if (z) {
                            UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
            }
        } else {
            i3 = i;
        }
        return i3 - i;
    }

    public static /* synthetic */ int readUntilDelimiters$default(Input input, byte b, byte b2, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i = 0;
        }
        int i4 = i;
        if ((i3 & 16) != 0) {
            i2 = bArr.length;
        }
        return readUntilDelimiters(input, b, b2, bArr, i4, i2);
    }

    public static final long readUntilDelimiter(@NotNull Input receiver$0, byte b, @NotNull Output dst) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        boolean z = true;
        IoBuffer prepareReadFirstHead = UnsafeKt.prepareReadFirstHead(receiver$0, 1);
        long j = 0;
        if (prepareReadFirstHead != null) {
            while (true) {
                try {
                    j += ScannerJVMKt.readUntilDelimiterImpl(prepareReadFirstHead, b, dst);
                    if (!(!prepareReadFirstHead.canRead())) {
                        break;
                    }
                    try {
                        IoBuffer prepareReadNextHead = UnsafeKt.prepareReadNextHead(receiver$0, prepareReadFirstHead);
                        if (prepareReadNextHead == null) {
                            z = false;
                            break;
                        }
                        prepareReadFirstHead = prepareReadNextHead;
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        if (z) {
                            UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
            }
        }
        return j;
    }

    public static final long readUntilDelimiters(@NotNull Input receiver$0, byte b, byte b2, @NotNull Output dst) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        boolean z = true;
        IoBuffer prepareReadFirstHead = UnsafeKt.prepareReadFirstHead(receiver$0, 1);
        long j = 0;
        if (prepareReadFirstHead != null) {
            while (true) {
                try {
                    j += ScannerJVMKt.readUntilDelimitersImpl(prepareReadFirstHead, b, b2, dst);
                    if (!(!prepareReadFirstHead.canRead())) {
                        break;
                    }
                    try {
                        IoBuffer prepareReadNextHead = UnsafeKt.prepareReadNextHead(receiver$0, prepareReadFirstHead);
                        if (prepareReadNextHead == null) {
                            z = false;
                            break;
                        }
                        prepareReadFirstHead = prepareReadNextHead;
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        if (z) {
                            UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(receiver$0, prepareReadFirstHead);
            }
        }
        return j;
    }
}
