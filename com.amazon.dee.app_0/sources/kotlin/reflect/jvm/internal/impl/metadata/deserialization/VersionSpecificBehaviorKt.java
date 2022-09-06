package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: versionSpecificBehavior.kt */
/* loaded from: classes4.dex */
public final class VersionSpecificBehaviorKt {
    public static final boolean isKotlin1Dot4OrLater(@NotNull BinaryVersion version) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        return version.getMajor() == 1 && version.getMinor() >= 4;
    }

    public static final boolean isVersionRequirementTableWrittenCorrectly(@NotNull BinaryVersion version) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        return isKotlin1Dot4OrLater(version);
    }
}
