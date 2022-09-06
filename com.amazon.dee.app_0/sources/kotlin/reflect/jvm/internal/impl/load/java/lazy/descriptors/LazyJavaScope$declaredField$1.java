package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LazyJavaScope.kt */
/* loaded from: classes3.dex */
public final class LazyJavaScope$declaredField$1 extends Lambda implements Function1<Name, PropertyDescriptor> {
    final /* synthetic */ LazyJavaScope this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaScope$declaredField$1(LazyJavaScope lazyJavaScope) {
        super(1);
        this.this$0 = lazyJavaScope;
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final PropertyDescriptor mo12165invoke(@NotNull Name name) {
        PropertyDescriptor resolveProperty;
        MemoizedFunctionToNullable memoizedFunctionToNullable;
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (this.this$0.getMainScope() != null) {
            memoizedFunctionToNullable = this.this$0.getMainScope().declaredField;
            return (PropertyDescriptor) memoizedFunctionToNullable.mo12165invoke(name);
        }
        JavaField findFieldByName = this.this$0.getDeclaredMemberIndex().mo12560invoke().findFieldByName(name);
        if (findFieldByName == null || findFieldByName.isEnumEntry()) {
            return null;
        }
        resolveProperty = this.this$0.resolveProperty(findFieldByName);
        return resolveProperty;
    }
}
