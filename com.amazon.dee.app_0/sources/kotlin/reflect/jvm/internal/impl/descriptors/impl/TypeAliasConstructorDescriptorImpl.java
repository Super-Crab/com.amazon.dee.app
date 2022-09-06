package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.List;
import kotlin.TypeCastException;
import kotlin._Assertions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeAliasConstructorDescriptor.kt */
/* loaded from: classes2.dex */
public final class TypeAliasConstructorDescriptorImpl extends FunctionDescriptorImpl implements TypeAliasConstructorDescriptor {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(TypeAliasConstructorDescriptorImpl.class), "withDispatchReceiver", "getWithDispatchReceiver()Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptor;"))};
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final StorageManager storageManager;
    @NotNull
    private final TypeAliasDescriptor typeAliasDescriptor;
    @NotNull
    private ClassConstructorDescriptor underlyingConstructorDescriptor;
    @Nullable
    private final NullableLazyValue withDispatchReceiver$delegate;

    /* compiled from: TypeAliasConstructorDescriptor.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final TypeSubstitutor getTypeSubstitutorForUnderlyingClass(@NotNull TypeAliasDescriptor typeAliasDescriptor) {
            if (typeAliasDescriptor.getClassDescriptor() == null) {
                return null;
            }
            return TypeSubstitutor.create(typeAliasDescriptor.getExpandedType());
        }

        @Nullable
        public final TypeAliasConstructorDescriptor createIfAvailable(@NotNull StorageManager storageManager, @NotNull TypeAliasDescriptor typeAliasDescriptor, @NotNull ClassConstructorDescriptor constructor) {
            ClassConstructorDescriptor mo12098substitute;
            Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
            Intrinsics.checkParameterIsNotNull(typeAliasDescriptor, "typeAliasDescriptor");
            Intrinsics.checkParameterIsNotNull(constructor, "constructor");
            TypeSubstitutor typeSubstitutorForUnderlyingClass = getTypeSubstitutorForUnderlyingClass(typeAliasDescriptor);
            ReceiverParameterDescriptor receiverParameterDescriptor = null;
            if (typeSubstitutorForUnderlyingClass != null && (mo12098substitute = constructor.mo12098substitute(typeSubstitutorForUnderlyingClass)) != null) {
                Annotations mo12070getAnnotations = constructor.mo12070getAnnotations();
                CallableMemberDescriptor.Kind kind = constructor.getKind();
                Intrinsics.checkExpressionValueIsNotNull(kind, "constructor.kind");
                SourceElement source = typeAliasDescriptor.getSource();
                Intrinsics.checkExpressionValueIsNotNull(source, "typeAliasDescriptor.source");
                TypeAliasConstructorDescriptorImpl typeAliasConstructorDescriptorImpl = new TypeAliasConstructorDescriptorImpl(storageManager, typeAliasDescriptor, mo12098substitute, null, mo12070getAnnotations, kind, source, null);
                List<ValueParameterDescriptor> substitutedValueParameters = FunctionDescriptorImpl.getSubstitutedValueParameters(typeAliasConstructorDescriptorImpl, constructor.getValueParameters(), typeSubstitutorForUnderlyingClass);
                if (substitutedValueParameters != null) {
                    Intrinsics.checkExpressionValueIsNotNull(substitutedValueParameters, "FunctionDescriptorImpl.g…         ) ?: return null");
                    SimpleType lowerIfFlexible = FlexibleTypesKt.lowerIfFlexible(mo12098substitute.getReturnType().unwrap());
                    SimpleType defaultType = typeAliasDescriptor.getDefaultType();
                    Intrinsics.checkExpressionValueIsNotNull(defaultType, "typeAliasDescriptor.defaultType");
                    SimpleType withAbbreviation = SpecialTypesKt.withAbbreviation(lowerIfFlexible, defaultType);
                    ReceiverParameterDescriptor it2 = constructor.getDispatchReceiverParameter();
                    if (it2 != null) {
                        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                        receiverParameterDescriptor = DescriptorFactory.createExtensionReceiverParameterForCallable(typeAliasConstructorDescriptorImpl, typeSubstitutorForUnderlyingClass.safeSubstitute(it2.getType(), Variance.INVARIANT), Annotations.Companion.getEMPTY());
                    }
                    typeAliasConstructorDescriptorImpl.mo11596initialize(receiverParameterDescriptor, null, typeAliasDescriptor.getDeclaredTypeParameters(), substitutedValueParameters, withAbbreviation, Modality.FINAL, typeAliasDescriptor.getVisibility());
                    return typeAliasConstructorDescriptorImpl;
                }
            }
            return null;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ TypeAliasConstructorDescriptorImpl(StorageManager storageManager, TypeAliasDescriptor typeAliasDescriptor, ClassConstructorDescriptor classConstructorDescriptor, TypeAliasConstructorDescriptor typeAliasConstructorDescriptor, Annotations annotations, CallableMemberDescriptor.Kind kind, SourceElement sourceElement, DefaultConstructorMarker defaultConstructorMarker) {
        this(storageManager, typeAliasDescriptor, classConstructorDescriptor, typeAliasConstructorDescriptor, annotations, kind, sourceElement);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
    @NotNull
    public ClassDescriptor getConstructedClass() {
        ClassDescriptor constructedClass = getUnderlyingConstructorDescriptor().getConstructedClass();
        Intrinsics.checkExpressionValueIsNotNull(constructedClass, "underlyingConstructorDescriptor.constructedClass");
        return constructedClass;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @NotNull
    public KotlinType getReturnType() {
        KotlinType returnType = super.getReturnType();
        if (returnType == null) {
            Intrinsics.throwNpe();
        }
        return returnType;
    }

    @NotNull
    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    @NotNull
    public TypeAliasDescriptor getTypeAliasDescriptor() {
        return this.typeAliasDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptor
    @NotNull
    public ClassConstructorDescriptor getUnderlyingConstructorDescriptor() {
        return this.underlyingConstructorDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
    public boolean isPrimary() {
        return getUnderlyingConstructorDescriptor().isPrimary();
    }

    private TypeAliasConstructorDescriptorImpl(StorageManager storageManager, TypeAliasDescriptor typeAliasDescriptor, ClassConstructorDescriptor classConstructorDescriptor, TypeAliasConstructorDescriptor typeAliasConstructorDescriptor, Annotations annotations, CallableMemberDescriptor.Kind kind, SourceElement sourceElement) {
        super(typeAliasDescriptor, typeAliasConstructorDescriptor, annotations, Name.special("<init>"), kind, sourceElement);
        this.storageManager = storageManager;
        this.typeAliasDescriptor = typeAliasDescriptor;
        setActual(getTypeAliasDescriptor().isActual());
        this.withDispatchReceiver$delegate = this.storageManager.createNullableLazyValue(new TypeAliasConstructorDescriptorImpl$withDispatchReceiver$2(this, classConstructorDescriptor));
        this.underlyingConstructorDescriptor = classConstructorDescriptor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    @NotNull
    /* renamed from: createSubstitutedCopy  reason: collision with other method in class */
    public TypeAliasConstructorDescriptorImpl mo12045createSubstitutedCopy(@NotNull DeclarationDescriptor newOwner, @Nullable FunctionDescriptor functionDescriptor, @NotNull CallableMemberDescriptor.Kind kind, @Nullable Name name, @NotNull Annotations annotations, @NotNull SourceElement source) {
        Intrinsics.checkParameterIsNotNull(newOwner, "newOwner");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(source, "source");
        boolean z = false;
        boolean z2 = kind == CallableMemberDescriptor.Kind.DECLARATION || kind == CallableMemberDescriptor.Kind.SYNTHESIZED;
        if (_Assertions.ENABLED && !z2) {
            throw new AssertionError("Creating a type alias constructor that is not a declaration: \ncopy from: " + this + "\nnewOwner: " + newOwner + "\nkind: " + kind);
        }
        if (name == null) {
            z = true;
        }
        if (!_Assertions.ENABLED || z) {
            return new TypeAliasConstructorDescriptorImpl(this.storageManager, getTypeAliasDescriptor(), getUnderlyingConstructorDescriptor(), this, annotations, CallableMemberDescriptor.Kind.DECLARATION, source);
        }
        throw new AssertionError("Renaming type alias constructor: " + this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    @NotNull
    /* renamed from: copy */
    public TypeAliasConstructorDescriptor mo12143copy(@NotNull DeclarationDescriptor newOwner, @NotNull Modality modality, @NotNull Visibility visibility, @NotNull CallableMemberDescriptor.Kind kind, boolean z) {
        Intrinsics.checkParameterIsNotNull(newOwner, "newOwner");
        Intrinsics.checkParameterIsNotNull(modality, "modality");
        Intrinsics.checkParameterIsNotNull(visibility, "visibility");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        FunctionDescriptor build = newCopyBuilder().setOwner(newOwner).setModality(modality).setVisibility(visibility).setKind(kind).setCopyOverrides(z).build();
        if (build != null) {
            return (TypeAliasConstructorDescriptor) build;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor");
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    /* renamed from: getContainingDeclaration */
    public TypeAliasDescriptor mo11607getContainingDeclaration() {
        return getTypeAliasDescriptor();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    @Nullable
    /* renamed from: substitute  reason: collision with other method in class */
    public TypeAliasConstructorDescriptor mo12098substitute(@NotNull TypeSubstitutor substitutor) {
        Intrinsics.checkParameterIsNotNull(substitutor, "substitutor");
        FunctionDescriptor mo12098substitute = super.mo12098substitute(substitutor);
        if (mo12098substitute != null) {
            TypeAliasConstructorDescriptorImpl typeAliasConstructorDescriptorImpl = (TypeAliasConstructorDescriptorImpl) mo12098substitute;
            TypeSubstitutor create = TypeSubstitutor.create(typeAliasConstructorDescriptorImpl.getReturnType());
            Intrinsics.checkExpressionValueIsNotNull(create, "TypeSubstitutor.create(s…asConstructor.returnType)");
            ClassConstructorDescriptor mo12098substitute2 = getUnderlyingConstructorDescriptor().mo11613getOriginal().mo12098substitute(create);
            if (mo12098substitute2 == null) {
                return null;
            }
            typeAliasConstructorDescriptorImpl.underlyingConstructorDescriptor = mo12098substitute2;
            return typeAliasConstructorDescriptorImpl;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptorImpl");
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    /* renamed from: getOriginal  reason: collision with other method in class */
    public TypeAliasConstructorDescriptor mo11613getOriginal() {
        FunctionDescriptor mo11613getOriginal = super.mo11613getOriginal();
        if (mo11613getOriginal != null) {
            return (TypeAliasConstructorDescriptor) mo11613getOriginal;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor");
    }
}
