package com.amazon.alexa.accessory.repositories.crypto;

import com.amazon.alexa.accessory.Interactor;
import com.amazon.alexa.accessory.internal.monitor.BluetoothBondMonitor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
/* loaded from: classes6.dex */
public class KeyExchangeInteractor implements Interactor {
    private static final String TAG = "KeyExchangeInteractor";
    private boolean active;
    private final BluetoothBondMonitor bluetoothBondMonitor;
    private final KeyExchangeInvalidator keyExchangeInvalidator;

    public KeyExchangeInteractor(KeyExchangeInvalidator keyExchangeInvalidator, BluetoothBondMonitor bluetoothBondMonitor) {
        Preconditions.notNull(keyExchangeInvalidator, "keyExchangeInvalidator");
        Preconditions.notNull(bluetoothBondMonitor, "bluetoothBondMonitor");
        this.keyExchangeInvalidator = keyExchangeInvalidator;
        this.bluetoothBondMonitor = bluetoothBondMonitor;
    }

    @Override // com.amazon.alexa.accessory.Interactor
    public void activate() {
        Preconditions.mainThread();
        if (this.active) {
            return;
        }
        Logger.d("%s activating.", TAG);
        this.active = true;
        this.keyExchangeInvalidator.activate(this.bluetoothBondMonitor);
    }

    @Override // com.amazon.alexa.accessory.Interactor
    public void deactivate() {
        if (!this.active) {
            return;
        }
        Logger.d("%s deactivating.", TAG);
        this.active = false;
        this.keyExchangeInvalidator.deactivate(this.bluetoothBondMonitor);
    }
}
