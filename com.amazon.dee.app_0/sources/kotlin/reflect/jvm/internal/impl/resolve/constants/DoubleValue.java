package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
/* compiled from: constantValues.kt */
/* loaded from: classes4.dex */
public final class DoubleValue extends ConstantValue<Double> {
    public DoubleValue(double d) {
        super(Double.valueOf(d));
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    @NotNull
    public String toString() {
        return getValue().doubleValue() + ".toDouble()";
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    @NotNull
    /* renamed from: getType  reason: collision with other method in class */
    public SimpleType mo12026getType(@NotNull ModuleDescriptor module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        SimpleType doubleType = module.getBuiltIns().getDoubleType();
        Intrinsics.checkExpressionValueIsNotNull(doubleType, "module.builtIns.doubleType");
        return doubleType;
    }
}
