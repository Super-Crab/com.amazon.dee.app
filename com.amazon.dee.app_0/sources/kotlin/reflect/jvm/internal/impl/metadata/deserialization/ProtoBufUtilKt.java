package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ProtoBufUtil.kt */
/* loaded from: classes4.dex */
public final class ProtoBufUtilKt {
    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static final <M extends GeneratedMessageLite.ExtendableMessage<M>, T> T getExtensionOrNull(@NotNull GeneratedMessageLite.ExtendableMessage<M> getExtensionOrNull, @NotNull GeneratedMessageLite.GeneratedExtension<M, T> extension) {
        Intrinsics.checkParameterIsNotNull(getExtensionOrNull, "$this$getExtensionOrNull");
        Intrinsics.checkParameterIsNotNull(extension, "extension");
        if (getExtensionOrNull.hasExtension(extension)) {
            return (T) getExtensionOrNull.getExtension(extension);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static final <M extends GeneratedMessageLite.ExtendableMessage<M>, T> T getExtensionOrNull(@NotNull GeneratedMessageLite.ExtendableMessage<M> getExtensionOrNull, @NotNull GeneratedMessageLite.GeneratedExtension<M, List<T>> extension, int i) {
        Intrinsics.checkParameterIsNotNull(getExtensionOrNull, "$this$getExtensionOrNull");
        Intrinsics.checkParameterIsNotNull(extension, "extension");
        if (i < getExtensionOrNull.getExtensionCount(extension)) {
            return (T) getExtensionOrNull.getExtension(extension, i);
        }
        return null;
    }
}
