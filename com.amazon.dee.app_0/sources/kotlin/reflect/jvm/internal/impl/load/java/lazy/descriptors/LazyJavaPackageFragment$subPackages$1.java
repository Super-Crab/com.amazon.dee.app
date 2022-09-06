package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LazyJavaPackageFragment.kt */
/* loaded from: classes3.dex */
public final class LazyJavaPackageFragment$subPackages$1 extends Lambda implements Function0<List<? extends FqName>> {
    final /* synthetic */ LazyJavaPackageFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaPackageFragment$subPackages$1(LazyJavaPackageFragment lazyJavaPackageFragment) {
        super(0);
        this.this$0 = lazyJavaPackageFragment;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final List<? extends FqName> mo12560invoke() {
        JavaPackage javaPackage;
        int collectionSizeOrDefault;
        javaPackage = this.this$0.jPackage;
        Collection<JavaPackage> subPackages = javaPackage.getSubPackages();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(subPackages, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (JavaPackage javaPackage2 : subPackages) {
            arrayList.add(javaPackage2.getFqName());
        }
        return arrayList;
    }
}
