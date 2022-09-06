package kotlin.reflect.jvm.internal.impl.resolve.constants;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.util.List;
import kotlin._Assertions;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
/* compiled from: constantValues.kt */
/* loaded from: classes4.dex */
public final class ArrayValue extends ConstantValue<List<? extends ConstantValue<?>>> {
    private final Function1<ModuleDescriptor, KotlinType> computeType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ArrayValue(@NotNull List<? extends ConstantValue<?>> value, @NotNull Function1<? super ModuleDescriptor, ? extends KotlinType> computeType) {
        super(value);
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(computeType, "computeType");
        this.computeType = computeType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    @NotNull
    /* renamed from: getType */
    public KotlinType mo12026getType(@NotNull ModuleDescriptor module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        KotlinType mo12165invoke = this.computeType.mo12165invoke(module);
        boolean z = KotlinBuiltIns.isArray(mo12165invoke) || KotlinBuiltIns.isPrimitiveArray(mo12165invoke);
        if (!_Assertions.ENABLED || z) {
            return mo12165invoke;
        }
        throw new AssertionError("Type should be an array, but was " + mo12165invoke + RealTimeTextConstants.COLON_SPACE + getValue());
    }
}
