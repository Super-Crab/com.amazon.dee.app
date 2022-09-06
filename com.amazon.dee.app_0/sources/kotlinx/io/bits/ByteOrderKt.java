package kotlinx.io.bits;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
/* compiled from: ByteOrder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u0004*\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0016\u0010\u0000\u001a\u00020\u0007*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"reverseByteOrder", "Lkotlin/UInt;", "reverseByteOrder-WZ4Q5Ns", "(I)I", "Lkotlin/ULong;", "reverseByteOrder-VKZWuLQ", "(J)J", "Lkotlin/UShort;", "reverseByteOrder-xj2QHRw", "(S)S", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteOrderKt {
    @ExperimentalUnsignedTypes
    /* renamed from: reverseByteOrder-VKZWuLQ  reason: not valid java name */
    public static final long m12328reverseByteOrderVKZWuLQ(long j) {
        return ULong.m10534constructorimpl(Long.reverseBytes(j));
    }

    @ExperimentalUnsignedTypes
    /* renamed from: reverseByteOrder-WZ4Q5Ns  reason: not valid java name */
    public static final int m12329reverseByteOrderWZ4Q5Ns(int i) {
        return UInt.m10464constructorimpl(Integer.reverseBytes(i));
    }

    @ExperimentalUnsignedTypes
    /* renamed from: reverseByteOrder-xj2QHRw  reason: not valid java name */
    public static final short m12330reverseByteOrderxj2QHRw(short s) {
        return UShort.m10632constructorimpl(Short.reverseBytes(s));
    }
}
