package com.google.gson;
/* loaded from: classes3.dex */
public final class JsonNull extends JsonElement {
    public static final JsonNull INSTANCE = new JsonNull();

    public boolean equals(Object obj) {
        return this == obj || (obj instanceof JsonNull);
    }

    public int hashCode() {
        return JsonNull.class.hashCode();
    }

    @Override // com.google.gson.JsonElement
    /* renamed from: deepCopy  reason: collision with other method in class */
    public JsonNull mo8313deepCopy() {
        return INSTANCE;
    }
}
