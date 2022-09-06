package kotlinx.io.bits;

import kotlin.Metadata;
/* compiled from: ByteOrderJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0086\b\u001a\r\u0010\u0000\u001a\u00020\u0002*\u00020\u0002H\u0086\b\u001a\r\u0010\u0000\u001a\u00020\u0003*\u00020\u0003H\u0086\b\u001a\r\u0010\u0000\u001a\u00020\u0004*\u00020\u0004H\u0086\b\u001a\r\u0010\u0000\u001a\u00020\u0005*\u00020\u0005H\u0086\b¨\u0006\u0006"}, d2 = {"reverseByteOrder", "", "", "", "", "", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteOrderJVMKt {
    public static final short reverseByteOrder(short s) {
        return Short.reverseBytes(s);
    }

    public static final int reverseByteOrder(int i) {
        return Integer.reverseBytes(i);
    }

    public static final long reverseByteOrder(long j) {
        return Long.reverseBytes(j);
    }

    public static final float reverseByteOrder(float f) {
        return Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(f)));
    }

    public static final double reverseByteOrder(double d) {
        return Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(d)));
    }
}
