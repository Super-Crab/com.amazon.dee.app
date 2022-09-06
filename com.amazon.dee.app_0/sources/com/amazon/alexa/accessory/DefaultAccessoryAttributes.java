package com.amazon.alexa.accessory;

import java.util.UUID;
/* loaded from: classes.dex */
public final class DefaultAccessoryAttributes implements AccessoryAttributes {
    private static final UUID ALEXA_SERVICE = UUID.fromString("0000FE03-0000-1000-8000-00805F9B34FB");
    private static final UUID ALEXA_CHARACTERISTIC_TX = UUID.fromString("F04EB177-3005-43A7-AC61-A390DDF83076");
    private static final UUID ALEXA_CHARACTERISTIC_RX = UUID.fromString("2BEEA05B-1879-4BB4-8A2F-72641F82420B");
    private static final UUID ALEXA_CONFIGURATION = UUID.fromString("00002902-0000-1000-8000-00805F9B34FB");
    private static final UUID ALEXA_BLUETOOTH_PROFILE = UUID.fromString("931C7E8A-540F-4686-B798-E8DF0A2AD9F7");
    private static final UUID ALEXA_UNSECURE_CHARACTERISTIC_TX = UUID.fromString("74F996C9-7D6C-4D58-9232-0427AB61C53C");
    private static final UUID ALEXA_UNSECURE_CHARACTERISTIC_RX = UUID.fromString("B32E83C0-FECE-47C1-9015-53B7E7F0D2FE");

    @Override // com.amazon.alexa.accessory.AccessoryAttributes
    public UUID lowEnergyCharacteristicDescriptorRxId() {
        return ALEXA_CONFIGURATION;
    }

    @Override // com.amazon.alexa.accessory.AccessoryAttributes
    public UUID lowEnergyCharacteristicRxId() {
        return ALEXA_CHARACTERISTIC_RX;
    }

    @Override // com.amazon.alexa.accessory.AccessoryAttributes
    public UUID lowEnergyCharacteristicTxId() {
        return ALEXA_CHARACTERISTIC_TX;
    }

    @Override // com.amazon.alexa.accessory.AccessoryAttributes
    public UUID lowEnergyServiceAdvertisingId() {
        return ALEXA_SERVICE;
    }

    @Override // com.amazon.alexa.accessory.AccessoryAttributes
    public UUID rfcommChannelId() {
        return ALEXA_BLUETOOTH_PROFILE;
    }

    @Override // com.amazon.alexa.accessory.AccessoryAttributes
    public UUID unsecureLowEnergyCharacteristicRxId() {
        return ALEXA_UNSECURE_CHARACTERISTIC_RX;
    }

    @Override // com.amazon.alexa.accessory.AccessoryAttributes
    public UUID unsecureLowEnergyCharacteristicTxId() {
        return ALEXA_UNSECURE_CHARACTERISTIC_TX;
    }
}
