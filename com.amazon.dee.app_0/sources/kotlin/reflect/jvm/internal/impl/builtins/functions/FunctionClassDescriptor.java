package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntRange;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.ReflectionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FunctionClassDescriptor.kt */
/* loaded from: classes2.dex */
public final class FunctionClassDescriptor extends AbstractClassDescriptor {
    public static final Companion Companion = new Companion(null);
    private static final ClassId functionClassId = new ClassId(KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME, Name.identifier("Function"));
    private static final ClassId kFunctionClassId = new ClassId(ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME(), Name.identifier("KFunction"));
    private final int arity;
    private final PackageFragmentDescriptor containingDeclaration;
    @NotNull
    private final Kind functionKind;
    private final FunctionClassScope memberScope;
    private final List<TypeParameterDescriptor> parameters;
    private final StorageManager storageManager;
    private final FunctionTypeConstructor typeConstructor;

    /* compiled from: FunctionClassDescriptor.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static final class AnonymousClass1 extends Lambda implements Function2<Variance, String, Unit> {
        final /* synthetic */ ArrayList $result;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ArrayList arrayList) {
            super(2);
            this.$result = arrayList;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12248invoke(Variance variance, String str) {
            invoke2(variance, str);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Variance variance, @NotNull String name) {
            Intrinsics.checkParameterIsNotNull(variance, "variance");
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.$result.add(TypeParameterDescriptorImpl.createWithDefaultBound(FunctionClassDescriptor.this, Annotations.Companion.getEMPTY(), false, variance, Name.identifier(name), this.$result.size(), FunctionClassDescriptor.this.storageManager));
        }
    }

    /* compiled from: FunctionClassDescriptor.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Init of enum Function can be incorrect */
    /* JADX WARN: Init of enum SuspendFunction can be incorrect */
    /* compiled from: FunctionClassDescriptor.kt */
    /* loaded from: classes2.dex */
    public enum Kind {
        Function(BUILT_INS_PACKAGE_FQ_NAME, "Function"),
        SuspendFunction(COROUTINES_PACKAGE_FQ_NAME_RELEASE, "SuspendFunction"),
        KFunction(ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME(), "KFunction"),
        KSuspendFunction(ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME(), "KSuspendFunction");
        
        public static final Companion Companion;
        @NotNull
        private final String classNamePrefix;
        @NotNull
        private final FqName packageFqName;

        /* compiled from: FunctionClassDescriptor.kt */
        /* loaded from: classes2.dex */
        public static final class Companion {
            private Companion() {
            }

            /* JADX WARN: Removed duplicated region for block: B:13:0x0032 A[LOOP:0: B:3:0x0011->B:13:0x0032, LOOP_END] */
            /* JADX WARN: Removed duplicated region for block: B:16:0x0030 A[SYNTHETIC] */
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor.Kind byClassNamePrefix(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.name.FqName r9, @org.jetbrains.annotations.NotNull java.lang.String r10) {
                /*
                    r8 = this;
                    java.lang.String r0 = "packageFqName"
                    kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
                    java.lang.String r0 = "className"
                    kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
                    kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor$Kind[] r0 = kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor.Kind.values()
                    int r1 = r0.length
                    r2 = 0
                    r3 = r2
                L11:
                    r4 = 0
                    if (r3 >= r1) goto L35
                    r5 = r0[r3]
                    kotlin.reflect.jvm.internal.impl.name.FqName r6 = r5.getPackageFqName()
                    boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r9)
                    if (r6 == 0) goto L2d
                    java.lang.String r6 = r5.getClassNamePrefix()
                    r7 = 2
                    boolean r4 = kotlin.text.StringsKt.startsWith$default(r10, r6, r2, r7, r4)
                    if (r4 == 0) goto L2d
                    r4 = 1
                    goto L2e
                L2d:
                    r4 = r2
                L2e:
                    if (r4 == 0) goto L32
                    r4 = r5
                    goto L35
                L32:
                    int r3 = r3 + 1
                    goto L11
                L35:
                    return r4
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor.Kind.Companion.byClassNamePrefix(kotlin.reflect.jvm.internal.impl.name.FqName, java.lang.String):kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor$Kind");
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        static {
            FqName BUILT_INS_PACKAGE_FQ_NAME = KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME;
            Intrinsics.checkExpressionValueIsNotNull(BUILT_INS_PACKAGE_FQ_NAME, "BUILT_INS_PACKAGE_FQ_NAME");
            FqName COROUTINES_PACKAGE_FQ_NAME_RELEASE = DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_RELEASE;
            Intrinsics.checkExpressionValueIsNotNull(COROUTINES_PACKAGE_FQ_NAME_RELEASE, "COROUTINES_PACKAGE_FQ_NAME_RELEASE");
            Companion = new Companion(null);
        }

        Kind(FqName fqName, String str) {
            this.packageFqName = fqName;
            this.classNamePrefix = str;
        }

        @NotNull
        public final String getClassNamePrefix() {
            return this.classNamePrefix;
        }

        @NotNull
        public final FqName getPackageFqName() {
            return this.packageFqName;
        }

        @NotNull
        public final Name numberedClassName(int i) {
            Name identifier = Name.identifier(this.classNamePrefix + i);
            Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(\"$classNamePrefix$arity\")");
            return identifier;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FunctionClassDescriptor(@NotNull StorageManager storageManager, @NotNull PackageFragmentDescriptor containingDeclaration, @NotNull Kind functionKind, int i) {
        super(storageManager, functionKind.numberedClassName(i));
        int collectionSizeOrDefault;
        List<TypeParameterDescriptor> list;
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(containingDeclaration, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(functionKind, "functionKind");
        this.storageManager = storageManager;
        this.containingDeclaration = containingDeclaration;
        this.functionKind = functionKind;
        this.arity = i;
        this.typeConstructor = new FunctionTypeConstructor();
        this.memberScope = new FunctionClassScope(this.storageManager, this);
        ArrayList arrayList = new ArrayList();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(arrayList);
        IntRange intRange = new IntRange(1, this.arity);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(intRange, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        Iterator<Integer> it2 = intRange.iterator();
        while (it2.hasNext()) {
            int nextInt = ((IntIterator) it2).nextInt();
            Variance variance = Variance.IN_VARIANCE;
            StringBuilder sb = new StringBuilder();
            sb.append('P');
            sb.append(nextInt);
            anonymousClass1.invoke2(variance, sb.toString());
            arrayList2.add(Unit.INSTANCE);
        }
        anonymousClass1.invoke2(Variance.OUT_VARIANCE, "R");
        list = CollectionsKt___CollectionsKt.toList(arrayList);
        this.parameters = list;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    @NotNull
    /* renamed from: getAnnotations */
    public Annotations mo12070getAnnotations() {
        return Annotations.Companion.getEMPTY();
    }

    public final int getArity() {
        return this.arity;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @Nullable
    /* renamed from: getCompanionObjectDescriptor */
    public Void mo11505getCompanionObjectDescriptor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    /* renamed from: getCompanionObjectDescriptor  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ ClassDescriptor mo11505getCompanionObjectDescriptor() {
        return (ClassDescriptor) mo11505getCompanionObjectDescriptor();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    @NotNull
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        return this.parameters;
    }

    @NotNull
    public final Kind getFunctionKind() {
        return this.functionKind;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    public ClassKind getKind() {
        return ClassKind.INTERFACE;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    @NotNull
    public Modality getModality() {
        return Modality.ABSTRACT;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    @NotNull
    public SourceElement getSource() {
        SourceElement sourceElement = SourceElement.NO_SOURCE;
        Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
        return sourceElement;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    @NotNull
    /* renamed from: getTypeConstructor */
    public TypeConstructor mo11528getTypeConstructor() {
        return this.typeConstructor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @Nullable
    /* renamed from: getUnsubstitutedPrimaryConstructor */
    public Void mo11511getUnsubstitutedPrimaryConstructor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    /* renamed from: getUnsubstitutedPrimaryConstructor  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ ClassConstructorDescriptor mo11511getUnsubstitutedPrimaryConstructor() {
        return (ClassConstructorDescriptor) mo11511getUnsubstitutedPrimaryConstructor();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    @NotNull
    public Visibility getVisibility() {
        Visibility visibility = Visibilities.PUBLIC;
        Intrinsics.checkExpressionValueIsNotNull(visibility, "Visibilities.PUBLIC");
        return visibility;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isCompanionObject() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isData() {
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

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isInline() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public boolean isInner() {
        return false;
    }

    @NotNull
    public String toString() {
        String asString = getName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "name.asString()");
        return asString;
    }

    /* compiled from: FunctionClassDescriptor.kt */
    /* loaded from: classes2.dex */
    private final class FunctionTypeConstructor extends AbstractClassTypeConstructor {

        /* loaded from: classes2.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Kind.values().length];

            static {
                $EnumSwitchMapping$0[Kind.Function.ordinal()] = 1;
                $EnumSwitchMapping$0[Kind.KFunction.ordinal()] = 2;
                $EnumSwitchMapping$0[Kind.SuspendFunction.ordinal()] = 3;
                $EnumSwitchMapping$0[Kind.KSuspendFunction.ordinal()] = 4;
            }
        }

        public FunctionTypeConstructor() {
            super(FunctionClassDescriptor.this.storageManager);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        @NotNull
        public Collection<KotlinType> computeSupertypes() {
            List<ClassId> listOf;
            int collectionSizeOrDefault;
            List list;
            List<TypeParameterDescriptor> takeLast;
            int collectionSizeOrDefault2;
            int i = WhenMappings.$EnumSwitchMapping$0[FunctionClassDescriptor.this.getFunctionKind().ordinal()];
            if (i == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(FunctionClassDescriptor.functionClassId);
            } else if (i == 2) {
                listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new ClassId[]{FunctionClassDescriptor.kFunctionClassId, new ClassId(KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME, Kind.Function.numberedClassName(FunctionClassDescriptor.this.getArity()))});
            } else if (i == 3) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(FunctionClassDescriptor.functionClassId);
            } else if (i != 4) {
                throw new NoWhenBranchMatchedException();
            } else {
                listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new ClassId[]{FunctionClassDescriptor.kFunctionClassId, new ClassId(DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_RELEASE, Kind.SuspendFunction.numberedClassName(FunctionClassDescriptor.this.getArity()))});
            }
            ModuleDescriptor mo11607getContainingDeclaration = FunctionClassDescriptor.this.containingDeclaration.mo11607getContainingDeclaration();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (ClassId classId : listOf) {
                ClassDescriptor findClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(mo11607getContainingDeclaration, classId);
                if (findClassAcrossModuleDependencies == null) {
                    throw new IllegalStateException(("Built-in class " + classId + " not found").toString());
                }
                List<TypeParameterDescriptor> parameters = getParameters();
                TypeConstructor mo11528getTypeConstructor = findClassAcrossModuleDependencies.mo11528getTypeConstructor();
                Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "descriptor.typeConstructor");
                takeLast = CollectionsKt___CollectionsKt.takeLast(parameters, mo11528getTypeConstructor.getParameters().size());
                collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(takeLast, 10);
                ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
                for (TypeParameterDescriptor typeParameterDescriptor : takeLast) {
                    arrayList2.add(new TypeProjectionImpl(typeParameterDescriptor.getDefaultType()));
                }
                arrayList.add(KotlinTypeFactory.simpleNotNullType(Annotations.Companion.getEMPTY(), findClassAcrossModuleDependencies, arrayList2));
            }
            list = CollectionsKt___CollectionsKt.toList(arrayList);
            return list;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        public List<TypeParameterDescriptor> getParameters() {
            return FunctionClassDescriptor.this.parameters;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        @NotNull
        public SupertypeLoopChecker getSupertypeLoopChecker() {
            return SupertypeLoopChecker.EMPTY.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return true;
        }

        @NotNull
        public String toString() {
            return mo12085getDeclarationDescriptor().toString();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor, kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor, kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        /* renamed from: getDeclarationDescriptor */
        public FunctionClassDescriptor mo12085getDeclarationDescriptor() {
            return FunctionClassDescriptor.this;
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    /* renamed from: getConstructors  reason: collision with other method in class */
    public List<ClassConstructorDescriptor> mo11663getConstructors() {
        List<ClassConstructorDescriptor> emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    /* renamed from: getContainingDeclaration  reason: collision with other method in class */
    public PackageFragmentDescriptor mo11607getContainingDeclaration() {
        return this.containingDeclaration;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    /* renamed from: getSealedSubclasses  reason: collision with other method in class */
    public List<ClassDescriptor> mo11508getSealedSubclasses() {
        List<ClassDescriptor> emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    /* renamed from: getStaticScope */
    public MemberScope.Empty mo12047getStaticScope() {
        return MemberScope.Empty.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    @NotNull
    /* renamed from: getUnsubstitutedMemberScope */
    public FunctionClassScope mo11665getUnsubstitutedMemberScope(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        return this.memberScope;
    }
}
