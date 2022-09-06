package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
/* compiled from: constantValues.kt */
/* loaded from: classes4.dex */
public final class BooleanValue extends ConstantValue<Boolean> {
    public BooleanValue(boolean z) {
        super(Boolean.valueOf(z));
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    @NotNull
    /* renamed from: getType  reason: collision with other method in class */
    public SimpleType mo12026getType(@NotNull ModuleDescriptor module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        SimpleType booleanType = module.getBuiltIns().getBooleanType();
        Intrinsics.checkExpressionValueIsNotNull(booleanType, "module.builtIns.booleanType");
        return booleanType;
    }
}
