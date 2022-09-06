package kotlin.reflect.jvm.internal.impl.types.typeUtil;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TypeUtils.kt */
/* loaded from: classes4.dex */
public final class TypeUtilsKt$requiresTypeAliasExpansion$1 extends Lambda implements Function1<UnwrappedType, Boolean> {
    public static final TypeUtilsKt$requiresTypeAliasExpansion$1 INSTANCE = new TypeUtilsKt$requiresTypeAliasExpansion$1();

    TypeUtilsKt$requiresTypeAliasExpansion$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(UnwrappedType unwrappedType) {
        return Boolean.valueOf(invoke2(unwrappedType));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@NotNull UnwrappedType it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        ClassifierDescriptor mo12085getDeclarationDescriptor = it2.mo12131getConstructor().mo12085getDeclarationDescriptor();
        if (mo12085getDeclarationDescriptor != null) {
            return (mo12085getDeclarationDescriptor instanceof TypeAliasDescriptor) || (mo12085getDeclarationDescriptor instanceof TypeParameterDescriptor);
        }
        return false;
    }
}
