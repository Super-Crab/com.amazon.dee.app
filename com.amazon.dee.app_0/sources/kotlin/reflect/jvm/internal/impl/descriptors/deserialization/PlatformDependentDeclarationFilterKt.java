package kotlin.reflect.jvm.internal.impl.descriptors.deserialization;

import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
/* compiled from: PlatformDependentDeclarationFilter.kt */
/* loaded from: classes2.dex */
public final class PlatformDependentDeclarationFilterKt {
    @NotNull
    private static final FqName PLATFORM_DEPENDENT_ANNOTATION_FQ_NAME = new FqName("kotlin.internal.PlatformDependent");

    @NotNull
    public static final FqName getPLATFORM_DEPENDENT_ANNOTATION_FQ_NAME() {
        return PLATFORM_DEPENDENT_ANNOTATION_FQ_NAME;
    }
}
