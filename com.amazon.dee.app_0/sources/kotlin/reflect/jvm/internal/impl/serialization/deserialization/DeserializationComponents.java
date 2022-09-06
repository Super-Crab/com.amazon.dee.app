package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: context.kt */
/* loaded from: classes4.dex */
public final class DeserializationComponents {
    @NotNull
    private final AdditionalClassPartsProvider additionalClassPartsProvider;
    @NotNull
    private final AnnotationAndConstantLoader<AnnotationDescriptor, ConstantValue<?>> annotationAndConstantLoader;
    @NotNull
    private final ClassDataFinder classDataFinder;
    @NotNull
    private final ClassDeserializer classDeserializer;
    @NotNull
    private final DeserializationConfiguration configuration;
    @NotNull
    private final ContractDeserializer contractDeserializer;
    @NotNull
    private final ErrorReporter errorReporter;
    @NotNull
    private final ExtensionRegistryLite extensionRegistryLite;
    @NotNull
    private final Iterable<ClassDescriptorFactory> fictitiousClassDescriptorFactories;
    @NotNull
    private final FlexibleTypeDeserializer flexibleTypeDeserializer;
    @NotNull
    private final NewKotlinTypeChecker kotlinTypeChecker;
    @NotNull
    private final LocalClassifierTypeSettings localClassifierTypeSettings;
    @NotNull
    private final LookupTracker lookupTracker;
    @NotNull
    private final ModuleDescriptor moduleDescriptor;
    @NotNull
    private final NotFoundClasses notFoundClasses;
    @NotNull
    private final PackageFragmentProvider packageFragmentProvider;
    @NotNull
    private final PlatformDependentDeclarationFilter platformDependentDeclarationFilter;
    @NotNull
    private final StorageManager storageManager;

    /* JADX WARN: Multi-variable type inference failed */
    public DeserializationComponents(@NotNull StorageManager storageManager, @NotNull ModuleDescriptor moduleDescriptor, @NotNull DeserializationConfiguration configuration, @NotNull ClassDataFinder classDataFinder, @NotNull AnnotationAndConstantLoader<? extends AnnotationDescriptor, ? extends ConstantValue<?>> annotationAndConstantLoader, @NotNull PackageFragmentProvider packageFragmentProvider, @NotNull LocalClassifierTypeSettings localClassifierTypeSettings, @NotNull ErrorReporter errorReporter, @NotNull LookupTracker lookupTracker, @NotNull FlexibleTypeDeserializer flexibleTypeDeserializer, @NotNull Iterable<? extends ClassDescriptorFactory> fictitiousClassDescriptorFactories, @NotNull NotFoundClasses notFoundClasses, @NotNull ContractDeserializer contractDeserializer, @NotNull AdditionalClassPartsProvider additionalClassPartsProvider, @NotNull PlatformDependentDeclarationFilter platformDependentDeclarationFilter, @NotNull ExtensionRegistryLite extensionRegistryLite, @NotNull NewKotlinTypeChecker kotlinTypeChecker) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "moduleDescriptor");
        Intrinsics.checkParameterIsNotNull(configuration, "configuration");
        Intrinsics.checkParameterIsNotNull(classDataFinder, "classDataFinder");
        Intrinsics.checkParameterIsNotNull(annotationAndConstantLoader, "annotationAndConstantLoader");
        Intrinsics.checkParameterIsNotNull(packageFragmentProvider, "packageFragmentProvider");
        Intrinsics.checkParameterIsNotNull(localClassifierTypeSettings, "localClassifierTypeSettings");
        Intrinsics.checkParameterIsNotNull(errorReporter, "errorReporter");
        Intrinsics.checkParameterIsNotNull(lookupTracker, "lookupTracker");
        Intrinsics.checkParameterIsNotNull(flexibleTypeDeserializer, "flexibleTypeDeserializer");
        Intrinsics.checkParameterIsNotNull(fictitiousClassDescriptorFactories, "fictitiousClassDescriptorFactories");
        Intrinsics.checkParameterIsNotNull(notFoundClasses, "notFoundClasses");
        Intrinsics.checkParameterIsNotNull(contractDeserializer, "contractDeserializer");
        Intrinsics.checkParameterIsNotNull(additionalClassPartsProvider, "additionalClassPartsProvider");
        Intrinsics.checkParameterIsNotNull(platformDependentDeclarationFilter, "platformDependentDeclarationFilter");
        Intrinsics.checkParameterIsNotNull(extensionRegistryLite, "extensionRegistryLite");
        Intrinsics.checkParameterIsNotNull(kotlinTypeChecker, "kotlinTypeChecker");
        this.storageManager = storageManager;
        this.moduleDescriptor = moduleDescriptor;
        this.configuration = configuration;
        this.classDataFinder = classDataFinder;
        this.annotationAndConstantLoader = annotationAndConstantLoader;
        this.packageFragmentProvider = packageFragmentProvider;
        this.localClassifierTypeSettings = localClassifierTypeSettings;
        this.errorReporter = errorReporter;
        this.lookupTracker = lookupTracker;
        this.flexibleTypeDeserializer = flexibleTypeDeserializer;
        this.fictitiousClassDescriptorFactories = fictitiousClassDescriptorFactories;
        this.notFoundClasses = notFoundClasses;
        this.contractDeserializer = contractDeserializer;
        this.additionalClassPartsProvider = additionalClassPartsProvider;
        this.platformDependentDeclarationFilter = platformDependentDeclarationFilter;
        this.extensionRegistryLite = extensionRegistryLite;
        this.kotlinTypeChecker = kotlinTypeChecker;
        this.classDeserializer = new ClassDeserializer(this);
    }

    @NotNull
    public final DeserializationContext createContext(@NotNull PackageFragmentDescriptor descriptor, @NotNull NameResolver nameResolver, @NotNull TypeTable typeTable, @NotNull VersionRequirementTable versionRequirementTable, @NotNull BinaryVersion metadataVersion, @Nullable DeserializedContainerSource deserializedContainerSource) {
        List emptyList;
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        Intrinsics.checkParameterIsNotNull(versionRequirementTable, "versionRequirementTable");
        Intrinsics.checkParameterIsNotNull(metadataVersion, "metadataVersion");
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return new DeserializationContext(this, nameResolver, descriptor, typeTable, versionRequirementTable, metadataVersion, deserializedContainerSource, null, emptyList);
    }

    @Nullable
    public final ClassDescriptor deserializeClass(@NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        return ClassDeserializer.deserializeClass$default(this.classDeserializer, classId, null, 2, null);
    }

    @NotNull
    public final AdditionalClassPartsProvider getAdditionalClassPartsProvider() {
        return this.additionalClassPartsProvider;
    }

    @NotNull
    public final AnnotationAndConstantLoader<AnnotationDescriptor, ConstantValue<?>> getAnnotationAndConstantLoader() {
        return this.annotationAndConstantLoader;
    }

    @NotNull
    public final ClassDataFinder getClassDataFinder() {
        return this.classDataFinder;
    }

    @NotNull
    public final ClassDeserializer getClassDeserializer() {
        return this.classDeserializer;
    }

    @NotNull
    public final DeserializationConfiguration getConfiguration() {
        return this.configuration;
    }

    @NotNull
    public final ContractDeserializer getContractDeserializer() {
        return this.contractDeserializer;
    }

    @NotNull
    public final ErrorReporter getErrorReporter() {
        return this.errorReporter;
    }

    @NotNull
    public final ExtensionRegistryLite getExtensionRegistryLite() {
        return this.extensionRegistryLite;
    }

    @NotNull
    public final Iterable<ClassDescriptorFactory> getFictitiousClassDescriptorFactories() {
        return this.fictitiousClassDescriptorFactories;
    }

    @NotNull
    public final FlexibleTypeDeserializer getFlexibleTypeDeserializer() {
        return this.flexibleTypeDeserializer;
    }

    @NotNull
    public final NewKotlinTypeChecker getKotlinTypeChecker() {
        return this.kotlinTypeChecker;
    }

    @NotNull
    public final LocalClassifierTypeSettings getLocalClassifierTypeSettings() {
        return this.localClassifierTypeSettings;
    }

    @NotNull
    public final LookupTracker getLookupTracker() {
        return this.lookupTracker;
    }

    @NotNull
    public final ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    @NotNull
    public final NotFoundClasses getNotFoundClasses() {
        return this.notFoundClasses;
    }

    @NotNull
    public final PackageFragmentProvider getPackageFragmentProvider() {
        return this.packageFragmentProvider;
    }

    @NotNull
    public final PlatformDependentDeclarationFilter getPlatformDependentDeclarationFilter() {
        return this.platformDependentDeclarationFilter;
    }

    @NotNull
    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    public /* synthetic */ DeserializationComponents(StorageManager storageManager, ModuleDescriptor moduleDescriptor, DeserializationConfiguration deserializationConfiguration, ClassDataFinder classDataFinder, AnnotationAndConstantLoader annotationAndConstantLoader, PackageFragmentProvider packageFragmentProvider, LocalClassifierTypeSettings localClassifierTypeSettings, ErrorReporter errorReporter, LookupTracker lookupTracker, FlexibleTypeDeserializer flexibleTypeDeserializer, Iterable iterable, NotFoundClasses notFoundClasses, ContractDeserializer contractDeserializer, AdditionalClassPartsProvider additionalClassPartsProvider, PlatformDependentDeclarationFilter platformDependentDeclarationFilter, ExtensionRegistryLite extensionRegistryLite, NewKotlinTypeChecker newKotlinTypeChecker, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(storageManager, moduleDescriptor, deserializationConfiguration, classDataFinder, annotationAndConstantLoader, packageFragmentProvider, localClassifierTypeSettings, errorReporter, lookupTracker, flexibleTypeDeserializer, iterable, notFoundClasses, contractDeserializer, (i & 8192) != 0 ? AdditionalClassPartsProvider.None.INSTANCE : additionalClassPartsProvider, (i & 16384) != 0 ? PlatformDependentDeclarationFilter.All.INSTANCE : platformDependentDeclarationFilter, extensionRegistryLite, (i & 65536) != 0 ? NewKotlinTypeChecker.Companion.getDefault() : newKotlinTypeChecker);
    }
}
