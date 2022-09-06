package com.amazon.alexa.accessory.davs;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.JsonUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class DavsI18nWakeword implements JsonObjectSerializable {
    public static final Factory FACTORY = new Factory();
    private static final String SCOPES_KEY = "scopes";
    private static final String VALUES_KEY = "values";
    private final List<String> scopes;
    private final List<List<String>> values;

    /* loaded from: classes.dex */
    public static final class Builder {
        List<String> scopes;
        List<List<String>> values;

        public DavsI18nWakeword build() {
            Preconditions.notNull(this.scopes, "scopes");
            Preconditions.notNull(this.values, "values");
            return new DavsI18nWakeword(this);
        }

        public Builder scopes(List<String> list) {
            this.scopes = list;
            return this;
        }

        public Builder values(List<List<String>> list) {
            this.values = list;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<DavsI18nWakeword> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public DavsI18nWakeword mo1239create(JSONObject jSONObject) throws JSONException {
            return new Builder().scopes(JsonUtils.convertJsonArrayToStringList(jSONObject.getJSONArray("scopes"))).values(JsonUtils.convertJsonArrayToListOfStringList(jSONObject.getJSONArray("values"))).build();
        }
    }

    public DavsI18nWakeword(Builder builder) {
        this.scopes = builder.scopes;
        this.values = builder.values;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DavsI18nWakeword.class != obj.getClass()) {
            return false;
        }
        DavsI18nWakeword davsI18nWakeword = (DavsI18nWakeword) obj;
        return Objects.equals(this.scopes, davsI18nWakeword.scopes) && Objects.equals(this.values, davsI18nWakeword.values);
    }

    public List<String> getScopes() {
        return this.scopes;
    }

    public List<List<String>> getValues() {
        return this.values;
    }

    public int hashCode() {
        return Objects.hash(this.scopes, this.values);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (List<String> list : this.values) {
            jSONArray.put(new JSONArray((Collection) list));
        }
        return new JSONObject().put("scopes", new JSONArray((Collection) this.scopes)).put("values", jSONArray);
    }
}
