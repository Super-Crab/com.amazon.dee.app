package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: JvmPackageScope.kt */
/* loaded from: classes3.dex */
public final class JvmPackageScope$kotlinScopes$2 extends Lambda implements Function0<List<? extends MemberScope>> {
    final /* synthetic */ JvmPackageScope this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmPackageScope$kotlinScopes$2(JvmPackageScope jvmPackageScope) {
        super(0);
        this.this$0 = jvmPackageScope;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final List<? extends MemberScope> mo12560invoke() {
        LazyJavaPackageFragment lazyJavaPackageFragment;
        List<? extends MemberScope> list;
        LazyJavaResolverContext lazyJavaResolverContext;
        LazyJavaPackageFragment lazyJavaPackageFragment2;
        lazyJavaPackageFragment = this.this$0.packageFragment;
        Collection<KotlinJvmBinaryClass> values = lazyJavaPackageFragment.getBinaryClasses$descriptors_jvm().values();
        ArrayList arrayList = new ArrayList();
        for (KotlinJvmBinaryClass kotlinJvmBinaryClass : values) {
            lazyJavaResolverContext = this.this$0.c;
            DeserializedDescriptorResolver deserializedDescriptorResolver = lazyJavaResolverContext.getComponents().getDeserializedDescriptorResolver();
            lazyJavaPackageFragment2 = this.this$0.packageFragment;
            MemberScope createKotlinPackagePartScope = deserializedDescriptorResolver.createKotlinPackagePartScope(lazyJavaPackageFragment2, kotlinJvmBinaryClass);
            if (createKotlinPackagePartScope != null) {
                arrayList.add(createKotlinPackagePartScope);
            }
        }
        list = CollectionsKt___CollectionsKt.toList(arrayList);
        return list;
    }
}
