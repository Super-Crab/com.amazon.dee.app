package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NotFoundClasses.kt */
/* loaded from: classes2.dex */
public final class NotFoundClasses$packageFragments$1 extends Lambda implements Function1<FqName, EmptyPackageFragmentDescriptor> {
    final /* synthetic */ NotFoundClasses this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotFoundClasses$packageFragments$1(NotFoundClasses notFoundClasses) {
        super(1);
        this.this$0 = notFoundClasses;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final EmptyPackageFragmentDescriptor mo12165invoke(@NotNull FqName fqName) {
        ModuleDescriptor moduleDescriptor;
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        moduleDescriptor = this.this$0.module;
        return new EmptyPackageFragmentDescriptor(moduleDescriptor, fqName);
    }
}
