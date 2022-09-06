package com.amazon.alexa.property;

import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes9.dex */
public class Property<NamespaceType, NameType, InstanceType, ValueType> {
    @CheckForNull
    private final InstanceType instance;
    @CheckForNull
    private final NameType name;
    @CheckForNull
    private final NamespaceType namespace;
    @CheckForNull
    private final String timeOfSample;
    @CheckForNull
    private final int uncertaintyInMilliseconds;
    @CheckForNull
    private final ValueType value;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes9.dex */
    public static class PropertyBuilder<NamespaceType, NameType, InstanceType, ValueType> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private InstanceType instance;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private NameType name;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private NamespaceType namespace;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private String timeOfSample;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private int uncertaintyInMilliseconds;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private ValueType value;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        PropertyBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Property<NamespaceType, NameType, InstanceType, ValueType> build() {
            return new Property<>(this.namespace, this.instance, this.name, this.value, this.timeOfSample, this.uncertaintyInMilliseconds);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertyBuilder<NamespaceType, NameType, InstanceType, ValueType> instance(InstanceType instancetype) {
            this.instance = instancetype;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertyBuilder<NamespaceType, NameType, InstanceType, ValueType> name(NameType nametype) {
            this.name = nametype;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertyBuilder<NamespaceType, NameType, InstanceType, ValueType> namespace(NamespaceType namespacetype) {
            this.namespace = namespacetype;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertyBuilder<NamespaceType, NameType, InstanceType, ValueType> timeOfSample(String str) {
            this.timeOfSample = str;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Property.PropertyBuilder(namespace=");
            outline107.append(this.namespace);
            outline107.append(", instance=");
            outline107.append(this.instance);
            outline107.append(", name=");
            outline107.append(this.name);
            outline107.append(", value=");
            outline107.append(this.value);
            outline107.append(", timeOfSample=");
            outline107.append(this.timeOfSample);
            outline107.append(", uncertaintyInMilliseconds=");
            return GeneratedOutlineSupport1.outline86(outline107, this.uncertaintyInMilliseconds, ")");
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertyBuilder<NamespaceType, NameType, InstanceType, ValueType> uncertaintyInMilliseconds(int i) {
            this.uncertaintyInMilliseconds = i;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PropertyBuilder<NamespaceType, NameType, InstanceType, ValueType> value(ValueType valuetype) {
            this.value = valuetype;
            return this;
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected Property(NamespaceType namespacetype, InstanceType instancetype, NameType nametype, ValueType valuetype, String str, int i) {
        this.namespace = namespacetype;
        this.instance = instancetype;
        this.name = nametype;
        this.value = valuetype;
        this.timeOfSample = str;
        this.uncertaintyInMilliseconds = i;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <NamespaceType, NameType, InstanceType, ValueType> PropertyBuilder<NamespaceType, NameType, InstanceType, ValueType> builder() {
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
        NamespaceType namespace = getNamespace();
        Object namespace2 = property.getNamespace();
        if (namespace != null ? !namespace.equals(namespace2) : namespace2 != null) {
            return false;
        }
        InstanceType property2 = getInstance();
        Object property3 = property.getInstance();
        if (property2 != null ? !property2.equals(property3) : property3 != null) {
            return false;
        }
        NameType name = getName();
        Object name2 = property.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        ValueType value = getValue();
        Object value2 = property.getValue();
        if (value != null ? !value.equals(value2) : value2 != null) {
            return false;
        }
        String timeOfSample = getTimeOfSample();
        String timeOfSample2 = property.getTimeOfSample();
        if (timeOfSample != null ? !timeOfSample.equals(timeOfSample2) : timeOfSample2 != null) {
            return false;
        }
        return getUncertaintyInMilliseconds() == property.getUncertaintyInMilliseconds();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public InstanceType getInstance() {
        return this.instance;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public NameType getName() {
        return this.name;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public NamespaceType getNamespace() {
        return this.namespace;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String getTimeOfSample() {
        return this.timeOfSample;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int getUncertaintyInMilliseconds() {
        return this.uncertaintyInMilliseconds;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public ValueType getValue() {
        return this.value;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        NamespaceType namespace = getNamespace();
        int i = 43;
        int hashCode = namespace == null ? 43 : namespace.hashCode();
        InstanceType property = getInstance();
        int hashCode2 = ((hashCode + 59) * 59) + (property == null ? 43 : property.hashCode());
        NameType name = getName();
        int hashCode3 = (hashCode2 * 59) + (name == null ? 43 : name.hashCode());
        ValueType value = getValue();
        int hashCode4 = (hashCode3 * 59) + (value == null ? 43 : value.hashCode());
        String timeOfSample = getTimeOfSample();
        int i2 = hashCode4 * 59;
        if (timeOfSample != null) {
            i = timeOfSample.hashCode();
        }
        return getUncertaintyInMilliseconds() + ((i2 + i) * 59);
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Property(namespace=");
        outline107.append(getNamespace());
        outline107.append(", instance=");
        outline107.append(getInstance());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(", value=");
        outline107.append(getValue());
        outline107.append(", timeOfSample=");
        outline107.append(getTimeOfSample());
        outline107.append(", uncertaintyInMilliseconds=");
        outline107.append(getUncertaintyInMilliseconds());
        outline107.append(")");
        return outline107.toString();
    }
}
