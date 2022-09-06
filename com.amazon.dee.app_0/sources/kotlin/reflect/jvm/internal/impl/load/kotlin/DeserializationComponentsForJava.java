package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.LocalClassifierTypeSettings;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker;
import org.jetbrains.annotations.NotNull;
/* compiled from: DeserializationComponentsForJava.kt */
/* loaded from: classes3.dex */
public final class DeserializationComponentsForJava {
    @NotNull
    private final DeserializationComponents components;

    public DeserializationComponentsForJava(@NotNull StorageManager storageManager, @NotNull ModuleDescriptor moduleDescriptor, @NotNull DeserializationConfiguration configuration, @NotNull JavaClassDataFinder classDataFinder, @NotNull BinaryClassAnnotationAndConstantLoaderImpl annotationAndConstantLoader, @NotNull LazyJavaPackageFragmentProvider packageFragmentProvider, @NotNull NotFoundClasses notFoundClasses, @NotNull ErrorReporter errorReporter, @NotNull LookupTracker lookupTracker, @NotNull ContractDeserializer contractDeserializer, @NotNull NewKotlinTypeChecker kotlinTypeChecker) {
        List emptyList;
        AdditionalClassPartsProvider additionalClassPartsProvider;
        PlatformDependentDeclarationFilter platformDependentDeclarationFilter;
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "moduleDescriptor");
        Intrinsics.checkParameterIsNotNull(configuration, "configuration");
        Intrinsics.checkParameterIsNotNull(classDataFinder, "classDataFinder");
        Intrinsics.checkParameterIsNotNull(annotationAndConstantLoader, "annotationAndConstantLoader");
        Intrinsics.checkParameterIsNotNull(packageFragmentProvider, "packageFragmentProvider");
        Intrinsics.checkParameterIsNotNull(notFoundClasses, "notFoundClasses");
        Intrinsics.checkParameterIsNotNull(errorReporter, "errorReporter");
        Intrinsics.checkParameterIsNotNull(lookupTracker, "lookupTracker");
        Intrinsics.checkParameterIsNotNull(contractDeserializer, "contractDeserializer");
        Intrinsics.checkParameterIsNotNull(kotlinTypeChecker, "kotlinTypeChecker");
        KotlinBuiltIns builtIns = moduleDescriptor.getBuiltIns();
        JvmBuiltIns jvmBuiltIns = (JvmBuiltIns) (!(builtIns instanceof JvmBuiltIns) ? null : builtIns);
        LocalClassifierTypeSettings.Default r8 = LocalClassifierTypeSettings.Default.INSTANCE;
        JavaFlexibleTypeDeserializer javaFlexibleTypeDeserializer = JavaFlexibleTypeDeserializer.INSTANCE;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        this.components = new DeserializationComponents(storageManager, moduleDescriptor, configuration, classDataFinder, annotationAndConstantLoader, packageFragmentProvider, r8, errorReporter, lookupTracker, javaFlexibleTypeDeserializer, emptyList, notFoundClasses, contractDeserializer, (jvmBuiltIns == null || (additionalClassPartsProvider = jvmBuiltIns.getSettings()) == null) ? AdditionalClassPartsProvider.None.INSTANCE : additionalClassPartsProvider, (jvmBuiltIns == null || (platformDependentDeclarationFilter = jvmBuiltIns.getSettings()) == null) ? PlatformDependentDeclarationFilter.NoPlatformDependent.INSTANCE : platformDependentDeclarationFilter, JvmProtoBufUtil.INSTANCE.getEXTENSION_REGISTRY(), kotlinTypeChecker);
    }

    @NotNull
    public final DeserializationComponents getComponents() {
        return this.components;
    }
}
