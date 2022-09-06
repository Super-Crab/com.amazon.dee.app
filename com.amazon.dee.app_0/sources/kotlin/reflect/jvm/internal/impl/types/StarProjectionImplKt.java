package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StarProjectionImpl.kt */
/* loaded from: classes4.dex */
public final class StarProjectionImplKt {
    @NotNull
    public static final KotlinType starProjectionType(@NotNull TypeParameterDescriptor starProjectionType) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(starProjectionType, "$this$starProjectionType");
        DeclarationDescriptor mo11607getContainingDeclaration = starProjectionType.mo11607getContainingDeclaration();
        if (mo11607getContainingDeclaration != null) {
            TypeConstructor mo11528getTypeConstructor = ((ClassifierDescriptorWithTypeParameters) mo11607getContainingDeclaration).mo11528getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "classDescriptor.typeConstructor");
            List<TypeParameterDescriptor> parameters = mo11528getTypeConstructor.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "classDescriptor.typeConstructor.parameters");
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters, 10);
            final ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (TypeParameterDescriptor it2 : parameters) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                arrayList.add(it2.mo11528getTypeConstructor());
            }
            TypeSubstitutor create = TypeSubstitutor.create(new TypeConstructorSubstitution() { // from class: kotlin.reflect.jvm.internal.impl.types.StarProjectionImplKt$starProjectionType$1
                @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution
                @Nullable
                public TypeProjection get(@NotNull TypeConstructor key) {
                    Intrinsics.checkParameterIsNotNull(key, "key");
                    if (arrayList.contains(key)) {
                        ClassifierDescriptor mo12085getDeclarationDescriptor = key.mo12085getDeclarationDescriptor();
                        if (mo12085getDeclarationDescriptor == null) {
                            throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
                        }
                        return TypeUtils.makeStarProjection((TypeParameterDescriptor) mo12085getDeclarationDescriptor);
                    }
                    return null;
                }
            });
            List<KotlinType> upperBounds = starProjectionType.getUpperBounds();
            Intrinsics.checkExpressionValueIsNotNull(upperBounds, "this.upperBounds");
            KotlinType substitute = create.substitute((KotlinType) CollectionsKt.first((List<? extends Object>) upperBounds), Variance.OUT_VARIANCE);
            if (substitute != null) {
                return substitute;
            }
            SimpleType defaultBound = DescriptorUtilsKt.getBuiltIns(starProjectionType).getDefaultBound();
            Intrinsics.checkExpressionValueIsNotNull(defaultBound, "builtIns.defaultBound");
            return defaultBound;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassifierDescriptorWithTypeParameters");
    }
}
