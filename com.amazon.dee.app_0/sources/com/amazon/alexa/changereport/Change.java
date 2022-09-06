package com.amazon.alexa.changereport;

import com.amazon.alexa.property.Property;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import lombok.Generated;
/* loaded from: classes6.dex */
public final class Change<NamespaceType, NameType, InstanceType, ValueType> {
    @CheckForNull
    private final Cause cause;
    @CheckForNull
    private final List<Property<NamespaceType, NameType, InstanceType, ValueType>> properties;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes6.dex */
    public static class ChangeBuilder<NamespaceType, NameType, InstanceType, ValueType> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Cause cause;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private List<Property<NamespaceType, NameType, InstanceType, ValueType>> properties;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        ChangeBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Change<NamespaceType, NameType, InstanceType, ValueType> build() {
            return new Change<>(this.cause, this.properties);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ChangeBuilder<NamespaceType, NameType, InstanceType, ValueType> cause(Cause cause) {
            this.cause = cause;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ChangeBuilder<NamespaceType, NameType, InstanceType, ValueType> properties(List<Property<NamespaceType, NameType, InstanceType, ValueType>> list) {
            this.properties = list;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Change.ChangeBuilder(cause=");
            outline107.append(this.cause);
            outline107.append(", properties=");
            return GeneratedOutlineSupport1.outline95(outline107, this.properties, ")");
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected Change(Cause cause, List<Property<NamespaceType, NameType, InstanceType, ValueType>> list) {
        this.cause = cause;
        this.properties = list;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <NamespaceType, NameType, InstanceType, ValueType> ChangeBuilder<NamespaceType, NameType, InstanceType, ValueType> builder() {
        return new ChangeBuilder<>();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Change)) {
            return false;
        }
        Change change = (Change) obj;
        Cause cause = getCause();
        Cause cause2 = change.getCause();
        if (cause != null ? !cause.equals(cause2) : cause2 != null) {
            return false;
        }
        List<Property<NamespaceType, NameType, InstanceType, ValueType>> properties = getProperties();
        List<Property<NamespaceType, NameType, InstanceType, ValueType>> properties2 = change.getProperties();
        return properties != null ? properties.equals(properties2) : properties2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Cause getCause() {
        return this.cause;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public List<Property<NamespaceType, NameType, InstanceType, ValueType>> getProperties() {
        return this.properties;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        Cause cause = getCause();
        int i = 43;
        int hashCode = cause == null ? 43 : cause.hashCode();
        List<Property<NamespaceType, NameType, InstanceType, ValueType>> properties = getProperties();
        int i2 = (hashCode + 59) * 59;
        if (properties != null) {
            i = properties.hashCode();
        }
        return i2 + i;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Change(cause=");
        outline107.append(getCause());
        outline107.append(", properties=");
        outline107.append(getProperties());
        outline107.append(")");
        return outline107.toString();
    }
}
