package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: DeserializedClassDescriptor.kt */
/* loaded from: classes4.dex */
final class DeserializedClassDescriptor$DeserializedClassMemberScope$computeNonDeclaredFunctions$1 extends Lambda implements Function1<SimpleFunctionDescriptor, Boolean> {
    final /* synthetic */ DeserializedClassDescriptor.DeserializedClassMemberScope this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeserializedClassDescriptor$DeserializedClassMemberScope$computeNonDeclaredFunctions$1(DeserializedClassDescriptor.DeserializedClassMemberScope deserializedClassMemberScope) {
        super(1);
        this.this$0 = deserializedClassMemberScope;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(SimpleFunctionDescriptor simpleFunctionDescriptor) {
        return Boolean.valueOf(invoke2(simpleFunctionDescriptor));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@NotNull SimpleFunctionDescriptor it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        return this.this$0.getC().getComponents().getPlatformDependentDeclarationFilter().isFunctionAvailable(this.this$0.this$0, it2);
    }
}
