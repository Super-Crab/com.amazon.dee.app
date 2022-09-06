package com.amazon.alexa.changereport;

import com.amazon.alexa.context.Context;
import com.amazon.alexa.event.Event;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes6.dex */
public class ChangeReport<NamespaceType, NameType, InstanceType, PayloadType> {
    @CheckForNull
    private final Context context;
    @CheckForNull
    private final Event<NamespaceType, NameType, InstanceType, PayloadType> event;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes6.dex */
    public static class ChangeReportBuilder<NamespaceType, NameType, InstanceType, PayloadType> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Context context;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Event<NamespaceType, NameType, InstanceType, PayloadType> event;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        ChangeReportBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ChangeReport<NamespaceType, NameType, InstanceType, PayloadType> build() {
            return new ChangeReport<>(this.event, this.context);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ChangeReportBuilder<NamespaceType, NameType, InstanceType, PayloadType> context(Context context) {
            this.context = context;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ChangeReportBuilder<NamespaceType, NameType, InstanceType, PayloadType> event(Event<NamespaceType, NameType, InstanceType, PayloadType> event) {
            this.event = event;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ChangeReport.ChangeReportBuilder(event=");
            outline107.append(this.event);
            outline107.append(", context=");
            outline107.append(this.context);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public ChangeReport(Event<NamespaceType, NameType, InstanceType, PayloadType> event, Context context) {
        this.event = event;
        this.context = context;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <NamespaceType, NameType, InstanceType, PayloadType> ChangeReportBuilder<NamespaceType, NameType, InstanceType, PayloadType> builder() {
        return new ChangeReportBuilder<>();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected boolean canEqual(Object obj) {
        return obj instanceof ChangeReport;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ChangeReport)) {
            return false;
        }
        ChangeReport changeReport = (ChangeReport) obj;
        if (!changeReport.canEqual(this)) {
            return false;
        }
        Event<NamespaceType, NameType, InstanceType, PayloadType> event = getEvent();
        Event<NamespaceType, NameType, InstanceType, PayloadType> event2 = changeReport.getEvent();
        if (event != null ? !event.equals(event2) : event2 != null) {
            return false;
        }
        Context context = getContext();
        Context context2 = changeReport.getContext();
        return context != null ? context.equals(context2) : context2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Context getContext() {
        return this.context;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Event<NamespaceType, NameType, InstanceType, PayloadType> getEvent() {
        return this.event;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        Event<NamespaceType, NameType, InstanceType, PayloadType> event = getEvent();
        int i = 43;
        int hashCode = event == null ? 43 : event.hashCode();
        Context context = getContext();
        int i2 = (hashCode + 59) * 59;
        if (context != null) {
            i = context.hashCode();
        }
        return i2 + i;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ChangeReport(event=");
        outline107.append(getEvent());
        outline107.append(", context=");
        outline107.append(getContext());
        outline107.append(")");
        return outline107.toString();
    }
}
