package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
/* compiled from: AbstractTypeAliasDescriptor.kt */
/* loaded from: classes2.dex */
final class AbstractTypeAliasDescriptor$isInner$1 extends Lambda implements Function1<UnwrappedType, Boolean> {
    final /* synthetic */ AbstractTypeAliasDescriptor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractTypeAliasDescriptor$isInner$1(AbstractTypeAliasDescriptor abstractTypeAliasDescriptor) {
        super(1);
        this.this$0 = abstractTypeAliasDescriptor;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(UnwrappedType unwrappedType) {
        return Boolean.valueOf(invoke2(unwrappedType));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(UnwrappedType type) {
        Intrinsics.checkExpressionValueIsNotNull(type, "type");
        if (!KotlinTypeKt.isError(type)) {
            ClassifierDescriptor mo12085getDeclarationDescriptor = type.mo12131getConstructor().mo12085getDeclarationDescriptor();
            return (mo12085getDeclarationDescriptor instanceof TypeParameterDescriptor) && (Intrinsics.areEqual(((TypeParameterDescriptor) mo12085getDeclarationDescriptor).mo11607getContainingDeclaration(), this.this$0) ^ true);
        }
        return false;
    }
}
