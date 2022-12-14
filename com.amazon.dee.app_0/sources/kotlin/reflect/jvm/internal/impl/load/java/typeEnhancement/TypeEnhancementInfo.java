package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: predefinedEnhancementInfo.kt */
/* loaded from: classes3.dex */
public final class TypeEnhancementInfo {
    @NotNull
    private final Map<Integer, JavaTypeQualifiers> map;

    public TypeEnhancementInfo(@NotNull Map<Integer, JavaTypeQualifiers> map) {
        Intrinsics.checkParameterIsNotNull(map, "map");
        this.map = map;
    }

    @NotNull
    public final Map<Integer, JavaTypeQualifiers> getMap() {
        return this.map;
    }
}
