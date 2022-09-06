package com.amazon.matter.discovery;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelUuid;
import android.util.Log;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.matter.MatterClient;
import com.amazon.matter.MatterDeviceCommissionListener;
import com.amazon.matter.data.MatterError;
import com.amazon.matter.data.MatterErrorType;
import com.amazon.matter.data.PairDeviceResponse;
import com.amazon.matter.data.PairDeviceStatus;
import com.amazon.matter.discovery.DiscoveryServiceBleImpl;
import com.amazon.matter.eventbus.EventBusHelper;
import com.amazon.matter.eventbus.MatterEventType;
import com.amazon.matter.metrics.MatterMetricsService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class DiscoveryServiceBleImpl implements DiscoveryService {
    private static final Gson GSON = new Gson();
    private static final String MATTER_UUID = "0000FFF6-0000-1000-8000-00805F9B34FB";
    private static final int SCAN_TIMEOUT_MILLISEC = 5000;
    private static final String TAG = "DiscoveryServiceBleImpl";
    private BluetoothGattCallback mBluetoothGattCallback;
    private BluetoothLeScanner mBluetoothLeScanner;
    private Context mContext;
    private BluetoothDevice mDeviceDiscovered;
    private short mDiscriminator;
    private EventBusHelper mEventBusHelper;
    private long mFabricId;
    private long mNodeId;
    private long mPasscode;
    private short mProductId;
    private boolean mScanning;
    private short mVendorId;
    private MatterMetricsService metricsService;
    private MobilyticsMetricsTimer timer;
    private ScanCallback leScanCallback = new ScanCallback() { // from class: com.amazon.matter.discovery.DiscoveryServiceBleImpl.1
        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            String str = DiscoveryServiceBleImpl.TAG;
            Log.e(str, "onScanFailed:" + i);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            String unused = DiscoveryServiceBleImpl.TAG;
            String str = "onScanResult:" + scanResult;
            byte[] serviceData = scanResult.getScanRecord().getServiceData(ParcelUuid.fromString(DiscoveryServiceBleImpl.MATTER_UUID));
            if (serviceData == null) {
                String unused2 = DiscoveryServiceBleImpl.TAG;
            } else if (!BleHelper.isServiceDataMatchSetupPayload(serviceData, DiscoveryServiceBleImpl.this.mDiscriminator, DiscoveryServiceBleImpl.this.mVendorId, DiscoveryServiceBleImpl.this.mProductId)) {
                String unused3 = DiscoveryServiceBleImpl.TAG;
            } else {
                BluetoothDevice device = scanResult.getDevice();
                if (DiscoveryServiceBleImpl.this.mDeviceDiscovered == device) {
                    Log.i(DiscoveryServiceBleImpl.TAG, "Device: " + device + " was already discovered.");
                    return;
                }
                Log.i(DiscoveryServiceBleImpl.TAG, "New device: " + device + " discovered");
                Log.i(DiscoveryServiceBleImpl.TAG, "stop scanning onScanResult");
                DiscoveryServiceBleImpl.this.mDeviceDiscovered = device;
                DiscoveryServiceBleImpl.this.mBluetoothLeScanner.stopScan(DiscoveryServiceBleImpl.this.leScanCallback);
                DiscoveryServiceBleImpl.this.mScanning = false;
                DiscoveryServiceBleImpl.this.metricsService.recordSuccessMetric(MatterEventType.PAIR_DEVICE_RESPONSE_SUCCESS);
                DiscoveryServiceBleImpl.this.metricsService.recordEventTime(DiscoveryServiceBleImpl.this.timer);
                DiscoveryServiceBleImpl.this.mEventBusHelper.sendMessageToEventBus(MatterEventType.PAIR_DEVICE_RESPONSE_SUCCESS, DiscoveryServiceBleImpl.GSON.toJson(new PairDeviceResponse(PairDeviceStatus.DEVICE_DISCOVERED)));
                DiscoveryServiceBleImpl discoveryServiceBleImpl = DiscoveryServiceBleImpl.this;
                discoveryServiceBleImpl.connectAndPair(discoveryServiceBleImpl.mDeviceDiscovered);
            }
        }
    };
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.matter.discovery.DiscoveryServiceBleImpl$2  reason: invalid class name */
    /* loaded from: classes9.dex */
    public class AnonymousClass2 extends BluetoothGattCallback {
        private static final int MAX_RETRIES_TO_CONNECT = 10;
        private static final int RETRY_TO_CONNECT_DURATION_IN_MILL = 100;
        private int retriesToConnect = 1;
        final /* synthetic */ MatterMetricsService val$metricsService;
        private BluetoothGattCallback wrappedCallback;

        AnonymousClass2(MatterMetricsService matterMetricsService) {
            this.val$metricsService = matterMetricsService;
            this.wrappedCallback = MatterClient.getMatterClient(DiscoveryServiceBleImpl.this.mContext).getCallback();
        }

        public /* synthetic */ void lambda$onConnectionStateChange$0$DiscoveryServiceBleImpl$2() {
            DiscoveryServiceBleImpl discoveryServiceBleImpl = DiscoveryServiceBleImpl.this;
            discoveryServiceBleImpl.connectAndPair(discoveryServiceBleImpl.mDeviceDiscovered);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            String unused = DiscoveryServiceBleImpl.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onCharacteristicChanged: ");
            outline107.append(bluetoothGattCharacteristic.getUuid());
            outline107.toString();
            this.wrappedCallback.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            String unused = DiscoveryServiceBleImpl.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onCharacteristicRead: ");
            outline107.append(bluetoothGattCharacteristic.getUuid());
            outline107.append(" -> ");
            outline107.append(i);
            outline107.toString();
            this.wrappedCallback.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            String unused = DiscoveryServiceBleImpl.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onCharacteristicWrite: ");
            outline107.append(bluetoothGattCharacteristic.getUuid());
            outline107.append(" -> ");
            outline107.append(i);
            outline107.toString();
            this.wrappedCallback.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            this.wrappedCallback.onConnectionStateChange(bluetoothGatt, i, i2);
            Log.i(DiscoveryServiceBleImpl.TAG, "onConnectionStateChange: status -> " + i + " newState:" + i2);
            if (i == 0) {
                if (i2 == 2) {
                    Log.i(DiscoveryServiceBleImpl.TAG, "Discover services");
                    bluetoothGatt.discoverServices();
                    return;
                } else if (i2 != 0) {
                    return;
                } else {
                    bluetoothGatt.close();
                    return;
                }
            }
            Log.e(DiscoveryServiceBleImpl.TAG, "Error on connect: status -> " + i);
            bluetoothGatt.close();
            if (this.retriesToConnect > 10) {
                String str = DiscoveryServiceBleImpl.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Fatal: Tried ");
                outline107.append(this.retriesToConnect);
                outline107.append(" times and failed to connect.");
                Log.e(str, outline107.toString());
                this.val$metricsService.recordErrorMetric(MatterEventType.PAIR_DEVICE_REQUEST, MatterErrorType.PAIR_DEVICE_CANNOT_CONNECT);
                this.val$metricsService.recordEventTime(DiscoveryServiceBleImpl.this.timer);
                DiscoveryServiceBleImpl.this.mEventBusHelper.sendMessageToEventBus(MatterEventType.PAIR_DEVICE_RESPONSE_ERROR, DiscoveryServiceBleImpl.GSON.toJson(new MatterError(MatterErrorType.PAIR_DEVICE_CANNOT_CONNECT, "")));
                return;
            }
            String str2 = DiscoveryServiceBleImpl.TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Retrying to connect: ");
            outline1072.append(this.retriesToConnect);
            Log.e(str2, outline1072.toString());
            DiscoveryServiceBleImpl.this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.matter.discovery.-$$Lambda$DiscoveryServiceBleImpl$2$jllL5Oiijjr-o8tSiAcnuwxgFHA
                @Override // java.lang.Runnable
                public final void run() {
                    DiscoveryServiceBleImpl.AnonymousClass2.this.lambda$onConnectionStateChange$0$DiscoveryServiceBleImpl$2();
                }
            }, 100L);
            this.retriesToConnect++;
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
            String unused = DiscoveryServiceBleImpl.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDescriptorRead: ");
            outline107.append(bluetoothGattDescriptor.getUuid());
            outline107.append(" -> ");
            outline107.append(i);
            outline107.toString();
            this.wrappedCallback.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            String unused = DiscoveryServiceBleImpl.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDescriptorWrite: ");
            outline107.append(bluetoothGattDescriptor.getUuid());
            outline107.append(" -> ");
            outline107.append(i);
            outline107.toString();
            this.wrappedCallback.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onMtuChanged(bluetoothGatt, i, i2);
            this.wrappedCallback.onMtuChanged(bluetoothGatt, i, i2);
            String unused = DiscoveryServiceBleImpl.TAG;
            String str = "onMtuChanged: " + i + " -> " + i2;
            Log.i(DiscoveryServiceBleImpl.TAG, "Start pairing with Matter device");
            MatterClient.getMatterClient(DiscoveryServiceBleImpl.this.mContext).setCompletionListener(new MatterDeviceCommissionListener(DiscoveryServiceBleImpl.this.mContext, DiscoveryServiceBleImpl.this.mNodeId, DiscoveryServiceBleImpl.this.mEventBusHelper));
            MatterClient.getMatterClient(DiscoveryServiceBleImpl.this.mContext).pairDevice(bluetoothGatt, DiscoveryServiceBleImpl.this.mFabricId, DiscoveryServiceBleImpl.this.mNodeId, DiscoveryServiceBleImpl.this.mPasscode);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onReadRemoteRssi(bluetoothGatt, i, i2);
            String unused = DiscoveryServiceBleImpl.TAG;
            String str = "onReadRemoteRssi: " + i + " -> " + i2;
            this.wrappedCallback.onReadRemoteRssi(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
            super.onReliableWriteCompleted(bluetoothGatt, i);
            String unused = DiscoveryServiceBleImpl.TAG;
            GeneratedOutlineSupport1.outline149("onReliableWriteCompleted: ", i);
            this.wrappedCallback.onReliableWriteCompleted(bluetoothGatt, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            this.wrappedCallback.onServicesDiscovered(bluetoothGatt, i);
            String unused = DiscoveryServiceBleImpl.TAG;
            String str = "onServicesDiscovered: status -> " + i;
            bluetoothGatt.requestMtu(131);
        }
    }

    public DiscoveryServiceBleImpl(Context context, EventBusHelper eventBusHelper, MatterMetricsService matterMetricsService) {
        this.mEventBusHelper = eventBusHelper;
        this.mContext = context;
        this.metricsService = matterMetricsService;
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null) {
            Log.e(TAG, "Bluetooth is not available");
            return;
        }
        this.mBluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
        this.mScanning = false;
        this.mBluetoothGattCallback = getBluetoothGattCallback();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectAndPair(BluetoothDevice bluetoothDevice) {
        String str = TAG;
        Log.i(str, "connecting to device: " + bluetoothDevice);
        int i = Build.VERSION.SDK_INT;
        bluetoothDevice.connectGatt(this.mContext, false, this.mBluetoothGattCallback, 2);
    }

    private BluetoothGattCallback getBluetoothGattCallback() {
        return new AnonymousClass2(this.metricsService);
    }

    @Override // com.amazon.matter.discovery.DiscoveryService
    public void discoverAndPairDevice(short s, short s2, short s3, long j, long j2, long j3, final MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        if (this.mScanning) {
            Log.i(TAG, "LE Scanning already in progress");
            return;
        }
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            this.mFabricId = j;
            this.mNodeId = j2;
            this.mPasscode = j3;
            this.timer = mobilyticsMetricsTimer;
            this.mDiscriminator = s;
            this.mVendorId = s2;
            this.mProductId = s3;
            this.mDeviceDiscovered = null;
            ScanSettings build = new ScanSettings.Builder().setScanMode(2).build();
            ArrayList arrayList = new ArrayList();
            Log.i(TAG, "start scanning");
            this.mScanning = true;
            this.mBluetoothLeScanner.startScan(arrayList, build, this.leScanCallback);
            this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.matter.discovery.-$$Lambda$DiscoveryServiceBleImpl$29NHYJfUAtvw03ZvIHtcpUByX9Q
                @Override // java.lang.Runnable
                public final void run() {
                    DiscoveryServiceBleImpl.this.lambda$discoverAndPairDevice$0$DiscoveryServiceBleImpl(mobilyticsMetricsTimer);
                }
            }, 5000L);
            return;
        }
        Log.e(TAG, "Bluetooth is either not available or not enabled");
        this.metricsService.recordErrorMetric(MatterEventType.PAIR_DEVICE_REQUEST, MatterErrorType.PAIR_DEVICE_BLUETOOTH_NOT_AVAILABLE_OR_NOT_ENABLED);
        this.metricsService.recordEventTime(mobilyticsMetricsTimer);
        this.mEventBusHelper.sendMessageToEventBus(MatterEventType.PAIR_DEVICE_RESPONSE_ERROR, GSON.toJson(new MatterError(MatterErrorType.PAIR_DEVICE_BLUETOOTH_NOT_AVAILABLE_OR_NOT_ENABLED, "")));
    }

    public /* synthetic */ void lambda$discoverAndPairDevice$0$DiscoveryServiceBleImpl(MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        Log.i(TAG, "stop scanning called.");
        if (this.mScanning) {
            this.mBluetoothLeScanner.stopScan(this.leScanCallback);
            this.mScanning = false;
            if (this.mDeviceDiscovered != null) {
                return;
            }
            this.metricsService.recordErrorMetric(MatterEventType.PAIR_DEVICE_REQUEST, MatterErrorType.PAIR_DEVICE_NO_DEVICE_DISCOVERED);
            this.metricsService.recordEventTime(mobilyticsMetricsTimer);
            this.mEventBusHelper.sendMessageToEventBus(MatterEventType.PAIR_DEVICE_RESPONSE_ERROR, GSON.toJson(new MatterError(MatterErrorType.PAIR_DEVICE_NO_DEVICE_DISCOVERED, "")));
        }
    }
}
