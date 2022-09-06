package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
/* compiled from: CapturedTypeApproximation.kt */
/* loaded from: classes4.dex */
final class CapturedTypeApproximationKt$toTypeProjection$2 extends Lambda implements Function1<Variance, Variance> {
    final /* synthetic */ TypeArgument $this_toTypeProjection;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CapturedTypeApproximationKt$toTypeProjection$2(TypeArgument typeArgument) {
        super(1);
        this.$this_toTypeProjection = typeArgument;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Variance mo12165invoke(@NotNull Variance variance) {
        Intrinsics.checkParameterIsNotNull(variance, "variance");
        return variance == this.$this_toTypeProjection.getTypeParameter().getVariance() ? Variance.INVARIANT : variance;
    }
}
