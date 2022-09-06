package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ValueParameterDescriptorImpl.kt */
/* loaded from: classes2.dex */
public class ValueParameterDescriptorImpl extends VariableDescriptorImpl implements ValueParameterDescriptor {
    public static final Companion Companion = new Companion(null);
    private final boolean declaresDefaultValue;
    private final int index;
    private final boolean isCrossinline;
    private final boolean isNoinline;
    private final ValueParameterDescriptor original;
    @Nullable
    private final KotlinType varargElementType;

    /* compiled from: ValueParameterDescriptorImpl.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final ValueParameterDescriptorImpl createWithDestructuringDeclarations(@NotNull CallableDescriptor containingDeclaration, @Nullable ValueParameterDescriptor valueParameterDescriptor, int i, @NotNull Annotations annotations, @NotNull Name name, @NotNull KotlinType outType, boolean z, boolean z2, boolean z3, @Nullable KotlinType kotlinType, @NotNull SourceElement source, @Nullable Function0<? extends List<? extends VariableDescriptor>> function0) {
            Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
            Intrinsics.checkParameterIsNotNull(annotations, "annotations");
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(outType, "outType");
            Intrinsics.checkParameterIsNotNull(source, "source");
            if (function0 == null) {
                return new ValueParameterDescriptorImpl(containingDeclaration, valueParameterDescriptor, i, annotations, name, outType, z, z2, z3, kotlinType, source);
            }
            return new WithDestructuringDeclaration(containingDeclaration, valueParameterDescriptor, i, annotations, name, outType, z, z2, z3, kotlinType, source, function0);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: ValueParameterDescriptorImpl.kt */
    /* loaded from: classes2.dex */
    public static final class WithDestructuringDeclaration extends ValueParameterDescriptorImpl {
        @NotNull
        private final Lazy destructuringVariables$delegate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WithDestructuringDeclaration(@NotNull CallableDescriptor containingDeclaration, @Nullable ValueParameterDescriptor valueParameterDescriptor, int i, @NotNull Annotations annotations, @NotNull Name name, @NotNull KotlinType outType, boolean z, boolean z2, boolean z3, @Nullable KotlinType kotlinType, @NotNull SourceElement source, @NotNull Function0<? extends List<? extends VariableDescriptor>> destructuringVariables) {
            super(containingDeclaration, valueParameterDescriptor, i, annotations, name, outType, z, z2, z3, kotlinType, source);
            Lazy lazy;
            Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
            Intrinsics.checkParameterIsNotNull(annotations, "annotations");
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(outType, "outType");
            Intrinsics.checkParameterIsNotNull(source, "source");
            Intrinsics.checkParameterIsNotNull(destructuringVariables, "destructuringVariables");
            lazy = LazyKt__LazyJVMKt.lazy(destructuringVariables);
            this.destructuringVariables$delegate = lazy;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
        @NotNull
        public ValueParameterDescriptor copy(@NotNull CallableDescriptor newOwner, @NotNull Name newName, int i) {
            Intrinsics.checkParameterIsNotNull(newOwner, "newOwner");
            Intrinsics.checkParameterIsNotNull(newName, "newName");
            Annotations annotations = mo12070getAnnotations();
            Intrinsics.checkExpressionValueIsNotNull(annotations, "annotations");
            KotlinType type = getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "type");
            boolean declaresDefaultValue = declaresDefaultValue();
            boolean isCrossinline = isCrossinline();
            boolean isNoinline = isNoinline();
            KotlinType varargElementType = getVarargElementType();
            SourceElement sourceElement = SourceElement.NO_SOURCE;
            Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
            return new WithDestructuringDeclaration(newOwner, null, i, annotations, newName, type, declaresDefaultValue, isCrossinline, isNoinline, varargElementType, sourceElement, new ValueParameterDescriptorImpl$WithDestructuringDeclaration$copy$1(this));
        }

        @NotNull
        public final List<VariableDescriptor> getDestructuringVariables() {
            return (List) this.destructuringVariables$delegate.getValue();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ValueParameterDescriptorImpl(@NotNull CallableDescriptor containingDeclaration, @Nullable ValueParameterDescriptor valueParameterDescriptor, int i, @NotNull Annotations annotations, @NotNull Name name, @NotNull KotlinType outType, boolean z, boolean z2, boolean z3, @Nullable KotlinType kotlinType, @NotNull SourceElement source) {
        super(containingDeclaration, annotations, name, outType, source);
        Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(outType, "outType");
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.index = i;
        this.declaresDefaultValue = z;
        this.isCrossinline = z2;
        this.isNoinline = z3;
        this.varargElementType = kotlinType;
        this.original = valueParameterDescriptor != null ? valueParameterDescriptor : this;
    }

    @JvmStatic
    @NotNull
    public static final ValueParameterDescriptorImpl createWithDestructuringDeclarations(@NotNull CallableDescriptor callableDescriptor, @Nullable ValueParameterDescriptor valueParameterDescriptor, int i, @NotNull Annotations annotations, @NotNull Name name, @NotNull KotlinType kotlinType, boolean z, boolean z2, boolean z3, @Nullable KotlinType kotlinType2, @NotNull SourceElement sourceElement, @Nullable Function0<? extends List<? extends VariableDescriptor>> function0) {
        return Companion.createWithDestructuringDeclarations(callableDescriptor, valueParameterDescriptor, i, annotations, name, kotlinType, z, z2, z3, kotlinType2, sourceElement, function0);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public <R, D> R accept(@NotNull DeclarationDescriptorVisitor<R, D> visitor, D d) {
        Intrinsics.checkParameterIsNotNull(visitor, "visitor");
        return visitor.visitValueParameterDescriptor(this, d);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    @NotNull
    public ValueParameterDescriptor copy(@NotNull CallableDescriptor newOwner, @NotNull Name newName, int i) {
        Intrinsics.checkParameterIsNotNull(newOwner, "newOwner");
        Intrinsics.checkParameterIsNotNull(newName, "newName");
        Annotations annotations = mo12070getAnnotations();
        Intrinsics.checkExpressionValueIsNotNull(annotations, "annotations");
        KotlinType type = getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "type");
        boolean declaresDefaultValue = declaresDefaultValue();
        boolean isCrossinline = isCrossinline();
        boolean isNoinline = isNoinline();
        KotlinType varargElementType = getVarargElementType();
        SourceElement sourceElement = SourceElement.NO_SOURCE;
        Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
        return new ValueParameterDescriptorImpl(newOwner, null, i, annotations, newName, type, declaresDefaultValue, isCrossinline, isNoinline, varargElementType, sourceElement);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    public boolean declaresDefaultValue() {
        if (this.declaresDefaultValue) {
            CallableDescriptor mo11607getContainingDeclaration = mo11607getContainingDeclaration();
            if (mo11607getContainingDeclaration == null) {
                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableMemberDescriptor");
            }
            CallableMemberDescriptor.Kind kind = ((CallableMemberDescriptor) mo11607getContainingDeclaration).getKind();
            Intrinsics.checkExpressionValueIsNotNull(kind, "(containingDeclaration a…bleMemberDescriptor).kind");
            if (kind.isReal()) {
                return true;
            }
        }
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    @Nullable
    /* renamed from: getCompileTimeInitializer */
    public Void mo11606getCompileTimeInitializer() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    /* renamed from: getCompileTimeInitializer  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ ConstantValue mo11606getCompileTimeInitializer() {
        return (ConstantValue) mo11606getCompileTimeInitializer();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    public int getIndex() {
        return this.index;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    @NotNull
    public Collection<ValueParameterDescriptor> getOverriddenDescriptors() {
        int collectionSizeOrDefault;
        Collection<? extends CallableDescriptor> overriddenDescriptors = mo11607getContainingDeclaration().getOverriddenDescriptors();
        Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "containingDeclaration.overriddenDescriptors");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(overriddenDescriptors, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (CallableDescriptor it2 : overriddenDescriptors) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            arrayList.add(it2.getValueParameters().get(getIndex()));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    @Nullable
    public KotlinType getVarargElementType() {
        return this.varargElementType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    @NotNull
    public Visibility getVisibility() {
        Visibility visibility = Visibilities.LOCAL;
        Intrinsics.checkExpressionValueIsNotNull(visibility, "Visibilities.LOCAL");
        return visibility;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    public boolean isCrossinline() {
        return this.isCrossinline;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public boolean isLateInit() {
        return ValueParameterDescriptor.DefaultImpls.isLateInit(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    public boolean isNoinline() {
        return this.isNoinline;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public boolean isVar() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    /* renamed from: getContainingDeclaration */
    public CallableDescriptor mo11607getContainingDeclaration() {
        DeclarationDescriptor mo11607getContainingDeclaration = super.mo11607getContainingDeclaration();
        if (mo11607getContainingDeclaration != null) {
            return (CallableDescriptor) mo11607getContainingDeclaration;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableDescriptor");
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    @NotNull
    /* renamed from: substitute  reason: collision with other method in class */
    public ValueParameterDescriptor mo12098substitute(@NotNull TypeSubstitutor substitutor) {
        Intrinsics.checkParameterIsNotNull(substitutor, "substitutor");
        if (substitutor.isEmpty()) {
            return this;
        }
        throw new UnsupportedOperationException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    /* renamed from: getOriginal  reason: collision with other method in class */
    public ValueParameterDescriptor mo11613getOriginal() {
        ValueParameterDescriptor valueParameterDescriptor = this.original;
        return valueParameterDescriptor == this ? this : valueParameterDescriptor.mo11613getOriginal();
    }
}
