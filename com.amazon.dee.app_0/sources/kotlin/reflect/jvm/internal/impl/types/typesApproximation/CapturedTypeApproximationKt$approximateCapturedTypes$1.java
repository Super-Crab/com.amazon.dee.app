package kotlin.reflect.jvm.internal.impl.types.typesApproximation;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CapturedTypeApproximation.kt */
/* loaded from: classes4.dex */
public final class CapturedTypeApproximationKt$approximateCapturedTypes$1 extends Lambda implements Function1<KotlinType, KotlinType> {
    final /* synthetic */ KotlinType $type;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CapturedTypeApproximationKt$approximateCapturedTypes$1(KotlinType kotlinType) {
        super(1);
        this.$type = kotlinType;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final KotlinType mo12165invoke(@NotNull KotlinType makeNullableIfNeeded) {
        Intrinsics.checkParameterIsNotNull(makeNullableIfNeeded, "$this$makeNullableIfNeeded");
        KotlinType makeNullableIfNeeded2 = TypeUtils.makeNullableIfNeeded(makeNullableIfNeeded, this.$type.isMarkedNullable());
        Intrinsics.checkExpressionValueIsNotNull(makeNullableIfNeeded2, "TypeUtils.makeNullableIf…s, type.isMarkedNullable)");
        return makeNullableIfNeeded2;
    }
}
