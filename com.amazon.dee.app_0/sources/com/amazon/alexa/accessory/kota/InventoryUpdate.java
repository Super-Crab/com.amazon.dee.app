package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.BinaryMetadata;
import com.amazon.alexa.accessory.kota.SoftwareComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class InventoryUpdate implements JsonObjectSerializable {
    private static final String JSON_BINARY_METADATA = "binaryMetadata";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_FORCE = "force";
    private static final String JSON_MIME_TYPE = "mimeType";
    private static final String JSON_SOFTWARE_COMPONENT = "softwareComponent";
    private static final String JSON_TARGET_SOFTWARE_COMPONENT_VERSION_CODE = "targetSoftwareComponentVersionCode";
    private static final String JSON_URL = "url";
    public static final InventoryUpdate NONE = new Builder().mimeType("").description("").binaryMetadata(new BinaryMetadata.Builder().binaryType("").md5Sum("").build()).softwareComponent(new SoftwareComponent.Builder().softwareComponentId("").softwareComponentType("").build()).url("").build();
    private final BinaryMetadata binaryMetadata;
    private final String description;
    private final boolean force;
    private final String mimeType;
    private final SoftwareComponent softwareComponent;
    private final int targetSoftwareComponentVersionCode;
    private final String url;

    /* loaded from: classes.dex */
    public static final class Builder {
        BinaryMetadata binaryMetadata;
        String description;
        boolean force;
        String mimeType;
        SoftwareComponent softwareComponent;
        int targetSoftwareComponentVersionCode;
        String url;

        public Builder binaryMetadata(BinaryMetadata binaryMetadata) {
            this.binaryMetadata = binaryMetadata;
            return this;
        }

        public InventoryUpdate build() {
            Preconditions.notNull(this.description, "description");
            Preconditions.notNull(this.softwareComponent, InventoryUpdate.JSON_SOFTWARE_COMPONENT);
            Preconditions.notNull(this.binaryMetadata, InventoryUpdate.JSON_BINARY_METADATA);
            Preconditions.notNull(this.mimeType, InventoryUpdate.JSON_MIME_TYPE);
            return new InventoryUpdate(this);
        }

        public Builder description(String str) {
            this.description = str;
            return this;
        }

        public Builder force(boolean z) {
            this.force = z;
            return this;
        }

        public Builder mimeType(String str) {
            this.mimeType = str;
            return this;
        }

        public Builder softwareComponent(SoftwareComponent softwareComponent) {
            this.softwareComponent = softwareComponent;
            return this;
        }

        public Builder targetSoftwareComponentVersionCode(int i) {
            this.targetSoftwareComponentVersionCode = i;
            return this;
        }

        public Builder url(String str) {
            this.url = str;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class JsonFactory implements JsonObjectSerializable.Factory<InventoryUpdate> {
        private final SoftwareComponent.JsonFactory softwareComponentFactory = new SoftwareComponent.JsonFactory();
        private final BinaryMetadata.JsonFactory binaryMetadataFactory = new BinaryMetadata.JsonFactory();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public InventoryUpdate mo1239create(JSONObject jSONObject) throws JSONException {
            return new Builder().binaryMetadata(this.binaryMetadataFactory.mo1239create(jSONObject.getJSONObject(InventoryUpdate.JSON_BINARY_METADATA))).description(jSONObject.getString("description")).force(jSONObject.getBoolean(InventoryUpdate.JSON_FORCE)).mimeType(jSONObject.getString(InventoryUpdate.JSON_MIME_TYPE)).softwareComponent(this.softwareComponentFactory.mo1239create(jSONObject.getJSONObject(InventoryUpdate.JSON_SOFTWARE_COMPONENT))).targetSoftwareComponentVersionCode(jSONObject.getInt(InventoryUpdate.JSON_TARGET_SOFTWARE_COMPONENT_VERSION_CODE)).url(jSONObject.getString("url")).build();
        }
    }

    InventoryUpdate(Builder builder) {
        this.description = builder.description;
        this.force = builder.force;
        this.mimeType = builder.mimeType;
        this.softwareComponent = builder.softwareComponent;
        this.binaryMetadata = builder.binaryMetadata;
        this.targetSoftwareComponentVersionCode = builder.targetSoftwareComponentVersionCode;
        this.url = builder.url;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || InventoryUpdate.class != obj.getClass()) {
            return false;
        }
        InventoryUpdate inventoryUpdate = (InventoryUpdate) obj;
        if (this.force != inventoryUpdate.force || this.targetSoftwareComponentVersionCode != inventoryUpdate.targetSoftwareComponentVersionCode || !this.description.equals(inventoryUpdate.description) || !this.mimeType.equals(inventoryUpdate.mimeType) || !this.softwareComponent.equals(inventoryUpdate.softwareComponent) || !this.binaryMetadata.equals(inventoryUpdate.binaryMetadata)) {
            return false;
        }
        return this.url.equals(inventoryUpdate.url);
    }

    public BinaryMetadata getBinaryMetadata() {
        return this.binaryMetadata;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getForce() {
        return this.force;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public SoftwareComponent getSoftwareComponent() {
        return this.softwareComponent;
    }

    public int getTargetSoftwareComponentVersionCode() {
        return this.targetSoftwareComponentVersionCode;
    }

    public String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int outline7 = GeneratedOutlineSupport1.outline7(this.mimeType, ((this.description.hashCode() * 31) + (this.force ? 1 : 0)) * 31, 31);
        int hashCode = this.binaryMetadata.hashCode();
        return this.url.hashCode() + ((((hashCode + ((this.softwareComponent.hashCode() + outline7) * 31)) * 31) + this.targetSoftwareComponentVersionCode) * 31);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("description", this.description).put(JSON_FORCE, this.force).put(JSON_MIME_TYPE, this.mimeType).put(JSON_SOFTWARE_COMPONENT, this.softwareComponent.toJsonObject()).put(JSON_BINARY_METADATA, this.binaryMetadata.toJsonObject()).put(JSON_TARGET_SOFTWARE_COMPONENT_VERSION_CODE, this.targetSoftwareComponentVersionCode).put("url", this.url);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InventoryUpdate{description='");
        GeneratedOutlineSupport1.outline176(outline107, this.description, Chars.QUOTE, ", force=");
        outline107.append(this.force);
        outline107.append(", mimeType='");
        GeneratedOutlineSupport1.outline176(outline107, this.mimeType, Chars.QUOTE, ", softwareComponent=");
        outline107.append(this.softwareComponent);
        outline107.append(", binaryMetadata=");
        outline107.append(this.binaryMetadata);
        outline107.append(", targetSoftwareComponentVersionCode=");
        outline107.append(this.targetSoftwareComponentVersionCode);
        outline107.append(", url='");
        return GeneratedOutlineSupport1.outline90(outline107, this.url, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
