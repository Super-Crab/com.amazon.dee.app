package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class SoftwareComponent implements JsonObjectSerializable {
    private static final String JSON_SOFTWARE_COMPONENT_ID = "softwareComponentId";
    private static final String JSON_SOFTWARE_COMPONENT_TYPE = "softwareComponentType";
    private final String softwareComponentId;
    private final String softwareComponentType;

    /* loaded from: classes.dex */
    public static final class Builder {
        String softwareComponentId;
        String softwareComponentType;

        public SoftwareComponent build() {
            Preconditions.notNull(this.softwareComponentType, SoftwareComponent.JSON_SOFTWARE_COMPONENT_TYPE);
            Preconditions.notNull(this.softwareComponentId, SoftwareComponent.JSON_SOFTWARE_COMPONENT_ID);
            return new SoftwareComponent(this);
        }

        public Builder softwareComponentId(String str) {
            this.softwareComponentId = str;
            return this;
        }

        public Builder softwareComponentType(String str) {
            this.softwareComponentType = str;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class JsonBuilder implements JsonObjectSerializable.Factory<SoftwareComponent> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public SoftwareComponent mo1239create(JSONObject jSONObject) throws JSONException {
            return new Builder().softwareComponentId(jSONObject.getString(SoftwareComponent.JSON_SOFTWARE_COMPONENT_ID)).softwareComponentType(jSONObject.getString(SoftwareComponent.JSON_SOFTWARE_COMPONENT_TYPE)).build();
        }
    }

    /* loaded from: classes.dex */
    public static final class JsonFactory implements JsonObjectSerializable.Factory<SoftwareComponent> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public SoftwareComponent mo1239create(JSONObject jSONObject) throws JSONException {
            return new Builder().softwareComponentId(jSONObject.getString(SoftwareComponent.JSON_SOFTWARE_COMPONENT_ID)).softwareComponentType(jSONObject.getString(SoftwareComponent.JSON_SOFTWARE_COMPONENT_TYPE)).build();
        }
    }

    SoftwareComponent(Builder builder) {
        this.softwareComponentId = builder.softwareComponentId;
        this.softwareComponentType = builder.softwareComponentType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SoftwareComponent.class != obj.getClass()) {
            return false;
        }
        SoftwareComponent softwareComponent = (SoftwareComponent) obj;
        return Objects.equals(this.softwareComponentId, softwareComponent.softwareComponentId) && Objects.equals(this.softwareComponentType, softwareComponent.softwareComponentType);
    }

    public String getSoftwareComponentId() {
        return this.softwareComponentId;
    }

    public String getSoftwareComponentType() {
        return this.softwareComponentType;
    }

    public int hashCode() {
        return Objects.hash(this.softwareComponentId, this.softwareComponentType);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(JSON_SOFTWARE_COMPONENT_ID, this.softwareComponentId).put(JSON_SOFTWARE_COMPONENT_TYPE, this.softwareComponentType);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SoftwareComponent{softwareComponentId='");
        GeneratedOutlineSupport1.outline176(outline107, this.softwareComponentId, Chars.QUOTE, ", softwareComponentType='");
        return GeneratedOutlineSupport1.outline90(outline107, this.softwareComponentType, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
