package com.amazon.alexa.accessory.davs;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.davs.DavsI18nWakeword;
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
public final class DavsI18nConfig implements JsonObjectSerializable {
    @VisibleForTesting
    static final String CONFIGURATIONS_KEY = "configurations";
    public static final Factory FACTORY = new Factory();
    @VisibleForTesting
    static final String LOCALE_COMBINATIONS_KEY = "localeCombinations";
    @VisibleForTesting
    static final String LOCALE_KEY = "locales";
    @VisibleForTesting
    static final String WAKEWORDS_KEY = "wakeWords";
    private final List<List<String>> localeCombinations;
    private final List<String> locales;
    private final List<DavsI18nWakeword> wakeWords;

    /* loaded from: classes.dex */
    public static final class Builder {
        List<List<String>> localeCombinations;
        List<String> locales;
        List<DavsI18nWakeword> wakeWords;

        public DavsI18nConfig build() {
            Preconditions.notNull(this.wakeWords, "wakeWords");
            Preconditions.notNull(this.locales, "locales");
            Preconditions.notNull(this.localeCombinations, "localeCombinations");
            return new DavsI18nConfig(this);
        }

        public Builder localeCombinations(List<List<String>> list) {
            this.localeCombinations = list;
            return this;
        }

        public Builder locales(List<String> list) {
            this.locales = list;
            return this;
        }

        public Builder wakeWords(List<DavsI18nWakeword> list) {
            this.wakeWords = list;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<DavsI18nConfig> {
        private final DavsI18nWakeword.Factory davsI18nWakewordFactory = new DavsI18nWakeword.Factory();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public DavsI18nConfig mo1239create(JSONObject jSONObject) throws JSONException {
            JSONObject jSONObject2 = jSONObject.getJSONObject(DavsI18nConfig.CONFIGURATIONS_KEY);
            return new Builder().wakeWords(JsonUtils.fromJsonArray(jSONObject2.getJSONArray("wakeWords"), this.davsI18nWakewordFactory)).locales(JsonUtils.convertJsonArrayToStringList(jSONObject2.getJSONArray("locales"))).localeCombinations(JsonUtils.convertJsonArrayToListOfStringList(jSONObject2.getJSONArray("localeCombinations"))).build();
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DavsI18nConfig.class != obj.getClass()) {
            return false;
        }
        DavsI18nConfig davsI18nConfig = (DavsI18nConfig) obj;
        return Objects.equals(this.wakeWords, davsI18nConfig.wakeWords) && Objects.equals(this.locales, davsI18nConfig.locales) && Objects.equals(this.localeCombinations, davsI18nConfig.localeCombinations);
    }

    public List<List<String>> getLocaleCombinations() {
        return this.localeCombinations;
    }

    public List<String> getLocales() {
        return this.locales;
    }

    public List<DavsI18nWakeword> getWakeWords() {
        return this.wakeWords;
    }

    public int hashCode() {
        return Objects.hash(this.wakeWords, this.locales, this.localeCombinations);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (List<String> list : this.localeCombinations) {
            jSONArray.put(new JSONArray((Collection) list));
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("locales", new JSONArray((Collection) this.locales));
        jSONObject.put("localeCombinations", jSONArray);
        jSONObject.put("wakeWords", JsonUtils.toJsonArray(this.wakeWords));
        return new JSONObject().put(CONFIGURATIONS_KEY, jSONObject);
    }

    private DavsI18nConfig(Builder builder) {
        this.wakeWords = builder.wakeWords;
        this.locales = builder.locales;
        this.localeCombinations = builder.localeCombinations;
    }
}
