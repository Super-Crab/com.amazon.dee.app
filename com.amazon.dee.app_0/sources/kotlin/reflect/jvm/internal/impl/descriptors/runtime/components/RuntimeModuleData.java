package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.CompositePackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.SingleModuleClassResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializationComponentsForJava;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JavaDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
/* compiled from: RuntimeModuleData.kt */
/* loaded from: classes2.dex */
public final class RuntimeModuleData {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final DeserializationComponents deserialization;
    @NotNull
    private final PackagePartScopeCache packagePartScopeCache;

    /* compiled from: RuntimeModuleData.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final RuntimeModuleData create(@NotNull ClassLoader classLoader) {
            LazyJavaPackageFragmentProvider makeLazyJavaPackageFragmentFromClassLoaderProvider;
            List listOf;
            Intrinsics.checkParameterIsNotNull(classLoader, "classLoader");
            LockBasedStorageManager lockBasedStorageManager = new LockBasedStorageManager("RuntimeModuleData");
            JvmBuiltIns jvmBuiltIns = new JvmBuiltIns(lockBasedStorageManager, JvmBuiltIns.Kind.FROM_DEPENDENCIES);
            Name special = Name.special("<runtime module for " + classLoader + Typography.greater);
            Intrinsics.checkExpressionValueIsNotNull(special, "Name.special(\"<runtime module for $classLoader>\")");
            ModuleDescriptorImpl moduleDescriptorImpl = new ModuleDescriptorImpl(special, lockBasedStorageManager, jvmBuiltIns, null, null, null, 56, null);
            jvmBuiltIns.setBuiltInsModule(moduleDescriptorImpl);
            jvmBuiltIns.initialize(moduleDescriptorImpl, true);
            ReflectKotlinClassFinder reflectKotlinClassFinder = new ReflectKotlinClassFinder(classLoader);
            DeserializedDescriptorResolver deserializedDescriptorResolver = new DeserializedDescriptorResolver();
            SingleModuleClassResolver singleModuleClassResolver = new SingleModuleClassResolver();
            NotFoundClasses notFoundClasses = new NotFoundClasses(lockBasedStorageManager, moduleDescriptorImpl);
            makeLazyJavaPackageFragmentFromClassLoaderProvider = RuntimeModuleDataKt.makeLazyJavaPackageFragmentFromClassLoaderProvider(classLoader, moduleDescriptorImpl, lockBasedStorageManager, notFoundClasses, reflectKotlinClassFinder, deserializedDescriptorResolver, singleModuleClassResolver, (r17 & 128) != 0 ? PackagePartProvider.Empty.INSTANCE : null);
            DeserializationComponentsForJava makeDeserializationComponentsForJava = RuntimeModuleDataKt.makeDeserializationComponentsForJava(moduleDescriptorImpl, lockBasedStorageManager, notFoundClasses, makeLazyJavaPackageFragmentFromClassLoaderProvider, reflectKotlinClassFinder, deserializedDescriptorResolver);
            deserializedDescriptorResolver.setComponents(makeDeserializationComponentsForJava);
            JavaResolverCache javaResolverCache = JavaResolverCache.EMPTY;
            Intrinsics.checkExpressionValueIsNotNull(javaResolverCache, "JavaResolverCache.EMPTY");
            JavaDescriptorResolver javaDescriptorResolver = new JavaDescriptorResolver(makeLazyJavaPackageFragmentFromClassLoaderProvider, javaResolverCache);
            singleModuleClassResolver.setResolver(javaDescriptorResolver);
            ClassLoader stdlibClassLoader = Unit.class.getClassLoader();
            Intrinsics.checkExpressionValueIsNotNull(stdlibClassLoader, "stdlibClassLoader");
            JvmBuiltInsPackageFragmentProvider jvmBuiltInsPackageFragmentProvider = new JvmBuiltInsPackageFragmentProvider(lockBasedStorageManager, new ReflectKotlinClassFinder(stdlibClassLoader), moduleDescriptorImpl, notFoundClasses, jvmBuiltIns.getSettings(), jvmBuiltIns.getSettings(), DeserializationConfiguration.Default.INSTANCE, NewKotlinTypeChecker.Companion.getDefault());
            moduleDescriptorImpl.setDependencies(moduleDescriptorImpl);
            listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new PackageFragmentProvider[]{javaDescriptorResolver.getPackageFragmentProvider(), jvmBuiltInsPackageFragmentProvider});
            moduleDescriptorImpl.initialize(new CompositePackageFragmentProvider(listOf));
            return new RuntimeModuleData(makeDeserializationComponentsForJava.getComponents(), new PackagePartScopeCache(deserializedDescriptorResolver, reflectKotlinClassFinder), null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private RuntimeModuleData(DeserializationComponents deserializationComponents, PackagePartScopeCache packagePartScopeCache) {
        this.deserialization = deserializationComponents;
        this.packagePartScopeCache = packagePartScopeCache;
    }

    @NotNull
    public final DeserializationComponents getDeserialization() {
        return this.deserialization;
    }

    @NotNull
    public final ModuleDescriptor getModule() {
        return this.deserialization.getModuleDescriptor();
    }

    @NotNull
    public final PackagePartScopeCache getPackagePartScopeCache() {
        return this.packagePartScopeCache;
    }

    public /* synthetic */ RuntimeModuleData(DeserializationComponents deserializationComponents, PackagePartScopeCache packagePartScopeCache, DefaultConstructorMarker defaultConstructorMarker) {
        this(deserializationComponents, packagePartScopeCache);
    }
}
