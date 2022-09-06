package kotlin.reflect.jvm.internal.impl.resolve.constants;

import com.amazon.alexa.mobilytics.configuration.Config;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ClassLiteralValue.kt */
/* loaded from: classes4.dex */
public final class ClassLiteralValue {
    private final int arrayNestedness;
    @NotNull
    private final ClassId classId;

    public ClassLiteralValue(@NotNull ClassId classId, int i) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        this.classId = classId;
        this.arrayNestedness = i;
    }

    @NotNull
    public final ClassId component1() {
        return this.classId;
    }

    public final int component2() {
        return this.arrayNestedness;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof ClassLiteralValue)) {
                return false;
            }
            ClassLiteralValue classLiteralValue = (ClassLiteralValue) obj;
            return Intrinsics.areEqual(this.classId, classLiteralValue.classId) && this.arrayNestedness == classLiteralValue.arrayNestedness;
        }
        return true;
    }

    public final int getArrayNestedness() {
        return this.arrayNestedness;
    }

    @NotNull
    public final ClassId getClassId() {
        return this.classId;
    }

    public int hashCode() {
        ClassId classId = this.classId;
        return ((classId != null ? classId.hashCode() : 0) * 31) + this.arrayNestedness;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = this.arrayNestedness;
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("kotlin/Array<");
        }
        sb.append(this.classId);
        int i3 = this.arrayNestedness;
        for (int i4 = 0; i4 < i3; i4++) {
            sb.append(Config.Compare.GREATER_THAN);
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
