package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Iterator;
/* loaded from: classes.dex */
public final class AccessoryUtils {

    /* renamed from: com.amazon.alexa.accessory.internal.util.AccessoryUtils$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$internal$PeripheralDevice$Type = new int[PeripheralDevice.Type.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$internal$PeripheralDevice$Type[PeripheralDevice.Type.BLUETOOTH_CLASSIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$internal$PeripheralDevice$Type[PeripheralDevice.Type.BLUETOOTH_LE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$internal$PeripheralDevice$Type[PeripheralDevice.Type.BLUETOOTH_UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private AccessoryUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static Accessory getAccessoryFromDeviceGroup(DeviceGroup deviceGroup) {
        String str;
        Iterator<Device> it2 = deviceGroup.getDevices().iterator();
        while (true) {
            if (!it2.hasNext()) {
                str = null;
                break;
            }
            Device next = it2.next();
            if (!next.getName().isEmpty()) {
                str = next.getName();
                break;
            }
        }
        return new Accessory(deviceGroup.getIdentifier(), deviceGroup.getTransportType(), str);
    }

    public static Accessory getAccessoryFromPeripheral(PeripheralDevice peripheralDevice) {
        return new Accessory(peripheralDevice.getAddress(), getTransportFromPeripheral(peripheralDevice.getType()), peripheralDevice.getName());
    }

    public static AccessoryTransport.Type getTransportFromDevice(int i) {
        if (i != 0) {
            if (i == 1) {
                return AccessoryTransport.Type.RFCOMM;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unsupported accessory transport ", i));
        }
        return AccessoryTransport.Type.GATT;
    }

    public static AccessoryTransport.Type getTransportFromPeripheral(PeripheralDevice.Type type) {
        int ordinal = type.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1 && ordinal != 2) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported peripheral device type ");
                outline107.append(PeripheralDevice.peripheralTypeToString(type));
                throw new IllegalArgumentException(outline107.toString());
            }
            return AccessoryTransport.Type.GATT;
        }
        return AccessoryTransport.Type.RFCOMM;
    }
}
