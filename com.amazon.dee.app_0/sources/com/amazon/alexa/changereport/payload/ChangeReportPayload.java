package com.amazon.alexa.changereport.payload;

import com.amazon.alexa.changereport.Change;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes6.dex */
public class ChangeReportPayload<NamespaceType, NameType, InstanceType, ValueType> {
    @CheckForNull
    private final Change<NamespaceType, NameType, InstanceType, ValueType> change;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes6.dex */
    public static class ChangeReportPayloadBuilder<NamespaceType, NameType, InstanceType, ValueType> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Change<NamespaceType, NameType, InstanceType, ValueType> change;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        ChangeReportPayloadBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ChangeReportPayload<NamespaceType, NameType, InstanceType, ValueType> build() {
            return new ChangeReportPayload<>(this.change);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ChangeReportPayloadBuilder<NamespaceType, NameType, InstanceType, ValueType> change(Change<NamespaceType, NameType, InstanceType, ValueType> change) {
            this.change = change;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ChangeReportPayload.ChangeReportPayloadBuilder(change=");
            outline107.append(this.change);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public ChangeReportPayload(Change<NamespaceType, NameType, InstanceType, ValueType> change) {
        this.change = change;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <NamespaceType, NameType, InstanceType, ValueType> ChangeReportPayloadBuilder<NamespaceType, NameType, InstanceType, ValueType> builder() {
        return new ChangeReportPayloadBuilder<>();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected boolean canEqual(Object obj) {
        return obj instanceof ChangeReportPayload;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ChangeReportPayload)) {
            return false;
        }
        ChangeReportPayload changeReportPayload = (ChangeReportPayload) obj;
        if (!changeReportPayload.canEqual(this)) {
            return false;
        }
        Change<NamespaceType, NameType, InstanceType, ValueType> change = getChange();
        Change<NamespaceType, NameType, InstanceType, ValueType> change2 = changeReportPayload.getChange();
        return change != null ? change.equals(change2) : change2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Change<NamespaceType, NameType, InstanceType, ValueType> getChange() {
        return this.change;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        Change<NamespaceType, NameType, InstanceType, ValueType> change = getChange();
        return 59 + (change == null ? 43 : change.hashCode());
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ChangeReportPayload(change=");
        outline107.append(getChange());
        outline107.append(")");
        return outline107.toString();
    }
}
