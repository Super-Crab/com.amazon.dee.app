package com.amazon.alexa.statereporting.lib.model.api.discovery;

import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import lombok.Generated;
/* loaded from: classes10.dex */
public class Properties<PropertyKey> {
    @CheckForNull
    private final Boolean proactivelyReported;
    @CheckForNull
    private final Boolean retrievable;
    @CheckForNull
    private final List<SupportedProperty<PropertyKey>> supported;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes10.dex */
    public static class PropertiesBuilder<PropertyKey> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Boolean proactivelyReported;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Boolean retrievable;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private List<SupportedProperty<PropertyKey>> supported;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        PropertiesBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Properties<PropertyKey> build() {
            return new Properties<>(this.supported, this.proactivelyReported, this.retrievable);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertiesBuilder<PropertyKey> proactivelyReported(Boolean bool) {
            this.proactivelyReported = bool;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertiesBuilder<PropertyKey> retrievable(Boolean bool) {
            this.retrievable = bool;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertiesBuilder<PropertyKey> supported(List<SupportedProperty<PropertyKey>> list) {
            this.supported = list;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Properties.PropertiesBuilder(supported=");
            outline107.append(this.supported);
            outline107.append(", proactivelyReported=");
            outline107.append(this.proactivelyReported);
            outline107.append(", retrievable=");
            outline107.append(this.retrievable);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Properties(List<SupportedProperty<PropertyKey>> list, Boolean bool, Boolean bool2) {
        this.supported = list;
        this.proactivelyReported = bool;
        this.retrievable = bool2;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <PropertyKey> PropertiesBuilder<PropertyKey> builder() {
        return new PropertiesBuilder<>();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected boolean canEqual(Object obj) {
        return obj instanceof Properties;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Properties)) {
            return false;
        }
        Properties properties = (Properties) obj;
        if (!properties.canEqual(this)) {
            return false;
        }
        List<SupportedProperty<PropertyKey>> supported = getSupported();
        List<SupportedProperty<PropertyKey>> supported2 = properties.getSupported();
        if (supported != null ? !supported.equals(supported2) : supported2 != null) {
            return false;
        }
        Boolean proactivelyReported = getProactivelyReported();
        Boolean proactivelyReported2 = properties.getProactivelyReported();
        if (proactivelyReported != null ? !proactivelyReported.equals(proactivelyReported2) : proactivelyReported2 != null) {
            return false;
        }
        Boolean retrievable = getRetrievable();
        Boolean retrievable2 = properties.getRetrievable();
        return retrievable != null ? retrievable.equals(retrievable2) : retrievable2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Boolean getProactivelyReported() {
        return this.proactivelyReported;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Boolean getRetrievable() {
        return this.retrievable;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public List<SupportedProperty<PropertyKey>> getSupported() {
        return this.supported;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        List<SupportedProperty<PropertyKey>> supported = getSupported();
        int i = 43;
        int hashCode = supported == null ? 43 : supported.hashCode();
        Boolean proactivelyReported = getProactivelyReported();
        int hashCode2 = ((hashCode + 59) * 59) + (proactivelyReported == null ? 43 : proactivelyReported.hashCode());
        Boolean retrievable = getRetrievable();
        int i2 = hashCode2 * 59;
        if (retrievable != null) {
            i = retrievable.hashCode();
        }
        return i2 + i;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Properties(supported=");
        outline107.append(getSupported());
        outline107.append(", proactivelyReported=");
        outline107.append(getProactivelyReported());
        outline107.append(", retrievable=");
        outline107.append(getRetrievable());
        outline107.append(")");
        return outline107.toString();
    }
}
