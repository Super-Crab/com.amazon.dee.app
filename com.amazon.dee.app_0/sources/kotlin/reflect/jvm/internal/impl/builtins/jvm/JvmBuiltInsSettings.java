package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilterKt;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.CacheWithNotNullValues;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
import org.jetbrains.annotations.NotNull;
/* compiled from: JvmBuiltInsSettings.kt */
/* loaded from: classes2.dex */
public class JvmBuiltInsSettings implements AdditionalClassPartsProvider, PlatformDependentDeclarationFilter {
    @NotNull
    private static final Set<String> BLACK_LIST_CONSTRUCTOR_SIGNATURES;
    @NotNull
    private static final Set<String> BLACK_LIST_METHOD_SIGNATURES;
    @NotNull
    private static final Set<String> DROP_LIST_METHOD_SIGNATURES;
    @NotNull
    private static final Set<String> MUTABLE_METHOD_SIGNATURES;
    @NotNull
    private static final Set<String> WHITE_LIST_CONSTRUCTOR_SIGNATURES;
    @NotNull
    private static final Set<String> WHITE_LIST_METHOD_SIGNATURES;
    private final NotNullLazyValue cloneableType$delegate;
    private final Lazy isAdditionalBuiltInsFeatureSupported$delegate;
    private final JavaToKotlinClassMap j2kClassMap;
    private final CacheWithNotNullValues<FqName, ClassDescriptor> javaAnalogueClassesWithCustomSupertypeCache;
    private final KotlinType mockSerializableType;
    private final ModuleDescriptor moduleDescriptor;
    private final NotNullLazyValue notConsideredDeprecation$delegate;
    private final Lazy ownerModuleDescriptor$delegate;
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInsSettings.class), "cloneableType", "getCloneableType()Lorg/jetbrains/kotlin/types/SimpleType;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInsSettings.class), "notConsideredDeprecation", "getNotConsideredDeprecation()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;"))};
    public static final Companion Companion = new Companion(null);

    /* compiled from: JvmBuiltInsSettings.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Set<String> buildPrimitiveStringConstructorsSet() {
            List<JvmPrimitiveType> listOf;
            SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
            listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new JvmPrimitiveType[]{JvmPrimitiveType.BOOLEAN, JvmPrimitiveType.BYTE, JvmPrimitiveType.DOUBLE, JvmPrimitiveType.FLOAT, JvmPrimitiveType.BYTE, JvmPrimitiveType.INT, JvmPrimitiveType.LONG, JvmPrimitiveType.SHORT});
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (JvmPrimitiveType jvmPrimitiveType : listOf) {
                String asString = jvmPrimitiveType.getWrapperFqName().shortName().asString();
                Intrinsics.checkExpressionValueIsNotNull(asString, "it.wrapperFqName.shortName().asString()");
                String[] constructors = signatureBuildingComponents.constructors("Ljava/lang/String;");
                CollectionsKt__MutableCollectionsKt.addAll(linkedHashSet, signatureBuildingComponents.inJavaLang(asString, (String[]) Arrays.copyOf(constructors, constructors.length)));
            }
            return linkedHashSet;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Set<String> buildPrimitiveValueMethodsSet() {
            List<JvmPrimitiveType> listOf;
            SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
            listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new JvmPrimitiveType[]{JvmPrimitiveType.BOOLEAN, JvmPrimitiveType.CHAR});
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (JvmPrimitiveType jvmPrimitiveType : listOf) {
                String asString = jvmPrimitiveType.getWrapperFqName().shortName().asString();
                Intrinsics.checkExpressionValueIsNotNull(asString, "it.wrapperFqName.shortName().asString()");
                CollectionsKt__MutableCollectionsKt.addAll(linkedHashSet, signatureBuildingComponents.inJavaLang(asString, jvmPrimitiveType.getJavaKeywordName() + "Value()" + jvmPrimitiveType.getDesc()));
            }
            return linkedHashSet;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isArrayOrPrimitiveArray(FqNameUnsafe fqNameUnsafe) {
            return Intrinsics.areEqual(fqNameUnsafe, KotlinBuiltIns.FQ_NAMES.array) || KotlinBuiltIns.isPrimitiveArray(fqNameUnsafe);
        }

        @NotNull
        public final Set<String> getBLACK_LIST_METHOD_SIGNATURES() {
            return JvmBuiltInsSettings.BLACK_LIST_METHOD_SIGNATURES;
        }

        @NotNull
        public final Set<String> getDROP_LIST_METHOD_SIGNATURES() {
            return JvmBuiltInsSettings.DROP_LIST_METHOD_SIGNATURES;
        }

        @NotNull
        public final Set<String> getWHITE_LIST_METHOD_SIGNATURES() {
            return JvmBuiltInsSettings.WHITE_LIST_METHOD_SIGNATURES;
        }

        public final boolean isSerializableInJava(@NotNull FqNameUnsafe fqName) {
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            if (isArrayOrPrimitiveArray(fqName)) {
                return true;
            }
            ClassId mapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(fqName);
            if (mapKotlinToJava != null) {
                try {
                    return Serializable.class.isAssignableFrom(Class.forName(mapKotlinToJava.asSingleFqName().asString()));
                } catch (ClassNotFoundException unused) {
                }
            }
            return false;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: JvmBuiltInsSettings.kt */
    /* loaded from: classes2.dex */
    public enum JDKMemberStatus {
        BLACK_LIST,
        WHITE_LIST,
        NOT_CONSIDERED,
        DROP
    }

    /* loaded from: classes2.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[JDKMemberStatus.values().length];

        static {
            $EnumSwitchMapping$0[JDKMemberStatus.BLACK_LIST.ordinal()] = 1;
            $EnumSwitchMapping$0[JDKMemberStatus.NOT_CONSIDERED.ordinal()] = 2;
            $EnumSwitchMapping$0[JDKMemberStatus.DROP.ordinal()] = 3;
            $EnumSwitchMapping$0[JDKMemberStatus.WHITE_LIST.ordinal()] = 4;
        }
    }

    static {
        Set<String> plus;
        Set plus2;
        Set plus3;
        Set plus4;
        Set plus5;
        Set<String> plus6;
        Set plus7;
        Set plus8;
        Set plus9;
        Set plus10;
        Set plus11;
        Set<String> plus12;
        Set plus13;
        Set<String> plus14;
        Set plus15;
        Set<String> plus16;
        plus = SetsKt___SetsKt.plus(SignatureBuildingComponents.INSTANCE.inJavaUtil("Collection", "toArray()[Ljava/lang/Object;", "toArray([Ljava/lang/Object;)[Ljava/lang/Object;"), "java/lang/annotation/Annotation.annotationType()Ljava/lang/Class;");
        DROP_LIST_METHOD_SIGNATURES = plus;
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        plus2 = SetsKt___SetsKt.plus((Set) Companion.buildPrimitiveValueMethodsSet(), (Iterable) signatureBuildingComponents.inJavaUtil("List", "sort(Ljava/util/Comparator;)V"));
        plus3 = SetsKt___SetsKt.plus((Set) plus2, (Iterable) signatureBuildingComponents.inJavaLang("String", "codePointAt(I)I", "codePointBefore(I)I", "codePointCount(II)I", "compareToIgnoreCase(Ljava/lang/String;)I", "concat(Ljava/lang/String;)Ljava/lang/String;", "contains(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/StringBuffer;)Z", "endsWith(Ljava/lang/String;)Z", "equalsIgnoreCase(Ljava/lang/String;)Z", "getBytes()[B", "getBytes(II[BI)V", "getBytes(Ljava/lang/String;)[B", "getBytes(Ljava/nio/charset/Charset;)[B", "getChars(II[CI)V", "indexOf(I)I", "indexOf(II)I", "indexOf(Ljava/lang/String;)I", "indexOf(Ljava/lang/String;I)I", "intern()Ljava/lang/String;", "isEmpty()Z", "lastIndexOf(I)I", "lastIndexOf(II)I", "lastIndexOf(Ljava/lang/String;)I", "lastIndexOf(Ljava/lang/String;I)I", "matches(Ljava/lang/String;)Z", "offsetByCodePoints(II)I", "regionMatches(ILjava/lang/String;II)Z", "regionMatches(ZILjava/lang/String;II)Z", "replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(CC)Ljava/lang/String;", "replaceFirst(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;", "split(Ljava/lang/String;I)[Ljava/lang/String;", "split(Ljava/lang/String;)[Ljava/lang/String;", "startsWith(Ljava/lang/String;I)Z", "startsWith(Ljava/lang/String;)Z", "substring(II)Ljava/lang/String;", "substring(I)Ljava/lang/String;", "toCharArray()[C", "toLowerCase()Ljava/lang/String;", "toLowerCase(Ljava/util/Locale;)Ljava/lang/String;", "toUpperCase()Ljava/lang/String;", "toUpperCase(Ljava/util/Locale;)Ljava/lang/String;", "trim()Ljava/lang/String;", "isBlank()Z", "lines()Ljava/util/stream/Stream;", "repeat(I)Ljava/lang/String;"));
        plus4 = SetsKt___SetsKt.plus((Set) plus3, (Iterable) signatureBuildingComponents.inJavaLang("Double", "isInfinite()Z", "isNaN()Z"));
        plus5 = SetsKt___SetsKt.plus((Set) plus4, (Iterable) signatureBuildingComponents.inJavaLang("Float", "isInfinite()Z", "isNaN()Z"));
        plus6 = SetsKt___SetsKt.plus((Set) plus5, (Iterable) signatureBuildingComponents.inJavaLang("Enum", "getDeclaringClass()Ljava/lang/Class;", "finalize()V"));
        BLACK_LIST_METHOD_SIGNATURES = plus6;
        SignatureBuildingComponents signatureBuildingComponents2 = SignatureBuildingComponents.INSTANCE;
        plus7 = SetsKt___SetsKt.plus((Set) signatureBuildingComponents2.inJavaLang("CharSequence", "codePoints()Ljava/util/stream/IntStream;", "chars()Ljava/util/stream/IntStream;"), (Iterable) signatureBuildingComponents2.inJavaUtil("Iterator", "forEachRemaining(Ljava/util/function/Consumer;)V"));
        plus8 = SetsKt___SetsKt.plus((Set) plus7, (Iterable) signatureBuildingComponents2.inJavaLang("Iterable", "forEach(Ljava/util/function/Consumer;)V", "spliterator()Ljava/util/Spliterator;"));
        plus9 = SetsKt___SetsKt.plus((Set) plus8, (Iterable) signatureBuildingComponents2.inJavaLang("Throwable", "setStackTrace([Ljava/lang/StackTraceElement;)V", "fillInStackTrace()Ljava/lang/Throwable;", "getLocalizedMessage()Ljava/lang/String;", "printStackTrace()V", "printStackTrace(Ljava/io/PrintStream;)V", "printStackTrace(Ljava/io/PrintWriter;)V", "getStackTrace()[Ljava/lang/StackTraceElement;", "initCause(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "getSuppressed()[Ljava/lang/Throwable;", "addSuppressed(Ljava/lang/Throwable;)V"));
        plus10 = SetsKt___SetsKt.plus((Set) plus9, (Iterable) signatureBuildingComponents2.inJavaUtil("Collection", "spliterator()Ljava/util/Spliterator;", "parallelStream()Ljava/util/stream/Stream;", "stream()Ljava/util/stream/Stream;", "removeIf(Ljava/util/function/Predicate;)Z"));
        plus11 = SetsKt___SetsKt.plus((Set) plus10, (Iterable) signatureBuildingComponents2.inJavaUtil("List", "replaceAll(Ljava/util/function/UnaryOperator;)V"));
        plus12 = SetsKt___SetsKt.plus((Set) plus11, (Iterable) signatureBuildingComponents2.inJavaUtil("Map", "getOrDefault(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "forEach(Ljava/util/function/BiConsumer;)V", "replaceAll(Ljava/util/function/BiFunction;)V", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;"));
        WHITE_LIST_METHOD_SIGNATURES = plus12;
        SignatureBuildingComponents signatureBuildingComponents3 = SignatureBuildingComponents.INSTANCE;
        plus13 = SetsKt___SetsKt.plus((Set) signatureBuildingComponents3.inJavaUtil("Collection", "removeIf(Ljava/util/function/Predicate;)Z"), (Iterable) signatureBuildingComponents3.inJavaUtil("List", "replaceAll(Ljava/util/function/UnaryOperator;)V", "sort(Ljava/util/Comparator;)V"));
        plus14 = SetsKt___SetsKt.plus((Set) plus13, (Iterable) signatureBuildingComponents3.inJavaUtil("Map", "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove(Ljava/lang/Object;Ljava/lang/Object;)Z", "replaceAll(Ljava/util/function/BiFunction;)V", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z"));
        MUTABLE_METHOD_SIGNATURES = plus14;
        SignatureBuildingComponents signatureBuildingComponents4 = SignatureBuildingComponents.INSTANCE;
        Set buildPrimitiveStringConstructorsSet = Companion.buildPrimitiveStringConstructorsSet();
        String[] constructors = signatureBuildingComponents4.constructors("D");
        plus15 = SetsKt___SetsKt.plus((Set) buildPrimitiveStringConstructorsSet, (Iterable) signatureBuildingComponents4.inJavaLang("Float", (String[]) Arrays.copyOf(constructors, constructors.length)));
        String[] constructors2 = signatureBuildingComponents4.constructors("[C", "[CII", "[III", "[BIILjava/lang/String;", "[BIILjava/nio/charset/Charset;", "[BLjava/lang/String;", "[BLjava/nio/charset/Charset;", "[BII", "[B", "Ljava/lang/StringBuffer;", "Ljava/lang/StringBuilder;");
        plus16 = SetsKt___SetsKt.plus((Set) plus15, (Iterable) signatureBuildingComponents4.inJavaLang("String", (String[]) Arrays.copyOf(constructors2, constructors2.length)));
        BLACK_LIST_CONSTRUCTOR_SIGNATURES = plus16;
        SignatureBuildingComponents signatureBuildingComponents5 = SignatureBuildingComponents.INSTANCE;
        String[] constructors3 = signatureBuildingComponents5.constructors("Ljava/lang/String;Ljava/lang/Throwable;ZZ");
        WHITE_LIST_CONSTRUCTOR_SIGNATURES = signatureBuildingComponents5.inJavaLang("Throwable", (String[]) Arrays.copyOf(constructors3, constructors3.length));
    }

    public JvmBuiltInsSettings(@NotNull ModuleDescriptor moduleDescriptor, @NotNull StorageManager storageManager, @NotNull Function0<? extends ModuleDescriptor> deferredOwnerModuleDescriptor, @NotNull Function0<Boolean> isAdditionalBuiltInsFeatureSupported) {
        Lazy lazy;
        Lazy lazy2;
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "moduleDescriptor");
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(deferredOwnerModuleDescriptor, "deferredOwnerModuleDescriptor");
        Intrinsics.checkParameterIsNotNull(isAdditionalBuiltInsFeatureSupported, "isAdditionalBuiltInsFeatureSupported");
        this.moduleDescriptor = moduleDescriptor;
        this.j2kClassMap = JavaToKotlinClassMap.INSTANCE;
        lazy = LazyKt__LazyJVMKt.lazy(deferredOwnerModuleDescriptor);
        this.ownerModuleDescriptor$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(isAdditionalBuiltInsFeatureSupported);
        this.isAdditionalBuiltInsFeatureSupported$delegate = lazy2;
        this.mockSerializableType = createMockJavaIoSerializableType(storageManager);
        this.cloneableType$delegate = storageManager.createLazyValue(new JvmBuiltInsSettings$cloneableType$2(this, storageManager));
        this.javaAnalogueClassesWithCustomSupertypeCache = storageManager.createCacheWithNotNullValues();
        this.notConsideredDeprecation$delegate = storageManager.createLazyValue(new JvmBuiltInsSettings$notConsideredDeprecation$2(this));
    }

    private final SimpleFunctionDescriptor createCloneForArray(DeserializedClassDescriptor deserializedClassDescriptor, SimpleFunctionDescriptor simpleFunctionDescriptor) {
        FunctionDescriptor.CopyBuilder<? extends SimpleFunctionDescriptor> newCopyBuilder = simpleFunctionDescriptor.newCopyBuilder();
        newCopyBuilder.setOwner(deserializedClassDescriptor);
        newCopyBuilder.setVisibility(Visibilities.PUBLIC);
        newCopyBuilder.setReturnType(deserializedClassDescriptor.getDefaultType());
        newCopyBuilder.setDispatchReceiverParameter(deserializedClassDescriptor.getThisAsReceiverParameter());
        SimpleFunctionDescriptor build = newCopyBuilder.build();
        if (build == null) {
            Intrinsics.throwNpe();
        }
        return build;
    }

    private final KotlinType createMockJavaIoSerializableType(@NotNull StorageManager storageManager) {
        List listOf;
        Set<ClassConstructorDescriptor> emptySet;
        final ModuleDescriptor moduleDescriptor = this.moduleDescriptor;
        final FqName fqName = new FqName("java.io");
        PackageFragmentDescriptorImpl packageFragmentDescriptorImpl = new PackageFragmentDescriptorImpl(moduleDescriptor, fqName) { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$createMockJavaIoSerializableType$mockJavaIoPackageFragment$1
            @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
            @NotNull
            /* renamed from: getMemberScope */
            public MemberScope.Empty mo11676getMemberScope() {
                return MemberScope.Empty.INSTANCE;
            }
        };
        listOf = CollectionsKt__CollectionsJVMKt.listOf(new LazyWrappedType(storageManager, new JvmBuiltInsSettings$createMockJavaIoSerializableType$superTypes$1(this)));
        ClassDescriptorImpl classDescriptorImpl = new ClassDescriptorImpl(packageFragmentDescriptorImpl, Name.identifier("Serializable"), Modality.ABSTRACT, ClassKind.INTERFACE, listOf, SourceElement.NO_SOURCE, false, storageManager);
        MemberScope.Empty empty = MemberScope.Empty.INSTANCE;
        emptySet = SetsKt__SetsKt.emptySet();
        classDescriptorImpl.initialize(empty, emptySet, null);
        SimpleType defaultType = classDescriptorImpl.getDefaultType();
        Intrinsics.checkExpressionValueIsNotNull(defaultType, "mockSerializableClass.defaultType");
        return defaultType;
    }

    private final Collection<SimpleFunctionDescriptor> getAdditionalFunctions(ClassDescriptor classDescriptor, Function1<? super MemberScope, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        List emptyList;
        List emptyList2;
        int collectionSizeOrDefault;
        boolean z;
        LazyJavaClassDescriptor javaAnalogue = getJavaAnalogue(classDescriptor);
        if (javaAnalogue != null) {
            Collection<ClassDescriptor> mapPlatformClass = this.j2kClassMap.mapPlatformClass(DescriptorUtilsKt.getFqNameSafe(javaAnalogue), FallbackBuiltIns.Companion.getInstance());
            ClassDescriptor classDescriptor2 = (ClassDescriptor) CollectionsKt.lastOrNull(mapPlatformClass);
            if (classDescriptor2 != null) {
                SmartSet.Companion companion = SmartSet.Companion;
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(mapPlatformClass, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                for (ClassDescriptor classDescriptor3 : mapPlatformClass) {
                    arrayList.add(DescriptorUtilsKt.getFqNameSafe(classDescriptor3));
                }
                SmartSet create = companion.create(arrayList);
                boolean isMutable = this.j2kClassMap.isMutable(classDescriptor);
                MemberScope mo11664getUnsubstitutedMemberScope = this.javaAnalogueClassesWithCustomSupertypeCache.computeIfAbsent(DescriptorUtilsKt.getFqNameSafe(javaAnalogue), new JvmBuiltInsSettings$getAdditionalFunctions$fakeJavaClassDescriptor$1(javaAnalogue, classDescriptor2)).mo11664getUnsubstitutedMemberScope();
                Intrinsics.checkExpressionValueIsNotNull(mo11664getUnsubstitutedMemberScope, "fakeJavaClassDescriptor.unsubstitutedMemberScope");
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : function1.mo12165invoke(mo11664getUnsubstitutedMemberScope)) {
                    SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) obj;
                    boolean z2 = false;
                    if (simpleFunctionDescriptor.getKind() == CallableMemberDescriptor.Kind.DECLARATION && simpleFunctionDescriptor.getVisibility().isPublicAPI() && !KotlinBuiltIns.isDeprecated(simpleFunctionDescriptor)) {
                        Collection<? extends FunctionDescriptor> overriddenDescriptors = simpleFunctionDescriptor.getOverriddenDescriptors();
                        Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "analogueMember.overriddenDescriptors");
                        if (!overriddenDescriptors.isEmpty()) {
                            for (FunctionDescriptor it2 : overriddenDescriptors) {
                                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                                DeclarationDescriptor mo11607getContainingDeclaration = it2.mo11607getContainingDeclaration();
                                Intrinsics.checkExpressionValueIsNotNull(mo11607getContainingDeclaration, "it.containingDeclaration");
                                if (create.contains(DescriptorUtilsKt.getFqNameSafe(mo11607getContainingDeclaration))) {
                                    z = true;
                                    break;
                                }
                            }
                        }
                        z = false;
                        if (!z && !isMutabilityViolation(simpleFunctionDescriptor, isMutable)) {
                            z2 = true;
                        }
                    }
                    if (z2) {
                        arrayList2.add(obj);
                    }
                }
                return arrayList2;
            }
            emptyList2 = CollectionsKt__CollectionsKt.emptyList();
            return emptyList2;
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    private final SimpleType getCloneableType() {
        return (SimpleType) StorageKt.getValue(this.cloneableType$delegate, this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LazyJavaClassDescriptor getJavaAnalogue(@NotNull ClassDescriptor classDescriptor) {
        ClassId mapKotlinToJava;
        FqName asSingleFqName;
        if (!KotlinBuiltIns.isAny(classDescriptor) && KotlinBuiltIns.isUnderKotlinPackage(classDescriptor)) {
            FqNameUnsafe fqNameUnsafe = DescriptorUtilsKt.getFqNameUnsafe(classDescriptor);
            if (!fqNameUnsafe.isSafe() || (mapKotlinToJava = this.j2kClassMap.mapKotlinToJava(fqNameUnsafe)) == null || (asSingleFqName = mapKotlinToJava.asSingleFqName()) == null) {
                return null;
            }
            Intrinsics.checkExpressionValueIsNotNull(asSingleFqName, "j2kClassMap.mapKotlinToJ…leFqName() ?: return null");
            ClassDescriptor resolveClassByFqName = DescriptorUtilKt.resolveClassByFqName(getOwnerModuleDescriptor(), asSingleFqName, NoLookupLocation.FROM_BUILTINS);
            if (!(resolveClassByFqName instanceof LazyJavaClassDescriptor)) {
                resolveClassByFqName = null;
            }
            return (LazyJavaClassDescriptor) resolveClassByFqName;
        }
        return null;
    }

    private final JDKMemberStatus getJdkMethodStatus(@NotNull FunctionDescriptor functionDescriptor) {
        List listOf;
        DeclarationDescriptor mo11607getContainingDeclaration = functionDescriptor.mo11607getContainingDeclaration();
        if (mo11607getContainingDeclaration != null) {
            final String computeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor, false, false, 3, null);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = null;
            listOf = CollectionsKt__CollectionsJVMKt.listOf((ClassDescriptor) mo11607getContainingDeclaration);
            Object dfs = DFS.dfs(listOf, new DFS.Neighbors<N>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$getJdkMethodStatus$1
                @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
                @NotNull
                public final List<LazyJavaClassDescriptor> getNeighbors(ClassDescriptor it2) {
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    TypeConstructor mo11528getTypeConstructor = it2.mo11528getTypeConstructor();
                    Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "it.typeConstructor");
                    Collection<KotlinType> mo12135getSupertypes = mo11528getTypeConstructor.mo12135getSupertypes();
                    Intrinsics.checkExpressionValueIsNotNull(mo12135getSupertypes, "it.typeConstructor.supertypes");
                    ArrayList arrayList = new ArrayList();
                    for (KotlinType kotlinType : mo12135getSupertypes) {
                        ClassifierDescriptor mo12085getDeclarationDescriptor = kotlinType.mo12131getConstructor().mo12085getDeclarationDescriptor();
                        LazyJavaClassDescriptor lazyJavaClassDescriptor = null;
                        ClassifierDescriptor mo11613getOriginal = mo12085getDeclarationDescriptor != null ? mo12085getDeclarationDescriptor.mo11613getOriginal() : null;
                        if (!(mo11613getOriginal instanceof ClassDescriptor)) {
                            mo11613getOriginal = null;
                        }
                        ClassDescriptor classDescriptor = (ClassDescriptor) mo11613getOriginal;
                        if (classDescriptor != null) {
                            lazyJavaClassDescriptor = JvmBuiltInsSettings.this.getJavaAnalogue(classDescriptor);
                        }
                        if (lazyJavaClassDescriptor != null) {
                            arrayList.add(lazyJavaClassDescriptor);
                        }
                    }
                    return arrayList;
                }
            }, new DFS.AbstractNodeHandler<ClassDescriptor, JDKMemberStatus>() { // from class: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$getJdkMethodStatus$2
                /* JADX WARN: Type inference failed for: r0v10, types: [T, kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$JDKMemberStatus] */
                /* JADX WARN: Type inference failed for: r0v11, types: [T, kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$JDKMemberStatus] */
                /* JADX WARN: Type inference failed for: r0v12, types: [T, kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$JDKMemberStatus] */
                @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
                public boolean beforeChildren(@NotNull ClassDescriptor javaClassDescriptor) {
                    Intrinsics.checkParameterIsNotNull(javaClassDescriptor, "javaClassDescriptor");
                    String signature = SignatureBuildingComponents.INSTANCE.signature(javaClassDescriptor, computeJvmDescriptor$default);
                    if (JvmBuiltInsSettings.Companion.getBLACK_LIST_METHOD_SIGNATURES().contains(signature)) {
                        objectRef.element = JvmBuiltInsSettings.JDKMemberStatus.BLACK_LIST;
                    } else if (JvmBuiltInsSettings.Companion.getWHITE_LIST_METHOD_SIGNATURES().contains(signature)) {
                        objectRef.element = JvmBuiltInsSettings.JDKMemberStatus.WHITE_LIST;
                    } else if (JvmBuiltInsSettings.Companion.getDROP_LIST_METHOD_SIGNATURES().contains(signature)) {
                        objectRef.element = JvmBuiltInsSettings.JDKMemberStatus.DROP;
                    }
                    return ((JvmBuiltInsSettings.JDKMemberStatus) objectRef.element) == null;
                }

                @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
                @NotNull
                /* renamed from: result  reason: collision with other method in class */
                public JvmBuiltInsSettings.JDKMemberStatus mo12145result() {
                    JvmBuiltInsSettings.JDKMemberStatus jDKMemberStatus = (JvmBuiltInsSettings.JDKMemberStatus) objectRef.element;
                    return jDKMemberStatus != null ? jDKMemberStatus : JvmBuiltInsSettings.JDKMemberStatus.NOT_CONSIDERED;
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(dfs, "DFS.dfs<ClassDescriptor,…CONSIDERED\n            })");
            return (JDKMemberStatus) dfs;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
    }

    private final Annotations getNotConsideredDeprecation() {
        return (Annotations) StorageKt.getValue(this.notConsideredDeprecation$delegate, this, $$delegatedProperties[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ModuleDescriptor getOwnerModuleDescriptor() {
        return (ModuleDescriptor) this.ownerModuleDescriptor$delegate.getValue();
    }

    private final boolean isAdditionalBuiltInsFeatureSupported() {
        return ((Boolean) this.isAdditionalBuiltInsFeatureSupported$delegate.getValue()).booleanValue();
    }

    private final boolean isMutabilityViolation(@NotNull SimpleFunctionDescriptor simpleFunctionDescriptor, boolean z) {
        List listOf;
        DeclarationDescriptor mo11607getContainingDeclaration = simpleFunctionDescriptor.mo11607getContainingDeclaration();
        if (mo11607getContainingDeclaration != null) {
            String computeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default(simpleFunctionDescriptor, false, false, 3, null);
            if (z ^ MUTABLE_METHOD_SIGNATURES.contains(SignatureBuildingComponents.INSTANCE.signature((ClassDescriptor) mo11607getContainingDeclaration, computeJvmDescriptor$default))) {
                return true;
            }
            listOf = CollectionsKt__CollectionsJVMKt.listOf(simpleFunctionDescriptor);
            Boolean ifAny = DFS.ifAny(listOf, JvmBuiltInsSettings$isMutabilityViolation$1.INSTANCE, new JvmBuiltInsSettings$isMutabilityViolation$2(this));
            Intrinsics.checkExpressionValueIsNotNull(ifAny, "DFS.ifAny<CallableMember…lassDescriptor)\n        }");
            return ifAny.booleanValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
    }

    private final boolean isTrivialCopyConstructorFor(@NotNull ConstructorDescriptor constructorDescriptor, ClassDescriptor classDescriptor) {
        if (constructorDescriptor.getValueParameters().size() == 1) {
            List<ValueParameterDescriptor> valueParameters = constructorDescriptor.getValueParameters();
            Intrinsics.checkExpressionValueIsNotNull(valueParameters, "valueParameters");
            Object single = CollectionsKt.single((List<? extends Object>) valueParameters);
            Intrinsics.checkExpressionValueIsNotNull(single, "valueParameters.single()");
            ClassifierDescriptor mo12085getDeclarationDescriptor = ((ValueParameterDescriptor) single).getType().mo12131getConstructor().mo12085getDeclarationDescriptor();
            if (Intrinsics.areEqual(mo12085getDeclarationDescriptor != null ? DescriptorUtilsKt.getFqNameUnsafe(mo12085getDeclarationDescriptor) : null, DescriptorUtilsKt.getFqNameUnsafe(classDescriptor))) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00c1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x004e A[SYNTHETIC] */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.Collection<kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor> getConstructors(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r18) {
        /*
            Method dump skipped, instructions count: 312
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings.getConstructors(kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor):java.util.Collection");
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0125 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ab A[SYNTHETIC] */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.Collection<kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor> getFunctions(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.name.Name r7, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r8) {
        /*
            Method dump skipped, instructions count: 314
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings.getFunctions(kotlin.reflect.jvm.internal.impl.name.Name, kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor):java.util.Collection");
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    @NotNull
    public Collection<KotlinType> getSupertypes(@NotNull ClassDescriptor classDescriptor) {
        List emptyList;
        List listOf;
        List listOf2;
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        FqNameUnsafe fqNameUnsafe = DescriptorUtilsKt.getFqNameUnsafe(classDescriptor);
        if (Companion.isArrayOrPrimitiveArray(fqNameUnsafe)) {
            SimpleType cloneableType = getCloneableType();
            Intrinsics.checkExpressionValueIsNotNull(cloneableType, "cloneableType");
            listOf2 = CollectionsKt__CollectionsKt.listOf((Object[]) new KotlinType[]{cloneableType, this.mockSerializableType});
            return listOf2;
        } else if (Companion.isSerializableInJava(fqNameUnsafe)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(this.mockSerializableType);
            return listOf;
        } else {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter
    public boolean isFunctionAvailable(@NotNull ClassDescriptor classDescriptor, @NotNull SimpleFunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        LazyJavaClassDescriptor javaAnalogue = getJavaAnalogue(classDescriptor);
        if (javaAnalogue == null || !functionDescriptor.mo12070getAnnotations().hasAnnotation(PlatformDependentDeclarationFilterKt.getPLATFORM_DEPENDENT_ANNOTATION_FQ_NAME())) {
            return true;
        }
        if (!isAdditionalBuiltInsFeatureSupported()) {
            return false;
        }
        String computeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor, false, false, 3, null);
        LazyJavaClassMemberScope mo11664getUnsubstitutedMemberScope = javaAnalogue.mo11664getUnsubstitutedMemberScope();
        Name name = functionDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "functionDescriptor.name");
        Collection<SimpleFunctionDescriptor> mo12099getContributedFunctions = mo11664getUnsubstitutedMemberScope.mo12099getContributedFunctions(name, NoLookupLocation.FROM_BUILTINS);
        if ((mo12099getContributedFunctions instanceof Collection) && mo12099getContributedFunctions.isEmpty()) {
            return false;
        }
        for (SimpleFunctionDescriptor simpleFunctionDescriptor : mo12099getContributedFunctions) {
            if (Intrinsics.areEqual(MethodSignatureMappingKt.computeJvmDescriptor$default(simpleFunctionDescriptor, false, false, 3, null), computeJvmDescriptor$default)) {
                return true;
            }
        }
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    @NotNull
    /* renamed from: getFunctionsNames  reason: collision with other method in class */
    public Set<Name> mo11519getFunctionsNames(@NotNull ClassDescriptor classDescriptor) {
        Set<Name> emptySet;
        LazyJavaClassMemberScope mo11664getUnsubstitutedMemberScope;
        Set<Name> functionNames;
        Set<Name> emptySet2;
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        if (!isAdditionalBuiltInsFeatureSupported()) {
            emptySet2 = SetsKt__SetsKt.emptySet();
            return emptySet2;
        }
        LazyJavaClassDescriptor javaAnalogue = getJavaAnalogue(classDescriptor);
        if (javaAnalogue != null && (mo11664getUnsubstitutedMemberScope = javaAnalogue.mo11664getUnsubstitutedMemberScope()) != null && (functionNames = mo11664getUnsubstitutedMemberScope.getFunctionNames()) != null) {
            return functionNames;
        }
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }
}
