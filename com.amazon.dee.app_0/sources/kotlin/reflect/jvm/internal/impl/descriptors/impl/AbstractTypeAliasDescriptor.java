package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: AbstractTypeAliasDescriptor.kt */
/* loaded from: classes2.dex */
public abstract class AbstractTypeAliasDescriptor extends DeclarationDescriptorNonRootImpl implements TypeAliasDescriptor {
    private List<? extends TypeParameterDescriptor> declaredTypeParametersImpl;
    private final AbstractTypeAliasDescriptor$typeConstructor$1 typeConstructor;
    private final Visibility visibilityImpl;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor$typeConstructor$1] */
    public AbstractTypeAliasDescriptor(@NotNull DeclarationDescriptor containingDeclaration, @NotNull Annotations annotations, @NotNull Name name, @NotNull SourceElement sourceElement, @NotNull Visibility visibilityImpl) {
        super(containingDeclaration, annotations, name, sourceElement);
        Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(sourceElement, "sourceElement");
        Intrinsics.checkParameterIsNotNull(visibilityImpl, "visibilityImpl");
        this.visibilityImpl = visibilityImpl;
        this.typeConstructor = new TypeConstructor() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor$typeConstructor$1
            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            @NotNull
            public KotlinBuiltIns getBuiltIns() {
                return DescriptorUtilsKt.getBuiltIns(mo12085getDeclarationDescriptor());
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            @NotNull
            public List<TypeParameterDescriptor> getParameters() {
                return AbstractTypeAliasDescriptor.this.getTypeConstructorTypeParameters();
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            @NotNull
            /* renamed from: getSupertypes */
            public Collection<KotlinType> mo12135getSupertypes() {
                Collection<KotlinType> mo12135getSupertypes = mo12085getDeclarationDescriptor().getUnderlyingType().mo12131getConstructor().mo12135getSupertypes();
                Intrinsics.checkExpressionValueIsNotNull(mo12135getSupertypes, "declarationDescriptor.un…pe.constructor.supertypes");
                return mo12135getSupertypes;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            public boolean isDenotable() {
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            @NotNull
            /* renamed from: refine */
            public TypeConstructor mo12136refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
                Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
                return this;
            }

            @NotNull
            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[typealias ");
                outline107.append(mo12085getDeclarationDescriptor().getName().asString());
                outline107.append(JsonReaderKt.END_LIST);
                return outline107.toString();
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            @NotNull
            /* renamed from: getDeclarationDescriptor  reason: collision with other method in class */
            public TypeAliasDescriptor mo12085getDeclarationDescriptor() {
                return AbstractTypeAliasDescriptor.this;
            }
        };
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public <R, D> R accept(@NotNull DeclarationDescriptorVisitor<R, D> visitor, D d) {
        Intrinsics.checkParameterIsNotNull(visitor, "visitor");
        return visitor.visitTypeAliasDescriptor(this, d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final SimpleType computeDefaultType() {
        MemberScope memberScope;
        ClassDescriptor classDescriptor = getClassDescriptor();
        if (classDescriptor == null || (memberScope = classDescriptor.mo11664getUnsubstitutedMemberScope()) == null) {
            memberScope = MemberScope.Empty.INSTANCE;
        }
        SimpleType makeUnsubstitutedType = TypeUtils.makeUnsubstitutedType(this, memberScope, new AbstractTypeAliasDescriptor$computeDefaultType$1(this));
        Intrinsics.checkExpressionValueIsNotNull(makeUnsubstitutedType, "TypeUtils.makeUnsubstitu…s)?.defaultType\n        }");
        return makeUnsubstitutedType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    @NotNull
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        List list = this.declaredTypeParametersImpl;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("declaredTypeParametersImpl");
        }
        return list;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    @NotNull
    public Modality getModality() {
        return Modality.FINAL;
    }

    @NotNull
    protected abstract StorageManager getStorageManager();

    @NotNull
    public final Collection<TypeAliasConstructorDescriptor> getTypeAliasConstructors() {
        List emptyList;
        ClassDescriptor classDescriptor = getClassDescriptor();
        if (classDescriptor != null) {
            Collection<ClassConstructorDescriptor> mo11663getConstructors = classDescriptor.mo11663getConstructors();
            Intrinsics.checkExpressionValueIsNotNull(mo11663getConstructors, "classDescriptor.constructors");
            ArrayList arrayList = new ArrayList();
            for (ClassConstructorDescriptor it2 : mo11663getConstructors) {
                TypeAliasConstructorDescriptorImpl.Companion companion = TypeAliasConstructorDescriptorImpl.Companion;
                StorageManager storageManager = getStorageManager();
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                TypeAliasConstructorDescriptor createIfAvailable = companion.createIfAvailable(storageManager, this, it2);
                if (createIfAvailable != null) {
                    arrayList.add(createIfAvailable);
                }
            }
            return arrayList;
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    @NotNull
    /* renamed from: getTypeConstructor */
    public TypeConstructor mo11528getTypeConstructor() {
        return this.typeConstructor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public abstract List<TypeParameterDescriptor> getTypeConstructorTypeParameters();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    @NotNull
    public Visibility getVisibility() {
        return this.visibilityImpl;
    }

    public final void initialize(@NotNull List<? extends TypeParameterDescriptor> declaredTypeParameters) {
        Intrinsics.checkParameterIsNotNull(declaredTypeParameters, "declaredTypeParameters");
        this.declaredTypeParametersImpl = declaredTypeParameters;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public boolean isInner() {
        return TypeUtils.contains(getUnderlyingType(), new AbstractTypeAliasDescriptor$isInner$1(this));
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl
    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("typealias ");
        outline107.append(getName().asString());
        return outline107.toString();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    /* renamed from: getOriginal  reason: collision with other method in class */
    public TypeAliasDescriptor mo11613getOriginal() {
        DeclarationDescriptorWithSource mo11613getOriginal = super.mo11613getOriginal();
        if (mo11613getOriginal != null) {
            return (TypeAliasDescriptor) mo11613getOriginal;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeAliasDescriptor");
    }
}
