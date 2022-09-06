package kotlinx.coroutines.io.internal;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty1;
import org.jetbrains.annotations.NotNull;
/* compiled from: Utils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a/\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\n\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00010\nH\u0080\b\u001a/\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\f\"\n\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\r0\nH\u0080\b\u001a=\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00100\u000f\"\n\b\u0000\u0010\u0007\u0018\u0001*\u00020\b\"\u0006\b\u0001\u0010\u0010\u0018\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00100\nH\u0080\b\u001a \u0010\u0011\u001a\u00020\u0012*\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0016\u001a\u00020\u0001*\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0012H\u0000\u001a\f\u0010\u0018\u001a\u00020\u0019*\u00020\u0012H\u0000\u001a\u001e\u0010\u001a\u001a\u00020\u0001*\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00122\b\b\u0002\u0010\u001c\u001a\u00020\u0001H\u0000\u001a\u001e\u0010\u001d\u001a\u00020\u0001*\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u0001H\u0000\u001a\u001e\u0010\u001f\u001a\u00020\u0019*\u00020\u00122\u0006\u0010 \u001a\u00020\u00122\b\b\u0002\u0010!\u001a\u00020\u0001H\u0000¨\u0006\""}, d2 = {"getIOIntProperty", "", "name", "", "default", "intUpdater", "Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;", "Owner", "", "p", "Lkotlin/reflect/KProperty1;", "longUpdater", "Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;", "", "updater", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", ExifInterface.GPS_DIRECTION_TRUE, "asByteBuffer", "Ljava/nio/ByteBuffer;", "", "offset", "length", "indexOfPartial", "sub", "isEmpty", "", "putAtMost", "src", JsonReportFormat.COUNT, "putLimited", MetricsUtil.LegacyMetricTypes.LIMIT, "startsWith", "prefix", "prefixSkip", "kotlinx-coroutines-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class UtilsKt {
    @NotNull
    public static final ByteBuffer asByteBuffer(@NotNull byte[] receiver$0, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ByteBuffer wrap = ByteBuffer.wrap(receiver$0, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(wrap, "ByteBuffer.wrap(this, offset, length)");
        return wrap;
    }

    @NotNull
    public static /* synthetic */ ByteBuffer asByteBuffer$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        return asByteBuffer(bArr, i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001e, code lost:
        r2 = kotlin.text.StringsKt__StringNumberConversionsKt.toIntOrNull(r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int getIOIntProperty(@org.jetbrains.annotations.NotNull java.lang.String r2, int r3) {
        /*
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.SecurityException -> L1b
            r0.<init>()     // Catch: java.lang.SecurityException -> L1b
            java.lang.String r1 = "kotlinx.coroutines.io."
            r0.append(r1)     // Catch: java.lang.SecurityException -> L1b
            r0.append(r2)     // Catch: java.lang.SecurityException -> L1b
            java.lang.String r2 = r0.toString()     // Catch: java.lang.SecurityException -> L1b
            java.lang.String r2 = java.lang.System.getProperty(r2)     // Catch: java.lang.SecurityException -> L1b
            goto L1c
        L1b:
            r2 = 0
        L1c:
            if (r2 == 0) goto L28
            java.lang.Integer r2 = kotlin.text.StringsKt.toIntOrNull(r2)
            if (r2 == 0) goto L28
            int r3 = r2.intValue()
        L28:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.internal.UtilsKt.getIOIntProperty(java.lang.String, int):int");
    }

    public static final int indexOfPartial(@NotNull ByteBuffer receiver$0, @NotNull ByteBuffer sub) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(sub, "sub");
        int position = sub.position();
        int remaining = sub.remaining();
        byte b = sub.get(position);
        int limit = receiver$0.limit();
        loop0: for (int position2 = receiver$0.position(); position2 < limit; position2++) {
            if (receiver$0.get(position2) == b) {
                for (int i = 1; i < remaining; i++) {
                    int i2 = position2 + i;
                    if (i2 == limit) {
                        break loop0;
                    } else if (receiver$0.get(i2) != sub.get(position + i)) {
                        break;
                    }
                }
                return position2 - receiver$0.position();
            }
        }
        return -1;
    }

    private static final <Owner> AtomicIntegerFieldUpdater<Owner> intUpdater(KProperty1<Owner, Integer> kProperty1) {
        Intrinsics.reifiedOperationMarker(4, "Owner");
        AtomicIntegerFieldUpdater<Owner> newUpdater = AtomicIntegerFieldUpdater.newUpdater(Object.class, kProperty1.getName());
        Intrinsics.checkExpressionValueIsNotNull(newUpdater, "AtomicIntegerFieldUpdate…wner::class.java, p.name)");
        return newUpdater;
    }

    public static final boolean isEmpty(@NotNull ByteBuffer receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return !receiver$0.hasRemaining();
    }

    private static final <Owner> AtomicLongFieldUpdater<Owner> longUpdater(KProperty1<Owner, Long> kProperty1) {
        Intrinsics.reifiedOperationMarker(4, "Owner");
        AtomicLongFieldUpdater<Owner> newUpdater = AtomicLongFieldUpdater.newUpdater(Object.class, kProperty1.getName());
        Intrinsics.checkExpressionValueIsNotNull(newUpdater, "AtomicLongFieldUpdater.n…wner::class.java, p.name)");
        return newUpdater;
    }

    public static final int putAtMost(@NotNull ByteBuffer receiver$0, @NotNull ByteBuffer src, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(src, "src");
        int remaining = receiver$0.remaining();
        int remaining2 = src.remaining();
        if (remaining2 <= remaining && remaining2 <= i) {
            receiver$0.put(src);
        } else {
            remaining2 = Math.min(remaining, Math.min(remaining2, i));
            int i2 = 1;
            if (1 <= remaining2) {
                while (true) {
                    receiver$0.put(src.get());
                    if (i2 == remaining2) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return remaining2;
    }

    public static /* synthetic */ int putAtMost$default(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = byteBuffer2.remaining();
        }
        return putAtMost(byteBuffer, byteBuffer2, i);
    }

    public static final int putLimited(@NotNull ByteBuffer receiver$0, @NotNull ByteBuffer src, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(src, "src");
        return putAtMost(receiver$0, src, i - src.position());
    }

    public static /* synthetic */ int putLimited$default(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = byteBuffer.limit();
        }
        return putLimited(byteBuffer, byteBuffer2, i);
    }

    public static final boolean startsWith(@NotNull ByteBuffer receiver$0, @NotNull ByteBuffer prefix, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        int min = Math.min(receiver$0.remaining(), prefix.remaining() - i);
        if (min <= 0) {
            return false;
        }
        int position = receiver$0.position();
        int position2 = prefix.position() + i;
        for (int i2 = 0; i2 < min; i2++) {
            if (receiver$0.get(position + i2) != prefix.get(position2 + i2)) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ boolean startsWith$default(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return startsWith(byteBuffer, byteBuffer2, i);
    }

    private static final <Owner, T> AtomicReferenceFieldUpdater<Owner, T> updater(KProperty1<Owner, ? extends T> kProperty1) {
        Intrinsics.reifiedOperationMarker(4, "Owner");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        AtomicReferenceFieldUpdater<Owner, T> newUpdater = AtomicReferenceFieldUpdater.newUpdater(Object.class, Object.class, kProperty1.getName());
        Intrinsics.checkExpressionValueIsNotNull(newUpdater, "AtomicReferenceFieldUpda…a, T::class.java, p.name)");
        return newUpdater;
    }
}
