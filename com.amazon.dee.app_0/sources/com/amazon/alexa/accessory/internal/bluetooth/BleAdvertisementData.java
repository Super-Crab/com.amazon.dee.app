package com.amazon.alexa.accessory.internal.bluetooth;

import com.amazon.alexa.accessory.internal.util.ByteUtils;
import java.util.HashMap;
import java.util.Locale;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
public final class BleAdvertisementData {
    private final GapProfileMap gapProfileMap;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class GapProfileMap extends HashMap<Byte, byte[]> {
        private void checkPacketType(Byte b) {
            if (GenericAccessProfile.GAP_ASSIGNED_NUMBERS.contains(b)) {
                return;
            }
            throw new IllegalArgumentException(b + " is not a GAP packet type number");
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public byte[] get(Object obj) {
            checkPacketType((Byte) obj);
            return (byte[]) super.get(obj);
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public byte[] put(Byte b, byte[] bArr) {
            checkPacketType(b);
            return (byte[]) super.put((GapProfileMap) b, (Byte) bArr);
        }
    }

    public BleAdvertisementData(byte[] bArr) {
        this.gapProfileMap = BluetoothUtils.parseLeAdvertisementData(bArr);
    }

    public byte[] getDataForADType(byte b) {
        return this.gapProfileMap.get((Object) Byte.valueOf(b));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{\n");
        for (Byte b : this.gapProfileMap.keySet()) {
            byte byteValue = b.byteValue();
            byte[] bArr = this.gapProfileMap.get((Object) Byte.valueOf(byteValue));
            sb.append("    ");
            sb.append(String.format(Locale.US, "%02X: ", Byte.valueOf(byteValue)));
            sb.append(ByteUtils.bytesToHex(bArr));
            sb.append('\n');
        }
        sb.append("    }\n");
        return "BleAdvertisementData{gapProfileMap=" + sb.toString() + JsonReaderKt.END_OBJ;
    }
}
