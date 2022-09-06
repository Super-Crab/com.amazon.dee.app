package com.amazonaws.com.google.gson;
/* loaded from: classes13.dex */
public final class JsonNull extends JsonElement {
    public static final JsonNull INSTANCE = new JsonNull();

    public boolean equals(Object obj) {
        return this == obj || (obj instanceof JsonNull);
    }

    public int hashCode() {
        return JsonNull.class.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.com.google.gson.JsonElement
    /* renamed from: deepCopy  reason: collision with other method in class */
    public JsonNull mo6653deepCopy() {
        return INSTANCE;
    }
}
