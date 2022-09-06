package kotlin.reflect.jvm.internal.impl.incremental.components;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LookupLocation.kt */
/* loaded from: classes2.dex */
public final class Position implements Serializable {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Position NO_POSITION = new Position(-1, -1);
    private final int column;
    private final int line;

    /* compiled from: LookupLocation.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Position getNO_POSITION() {
            return Position.NO_POSITION;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Position(int i, int i2) {
        this.line = i;
        this.column = i2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Position)) {
                return false;
            }
            Position position = (Position) obj;
            return this.line == position.line && this.column == position.column;
        }
        return true;
    }

    public int hashCode() {
        return (this.line * 31) + this.column;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Position(line=");
        outline107.append(this.line);
        outline107.append(", column=");
        return GeneratedOutlineSupport1.outline86(outline107, this.column, ")");
    }
}
