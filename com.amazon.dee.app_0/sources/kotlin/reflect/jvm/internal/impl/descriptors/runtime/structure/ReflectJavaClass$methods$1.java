package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Method;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ReflectJavaClass.kt */
/* loaded from: classes2.dex */
public final class ReflectJavaClass$methods$1 extends Lambda implements Function1<Method, Boolean> {
    final /* synthetic */ ReflectJavaClass this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReflectJavaClass$methods$1(ReflectJavaClass reflectJavaClass) {
        super(1);
        this.this$0 = reflectJavaClass;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(Method method) {
        return Boolean.valueOf(invoke2(method));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(Method method) {
        boolean isEnumValuesOrValueOf;
        Intrinsics.checkExpressionValueIsNotNull(method, "method");
        if (!method.isSynthetic()) {
            if (!this.this$0.isEnum()) {
                return true;
            }
            isEnumValuesOrValueOf = this.this$0.isEnumValuesOrValueOf(method);
            if (!isEnumValuesOrValueOf) {
                return true;
            }
        }
        return false;
    }
}
