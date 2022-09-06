package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: KTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\u0014\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0001H\u0007¨\u0006\u0007"}, d2 = {"isSubtypeOf", "", "Lkotlin/reflect/KType;", "other", "isSupertypeOf", "withNullability", "nullable", "kotlin-reflection"}, k = 2, mv = {1, 1, 16})
@JvmName(name = "KTypes")
/* loaded from: classes2.dex */
public final class KTypes {
    @SinceKotlin(version = "1.1")
    public static final boolean isSubtypeOf(@NotNull KType isSubtypeOf, @NotNull KType other) {
        Intrinsics.checkParameterIsNotNull(isSubtypeOf, "$this$isSubtypeOf");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return TypeUtilsKt.isSubtypeOf(((KTypeImpl) isSubtypeOf).getType(), ((KTypeImpl) other).getType());
    }

    @SinceKotlin(version = "1.1")
    public static final boolean isSupertypeOf(@NotNull KType isSupertypeOf, @NotNull KType other) {
        Intrinsics.checkParameterIsNotNull(isSupertypeOf, "$this$isSupertypeOf");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return isSubtypeOf(other, isSupertypeOf);
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final KType withNullability(@NotNull KType withNullability, boolean z) {
        Intrinsics.checkParameterIsNotNull(withNullability, "$this$withNullability");
        if (withNullability.isMarkedNullable()) {
            if (z) {
                return withNullability;
            }
            KotlinType makeNotNullable = TypeUtils.makeNotNullable(((KTypeImpl) withNullability).getType());
            Intrinsics.checkExpressionValueIsNotNull(makeNotNullable, "TypeUtils.makeNotNullabl…(this as KTypeImpl).type)");
            return new KTypeImpl(makeNotNullable, new KTypes$withNullability$1(withNullability));
        }
        KotlinType type = ((KTypeImpl) withNullability).getType();
        if (FlexibleTypesKt.isFlexible(type)) {
            KotlinType makeNullableAsSpecified = TypeUtils.makeNullableAsSpecified(type, z);
            Intrinsics.checkExpressionValueIsNotNull(makeNullableAsSpecified, "TypeUtils.makeNullableAs…ied(kotlinType, nullable)");
            return new KTypeImpl(makeNullableAsSpecified, new KTypes$withNullability$2(withNullability));
        } else if (!z) {
            return withNullability;
        } else {
            KotlinType makeNullable = TypeUtils.makeNullable(type);
            Intrinsics.checkExpressionValueIsNotNull(makeNullable, "TypeUtils.makeNullable(kotlinType)");
            return new KTypeImpl(makeNullable, new KTypes$withNullability$3(withNullability));
        }
    }
}
