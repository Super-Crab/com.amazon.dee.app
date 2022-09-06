package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AbstractDeserializedPackageFragmentProvider.kt */
/* loaded from: classes4.dex */
public abstract class AbstractDeserializedPackageFragmentProvider implements PackageFragmentProvider {
    @NotNull
    protected DeserializationComponents components;
    @NotNull
    private final KotlinMetadataFinder finder;
    private final MemoizedFunctionToNullable<FqName, PackageFragmentDescriptor> fragments;
    @NotNull
    private final ModuleDescriptor moduleDescriptor;
    @NotNull
    private final StorageManager storageManager;

    public AbstractDeserializedPackageFragmentProvider(@NotNull StorageManager storageManager, @NotNull KotlinMetadataFinder finder, @NotNull ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(finder, "finder");
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "moduleDescriptor");
        this.storageManager = storageManager;
        this.finder = finder;
        this.moduleDescriptor = moduleDescriptor;
        this.fragments = this.storageManager.createMemoizedFunctionWithNullableValues(new AbstractDeserializedPackageFragmentProvider$fragments$1(this));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public abstract DeserializedPackageFragment findPackage(@NotNull FqName fqName);

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final DeserializationComponents getComponents() {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return deserializationComponents;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final KotlinMetadataFinder getFinder() {
        return this.finder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @NotNull
    public List<PackageFragmentDescriptor> getPackageFragments(@NotNull FqName fqName) {
        List<PackageFragmentDescriptor> listOfNotNull;
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        listOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull(this.fragments.mo12165invoke(fqName));
        return listOfNotNull;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @NotNull
    /* renamed from: getSubPackagesOf */
    public Collection<FqName> mo11654getSubPackagesOf(@NotNull FqName fqName, @NotNull Function1<? super Name, Boolean> nameFilter) {
        Set emptySet;
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setComponents(@NotNull DeserializationComponents deserializationComponents) {
        Intrinsics.checkParameterIsNotNull(deserializationComponents, "<set-?>");
        this.components = deserializationComponents;
    }
}
