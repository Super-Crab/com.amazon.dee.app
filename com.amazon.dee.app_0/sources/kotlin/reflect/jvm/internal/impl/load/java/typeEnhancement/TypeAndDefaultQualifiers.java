package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: signatureEnhancement.kt */
/* loaded from: classes3.dex */
public final class TypeAndDefaultQualifiers {
    @Nullable
    private final JavaTypeQualifiers defaultQualifiers;
    @NotNull
    private final KotlinType type;

    public TypeAndDefaultQualifiers(@NotNull KotlinType type, @Nullable JavaTypeQualifiers javaTypeQualifiers) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.type = type;
        this.defaultQualifiers = javaTypeQualifiers;
    }

    @NotNull
    public final KotlinType component1() {
        return this.type;
    }

    @Nullable
    public final JavaTypeQualifiers component2() {
        return this.defaultQualifiers;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof TypeAndDefaultQualifiers)) {
                return false;
            }
            TypeAndDefaultQualifiers typeAndDefaultQualifiers = (TypeAndDefaultQualifiers) obj;
            return Intrinsics.areEqual(this.type, typeAndDefaultQualifiers.type) && Intrinsics.areEqual(this.defaultQualifiers, typeAndDefaultQualifiers.defaultQualifiers);
        }
        return true;
    }

    @NotNull
    public final KotlinType getType() {
        return this.type;
    }

    public int hashCode() {
        KotlinType kotlinType = this.type;
        int i = 0;
        int hashCode = (kotlinType != null ? kotlinType.hashCode() : 0) * 31;
        JavaTypeQualifiers javaTypeQualifiers = this.defaultQualifiers;
        if (javaTypeQualifiers != null) {
            i = javaTypeQualifiers.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TypeAndDefaultQualifiers(type=");
        outline107.append(this.type);
        outline107.append(", defaultQualifiers=");
        outline107.append(this.defaultQualifiers);
        outline107.append(")");
        return outline107.toString();
    }
}
