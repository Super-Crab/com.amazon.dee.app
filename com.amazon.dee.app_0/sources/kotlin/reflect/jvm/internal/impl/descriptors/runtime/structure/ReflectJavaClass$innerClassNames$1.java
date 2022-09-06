package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: ReflectJavaClass.kt */
/* loaded from: classes2.dex */
final class ReflectJavaClass$innerClassNames$1 extends Lambda implements Function1<Class<?>, Boolean> {
    public static final ReflectJavaClass$innerClassNames$1 INSTANCE = new ReflectJavaClass$innerClassNames$1();

    ReflectJavaClass$innerClassNames$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(Class<?> cls) {
        return Boolean.valueOf(invoke2(cls));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(Class<?> it2) {
        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
        String simpleName = it2.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "it.simpleName");
        return simpleName.length() == 0;
    }
}
