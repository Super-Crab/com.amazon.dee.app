package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ConstantValueFactory.kt */
/* loaded from: classes4.dex */
public final class ConstantValueFactory$createArrayValue$3 extends Lambda implements Function1<ModuleDescriptor, SimpleType> {
    final /* synthetic */ PrimitiveType $componentType;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstantValueFactory$createArrayValue$3(PrimitiveType primitiveType) {
        super(1);
        this.$componentType = primitiveType;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final SimpleType mo12165invoke(@NotNull ModuleDescriptor module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        SimpleType primitiveArrayKotlinType = module.getBuiltIns().getPrimitiveArrayKotlinType(this.$componentType);
        Intrinsics.checkExpressionValueIsNotNull(primitiveArrayKotlinType, "module.builtIns.getPrimi…KotlinType(componentType)");
        return primitiveArrayKotlinType;
    }
}
