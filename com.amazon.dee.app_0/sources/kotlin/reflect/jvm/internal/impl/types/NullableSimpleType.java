package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: KotlinTypeFactory.kt */
/* loaded from: classes4.dex */
final class NullableSimpleType extends DelegatingSimpleTypeImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NullableSimpleType(@NotNull SimpleType delegate) {
        super(delegate);
        Intrinsics.checkParameterIsNotNull(delegate, "delegate");
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    public boolean isMarkedNullable() {
        return true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    @NotNull
    /* renamed from: replaceDelegate  reason: collision with other method in class */
    public NullableSimpleType mo12116replaceDelegate(@NotNull SimpleType delegate) {
        Intrinsics.checkParameterIsNotNull(delegate, "delegate");
        return new NullableSimpleType(delegate);
    }
}
