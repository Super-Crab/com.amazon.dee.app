package kotlin.reflect.jvm.internal.impl.platform;

import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: platformUtil.kt */
/* loaded from: classes4.dex */
public final class PlatformUtilKt {
    @NotNull
    public static final String getPresentableDescription(@NotNull TargetPlatform presentableDescription) {
        String joinToString$default;
        Intrinsics.checkParameterIsNotNull(presentableDescription, "$this$presentableDescription");
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(presentableDescription.getComponentPlatforms(), "/", null, null, 0, null, null, 62, null);
        return joinToString$default;
    }
}
