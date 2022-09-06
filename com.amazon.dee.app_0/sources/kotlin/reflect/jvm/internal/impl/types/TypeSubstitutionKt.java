package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;
/* compiled from: TypeSubstitution.kt */
/* loaded from: classes4.dex */
public final class TypeSubstitutionKt {
    @NotNull
    public static final SimpleType asSimpleType(@NotNull KotlinType asSimpleType) {
        Intrinsics.checkParameterIsNotNull(asSimpleType, "$this$asSimpleType");
        UnwrappedType unwrap = asSimpleType.unwrap();
        if (!(unwrap instanceof SimpleType)) {
            unwrap = null;
        }
        SimpleType simpleType = (SimpleType) unwrap;
        if (simpleType != null) {
            return simpleType;
        }
        throw new IllegalStateException(("This is should be simple type: " + asSimpleType).toString());
    }

    @JvmOverloads
    @NotNull
    public static final KotlinType replace(@NotNull KotlinType replace, @NotNull List<? extends TypeProjection> newArguments, @NotNull Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(replace, "$this$replace");
        Intrinsics.checkParameterIsNotNull(newArguments, "newArguments");
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        if ((newArguments.isEmpty() || newArguments == replace.getArguments()) && newAnnotations == replace.mo12070getAnnotations()) {
            return replace;
        }
        UnwrappedType unwrap = replace.unwrap();
        if (unwrap instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrap;
            return KotlinTypeFactory.flexibleType(replace(flexibleType.getLowerBound(), newArguments, newAnnotations), replace(flexibleType.getUpperBound(), newArguments, newAnnotations));
        } else if (!(unwrap instanceof SimpleType)) {
            throw new NoWhenBranchMatchedException();
        } else {
            return replace((SimpleType) unwrap, newArguments, newAnnotations);
        }
    }

    public static /* synthetic */ KotlinType replace$default(KotlinType kotlinType, List list, Annotations annotations, int i, Object obj) {
        if ((i & 1) != 0) {
            list = kotlinType.getArguments();
        }
        if ((i & 2) != 0) {
            annotations = kotlinType.mo12070getAnnotations();
        }
        return replace(kotlinType, list, annotations);
    }

    public static /* synthetic */ SimpleType replace$default(SimpleType simpleType, List list, Annotations annotations, int i, Object obj) {
        if ((i & 1) != 0) {
            list = simpleType.getArguments();
        }
        if ((i & 2) != 0) {
            annotations = simpleType.mo12070getAnnotations();
        }
        return replace(simpleType, (List<? extends TypeProjection>) list, annotations);
    }

    @JvmOverloads
    @NotNull
    public static final SimpleType replace(@NotNull SimpleType replace, @NotNull List<? extends TypeProjection> newArguments, @NotNull Annotations newAnnotations) {
        Intrinsics.checkParameterIsNotNull(replace, "$this$replace");
        Intrinsics.checkParameterIsNotNull(newArguments, "newArguments");
        Intrinsics.checkParameterIsNotNull(newAnnotations, "newAnnotations");
        if (!newArguments.isEmpty() || newAnnotations != replace.mo12070getAnnotations()) {
            if (newArguments.isEmpty()) {
                return replace.mo12134replaceAnnotations(newAnnotations);
            }
            return KotlinTypeFactory.simpleType$default(newAnnotations, replace.mo12131getConstructor(), newArguments, replace.isMarkedNullable(), null, 16, null);
        }
        return replace;
    }
}
