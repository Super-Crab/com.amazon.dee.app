package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import org.codehaus.jackson.JsonParser;
/* compiled from: lambda */
/* renamed from: com.amazon.clouddrive.extended.model.deserializer.-$$Lambda$lXlJC41MFIklcFIwGYK4iV8JLtc  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$lXlJC41MFIklcFIwGYK4iV8JLtc implements JsonDeserializer {
    public static final /* synthetic */ $$Lambda$lXlJC41MFIklcFIwGYK4iV8JLtc INSTANCE = new $$Lambda$lXlJC41MFIklcFIwGYK4iV8JLtc();

    private /* synthetic */ $$Lambda$lXlJC41MFIklcFIwGYK4iV8JLtc() {
    }

    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public final Object mo3229deserialize(JsonParser jsonParser) {
        return SimpleDeserializers.deserializeString(jsonParser);
    }
}
