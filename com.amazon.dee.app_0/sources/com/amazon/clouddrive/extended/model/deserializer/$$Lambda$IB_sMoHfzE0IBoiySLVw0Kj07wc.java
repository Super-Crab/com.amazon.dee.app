package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import org.codehaus.jackson.JsonParser;
/* compiled from: lambda */
/* renamed from: com.amazon.clouddrive.extended.model.deserializer.-$$Lambda$IB_sMoHfzE0IBoiySLVw0Kj07wc  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$IB_sMoHfzE0IBoiySLVw0Kj07wc implements JsonDeserializer {
    public static final /* synthetic */ $$Lambda$IB_sMoHfzE0IBoiySLVw0Kj07wc INSTANCE = new $$Lambda$IB_sMoHfzE0IBoiySLVw0Kj07wc();

    private /* synthetic */ $$Lambda$IB_sMoHfzE0IBoiySLVw0Kj07wc() {
    }

    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public final Object mo3229deserialize(JsonParser jsonParser) {
        return SimpleDeserializers.deserializeInteger(jsonParser);
    }
}
