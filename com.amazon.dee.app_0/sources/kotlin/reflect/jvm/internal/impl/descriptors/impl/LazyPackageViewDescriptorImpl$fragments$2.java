package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: LazyPackageViewDescriptorImpl.kt */
/* loaded from: classes2.dex */
final class LazyPackageViewDescriptorImpl$fragments$2 extends Lambda implements Function0<List<? extends PackageFragmentDescriptor>> {
    final /* synthetic */ LazyPackageViewDescriptorImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyPackageViewDescriptorImpl$fragments$2(LazyPackageViewDescriptorImpl lazyPackageViewDescriptorImpl) {
        super(0);
        this.this$0 = lazyPackageViewDescriptorImpl;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final List<? extends PackageFragmentDescriptor> mo12560invoke() {
        return this.this$0.mo11567getModule().getPackageFragmentProvider().getPackageFragments(this.this$0.getFqName());
    }
}
