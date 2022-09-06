package com.amazon.alexa.statereporting.lib.model.api.context;

import com.amazon.alexa.statereporting.lib.model.api.Property;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import lombok.Generated;
/* loaded from: classes10.dex */
public class StateReportContext<PropertyKey> {
    @CheckForNull
    private List<Property<PropertyKey, ?, ?>> properties;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes10.dex */
    public static class StateReportContextBuilder<PropertyKey> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private List<Property<PropertyKey, ?, ?>> properties;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        StateReportContextBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public StateReportContext<PropertyKey> build() {
            return new StateReportContext<>(this.properties);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public StateReportContextBuilder<PropertyKey> properties(List<Property<PropertyKey, ?, ?>> list) {
            this.properties = list;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("StateReportContext.StateReportContextBuilder(properties="), this.properties, ")");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public StateReportContext(List<Property<PropertyKey, ?, ?>> list) {
        this.properties = list;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <PropertyKey> StateReportContextBuilder<PropertyKey> builder() {
        return new StateReportContextBuilder<>();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected boolean canEqual(Object obj) {
        return obj instanceof StateReportContext;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StateReportContext)) {
            return false;
        }
        StateReportContext stateReportContext = (StateReportContext) obj;
        if (!stateReportContext.canEqual(this)) {
            return false;
        }
        List<Property<PropertyKey, ?, ?>> properties = getProperties();
        List<Property<PropertyKey, ?, ?>> properties2 = stateReportContext.getProperties();
        return properties != null ? properties.equals(properties2) : properties2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public List<Property<PropertyKey, ?, ?>> getProperties() {
        return this.properties;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        List<Property<PropertyKey, ?, ?>> properties = getProperties();
        return 59 + (properties == null ? 43 : properties.hashCode());
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StateReportContext(properties=");
        outline107.append(getProperties());
        outline107.append(")");
        return outline107.toString();
    }
}
