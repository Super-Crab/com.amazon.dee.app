package com.amazon.alexa.changereport;

import com.amazon.alexa.changereport.type.ChangeReportType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes6.dex */
public final class Cause {
    @CheckForNull
    private final ChangeReportType type;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes6.dex */
    public static class CauseBuilder {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private ChangeReportType type;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        CauseBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Cause build() {
            return new Cause(this.type);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cause.CauseBuilder(type=");
            outline107.append(this.type);
            outline107.append(")");
            return outline107.toString();
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public CauseBuilder type(ChangeReportType changeReportType) {
            this.type = changeReportType;
            return this;
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected Cause(ChangeReportType changeReportType) {
        this.type = changeReportType;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static CauseBuilder builder() {
        return new CauseBuilder();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Cause)) {
            return false;
        }
        ChangeReportType type = getType();
        ChangeReportType type2 = ((Cause) obj).getType();
        return type != null ? type.equals(type2) : type2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public ChangeReportType getType() {
        return this.type;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        ChangeReportType type = getType();
        return 59 + (type == null ? 43 : type.hashCode());
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cause(type=");
        outline107.append(getType());
        outline107.append(")");
        return outline107.toString();
    }
}
