package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LazyJavaClassMemberScope.kt */
/* loaded from: classes3.dex */
public final class LazyJavaClassMemberScope$isVisibleAsFunctionInCurrentClass$$inlined$any$lambda$1 extends Lambda implements Function1<Name, Collection<? extends SimpleFunctionDescriptor>> {
    final /* synthetic */ SimpleFunctionDescriptor $function$inlined;
    final /* synthetic */ LazyJavaClassMemberScope this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaClassMemberScope$isVisibleAsFunctionInCurrentClass$$inlined$any$lambda$1(LazyJavaClassMemberScope lazyJavaClassMemberScope, SimpleFunctionDescriptor simpleFunctionDescriptor) {
        super(1);
        this.this$0 = lazyJavaClassMemberScope;
        this.$function$inlined = simpleFunctionDescriptor;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Collection<SimpleFunctionDescriptor> mo12165invoke(@NotNull Name accessorName) {
        Collection searchMethodsByNameWithoutBuiltinMagic;
        Collection searchMethodsInSupertypesWithoutBuiltinMagic;
        List plus;
        List listOf;
        Intrinsics.checkParameterIsNotNull(accessorName, "accessorName");
        if (!Intrinsics.areEqual(this.$function$inlined.getName(), accessorName)) {
            searchMethodsByNameWithoutBuiltinMagic = this.this$0.searchMethodsByNameWithoutBuiltinMagic(accessorName);
            searchMethodsInSupertypesWithoutBuiltinMagic = this.this$0.searchMethodsInSupertypesWithoutBuiltinMagic(accessorName);
            plus = CollectionsKt___CollectionsKt.plus((Collection) searchMethodsByNameWithoutBuiltinMagic, (Iterable) searchMethodsInSupertypesWithoutBuiltinMagic);
            return plus;
        }
        listOf = CollectionsKt__CollectionsJVMKt.listOf(this.$function$inlined);
        return listOf;
    }
}
