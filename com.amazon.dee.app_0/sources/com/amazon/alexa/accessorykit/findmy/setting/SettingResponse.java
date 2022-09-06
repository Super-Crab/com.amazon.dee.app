package com.amazon.alexa.accessorykit.findmy.setting;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.auto.value.AutoValue;
import org.json.JSONException;
import org.json.JSONObject;
@AutoValue
/* loaded from: classes6.dex */
public abstract class SettingResponse {
    public static final Factory FACTORY = new Factory();

    /* loaded from: classes6.dex */
    public enum Enablement {
        ENABLED("\"ENABLED\""),
        DISABLED("\"DISABLED\"");
        
        private String value;

        Enablement(String str) {
            this.value = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Enablement fromString(String str) {
            Enablement[] values;
            Preconditions.notEmpty(str, "value");
            for (Enablement enablement : values()) {
                if (enablement.value.equalsIgnoreCase(str)) {
                    return enablement;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid Enablement value. No Enablement value found for:", str));
        }
    }

    /* loaded from: classes6.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<SettingResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public SettingResponse mo1239create(JSONObject jSONObject) throws JSONException {
            return SettingResponse.create(Enablement.fromString(jSONObject.getString("value")));
        }
    }

    public static SettingResponse create(Enablement enablement) {
        return new AutoValue_SettingResponse(enablement);
    }

    public abstract Enablement getSettingValue();
}
