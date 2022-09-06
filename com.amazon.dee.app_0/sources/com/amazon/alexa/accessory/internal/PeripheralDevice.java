package com.amazon.alexa.accessory.internal;

import android.bluetooth.BluetoothDevice;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public final class PeripheralDevice {
    private final String address;
    private final String name;
    private final Type type;

    /* renamed from: com.amazon.alexa.accessory.internal.PeripheralDevice$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$AccessoryTransport$Type = new int[AccessoryTransport.Type.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$internal$PeripheralDevice$Type;

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$AccessoryTransport$Type[AccessoryTransport.Type.GATT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$AccessoryTransport$Type[AccessoryTransport.Type.RFCOMM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$amazon$alexa$accessory$internal$PeripheralDevice$Type = new int[Type.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$internal$PeripheralDevice$Type[Type.BLUETOOTH_CLASSIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$internal$PeripheralDevice$Type[Type.BLUETOOTH_LE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$internal$PeripheralDevice$Type[Type.BLUETOOTH_UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum Type {
        BLUETOOTH_CLASSIC,
        BLUETOOTH_LE,
        BLUETOOTH_UNKNOWN
    }

    public PeripheralDevice(String str, Type type, String str2) {
        Preconditions.notNull(str, "address");
        Preconditions.notNull(type, "type");
        this.address = str;
        this.type = type;
        this.name = str2;
    }

    private static Type getTypeForAccessory(Accessory accessory) {
        int ordinal = accessory.getTransport().ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                return Type.BLUETOOTH_UNKNOWN;
            }
            return Type.BLUETOOTH_CLASSIC;
        }
        return Type.BLUETOOTH_LE;
    }

    private static Type getTypeForBluetoothDevice(int i) {
        if (i != 1) {
            if (i == 2) {
                return Type.BLUETOOTH_LE;
            }
            if (i != 3) {
                return Type.BLUETOOTH_UNKNOWN;
            }
            return Type.BLUETOOTH_CLASSIC;
        }
        return Type.BLUETOOTH_CLASSIC;
    }

    private static Type getTypeForDevice(int i) {
        if (i != 0) {
            if (i != 1) {
                return Type.BLUETOOTH_UNKNOWN;
            }
            return Type.BLUETOOTH_CLASSIC;
        }
        return Type.BLUETOOTH_LE;
    }

    private static Type getTypeForDeviceGroup(AccessoryTransport.Type type) {
        int ordinal = type.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                return Type.BLUETOOTH_UNKNOWN;
            }
            return Type.BLUETOOTH_CLASSIC;
        }
        return Type.BLUETOOTH_LE;
    }

    public static String peripheralTypeToString(Type type) {
        int ordinal = type.ordinal();
        return ordinal != 0 ? ordinal != 1 ? "BLUETOOTH_UNKNOWN" : "BLUETOOTH_LE" : "BLUETOOTH_CLASSIC";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PeripheralDevice.class != obj.getClass()) {
            return false;
        }
        PeripheralDevice peripheralDevice = (PeripheralDevice) obj;
        String str = this.address;
        if (str == null ? peripheralDevice.address != null : !str.equals(peripheralDevice.address)) {
            return false;
        }
        return this.type == peripheralDevice.type;
    }

    public String getAddress() {
        return this.address;
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    public int hashCode() {
        String str = this.address;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Type type = this.type;
        if (type != null) {
            i = type.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PeripheralDevice{address='");
        GeneratedOutlineSupport1.outline176(outline107, this.address, Chars.QUOTE, ", type=");
        outline107.append(this.type);
        outline107.append(", name='");
        return GeneratedOutlineSupport1.outline90(outline107, this.name, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    public PeripheralDevice(String str, Type type) {
        this(str, type, null);
    }

    public PeripheralDevice(BluetoothDevice bluetoothDevice) {
        this(bluetoothDevice.getAddress(), getTypeForBluetoothDevice(bluetoothDevice.getType()), bluetoothDevice.getName());
    }

    public PeripheralDevice(DeviceGroup deviceGroup) {
        this(deviceGroup.getIdentifier(), getTypeForDeviceGroup(deviceGroup.getTransportType()));
    }

    public PeripheralDevice(Accessory accessory) {
        this(accessory.getAddress(), getTypeForAccessory(accessory), accessory.getName());
    }
}
