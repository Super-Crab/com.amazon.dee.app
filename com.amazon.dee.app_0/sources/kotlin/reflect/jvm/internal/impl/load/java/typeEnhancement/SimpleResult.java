package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
/* compiled from: typeEnhancement.kt */
/* loaded from: classes3.dex */
final class SimpleResult extends Result {
    @NotNull
    private final SimpleType type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimpleResult(@NotNull SimpleType type, int i, boolean z) {
        super(type, i, z);
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.type = type;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.Result
    @NotNull
    /* renamed from: getType  reason: collision with other method in class */
    public SimpleType mo11705getType() {
        return this.type;
    }
}
