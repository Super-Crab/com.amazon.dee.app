package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LazyJavaScope.kt */
/* loaded from: classes3.dex */
public final class LazyJavaScope$properties$1 extends Lambda implements Function1<Name, List<? extends PropertyDescriptor>> {
    final /* synthetic */ LazyJavaScope this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaScope$properties$1(LazyJavaScope lazyJavaScope) {
        super(1);
        this.this$0 = lazyJavaScope;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final List<PropertyDescriptor> mo12165invoke(@NotNull Name name) {
        MemoizedFunctionToNullable memoizedFunctionToNullable;
        List<PropertyDescriptor> list;
        List<PropertyDescriptor> list2;
        Intrinsics.checkParameterIsNotNull(name, "name");
        ArrayList arrayList = new ArrayList();
        memoizedFunctionToNullable = this.this$0.declaredField;
        CollectionsKt.addIfNotNull(arrayList, memoizedFunctionToNullable.mo12165invoke(name));
        this.this$0.computeNonDeclaredProperties(name, arrayList);
        if (DescriptorUtils.isAnnotationClass(this.this$0.mo11690getOwnerDescriptor())) {
            list2 = CollectionsKt___CollectionsKt.toList(arrayList);
            return list2;
        }
        list = CollectionsKt___CollectionsKt.toList(this.this$0.getC().getComponents().getSignatureEnhancement().enhanceSignatures(this.this$0.getC(), arrayList));
        return list;
    }
}
