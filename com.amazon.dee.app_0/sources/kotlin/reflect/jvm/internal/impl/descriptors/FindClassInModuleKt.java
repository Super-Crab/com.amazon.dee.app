package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: findClassInModule.kt */
/* loaded from: classes2.dex */
public final class FindClassInModuleKt {
    @Nullable
    public static final ClassDescriptor findClassAcrossModuleDependencies(@NotNull ModuleDescriptor findClassAcrossModuleDependencies, @NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(findClassAcrossModuleDependencies, "$this$findClassAcrossModuleDependencies");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        ClassifierDescriptor findClassifierAcrossModuleDependencies = findClassifierAcrossModuleDependencies(findClassAcrossModuleDependencies, classId);
        if (!(findClassifierAcrossModuleDependencies instanceof ClassDescriptor)) {
            findClassifierAcrossModuleDependencies = null;
        }
        return (ClassDescriptor) findClassifierAcrossModuleDependencies;
    }

    @Nullable
    public static final ClassifierDescriptor findClassifierAcrossModuleDependencies(@NotNull ModuleDescriptor findClassifierAcrossModuleDependencies, @NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(findClassifierAcrossModuleDependencies, "$this$findClassifierAcrossModuleDependencies");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        FqName packageFqName = classId.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName, "classId.packageFqName");
        PackageViewDescriptor packageViewDescriptor = findClassifierAcrossModuleDependencies.getPackage(packageFqName);
        List<Name> pathSegments = classId.getRelativeClassName().pathSegments();
        Intrinsics.checkExpressionValueIsNotNull(pathSegments, "classId.relativeClassName.pathSegments()");
        MemberScope memberScope = packageViewDescriptor.getMemberScope();
        Object first = CollectionsKt.first((List<? extends Object>) pathSegments);
        Intrinsics.checkExpressionValueIsNotNull(first, "segments.first()");
        ClassifierDescriptor mo12030getContributedClassifier = memberScope.mo12030getContributedClassifier((Name) first, NoLookupLocation.FROM_DESERIALIZATION);
        if (mo12030getContributedClassifier != null) {
            for (Name name : pathSegments.subList(1, pathSegments.size())) {
                if (!(mo12030getContributedClassifier instanceof ClassDescriptor)) {
                    return null;
                }
                MemberScope unsubstitutedInnerClassesScope = ((ClassDescriptor) mo12030getContributedClassifier).getUnsubstitutedInnerClassesScope();
                Intrinsics.checkExpressionValueIsNotNull(name, "name");
                ClassifierDescriptor mo12030getContributedClassifier2 = unsubstitutedInnerClassesScope.mo12030getContributedClassifier(name, NoLookupLocation.FROM_DESERIALIZATION);
                if (!(mo12030getContributedClassifier2 instanceof ClassDescriptor)) {
                    mo12030getContributedClassifier2 = null;
                }
                mo12030getContributedClassifier = (ClassDescriptor) mo12030getContributedClassifier2;
                if (mo12030getContributedClassifier == null) {
                    return null;
                }
            }
            return mo12030getContributedClassifier;
        }
        return null;
    }

    @NotNull
    public static final ClassDescriptor findNonGenericClassAcrossDependencies(@NotNull ModuleDescriptor findNonGenericClassAcrossDependencies, @NotNull ClassId classId, @NotNull NotFoundClasses notFoundClasses) {
        Sequence generateSequence;
        Sequence map;
        List<Integer> list;
        Intrinsics.checkParameterIsNotNull(findNonGenericClassAcrossDependencies, "$this$findNonGenericClassAcrossDependencies");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        Intrinsics.checkParameterIsNotNull(notFoundClasses, "notFoundClasses");
        ClassDescriptor findClassAcrossModuleDependencies = findClassAcrossModuleDependencies(findNonGenericClassAcrossDependencies, classId);
        if (findClassAcrossModuleDependencies != null) {
            return findClassAcrossModuleDependencies;
        }
        generateSequence = SequencesKt__SequencesKt.generateSequence(classId, FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$1.INSTANCE);
        map = SequencesKt___SequencesKt.map(generateSequence, FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$2.INSTANCE);
        list = SequencesKt___SequencesKt.toList(map);
        return notFoundClasses.getClass(classId, list);
    }

    @Nullable
    public static final TypeAliasDescriptor findTypeAliasAcrossModuleDependencies(@NotNull ModuleDescriptor findTypeAliasAcrossModuleDependencies, @NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(findTypeAliasAcrossModuleDependencies, "$this$findTypeAliasAcrossModuleDependencies");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        ClassifierDescriptor findClassifierAcrossModuleDependencies = findClassifierAcrossModuleDependencies(findTypeAliasAcrossModuleDependencies, classId);
        if (!(findClassifierAcrossModuleDependencies instanceof TypeAliasDescriptor)) {
            findClassifierAcrossModuleDependencies = null;
        }
        return (TypeAliasDescriptor) findClassifierAcrossModuleDependencies;
    }
}
