package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: context.kt */
/* loaded from: classes3.dex */
public final class NullabilityQualifierWithApplicability {
    @NotNull
    private final NullabilityQualifierWithMigrationStatus nullabilityQualifier;
    @NotNull
    private final Collection<AnnotationTypeQualifierResolver.QualifierApplicabilityType> qualifierApplicabilityTypes;

    /* JADX WARN: Multi-variable type inference failed */
    public NullabilityQualifierWithApplicability(@NotNull NullabilityQualifierWithMigrationStatus nullabilityQualifier, @NotNull Collection<? extends AnnotationTypeQualifierResolver.QualifierApplicabilityType> qualifierApplicabilityTypes) {
        Intrinsics.checkParameterIsNotNull(nullabilityQualifier, "nullabilityQualifier");
        Intrinsics.checkParameterIsNotNull(qualifierApplicabilityTypes, "qualifierApplicabilityTypes");
        this.nullabilityQualifier = nullabilityQualifier;
        this.qualifierApplicabilityTypes = qualifierApplicabilityTypes;
    }

    @NotNull
    public final NullabilityQualifierWithMigrationStatus component1() {
        return this.nullabilityQualifier;
    }

    @NotNull
    public final Collection<AnnotationTypeQualifierResolver.QualifierApplicabilityType> component2() {
        return this.qualifierApplicabilityTypes;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof NullabilityQualifierWithApplicability)) {
                return false;
            }
            NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability = (NullabilityQualifierWithApplicability) obj;
            return Intrinsics.areEqual(this.nullabilityQualifier, nullabilityQualifierWithApplicability.nullabilityQualifier) && Intrinsics.areEqual(this.qualifierApplicabilityTypes, nullabilityQualifierWithApplicability.qualifierApplicabilityTypes);
        }
        return true;
    }

    public int hashCode() {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus = this.nullabilityQualifier;
        int i = 0;
        int hashCode = (nullabilityQualifierWithMigrationStatus != null ? nullabilityQualifierWithMigrationStatus.hashCode() : 0) * 31;
        Collection<AnnotationTypeQualifierResolver.QualifierApplicabilityType> collection = this.qualifierApplicabilityTypes;
        if (collection != null) {
            i = collection.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NullabilityQualifierWithApplicability(nullabilityQualifier=");
        outline107.append(this.nullabilityQualifier);
        outline107.append(", qualifierApplicabilityTypes=");
        outline107.append(this.qualifierApplicabilityTypes);
        outline107.append(")");
        return outline107.toString();
    }
}
