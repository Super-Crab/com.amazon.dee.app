package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.JsonUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class CompanionInventoryRequest implements JsonObjectSerializable {
    private final Map<String, Object> buildDimensions;
    private final CompanionDeviceId companionDeviceId;
    private final List<Inventory> inventory;
    private final Set<String> softwareComponentTypes;

    /* loaded from: classes.dex */
    public static final class Builder {
        Map<String, Object> buildDimensions;
        CompanionDeviceId companionDeviceId;
        List<Inventory> inventory;
        Set<String> softwareComponentTypes;

        public CompanionInventoryRequest build() {
            Preconditions.notNull(this.companionDeviceId, "companionDeviceId");
            if (this.buildDimensions == null) {
                this.buildDimensions = Collections.emptyMap();
            }
            if (this.softwareComponentTypes == null) {
                this.softwareComponentTypes = Collections.emptySet();
            }
            if (this.inventory == null) {
                this.inventory = Collections.emptyList();
            }
            return new CompanionInventoryRequest(this);
        }

        public Builder buildDimension(String str, Object obj) {
            if (this.buildDimensions == null) {
                this.buildDimensions = new HashMap();
            }
            this.buildDimensions.put(str, obj);
            return this;
        }

        public Builder buildDimensions(Map<String, Object> map) {
            this.buildDimensions = map;
            return this;
        }

        public Builder companionDeviceId(CompanionDeviceId companionDeviceId) {
            this.companionDeviceId = companionDeviceId;
            return this;
        }

        public Builder inventory(List<Inventory> list) {
            this.inventory = list;
            return this;
        }

        public Builder softwareComponentType(String str) {
            if (this.softwareComponentTypes == null) {
                this.softwareComponentTypes = new HashSet();
            }
            this.softwareComponentTypes.add(str);
            return this;
        }

        public Builder softwareComponentTypes(Set<String> set) {
            this.softwareComponentTypes = set;
            return this;
        }

        public Builder inventory(Inventory... inventoryArr) {
            this.inventory = Arrays.asList(inventoryArr);
            return this;
        }
    }

    CompanionInventoryRequest(Builder builder) {
        this.buildDimensions = builder.buildDimensions;
        this.softwareComponentTypes = builder.softwareComponentTypes;
        this.companionDeviceId = builder.companionDeviceId;
        this.inventory = builder.inventory;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CompanionInventoryRequest.class != obj.getClass()) {
            return false;
        }
        CompanionInventoryRequest companionInventoryRequest = (CompanionInventoryRequest) obj;
        if (!this.buildDimensions.equals(companionInventoryRequest.buildDimensions) || !this.softwareComponentTypes.equals(companionInventoryRequest.softwareComponentTypes) || !this.companionDeviceId.equals(companionInventoryRequest.companionDeviceId)) {
            return false;
        }
        return this.inventory.equals(companionInventoryRequest.inventory);
    }

    public Map<String, Object> getBuildDimensions() {
        return this.buildDimensions;
    }

    public CompanionDeviceId getCompanionDeviceId() {
        return this.companionDeviceId;
    }

    public List<Inventory> getInventory() {
        return this.inventory;
    }

    public Set<String> getSoftwareComponentTypes() {
        return this.softwareComponentTypes;
    }

    public int hashCode() {
        int hashCode = this.softwareComponentTypes.hashCode();
        int hashCode2 = this.companionDeviceId.hashCode();
        return this.inventory.hashCode() + ((hashCode2 + ((hashCode + (this.buildDimensions.hashCode() * 31)) * 31)) * 31);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("buildDimensions", new JSONObject((Map) this.buildDimensions)).put("softwareComponentTypes", new JSONArray((Collection) this.softwareComponentTypes)).put("companionDeviceId", this.companionDeviceId.toJsonObject()).put("inventory", JsonUtils.toJsonArray(this.inventory));
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InventoryRequest{buildDimensions=");
        outline107.append(this.buildDimensions);
        outline107.append(", softwareComponentTypes=");
        outline107.append(this.softwareComponentTypes);
        outline107.append(", companionDeviceId=");
        outline107.append(this.companionDeviceId);
        outline107.append(", inventory=");
        return GeneratedOutlineSupport1.outline94(outline107, this.inventory, JsonReaderKt.END_OBJ);
    }
}
