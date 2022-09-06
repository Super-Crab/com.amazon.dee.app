package com.amazon.alexa.statereporting.lib.model.api.event;

import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes10.dex */
public class ChangeReportEventPayload<PropertyKey> {
    @CheckForNull
    private final Change<PropertyKey> change;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes10.dex */
    public static class ChangeReportEventPayloadBuilder<PropertyKey> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Change<PropertyKey> change;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        ChangeReportEventPayloadBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ChangeReportEventPayload<PropertyKey> build() {
            return new ChangeReportEventPayload<>(this.change);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ChangeReportEventPayloadBuilder<PropertyKey> change(Change<PropertyKey> change) {
            this.change = change;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ChangeReportEventPayload.ChangeReportEventPayloadBuilder(change=");
            outline107.append(this.change);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public ChangeReportEventPayload(Change<PropertyKey> change) {
        this.change = change;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <PropertyKey> ChangeReportEventPayloadBuilder<PropertyKey> builder() {
        return new ChangeReportEventPayloadBuilder<>();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected boolean canEqual(Object obj) {
        return obj instanceof ChangeReportEventPayload;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ChangeReportEventPayload)) {
            return false;
        }
        ChangeReportEventPayload changeReportEventPayload = (ChangeReportEventPayload) obj;
        if (!changeReportEventPayload.canEqual(this)) {
            return false;
        }
        Change<PropertyKey> change = getChange();
        Change<PropertyKey> change2 = changeReportEventPayload.getChange();
        return change != null ? change.equals(change2) : change2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Change<PropertyKey> getChange() {
        return this.change;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        Change<PropertyKey> change = getChange();
        return 59 + (change == null ? 43 : change.hashCode());
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ChangeReportEventPayload(change=");
        outline107.append(getChange());
        outline107.append(")");
        return outline107.toString();
    }
}
