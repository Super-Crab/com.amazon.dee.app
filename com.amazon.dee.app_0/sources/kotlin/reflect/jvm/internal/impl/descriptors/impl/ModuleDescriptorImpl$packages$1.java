package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ModuleDescriptorImpl.kt */
/* loaded from: classes2.dex */
public final class ModuleDescriptorImpl$packages$1 extends Lambda implements Function1<FqName, LazyPackageViewDescriptorImpl> {
    final /* synthetic */ ModuleDescriptorImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDescriptorImpl$packages$1(ModuleDescriptorImpl moduleDescriptorImpl) {
        super(1);
        this.this$0 = moduleDescriptorImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final LazyPackageViewDescriptorImpl mo12165invoke(@NotNull FqName fqName) {
        StorageManager storageManager;
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        ModuleDescriptorImpl moduleDescriptorImpl = this.this$0;
        storageManager = moduleDescriptorImpl.storageManager;
        return new LazyPackageViewDescriptorImpl(moduleDescriptorImpl, fqName, storageManager);
    }
}
