package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: KotlinType.kt */
/* loaded from: classes4.dex */
public final class KotlinTypeKt {
    public static final boolean isError(@NotNull KotlinType isError) {
        Intrinsics.checkParameterIsNotNull(isError, "$this$isError");
        UnwrappedType unwrap = isError.unwrap();
        return (unwrap instanceof ErrorType) || ((unwrap instanceof FlexibleType) && (((FlexibleType) unwrap).getDelegate() instanceof ErrorType));
    }
}
