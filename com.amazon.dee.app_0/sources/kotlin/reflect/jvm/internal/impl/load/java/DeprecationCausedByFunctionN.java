package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: utils.kt */
/* loaded from: classes3.dex */
public final class DeprecationCausedByFunctionN {
    @NotNull
    private final DeclarationDescriptor target;

    public DeprecationCausedByFunctionN(@NotNull DeclarationDescriptor target) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        this.target = target;
    }
}
