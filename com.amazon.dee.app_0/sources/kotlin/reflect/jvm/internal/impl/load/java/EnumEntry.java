package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: utils.kt */
/* loaded from: classes3.dex */
public final class EnumEntry extends JavaDefaultValue {
    @NotNull
    private final ClassDescriptor descriptor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnumEntry(@NotNull ClassDescriptor descriptor) {
        super(null);
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        this.descriptor = descriptor;
    }
}
