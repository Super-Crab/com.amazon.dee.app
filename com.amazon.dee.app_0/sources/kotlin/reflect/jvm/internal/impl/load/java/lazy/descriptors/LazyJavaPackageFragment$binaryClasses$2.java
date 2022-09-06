package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinderKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LazyJavaPackageFragment.kt */
/* loaded from: classes3.dex */
public final class LazyJavaPackageFragment$binaryClasses$2 extends Lambda implements Function0<Map<String, ? extends KotlinJvmBinaryClass>> {
    final /* synthetic */ LazyJavaPackageFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaPackageFragment$binaryClasses$2(LazyJavaPackageFragment lazyJavaPackageFragment) {
        super(0);
        this.this$0 = lazyJavaPackageFragment;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final Map<String, ? extends KotlinJvmBinaryClass> mo12560invoke() {
        LazyJavaResolverContext lazyJavaResolverContext;
        Map<String, ? extends KotlinJvmBinaryClass> map;
        LazyJavaResolverContext lazyJavaResolverContext2;
        lazyJavaResolverContext = this.this$0.c;
        PackagePartProvider packagePartProvider = lazyJavaResolverContext.getComponents().getPackagePartProvider();
        String asString = this.this$0.getFqName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "fqName.asString()");
        List<String> findPackageParts = packagePartProvider.findPackageParts(asString);
        ArrayList arrayList = new ArrayList();
        for (String str : findPackageParts) {
            JvmClassName byInternalName = JvmClassName.byInternalName(str);
            Intrinsics.checkExpressionValueIsNotNull(byInternalName, "JvmClassName.byInternalName(partName)");
            ClassId classId = ClassId.topLevel(byInternalName.getFqNameForTopLevelClassMaybeWithDollars());
            Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(JvmClas…velClassMaybeWithDollars)");
            lazyJavaResolverContext2 = this.this$0.c;
            KotlinJvmBinaryClass findKotlinClass = KotlinClassFinderKt.findKotlinClass(lazyJavaResolverContext2.getComponents().getKotlinClassFinder(), classId);
            Pair pair = findKotlinClass != null ? TuplesKt.to(str, findKotlinClass) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        map = MapsKt__MapsKt.toMap(arrayList);
        return map;
    }
}
