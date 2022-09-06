package com.amazon.alexa.accessorykit.cloudpairing;

import com.amazon.alexa.accessory.protocol.Cloudpairing;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.protobuf.ByteString;
import java.io.NotSerializableException;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class CloudPairingSeedTransformer implements MapModelTransformer<Cloudpairing.Seed> {
    private static final String SEED_KEY = "seed";

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public Cloudpairing.Seed mo626transform(ReadableMap readableMap) throws ParseException {
        try {
            return Cloudpairing.Seed.newBuilder().setBluetoothSeed(ByteString.copyFrom(readableMap.getString(SEED_KEY).getBytes())).mo10084build();
        } catch (Exception e) {
            throw new ParseException(e.toString(), 0);
        }
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(Cloudpairing.Seed seed) throws NotSerializableException {
        throw new NotSerializableException("Not Supported");
    }
}
