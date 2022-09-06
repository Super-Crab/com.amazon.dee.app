package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ModuleDescriptorImpl.kt */
/* loaded from: classes2.dex */
public final class ModuleDescriptorImpl$packageFragmentProviderForWholeModuleWithDependencies$2 extends Lambda implements Function0<CompositePackageFragmentProvider> {
    final /* synthetic */ ModuleDescriptorImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDescriptorImpl$packageFragmentProviderForWholeModuleWithDependencies$2(ModuleDescriptorImpl moduleDescriptorImpl) {
        super(0);
        this.this$0 = moduleDescriptorImpl;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final CompositePackageFragmentProvider mo12560invoke() {
        ModuleDependencies moduleDependencies;
        String id;
        int collectionSizeOrDefault;
        PackageFragmentProvider packageFragmentProvider;
        boolean isInitialized;
        String id2;
        String id3;
        String id4;
        moduleDependencies = this.this$0.dependencies;
        if (moduleDependencies != null) {
            List<ModuleDescriptorImpl> allDependencies = moduleDependencies.getAllDependencies();
            boolean contains = allDependencies.contains(this.this$0);
            if (_Assertions.ENABLED && !contains) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Module ");
                id4 = this.this$0.getId();
                outline107.append(id4);
                outline107.append(" is not contained in his own dependencies, this is probably a misconfiguration");
                throw new AssertionError(outline107.toString());
            }
            for (ModuleDescriptorImpl moduleDescriptorImpl : allDependencies) {
                isInitialized = moduleDescriptorImpl.isInitialized();
                if (_Assertions.ENABLED && !isInitialized) {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Dependency module ");
                    id2 = moduleDescriptorImpl.getId();
                    outline1072.append(id2);
                    outline1072.append(" was not initialized by the time contents of dependent module ");
                    id3 = this.this$0.getId();
                    outline1072.append(id3);
                    outline1072.append(" were queried");
                    throw new AssertionError(outline1072.toString());
                }
            }
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(allDependencies, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (ModuleDescriptorImpl moduleDescriptorImpl2 : allDependencies) {
                packageFragmentProvider = moduleDescriptorImpl2.packageFragmentProviderForModuleContent;
                if (packageFragmentProvider == null) {
                    Intrinsics.throwNpe();
                }
                arrayList.add(packageFragmentProvider);
            }
            return new CompositePackageFragmentProvider(arrayList);
        }
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Dependencies of module ");
        id = this.this$0.getId();
        outline1073.append(id);
        outline1073.append(" were not set before querying module content");
        throw new AssertionError(outline1073.toString());
    }
}
