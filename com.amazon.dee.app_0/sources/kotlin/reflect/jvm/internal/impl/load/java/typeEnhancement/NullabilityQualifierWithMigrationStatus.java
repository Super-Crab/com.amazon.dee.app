package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: signatureEnhancement.kt */
/* loaded from: classes3.dex */
public final class NullabilityQualifierWithMigrationStatus {
    private final boolean isForWarningOnly;
    @NotNull
    private final NullabilityQualifier qualifier;

    public NullabilityQualifierWithMigrationStatus(@NotNull NullabilityQualifier qualifier, boolean z) {
        Intrinsics.checkParameterIsNotNull(qualifier, "qualifier");
        this.qualifier = qualifier;
        this.isForWarningOnly = z;
    }

    public static /* synthetic */ NullabilityQualifierWithMigrationStatus copy$default(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus, NullabilityQualifier nullabilityQualifier, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            nullabilityQualifier = nullabilityQualifierWithMigrationStatus.qualifier;
        }
        if ((i & 2) != 0) {
            z = nullabilityQualifierWithMigrationStatus.isForWarningOnly;
        }
        return nullabilityQualifierWithMigrationStatus.copy(nullabilityQualifier, z);
    }

    @NotNull
    public final NullabilityQualifierWithMigrationStatus copy(@NotNull NullabilityQualifier qualifier, boolean z) {
        Intrinsics.checkParameterIsNotNull(qualifier, "qualifier");
        return new NullabilityQualifierWithMigrationStatus(qualifier, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof NullabilityQualifierWithMigrationStatus)) {
                return false;
            }
            NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus = (NullabilityQualifierWithMigrationStatus) obj;
            return Intrinsics.areEqual(this.qualifier, nullabilityQualifierWithMigrationStatus.qualifier) && this.isForWarningOnly == nullabilityQualifierWithMigrationStatus.isForWarningOnly;
        }
        return true;
    }

    @NotNull
    public final NullabilityQualifier getQualifier() {
        return this.qualifier;
    }

    public int hashCode() {
        NullabilityQualifier nullabilityQualifier = this.qualifier;
        int hashCode = (nullabilityQualifier != null ? nullabilityQualifier.hashCode() : 0) * 31;
        boolean z = this.isForWarningOnly;
        if (z) {
            z = true;
        }
        int i = z ? 1 : 0;
        int i2 = z ? 1 : 0;
        return hashCode + i;
    }

    public final boolean isForWarningOnly() {
        return this.isForWarningOnly;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NullabilityQualifierWithMigrationStatus(qualifier=");
        outline107.append(this.qualifier);
        outline107.append(", isForWarningOnly=");
        return GeneratedOutlineSupport1.outline97(outline107, this.isForWarningOnly, ")");
    }

    public /* synthetic */ NullabilityQualifierWithMigrationStatus(NullabilityQualifier nullabilityQualifier, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nullabilityQualifier, (i & 2) != 0 ? false : z);
    }
}
