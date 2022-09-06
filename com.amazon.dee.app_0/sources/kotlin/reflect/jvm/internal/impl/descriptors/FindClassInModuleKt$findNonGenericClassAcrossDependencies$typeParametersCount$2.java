package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.NotNull;
/* compiled from: findClassInModule.kt */
/* loaded from: classes2.dex */
final class FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$2 extends Lambda implements Function1<ClassId, Integer> {
    public static final FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$2 INSTANCE = new FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$2();

    FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$2() {
        super(1);
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final int invoke2(@NotNull ClassId it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        return 0;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Integer mo12165invoke(ClassId classId) {
        return Integer.valueOf(invoke2(classId));
    }
}
