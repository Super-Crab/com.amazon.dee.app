package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: IncompatibleVersionErrorData.kt */
/* loaded from: classes4.dex */
public final class IncompatibleVersionErrorData<T extends BinaryVersion> {
    @NotNull
    private final T actualVersion;
    @NotNull
    private final ClassId classId;
    @NotNull
    private final T expectedVersion;
    @NotNull
    private final String filePath;

    public IncompatibleVersionErrorData(@NotNull T actualVersion, @NotNull T expectedVersion, @NotNull String filePath, @NotNull ClassId classId) {
        Intrinsics.checkParameterIsNotNull(actualVersion, "actualVersion");
        Intrinsics.checkParameterIsNotNull(expectedVersion, "expectedVersion");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        this.actualVersion = actualVersion;
        this.expectedVersion = expectedVersion;
        this.filePath = filePath;
        this.classId = classId;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof IncompatibleVersionErrorData)) {
                return false;
            }
            IncompatibleVersionErrorData incompatibleVersionErrorData = (IncompatibleVersionErrorData) obj;
            return Intrinsics.areEqual(this.actualVersion, incompatibleVersionErrorData.actualVersion) && Intrinsics.areEqual(this.expectedVersion, incompatibleVersionErrorData.expectedVersion) && Intrinsics.areEqual(this.filePath, incompatibleVersionErrorData.filePath) && Intrinsics.areEqual(this.classId, incompatibleVersionErrorData.classId);
        }
        return true;
    }

    public int hashCode() {
        T t = this.actualVersion;
        int i = 0;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        T t2 = this.expectedVersion;
        int hashCode2 = (hashCode + (t2 != null ? t2.hashCode() : 0)) * 31;
        String str = this.filePath;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        ClassId classId = this.classId;
        if (classId != null) {
            i = classId.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IncompatibleVersionErrorData(actualVersion=");
        outline107.append(this.actualVersion);
        outline107.append(", expectedVersion=");
        outline107.append(this.expectedVersion);
        outline107.append(", filePath=");
        outline107.append(this.filePath);
        outline107.append(", classId=");
        outline107.append(this.classId);
        outline107.append(")");
        return outline107.toString();
    }
}
