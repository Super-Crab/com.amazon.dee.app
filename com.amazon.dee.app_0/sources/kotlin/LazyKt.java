package kotlin;

import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"kotlin/LazyKt__LazyJVMKt", "kotlin/LazyKt__LazyKt"}, k = 4, mv = {1, 1, 16}, xi = 1)
/* loaded from: classes3.dex */
public final class LazyKt extends LazyKt__LazyKt {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LazyThreadSafetyMode.values().length];

        static {
            $EnumSwitchMapping$0[LazyThreadSafetyMode.SYNCHRONIZED.ordinal()] = 1;
            $EnumSwitchMapping$0[LazyThreadSafetyMode.PUBLICATION.ordinal()] = 2;
            $EnumSwitchMapping$0[LazyThreadSafetyMode.NONE.ordinal()] = 3;
        }
    }

    private LazyKt() {
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> Lazy<T> lazy(@NotNull Function0<? extends T> function0) {
        return LazyKt__LazyJVMKt.lazy(function0);
    }
}
