package kotlin.reflect.jvm.internal.impl.renderer;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.mail.Part;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PossiblyInnerType;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUseSiteTarget;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.AbbreviatedType;
import kotlin.reflect.jvm.internal.impl.types.ErrorType;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.UnresolvedType;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.WrappedType;
import kotlin.text.StringsKt__StringBuilderJVMKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.StringsKt___StringsKt;
import kotlin.text.Typography;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DescriptorRendererImpl.kt */
/* loaded from: classes4.dex */
public final class DescriptorRendererImpl extends DescriptorRenderer implements DescriptorRendererOptions {
    private final Lazy functionTypeAnnotationsRenderer$delegate;
    private final Lazy functionTypeParameterTypesRenderer$delegate;
    @NotNull
    private final DescriptorRendererOptionsImpl options;

    /* compiled from: DescriptorRendererImpl.kt */
    /* loaded from: classes4.dex */
    private final class RenderDeclarationDescriptorVisitor implements DeclarationDescriptorVisitor<Unit, StringBuilder> {

        /* loaded from: classes4.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[PropertyAccessorRenderingPolicy.values().length];

            static {
                $EnumSwitchMapping$0[PropertyAccessorRenderingPolicy.PRETTY.ordinal()] = 1;
                $EnumSwitchMapping$0[PropertyAccessorRenderingPolicy.DEBUG.ordinal()] = 2;
                $EnumSwitchMapping$0[PropertyAccessorRenderingPolicy.NONE.ordinal()] = 3;
            }
        }

        public RenderDeclarationDescriptorVisitor() {
        }

        private final void visitPropertyAccessorDescriptor(PropertyAccessorDescriptor propertyAccessorDescriptor, StringBuilder sb, String str) {
            int i = WhenMappings.$EnumSwitchMapping$0[DescriptorRendererImpl.this.getPropertyAccessorRenderingPolicy().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                visitFunctionDescriptor2((FunctionDescriptor) propertyAccessorDescriptor, sb);
                return;
            }
            DescriptorRendererImpl.this.renderAccessorModifiers(propertyAccessorDescriptor, sb);
            sb.append(str + " for ");
            DescriptorRendererImpl descriptorRendererImpl = DescriptorRendererImpl.this;
            PropertyDescriptor correspondingProperty = propertyAccessorDescriptor.getCorrespondingProperty();
            Intrinsics.checkExpressionValueIsNotNull(correspondingProperty, "descriptor.correspondingProperty");
            descriptorRendererImpl.renderProperty(correspondingProperty, sb);
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitClassDescriptor(ClassDescriptor classDescriptor, StringBuilder sb) {
            visitClassDescriptor2(classDescriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitConstructorDescriptor(ConstructorDescriptor constructorDescriptor, StringBuilder sb) {
            visitConstructorDescriptor2(constructorDescriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitFunctionDescriptor(FunctionDescriptor functionDescriptor, StringBuilder sb) {
            visitFunctionDescriptor2(functionDescriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitModuleDeclaration(ModuleDescriptor moduleDescriptor, StringBuilder sb) {
            visitModuleDeclaration2(moduleDescriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitPackageFragmentDescriptor(PackageFragmentDescriptor packageFragmentDescriptor, StringBuilder sb) {
            visitPackageFragmentDescriptor2(packageFragmentDescriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitPackageViewDescriptor(PackageViewDescriptor packageViewDescriptor, StringBuilder sb) {
            visitPackageViewDescriptor2(packageViewDescriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitPropertyDescriptor(PropertyDescriptor propertyDescriptor, StringBuilder sb) {
            visitPropertyDescriptor2(propertyDescriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitPropertyGetterDescriptor(PropertyGetterDescriptor propertyGetterDescriptor, StringBuilder sb) {
            visitPropertyGetterDescriptor2(propertyGetterDescriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitPropertySetterDescriptor(PropertySetterDescriptor propertySetterDescriptor, StringBuilder sb) {
            visitPropertySetterDescriptor2(propertySetterDescriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitReceiverParameterDescriptor(ReceiverParameterDescriptor receiverParameterDescriptor, StringBuilder sb) {
            visitReceiverParameterDescriptor2(receiverParameterDescriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitTypeAliasDescriptor(TypeAliasDescriptor typeAliasDescriptor, StringBuilder sb) {
            visitTypeAliasDescriptor2(typeAliasDescriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitTypeParameterDescriptor(TypeParameterDescriptor typeParameterDescriptor, StringBuilder sb) {
            visitTypeParameterDescriptor2(typeParameterDescriptor, sb);
            return Unit.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public /* bridge */ /* synthetic */ Unit visitValueParameterDescriptor(ValueParameterDescriptor valueParameterDescriptor, StringBuilder sb) {
            visitValueParameterDescriptor2(valueParameterDescriptor, sb);
            return Unit.INSTANCE;
        }

        /* renamed from: visitClassDescriptor  reason: avoid collision after fix types in other method */
        public void visitClassDescriptor2(@NotNull ClassDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            DescriptorRendererImpl.this.renderClass(descriptor, builder);
        }

        /* renamed from: visitConstructorDescriptor  reason: avoid collision after fix types in other method */
        public void visitConstructorDescriptor2(@NotNull ConstructorDescriptor constructorDescriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkParameterIsNotNull(constructorDescriptor, "constructorDescriptor");
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            DescriptorRendererImpl.this.renderConstructor(constructorDescriptor, builder);
        }

        /* renamed from: visitFunctionDescriptor  reason: avoid collision after fix types in other method */
        public void visitFunctionDescriptor2(@NotNull FunctionDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            DescriptorRendererImpl.this.renderFunction(descriptor, builder);
        }

        /* renamed from: visitModuleDeclaration  reason: avoid collision after fix types in other method */
        public void visitModuleDeclaration2(@NotNull ModuleDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            DescriptorRendererImpl.this.renderName(descriptor, builder, true);
        }

        /* renamed from: visitPackageFragmentDescriptor  reason: avoid collision after fix types in other method */
        public void visitPackageFragmentDescriptor2(@NotNull PackageFragmentDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            DescriptorRendererImpl.this.renderPackageFragment(descriptor, builder);
        }

        /* renamed from: visitPackageViewDescriptor  reason: avoid collision after fix types in other method */
        public void visitPackageViewDescriptor2(@NotNull PackageViewDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            DescriptorRendererImpl.this.renderPackageView(descriptor, builder);
        }

        /* renamed from: visitPropertyDescriptor  reason: avoid collision after fix types in other method */
        public void visitPropertyDescriptor2(@NotNull PropertyDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            DescriptorRendererImpl.this.renderProperty(descriptor, builder);
        }

        /* renamed from: visitPropertyGetterDescriptor  reason: avoid collision after fix types in other method */
        public void visitPropertyGetterDescriptor2(@NotNull PropertyGetterDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            visitPropertyAccessorDescriptor(descriptor, builder, "getter");
        }

        /* renamed from: visitPropertySetterDescriptor  reason: avoid collision after fix types in other method */
        public void visitPropertySetterDescriptor2(@NotNull PropertySetterDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            visitPropertyAccessorDescriptor(descriptor, builder, "setter");
        }

        /* renamed from: visitReceiverParameterDescriptor  reason: avoid collision after fix types in other method */
        public void visitReceiverParameterDescriptor2(@NotNull ReceiverParameterDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            builder.append(descriptor.getName());
        }

        /* renamed from: visitTypeAliasDescriptor  reason: avoid collision after fix types in other method */
        public void visitTypeAliasDescriptor2(@NotNull TypeAliasDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            DescriptorRendererImpl.this.renderTypeAlias(descriptor, builder);
        }

        /* renamed from: visitTypeParameterDescriptor  reason: avoid collision after fix types in other method */
        public void visitTypeParameterDescriptor2(@NotNull TypeParameterDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            DescriptorRendererImpl.this.renderTypeParameter(descriptor, builder, true);
        }

        /* renamed from: visitValueParameterDescriptor  reason: avoid collision after fix types in other method */
        public void visitValueParameterDescriptor2(@NotNull ValueParameterDescriptor descriptor, @NotNull StringBuilder builder) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            DescriptorRendererImpl.this.renderValueParameter(descriptor, true, builder, true);
        }
    }

    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[RenderingFormat.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;

        static {
            $EnumSwitchMapping$0[RenderingFormat.PLAIN.ordinal()] = 1;
            $EnumSwitchMapping$0[RenderingFormat.HTML.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[RenderingFormat.values().length];
            $EnumSwitchMapping$1[RenderingFormat.PLAIN.ordinal()] = 1;
            $EnumSwitchMapping$1[RenderingFormat.HTML.ordinal()] = 2;
            $EnumSwitchMapping$2 = new int[RenderingFormat.values().length];
            $EnumSwitchMapping$2[RenderingFormat.PLAIN.ordinal()] = 1;
            $EnumSwitchMapping$2[RenderingFormat.HTML.ordinal()] = 2;
            $EnumSwitchMapping$3 = new int[RenderingFormat.values().length];
            $EnumSwitchMapping$3[RenderingFormat.PLAIN.ordinal()] = 1;
            $EnumSwitchMapping$3[RenderingFormat.HTML.ordinal()] = 2;
            $EnumSwitchMapping$4 = new int[ParameterNameRenderingPolicy.values().length];
            $EnumSwitchMapping$4[ParameterNameRenderingPolicy.ALL.ordinal()] = 1;
            $EnumSwitchMapping$4[ParameterNameRenderingPolicy.ONLY_NON_SYNTHESIZED.ordinal()] = 2;
            $EnumSwitchMapping$4[ParameterNameRenderingPolicy.NONE.ordinal()] = 3;
        }
    }

    public DescriptorRendererImpl(@NotNull DescriptorRendererOptionsImpl options) {
        Lazy lazy;
        Lazy lazy2;
        Intrinsics.checkParameterIsNotNull(options, "options");
        this.options = options;
        boolean isLocked = this.options.isLocked();
        if (!_Assertions.ENABLED || isLocked) {
            lazy = LazyKt__LazyJVMKt.lazy(new DescriptorRendererImpl$functionTypeAnnotationsRenderer$2(this));
            this.functionTypeAnnotationsRenderer$delegate = lazy;
            lazy2 = LazyKt__LazyJVMKt.lazy(new DescriptorRendererImpl$functionTypeParameterTypesRenderer$2(this));
            this.functionTypeParameterTypesRenderer$delegate = lazy2;
            return;
        }
        throw new AssertionError("Assertion failed");
    }

    private final void appendDefinedIn(@NotNull StringBuilder sb, DeclarationDescriptor declarationDescriptor) {
        if ((declarationDescriptor instanceof PackageFragmentDescriptor) || (declarationDescriptor instanceof PackageViewDescriptor)) {
            return;
        }
        if (declarationDescriptor instanceof ModuleDescriptor) {
            sb.append(" is a module");
            return;
        }
        DeclarationDescriptor mo11607getContainingDeclaration = declarationDescriptor.mo11607getContainingDeclaration();
        if (mo11607getContainingDeclaration == null || (mo11607getContainingDeclaration instanceof ModuleDescriptor)) {
            return;
        }
        sb.append(" ");
        sb.append(renderMessage("defined in"));
        sb.append(" ");
        FqNameUnsafe fqName = DescriptorUtils.getFqName(mo11607getContainingDeclaration);
        Intrinsics.checkExpressionValueIsNotNull(fqName, "DescriptorUtils.getFqName(containingDeclaration)");
        sb.append(fqName.isRoot() ? "root package" : renderFqName(fqName));
        if (!getWithSourceFileForTopLevel() || !(mo11607getContainingDeclaration instanceof PackageFragmentDescriptor) || !(declarationDescriptor instanceof DeclarationDescriptorWithSource)) {
            return;
        }
        SourceElement source = ((DeclarationDescriptorWithSource) declarationDescriptor).getSource();
        Intrinsics.checkExpressionValueIsNotNull(source, "descriptor.source");
        SourceFile containingFile = source.getContainingFile();
        Intrinsics.checkExpressionValueIsNotNull(containingFile, "descriptor.source.containingFile");
        String name = containingFile.getName();
        if (name == null) {
            return;
        }
        sb.append(" ");
        sb.append(renderMessage("in file"));
        sb.append(" ");
        sb.append(name);
    }

    private final void appendTypeProjections(@NotNull StringBuilder sb, List<? extends TypeProjection> list) {
        CollectionsKt___CollectionsKt.joinTo$default(list, sb, ", ", null, null, 0, null, new DescriptorRendererImpl$appendTypeProjections$1(this), 60, null);
    }

    private final String arrow() {
        int i = WhenMappings.$EnumSwitchMapping$2[getTextFormat().ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            return "&rarr;";
        }
        return escape("->");
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0032, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r7 + com.amazon.deecomms.common.Constants.DEFAULT_IMAGE_CHAR, r8) == false) goto L7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean differsOnlyInNullability(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.String r1 = "?"
            java.lang.String r2 = ""
            r3 = 0
            r4 = 4
            r5 = 0
            r0 = r8
            java.lang.String r0 = kotlin.text.StringsKt.replace$default(r0, r1, r2, r3, r4, r5)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r0)
            r1 = 0
            if (r0 != 0) goto L50
            r0 = 2
            r2 = 0
            java.lang.String r3 = "?"
            boolean r0 = kotlin.text.StringsKt.endsWith$default(r8, r3, r1, r0, r2)
            if (r0 == 0) goto L34
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r7)
            r2 = 63
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r8)
            if (r0 != 0) goto L50
        L34:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r2 = 40
            r0.append(r2)
            r0.append(r7)
            java.lang.String r7 = ")?"
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r8)
            if (r7 == 0) goto L51
        L50:
            r1 = 1
        L51:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.differsOnlyInNullability(java.lang.String, java.lang.String):boolean");
    }

    private final String escape(String str) {
        return getTextFormat().escape(str);
    }

    private final DescriptorRendererImpl getFunctionTypeAnnotationsRenderer() {
        return (DescriptorRendererImpl) this.functionTypeAnnotationsRenderer$delegate.getValue();
    }

    private final DescriptorRenderer getFunctionTypeParameterTypesRenderer() {
        return (DescriptorRenderer) this.functionTypeParameterTypesRenderer$delegate.getValue();
    }

    private final String gt() {
        return escape(Config.Compare.GREATER_THAN);
    }

    private final boolean hasModifiersOrAnnotations(@NotNull KotlinType kotlinType) {
        return FunctionTypesKt.isSuspendFunctionType(kotlinType) || !kotlinType.mo12070getAnnotations().isEmpty();
    }

    private final Modality implicitModalityWithoutExtensions(@NotNull MemberDescriptor memberDescriptor) {
        if (memberDescriptor instanceof ClassDescriptor) {
            return ((ClassDescriptor) memberDescriptor).getKind() == ClassKind.INTERFACE ? Modality.ABSTRACT : Modality.FINAL;
        }
        DeclarationDescriptor mo11607getContainingDeclaration = memberDescriptor.mo11607getContainingDeclaration();
        if (!(mo11607getContainingDeclaration instanceof ClassDescriptor)) {
            mo11607getContainingDeclaration = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) mo11607getContainingDeclaration;
        if (classDescriptor != null) {
            if (!(memberDescriptor instanceof CallableMemberDescriptor)) {
                return Modality.FINAL;
            }
            CallableMemberDescriptor callableMemberDescriptor = (CallableMemberDescriptor) memberDescriptor;
            Collection<? extends CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
            Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "this.overriddenDescriptors");
            if ((!overriddenDescriptors.isEmpty()) && classDescriptor.getModality() != Modality.FINAL) {
                return Modality.OPEN;
            }
            if (classDescriptor.getKind() == ClassKind.INTERFACE && (!Intrinsics.areEqual(callableMemberDescriptor.getVisibility(), Visibilities.PRIVATE))) {
                Modality modality = callableMemberDescriptor.getModality();
                Modality modality2 = Modality.ABSTRACT;
                return modality == modality2 ? modality2 : Modality.OPEN;
            }
            return Modality.FINAL;
        }
        return Modality.FINAL;
    }

    private final String lt() {
        return escape(Config.Compare.LESS_THAN);
    }

    private final boolean overridesSomething(CallableMemberDescriptor callableMemberDescriptor) {
        return !callableMemberDescriptor.getOverriddenDescriptors().isEmpty();
    }

    private final void renderAbbreviatedTypeExpansion(@NotNull StringBuilder sb, AbbreviatedType abbreviatedType) {
        if (getTextFormat() == RenderingFormat.HTML) {
            sb.append("<font color=\"808080\"><i>");
        }
        sb.append(" /* = ");
        renderNormalizedTypeAsIs(sb, abbreviatedType.getExpandedType());
        sb.append(" */");
        if (getTextFormat() == RenderingFormat.HTML) {
            sb.append("</i></font>");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderAccessorModifiers(PropertyAccessorDescriptor propertyAccessorDescriptor, StringBuilder sb) {
        renderMemberModifiers(propertyAccessorDescriptor, sb);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void renderAdditionalModifiers(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r7, java.lang.StringBuilder r8) {
        /*
            r6 = this;
            boolean r0 = r7.isOperator()
            java.lang.String r1 = "it"
            java.lang.String r2 = "functionDescriptor.overriddenDescriptors"
            r3 = 0
            r4 = 1
            if (r0 == 0) goto L3f
            java.util.Collection r0 = r7.getOverriddenDescriptors()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            boolean r5 = r0.isEmpty()
            if (r5 == 0) goto L1b
        L19:
            r0 = r4
            goto L35
        L1b:
            java.util.Iterator r0 = r0.iterator()
        L1f:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L19
            java.lang.Object r5 = r0.next()
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r5 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r5
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r1)
            boolean r5 = r5.isOperator()
            if (r5 == 0) goto L1f
            r0 = r3
        L35:
            if (r0 != 0) goto L3d
            boolean r0 = r6.getAlwaysRenderModifiers()
            if (r0 == 0) goto L3f
        L3d:
            r0 = r4
            goto L40
        L3f:
            r0 = r3
        L40:
            boolean r5 = r7.isInfix()
            if (r5 == 0) goto L78
            java.util.Collection r5 = r7.getOverriddenDescriptors()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r2)
            boolean r2 = r5.isEmpty()
            if (r2 == 0) goto L55
        L53:
            r1 = r4
            goto L6f
        L55:
            java.util.Iterator r2 = r5.iterator()
        L59:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L53
            java.lang.Object r5 = r2.next()
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r5 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r5
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r1)
            boolean r5 = r5.isInfix()
            if (r5 == 0) goto L59
            r1 = r3
        L6f:
            if (r1 != 0) goto L77
            boolean r1 = r6.getAlwaysRenderModifiers()
            if (r1 == 0) goto L78
        L77:
            r3 = r4
        L78:
            boolean r1 = r7.isTailrec()
            java.lang.String r2 = "tailrec"
            r6.renderModifier(r8, r1, r2)
            r6.renderSuspendModifier(r7, r8)
            boolean r7 = r7.isInline()
            java.lang.String r1 = "inline"
            r6.renderModifier(r8, r7, r1)
            java.lang.String r7 = "infix"
            r6.renderModifier(r8, r3, r7)
            java.lang.String r7 = "operator"
            r6.renderModifier(r8, r0, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderAdditionalModifiers(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor, java.lang.StringBuilder):void");
    }

    private final List<String> renderAndSortAnnotationArguments(AnnotationDescriptor annotationDescriptor) {
        List list;
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        List plus;
        List<String> sorted;
        ClassConstructorDescriptor mo11511getUnsubstitutedPrimaryConstructor;
        List<ValueParameterDescriptor> valueParameters;
        int collectionSizeOrDefault3;
        Map<Name, ConstantValue<?>> allValueArguments = annotationDescriptor.getAllValueArguments();
        ClassDescriptor annotationClass = getRenderDefaultAnnotationArguments() ? DescriptorUtilsKt.getAnnotationClass(annotationDescriptor) : null;
        if (annotationClass == null || (mo11511getUnsubstitutedPrimaryConstructor = annotationClass.mo11511getUnsubstitutedPrimaryConstructor()) == null || (valueParameters = mo11511getUnsubstitutedPrimaryConstructor.getValueParameters()) == null) {
            list = null;
        } else {
            ArrayList<ValueParameterDescriptor> arrayList = new ArrayList();
            for (Object obj : valueParameters) {
                if (((ValueParameterDescriptor) obj).declaresDefaultValue()) {
                    arrayList.add(obj);
                }
            }
            collectionSizeOrDefault3 = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
            list = new ArrayList(collectionSizeOrDefault3);
            for (ValueParameterDescriptor it2 : arrayList) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                list.add(it2.getName());
            }
        }
        if (list == null) {
            list = CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : list) {
            if (!allValueArguments.containsKey((Name) obj2)) {
                arrayList2.add(obj2);
            }
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10);
        ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault);
        Iterator it3 = arrayList2.iterator();
        while (it3.hasNext()) {
            arrayList3.add(((Name) it3.next()).asString() + " = ...");
        }
        Set<Map.Entry<Name, ConstantValue<?>>> entrySet = allValueArguments.entrySet();
        collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(entrySet, 10);
        ArrayList arrayList4 = new ArrayList(collectionSizeOrDefault2);
        Iterator<T> it4 = entrySet.iterator();
        while (it4.hasNext()) {
            Map.Entry entry = (Map.Entry) it4.next();
            Name name = (Name) entry.getKey();
            ConstantValue<?> constantValue = (ConstantValue) entry.getValue();
            StringBuilder sb = new StringBuilder();
            sb.append(name.asString());
            sb.append(" = ");
            sb.append(!list.contains(name) ? renderConstant(constantValue) : "...");
            arrayList4.add(sb.toString());
        }
        plus = CollectionsKt___CollectionsKt.plus((Collection) arrayList3, (Iterable) arrayList4);
        sorted = CollectionsKt___CollectionsKt.sorted(plus);
        return sorted;
    }

    private final void renderAnnotations(@NotNull StringBuilder sb, Annotated annotated, AnnotationUseSiteTarget annotationUseSiteTarget) {
        boolean contains;
        if (!getModifiers().contains(DescriptorRendererModifier.ANNOTATIONS)) {
            return;
        }
        Set<FqName> excludedTypeAnnotationClasses = annotated instanceof KotlinType ? getExcludedTypeAnnotationClasses() : getExcludedAnnotationClasses();
        Function1<AnnotationDescriptor, Boolean> annotationFilter = getAnnotationFilter();
        for (AnnotationDescriptor annotationDescriptor : annotated.mo12070getAnnotations()) {
            contains = CollectionsKt___CollectionsKt.contains(excludedTypeAnnotationClasses, annotationDescriptor.getFqName());
            if (!contains && (annotationFilter == null || annotationFilter.mo12165invoke(annotationDescriptor).booleanValue())) {
                sb.append(renderAnnotation(annotationDescriptor, annotationUseSiteTarget));
                if (getEachAnnotationOnNewLine()) {
                    StringsKt__StringBuilderJVMKt.appendln(sb);
                } else {
                    sb.append(" ");
                }
            }
        }
    }

    static /* synthetic */ void renderAnnotations$default(DescriptorRendererImpl descriptorRendererImpl, StringBuilder sb, Annotated annotated, AnnotationUseSiteTarget annotationUseSiteTarget, int i, Object obj) {
        if ((i & 2) != 0) {
            annotationUseSiteTarget = null;
        }
        descriptorRendererImpl.renderAnnotations(sb, annotated, annotationUseSiteTarget);
    }

    private final void renderCapturedTypeParametersIfRequired(ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters, StringBuilder sb) {
        List<TypeParameterDescriptor> declaredTypeParameters = classifierDescriptorWithTypeParameters.getDeclaredTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters, "classifier.declaredTypeParameters");
        TypeConstructor mo11528getTypeConstructor = classifierDescriptorWithTypeParameters.mo11528getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "classifier.typeConstructor");
        List<TypeParameterDescriptor> parameters = mo11528getTypeConstructor.getParameters();
        Intrinsics.checkExpressionValueIsNotNull(parameters, "classifier.typeConstructor.parameters");
        if (!getVerbose() || !classifierDescriptorWithTypeParameters.isInner() || parameters.size() <= declaredTypeParameters.size()) {
            return;
        }
        sb.append(" /*captured type parameters: ");
        renderTypeParameterList(sb, parameters.subList(declaredTypeParameters.size(), parameters.size()));
        sb.append("*/");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderClass(ClassDescriptor classDescriptor, StringBuilder sb) {
        ClassConstructorDescriptor mo11511getUnsubstitutedPrimaryConstructor;
        boolean z = classDescriptor.getKind() == ClassKind.ENUM_ENTRY;
        if (!getStartFromName()) {
            renderAnnotations$default(this, sb, classDescriptor, null, 2, null);
            if (!z) {
                Visibility visibility = classDescriptor.getVisibility();
                Intrinsics.checkExpressionValueIsNotNull(visibility, "klass.visibility");
                renderVisibility(visibility, sb);
            }
            if (classDescriptor.getKind() != ClassKind.INTERFACE || classDescriptor.getModality() != Modality.ABSTRACT) {
                ClassKind kind = classDescriptor.getKind();
                Intrinsics.checkExpressionValueIsNotNull(kind, "klass.kind");
                if (!kind.isSingleton() || classDescriptor.getModality() != Modality.FINAL) {
                    Modality modality = classDescriptor.getModality();
                    Intrinsics.checkExpressionValueIsNotNull(modality, "klass.modality");
                    renderModality(modality, sb, implicitModalityWithoutExtensions(classDescriptor));
                }
            }
            renderMemberModifiers(classDescriptor, sb);
            renderModifier(sb, getModifiers().contains(DescriptorRendererModifier.INNER) && classDescriptor.isInner(), "inner");
            renderModifier(sb, getModifiers().contains(DescriptorRendererModifier.DATA) && classDescriptor.isData(), "data");
            renderModifier(sb, getModifiers().contains(DescriptorRendererModifier.INLINE) && classDescriptor.isInline(), Part.INLINE);
            renderClassKindPrefix(classDescriptor, sb);
        }
        if (!DescriptorUtils.isCompanionObject(classDescriptor)) {
            if (!getStartFromName()) {
                renderSpaceIfNeeded(sb);
            }
            renderName(classDescriptor, sb, true);
        } else {
            renderCompanionObjectName(classDescriptor, sb);
        }
        if (z) {
            return;
        }
        List<TypeParameterDescriptor> declaredTypeParameters = classDescriptor.getDeclaredTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters, "klass.declaredTypeParameters");
        renderTypeParameters(declaredTypeParameters, sb, false);
        renderCapturedTypeParametersIfRequired(classDescriptor, sb);
        ClassKind kind2 = classDescriptor.getKind();
        Intrinsics.checkExpressionValueIsNotNull(kind2, "klass.kind");
        if (!kind2.isSingleton() && getClassWithPrimaryConstructor() && (mo11511getUnsubstitutedPrimaryConstructor = classDescriptor.mo11511getUnsubstitutedPrimaryConstructor()) != null) {
            sb.append(" ");
            renderAnnotations$default(this, sb, mo11511getUnsubstitutedPrimaryConstructor, null, 2, null);
            Visibility visibility2 = mo11511getUnsubstitutedPrimaryConstructor.getVisibility();
            Intrinsics.checkExpressionValueIsNotNull(visibility2, "primaryConstructor.visibility");
            renderVisibility(visibility2, sb);
            sb.append(renderKeyword("constructor"));
            List<ValueParameterDescriptor> valueParameters = mo11511getUnsubstitutedPrimaryConstructor.getValueParameters();
            Intrinsics.checkExpressionValueIsNotNull(valueParameters, "primaryConstructor.valueParameters");
            renderValueParameters(valueParameters, mo11511getUnsubstitutedPrimaryConstructor.hasSynthesizedParameterNames(), sb);
        }
        renderSuperTypes(classDescriptor, sb);
        renderWhereSuffix(declaredTypeParameters, sb);
    }

    private final void renderClassKindPrefix(ClassDescriptor classDescriptor, StringBuilder sb) {
        sb.append(renderKeyword(DescriptorRenderer.Companion.getClassifierKindPrefix(classDescriptor)));
    }

    private final void renderCompanionObjectName(DeclarationDescriptor declarationDescriptor, StringBuilder sb) {
        if (getRenderCompanionObjectName()) {
            if (getStartFromName()) {
                sb.append("companion object");
            }
            renderSpaceIfNeeded(sb);
            DeclarationDescriptor mo11607getContainingDeclaration = declarationDescriptor.mo11607getContainingDeclaration();
            if (mo11607getContainingDeclaration != null) {
                sb.append("of ");
                Name name = mo11607getContainingDeclaration.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "containingDeclaration.name");
                sb.append(renderName(name, false));
            }
        }
        if (getVerbose() || (!Intrinsics.areEqual(declarationDescriptor.getName(), SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT))) {
            if (!getStartFromName()) {
                renderSpaceIfNeeded(sb);
            }
            Name name2 = declarationDescriptor.getName();
            Intrinsics.checkExpressionValueIsNotNull(name2, "descriptor.name");
            sb.append(renderName(name2, true));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String renderConstant(ConstantValue<?> constantValue) {
        String removePrefix;
        String joinToString$default;
        if (constantValue instanceof ArrayValue) {
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(((ArrayValue) constantValue).getValue(), ", ", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE, 0, null, new DescriptorRendererImpl$renderConstant$1(this), 24, null);
            return joinToString$default;
        } else if (constantValue instanceof AnnotationValue) {
            removePrefix = StringsKt__StringsKt.removePrefix(DescriptorRenderer.renderAnnotation$default(this, ((AnnotationValue) constantValue).getValue(), null, 2, null), (CharSequence) "@");
            return removePrefix;
        } else if (constantValue instanceof KClassValue) {
            KClassValue.Value value = ((KClassValue) constantValue).getValue();
            if (value instanceof KClassValue.Value.LocalClass) {
                return ((KClassValue.Value.LocalClass) value).getType() + "::class";
            } else if (!(value instanceof KClassValue.Value.NormalClass)) {
                throw new NoWhenBranchMatchedException();
            } else {
                KClassValue.Value.NormalClass normalClass = (KClassValue.Value.NormalClass) value;
                String asString = normalClass.getClassId().asSingleFqName().asString();
                Intrinsics.checkExpressionValueIsNotNull(asString, "classValue.classId.asSingleFqName().asString()");
                int arrayDimensions = normalClass.getArrayDimensions();
                for (int i = 0; i < arrayDimensions; i++) {
                    asString = GeneratedOutlineSupport1.outline73("kotlin.Array<", asString, Typography.greater);
                }
                return GeneratedOutlineSupport1.outline72(asString, "::class");
            }
        } else {
            return constantValue.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0029, code lost:
        if (r0.getModality() != kotlin.reflect.jvm.internal.impl.descriptors.Modality.SEALED) goto L53;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void renderConstructor(kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor r18, java.lang.StringBuilder r19) {
        /*
            Method dump skipped, instructions count: 281
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderConstructor(kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor, java.lang.StringBuilder):void");
    }

    private final void renderDefaultType(@NotNull StringBuilder sb, KotlinType kotlinType) {
        renderAnnotations$default(this, sb, kotlinType, null, 2, null);
        if (KotlinTypeKt.isError(kotlinType)) {
            if ((kotlinType instanceof UnresolvedType) && getPresentableUnresolvedTypes()) {
                sb.append(((UnresolvedType) kotlinType).getPresentableName());
            } else if ((kotlinType instanceof ErrorType) && !getInformativeErrorType()) {
                sb.append(((ErrorType) kotlinType).getPresentableName());
            } else {
                sb.append(kotlinType.mo12131getConstructor().toString());
            }
            sb.append(renderTypeArguments(kotlinType.getArguments()));
        } else {
            renderTypeConstructorAndArguments$default(this, sb, kotlinType, null, 2, null);
        }
        if (kotlinType.isMarkedNullable()) {
            sb.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        }
        if (SpecialTypesKt.isDefinitelyNotNullType(kotlinType)) {
            sb.append("!!");
        }
    }

    private final String renderError(String str) {
        int i = WhenMappings.$EnumSwitchMapping$1[getTextFormat().ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            return GeneratedOutlineSupport1.outline75("<font color=red><b>", str, "</b></font>");
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderFunction(FunctionDescriptor functionDescriptor, StringBuilder sb) {
        if (!getStartFromName()) {
            if (!getStartFromDeclarationKeyword()) {
                renderAnnotations$default(this, sb, functionDescriptor, null, 2, null);
                Visibility visibility = functionDescriptor.getVisibility();
                Intrinsics.checkExpressionValueIsNotNull(visibility, "function.visibility");
                renderVisibility(visibility, sb);
                renderModalityForCallable(functionDescriptor, sb);
                if (getIncludeAdditionalModifiers()) {
                    renderMemberModifiers(functionDescriptor, sb);
                }
                renderOverride(functionDescriptor, sb);
                if (getIncludeAdditionalModifiers()) {
                    renderAdditionalModifiers(functionDescriptor, sb);
                } else {
                    renderSuspendModifier(functionDescriptor, sb);
                }
                renderMemberKind(functionDescriptor, sb);
                if (getVerbose()) {
                    if (functionDescriptor.isHiddenToOvercomeSignatureClash()) {
                        sb.append("/*isHiddenToOvercomeSignatureClash*/ ");
                    }
                    if (functionDescriptor.isHiddenForResolutionEverywhereBesideSupercalls()) {
                        sb.append("/*isHiddenForResolutionEverywhereBesideSupercalls*/ ");
                    }
                }
            }
            sb.append(renderKeyword("fun"));
            sb.append(" ");
            List<TypeParameterDescriptor> typeParameters = functionDescriptor.getTypeParameters();
            Intrinsics.checkExpressionValueIsNotNull(typeParameters, "function.typeParameters");
            renderTypeParameters(typeParameters, sb, true);
            renderReceiver(functionDescriptor, sb);
        }
        renderName(functionDescriptor, sb, true);
        List<ValueParameterDescriptor> valueParameters = functionDescriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "function.valueParameters");
        renderValueParameters(valueParameters, functionDescriptor.hasSynthesizedParameterNames(), sb);
        renderReceiverAfterName(functionDescriptor, sb);
        KotlinType returnType = functionDescriptor.getReturnType();
        if (!getWithoutReturnType() && (getUnitReturnType() || returnType == null || !KotlinBuiltIns.isUnit(returnType))) {
            sb.append(RealTimeTextConstants.COLON_SPACE);
            sb.append(returnType == null ? "[NULL]" : renderType(returnType));
        }
        List<TypeParameterDescriptor> typeParameters2 = functionDescriptor.getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters2, "function.typeParameters");
        renderWhereSuffix(typeParameters2, sb);
    }

    private final void renderFunctionType(@NotNull StringBuilder sb, KotlinType kotlinType) {
        Name name;
        char last;
        int lastIndex;
        int lastIndex2;
        int length = sb.length();
        renderAnnotations$default(getFunctionTypeAnnotationsRenderer(), sb, kotlinType, null, 2, null);
        boolean z = true;
        boolean z2 = sb.length() != length;
        boolean isSuspendFunctionType = FunctionTypesKt.isSuspendFunctionType(kotlinType);
        boolean isMarkedNullable = kotlinType.isMarkedNullable();
        KotlinType receiverTypeFromFunctionType = FunctionTypesKt.getReceiverTypeFromFunctionType(kotlinType);
        boolean z3 = isMarkedNullable || (z2 && receiverTypeFromFunctionType != null);
        if (z3) {
            if (isSuspendFunctionType) {
                sb.insert(length, '(');
            } else {
                if (z2) {
                    last = StringsKt___StringsKt.last(sb);
                    boolean z4 = last == ' ';
                    if (_Assertions.ENABLED && !z4) {
                        throw new AssertionError("Assertion failed");
                    }
                    lastIndex = StringsKt__StringsKt.getLastIndex(sb);
                    if (sb.charAt(lastIndex - 1) != ')') {
                        lastIndex2 = StringsKt__StringsKt.getLastIndex(sb);
                        sb.insert(lastIndex2, "()");
                    }
                }
                sb.append("(");
            }
        }
        renderModifier(sb, isSuspendFunctionType, "suspend");
        if (receiverTypeFromFunctionType != null) {
            if ((!shouldRenderAsPrettyFunctionType(receiverTypeFromFunctionType) || receiverTypeFromFunctionType.isMarkedNullable()) && !hasModifiersOrAnnotations(receiverTypeFromFunctionType)) {
                z = false;
            }
            if (z) {
                sb.append("(");
            }
            renderNormalizedType(sb, receiverTypeFromFunctionType);
            if (z) {
                sb.append(")");
            }
            sb.append(".");
        }
        sb.append("(");
        int i = 0;
        for (TypeProjection typeProjection : FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType)) {
            if (i > 0) {
                sb.append(", ");
            }
            if (getParameterNamesInFunctionalTypes()) {
                KotlinType type = typeProjection.getType();
                Intrinsics.checkExpressionValueIsNotNull(type, "typeProjection.type");
                name = FunctionTypesKt.extractParameterNameFromFunctionTypeArgument(type);
            } else {
                name = null;
            }
            if (name != null) {
                sb.append(renderName(name, false));
                sb.append(RealTimeTextConstants.COLON_SPACE);
            }
            sb.append(getFunctionTypeParameterTypesRenderer().renderTypeProjection(typeProjection));
            i++;
        }
        sb.append(") ");
        sb.append(arrow());
        sb.append(" ");
        renderNormalizedType(sb, FunctionTypesKt.getReturnTypeFromFunctionType(kotlinType));
        if (z3) {
            sb.append(")");
        }
        if (isMarkedNullable) {
            sb.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        }
    }

    private final void renderInitializer(VariableDescriptor variableDescriptor, StringBuilder sb) {
        ConstantValue<?> constant;
        if (!getIncludePropertyConstant() || (constant = variableDescriptor.mo11606getCompileTimeInitializer()) == null) {
            return;
        }
        sb.append(" = ");
        Intrinsics.checkExpressionValueIsNotNull(constant, "constant");
        sb.append(escape(renderConstant(constant)));
    }

    private final String renderKeyword(String str) {
        int i = WhenMappings.$EnumSwitchMapping$0[getTextFormat().ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            return getBoldOnlyForNamesInHtml() ? str : GeneratedOutlineSupport1.outline75("<b>", str, "</b>");
        }
        return str;
    }

    private final void renderMemberKind(CallableMemberDescriptor callableMemberDescriptor, StringBuilder sb) {
        if (getModifiers().contains(DescriptorRendererModifier.MEMBER_KIND) && getVerbose() && callableMemberDescriptor.getKind() != CallableMemberDescriptor.Kind.DECLARATION) {
            sb.append("/*");
            String name = callableMemberDescriptor.getKind().name();
            if (name == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = name.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
            sb.append(lowerCase);
            sb.append("*/ ");
        }
    }

    private final void renderMemberModifiers(MemberDescriptor memberDescriptor, StringBuilder sb) {
        renderModifier(sb, memberDescriptor.isExternal(), "external");
        boolean z = true;
        renderModifier(sb, getModifiers().contains(DescriptorRendererModifier.EXPECT) && memberDescriptor.isExpect(), "expect");
        if (!getModifiers().contains(DescriptorRendererModifier.ACTUAL) || !memberDescriptor.isActual()) {
            z = false;
        }
        renderModifier(sb, z, "actual");
    }

    private final void renderModality(Modality modality, StringBuilder sb, Modality modality2) {
        if (getRenderDefaultModality() || modality != modality2) {
            boolean contains = getModifiers().contains(DescriptorRendererModifier.MODALITY);
            String name = modality.name();
            if (name == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = name.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
            renderModifier(sb, contains, lowerCase);
        }
    }

    private final void renderModalityForCallable(CallableMemberDescriptor callableMemberDescriptor, StringBuilder sb) {
        if (!DescriptorUtils.isTopLevelDeclaration(callableMemberDescriptor) || callableMemberDescriptor.getModality() != Modality.FINAL) {
            if (getOverrideRenderingPolicy() == OverrideRenderingPolicy.RENDER_OVERRIDE && callableMemberDescriptor.getModality() == Modality.OPEN && overridesSomething(callableMemberDescriptor)) {
                return;
            }
            Modality modality = callableMemberDescriptor.getModality();
            Intrinsics.checkExpressionValueIsNotNull(modality, "callable.modality");
            renderModality(modality, sb, implicitModalityWithoutExtensions(callableMemberDescriptor));
        }
    }

    private final void renderModifier(StringBuilder sb, boolean z, String str) {
        if (z) {
            sb.append(renderKeyword(str));
            sb.append(" ");
        }
    }

    private final void renderNormalizedType(@NotNull StringBuilder sb, KotlinType kotlinType) {
        UnwrappedType unwrap = kotlinType.unwrap();
        if (!(unwrap instanceof AbbreviatedType)) {
            unwrap = null;
        }
        AbbreviatedType abbreviatedType = (AbbreviatedType) unwrap;
        if (abbreviatedType != null) {
            if (getRenderTypeExpansions()) {
                renderNormalizedTypeAsIs(sb, abbreviatedType.getExpandedType());
                return;
            }
            renderNormalizedTypeAsIs(sb, abbreviatedType.getAbbreviation());
            if (!getRenderUnabbreviatedType()) {
                return;
            }
            renderAbbreviatedTypeExpansion(sb, abbreviatedType);
            return;
        }
        renderNormalizedTypeAsIs(sb, kotlinType);
    }

    private final void renderNormalizedTypeAsIs(@NotNull StringBuilder sb, KotlinType kotlinType) {
        if ((kotlinType instanceof WrappedType) && getDebugMode() && !((WrappedType) kotlinType).isComputed()) {
            sb.append("<Not computed yet>");
            return;
        }
        UnwrappedType unwrap = kotlinType.unwrap();
        if (unwrap instanceof FlexibleType) {
            sb.append(((FlexibleType) unwrap).render(this, this));
        } else if (!(unwrap instanceof SimpleType)) {
        } else {
            renderSimpleType(sb, (SimpleType) unwrap);
        }
    }

    private final void renderOverride(CallableMemberDescriptor callableMemberDescriptor, StringBuilder sb) {
        if (getModifiers().contains(DescriptorRendererModifier.OVERRIDE) && overridesSomething(callableMemberDescriptor) && getOverrideRenderingPolicy() != OverrideRenderingPolicy.RENDER_OPEN) {
            renderModifier(sb, true, "override");
            if (!getVerbose()) {
                return;
            }
            sb.append("/*");
            sb.append(callableMemberDescriptor.getOverriddenDescriptors().size());
            sb.append("*/ ");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderPackageFragment(PackageFragmentDescriptor packageFragmentDescriptor, StringBuilder sb) {
        renderPackageHeader(packageFragmentDescriptor.getFqName(), "package-fragment", sb);
        if (getDebugMode()) {
            sb.append(" in ");
            renderName(packageFragmentDescriptor.mo11607getContainingDeclaration(), sb, false);
        }
    }

    private final void renderPackageHeader(FqName fqName, String str, StringBuilder sb) {
        sb.append(renderKeyword(str));
        FqNameUnsafe unsafe = fqName.toUnsafe();
        Intrinsics.checkExpressionValueIsNotNull(unsafe, "fqName.toUnsafe()");
        String renderFqName = renderFqName(unsafe);
        if (renderFqName.length() > 0) {
            sb.append(" ");
            sb.append(renderFqName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderPackageView(PackageViewDescriptor packageViewDescriptor, StringBuilder sb) {
        renderPackageHeader(packageViewDescriptor.getFqName(), "package", sb);
        if (getDebugMode()) {
            sb.append(" in context of ");
            renderName(packageViewDescriptor.mo11567getModule(), sb, false);
        }
    }

    private final void renderPossiblyInnerType(@NotNull StringBuilder sb, PossiblyInnerType possiblyInnerType) {
        PossiblyInnerType outerType = possiblyInnerType.getOuterType();
        if (outerType != null) {
            renderPossiblyInnerType(sb, outerType);
            sb.append('.');
            Name name = possiblyInnerType.getClassifierDescriptor().getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "possiblyInnerType.classifierDescriptor.name");
            sb.append(renderName(name, false));
        } else {
            TypeConstructor mo11528getTypeConstructor = possiblyInnerType.getClassifierDescriptor().mo11528getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "possiblyInnerType.classi…escriptor.typeConstructor");
            sb.append(renderTypeConstructor(mo11528getTypeConstructor));
        }
        sb.append(renderTypeArguments(possiblyInnerType.getArguments()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderProperty(PropertyDescriptor propertyDescriptor, StringBuilder sb) {
        if (!getStartFromName()) {
            if (!getStartFromDeclarationKeyword()) {
                renderPropertyAnnotations(propertyDescriptor, sb);
                Visibility visibility = propertyDescriptor.getVisibility();
                Intrinsics.checkExpressionValueIsNotNull(visibility, "property.visibility");
                renderVisibility(visibility, sb);
                boolean z = false;
                renderModifier(sb, getModifiers().contains(DescriptorRendererModifier.CONST) && propertyDescriptor.isConst(), "const");
                renderMemberModifiers(propertyDescriptor, sb);
                renderModalityForCallable(propertyDescriptor, sb);
                renderOverride(propertyDescriptor, sb);
                if (getModifiers().contains(DescriptorRendererModifier.LATEINIT) && propertyDescriptor.isLateInit()) {
                    z = true;
                }
                renderModifier(sb, z, "lateinit");
                renderMemberKind(propertyDescriptor, sb);
            }
            renderValVarPrefix$default(this, propertyDescriptor, sb, false, 4, null);
            List<TypeParameterDescriptor> typeParameters = propertyDescriptor.getTypeParameters();
            Intrinsics.checkExpressionValueIsNotNull(typeParameters, "property.typeParameters");
            renderTypeParameters(typeParameters, sb, true);
            renderReceiver(propertyDescriptor, sb);
        }
        renderName(propertyDescriptor, sb, true);
        sb.append(RealTimeTextConstants.COLON_SPACE);
        KotlinType type = propertyDescriptor.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "property.type");
        sb.append(renderType(type));
        renderReceiverAfterName(propertyDescriptor, sb);
        renderInitializer(propertyDescriptor, sb);
        List<TypeParameterDescriptor> typeParameters2 = propertyDescriptor.getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters2, "property.typeParameters");
        renderWhereSuffix(typeParameters2, sb);
    }

    private final void renderPropertyAnnotations(PropertyDescriptor propertyDescriptor, StringBuilder sb) {
        if (!getModifiers().contains(DescriptorRendererModifier.ANNOTATIONS)) {
            return;
        }
        renderAnnotations$default(this, sb, propertyDescriptor, null, 2, null);
        FieldDescriptor it2 = propertyDescriptor.getBackingField();
        if (it2 != null) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            renderAnnotations(sb, it2, AnnotationUseSiteTarget.FIELD);
        }
        FieldDescriptor it3 = propertyDescriptor.getDelegateField();
        if (it3 != null) {
            Intrinsics.checkExpressionValueIsNotNull(it3, "it");
            renderAnnotations(sb, it3, AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD);
        }
        if (getPropertyAccessorRenderingPolicy() != PropertyAccessorRenderingPolicy.NONE) {
            return;
        }
        PropertyGetterDescriptor it4 = propertyDescriptor.mo11581getGetter();
        if (it4 != null) {
            Intrinsics.checkExpressionValueIsNotNull(it4, "it");
            renderAnnotations(sb, it4, AnnotationUseSiteTarget.PROPERTY_GETTER);
        }
        PropertySetterDescriptor setter = propertyDescriptor.getSetter();
        if (setter == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(setter, "it");
        renderAnnotations(sb, setter, AnnotationUseSiteTarget.PROPERTY_SETTER);
        Intrinsics.checkExpressionValueIsNotNull(setter, "setter");
        List<ValueParameterDescriptor> valueParameters = setter.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "setter.valueParameters");
        ValueParameterDescriptor it5 = (ValueParameterDescriptor) CollectionsKt.single((List<? extends Object>) valueParameters);
        Intrinsics.checkExpressionValueIsNotNull(it5, "it");
        renderAnnotations(sb, it5, AnnotationUseSiteTarget.SETTER_PARAMETER);
    }

    private final void renderReceiver(CallableDescriptor callableDescriptor, StringBuilder sb) {
        ReceiverParameterDescriptor extensionReceiverParameter = callableDescriptor.getExtensionReceiverParameter();
        if (extensionReceiverParameter != null) {
            renderAnnotations(sb, extensionReceiverParameter, AnnotationUseSiteTarget.RECEIVER);
            KotlinType type = extensionReceiverParameter.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "receiver.type");
            String renderType = renderType(type);
            if (shouldRenderAsPrettyFunctionType(type) && !TypeUtils.isNullableType(type)) {
                renderType = '(' + renderType + ')';
            }
            sb.append(renderType);
            sb.append(".");
        }
    }

    private final void renderReceiverAfterName(CallableDescriptor callableDescriptor, StringBuilder sb) {
        ReceiverParameterDescriptor extensionReceiverParameter;
        if (getReceiverAfterName() && (extensionReceiverParameter = callableDescriptor.getExtensionReceiverParameter()) != null) {
            sb.append(" on ");
            KotlinType type = extensionReceiverParameter.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "receiver.type");
            sb.append(renderType(type));
        }
    }

    private final void renderSimpleType(@NotNull StringBuilder sb, SimpleType simpleType) {
        if (!Intrinsics.areEqual(simpleType, TypeUtils.CANT_INFER_FUNCTION_PARAM_TYPE) && !TypeUtils.isDontCarePlaceholder(simpleType)) {
            if (ErrorUtils.isUninferredParameter(simpleType)) {
                if (getUninferredTypeParameterAsName()) {
                    TypeConstructor mo12131getConstructor = simpleType.mo12131getConstructor();
                    if (mo12131getConstructor == null) {
                        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.types.ErrorUtils.UninferredParameterTypeConstructor");
                    }
                    TypeParameterDescriptor typeParameterDescriptor = ((ErrorUtils.UninferredParameterTypeConstructor) mo12131getConstructor).getTypeParameterDescriptor();
                    Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor, "(type.constructor as Uni…).typeParameterDescriptor");
                    String name = typeParameterDescriptor.getName().toString();
                    Intrinsics.checkExpressionValueIsNotNull(name, "(type.constructor as Uni…escriptor.name.toString()");
                    sb.append(renderError(name));
                    return;
                }
                sb.append("???");
                return;
            } else if (KotlinTypeKt.isError(simpleType)) {
                renderDefaultType(sb, simpleType);
                return;
            } else if (shouldRenderAsPrettyFunctionType(simpleType)) {
                renderFunctionType(sb, simpleType);
                return;
            } else {
                renderDefaultType(sb, simpleType);
                return;
            }
        }
        sb.append("???");
    }

    private final void renderSpaceIfNeeded(StringBuilder sb) {
        int length = sb.length();
        if (length == 0 || sb.charAt(length - 1) != ' ') {
            sb.append(Chars.SPACE);
        }
    }

    private final void renderSuperTypes(ClassDescriptor classDescriptor, StringBuilder sb) {
        if (!getWithoutSuperTypes() && !KotlinBuiltIns.isNothing(classDescriptor.getDefaultType())) {
            TypeConstructor mo11528getTypeConstructor = classDescriptor.mo11528getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "klass.typeConstructor");
            Collection<KotlinType> mo12135getSupertypes = mo11528getTypeConstructor.mo12135getSupertypes();
            Intrinsics.checkExpressionValueIsNotNull(mo12135getSupertypes, "klass.typeConstructor.supertypes");
            if (mo12135getSupertypes.isEmpty()) {
                return;
            }
            if (mo12135getSupertypes.size() == 1 && KotlinBuiltIns.isAnyOrNullableAny(mo12135getSupertypes.iterator().next())) {
                return;
            }
            renderSpaceIfNeeded(sb);
            sb.append(RealTimeTextConstants.COLON_SPACE);
            CollectionsKt___CollectionsKt.joinTo$default(mo12135getSupertypes, sb, ", ", null, null, 0, null, new DescriptorRendererImpl$renderSuperTypes$1(this), 60, null);
        }
    }

    private final void renderSuspendModifier(FunctionDescriptor functionDescriptor, StringBuilder sb) {
        renderModifier(sb, functionDescriptor.isSuspend(), "suspend");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderTypeAlias(TypeAliasDescriptor typeAliasDescriptor, StringBuilder sb) {
        renderAnnotations$default(this, sb, typeAliasDescriptor, null, 2, null);
        Visibility visibility = typeAliasDescriptor.getVisibility();
        Intrinsics.checkExpressionValueIsNotNull(visibility, "typeAlias.visibility");
        renderVisibility(visibility, sb);
        renderMemberModifiers(typeAliasDescriptor, sb);
        sb.append(renderKeyword("typealias"));
        sb.append(" ");
        renderName(typeAliasDescriptor, sb, true);
        List<TypeParameterDescriptor> declaredTypeParameters = typeAliasDescriptor.getDeclaredTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(declaredTypeParameters, "typeAlias.declaredTypeParameters");
        renderTypeParameters(declaredTypeParameters, sb, false);
        renderCapturedTypeParametersIfRequired(typeAliasDescriptor, sb);
        sb.append(" = ");
        sb.append(renderType(typeAliasDescriptor.getUnderlyingType()));
    }

    private final void renderTypeConstructorAndArguments(@NotNull StringBuilder sb, KotlinType kotlinType, TypeConstructor typeConstructor) {
        PossiblyInnerType buildPossiblyInnerType = TypeParameterUtilsKt.buildPossiblyInnerType(kotlinType);
        if (buildPossiblyInnerType == null) {
            sb.append(renderTypeConstructor(typeConstructor));
            sb.append(renderTypeArguments(kotlinType.getArguments()));
            return;
        }
        renderPossiblyInnerType(sb, buildPossiblyInnerType);
    }

    static /* synthetic */ void renderTypeConstructorAndArguments$default(DescriptorRendererImpl descriptorRendererImpl, StringBuilder sb, KotlinType kotlinType, TypeConstructor typeConstructor, int i, Object obj) {
        if ((i & 2) != 0) {
            typeConstructor = kotlinType.mo12131getConstructor();
        }
        descriptorRendererImpl.renderTypeConstructorAndArguments(sb, kotlinType, typeConstructor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderTypeParameter(TypeParameterDescriptor typeParameterDescriptor, StringBuilder sb, boolean z) {
        if (z) {
            sb.append(lt());
        }
        if (getVerbose()) {
            sb.append("/*");
            sb.append(typeParameterDescriptor.getIndex());
            sb.append("*/ ");
        }
        renderModifier(sb, typeParameterDescriptor.isReified(), "reified");
        String label = typeParameterDescriptor.getVariance().getLabel();
        boolean z2 = true;
        renderModifier(sb, label.length() > 0, label);
        renderAnnotations$default(this, sb, typeParameterDescriptor, null, 2, null);
        renderName(typeParameterDescriptor, sb, z);
        int size = typeParameterDescriptor.getUpperBounds().size();
        if ((size > 1 && !z) || size == 1) {
            KotlinType upperBound = typeParameterDescriptor.getUpperBounds().iterator().next();
            if (!KotlinBuiltIns.isDefaultBound(upperBound)) {
                sb.append(" : ");
                Intrinsics.checkExpressionValueIsNotNull(upperBound, "upperBound");
                sb.append(renderType(upperBound));
            }
        } else if (z) {
            for (KotlinType upperBound2 : typeParameterDescriptor.getUpperBounds()) {
                if (!KotlinBuiltIns.isDefaultBound(upperBound2)) {
                    if (z2) {
                        sb.append(" : ");
                    } else {
                        sb.append(" & ");
                    }
                    Intrinsics.checkExpressionValueIsNotNull(upperBound2, "upperBound");
                    sb.append(renderType(upperBound2));
                    z2 = false;
                }
            }
        }
        if (z) {
            sb.append(gt());
        }
    }

    private final void renderTypeParameterList(StringBuilder sb, List<? extends TypeParameterDescriptor> list) {
        Iterator<? extends TypeParameterDescriptor> it2 = list.iterator();
        while (it2.hasNext()) {
            renderTypeParameter(it2.next(), sb, false);
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
    }

    private final void renderTypeParameters(List<? extends TypeParameterDescriptor> list, StringBuilder sb, boolean z) {
        if (!getWithoutTypeParameters() && (!list.isEmpty())) {
            sb.append(lt());
            renderTypeParameterList(sb, list);
            sb.append(gt());
            if (!z) {
                return;
            }
            sb.append(" ");
        }
    }

    private final void renderValVarPrefix(VariableDescriptor variableDescriptor, StringBuilder sb, boolean z) {
        if (z || !(variableDescriptor instanceof ValueParameterDescriptor)) {
            sb.append(renderKeyword(variableDescriptor.isVar() ? "var" : MetricKeys.NON_EMPTY_VALUE));
            sb.append(" ");
        }
    }

    static /* synthetic */ void renderValVarPrefix$default(DescriptorRendererImpl descriptorRendererImpl, VariableDescriptor variableDescriptor, StringBuilder sb, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        descriptorRendererImpl.renderValVarPrefix(variableDescriptor, sb, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void renderValueParameter(kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r10, boolean r11, java.lang.StringBuilder r12, boolean r13) {
        /*
            r9 = this;
            if (r13 == 0) goto L10
            java.lang.String r0 = "value-parameter"
            java.lang.String r0 = r9.renderKeyword(r0)
            r12.append(r0)
            java.lang.String r0 = " "
            r12.append(r0)
        L10:
            boolean r0 = r9.getVerbose()
            if (r0 == 0) goto L27
            java.lang.String r0 = "/*"
            r12.append(r0)
            int r0 = r10.getIndex()
            r12.append(r0)
        */
        //  java.lang.String r0 = "*/ "
        /*
            r12.append(r0)
        L27:
            r4 = 0
            r5 = 2
            r6 = 0
            r1 = r9
            r2 = r12
            r3 = r10
            renderAnnotations$default(r1, r2, r3, r4, r5, r6)
            boolean r0 = r10.isCrossinline()
            java.lang.String r1 = "crossinline"
            r9.renderModifier(r12, r0, r1)
            boolean r0 = r10.isNoinline()
            java.lang.String r1 = "noinline"
            r9.renderModifier(r12, r0, r1)
            boolean r0 = r9.getRenderPrimaryConstructorParametersAsProperties()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L5f
            kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor r0 = r10.mo11607getContainingDeclaration()
            boolean r3 = r0 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor
            if (r3 != 0) goto L53
            r0 = 0
        L53:
            kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor) r0
            if (r0 == 0) goto L5f
            boolean r0 = r0.isPrimary()
            if (r0 != r2) goto L5f
            r8 = r2
            goto L60
        L5f:
            r8 = r1
        L60:
            if (r8 == 0) goto L6b
            boolean r0 = r9.getActualPropertiesInPrimaryConstructor()
            java.lang.String r3 = "actual"
            r9.renderModifier(r12, r0, r3)
        L6b:
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
            r7 = r13
            r3.renderVariable(r4, r5, r6, r7, r8)
            kotlin.jvm.functions.Function1 r11 = r9.getDefaultParameterValueRenderer()
            if (r11 == 0) goto L8b
            boolean r11 = r9.getDebugMode()
            if (r11 == 0) goto L84
            boolean r11 = r10.declaresDefaultValue()
            goto L88
        L84:
            boolean r11 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.declaresOrInheritsDefaultValue(r10)
        L88:
            if (r11 == 0) goto L8b
            r1 = r2
        L8b:
            if (r1 == 0) goto Lac
            java.lang.String r11 = " = "
            java.lang.StringBuilder r11 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r11)
            kotlin.jvm.functions.Function1 r13 = r9.getDefaultParameterValueRenderer()
            if (r13 != 0) goto L9c
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L9c:
            java.lang.Object r10 = r13.mo12165invoke(r10)
            java.lang.String r10 = (java.lang.String) r10
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            r12.append(r10)
        Lac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderValueParameter(kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor, boolean, java.lang.StringBuilder, boolean):void");
    }

    private final void renderValueParameters(Collection<? extends ValueParameterDescriptor> collection, boolean z, StringBuilder sb) {
        boolean shouldRenderParameterNames = shouldRenderParameterNames(z);
        int size = collection.size();
        getValueParametersHandler().appendBeforeValueParameters(size, sb);
        int i = 0;
        for (ValueParameterDescriptor valueParameterDescriptor : collection) {
            getValueParametersHandler().appendBeforeValueParameter(valueParameterDescriptor, i, size, sb);
            renderValueParameter(valueParameterDescriptor, shouldRenderParameterNames, sb, false);
            getValueParametersHandler().appendAfterValueParameter(valueParameterDescriptor, i, size, sb);
            i++;
        }
        getValueParametersHandler().appendAfterValueParameters(size, sb);
    }

    private final void renderVariable(VariableDescriptor variableDescriptor, boolean z, StringBuilder sb, boolean z2, boolean z3) {
        KotlinType type = variableDescriptor.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "variable.type");
        ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) (!(variableDescriptor instanceof ValueParameterDescriptor) ? null : variableDescriptor);
        KotlinType varargElementType = valueParameterDescriptor != null ? valueParameterDescriptor.getVarargElementType() : null;
        KotlinType kotlinType = varargElementType != null ? varargElementType : type;
        renderModifier(sb, varargElementType != null, "vararg");
        if (z3 || (z2 && !getStartFromName())) {
            renderValVarPrefix(variableDescriptor, sb, z3);
        }
        if (z) {
            renderName(variableDescriptor, sb, z2);
            sb.append(RealTimeTextConstants.COLON_SPACE);
        }
        sb.append(renderType(kotlinType));
        renderInitializer(variableDescriptor, sb);
        if (!getVerbose() || varargElementType == null) {
            return;
        }
        sb.append(" /*");
        sb.append(renderType(type));
        sb.append("*/");
    }

    private final boolean renderVisibility(Visibility visibility, StringBuilder sb) {
        if (!getModifiers().contains(DescriptorRendererModifier.VISIBILITY)) {
            return false;
        }
        if (getNormalizedVisibilities()) {
            visibility = visibility.normalize();
        }
        if (!getRenderDefaultVisibility() && Intrinsics.areEqual(visibility, Visibilities.DEFAULT_VISIBILITY)) {
            return false;
        }
        sb.append(renderKeyword(visibility.getInternalDisplayName()));
        sb.append(" ");
        return true;
    }

    private final void renderWhereSuffix(List<? extends TypeParameterDescriptor> list, StringBuilder sb) {
        List<KotlinType> drop;
        if (getWithoutTypeParameters()) {
            return;
        }
        ArrayList arrayList = new ArrayList(0);
        for (TypeParameterDescriptor typeParameterDescriptor : list) {
            List<KotlinType> upperBounds = typeParameterDescriptor.getUpperBounds();
            Intrinsics.checkExpressionValueIsNotNull(upperBounds, "typeParameter.upperBounds");
            drop = CollectionsKt___CollectionsKt.drop(upperBounds, 1);
            for (KotlinType it2 : drop) {
                StringBuilder sb2 = new StringBuilder();
                Name name = typeParameterDescriptor.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "typeParameter.name");
                sb2.append(renderName(name, false));
                sb2.append(" : ");
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                sb2.append(renderType(it2));
                arrayList.add(sb2.toString());
            }
        }
        if (!(!arrayList.isEmpty())) {
            return;
        }
        sb.append(" ");
        sb.append(renderKeyword("where"));
        sb.append(" ");
        CollectionsKt___CollectionsKt.joinTo$default(arrayList, sb, ", ", null, null, 0, null, null, 124, null);
    }

    private final String replacePrefixes(String str, String str2, String str3, String str4, String str5) {
        boolean startsWith$default;
        boolean startsWith$default2;
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, str2, false, 2, null);
        if (startsWith$default) {
            startsWith$default2 = StringsKt__StringsJVMKt.startsWith$default(str3, str4, false, 2, null);
            if (startsWith$default2) {
                String substring = str.substring(str2.length());
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                String substring2 = str3.substring(str4.length());
                Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
                String outline72 = GeneratedOutlineSupport1.outline72(str5, substring);
                if (Intrinsics.areEqual(substring, substring2)) {
                    return outline72;
                }
                if (differsOnlyInNullability(substring, substring2)) {
                    return GeneratedOutlineSupport1.outline47(outline72, '!');
                }
            }
        }
        return null;
    }

    private final boolean shouldRenderAsPrettyFunctionType(KotlinType kotlinType) {
        boolean z;
        if (FunctionTypesKt.isBuiltinFunctionalType(kotlinType)) {
            List<TypeProjection> arguments = kotlinType.getArguments();
            if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
                for (TypeProjection typeProjection : arguments) {
                    if (typeProjection.isStarProjection()) {
                        z = false;
                        break;
                    }
                }
            }
            z = true;
            return z;
        }
        return false;
    }

    private final boolean shouldRenderParameterNames(boolean z) {
        int i = WhenMappings.$EnumSwitchMapping$4[getParameterNameRenderingPolicy().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                return false;
            } else if (z) {
                return false;
            }
        }
        return true;
    }

    public boolean getActualPropertiesInPrimaryConstructor() {
        return this.options.getActualPropertiesInPrimaryConstructor();
    }

    public boolean getAlwaysRenderModifiers() {
        return this.options.getAlwaysRenderModifiers();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    @NotNull
    public AnnotationArgumentsRenderingPolicy getAnnotationArgumentsRenderingPolicy() {
        return this.options.getAnnotationArgumentsRenderingPolicy();
    }

    @Nullable
    public Function1<AnnotationDescriptor, Boolean> getAnnotationFilter() {
        return this.options.getAnnotationFilter();
    }

    public boolean getBoldOnlyForNamesInHtml() {
        return this.options.getBoldOnlyForNamesInHtml();
    }

    public boolean getClassWithPrimaryConstructor() {
        return this.options.getClassWithPrimaryConstructor();
    }

    @NotNull
    public ClassifierNamePolicy getClassifierNamePolicy() {
        return this.options.getClassifierNamePolicy();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public boolean getDebugMode() {
        return this.options.getDebugMode();
    }

    @Nullable
    public Function1<ValueParameterDescriptor, String> getDefaultParameterValueRenderer() {
        return this.options.getDefaultParameterValueRenderer();
    }

    public boolean getEachAnnotationOnNewLine() {
        return this.options.getEachAnnotationOnNewLine();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public boolean getEnhancedTypes() {
        return this.options.getEnhancedTypes();
    }

    @NotNull
    public Set<FqName> getExcludedAnnotationClasses() {
        return this.options.getExcludedAnnotationClasses();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    @NotNull
    public Set<FqName> getExcludedTypeAnnotationClasses() {
        return this.options.getExcludedTypeAnnotationClasses();
    }

    public boolean getIncludeAdditionalModifiers() {
        return this.options.getIncludeAdditionalModifiers();
    }

    public boolean getIncludeAnnotationArguments() {
        return this.options.getIncludeAnnotationArguments();
    }

    public boolean getIncludeEmptyAnnotationArguments() {
        return this.options.getIncludeEmptyAnnotationArguments();
    }

    public boolean getIncludePropertyConstant() {
        return this.options.getIncludePropertyConstant();
    }

    public boolean getInformativeErrorType() {
        return this.options.getInformativeErrorType();
    }

    @NotNull
    public Set<DescriptorRendererModifier> getModifiers() {
        return this.options.getModifiers();
    }

    public boolean getNormalizedVisibilities() {
        return this.options.getNormalizedVisibilities();
    }

    @NotNull
    public final DescriptorRendererOptionsImpl getOptions() {
        return this.options;
    }

    @NotNull
    public OverrideRenderingPolicy getOverrideRenderingPolicy() {
        return this.options.getOverrideRenderingPolicy();
    }

    @NotNull
    public ParameterNameRenderingPolicy getParameterNameRenderingPolicy() {
        return this.options.getParameterNameRenderingPolicy();
    }

    public boolean getParameterNamesInFunctionalTypes() {
        return this.options.getParameterNamesInFunctionalTypes();
    }

    public boolean getPresentableUnresolvedTypes() {
        return this.options.getPresentableUnresolvedTypes();
    }

    @NotNull
    public PropertyAccessorRenderingPolicy getPropertyAccessorRenderingPolicy() {
        return this.options.getPropertyAccessorRenderingPolicy();
    }

    public boolean getReceiverAfterName() {
        return this.options.getReceiverAfterName();
    }

    public boolean getRenderCompanionObjectName() {
        return this.options.getRenderCompanionObjectName();
    }

    public boolean getRenderConstructorDelegation() {
        return this.options.getRenderConstructorDelegation();
    }

    public boolean getRenderConstructorKeyword() {
        return this.options.getRenderConstructorKeyword();
    }

    public boolean getRenderDefaultAnnotationArguments() {
        return this.options.getRenderDefaultAnnotationArguments();
    }

    public boolean getRenderDefaultModality() {
        return this.options.getRenderDefaultModality();
    }

    public boolean getRenderDefaultVisibility() {
        return this.options.getRenderDefaultVisibility();
    }

    public boolean getRenderPrimaryConstructorParametersAsProperties() {
        return this.options.getRenderPrimaryConstructorParametersAsProperties();
    }

    public boolean getRenderTypeExpansions() {
        return this.options.getRenderTypeExpansions();
    }

    public boolean getRenderUnabbreviatedType() {
        return this.options.getRenderUnabbreviatedType();
    }

    public boolean getSecondaryConstructorsAsPrimary() {
        return this.options.getSecondaryConstructorsAsPrimary();
    }

    public boolean getStartFromDeclarationKeyword() {
        return this.options.getStartFromDeclarationKeyword();
    }

    public boolean getStartFromName() {
        return this.options.getStartFromName();
    }

    @NotNull
    public RenderingFormat getTextFormat() {
        return this.options.getTextFormat();
    }

    @NotNull
    public Function1<KotlinType, KotlinType> getTypeNormalizer() {
        return this.options.getTypeNormalizer();
    }

    public boolean getUninferredTypeParameterAsName() {
        return this.options.getUninferredTypeParameterAsName();
    }

    public boolean getUnitReturnType() {
        return this.options.getUnitReturnType();
    }

    @NotNull
    public DescriptorRenderer.ValueParametersHandler getValueParametersHandler() {
        return this.options.getValueParametersHandler();
    }

    public boolean getVerbose() {
        return this.options.getVerbose();
    }

    public boolean getWithDefinedIn() {
        return this.options.getWithDefinedIn();
    }

    public boolean getWithSourceFileForTopLevel() {
        return this.options.getWithSourceFileForTopLevel();
    }

    public boolean getWithoutReturnType() {
        return this.options.getWithoutReturnType();
    }

    public boolean getWithoutSuperTypes() {
        return this.options.getWithoutSuperTypes();
    }

    public boolean getWithoutTypeParameters() {
        return this.options.getWithoutTypeParameters();
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    @NotNull
    public String render(@NotNull DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "declarationDescriptor");
        StringBuilder sb = new StringBuilder();
        declarationDescriptor.accept(new RenderDeclarationDescriptorVisitor(), sb);
        if (getWithDefinedIn()) {
            appendDefinedIn(sb, declarationDescriptor);
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    @NotNull
    public String renderAnnotation(@NotNull AnnotationDescriptor annotation, @Nullable AnnotationUseSiteTarget annotationUseSiteTarget) {
        Intrinsics.checkParameterIsNotNull(annotation, "annotation");
        StringBuilder sb = new StringBuilder();
        sb.append('@');
        if (annotationUseSiteTarget != null) {
            sb.append(annotationUseSiteTarget.getRenderName() + ":");
        }
        KotlinType mo11659getType = annotation.mo11659getType();
        sb.append(renderType(mo11659getType));
        if (getIncludeAnnotationArguments()) {
            List<String> renderAndSortAnnotationArguments = renderAndSortAnnotationArguments(annotation);
            if (getIncludeEmptyAnnotationArguments() || (!renderAndSortAnnotationArguments.isEmpty())) {
                CollectionsKt___CollectionsKt.joinTo$default(renderAndSortAnnotationArguments, sb, ", ", "(", ")", 0, null, null, 112, null);
            }
        }
        if (getVerbose() && (KotlinTypeKt.isError(mo11659getType) || (mo11659getType.mo12131getConstructor().mo12085getDeclarationDescriptor() instanceof NotFoundClasses.MockClassDescriptor))) {
            sb.append(" /* annotation class not found */");
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public String renderClassifierName(@NotNull ClassifierDescriptor klass) {
        Intrinsics.checkParameterIsNotNull(klass, "klass");
        if (ErrorUtils.isError(klass)) {
            return klass.mo11528getTypeConstructor().toString();
        }
        return getClassifierNamePolicy().renderClassifier(klass, this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    @NotNull
    public String renderFlexibleType(@NotNull String lowerRendered, @NotNull String upperRendered, @NotNull KotlinBuiltIns builtIns) {
        String substringBefore$default;
        String substringBefore$default2;
        boolean startsWith$default;
        Intrinsics.checkParameterIsNotNull(lowerRendered, "lowerRendered");
        Intrinsics.checkParameterIsNotNull(upperRendered, "upperRendered");
        Intrinsics.checkParameterIsNotNull(builtIns, "builtIns");
        if (differsOnlyInNullability(lowerRendered, upperRendered)) {
            startsWith$default = StringsKt__StringsJVMKt.startsWith$default(upperRendered, "(", false, 2, null);
            if (startsWith$default) {
                return '(' + lowerRendered + ")!";
            }
            return GeneratedOutlineSupport1.outline47(lowerRendered, '!');
        }
        ClassifierNamePolicy classifierNamePolicy = getClassifierNamePolicy();
        ClassDescriptor collection = builtIns.getCollection();
        Intrinsics.checkExpressionValueIsNotNull(collection, "builtIns.collection");
        substringBefore$default = StringsKt__StringsKt.substringBefore$default(classifierNamePolicy.renderClassifier(collection, this), "Collection", (String) null, 2, (Object) null);
        String outline72 = GeneratedOutlineSupport1.outline72(substringBefore$default, "Mutable");
        String replacePrefixes = replacePrefixes(lowerRendered, outline72, upperRendered, substringBefore$default, substringBefore$default + "(Mutable)");
        if (replacePrefixes != null) {
            return replacePrefixes;
        }
        String replacePrefixes2 = replacePrefixes(lowerRendered, GeneratedOutlineSupport1.outline72(substringBefore$default, "MutableMap.MutableEntry"), upperRendered, GeneratedOutlineSupport1.outline72(substringBefore$default, "Map.Entry"), GeneratedOutlineSupport1.outline72(substringBefore$default, "(Mutable)Map.(Mutable)Entry"));
        if (replacePrefixes2 != null) {
            return replacePrefixes2;
        }
        ClassifierNamePolicy classifierNamePolicy2 = getClassifierNamePolicy();
        ClassDescriptor array = builtIns.getArray();
        Intrinsics.checkExpressionValueIsNotNull(array, "builtIns.array");
        substringBefore$default2 = StringsKt__StringsKt.substringBefore$default(classifierNamePolicy2.renderClassifier(array, this), "Array", (String) null, 2, (Object) null);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(substringBefore$default2);
        outline107.append(escape("Array<"));
        String sb = outline107.toString();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(substringBefore$default2);
        outline1072.append(escape("Array<out "));
        String sb2 = outline1072.toString();
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107(substringBefore$default2);
        outline1073.append(escape("Array<(out) "));
        String replacePrefixes3 = replacePrefixes(lowerRendered, sb, upperRendered, sb2, outline1073.toString());
        if (replacePrefixes3 != null) {
            return replacePrefixes3;
        }
        return '(' + lowerRendered + ".." + upperRendered + ')';
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    @NotNull
    public String renderFqName(@NotNull FqNameUnsafe fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        List<Name> pathSegments = fqName.pathSegments();
        Intrinsics.checkExpressionValueIsNotNull(pathSegments, "fqName.pathSegments()");
        return renderFqName(pathSegments);
    }

    @NotNull
    public String renderMessage(@NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        int i = WhenMappings.$EnumSwitchMapping$3[getTextFormat().ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            return GeneratedOutlineSupport1.outline75("<i>", message, "</i>");
        }
        return message;
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    @NotNull
    public String renderName(@NotNull Name name, boolean z) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        String escape = escape(RenderingUtilsKt.render(name));
        return (!getBoldOnlyForNamesInHtml() || getTextFormat() != RenderingFormat.HTML || !z) ? escape : GeneratedOutlineSupport1.outline75("<b>", escape, "</b>");
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    @NotNull
    public String renderType(@NotNull KotlinType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        StringBuilder sb = new StringBuilder();
        renderNormalizedType(sb, getTypeNormalizer().mo12165invoke(type));
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public String renderTypeArguments(@NotNull List<? extends TypeProjection> typeArguments) {
        Intrinsics.checkParameterIsNotNull(typeArguments, "typeArguments");
        if (typeArguments.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(lt());
        appendTypeProjections(sb, typeArguments);
        sb.append(gt());
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public String renderTypeConstructor(@NotNull TypeConstructor typeConstructor) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "typeConstructor");
        ClassifierDescriptor mo12085getDeclarationDescriptor = typeConstructor.mo12085getDeclarationDescriptor();
        if (!(mo12085getDeclarationDescriptor instanceof TypeParameterDescriptor) && !(mo12085getDeclarationDescriptor instanceof ClassDescriptor) && !(mo12085getDeclarationDescriptor instanceof TypeAliasDescriptor)) {
            if (mo12085getDeclarationDescriptor == null) {
                return typeConstructor.toString();
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected classifier: ");
            outline107.append(mo12085getDeclarationDescriptor.getClass());
            throw new IllegalStateException(outline107.toString().toString());
        }
        return renderClassifierName(mo12085getDeclarationDescriptor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    @NotNull
    public String renderTypeProjection(@NotNull TypeProjection typeProjection) {
        List<? extends TypeProjection> listOf;
        Intrinsics.checkParameterIsNotNull(typeProjection, "typeProjection");
        StringBuilder sb = new StringBuilder();
        listOf = CollectionsKt__CollectionsJVMKt.listOf(typeProjection);
        appendTypeProjections(sb, listOf);
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setAnnotationArgumentsRenderingPolicy(@NotNull AnnotationArgumentsRenderingPolicy annotationArgumentsRenderingPolicy) {
        Intrinsics.checkParameterIsNotNull(annotationArgumentsRenderingPolicy, "<set-?>");
        this.options.setAnnotationArgumentsRenderingPolicy(annotationArgumentsRenderingPolicy);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setClassifierNamePolicy(@NotNull ClassifierNamePolicy classifierNamePolicy) {
        Intrinsics.checkParameterIsNotNull(classifierNamePolicy, "<set-?>");
        this.options.setClassifierNamePolicy(classifierNamePolicy);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setDebugMode(boolean z) {
        this.options.setDebugMode(z);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setExcludedTypeAnnotationClasses(@NotNull Set<FqName> set) {
        Intrinsics.checkParameterIsNotNull(set, "<set-?>");
        this.options.setExcludedTypeAnnotationClasses(set);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setModifiers(@NotNull Set<? extends DescriptorRendererModifier> set) {
        Intrinsics.checkParameterIsNotNull(set, "<set-?>");
        this.options.setModifiers(set);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setParameterNameRenderingPolicy(@NotNull ParameterNameRenderingPolicy parameterNameRenderingPolicy) {
        Intrinsics.checkParameterIsNotNull(parameterNameRenderingPolicy, "<set-?>");
        this.options.setParameterNameRenderingPolicy(parameterNameRenderingPolicy);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setReceiverAfterName(boolean z) {
        this.options.setReceiverAfterName(z);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setRenderCompanionObjectName(boolean z) {
        this.options.setRenderCompanionObjectName(z);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setStartFromName(boolean z) {
        this.options.setStartFromName(z);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setTextFormat(@NotNull RenderingFormat renderingFormat) {
        Intrinsics.checkParameterIsNotNull(renderingFormat, "<set-?>");
        this.options.setTextFormat(renderingFormat);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setVerbose(boolean z) {
        this.options.setVerbose(z);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setWithDefinedIn(boolean z) {
        this.options.setWithDefinedIn(z);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setWithoutSuperTypes(boolean z) {
        this.options.setWithoutSuperTypes(z);
    }

    @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setWithoutTypeParameters(boolean z) {
        this.options.setWithoutTypeParameters(z);
    }

    private final String renderFqName(List<Name> list) {
        return escape(RenderingUtilsKt.renderFqName(list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderName(DeclarationDescriptor declarationDescriptor, StringBuilder sb, boolean z) {
        Name name = declarationDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "descriptor.name");
        sb.append(renderName(name, z));
    }
}
