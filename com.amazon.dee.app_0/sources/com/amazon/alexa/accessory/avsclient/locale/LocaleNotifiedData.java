package com.amazon.alexa.accessory.avsclient.locale;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.System;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class LocaleNotifiedData implements JsonObjectSerializable {
    private static final String ADDRESS_KEY = "address";
    public static final Factory FACTORY = new Factory();
    private static final String LOCALE_KEY = "locale";
    private static final String TOSTRING_FORMAT = "UnmatchedLocaleData{address=%s, locale=%s}";
    private final String address;
    private final System.Locale locale;

    /* loaded from: classes.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<LocaleNotifiedData> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public LocaleNotifiedData mo1239create(JSONObject jSONObject) throws JSONException {
            Preconditions.notNull(jSONObject, "jsonObject");
            String string = jSONObject.getString("locale");
            return new LocaleNotifiedData(jSONObject.getString("address"), System.Locale.newBuilder().setName(string).mo10084build());
        }
    }

    public LocaleNotifiedData(String str, System.Locale locale) {
        Preconditions.notNull(locale, "locale");
        Preconditions.notEmpty(str, "address");
        this.address = str;
        this.locale = locale;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LocaleNotifiedData.class != obj.getClass()) {
            return false;
        }
        LocaleNotifiedData localeNotifiedData = (LocaleNotifiedData) obj;
        return Objects.equals(this.locale.getName(), localeNotifiedData.locale.getName()) && Objects.equals(this.address, localeNotifiedData.address);
    }

    public String getAddress() {
        return this.address;
    }

    public System.Locale getLocale() {
        return this.locale;
    }

    public int hashCode() {
        return Objects.hash(this.address, this.locale.getName());
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("locale", this.locale.getName()).put("address", this.address);
    }

    public String toString() {
        return String.format(TOSTRING_FORMAT, this.address, this.locale.getName());
    }
}
