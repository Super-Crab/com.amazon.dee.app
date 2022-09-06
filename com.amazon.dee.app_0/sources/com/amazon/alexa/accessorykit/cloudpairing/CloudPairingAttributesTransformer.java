package com.amazon.alexa.accessorykit.cloudpairing;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Cloudpairing;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class CloudPairingAttributesTransformer implements MapModelTransformer<Cloudpairing.CloudPairingAttributes> {
    private static final String ACCESSORY_CATEGORY_KEY = "accessoryCategory";
    private static final String ACCESSORY_FEATURE_KEY = "accessoryFeature";
    private static final String BLUETOOTH_SERVICE_KEY = "bluetoothService";
    private static final String BLUETOOTH_TRANSPORT_KEY = "bluetoothTransport";
    private static final String IDENTIFIER_KEY = "identifier";
    private static final String TAG = "CloudPairingAttributesTransformer:";
    private static final String VERSION_KEY = "version";
    private final ContainerProvider containerProvider;

    /* renamed from: com.amazon.alexa.accessorykit.cloudpairing.CloudPairingAttributesTransformer$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Capability$CapabilityTypesCase = new int[Cloudpairing.Capability.CapabilityTypesCase.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Capability$CapabilityTypesCase[Cloudpairing.Capability.CapabilityTypesCase.BLUETOOTH_SERVICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Capability$CapabilityTypesCase[Cloudpairing.Capability.CapabilityTypesCase.BLUETOOTH_TRANSPORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Capability$CapabilityTypesCase[Cloudpairing.Capability.CapabilityTypesCase.ACCESSORY_FEATURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Cloudpairing$Capability$CapabilityTypesCase[Cloudpairing.Capability.CapabilityTypesCase.ACCESSORY_CATEGORY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public CloudPairingAttributesTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "containerProvider");
        this.containerProvider = containerProvider;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public Cloudpairing.CloudPairingAttributes mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(Cloudpairing.CloudPairingAttributes cloudPairingAttributes) {
        Preconditions.notNull(cloudPairingAttributes, "cloudPairingAttributes");
        WritableMap map = this.containerProvider.getMap();
        WritableArray array = this.containerProvider.getArray();
        WritableArray array2 = this.containerProvider.getArray();
        WritableArray array3 = this.containerProvider.getArray();
        map.putInt("version", cloudPairingAttributes.getVersion());
        map.putString("identifier", new String(cloudPairingAttributes.getIdentifier().toByteArray()));
        for (Cloudpairing.Capability capability : cloudPairingAttributes.getCapabilitiesList()) {
            int ordinal = capability.getCapabilityTypesCase().ordinal();
            if (ordinal == 0) {
                array.pushString(capability.getBluetoothService().name());
            } else if (ordinal == 1) {
                array2.pushString(capability.getBluetoothTransport().name());
            } else if (ordinal == 2) {
                array3.pushString(capability.getAccessoryFeature().name());
            } else if (ordinal != 3) {
                Logger.d("%s Unknown Cloudpairing Capability found: %s", TAG, capability.getCapabilityTypesCase().name());
            } else {
                map.putString(ACCESSORY_CATEGORY_KEY, capability.getAccessoryCategory().name());
            }
        }
        map.putArray(BLUETOOTH_SERVICE_KEY, array);
        map.putArray(BLUETOOTH_TRANSPORT_KEY, array2);
        map.putArray(ACCESSORY_FEATURE_KEY, array3);
        return map;
    }
}
