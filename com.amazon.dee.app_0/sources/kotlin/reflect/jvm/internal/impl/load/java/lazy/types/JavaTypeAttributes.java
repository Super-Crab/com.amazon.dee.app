package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JavaTypeResolver.kt */
/* loaded from: classes3.dex */
public final class JavaTypeAttributes {
    @NotNull
    private final JavaTypeFlexibility flexibility;
    @NotNull
    private final TypeUsage howThisTypeIsUsed;
    private final boolean isForAnnotationParameter;
    @Nullable
    private final TypeParameterDescriptor upperBoundOfTypeParameter;

    public JavaTypeAttributes(@NotNull TypeUsage howThisTypeIsUsed, @NotNull JavaTypeFlexibility flexibility, boolean z, @Nullable TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(howThisTypeIsUsed, "howThisTypeIsUsed");
        Intrinsics.checkParameterIsNotNull(flexibility, "flexibility");
        this.howThisTypeIsUsed = howThisTypeIsUsed;
        this.flexibility = flexibility;
        this.isForAnnotationParameter = z;
        this.upperBoundOfTypeParameter = typeParameterDescriptor;
    }

    public static /* synthetic */ JavaTypeAttributes copy$default(JavaTypeAttributes javaTypeAttributes, TypeUsage typeUsage, JavaTypeFlexibility javaTypeFlexibility, boolean z, TypeParameterDescriptor typeParameterDescriptor, int i, Object obj) {
        if ((i & 1) != 0) {
            typeUsage = javaTypeAttributes.howThisTypeIsUsed;
        }
        if ((i & 2) != 0) {
            javaTypeFlexibility = javaTypeAttributes.flexibility;
        }
        if ((i & 4) != 0) {
            z = javaTypeAttributes.isForAnnotationParameter;
        }
        if ((i & 8) != 0) {
            typeParameterDescriptor = javaTypeAttributes.upperBoundOfTypeParameter;
        }
        return javaTypeAttributes.copy(typeUsage, javaTypeFlexibility, z, typeParameterDescriptor);
    }

    @NotNull
    public final JavaTypeAttributes copy(@NotNull TypeUsage howThisTypeIsUsed, @NotNull JavaTypeFlexibility flexibility, boolean z, @Nullable TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkParameterIsNotNull(howThisTypeIsUsed, "howThisTypeIsUsed");
        Intrinsics.checkParameterIsNotNull(flexibility, "flexibility");
        return new JavaTypeAttributes(howThisTypeIsUsed, flexibility, z, typeParameterDescriptor);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof JavaTypeAttributes)) {
                return false;
            }
            JavaTypeAttributes javaTypeAttributes = (JavaTypeAttributes) obj;
            return Intrinsics.areEqual(this.howThisTypeIsUsed, javaTypeAttributes.howThisTypeIsUsed) && Intrinsics.areEqual(this.flexibility, javaTypeAttributes.flexibility) && this.isForAnnotationParameter == javaTypeAttributes.isForAnnotationParameter && Intrinsics.areEqual(this.upperBoundOfTypeParameter, javaTypeAttributes.upperBoundOfTypeParameter);
        }
        return true;
    }

    @NotNull
    public final JavaTypeFlexibility getFlexibility() {
        return this.flexibility;
    }

    @NotNull
    public final TypeUsage getHowThisTypeIsUsed() {
        return this.howThisTypeIsUsed;
    }

    @Nullable
    public final TypeParameterDescriptor getUpperBoundOfTypeParameter() {
        return this.upperBoundOfTypeParameter;
    }

    public int hashCode() {
        TypeUsage typeUsage = this.howThisTypeIsUsed;
        int i = 0;
        int hashCode = (typeUsage != null ? typeUsage.hashCode() : 0) * 31;
        JavaTypeFlexibility javaTypeFlexibility = this.flexibility;
        int hashCode2 = (hashCode + (javaTypeFlexibility != null ? javaTypeFlexibility.hashCode() : 0)) * 31;
        boolean z = this.isForAnnotationParameter;
        if (z) {
            z = true;
        }
        int i2 = z ? 1 : 0;
        int i3 = z ? 1 : 0;
        int i4 = (hashCode2 + i2) * 31;
        TypeParameterDescriptor typeParameterDescriptor = this.upperBoundOfTypeParameter;
        if (typeParameterDescriptor != null) {
            i = typeParameterDescriptor.hashCode();
        }
        return i4 + i;
    }

    public final boolean isForAnnotationParameter() {
        return this.isForAnnotationParameter;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("JavaTypeAttributes(howThisTypeIsUsed=");
        outline107.append(this.howThisTypeIsUsed);
        outline107.append(", flexibility=");
        outline107.append(this.flexibility);
        outline107.append(", isForAnnotationParameter=");
        outline107.append(this.isForAnnotationParameter);
        outline107.append(", upperBoundOfTypeParameter=");
        outline107.append(this.upperBoundOfTypeParameter);
        outline107.append(")");
        return outline107.toString();
    }

    @NotNull
    public final JavaTypeAttributes withFlexibility(@NotNull JavaTypeFlexibility flexibility) {
        Intrinsics.checkParameterIsNotNull(flexibility, "flexibility");
        return copy$default(this, null, flexibility, false, null, 13, null);
    }

    public /* synthetic */ JavaTypeAttributes(TypeUsage typeUsage, JavaTypeFlexibility javaTypeFlexibility, boolean z, TypeParameterDescriptor typeParameterDescriptor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeUsage, (i & 2) != 0 ? JavaTypeFlexibility.INFLEXIBLE : javaTypeFlexibility, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : typeParameterDescriptor);
    }
}
