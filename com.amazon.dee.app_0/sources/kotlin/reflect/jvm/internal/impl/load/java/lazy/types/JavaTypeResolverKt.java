package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImplKt;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JavaTypeResolver.kt */
/* loaded from: classes3.dex */
public final class JavaTypeResolverKt {
    private static final FqName JAVA_LANG_CLASS_FQ_NAME = new FqName("java.lang.Class");

    public static final /* synthetic */ FqName access$getJAVA_LANG_CLASS_FQ_NAME$p() {
        return JAVA_LANG_CLASS_FQ_NAME;
    }

    @NotNull
    public static final KotlinType getErasedUpperBound(@NotNull TypeParameterDescriptor getErasedUpperBound, @Nullable TypeParameterDescriptor typeParameterDescriptor, @NotNull Function0<? extends KotlinType> defaultValue) {
        Intrinsics.checkParameterIsNotNull(getErasedUpperBound, "$this$getErasedUpperBound");
        Intrinsics.checkParameterIsNotNull(defaultValue, "defaultValue");
        if (getErasedUpperBound == typeParameterDescriptor) {
            return defaultValue.mo12560invoke();
        }
        List<KotlinType> upperBounds = getErasedUpperBound.getUpperBounds();
        Intrinsics.checkExpressionValueIsNotNull(upperBounds, "upperBounds");
        KotlinType firstUpperBound = (KotlinType) CollectionsKt.first((List<? extends Object>) upperBounds);
        if (firstUpperBound.mo12131getConstructor().mo12085getDeclarationDescriptor() instanceof ClassDescriptor) {
            Intrinsics.checkExpressionValueIsNotNull(firstUpperBound, "firstUpperBound");
            return TypeUtilsKt.replaceArgumentsWithStarProjections(firstUpperBound);
        }
        if (typeParameterDescriptor != null) {
            getErasedUpperBound = typeParameterDescriptor;
        }
        ClassifierDescriptor mo12085getDeclarationDescriptor = firstUpperBound.mo12131getConstructor().mo12085getDeclarationDescriptor();
        if (mo12085getDeclarationDescriptor == null) {
            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
        }
        while (true) {
            TypeParameterDescriptor typeParameterDescriptor2 = (TypeParameterDescriptor) mo12085getDeclarationDescriptor;
            if (!Intrinsics.areEqual(typeParameterDescriptor2, getErasedUpperBound)) {
                List<KotlinType> upperBounds2 = typeParameterDescriptor2.getUpperBounds();
                Intrinsics.checkExpressionValueIsNotNull(upperBounds2, "current.upperBounds");
                KotlinType nextUpperBound = (KotlinType) CollectionsKt.first((List<? extends Object>) upperBounds2);
                if (nextUpperBound.mo12131getConstructor().mo12085getDeclarationDescriptor() instanceof ClassDescriptor) {
                    Intrinsics.checkExpressionValueIsNotNull(nextUpperBound, "nextUpperBound");
                    return TypeUtilsKt.replaceArgumentsWithStarProjections(nextUpperBound);
                }
                mo12085getDeclarationDescriptor = nextUpperBound.mo12131getConstructor().mo12085getDeclarationDescriptor();
                if (mo12085getDeclarationDescriptor == null) {
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
                }
            } else {
                return defaultValue.mo12560invoke();
            }
        }
    }

    public static /* synthetic */ KotlinType getErasedUpperBound$default(TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            typeParameterDescriptor2 = null;
        }
        if ((i & 2) != 0) {
            function0 = new JavaTypeResolverKt$getErasedUpperBound$1(typeParameterDescriptor);
        }
        return getErasedUpperBound(typeParameterDescriptor, typeParameterDescriptor2, function0);
    }

    @NotNull
    public static final TypeProjection makeStarProjection(@NotNull TypeParameterDescriptor typeParameter, @NotNull JavaTypeAttributes attr) {
        Intrinsics.checkParameterIsNotNull(typeParameter, "typeParameter");
        Intrinsics.checkParameterIsNotNull(attr, "attr");
        if (attr.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE) {
            return new TypeProjectionImpl(StarProjectionImplKt.starProjectionType(typeParameter));
        }
        return new StarProjectionImpl(typeParameter);
    }

    @NotNull
    public static final JavaTypeAttributes toAttributes(@NotNull TypeUsage toAttributes, boolean z, @Nullable TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(toAttributes, "$this$toAttributes");
        return new JavaTypeAttributes(toAttributes, null, z, typeParameterDescriptor, 2, null);
    }

    public static /* synthetic */ JavaTypeAttributes toAttributes$default(TypeUsage typeUsage, boolean z, TypeParameterDescriptor typeParameterDescriptor, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            typeParameterDescriptor = null;
        }
        return toAttributes(typeUsage, z, typeParameterDescriptor);
    }
}
