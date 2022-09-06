package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin._Assertions;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.InvalidModuleException;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.platform.TargetPlatform;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefinerKt;
import kotlin.reflect.jvm.internal.impl.types.checker.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ModuleDescriptorImpl.kt */
/* loaded from: classes2.dex */
public final class ModuleDescriptorImpl extends DeclarationDescriptorImpl implements ModuleDescriptor {
    @NotNull
    private final KotlinBuiltIns builtIns;
    private final Map<ModuleDescriptor.Capability<?>, Object> capabilities;
    private ModuleDependencies dependencies;
    private boolean isValid;
    private PackageFragmentProvider packageFragmentProviderForModuleContent;
    private final Lazy packageFragmentProviderForWholeModuleWithDependencies$delegate;
    private final MemoizedFunctionToNotNull<FqName, PackageViewDescriptor> packages;
    @Nullable
    private final TargetPlatform platform;
    @Nullable
    private final Name stableName;
    private final StorageManager storageManager;

    @JvmOverloads
    public ModuleDescriptorImpl(@NotNull Name name, @NotNull StorageManager storageManager, @NotNull KotlinBuiltIns kotlinBuiltIns, @Nullable TargetPlatform targetPlatform) {
        this(name, storageManager, kotlinBuiltIns, targetPlatform, null, null, 48, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ ModuleDescriptorImpl(kotlin.reflect.jvm.internal.impl.name.Name r10, kotlin.reflect.jvm.internal.impl.storage.StorageManager r11, kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns r12, kotlin.reflect.jvm.internal.impl.platform.TargetPlatform r13, java.util.Map r14, kotlin.reflect.jvm.internal.impl.name.Name r15, int r16, kotlin.jvm.internal.DefaultConstructorMarker r17) {
        /*
            r9 = this;
            r0 = r16 & 8
            r1 = 0
            if (r0 == 0) goto L7
            r6 = r1
            goto L8
        L7:
            r6 = r13
        L8:
            r0 = r16 & 16
            if (r0 == 0) goto L12
            java.util.Map r0 = kotlin.collections.MapsKt.emptyMap()
            r7 = r0
            goto L13
        L12:
            r7 = r14
        L13:
            r0 = r16 & 32
            if (r0 == 0) goto L19
            r8 = r1
            goto L1a
        L19:
            r8 = r15
        L1a:
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl.<init>(kotlin.reflect.jvm.internal.impl.name.Name, kotlin.reflect.jvm.internal.impl.storage.StorageManager, kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns, kotlin.reflect.jvm.internal.impl.platform.TargetPlatform, java.util.Map, kotlin.reflect.jvm.internal.impl.name.Name, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getId() {
        String name = getName().toString();
        Intrinsics.checkExpressionValueIsNotNull(name, "name.toString()");
        return name;
    }

    private final CompositePackageFragmentProvider getPackageFragmentProviderForWholeModuleWithDependencies() {
        return (CompositePackageFragmentProvider) this.packageFragmentProviderForWholeModuleWithDependencies$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isInitialized() {
        return this.packageFragmentProviderForModuleContent != null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public <R, D> R accept(@NotNull DeclarationDescriptorVisitor<R, D> visitor, D d) {
        Intrinsics.checkParameterIsNotNull(visitor, "visitor");
        return (R) ModuleDescriptor.DefaultImpls.accept(this, visitor, d);
    }

    public void assertValid() {
        if (isValid()) {
            return;
        }
        throw new InvalidModuleException("Accessing invalid module descriptor " + this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    @NotNull
    public KotlinBuiltIns getBuiltIns() {
        return this.builtIns;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    @Nullable
    public <T> T getCapability(@NotNull ModuleDescriptor.Capability<T> capability) {
        Intrinsics.checkParameterIsNotNull(capability, "capability");
        T t = (T) this.capabilities.get(capability);
        if (!(t instanceof Object)) {
            return null;
        }
        return t;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @Nullable
    /* renamed from: getContainingDeclaration */
    public DeclarationDescriptor mo11607getContainingDeclaration() {
        return ModuleDescriptor.DefaultImpls.getContainingDeclaration(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    @NotNull
    public List<ModuleDescriptor> getExpectedByModules() {
        ModuleDependencies moduleDependencies = this.dependencies;
        if (moduleDependencies != null) {
            return moduleDependencies.getExpectedByDependencies();
        }
        throw new AssertionError(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Dependencies of module "), getId(), " were not set"));
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    @NotNull
    public PackageViewDescriptor getPackage(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        assertValid();
        return this.packages.mo12165invoke(fqName);
    }

    @NotNull
    public final PackageFragmentProvider getPackageFragmentProvider() {
        assertValid();
        return getPackageFragmentProviderForWholeModuleWithDependencies();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    @NotNull
    public Collection<FqName> getSubPackagesOf(@NotNull FqName fqName, @NotNull Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        assertValid();
        return getPackageFragmentProvider().mo11654getSubPackagesOf(fqName, nameFilter);
    }

    public final void initialize(@NotNull PackageFragmentProvider providerForModuleContent) {
        Intrinsics.checkParameterIsNotNull(providerForModuleContent, "providerForModuleContent");
        boolean z = !isInitialized();
        if (!_Assertions.ENABLED || z) {
            this.packageFragmentProviderForModuleContent = providerForModuleContent;
            return;
        }
        throw new AssertionError(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Attempt to initialize module "), getId(), " twice"));
    }

    public boolean isValid() {
        return this.isValid;
    }

    public final void setDependencies(@NotNull ModuleDependencies dependencies) {
        Intrinsics.checkParameterIsNotNull(dependencies, "dependencies");
        boolean z = this.dependencies == null;
        if (!_Assertions.ENABLED || z) {
            this.dependencies = dependencies;
            return;
        }
        throw new AssertionError(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Dependencies of "), getId(), " were already set"));
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public boolean shouldSeeInternalsOf(@NotNull ModuleDescriptor targetModule) {
        boolean contains;
        Intrinsics.checkParameterIsNotNull(targetModule, "targetModule");
        if (Intrinsics.areEqual(this, targetModule)) {
            return true;
        }
        ModuleDependencies moduleDependencies = this.dependencies;
        if (moduleDependencies == null) {
            Intrinsics.throwNpe();
        }
        contains = CollectionsKt___CollectionsKt.contains(moduleDependencies.getModulesWhoseInternalsAreVisible(), targetModule);
        return contains || getExpectedByModules().contains(targetModule) || targetModule.getExpectedByModules().contains(this);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ModuleDescriptorImpl(@NotNull Name moduleName, @NotNull StorageManager storageManager, @NotNull KotlinBuiltIns builtIns, @Nullable TargetPlatform targetPlatform, @NotNull Map<ModuleDescriptor.Capability<?>, ? extends Object> capabilities, @Nullable Name name) {
        super(Annotations.Companion.getEMPTY(), moduleName);
        Map<ModuleDescriptor.Capability<?>, Object> mutableMap;
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(moduleName, "moduleName");
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(builtIns, "builtIns");
        Intrinsics.checkParameterIsNotNull(capabilities, "capabilities");
        this.storageManager = storageManager;
        this.builtIns = builtIns;
        this.platform = targetPlatform;
        this.stableName = name;
        if (moduleName.isSpecial()) {
            mutableMap = MapsKt__MapsKt.toMutableMap(capabilities);
            this.capabilities = mutableMap;
            this.capabilities.put(KotlinTypeRefinerKt.getREFINER_CAPABILITY(), new Ref(null));
            this.isValid = true;
            this.packages = this.storageManager.createMemoizedFunction(new ModuleDescriptorImpl$packages$1(this));
            lazy = LazyKt__LazyJVMKt.lazy(new ModuleDescriptorImpl$packageFragmentProviderForWholeModuleWithDependencies$2(this));
            this.packageFragmentProviderForWholeModuleWithDependencies$delegate = lazy;
            return;
        }
        throw new IllegalArgumentException("Module name must be special: " + moduleName);
    }

    public final void setDependencies(@NotNull ModuleDescriptorImpl... descriptors) {
        List<ModuleDescriptorImpl> list;
        Intrinsics.checkParameterIsNotNull(descriptors, "descriptors");
        list = ArraysKt___ArraysKt.toList(descriptors);
        setDependencies(list);
    }

    public final void setDependencies(@NotNull List<ModuleDescriptorImpl> descriptors) {
        Set<ModuleDescriptorImpl> emptySet;
        Intrinsics.checkParameterIsNotNull(descriptors, "descriptors");
        emptySet = SetsKt__SetsKt.emptySet();
        setDependencies(descriptors, emptySet);
    }

    public final void setDependencies(@NotNull List<ModuleDescriptorImpl> descriptors, @NotNull Set<ModuleDescriptorImpl> friends) {
        List emptyList;
        Intrinsics.checkParameterIsNotNull(descriptors, "descriptors");
        Intrinsics.checkParameterIsNotNull(friends, "friends");
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        setDependencies(new ModuleDependenciesImpl(descriptors, friends, emptyList));
    }
}
