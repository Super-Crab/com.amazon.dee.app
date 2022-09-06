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
public final class CallAfterDevice implements JsonObjectSerializable {
    private static final String CALL_AFTER = "callAfter";
    private static final String DSN = "dsn";
    public static final Factory FACTORY = new Factory();
    private static final String FIRMWARE_VERSION = "firmwareVersion";
    private static final String INVENTORY_RESPONSE = "inventoryResponse";
    public final long callAfter;
    public final String dsn;
    public final int firmwareVersion;
    public final CompanionInventoryResponse inventoryResponse;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<CallAfterDevice> {
        private Factory() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public CallAfterDevice mo1239create(JSONObject jSONObject) throws JSONException {
            return new CallAfterDevice(jSONObject.getString("dsn"), jSONObject.getInt("firmwareVersion"), jSONObject.getLong(CallAfterDevice.CALL_AFTER), CompanionInventoryResponse.FACTORY.mo1239create(jSONObject.getJSONObject(CallAfterDevice.INVENTORY_RESPONSE)));
        }
    }

    public CallAfterDevice(String str, int i, long j, CompanionInventoryResponse companionInventoryResponse) {
        Preconditions.notNull(str, "dsn");
        this.dsn = str;
        this.callAfter = j;
        this.firmwareVersion = i;
        this.inventoryResponse = companionInventoryResponse;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && CallAfterDevice.class == obj.getClass()) {
            return Objects.equals(this.dsn, ((CallAfterDevice) obj).dsn);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.dsn);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("dsn", this.dsn).put("firmwareVersion", this.firmwareVersion).put(CALL_AFTER, this.callAfter).put(INVENTORY_RESPONSE, this.inventoryResponse.toJsonObject());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CallAfterDevice{dsn='");
        GeneratedOutlineSupport1.outline176(outline107, this.dsn, Chars.QUOTE, ", firmwareVersion=");
        outline107.append(this.firmwareVersion);
        outline107.append(", callAfter=");
        outline107.append(this.callAfter);
        outline107.append(", inventoryResponse=");
        outline107.append(this.inventoryResponse);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
