package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LazyJavaScope.kt */
/* loaded from: classes3.dex */
public final class LazyJavaScope$functions$1 extends Lambda implements Function1<Name, List<? extends SimpleFunctionDescriptor>> {
    final /* synthetic */ LazyJavaScope this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaScope$functions$1(LazyJavaScope lazyJavaScope) {
        super(1);
        this.this$0 = lazyJavaScope;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final List<SimpleFunctionDescriptor> mo12165invoke(@NotNull Name name) {
        MemoizedFunctionToNotNull memoizedFunctionToNotNull;
        List<SimpleFunctionDescriptor> list;
        Intrinsics.checkParameterIsNotNull(name, "name");
        memoizedFunctionToNotNull = this.this$0.declaredFunctions;
        LinkedHashSet linkedHashSet = new LinkedHashSet((Collection) memoizedFunctionToNotNull.mo12165invoke(name));
        OverridingUtilsKt.retainMostSpecificInEachOverridableGroup(linkedHashSet);
        this.this$0.computeNonDeclaredFunctions(linkedHashSet, name);
        list = CollectionsKt___CollectionsKt.toList(this.this$0.getC().getComponents().getSignatureEnhancement().enhanceSignatures(this.this$0.getC(), linkedHashSet));
        return list;
    }
}
