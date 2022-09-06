package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: JvmBuiltInsSettings.kt */
/* loaded from: classes2.dex */
public final class JvmBuiltInsSettings$isMutabilityViolation$2 extends Lambda implements Function1<CallableMemberDescriptor, Boolean> {
    final /* synthetic */ JvmBuiltInsSettings this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmBuiltInsSettings$isMutabilityViolation$2(JvmBuiltInsSettings jvmBuiltInsSettings) {
        super(1);
        this.this$0 = jvmBuiltInsSettings;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(CallableMemberDescriptor callableMemberDescriptor) {
        return Boolean.valueOf(invoke2(callableMemberDescriptor));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(CallableMemberDescriptor overridden) {
        JavaToKotlinClassMap javaToKotlinClassMap;
        Intrinsics.checkExpressionValueIsNotNull(overridden, "overridden");
        if (overridden.getKind() == CallableMemberDescriptor.Kind.DECLARATION) {
            javaToKotlinClassMap = this.this$0.j2kClassMap;
            DeclarationDescriptor mo11607getContainingDeclaration = overridden.mo11607getContainingDeclaration();
            if (mo11607getContainingDeclaration == null) {
                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            }
            if (javaToKotlinClassMap.isMutable((ClassDescriptor) mo11607getContainingDeclaration)) {
                return true;
            }
        }
        return false;
    }
}
