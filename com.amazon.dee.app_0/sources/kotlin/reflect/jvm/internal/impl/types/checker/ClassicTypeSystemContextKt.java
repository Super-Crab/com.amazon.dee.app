package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClassicTypeSystemContext.kt */
/* loaded from: classes4.dex */
public final class ClassicTypeSystemContextKt {

    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TypeVariance.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[TypeVariance.INV.ordinal()] = 1;
            $EnumSwitchMapping$0[TypeVariance.IN.ordinal()] = 2;
            $EnumSwitchMapping$0[TypeVariance.OUT.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[Variance.values().length];
            $EnumSwitchMapping$1[Variance.INVARIANT.ordinal()] = 1;
            $EnumSwitchMapping$1[Variance.IN_VARIANCE.ordinal()] = 2;
            $EnumSwitchMapping$1[Variance.OUT_VARIANCE.ordinal()] = 3;
        }
    }

    @NotNull
    public static final TypeVariance convertVariance(@NotNull Variance convertVariance) {
        Intrinsics.checkParameterIsNotNull(convertVariance, "$this$convertVariance");
        int i = WhenMappings.$EnumSwitchMapping$1[convertVariance.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return TypeVariance.IN;
            }
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            return TypeVariance.OUT;
        }
        return TypeVariance.INV;
    }
}
