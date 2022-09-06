package kotlinx.io.core;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* JADX WARN: Init of enum BIG_ENDIAN can be incorrect */
/* JADX WARN: Init of enum LITTLE_ENDIAN can be incorrect */
/* compiled from: ByteOrderJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, d2 = {"Lkotlinx/io/core/ByteOrder;", "", "nioOrder", "Ljava/nio/ByteOrder;", "(Ljava/lang/String;ILjava/nio/ByteOrder;)V", "getNioOrder", "()Ljava/nio/ByteOrder;", "BIG_ENDIAN", "LITTLE_ENDIAN", "Companion", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public enum ByteOrder {
    BIG_ENDIAN(r2),
    LITTLE_ENDIAN(r2);
    
    public static final Companion Companion;

    /* renamed from: native  reason: not valid java name */
    private static final ByteOrder f5native;
    @NotNull
    private final java.nio.ByteOrder nioOrder;

    /* compiled from: ByteOrderJVM.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlinx/io/core/ByteOrder$Companion;", "", "()V", "native", "Lkotlinx/io/core/ByteOrder;", "nativeOrder", "of", "nioOrder", "Ljava/nio/ByteOrder;", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final ByteOrder nativeOrder() {
            return ByteOrder.f5native;
        }

        @NotNull
        public final ByteOrder of(@NotNull java.nio.ByteOrder nioOrder) {
            ByteOrder orderOf;
            Intrinsics.checkParameterIsNotNull(nioOrder, "nioOrder");
            orderOf = ByteOrderJVMKt.orderOf(nioOrder);
            return orderOf;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        ByteOrder orderOf;
        Intrinsics.checkExpressionValueIsNotNull(java.nio.ByteOrder.BIG_ENDIAN, "java.nio.ByteOrder.BIG_ENDIAN");
        Intrinsics.checkExpressionValueIsNotNull(java.nio.ByteOrder.LITTLE_ENDIAN, "java.nio.ByteOrder.LITTLE_ENDIAN");
        Companion = new Companion(null);
        java.nio.ByteOrder nativeOrder = java.nio.ByteOrder.nativeOrder();
        Intrinsics.checkExpressionValueIsNotNull(nativeOrder, "java.nio.ByteOrder.nativeOrder()");
        orderOf = ByteOrderJVMKt.orderOf(nativeOrder);
        f5native = orderOf;
    }

    ByteOrder(java.nio.ByteOrder byteOrder) {
        this.nioOrder = byteOrder;
    }

    @NotNull
    public final java.nio.ByteOrder getNioOrder() {
        return this.nioOrder;
    }
}
