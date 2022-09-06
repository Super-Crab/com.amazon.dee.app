package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: modifierChecks.kt */
/* loaded from: classes4.dex */
final class OperatorChecks$checks$3 extends Lambda implements Function1<FunctionDescriptor, String> {
    public static final OperatorChecks$checks$3 INSTANCE = new OperatorChecks$checks$3();

    OperatorChecks$checks$3() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final String mo12165invoke(@NotNull FunctionDescriptor receiver) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        ReceiverParameterDescriptor dispatchReceiverParameter = receiver.getDispatchReceiverParameter();
        if (dispatchReceiverParameter == null) {
            dispatchReceiverParameter = receiver.getExtensionReceiverParameter();
        }
        OperatorChecks operatorChecks = OperatorChecks.INSTANCE;
        boolean z2 = false;
        if (dispatchReceiverParameter != null) {
            KotlinType returnType = receiver.getReturnType();
            if (returnType != null) {
                KotlinType type = dispatchReceiverParameter.getType();
                Intrinsics.checkExpressionValueIsNotNull(type, "receiver.type");
                z = TypeUtilsKt.isSubtypeOf(returnType, type);
            } else {
                z = false;
            }
            if (z) {
                z2 = true;
            }
        }
        if (!z2) {
            return "receiver must be a supertype of the return type";
        }
        return null;
    }
}
