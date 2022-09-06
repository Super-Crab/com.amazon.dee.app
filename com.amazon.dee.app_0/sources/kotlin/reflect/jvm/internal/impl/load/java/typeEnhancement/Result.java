package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: typeEnhancement.kt */
/* loaded from: classes3.dex */
class Result {
    private final int subtreeSize;
    @NotNull
    private final KotlinType type;
    private final boolean wereChanges;

    public Result(@NotNull KotlinType type, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.type = type;
        this.subtreeSize = i;
        this.wereChanges = z;
    }

    public final int getSubtreeSize() {
        return this.subtreeSize;
    }

    @NotNull
    /* renamed from: getType */
    public KotlinType mo11705getType() {
        return this.type;
    }

    @Nullable
    public final KotlinType getTypeIfChanged() {
        KotlinType mo11705getType = mo11705getType();
        if (this.wereChanges) {
            return mo11705getType;
        }
        return null;
    }

    public final boolean getWereChanges() {
        return this.wereChanges;
    }
}
