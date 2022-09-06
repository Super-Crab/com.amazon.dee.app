package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
/* compiled from: constantValues.kt */
/* loaded from: classes4.dex */
public final class LongValue extends IntegerValueConstant<Long> {
    public LongValue(long j) {
        super(Long.valueOf(j));
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    @NotNull
    public String toString() {
        return getValue().longValue() + ".toLong()";
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    @NotNull
    /* renamed from: getType  reason: collision with other method in class */
    public SimpleType mo12026getType(@NotNull ModuleDescriptor module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        SimpleType longType = module.getBuiltIns().getLongType();
        Intrinsics.checkExpressionValueIsNotNull(longType, "module.builtIns.longType");
        return longType;
    }
}
