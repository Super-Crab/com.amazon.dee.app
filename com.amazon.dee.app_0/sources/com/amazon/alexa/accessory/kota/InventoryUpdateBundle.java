package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.InventoryUpdate;
import com.amazon.alexa.accessory.kota.UpdateRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class InventoryUpdateBundle implements JsonObjectSerializable {
    private static final String JSON_INVENTORY_UPDATE = "inventory_update";
    private static final String JSON_UPDATE_REQUEST = "update_request";
    private final InventoryUpdate inventoryUpdate;
    private final UpdateRequest updateRequest;

    /* loaded from: classes.dex */
    public static final class Builder {
        InventoryUpdate inventoryUpdate;
        UpdateRequest updateRequest;

        public InventoryUpdateBundle build() {
            Preconditions.notNull(this.inventoryUpdate, KotaJobSchedulerService.INVENTORY_UPDATE_KEY);
            Preconditions.notNull(this.updateRequest, KotaJobSchedulerService.UPDATE_REQUEST_KEY);
            return new InventoryUpdateBundle(this);
        }

        public Builder inventoryUpdate(InventoryUpdate inventoryUpdate) {
            this.inventoryUpdate = inventoryUpdate;
            return this;
        }

        public Builder updateRequest(UpdateRequest updateRequest) {
            this.updateRequest = updateRequest;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class JsonFactory implements JsonObjectSerializable.Factory<InventoryUpdateBundle> {
        private final InventoryUpdate.JsonFactory inventoryUpdateFactory = new InventoryUpdate.JsonFactory();
        private final UpdateRequest.JsonBuilder updateRequestFactory = new UpdateRequest.JsonBuilder();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public InventoryUpdateBundle mo1239create(JSONObject jSONObject) throws JSONException {
            return new Builder().inventoryUpdate(this.inventoryUpdateFactory.mo1239create(jSONObject.getJSONObject(InventoryUpdateBundle.JSON_INVENTORY_UPDATE))).updateRequest(this.updateRequestFactory.mo1239create(jSONObject.getJSONObject(InventoryUpdateBundle.JSON_UPDATE_REQUEST))).build();
        }
    }

    public InventoryUpdateBundle(Builder builder) {
        this.inventoryUpdate = builder.inventoryUpdate;
        this.updateRequest = builder.updateRequest;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || InventoryUpdateBundle.class != obj.getClass()) {
            return false;
        }
        InventoryUpdateBundle inventoryUpdateBundle = (InventoryUpdateBundle) obj;
        if (this.inventoryUpdate.equals(inventoryUpdateBundle.inventoryUpdate)) {
            return this.updateRequest.equals(inventoryUpdateBundle.updateRequest);
        }
        return false;
    }

    public InventoryUpdate getInventoryUpdate() {
        return this.inventoryUpdate;
    }

    public UpdateRequest getUpdateRequest() {
        return this.updateRequest;
    }

    public int hashCode() {
        return this.updateRequest.hashCode() + (this.inventoryUpdate.hashCode() * 31);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(JSON_INVENTORY_UPDATE, this.inventoryUpdate.toJsonObject()).put(JSON_UPDATE_REQUEST, this.updateRequest.toJsonObject());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InventoryUpdateBundle{inventoryUpdate='");
        outline107.append(this.inventoryUpdate);
        outline107.append(Chars.QUOTE);
        outline107.append(", updateRequest='");
        outline107.append(this.updateRequest);
        outline107.append(Chars.QUOTE);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
