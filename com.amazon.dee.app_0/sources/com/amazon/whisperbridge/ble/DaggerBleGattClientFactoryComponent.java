package com.amazon.whisperbridge.ble;

import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DaggerBleGattClientFactoryComponent implements BleGattClientFactoryComponent {
    private Provider<BluetoothGattFactory> provideBluetoothGattFactoryProvider;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private GattClientModule gattClientModule;

        public BleGattClientFactoryComponent build() {
            if (this.gattClientModule == null) {
                this.gattClientModule = new GattClientModule();
            }
            return new DaggerBleGattClientFactoryComponent(this);
        }

        public Builder gattClientModule(GattClientModule gattClientModule) {
            this.gattClientModule = (GattClientModule) Preconditions.checkNotNull(gattClientModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static BleGattClientFactoryComponent create() {
        return new Builder().build();
    }

    private void initialize(Builder builder) {
        this.provideBluetoothGattFactoryProvider = DoubleCheck.provider(GattClientModule_ProvideBluetoothGattFactoryFactory.create(builder.gattClientModule));
    }

    private BleGattClientFactory injectBleGattClientFactory(BleGattClientFactory bleGattClientFactory) {
        BleGattClientFactory_MembersInjector.injectMBluetoothGattFactory(bleGattClientFactory, this.provideBluetoothGattFactoryProvider.mo10268get());
        return bleGattClientFactory;
    }

    @Override // com.amazon.whisperbridge.ble.BleGattClientFactoryComponent
    public void inject(BleGattClientFactory bleGattClientFactory) {
        injectBleGattClientFactory(bleGattClientFactory);
    }

    private DaggerBleGattClientFactoryComponent(Builder builder) {
        initialize(builder);
    }
}
