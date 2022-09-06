package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Set;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoader;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoaderImpl;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializedClassDataFinder;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.LocalClassifierTypeSettings;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
/* compiled from: BuiltInsLoaderImpl.kt */
/* loaded from: classes4.dex */
public final class BuiltInsLoaderImpl implements BuiltInsLoader {
    private final BuiltInsResourceLoader resourceLoader = new BuiltInsResourceLoader();

    @NotNull
    public final PackageFragmentProvider createBuiltInPackageFragmentProvider(@NotNull StorageManager storageManager, @NotNull ModuleDescriptor module, @NotNull Set<FqName> packageFqNames, @NotNull Iterable<? extends ClassDescriptorFactory> classDescriptorFactories, @NotNull PlatformDependentDeclarationFilter platformDependentDeclarationFilter, @NotNull AdditionalClassPartsProvider additionalClassPartsProvider, boolean z, @NotNull Function1<? super String, ? extends InputStream> loadResource) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(module, "module");
        Intrinsics.checkParameterIsNotNull(packageFqNames, "packageFqNames");
        Intrinsics.checkParameterIsNotNull(classDescriptorFactories, "classDescriptorFactories");
        Intrinsics.checkParameterIsNotNull(platformDependentDeclarationFilter, "platformDependentDeclarationFilter");
        Intrinsics.checkParameterIsNotNull(additionalClassPartsProvider, "additionalClassPartsProvider");
        Intrinsics.checkParameterIsNotNull(loadResource, "loadResource");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(packageFqNames, 10);
        ArrayList<BuiltInsPackageFragmentImpl> arrayList = new ArrayList(collectionSizeOrDefault);
        for (FqName fqName : packageFqNames) {
            String builtInsFilePath = BuiltInSerializerProtocol.INSTANCE.getBuiltInsFilePath(fqName);
            InputStream mo12165invoke = loadResource.mo12165invoke(builtInsFilePath);
            if (mo12165invoke != null) {
                arrayList.add(BuiltInsPackageFragmentImpl.Companion.create(fqName, storageManager, module, mo12165invoke, z));
            } else {
                throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Resource not found in classpath: ", builtInsFilePath));
            }
        }
        PackageFragmentProviderImpl packageFragmentProviderImpl = new PackageFragmentProviderImpl(arrayList);
        NotFoundClasses notFoundClasses = new NotFoundClasses(storageManager, module);
        DeserializationConfiguration.Default r3 = DeserializationConfiguration.Default.INSTANCE;
        DeserializedClassDataFinder deserializedClassDataFinder = new DeserializedClassDataFinder(packageFragmentProviderImpl);
        AnnotationAndConstantLoaderImpl annotationAndConstantLoaderImpl = new AnnotationAndConstantLoaderImpl(module, notFoundClasses, BuiltInSerializerProtocol.INSTANCE);
        LocalClassifierTypeSettings.Default r7 = LocalClassifierTypeSettings.Default.INSTANCE;
        ErrorReporter errorReporter = ErrorReporter.DO_NOTHING;
        Intrinsics.checkExpressionValueIsNotNull(errorReporter, "ErrorReporter.DO_NOTHING");
        DeserializationComponents deserializationComponents = new DeserializationComponents(storageManager, module, r3, deserializedClassDataFinder, annotationAndConstantLoaderImpl, packageFragmentProviderImpl, r7, errorReporter, LookupTracker.DO_NOTHING.INSTANCE, FlexibleTypeDeserializer.ThrowException.INSTANCE, classDescriptorFactories, notFoundClasses, ContractDeserializer.Companion.getDEFAULT(), additionalClassPartsProvider, platformDependentDeclarationFilter, BuiltInSerializerProtocol.INSTANCE.getExtensionRegistry(), null, 65536, null);
        for (BuiltInsPackageFragmentImpl builtInsPackageFragmentImpl : arrayList) {
            builtInsPackageFragmentImpl.initialize(deserializationComponents);
        }
        return packageFragmentProviderImpl;
    }

    @Override // kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoader
    @NotNull
    public PackageFragmentProvider createPackageFragmentProvider(@NotNull StorageManager storageManager, @NotNull ModuleDescriptor builtInsModule, @NotNull Iterable<? extends ClassDescriptorFactory> classDescriptorFactories, @NotNull PlatformDependentDeclarationFilter platformDependentDeclarationFilter, @NotNull AdditionalClassPartsProvider additionalClassPartsProvider, boolean z) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(builtInsModule, "builtInsModule");
        Intrinsics.checkParameterIsNotNull(classDescriptorFactories, "classDescriptorFactories");
        Intrinsics.checkParameterIsNotNull(platformDependentDeclarationFilter, "platformDependentDeclarationFilter");
        Intrinsics.checkParameterIsNotNull(additionalClassPartsProvider, "additionalClassPartsProvider");
        Set<FqName> set = KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAMES;
        Intrinsics.checkExpressionValueIsNotNull(set, "KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAMES");
        return createBuiltInPackageFragmentProvider(storageManager, builtInsModule, set, classDescriptorFactories, platformDependentDeclarationFilter, additionalClassPartsProvider, z, new BuiltInsLoaderImpl$createPackageFragmentProvider$1(this.resourceLoader));
    }
}
