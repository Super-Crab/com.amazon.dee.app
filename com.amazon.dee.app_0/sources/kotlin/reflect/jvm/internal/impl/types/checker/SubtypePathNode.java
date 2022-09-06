package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: utils.kt */
/* loaded from: classes4.dex */
final class SubtypePathNode {
    @Nullable
    private final SubtypePathNode previous;
    @NotNull
    private final KotlinType type;

    public SubtypePathNode(@NotNull KotlinType type, @Nullable SubtypePathNode subtypePathNode) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.type = type;
        this.previous = subtypePathNode;
    }

    @Nullable
    public final SubtypePathNode getPrevious() {
        return this.previous;
    }

    @NotNull
    public final KotlinType getType() {
        return this.type;
    }
}
