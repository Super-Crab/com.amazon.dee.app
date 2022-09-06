package kotlin.reflect.jvm.internal.impl.types;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptorKt;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: KotlinTypeFactory.kt */
/* loaded from: classes4.dex */
public final class KotlinTypeFactory {
    public static final KotlinTypeFactory INSTANCE = new KotlinTypeFactory();
    @NotNull
    private static final Function1<KotlinTypeRefiner, SimpleType> EMPTY_REFINED_TYPE_FACTORY = KotlinTypeFactory$EMPTY_REFINED_TYPE_FACTORY$1.INSTANCE;

    /* compiled from: KotlinTypeFactory.kt */
    /* loaded from: classes4.dex */
    public static final class ExpandedTypeOrRefinedConstructor {
        @Nullable
        private final SimpleType expandedType;
        @Nullable
        private final TypeConstructor refinedConstructor;

        public ExpandedTypeOrRefinedConstructor(@Nullable SimpleType simpleType, @Nullable TypeConstructor typeConstructor) {
            this.expandedType = simpleType;
            this.refinedConstructor = typeConstructor;
        }

        @Nullable
        public final SimpleType getExpandedType() {
            return this.expandedType;
        }

        @Nullable
        public final TypeConstructor getRefinedConstructor() {
            return this.refinedConstructor;
        }
    }

    private KotlinTypeFactory() {
    }

    @JvmStatic
    @NotNull
    public static final SimpleType computeExpandedType(@NotNull TypeAliasDescriptor computeExpandedType, @NotNull List<? extends TypeProjection> arguments) {
        Intrinsics.checkParameterIsNotNull(computeExpandedType, "$this$computeExpandedType");
        Intrinsics.checkParameterIsNotNull(arguments, "arguments");
        return new TypeAliasExpander(TypeAliasExpansionReportStrategy.DO_NOTHING.INSTANCE, false).expand(TypeAliasExpansion.Companion.create(null, computeExpandedType, arguments), Annotations.Companion.getEMPTY());
    }

    private final MemberScope computeMemberScope(TypeConstructor typeConstructor, List<? extends TypeProjection> list, KotlinTypeRefiner kotlinTypeRefiner) {
        ClassifierDescriptor mo12085getDeclarationDescriptor = typeConstructor.mo12085getDeclarationDescriptor();
        if (mo12085getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            return mo12085getDeclarationDescriptor.getDefaultType().getMemberScope();
        }
        if (mo12085getDeclarationDescriptor instanceof ClassDescriptor) {
            if (kotlinTypeRefiner == null) {
                kotlinTypeRefiner = DescriptorUtilsKt.getKotlinTypeRefiner(DescriptorUtilsKt.getModule(mo12085getDeclarationDescriptor));
            }
            if (list.isEmpty()) {
                return ModuleAwareClassDescriptorKt.getRefinedUnsubstitutedMemberScopeIfPossible((ClassDescriptor) mo12085getDeclarationDescriptor, kotlinTypeRefiner);
            }
            return ModuleAwareClassDescriptorKt.getRefinedMemberScopeIfPossible((ClassDescriptor) mo12085getDeclarationDescriptor, TypeConstructorSubstitution.Companion.create(typeConstructor, list), kotlinTypeRefiner);
        } else if (mo12085getDeclarationDescriptor instanceof TypeAliasDescriptor) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Scope for abbreviation: ");
            outline107.append(((TypeAliasDescriptor) mo12085getDeclarationDescriptor).getName());
            MemberScope createErrorScope = ErrorUtils.createErrorScope(outline107.toString(), true);
            Intrinsics.checkExpressionValueIsNotNull(createErrorScope, "ErrorUtils.createErrorSc…{descriptor.name}\", true)");
            return createErrorScope;
        } else {
            throw new IllegalStateException("Unsupported classifier: " + mo12085getDeclarationDescriptor + " for constructor: " + typeConstructor);
        }
    }

    @JvmStatic
    @NotNull
    public static final UnwrappedType flexibleType(@NotNull SimpleType lowerBound, @NotNull SimpleType upperBound) {
        Intrinsics.checkParameterIsNotNull(lowerBound, "lowerBound");
        Intrinsics.checkParameterIsNotNull(upperBound, "upperBound");
        return Intrinsics.areEqual(lowerBound, upperBound) ? lowerBound : new FlexibleTypeImpl(lowerBound, upperBound);
    }

    @JvmStatic
    @NotNull
    public static final SimpleType integerLiteralType(@NotNull Annotations annotations, @NotNull IntegerLiteralTypeConstructor constructor, boolean z) {
        List emptyList;
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        MemberScope createErrorScope = ErrorUtils.createErrorScope("Scope for integer literal type", true);
        Intrinsics.checkExpressionValueIsNotNull(createErrorScope, "ErrorUtils.createErrorSc…eger literal type\", true)");
        return simpleTypeWithNonTrivialMemberScope(annotations, constructor, emptyList, z, createErrorScope);
    }

    public final ExpandedTypeOrRefinedConstructor refineConstructor(TypeConstructor typeConstructor, KotlinTypeRefiner kotlinTypeRefiner, List<? extends TypeProjection> list) {
        ClassifierDescriptor mo12130refineDescriptor;
        ClassifierDescriptor mo12085getDeclarationDescriptor = typeConstructor.mo12085getDeclarationDescriptor();
        if (mo12085getDeclarationDescriptor == null || (mo12130refineDescriptor = kotlinTypeRefiner.mo12130refineDescriptor(mo12085getDeclarationDescriptor)) == null) {
            return null;
        }
        if (mo12130refineDescriptor instanceof TypeAliasDescriptor) {
            return new ExpandedTypeOrRefinedConstructor(computeExpandedType((TypeAliasDescriptor) mo12130refineDescriptor, list), null);
        }
        TypeConstructor mo12136refine = mo12130refineDescriptor.mo11528getTypeConstructor().mo12136refine(kotlinTypeRefiner);
        Intrinsics.checkExpressionValueIsNotNull(mo12136refine, "descriptor.typeConstruct…refine(kotlinTypeRefiner)");
        return new ExpandedTypeOrRefinedConstructor(null, mo12136refine);
    }

    @JvmStatic
    @NotNull
    public static final SimpleType simpleNotNullType(@NotNull Annotations annotations, @NotNull ClassDescriptor descriptor, @NotNull List<? extends TypeProjection> arguments) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(arguments, "arguments");
        TypeConstructor mo11528getTypeConstructor = descriptor.mo11528getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "descriptor.typeConstructor");
        return simpleType$default(annotations, mo11528getTypeConstructor, arguments, false, null, 16, null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final SimpleType simpleType(@NotNull Annotations annotations, @NotNull TypeConstructor constructor, @NotNull List<? extends TypeProjection> arguments, boolean z, @Nullable KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        Intrinsics.checkParameterIsNotNull(arguments, "arguments");
        if (annotations.isEmpty() && arguments.isEmpty() && !z && constructor.mo12085getDeclarationDescriptor() != null) {
            ClassifierDescriptor mo12085getDeclarationDescriptor = constructor.mo12085getDeclarationDescriptor();
            if (mo12085getDeclarationDescriptor == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(mo12085getDeclarationDescriptor, "constructor.declarationDescriptor!!");
            SimpleType defaultType = mo12085getDeclarationDescriptor.getDefaultType();
            Intrinsics.checkExpressionValueIsNotNull(defaultType, "constructor.declarationDescriptor!!.defaultType");
            return defaultType;
        }
        return simpleTypeWithNonTrivialMemberScope(annotations, constructor, arguments, z, INSTANCE.computeMemberScope(constructor, arguments, kotlinTypeRefiner), new KotlinTypeFactory$simpleType$1(constructor, arguments, annotations, z));
    }

    public static /* synthetic */ SimpleType simpleType$default(Annotations annotations, TypeConstructor typeConstructor, List list, boolean z, KotlinTypeRefiner kotlinTypeRefiner, int i, Object obj) {
        if ((i & 16) != 0) {
            kotlinTypeRefiner = null;
        }
        return simpleType(annotations, typeConstructor, list, z, kotlinTypeRefiner);
    }

    @JvmStatic
    @NotNull
    public static final SimpleType simpleTypeWithNonTrivialMemberScope(@NotNull Annotations annotations, @NotNull TypeConstructor constructor, @NotNull List<? extends TypeProjection> arguments, boolean z, @NotNull MemberScope memberScope) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        Intrinsics.checkParameterIsNotNull(arguments, "arguments");
        Intrinsics.checkParameterIsNotNull(memberScope, "memberScope");
        SimpleTypeImpl simpleTypeImpl = new SimpleTypeImpl(constructor, arguments, z, memberScope, new KotlinTypeFactory$simpleTypeWithNonTrivialMemberScope$1(constructor, arguments, annotations, z, memberScope));
        return annotations.isEmpty() ? simpleTypeImpl : new AnnotatedSimpleType(simpleTypeImpl, annotations);
    }

    @JvmStatic
    @NotNull
    public static final SimpleType simpleTypeWithNonTrivialMemberScope(@NotNull Annotations annotations, @NotNull TypeConstructor constructor, @NotNull List<? extends TypeProjection> arguments, boolean z, @NotNull MemberScope memberScope, @NotNull Function1<? super KotlinTypeRefiner, ? extends SimpleType> refinedTypeFactory) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        Intrinsics.checkParameterIsNotNull(arguments, "arguments");
        Intrinsics.checkParameterIsNotNull(memberScope, "memberScope");
        Intrinsics.checkParameterIsNotNull(refinedTypeFactory, "refinedTypeFactory");
        SimpleTypeImpl simpleTypeImpl = new SimpleTypeImpl(constructor, arguments, z, memberScope, refinedTypeFactory);
        return annotations.isEmpty() ? simpleTypeImpl : new AnnotatedSimpleType(simpleTypeImpl, annotations);
    }
}
