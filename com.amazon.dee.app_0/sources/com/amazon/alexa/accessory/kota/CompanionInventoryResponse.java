package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.internal.util.JsonArraySerializable;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.JsonUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.InventoryUpdate;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class CompanionInventoryResponse implements JsonObjectSerializable {
    private static final String AVAILABLE_UPDATES_JSON = "availableUpdates";
    private static final String CALL_AFTER_JSON = "callAfter";
    public static final JsonFactory FACTORY = new JsonFactory();
    private static final String OTA_GROUP_ID_JSON = "otaGroupId";
    private final List<List<InventoryUpdate>> availableUpdates;
    private final long callAfter;
    private final String otaGroupId;

    /* loaded from: classes.dex */
    public static final class Builder {
        List<List<InventoryUpdate>> availableUpdates;
        long callAfter;
        String otaGroupId;

        public Builder availableUpdates(List<List<InventoryUpdate>> list) {
            this.availableUpdates = list;
            return this;
        }

        public CompanionInventoryResponse build() {
            Preconditions.notNull(this.otaGroupId, CompanionInventoryResponse.OTA_GROUP_ID_JSON);
            if (this.availableUpdates == null) {
                this.availableUpdates = Collections.emptyList();
            }
            return new CompanionInventoryResponse(this);
        }

        public Builder callAfter(long j) {
            this.callAfter = j;
            return this;
        }

        public Builder otaGroupId(String str) {
            this.otaGroupId = str;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class JsonFactory implements JsonObjectSerializable.Factory<CompanionInventoryResponse> {
        private final InventoryUpdate.JsonFactory inventoryUpdateFactory = new InventoryUpdate.JsonFactory();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public CompanionInventoryResponse mo1239create(JSONObject jSONObject) throws JSONException {
            return new Builder().otaGroupId(jSONObject.getString(CompanionInventoryResponse.OTA_GROUP_ID_JSON)).callAfter(Long.parseLong(jSONObject.getString(CompanionInventoryResponse.CALL_AFTER_JSON))).availableUpdates(JsonUtils.fromJsonArray(jSONObject.getJSONArray(CompanionInventoryResponse.AVAILABLE_UPDATES_JSON), new JsonArraySerializable.Factory<List<InventoryUpdate>>() { // from class: com.amazon.alexa.accessory.kota.CompanionInventoryResponse.JsonFactory.1
                @Override // com.amazon.alexa.accessory.internal.util.JsonArraySerializable.Factory
                public List<InventoryUpdate> create(JSONArray jSONArray) throws JSONException {
                    return JsonUtils.fromJsonArray(jSONArray, JsonFactory.this.inventoryUpdateFactory);
                }
            })).build();
        }
    }

    CompanionInventoryResponse(Builder builder) {
        this.otaGroupId = builder.otaGroupId;
        this.availableUpdates = builder.availableUpdates;
        this.callAfter = builder.callAfter;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CompanionInventoryResponse.class != obj.getClass()) {
            return false;
        }
        CompanionInventoryResponse companionInventoryResponse = (CompanionInventoryResponse) obj;
        return this.callAfter == companionInventoryResponse.callAfter && Objects.equals(this.otaGroupId, companionInventoryResponse.otaGroupId) && Objects.equals(this.availableUpdates, companionInventoryResponse.availableUpdates);
    }

    public List<List<InventoryUpdate>> getAvailableUpdates() {
        return this.availableUpdates;
    }

    public long getCallAfter() {
        return this.callAfter;
    }

    public String getOtaGroupId() {
        return this.otaGroupId;
    }

    public int hashCode() {
        return Objects.hash(this.otaGroupId, Long.valueOf(this.callAfter), this.availableUpdates);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(OTA_GROUP_ID_JSON, this.otaGroupId).put(CALL_AFTER_JSON, this.callAfter).put(AVAILABLE_UPDATES_JSON, JsonUtils.toNestedJsonArray(this.availableUpdates));
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CompanionInventoryResponse{otaGroupId='");
        GeneratedOutlineSupport1.outline176(outline107, this.otaGroupId, Chars.QUOTE, ", callAfter=");
        outline107.append(this.callAfter);
        outline107.append(", availableUpdates=");
        return GeneratedOutlineSupport1.outline94(outline107, this.availableUpdates, JsonReaderKt.END_OBJ);
    }
}
