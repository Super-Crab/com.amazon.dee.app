package com.amazon.alexa.statereporting.lib.model.api;

import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Date;
import lombok.Generated;
/* loaded from: classes10.dex */
public class Property<PropertyKey, PropertyValue, PropertyInstance> {
    @CheckForNull
    private final PropertyInstance instance;
    @CheckForNull
    private final PropertyKey name;
    @CheckForNull
    private final String namespace;
    @CheckForNull
    private final Date timeOfSample;
    @CheckForNull
    private final Long uncertaintyInMilliseconds;
    @CheckForNull
    private final PropertyValue value;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes10.dex */
    public static class PropertyBuilder<PropertyKey, PropertyValue, PropertyInstance> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private PropertyInstance instance;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private PropertyKey name;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private String namespace;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Date timeOfSample;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Long uncertaintyInMilliseconds;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private PropertyValue value;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        PropertyBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Property<PropertyKey, PropertyValue, PropertyInstance> build() {
            return new Property<>(this.namespace, this.name, this.value, this.instance, this.timeOfSample, this.uncertaintyInMilliseconds);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertyBuilder<PropertyKey, PropertyValue, PropertyInstance> instance(PropertyInstance propertyinstance) {
            this.instance = propertyinstance;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertyBuilder<PropertyKey, PropertyValue, PropertyInstance> name(PropertyKey propertykey) {
            this.name = propertykey;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertyBuilder<PropertyKey, PropertyValue, PropertyInstance> namespace(String str) {
            this.namespace = str;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertyBuilder<PropertyKey, PropertyValue, PropertyInstance> timeOfSample(Date date) {
            this.timeOfSample = date;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Property.PropertyBuilder(namespace=");
            outline107.append(this.namespace);
            outline107.append(", name=");
            outline107.append(this.name);
            outline107.append(", value=");
            outline107.append(this.value);
            outline107.append(", instance=");
            outline107.append(this.instance);
            outline107.append(", timeOfSample=");
            outline107.append(this.timeOfSample);
            outline107.append(", uncertaintyInMilliseconds=");
            outline107.append(this.uncertaintyInMilliseconds);
            outline107.append(")");
            return outline107.toString();
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertyBuilder<PropertyKey, PropertyValue, PropertyInstance> uncertaintyInMilliseconds(Long l) {
            this.uncertaintyInMilliseconds = l;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertyBuilder<PropertyKey, PropertyValue, PropertyInstance> value(PropertyValue propertyvalue) {
            this.value = propertyvalue;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Property(String str, PropertyKey propertykey, PropertyValue propertyvalue, PropertyInstance propertyinstance, Date date, Long l) {
        this.namespace = str;
        this.name = propertykey;
        this.value = propertyvalue;
        this.instance = propertyinstance;
        this.timeOfSample = date;
        this.uncertaintyInMilliseconds = l;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <PropertyKey, PropertyValue, PropertyInstance> PropertyBuilder<PropertyKey, PropertyValue, PropertyInstance> builder() {
        return new PropertyBuilder<>();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected boolean canEqual(Object obj) {
        return obj instanceof Property;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Property)) {
            return false;
        }
        Property property = (Property) obj;
        if (!property.canEqual(this)) {
            return false;
        }
        String namespace = getNamespace();
        String namespace2 = property.getNamespace();
        if (namespace != null ? !namespace.equals(namespace2) : namespace2 != null) {
            return false;
        }
        PropertyKey name = getName();
        Object name2 = property.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        PropertyValue value = getValue();
        Object value2 = property.getValue();
        if (value != null ? !value.equals(value2) : value2 != null) {
            return false;
        }
        PropertyInstance property2 = getInstance();
        Object property3 = property.getInstance();
        if (property2 != null ? !property2.equals(property3) : property3 != null) {
            return false;
        }
        Date timeOfSample = getTimeOfSample();
        Date timeOfSample2 = property.getTimeOfSample();
        if (timeOfSample != null ? !timeOfSample.equals(timeOfSample2) : timeOfSample2 != null) {
            return false;
        }
        Long uncertaintyInMilliseconds = getUncertaintyInMilliseconds();
        Long uncertaintyInMilliseconds2 = property.getUncertaintyInMilliseconds();
        return uncertaintyInMilliseconds != null ? uncertaintyInMilliseconds.equals(uncertaintyInMilliseconds2) : uncertaintyInMilliseconds2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public PropertyInstance getInstance() {
        return this.instance;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public PropertyKey getName() {
        return this.name;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String getNamespace() {
        return this.namespace;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Date getTimeOfSample() {
        return this.timeOfSample;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Long getUncertaintyInMilliseconds() {
        return this.uncertaintyInMilliseconds;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public PropertyValue getValue() {
        return this.value;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        String namespace = getNamespace();
        int i = 43;
        int hashCode = namespace == null ? 43 : namespace.hashCode();
        PropertyKey name = getName();
        int hashCode2 = ((hashCode + 59) * 59) + (name == null ? 43 : name.hashCode());
        PropertyValue value = getValue();
        int hashCode3 = (hashCode2 * 59) + (value == null ? 43 : value.hashCode());
        PropertyInstance property = getInstance();
        int hashCode4 = (hashCode3 * 59) + (property == null ? 43 : property.hashCode());
        Date timeOfSample = getTimeOfSample();
        int hashCode5 = (hashCode4 * 59) + (timeOfSample == null ? 43 : timeOfSample.hashCode());
        Long uncertaintyInMilliseconds = getUncertaintyInMilliseconds();
        int i2 = hashCode5 * 59;
        if (uncertaintyInMilliseconds != null) {
            i = uncertaintyInMilliseconds.hashCode();
        }
        return i2 + i;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Property(namespace=");
        outline107.append(getNamespace());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(", value=");
        outline107.append(getValue());
        outline107.append(", instance=");
        outline107.append(getInstance());
        outline107.append(", timeOfSample=");
        outline107.append(getTimeOfSample());
        outline107.append(", uncertaintyInMilliseconds=");
        outline107.append(getUncertaintyInMilliseconds());
        outline107.append(")");
        return outline107.toString();
    }
}
