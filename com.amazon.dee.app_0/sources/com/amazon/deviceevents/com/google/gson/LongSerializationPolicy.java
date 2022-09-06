package com.amazon.deviceevents.com.google.gson;
/* loaded from: classes12.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.amazon.deviceevents.com.google.gson.LongSerializationPolicy.1
        @Override // com.amazon.deviceevents.com.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long l) {
            return new JsonPrimitive((Number) l);
        }
    },
    STRING { // from class: com.amazon.deviceevents.com.google.gson.LongSerializationPolicy.2
        @Override // com.amazon.deviceevents.com.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long l) {
            return new JsonPrimitive(String.valueOf(l));
        }
    };

    public abstract JsonElement serialize(Long l);
}
