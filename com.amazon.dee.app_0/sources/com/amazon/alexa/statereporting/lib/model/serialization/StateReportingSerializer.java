package com.amazon.alexa.statereporting.lib.model.serialization;

import com.amazon.alexa.statereporting.lib.model.api.Property;
import com.amazon.alexa.statereporting.lib.model.api.context.StateReportContext;
import com.amazon.alexa.statereporting.lib.model.api.discovery.Capability;
import com.amazon.alexa.statereporting.lib.model.api.discovery.Properties;
import com.amazon.alexa.statereporting.lib.model.api.event.ChangeReportEventPayload;
import com.amazon.alexa.statereporting.lib.model.serialization.adapter.CapabilityAdapter;
import com.amazon.alexa.statereporting.lib.model.serialization.adapter.ChangeReportEventPayloadAdapter;
import com.amazon.alexa.statereporting.lib.model.serialization.adapter.PropertiesAdapter;
import com.amazon.alexa.statereporting.lib.model.serialization.adapter.PropertyAdapter;
import com.amazon.alexa.statereporting.lib.model.serialization.adapter.StateReportContextAdapter;
import com.amazon.alexa.statereporting.lib.model.serialization.type.SimpleCapability;
import com.amazon.alexa.statereporting.lib.model.serialization.type.SimpleChangeReportEventPayload;
import com.amazon.alexa.statereporting.lib.model.serialization.type.SimpleProperties;
import com.amazon.alexa.statereporting.lib.model.serialization.type.SimpleProperty;
import com.amazon.alexa.statereporting.lib.model.serialization.type.SimpleStateReportContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.UtcDateTypeAdapter;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Date;
import lombok.Generated;
/* loaded from: classes10.dex */
public final class StateReportingSerializer {
    public static final StateReportingSerializer INSTANCE = new Builder(String.class).build();
    private final Gson gson;

    /* loaded from: classes10.dex */
    public static final class Builder<PropertyKey> {
        private final Class<? extends PropertyKey> propertyKeyClass;
        private Class<? extends Property> propertyClass = SimpleProperty.class;
        private PropertyAdapter.PropertyToPropertyClass<PropertyKey> propertyClassSupplier = $$Lambda$StateReportingSerializer$Builder$I7Vi2uiksMphunvWUIwgEMp48uc.INSTANCE;
        private Class<? extends Properties> propertiesClass = SimpleProperties.class;
        private Class<? extends Capability> capabilityClass = SimpleCapability.class;
        private Class<? extends StateReportContext> stateReportContextClass = SimpleStateReportContext.class;
        private Class<? extends ChangeReportEventPayload> payloadClass = SimpleChangeReportEventPayload.class;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Builder(Class<? extends PropertyKey> cls) {
            this.propertyKeyClass = cls;
        }

        static /* synthetic */ Class lambda$new$0(Object obj) {
            return SimpleProperty.class;
        }

        public StateReportingSerializer build() {
            return new StateReportingSerializer(new GsonBuilder().enableComplexMapKeySerialization().registerTypeAdapter(Date.class, new UtcDateTypeAdapter()).registerTypeAdapter(Property.class, new PropertyAdapter(this.propertyKeyClass, this.propertyClass, this.propertyClassSupplier)).registerTypeAdapter(Properties.class, new PropertiesAdapter(this.propertiesClass)).registerTypeAdapter(Capability.class, new CapabilityAdapter(this.capabilityClass)).registerTypeAdapter(StateReportContext.class, new StateReportContextAdapter(this.stateReportContextClass)).registerTypeAdapter(ChangeReportEventPayload.class, new ChangeReportEventPayloadAdapter(this.payloadClass)).create());
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Builder<PropertyKey> capabilityClass(Class<? extends Capability> cls) {
            this.capabilityClass = cls;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Builder<PropertyKey> payloadClass(Class<? extends ChangeReportEventPayload> cls) {
            this.payloadClass = cls;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Builder<PropertyKey> propertiesClass(Class<? extends Properties> cls) {
            this.propertiesClass = cls;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Builder<PropertyKey> propertyClass(Class<? extends Property> cls) {
            this.propertyClass = cls;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Builder<PropertyKey> propertyClassSupplier(PropertyAdapter.PropertyToPropertyClass<PropertyKey> propertyToPropertyClass) {
            this.propertyClassSupplier = propertyToPropertyClass;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Builder<PropertyKey> stateReportContextClass(Class<? extends StateReportContext> cls) {
            this.stateReportContextClass = cls;
            return this;
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private StateReportingSerializer(Gson gson) {
        this.gson = gson;
    }

    @CheckForNull
    public <T> T fromJson(@Nullable String str, @NonNull Class<T> cls) {
        return (T) this.gson.fromJson(str, (Class<Object>) cls);
    }

    @CheckForNull
    public <T> String toJson(@Nullable T t) {
        if (t == null) {
            return null;
        }
        return this.gson.toJson(t);
    }
}
