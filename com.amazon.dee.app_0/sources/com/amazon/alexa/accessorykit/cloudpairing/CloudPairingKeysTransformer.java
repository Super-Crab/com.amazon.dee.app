package com.amazon.alexa.accessorykit.cloudpairing;

import com.amazon.alexa.accessory.protocol.Cloudpairing;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.protobuf.ByteString;
import java.io.NotSerializableException;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class CloudPairingKeysTransformer implements MapModelTransformer<Cloudpairing.CloudPairingKeys> {
    private static final String KEY = "key";
    private final MapModelTransformer<Cloudpairing.Seed> cloudPairingSeedTransformer = new CloudPairingSeedTransformer();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public Cloudpairing.CloudPairingKeys mo626transform(ReadableMap readableMap) throws ParseException {
        try {
            return Cloudpairing.CloudPairingKeys.newBuilder().setBluetoothKeys(Cloudpairing.BluetoothKeys.newBuilder().setSeed(this.cloudPairingSeedTransformer.mo626transform(readableMap)).setSymmetricKey(ByteString.copyFrom(readableMap.getString("key").getBytes()))).mo10084build();
        } catch (Exception e) {
            throw new ParseException(e.toString(), 0);
        }
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(Cloudpairing.CloudPairingKeys cloudPairingKeys) throws NotSerializableException {
        throw new NotSerializableException("Not Supported");
    }
}
