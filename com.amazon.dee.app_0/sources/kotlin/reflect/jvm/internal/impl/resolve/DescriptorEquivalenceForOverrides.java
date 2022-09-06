package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DescriptorEquivalenceForOverrides.kt */
/* loaded from: classes4.dex */
public final class DescriptorEquivalenceForOverrides {
    public static final DescriptorEquivalenceForOverrides INSTANCE = new DescriptorEquivalenceForOverrides();

    private DescriptorEquivalenceForOverrides() {
    }

    public static /* synthetic */ boolean areCallableDescriptorsEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides, CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, boolean z, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            z2 = false;
        }
        return descriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent(callableDescriptor, callableDescriptor2, z, z2);
    }

    private final boolean areClassesEquivalent(ClassDescriptor classDescriptor, ClassDescriptor classDescriptor2) {
        return Intrinsics.areEqual(classDescriptor.mo11528getTypeConstructor(), classDescriptor2.mo11528getTypeConstructor());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean areTypeParametersEquivalent(TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, boolean z, Function2<? super DeclarationDescriptor, ? super DeclarationDescriptor, Boolean> function2) {
        if (Intrinsics.areEqual(typeParameterDescriptor, typeParameterDescriptor2)) {
            return true;
        }
        return !Intrinsics.areEqual(typeParameterDescriptor.mo11607getContainingDeclaration(), typeParameterDescriptor2.mo11607getContainingDeclaration()) && ownersEquivalent(typeParameterDescriptor, typeParameterDescriptor2, function2, z) && typeParameterDescriptor.getIndex() == typeParameterDescriptor2.getIndex();
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ boolean areTypeParametersEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides, TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 8) != 0) {
            function2 = DescriptorEquivalenceForOverrides$areTypeParametersEquivalent$1.INSTANCE;
        }
        return descriptorEquivalenceForOverrides.areTypeParametersEquivalent(typeParameterDescriptor, typeParameterDescriptor2, z, function2);
    }

    private final boolean ownersEquivalent(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2, Function2<? super DeclarationDescriptor, ? super DeclarationDescriptor, Boolean> function2, boolean z) {
        DeclarationDescriptor mo11607getContainingDeclaration = declarationDescriptor.mo11607getContainingDeclaration();
        DeclarationDescriptor mo11607getContainingDeclaration2 = declarationDescriptor2.mo11607getContainingDeclaration();
        if (!(mo11607getContainingDeclaration instanceof CallableMemberDescriptor) && !(mo11607getContainingDeclaration2 instanceof CallableMemberDescriptor)) {
            return areEquivalent(mo11607getContainingDeclaration, mo11607getContainingDeclaration2, z);
        }
        return function2.mo12248invoke(mo11607getContainingDeclaration, mo11607getContainingDeclaration2).booleanValue();
    }

    private final SourceElement singleSource(@NotNull CallableDescriptor callableDescriptor) {
        while (callableDescriptor instanceof CallableMemberDescriptor) {
            CallableMemberDescriptor callableMemberDescriptor = (CallableMemberDescriptor) callableDescriptor;
            if (callableMemberDescriptor.getKind() != CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
                break;
            }
            Collection<? extends CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
            Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "overriddenDescriptors");
            callableDescriptor = (CallableMemberDescriptor) CollectionsKt.singleOrNull(overriddenDescriptors);
            if (callableDescriptor == null) {
                return null;
            }
        }
        return callableDescriptor.getSource();
    }

    public final boolean areCallableDescriptorsEquivalent(@NotNull final CallableDescriptor a, @NotNull final CallableDescriptor b, final boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        if (Intrinsics.areEqual(a, b)) {
            return true;
        }
        if (!Intrinsics.areEqual(a.getName(), b.getName())) {
            return false;
        }
        if (Intrinsics.areEqual(a.mo11607getContainingDeclaration(), b.mo11607getContainingDeclaration())) {
            if (!z || (!Intrinsics.areEqual(singleSource(a), singleSource(b)))) {
                return false;
            }
            if ((a instanceof MemberDescriptor) && (b instanceof MemberDescriptor) && ((MemberDescriptor) a).isExpect() != ((MemberDescriptor) b).isExpect()) {
                return false;
            }
        }
        if (DescriptorUtils.isLocal(a) || DescriptorUtils.isLocal(b) || !ownersEquivalent(a, b, DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$1.INSTANCE, z)) {
            return false;
        }
        OverridingUtil createWithEqualityAxioms = OverridingUtil.createWithEqualityAxioms(new KotlinTypeChecker.TypeConstructorEquality() { // from class: kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$overridingUtil$1

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: DescriptorEquivalenceForOverrides.kt */
            /* renamed from: kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$overridingUtil$1$1  reason: invalid class name */
            /* loaded from: classes4.dex */
            public static final class AnonymousClass1 extends Lambda implements Function2<DeclarationDescriptor, DeclarationDescriptor, Boolean> {
                AnonymousClass1() {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                /* renamed from: invoke */
                public /* bridge */ /* synthetic */ Boolean mo12248invoke(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
                    return Boolean.valueOf(invoke2(declarationDescriptor, declarationDescriptor2));
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final boolean invoke2(@Nullable DeclarationDescriptor declarationDescriptor, @Nullable DeclarationDescriptor declarationDescriptor2) {
                    return Intrinsics.areEqual(declarationDescriptor, a) && Intrinsics.areEqual(declarationDescriptor2, b);
                }
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker.TypeConstructorEquality
            /* renamed from: invoke */
            public final boolean equals(@NotNull TypeConstructor c1, @NotNull TypeConstructor c2) {
                boolean areTypeParametersEquivalent;
                Intrinsics.checkParameterIsNotNull(c1, "c1");
                Intrinsics.checkParameterIsNotNull(c2, "c2");
                if (Intrinsics.areEqual(c1, c2)) {
                    return true;
                }
                ClassifierDescriptor mo12085getDeclarationDescriptor = c1.mo12085getDeclarationDescriptor();
                ClassifierDescriptor mo12085getDeclarationDescriptor2 = c2.mo12085getDeclarationDescriptor();
                if (!(mo12085getDeclarationDescriptor instanceof TypeParameterDescriptor) || !(mo12085getDeclarationDescriptor2 instanceof TypeParameterDescriptor)) {
                    return false;
                }
                areTypeParametersEquivalent = DescriptorEquivalenceForOverrides.INSTANCE.areTypeParametersEquivalent((TypeParameterDescriptor) mo12085getDeclarationDescriptor, (TypeParameterDescriptor) mo12085getDeclarationDescriptor2, z, new AnonymousClass1());
                return areTypeParametersEquivalent;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(createWithEqualityAxioms, "OverridingUtil.createWit…= a && y == b }\n        }");
        OverridingUtil.OverrideCompatibilityInfo isOverridableBy = createWithEqualityAxioms.isOverridableBy(a, b, null, !z2);
        Intrinsics.checkExpressionValueIsNotNull(isOverridableBy, "overridingUtil.isOverrid… null, !ignoreReturnType)");
        if (isOverridableBy.getResult() == OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE) {
            OverridingUtil.OverrideCompatibilityInfo isOverridableBy2 = createWithEqualityAxioms.isOverridableBy(b, a, null, !z2);
            Intrinsics.checkExpressionValueIsNotNull(isOverridableBy2, "overridingUtil.isOverrid… null, !ignoreReturnType)");
            if (isOverridableBy2.getResult() == OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE) {
                return true;
            }
        }
        return false;
    }

    public final boolean areEquivalent(@Nullable DeclarationDescriptor declarationDescriptor, @Nullable DeclarationDescriptor declarationDescriptor2, boolean z) {
        return (!(declarationDescriptor instanceof ClassDescriptor) || !(declarationDescriptor2 instanceof ClassDescriptor)) ? (!(declarationDescriptor instanceof TypeParameterDescriptor) || !(declarationDescriptor2 instanceof TypeParameterDescriptor)) ? (!(declarationDescriptor instanceof CallableDescriptor) || !(declarationDescriptor2 instanceof CallableDescriptor)) ? (!(declarationDescriptor instanceof PackageFragmentDescriptor) || !(declarationDescriptor2 instanceof PackageFragmentDescriptor)) ? Intrinsics.areEqual(declarationDescriptor, declarationDescriptor2) : Intrinsics.areEqual(((PackageFragmentDescriptor) declarationDescriptor).getFqName(), ((PackageFragmentDescriptor) declarationDescriptor2).getFqName()) : areCallableDescriptorsEquivalent$default(this, (CallableDescriptor) declarationDescriptor, (CallableDescriptor) declarationDescriptor2, z, false, 8, null) : areTypeParametersEquivalent$default(this, (TypeParameterDescriptor) declarationDescriptor, (TypeParameterDescriptor) declarationDescriptor2, z, null, 8, null) : areClassesEquivalent((ClassDescriptor) declarationDescriptor, (ClassDescriptor) declarationDescriptor2);
    }
}
