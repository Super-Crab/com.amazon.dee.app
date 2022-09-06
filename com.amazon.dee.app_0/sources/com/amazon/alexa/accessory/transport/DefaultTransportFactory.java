package com.amazon.alexa.accessory.transport;

import android.content.Context;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryAttributes;
import com.amazon.alexa.accessory.AccessorySessionOptions;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.bluetooth.BluetoothDeviceBonder;
import com.amazon.alexa.accessory.internal.bluetooth.spp.SppSocketProducer;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.transport.gatt.GattTransport;
import com.amazon.alexa.accessory.transport.rfcomm.RfcommTransport;
import java.util.UUID;
/* loaded from: classes6.dex */
public final class DefaultTransportFactory implements AccessoryTransport.Factory {
    private final AccessoryAttributes accessoryAttributes;
    private final Context context;
    private final BluetoothDeviceBonder deviceBonder;
    private final UUID lowEnergyUuid;
    private final SppSocketProducer sppSocketProducer;

    /* renamed from: com.amazon.alexa.accessory.transport.DefaultTransportFactory$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$AccessoryTransport$Type = new int[AccessoryTransport.Type.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$AccessoryTransport$Type[AccessoryTransport.Type.GATT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$AccessoryTransport$Type[AccessoryTransport.Type.RFCOMM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public DefaultTransportFactory(Context context, UUID uuid, BluetoothDeviceBonder bluetoothDeviceBonder, AccessoryAttributes accessoryAttributes, SppSocketProducer sppSocketProducer) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(uuid, "lowEnergyUuid");
        Preconditions.notNull(bluetoothDeviceBonder, "deviceBonder");
        Preconditions.notNull(accessoryAttributes, "accessoryAttributes");
        Preconditions.notNull(sppSocketProducer, "sppSocketProducer");
        this.context = context;
        this.lowEnergyUuid = uuid;
        this.deviceBonder = bluetoothDeviceBonder;
        this.accessoryAttributes = accessoryAttributes;
        this.sppSocketProducer = sppSocketProducer;
    }

    @Override // com.amazon.alexa.accessory.AccessoryTransport.Factory
    public AccessoryTransport createTransport(Accessory accessory, AccessorySessionOptions accessorySessionOptions) {
        int ordinal = accessory.getTransport().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return new RfcommTransport(this.context, accessory, this.deviceBonder, this.accessoryAttributes.rfcommChannelId(), this.sppSocketProducer);
            }
            throw new IllegalArgumentException("Accessory has unknown transport " + accessory);
        }
        return new GattTransport(this.context, accessory, this.deviceBonder, this.lowEnergyUuid, this.accessoryAttributes, accessorySessionOptions);
    }
}
