package com.amazon.whisperjoin.provisionerSDK.devices;

import com.amazon.whisperbridge.ProvisioningStatus;
import com.amazon.whisperbridge.constants.Command;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.EncryptionProvider;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.common.sharedtypes.devices.DiscoveredRadio;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DeviceConnectionConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetailsProtoData;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.ProvisionableEventTypeCollection;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.DeviceEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.EventHandler;
import com.amazon.whisperjoin.common.sharedtypes.utility.EncodingHelpers;
import com.amazon.whisperjoin.common.sharedtypes.utility.Serializer;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.provisionerSDK.configuration.ConnectionConfiguration;
import com.amazon.whisperjoin.provisionerSDK.devices.handlers.DeviceConnectionStateEventHandler;
import com.amazon.whisperjoin.provisionerSDK.devices.handlers.ProvisionableDeviceEventDispatcher;
import com.amazon.whisperjoin.provisionerSDK.devices.helpers.DeviceRadioTransportHelper;
import com.amazon.whisperjoin.provisionerSDK.devices.security.TrustNegotiator;
import com.amazon.whisperjoin.provisionerSDK.devices.security.TrustNegotiatorProvider;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.TypeToken;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public class BasicPeripheralDevice implements PeripheralDevice {
    private static final String TAG = BasicPeripheralDevice.class.getName();
    private final Set<Command> UNENCRYPTED_OPERATIONS = ImmutableSet.builder().mo7848add((Object[]) new Command[]{Command.EXCHANGE_ECDHE_KEY, Command.EXCHANGE_AUTHENTICATED_ECDHE_KEY, Command.JPAKE_ROUND1, Command.JPAKE_ROUND2, Command.JPAKE_ROUND3}).mo7852build();
    protected final WhisperJoinPeripheralDeviceDetails mDeviceDetails;
    private final EventHandler<DeviceEvent> mDeviceEventHandler;
    private final DeviceRadioTransportHelper mDeviceRadioTransportHelper;
    private final EncodingHelpers mEncodingHelper;
    private EncryptionProvider mEncryptionProvider;
    protected final ExecutorService mExecutorService;
    private final int mMaximumMessageSize;
    private ProvisionableEventManager mProvisionableEventManager;
    protected Serializer mSerializer;
    private final TrustNegotiatorProvider mTrustNegotiatorProvider;

    public BasicPeripheralDevice(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails, ConnectionConfiguration connectionConfiguration, EncodingHelpers encodingHelpers, Serializer serializer, DeviceRadioTransportHelper deviceRadioTransportHelper, TrustNegotiatorProvider trustNegotiatorProvider) {
        if (whisperJoinPeripheralDeviceDetails != null) {
            if (connectionConfiguration == null) {
                throw new IllegalArgumentException("connectionConfiguration can not be null");
            }
            if (serializer != null) {
                this.mEncodingHelper = encodingHelpers == null ? new EncodingHelpers() : encodingHelpers;
                this.mDeviceDetails = whisperJoinPeripheralDeviceDetails;
                this.mExecutorService = connectionConfiguration.getExecutorService();
                this.mMaximumMessageSize = connectionConfiguration.getMaximumMessageSize();
                this.mSerializer = serializer;
                this.mDeviceRadioTransportHelper = deviceRadioTransportHelper;
                this.mTrustNegotiatorProvider = trustNegotiatorProvider;
                this.mDeviceEventHandler = new ProvisionableDeviceEventDispatcher(connectionConfiguration.getDeviceObservers(), this.mDeviceDetails);
                return;
            }
            throw new IllegalArgumentException("serializer must not be null");
        }
        throw new IllegalArgumentException("deviceDetails can not be null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableProvisionableNotifications() throws Exception {
        WJLog.d(TAG, "Enabling Notificaitons");
        ProvisionableEventTypeCollection provisionableEventTypeCollection = (ProvisionableEventTypeCollection) executeOperation(Command.GET_SUPPORTED_NOTIFICATIONS, null, new TypeToken<ProvisionableEventTypeCollection>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.BasicPeripheralDevice.3
        });
        if (provisionableEventTypeCollection == null) {
            WJLog.e(TAG, "Device not configured to support notifications");
            return;
        }
        this.mProvisionableEventManager = new ProvisionableEventManager(provisionableEventTypeCollection.getSetCollection(), this, this.mExecutorService, this.mDeviceEventHandler);
        this.mDeviceRadioTransportHelper.setEventNotificationCallback(this.mProvisionableEventManager);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mDeviceRadioTransportHelper.closeConnection();
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice
    public Future<Void> completeProvisioning() {
        return this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.BasicPeripheralDevice.5
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                BasicPeripheralDevice.this.executeOperation(Command.COMPLETE_PROVISIONING, null, null);
                return BasicPeripheralDevice.this.mDeviceRadioTransportHelper.onProvisioningComplete().get();
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice
    public Future<Void> connectToPeripheral(DeviceConnectionConfiguration deviceConnectionConfiguration) {
        return connectToPeripheral(null, deviceConnectionConfiguration);
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice
    public byte[] executeCommand(Command command, byte[] bArr) throws Exception {
        return executeCommand(this.mDeviceRadioTransportHelper.getCommandExecutor(), this.mEncryptionProvider, command, bArr);
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice
    public Object executeOperation(Command command, Object obj, TypeToken typeToken) throws Exception {
        byte[] executeCommand = executeCommand(command, obj != null ? this.mSerializer.serialize(obj) : null);
        if (typeToken != null) {
            return this.mSerializer.deserialize(executeCommand, typeToken);
        }
        return null;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice
    public DiscoveredRadio getConnectionRadio() {
        return this.mDeviceRadioTransportHelper.getSelectedRadio();
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice
    public Future<DeviceDetailsProtoData> getDeviceDetails() {
        return this.mExecutorService.submit(new Callable<DeviceDetailsProtoData>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.BasicPeripheralDevice.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public DeviceDetailsProtoData mo6630call() throws Exception {
                return (DeviceDetailsProtoData) BasicPeripheralDevice.this.executeOperation(Command.GET_DEVICE_DETAILS, null, new TypeToken<DeviceDetailsProtoData>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.BasicPeripheralDevice.6.1
                });
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice
    public WhisperJoinPeripheralDeviceDetails getPeripheralDeviceDetails() {
        return this.mDeviceDetails;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice
    public Future<byte[]> sendCommand(String str, byte[] bArr) {
        final byte[] encodeCommand = this.mEncodingHelper.encodeCommand(str, bArr, this.mMaximumMessageSize);
        return this.mExecutorService.submit(new Callable<byte[]>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.BasicPeripheralDevice.7
            @Override // java.util.concurrent.Callable
            public byte[] call() throws Exception {
                return BasicPeripheralDevice.this.executeCommand(Command.DATA, encodeCommand);
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice
    public Future<ProvisioningStatus> startProvisioning() {
        return this.mExecutorService.submit(new Callable<ProvisioningStatus>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.BasicPeripheralDevice.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public ProvisioningStatus mo6629call() throws Exception {
                ProvisioningStatus provisioningStatus = BasicPeripheralDevice.this.mDeviceRadioTransportHelper.startProvisioning().get();
                if (!provisioningStatus.isProvisioner()) {
                    return provisioningStatus;
                }
                TrustNegotiator trustProvider = BasicPeripheralDevice.this.mTrustNegotiatorProvider.getTrustProvider();
                BasicPeripheralDevice basicPeripheralDevice = BasicPeripheralDevice.this;
                basicPeripheralDevice.mEncryptionProvider = trustProvider.start(basicPeripheralDevice);
                BasicPeripheralDevice.this.enableProvisionableNotifications();
                return provisioningStatus;
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice
    public Future<Void> stopProvisioning() {
        return this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.BasicPeripheralDevice.4
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                BasicPeripheralDevice.this.executeOperation(Command.STOP_PROVISIONING, null, null);
                return null;
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice
    public Future<Void> connectToPeripheral(String str) {
        return connectToPeripheral(str, DeviceConnectionConfiguration.builder().withTrustState(TrustProvider.TrustState.TRUSTED_ENCRYPTED).build());
    }

    byte[] executeCommand(DeviceCommandExecutor deviceCommandExecutor, EncryptionProvider encryptionProvider, Command command, byte[] bArr) throws Exception {
        byte[] executeCommand;
        String str = TAG;
        WJLog.byteStruct(str, "Input Data for Command: " + command, bArr);
        if (this.UNENCRYPTED_OPERATIONS.contains(command)) {
            return deviceCommandExecutor.executeCommand(command, bArr);
        }
        if (bArr != null) {
            byte[] encrypt = encryptionProvider.encrypt(bArr);
            WJLog.byteStruct(TAG, "Encrypted Input Data: ", encrypt);
            executeCommand = deviceCommandExecutor.executeCommand(command, encrypt);
        } else {
            executeCommand = deviceCommandExecutor.executeCommand(command, null);
        }
        String str2 = TAG;
        WJLog.byteStruct(str2, "Encrypted Output Data for Command: " + command, executeCommand);
        if (executeCommand == null || executeCommand.length <= 0) {
            return executeCommand;
        }
        byte[] decrypt = encryptionProvider.decrypt(executeCommand);
        String str3 = TAG;
        WJLog.byteStruct(str3, "Decrypted Output Data for Command: " + command, decrypt);
        return decrypt;
    }

    private Future<Void> connectToPeripheral(final String str, final DeviceConnectionConfiguration deviceConnectionConfiguration) {
        return this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.BasicPeripheralDevice.1
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                BasicPeripheralDevice.this.mDeviceRadioTransportHelper.openConnection(new DeviceConnectionStateEventHandler(BasicPeripheralDevice.this.mDeviceEventHandler)).get();
                BasicPeripheralDevice.this.mTrustNegotiatorProvider.setDeviceConnectionConfiguration(deviceConnectionConfiguration);
                BasicPeripheralDevice.this.mTrustNegotiatorProvider.setPreSharedSecret(str);
                return null;
            }
        });
    }
}
