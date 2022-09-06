package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ChainedMemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;
/* compiled from: LazyPackageViewDescriptorImpl.kt */
/* loaded from: classes2.dex */
final class LazyPackageViewDescriptorImpl$memberScope$1 extends Lambda implements Function0<MemberScope> {
    final /* synthetic */ LazyPackageViewDescriptorImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyPackageViewDescriptorImpl$memberScope$1(LazyPackageViewDescriptorImpl lazyPackageViewDescriptorImpl) {
        super(0);
        this.this$0 = lazyPackageViewDescriptorImpl;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final MemberScope mo12560invoke() {
        int collectionSizeOrDefault;
        List plus;
        if (this.this$0.getFragments().isEmpty()) {
            return MemberScope.Empty.INSTANCE;
        }
        List<PackageFragmentDescriptor> fragments = this.this$0.getFragments();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(fragments, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (PackageFragmentDescriptor packageFragmentDescriptor : fragments) {
            arrayList.add(packageFragmentDescriptor.mo11676getMemberScope());
        }
        plus = CollectionsKt___CollectionsKt.plus((Collection<? extends Object>) ((Collection) arrayList), (Object) new SubpackagesScope(this.this$0.mo11567getModule(), this.this$0.getFqName()));
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("package view scope for ");
        outline107.append(this.this$0.getFqName());
        outline107.append(" in ");
        outline107.append(this.this$0.mo11567getModule().getName());
        return new ChainedMemberScope(outline107.toString(), plus);
    }
}
