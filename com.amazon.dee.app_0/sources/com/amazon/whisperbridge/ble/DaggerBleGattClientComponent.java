package com.amazon.whisperbridge.ble;

import com.amazon.whisperbridge.ble.command.BleCommandExecutor;
import com.amazon.whisperbridge.ble.command.BleCommandExecutorModule;
import com.amazon.whisperbridge.ble.command.BleCommandExecutorModule_ProvideCommandExecutorFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DaggerBleGattClientComponent implements BleGattClientComponent {
    private Provider<BleCommandExecutor> provideCommandExecutorProvider;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private BleCommandExecutorModule bleCommandExecutorModule;

        public Builder bleCommandExecutorModule(BleCommandExecutorModule bleCommandExecutorModule) {
            this.bleCommandExecutorModule = (BleCommandExecutorModule) Preconditions.checkNotNull(bleCommandExecutorModule);
            return this;
        }

        public BleGattClientComponent build() {
            if (this.bleCommandExecutorModule == null) {
                this.bleCommandExecutorModule = new BleCommandExecutorModule();
            }
            return new DaggerBleGattClientComponent(this);
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static BleGattClientComponent create() {
        return new Builder().build();
    }

    private void initialize(Builder builder) {
        this.provideCommandExecutorProvider = DoubleCheck.provider(BleCommandExecutorModule_ProvideCommandExecutorFactory.create(builder.bleCommandExecutorModule));
    }

    private BleGattClient injectBleGattClient(BleGattClient bleGattClient) {
        BleGattClient_MembersInjector.injectMClientCommandExecutor(bleGattClient, this.provideCommandExecutorProvider.mo10268get());
        return bleGattClient;
    }

    @Override // com.amazon.whisperbridge.ble.BleGattClientComponent
    public void inject(BleGattClient bleGattClient) {
        injectBleGattClient(bleGattClient);
    }

    private DaggerBleGattClientComponent(Builder builder) {
        initialize(builder);
    }
}
