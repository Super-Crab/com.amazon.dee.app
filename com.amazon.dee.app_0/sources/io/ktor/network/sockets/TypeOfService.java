package io.ktor.network.sockets;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeOfService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086@\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0014\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005B\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0007J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0012\u0010\b\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0002\u001a\u00020\u0006ø\u0001\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lio/ktor/network/sockets/TypeOfService;", "", "value", "", "constructor-impl", "(I)B", "Lkotlin/UByte;", "(B)B", "intValue", "getIntValue-impl", "(B)I", "getValue", "()B", "B", "equals", "", "other", "hashCode", "toString", "", "Companion", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class TypeOfService {
    private final byte value;
    public static final Companion Companion = new Companion(null);
    private static final byte UNDEFINED = m10314constructorimpl((byte) 0);
    private static final byte IPTOS_LOWCOST = m10314constructorimpl((byte) 2);
    private static final byte IPTOS_RELIABILITY = m10314constructorimpl((byte) 4);
    private static final byte IPTOS_THROUGHPUT = m10314constructorimpl((byte) 8);
    private static final byte IPTOS_LOWDELAY = m10314constructorimpl((byte) 16);

    /* compiled from: TypeOfService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\b\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0016\u0010\n\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u0016\u0010\f\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u0016\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000f\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lio/ktor/network/sockets/TypeOfService$Companion;", "", "()V", "IPTOS_LOWCOST", "Lio/ktor/network/sockets/TypeOfService;", "getIPTOS_LOWCOST", "()B", "B", "IPTOS_LOWDELAY", "getIPTOS_LOWDELAY", "IPTOS_RELIABILITY", "getIPTOS_RELIABILITY", "IPTOS_THROUGHPUT", "getIPTOS_THROUGHPUT", "UNDEFINED", "getUNDEFINED", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public final byte getIPTOS_LOWCOST() {
            return TypeOfService.IPTOS_LOWCOST;
        }

        public final byte getIPTOS_LOWDELAY() {
            return TypeOfService.IPTOS_LOWDELAY;
        }

        public final byte getIPTOS_RELIABILITY() {
            return TypeOfService.IPTOS_RELIABILITY;
        }

        public final byte getIPTOS_THROUGHPUT() {
            return TypeOfService.IPTOS_THROUGHPUT;
        }

        public final byte getUNDEFINED() {
            return TypeOfService.UNDEFINED;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private /* synthetic */ TypeOfService(byte b) {
        this.value = b;
    }

    @NotNull
    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ TypeOfService m10313boximpl(byte b) {
        return new TypeOfService(b);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static byte m10314constructorimpl(byte b) {
        return b;
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static byte m10315constructorimpl(int i) {
        return m10314constructorimpl(UByte.m10396constructorimpl((byte) i));
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m10316equalsimpl(byte b, @Nullable Object obj) {
        if (obj instanceof TypeOfService) {
            if (b == ((TypeOfService) obj).m10321unboximpl()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m10317equalsimpl0(byte b, byte b2) {
        throw null;
    }

    /* renamed from: getIntValue-impl  reason: not valid java name */
    public static final int m10318getIntValueimpl(byte b) {
        return b & 255;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m10319hashCodeimpl(byte b) {
        return b;
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m10320toStringimpl(byte b) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TypeOfService(value=");
        outline107.append(UByte.m10431toStringimpl(b));
        outline107.append(")");
        return outline107.toString();
    }

    public boolean equals(Object obj) {
        return m10316equalsimpl(this.value, obj);
    }

    public final byte getValue() {
        return this.value;
    }

    public int hashCode() {
        return m10319hashCodeimpl(this.value);
    }

    public String toString() {
        return m10320toStringimpl(this.value);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ byte m10321unboximpl() {
        return this.value;
    }
}
