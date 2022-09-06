package com.google.firebase.encoders.json;

import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
/* compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
/* loaded from: classes3.dex */
final /* synthetic */ class JsonDataEncoderBuilder$$Lambda$1 implements ValueEncoder {
    private static final JsonDataEncoderBuilder$$Lambda$1 instance = new JsonDataEncoderBuilder$$Lambda$1();

    private JsonDataEncoderBuilder$$Lambda$1() {
    }

    @Override // com.google.firebase.encoders.ValueEncoder, com.google.firebase.encoders.Encoder
    public void encode(Object obj, ValueEncoderContext valueEncoderContext) {
        JsonDataEncoderBuilder.lambda$static$0((String) obj, valueEncoderContext);
    }
}
