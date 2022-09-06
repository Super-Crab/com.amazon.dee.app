package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefinerKt;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DescriptorUtils.kt */
/* loaded from: classes4.dex */
public final class DescriptorUtilsKt {
    private static final Name RETENTION_PARAMETER_NAME;

    static {
        Name identifier = Name.identifier("value");
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(\"value\")");
        RETENTION_PARAMETER_NAME = identifier;
    }

    @NotNull
    public static final Collection<ClassDescriptor> computeSealedSubclasses(@NotNull ClassDescriptor sealedClass) {
        List emptyList;
        Intrinsics.checkParameterIsNotNull(sealedClass, "sealedClass");
        if (sealedClass.getModality() != Modality.SEALED) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        DescriptorUtilsKt$computeSealedSubclasses$1 descriptorUtilsKt$computeSealedSubclasses$1 = new DescriptorUtilsKt$computeSealedSubclasses$1(sealedClass, linkedHashSet);
        DeclarationDescriptor mo11607getContainingDeclaration = sealedClass.mo11607getContainingDeclaration();
        Intrinsics.checkExpressionValueIsNotNull(mo11607getContainingDeclaration, "sealedClass.containingDeclaration");
        if (mo11607getContainingDeclaration instanceof PackageFragmentDescriptor) {
            descriptorUtilsKt$computeSealedSubclasses$1.invoke(((PackageFragmentDescriptor) mo11607getContainingDeclaration).mo11676getMemberScope(), false);
        }
        MemberScope unsubstitutedInnerClassesScope = sealedClass.getUnsubstitutedInnerClassesScope();
        Intrinsics.checkExpressionValueIsNotNull(unsubstitutedInnerClassesScope, "sealedClass.unsubstitutedInnerClassesScope");
        descriptorUtilsKt$computeSealedSubclasses$1.invoke(unsubstitutedInnerClassesScope, true);
        return linkedHashSet;
    }

    public static final boolean declaresOrInheritsDefaultValue(@NotNull ValueParameterDescriptor declaresOrInheritsDefaultValue) {
        List listOf;
        Intrinsics.checkParameterIsNotNull(declaresOrInheritsDefaultValue, "$this$declaresOrInheritsDefaultValue");
        listOf = CollectionsKt__CollectionsJVMKt.listOf(declaresOrInheritsDefaultValue);
        Boolean ifAny = DFS.ifAny(listOf, DescriptorUtilsKt$declaresOrInheritsDefaultValue$1.INSTANCE, DescriptorUtilsKt$declaresOrInheritsDefaultValue$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(ifAny, "DFS.ifAny(\n        listO…eclaresDefaultValue\n    )");
        return ifAny.booleanValue();
    }

    @Nullable
    public static final ConstantValue<?> firstArgument(@NotNull AnnotationDescriptor firstArgument) {
        Intrinsics.checkParameterIsNotNull(firstArgument, "$this$firstArgument");
        return (ConstantValue) CollectionsKt.firstOrNull(firstArgument.getAllValueArguments().values());
    }

    @Nullable
    public static final CallableMemberDescriptor firstOverridden(@NotNull CallableMemberDescriptor firstOverridden, final boolean z, @NotNull final Function1<? super CallableMemberDescriptor, Boolean> predicate) {
        List listOf;
        Intrinsics.checkParameterIsNotNull(firstOverridden, "$this$firstOverridden");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = null;
        listOf = CollectionsKt__CollectionsJVMKt.listOf(firstOverridden);
        return (CallableMemberDescriptor) DFS.dfs(listOf, new DFS.Neighbors<N>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$firstOverridden$1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            @NotNull
            public final Iterable<CallableMemberDescriptor> getNeighbors(CallableMemberDescriptor callableMemberDescriptor) {
                List emptyList;
                Collection<? extends CallableMemberDescriptor> overriddenDescriptors;
                if (z) {
                    callableMemberDescriptor = callableMemberDescriptor != null ? callableMemberDescriptor.mo11613getOriginal() : null;
                }
                if (callableMemberDescriptor == null || (overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors()) == null) {
                    emptyList = CollectionsKt__CollectionsKt.emptyList();
                    return emptyList;
                }
                return overriddenDescriptors;
            }
        }, new DFS.AbstractNodeHandler<CallableMemberDescriptor, CallableMemberDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$firstOverridden$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public void afterChildren(@NotNull CallableMemberDescriptor current) {
                Intrinsics.checkParameterIsNotNull(current, "current");
                if (((CallableMemberDescriptor) Ref.ObjectRef.this.element) != null || !((Boolean) predicate.mo12165invoke(current)).booleanValue()) {
                    return;
                }
                Ref.ObjectRef.this.element = current;
            }

            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(@NotNull CallableMemberDescriptor current) {
                Intrinsics.checkParameterIsNotNull(current, "current");
                return ((CallableMemberDescriptor) Ref.ObjectRef.this.element) == null;
            }

            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            @Nullable
            /* renamed from: result  reason: collision with other method in class */
            public CallableMemberDescriptor mo12145result() {
                return (CallableMemberDescriptor) Ref.ObjectRef.this.element;
            }
        });
    }

    public static /* synthetic */ CallableMemberDescriptor firstOverridden$default(CallableMemberDescriptor callableMemberDescriptor, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return firstOverridden(callableMemberDescriptor, z, function1);
    }

    @Nullable
    public static final FqName fqNameOrNull(@NotNull DeclarationDescriptor fqNameOrNull) {
        Intrinsics.checkParameterIsNotNull(fqNameOrNull, "$this$fqNameOrNull");
        FqNameUnsafe fqNameUnsafe = getFqNameUnsafe(fqNameOrNull);
        if (!fqNameUnsafe.isSafe()) {
            fqNameUnsafe = null;
        }
        if (fqNameUnsafe != null) {
            return fqNameUnsafe.toSafe();
        }
        return null;
    }

    @Nullable
    public static final ClassDescriptor getAnnotationClass(@NotNull AnnotationDescriptor annotationClass) {
        Intrinsics.checkParameterIsNotNull(annotationClass, "$this$annotationClass");
        ClassifierDescriptor mo12085getDeclarationDescriptor = annotationClass.mo11659getType().mo12131getConstructor().mo12085getDeclarationDescriptor();
        if (!(mo12085getDeclarationDescriptor instanceof ClassDescriptor)) {
            mo12085getDeclarationDescriptor = null;
        }
        return (ClassDescriptor) mo12085getDeclarationDescriptor;
    }

    @NotNull
    public static final KotlinBuiltIns getBuiltIns(@NotNull DeclarationDescriptor builtIns) {
        Intrinsics.checkParameterIsNotNull(builtIns, "$this$builtIns");
        return getModule(builtIns).getBuiltIns();
    }

    @Nullable
    public static final ClassId getClassId(@Nullable ClassifierDescriptor classifierDescriptor) {
        DeclarationDescriptor mo11607getContainingDeclaration;
        ClassId classId;
        if (classifierDescriptor == null || (mo11607getContainingDeclaration = classifierDescriptor.mo11607getContainingDeclaration()) == null) {
            return null;
        }
        if (mo11607getContainingDeclaration instanceof PackageFragmentDescriptor) {
            return new ClassId(((PackageFragmentDescriptor) mo11607getContainingDeclaration).getFqName(), classifierDescriptor.getName());
        }
        if ((mo11607getContainingDeclaration instanceof ClassifierDescriptorWithTypeParameters) && (classId = getClassId((ClassifierDescriptor) mo11607getContainingDeclaration)) != null) {
            return classId.createNestedClassId(classifierDescriptor.getName());
        }
        return null;
    }

    @NotNull
    public static final FqName getFqNameSafe(@NotNull DeclarationDescriptor fqNameSafe) {
        Intrinsics.checkParameterIsNotNull(fqNameSafe, "$this$fqNameSafe");
        FqName fqNameSafe2 = DescriptorUtils.getFqNameSafe(fqNameSafe);
        Intrinsics.checkExpressionValueIsNotNull(fqNameSafe2, "DescriptorUtils.getFqNameSafe(this)");
        return fqNameSafe2;
    }

    @NotNull
    public static final FqNameUnsafe getFqNameUnsafe(@NotNull DeclarationDescriptor fqNameUnsafe) {
        Intrinsics.checkParameterIsNotNull(fqNameUnsafe, "$this$fqNameUnsafe");
        FqNameUnsafe fqName = DescriptorUtils.getFqName(fqNameUnsafe);
        Intrinsics.checkExpressionValueIsNotNull(fqName, "DescriptorUtils.getFqName(this)");
        return fqName;
    }

    @NotNull
    public static final KotlinTypeRefiner getKotlinTypeRefiner(@NotNull ModuleDescriptor getKotlinTypeRefiner) {
        KotlinTypeRefiner kotlinTypeRefiner;
        Intrinsics.checkParameterIsNotNull(getKotlinTypeRefiner, "$this$getKotlinTypeRefiner");
        kotlin.reflect.jvm.internal.impl.types.checker.Ref ref = (kotlin.reflect.jvm.internal.impl.types.checker.Ref) getKotlinTypeRefiner.getCapability(KotlinTypeRefinerKt.getREFINER_CAPABILITY());
        return (ref == null || (kotlinTypeRefiner = (KotlinTypeRefiner) ref.getValue()) == null) ? KotlinTypeRefiner.Default.INSTANCE : kotlinTypeRefiner;
    }

    @NotNull
    public static final ModuleDescriptor getModule(@NotNull DeclarationDescriptor module) {
        Intrinsics.checkParameterIsNotNull(module, "$this$module");
        ModuleDescriptor containingModule = DescriptorUtils.getContainingModule(module);
        Intrinsics.checkExpressionValueIsNotNull(containingModule, "DescriptorUtils.getContainingModule(this)");
        return containingModule;
    }

    @NotNull
    public static final Sequence<DeclarationDescriptor> getParents(@NotNull DeclarationDescriptor parents) {
        Intrinsics.checkParameterIsNotNull(parents, "$this$parents");
        return SequencesKt.drop(getParentsWithSelf(parents), 1);
    }

    @NotNull
    public static final Sequence<DeclarationDescriptor> getParentsWithSelf(@NotNull DeclarationDescriptor parentsWithSelf) {
        Intrinsics.checkParameterIsNotNull(parentsWithSelf, "$this$parentsWithSelf");
        return SequencesKt.generateSequence(parentsWithSelf, DescriptorUtilsKt$parentsWithSelf$1.INSTANCE);
    }

    @NotNull
    public static final CallableMemberDescriptor getPropertyIfAccessor(@NotNull CallableMemberDescriptor propertyIfAccessor) {
        Intrinsics.checkParameterIsNotNull(propertyIfAccessor, "$this$propertyIfAccessor");
        if (propertyIfAccessor instanceof PropertyAccessorDescriptor) {
            PropertyDescriptor correspondingProperty = ((PropertyAccessorDescriptor) propertyIfAccessor).getCorrespondingProperty();
            Intrinsics.checkExpressionValueIsNotNull(correspondingProperty, "correspondingProperty");
            return correspondingProperty;
        }
        return propertyIfAccessor;
    }

    @Nullable
    public static final ClassDescriptor getSuperClassNotAny(@NotNull ClassDescriptor getSuperClassNotAny) {
        Intrinsics.checkParameterIsNotNull(getSuperClassNotAny, "$this$getSuperClassNotAny");
        for (KotlinType kotlinType : getSuperClassNotAny.getDefaultType().mo12131getConstructor().mo12135getSupertypes()) {
            if (!KotlinBuiltIns.isAnyOrNullableAny(kotlinType)) {
                ClassifierDescriptor mo12085getDeclarationDescriptor = kotlinType.mo12131getConstructor().mo12085getDeclarationDescriptor();
                if (DescriptorUtils.isClassOrEnumClass(mo12085getDeclarationDescriptor)) {
                    if (mo12085getDeclarationDescriptor == null) {
                        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                    }
                    return (ClassDescriptor) mo12085getDeclarationDescriptor;
                }
            }
        }
        return null;
    }

    public static final boolean isTypeRefinementEnabled(@NotNull ModuleDescriptor isTypeRefinementEnabled) {
        Intrinsics.checkParameterIsNotNull(isTypeRefinementEnabled, "$this$isTypeRefinementEnabled");
        kotlin.reflect.jvm.internal.impl.types.checker.Ref ref = (kotlin.reflect.jvm.internal.impl.types.checker.Ref) isTypeRefinementEnabled.getCapability(KotlinTypeRefinerKt.getREFINER_CAPABILITY());
        return (ref != null ? (KotlinTypeRefiner) ref.getValue() : null) != null;
    }

    @Nullable
    public static final ClassDescriptor resolveTopLevelClass(@NotNull ModuleDescriptor resolveTopLevelClass, @NotNull FqName topLevelClassFqName, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(resolveTopLevelClass, "$this$resolveTopLevelClass");
        Intrinsics.checkParameterIsNotNull(topLevelClassFqName, "topLevelClassFqName");
        Intrinsics.checkParameterIsNotNull(location, "location");
        boolean z = !topLevelClassFqName.isRoot();
        if (!_Assertions.ENABLED || z) {
            FqName parent = topLevelClassFqName.parent();
            Intrinsics.checkExpressionValueIsNotNull(parent, "topLevelClassFqName.parent()");
            MemberScope memberScope = resolveTopLevelClass.getPackage(parent).getMemberScope();
            Name shortName = topLevelClassFqName.shortName();
            Intrinsics.checkExpressionValueIsNotNull(shortName, "topLevelClassFqName.shortName()");
            ClassifierDescriptor mo12030getContributedClassifier = memberScope.mo12030getContributedClassifier(shortName, location);
            if (!(mo12030getContributedClassifier instanceof ClassDescriptor)) {
                mo12030getContributedClassifier = null;
            }
            return (ClassDescriptor) mo12030getContributedClassifier;
        }
        throw new AssertionError("Assertion failed");
    }
}
