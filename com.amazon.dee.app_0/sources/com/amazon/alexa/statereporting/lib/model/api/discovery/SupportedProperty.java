package com.amazon.alexa.statereporting.lib.model.api.discovery;

import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes10.dex */
public final class SupportedProperty<PropertyKey> {
    @CheckForNull
    private final PropertyKey name;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes10.dex */
    public static class SupportedPropertyBuilder<PropertyKey> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private PropertyKey name;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        SupportedPropertyBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public SupportedProperty<PropertyKey> build() {
            return new SupportedProperty<>(this.name);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public SupportedPropertyBuilder<PropertyKey> name(PropertyKey propertykey) {
            this.name = propertykey;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            return GeneratedOutlineSupport1.outline88(GeneratedOutlineSupport1.outline107("SupportedProperty.SupportedPropertyBuilder(name="), this.name, ")");
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    SupportedProperty(PropertyKey propertykey) {
        this.name = propertykey;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <PropertyKey> SupportedPropertyBuilder<PropertyKey> builder() {
        return new SupportedPropertyBuilder<>();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SupportedProperty)) {
            return false;
        }
        PropertyKey name = getName();
        Object name2 = ((SupportedProperty) obj).getName();
        return name != null ? name.equals(name2) : name2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public PropertyKey getName() {
        return this.name;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        PropertyKey name = getName();
        return 59 + (name == null ? 43 : name.hashCode());
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SupportedProperty(name=");
        outline107.append(getName());
        outline107.append(")");
        return outline107.toString();
    }
}
