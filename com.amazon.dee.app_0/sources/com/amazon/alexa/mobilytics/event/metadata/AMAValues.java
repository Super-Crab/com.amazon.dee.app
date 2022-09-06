package com.amazon.alexa.mobilytics.event.metadata;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
@JsonAdapter(AMAMetadataValuesAdapter.class)
/* loaded from: classes9.dex */
public class AMAValues {
    private transient JsonObject jsonObject;

    public AMAValues() {
        this.jsonObject = new JsonObject();
    }

    public boolean isEmpty() {
        JsonObject jsonObject = this.jsonObject;
        return jsonObject == null || jsonObject.size() <= 0;
    }

    public void remove(String str) {
        if (this.jsonObject.has(str)) {
            this.jsonObject.remove(str);
        }
    }

    public void set(String str, Number number) {
        this.jsonObject.addProperty(str, number);
    }

    @NonNull
    public String toString() {
        JsonObject jsonObject = this.jsonObject;
        return jsonObject == null ? "{}" : jsonObject.toString();
    }

    public AMAValues(JsonReader jsonReader) {
        this.jsonObject = new JsonObject();
        this.jsonObject = (JsonObject) new Gson().fromJson(jsonReader, JsonObject.class);
    }
}
