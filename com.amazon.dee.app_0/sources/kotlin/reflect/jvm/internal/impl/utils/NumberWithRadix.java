package kotlin.reflect.jvm.internal.impl.utils;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: numbers.kt */
/* loaded from: classes4.dex */
public final class NumberWithRadix {
    @NotNull
    private final String number;
    private final int radix;

    public NumberWithRadix(@NotNull String number, int i) {
        Intrinsics.checkParameterIsNotNull(number, "number");
        this.number = number;
        this.radix = i;
    }

    @NotNull
    public final String component1() {
        return this.number;
    }

    public final int component2() {
        return this.radix;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof NumberWithRadix)) {
                return false;
            }
            NumberWithRadix numberWithRadix = (NumberWithRadix) obj;
            return Intrinsics.areEqual(this.number, numberWithRadix.number) && this.radix == numberWithRadix.radix;
        }
        return true;
    }

    public int hashCode() {
        String str = this.number;
        return ((str != null ? str.hashCode() : 0) * 31) + this.radix;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NumberWithRadix(number=");
        outline107.append(this.number);
        outline107.append(", radix=");
        return GeneratedOutlineSupport1.outline86(outline107, this.radix, ")");
    }
}
