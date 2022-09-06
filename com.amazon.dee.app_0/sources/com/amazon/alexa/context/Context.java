package com.amazon.alexa.context;

import com.amazon.alexa.property.Property;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import lombok.Generated;
/* loaded from: classes6.dex */
public final class Context {
    @CheckForNull
    private final List<Property> properties;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes6.dex */
    public static class ContextBuilder {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private List<Property> properties;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        ContextBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Context build() {
            return new Context(this.properties);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ContextBuilder properties(List<Property> list) {
            this.properties = list;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("Context.ContextBuilder(properties="), this.properties, ")");
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected Context(List<Property> list) {
        this.properties = list;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static ContextBuilder builder() {
        return new ContextBuilder();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Context)) {
            return false;
        }
        List<Property> properties = getProperties();
        List<Property> properties2 = ((Context) obj).getProperties();
        return properties != null ? properties.equals(properties2) : properties2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public List<Property> getProperties() {
        return this.properties;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        List<Property> properties = getProperties();
        return 59 + (properties == null ? 43 : properties.hashCode());
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Context(properties=");
        outline107.append(getProperties());
        outline107.append(")");
        return outline107.toString();
    }
}
