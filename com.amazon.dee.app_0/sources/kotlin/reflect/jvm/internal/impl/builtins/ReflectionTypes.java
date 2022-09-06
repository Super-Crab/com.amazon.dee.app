package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectionTypes.kt */
/* loaded from: classes2.dex */
public final class ReflectionTypes {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kClass", "getKClass()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kProperty", "getKProperty()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kProperty0", "getKProperty0()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kProperty1", "getKProperty1()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kProperty2", "getKProperty2()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kMutableProperty0", "getKMutableProperty0()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kMutableProperty1", "getKMutableProperty1()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReflectionTypes.class), "kMutableProperty2", "getKMutableProperty2()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;"))};
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ClassLookup kClass$delegate;
    @NotNull
    private final ClassLookup kMutableProperty0$delegate;
    @NotNull
    private final ClassLookup kMutableProperty1$delegate;
    @NotNull
    private final ClassLookup kMutableProperty2$delegate;
    @NotNull
    private final ClassLookup kProperty$delegate;
    @NotNull
    private final ClassLookup kProperty0$delegate;
    @NotNull
    private final ClassLookup kProperty1$delegate;
    @NotNull
    private final ClassLookup kProperty2$delegate;
    private final Lazy kotlinReflectScope$delegate;
    private final NotFoundClasses notFoundClasses;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReflectionTypes.kt */
    /* loaded from: classes2.dex */
    public static final class ClassLookup {
        private final int numberOfTypeParameters;

        public ClassLookup(int i) {
            this.numberOfTypeParameters = i;
        }

        @NotNull
        public final ClassDescriptor getValue(@NotNull ReflectionTypes types, @NotNull KProperty<?> property) {
            String capitalize;
            Intrinsics.checkParameterIsNotNull(types, "types");
            Intrinsics.checkParameterIsNotNull(property, "property");
            capitalize = StringsKt__StringsJVMKt.capitalize(property.getName());
            return types.find(capitalize, this.numberOfTypeParameters);
        }
    }

    /* compiled from: ReflectionTypes.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final KotlinType createKPropertyStarType(@NotNull ModuleDescriptor module) {
            List listOf;
            Intrinsics.checkParameterIsNotNull(module, "module");
            ClassId classId = KotlinBuiltIns.FQ_NAMES.kProperty;
            Intrinsics.checkExpressionValueIsNotNull(classId, "KotlinBuiltIns.FQ_NAMES.kProperty");
            ClassDescriptor findClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(module, classId);
            if (findClassAcrossModuleDependencies != null) {
                Annotations empty = Annotations.Companion.getEMPTY();
                TypeConstructor mo11528getTypeConstructor = findClassAcrossModuleDependencies.mo11528getTypeConstructor();
                Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "kPropertyClass.typeConstructor");
                List<TypeParameterDescriptor> parameters = mo11528getTypeConstructor.getParameters();
                Intrinsics.checkExpressionValueIsNotNull(parameters, "kPropertyClass.typeConstructor.parameters");
                Object single = CollectionsKt.single((List<? extends Object>) parameters);
                Intrinsics.checkExpressionValueIsNotNull(single, "kPropertyClass.typeConstructor.parameters.single()");
                listOf = CollectionsKt__CollectionsJVMKt.listOf(new StarProjectionImpl((TypeParameterDescriptor) single));
                return KotlinTypeFactory.simpleNotNullType(empty, findClassAcrossModuleDependencies, listOf);
            }
            return null;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ReflectionTypes(@NotNull ModuleDescriptor module, @NotNull NotFoundClasses notFoundClasses) {
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(module, "module");
        Intrinsics.checkParameterIsNotNull(notFoundClasses, "notFoundClasses");
        this.notFoundClasses = notFoundClasses;
        lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new ReflectionTypes$kotlinReflectScope$2(module));
        this.kotlinReflectScope$delegate = lazy;
        this.kClass$delegate = new ClassLookup(1);
        this.kProperty$delegate = new ClassLookup(1);
        this.kProperty0$delegate = new ClassLookup(1);
        this.kProperty1$delegate = new ClassLookup(2);
        this.kProperty2$delegate = new ClassLookup(3);
        this.kMutableProperty0$delegate = new ClassLookup(1);
        this.kMutableProperty1$delegate = new ClassLookup(2);
        this.kMutableProperty2$delegate = new ClassLookup(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassDescriptor find(String str, int i) {
        List<Integer> listOf;
        Name identifier = Name.identifier(str);
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(className)");
        ClassifierDescriptor mo12030getContributedClassifier = getKotlinReflectScope().mo12030getContributedClassifier(identifier, NoLookupLocation.FROM_REFLECTION);
        if (!(mo12030getContributedClassifier instanceof ClassDescriptor)) {
            mo12030getContributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) mo12030getContributedClassifier;
        if (classDescriptor != null) {
            return classDescriptor;
        }
        NotFoundClasses notFoundClasses = this.notFoundClasses;
        ClassId classId = new ClassId(ReflectionTypesKt.getKOTLIN_REFLECT_FQ_NAME(), identifier);
        listOf = CollectionsKt__CollectionsJVMKt.listOf(Integer.valueOf(i));
        return notFoundClasses.getClass(classId, listOf);
    }

    private final MemberScope getKotlinReflectScope() {
        return (MemberScope) this.kotlinReflectScope$delegate.getValue();
    }

    @NotNull
    public final ClassDescriptor getKClass() {
        return this.kClass$delegate.getValue(this, $$delegatedProperties[0]);
    }
}
