package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: typeParameterUtils.kt */
/* loaded from: classes2.dex */
public final class TypeParameterUtilsKt {
    @Nullable
    public static final PossiblyInnerType buildPossiblyInnerType(@NotNull KotlinType buildPossiblyInnerType) {
        Intrinsics.checkParameterIsNotNull(buildPossiblyInnerType, "$this$buildPossiblyInnerType");
        ClassifierDescriptor mo12085getDeclarationDescriptor = buildPossiblyInnerType.mo12131getConstructor().mo12085getDeclarationDescriptor();
        if (!(mo12085getDeclarationDescriptor instanceof ClassifierDescriptorWithTypeParameters)) {
            mo12085getDeclarationDescriptor = null;
        }
        return buildPossiblyInnerType(buildPossiblyInnerType, (ClassifierDescriptorWithTypeParameters) mo12085getDeclarationDescriptor, 0);
    }

    private static final CapturedTypeParameterDescriptor capturedCopyForInnerDeclaration(@NotNull TypeParameterDescriptor typeParameterDescriptor, DeclarationDescriptor declarationDescriptor, int i) {
        return new CapturedTypeParameterDescriptor(typeParameterDescriptor, declarationDescriptor, i);
    }

    @NotNull
    public static final List<TypeParameterDescriptor> computeConstructorTypeParameters(@NotNull ClassifierDescriptorWithTypeParameters computeConstructorTypeParameters) {
        Sequence takeWhile;
        Sequence filter;
        Sequence flatMap;
        List list;
        List<TypeParameterDescriptor> list2;
        DeclarationDescriptor declarationDescriptor;
        List<TypeParameterDescriptor> plus;
        int collectionSizeOrDefault;
        List<TypeParameterDescriptor> plus2;
        TypeConstructor mo11528getTypeConstructor;
        Intrinsics.checkParameterIsNotNull(computeConstructorTypeParameters, "$this$computeConstructorTypeParameters");
        List<TypeParameterDescriptor> declaredTypeParameters = computeConstructorTypeParameters.getDeclaredTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters, "declaredTypeParameters");
        if (computeConstructorTypeParameters.isInner() || (computeConstructorTypeParameters.mo11607getContainingDeclaration() instanceof CallableDescriptor)) {
            takeWhile = SequencesKt___SequencesKt.takeWhile(DescriptorUtilsKt.getParents(computeConstructorTypeParameters), TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$1.INSTANCE);
            filter = SequencesKt___SequencesKt.filter(takeWhile, TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$2.INSTANCE);
            flatMap = SequencesKt___SequencesKt.flatMap(filter, TypeParameterUtilsKt$computeConstructorTypeParameters$parametersFromContainingFunctions$3.INSTANCE);
            list = SequencesKt___SequencesKt.toList(flatMap);
            Iterator<DeclarationDescriptor> it2 = DescriptorUtilsKt.getParents(computeConstructorTypeParameters).iterator();
            while (true) {
                list2 = null;
                if (!it2.hasNext()) {
                    declarationDescriptor = null;
                    break;
                }
                declarationDescriptor = it2.next();
                if (declarationDescriptor instanceof ClassDescriptor) {
                    break;
                }
            }
            ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
            if (classDescriptor != null && (mo11528getTypeConstructor = classDescriptor.mo11528getTypeConstructor()) != null) {
                list2 = mo11528getTypeConstructor.getParameters();
            }
            if (list2 == null) {
                list2 = CollectionsKt__CollectionsKt.emptyList();
            }
            if (list.isEmpty() && list2.isEmpty()) {
                List<TypeParameterDescriptor> declaredTypeParameters2 = computeConstructorTypeParameters.getDeclaredTypeParameters();
                Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters2, "declaredTypeParameters");
                return declaredTypeParameters2;
            }
            plus = CollectionsKt___CollectionsKt.plus((Collection) list, (Iterable) list2);
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(plus, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (TypeParameterDescriptor it3 : plus) {
                Intrinsics.checkExpressionValueIsNotNull(it3, "it");
                arrayList.add(capturedCopyForInnerDeclaration(it3, computeConstructorTypeParameters, declaredTypeParameters.size()));
            }
            plus2 = CollectionsKt___CollectionsKt.plus((Collection) declaredTypeParameters, (Iterable) arrayList);
            return plus2;
        }
        return declaredTypeParameters;
    }

    private static final PossiblyInnerType buildPossiblyInnerType(@NotNull KotlinType kotlinType, ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters, int i) {
        ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters2 = null;
        if (classifierDescriptorWithTypeParameters == null || ErrorUtils.isError(classifierDescriptorWithTypeParameters)) {
            return null;
        }
        int size = classifierDescriptorWithTypeParameters.getDeclaredTypeParameters().size() + i;
        if (!classifierDescriptorWithTypeParameters.isInner()) {
            boolean z = size == kotlinType.getArguments().size() || DescriptorUtils.isLocal(classifierDescriptorWithTypeParameters);
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError((kotlinType.getArguments().size() - size) + " trailing arguments were found in " + kotlinType + " type");
            }
            return new PossiblyInnerType(classifierDescriptorWithTypeParameters, kotlinType.getArguments().subList(i, kotlinType.getArguments().size()), null);
        }
        List<TypeProjection> subList = kotlinType.getArguments().subList(i, size);
        DeclarationDescriptor mo11607getContainingDeclaration = classifierDescriptorWithTypeParameters.mo11607getContainingDeclaration();
        if (mo11607getContainingDeclaration instanceof ClassifierDescriptorWithTypeParameters) {
            classifierDescriptorWithTypeParameters2 = mo11607getContainingDeclaration;
        }
        return new PossiblyInnerType(classifierDescriptorWithTypeParameters, subList, buildPossiblyInnerType(kotlinType, classifierDescriptorWithTypeParameters2, size));
    }
}
